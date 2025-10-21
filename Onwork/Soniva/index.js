/**
 * Soniva Music AI Scraper
 *
 * 🚨 Penting! Harap diperhatikan 🚨
 * Logika untuk membuat **signature** dan mendapatkan **kunci API** yang valid
 * sebagian besar bukan dikembangkan sendiri, melainkan berasal dari
 * repository milik **AyGemuy** (https://github.com/AyGemuy/wudyver).
 *
 * Saya berkontribusi pada sebagian kecil (~5%) dari bagian kriptografi
 * yang digunakan untuk scraping, sedangkan struktur dan implementasi dasarnya
 * mengikuti kode asli dari repository tersebut.
 *
 * Semua kredit atas keberhasilan pembuatan header dan signature yang valid
 * tetap diberikan kepada **@AyGemuy**. Terima kasih.
 * 
 * - Claude AI ( Agent )
 */



import axios from "axios";
import crypto from "crypto";
import { v4 as uuidv4 } from "uuid";

class Func {
    static BASE_URL = "https://api.sonivamusic.com/musicai"
    static DOWNLOAD_BASE_URL = "https://d2m6kf0jl6dhrs.cloudfront.net"
    static JAVA_BYTE_KEY = [9, -68, 74, 103, -109, 76, 23, -83, -95, -36, 42, 35, 77, 77, 59, 59, 16, -117, 112, 47, -109, 65, -74, -86, 60, -100, 22, 87, 22, 46, -78, 86, -34, -5, -56, -124, 31, 57, 72, 117, -22, -50, -92, 93, 29, 125, -11, 126, -13, 40, 51, -94, -69, -79, 17, -109, 25, 33, 100, -115, 27, 127, -47, 78]
    static DECRYPTION_PARAM_1 = Uint8Array.from([94, 86, 68, 22, 67, 88, 1, 95, 13, 82, 30, 72, 8, 8, 91, 1]);
    static DECRYPTION_PARAM_2 = Uint8Array.from([94, 81, 94, 10, 92, 28, 71, 87, 78, 2, 9, 10, 72, 14, 92, 27, 92, 14, 4, 15]);
    static ENCRYPTED_KEY_BYTES = this.JAVA_BYTE_KEY.map((val) => val < 0 ? 256 + val : val);
    static CHARSET = 'utf-8'
    static DEVICE_ID = uuidv4();
    static isLogEnabled = false

    /**
     * Prints a log message to the console if logging is enabled.
     * @param {string} processName - The name of the process or function logging the message.
     * @param {string} msg - The message content to log.
     */
    static logMessage(processName, msg) {
        if (this.isLogEnabled) {
            console.log(`[${processName}] ${msg}`);
        }
    }

    /**
     * Performs an XOR operation on the input bytes using a hardcoded key.
     * @param {Buffer | Uint8Array} inputBytes - The bytes to be XORed.
     * @returns {Buffer} The resulting XORed bytes.
     */
    static xorWithStaticKey(inputBytes) {
        const key = Buffer.from("3a1c2ou68jox9dlj3v", this.CHARSET);
        const output = Buffer.alloc(inputBytes.length);
        for (let i = 0; i < inputBytes.length; i++) {
            output[i] = inputBytes[i] ^ key[i % key.length];
        }
        return output;
    }

    /**
     * Concatenates three strings.
     * @param {string} str1 - The first string.
     * @param {string} str2 - The second string.
     * @param {string} str3 - The third string.
     * @returns {string} The concatenated string.
     */
    static concatenateStrings(str1, str2, str3) {
        return str1 + str2 + str3;
    }

    /**
     * Extracts a portion (slice) of a Uint8Array or Buffer.
     * @param {Uint8Array | Buffer} arr - The array to slice.
     * @param {number} start - The starting index (inclusive).
     * @param {number} end - The ending index (exclusive).
     * @returns {Buffer} The extracted slice as a Buffer.
     */
    static sliceBytes(arr, start, end) {
        return Buffer.from(arr.slice(start, end));
    }

    /**
     * Decrypts a byte array using a SHA-512 based XOR mechanism.
     * @param {Buffer | Uint8Array} encryptedBytes - The encrypted bytes to decrypt.
     * @returns {Buffer} The decrypted bytes.
     */
    static decryptBytes(encryptedBytes) {
        const length = encryptedBytes.length;

        const xorParam1Decrypted = this.xorWithStaticKey(this.DECRYPTION_PARAM_1);
        const xorParam2Decrypted = this.xorWithStaticKey(this.DECRYPTION_PARAM_2);

        const stringForHash = this.concatenateStrings(
            xorParam1Decrypted.toString("utf-8"),
            xorParam2Decrypted.toString("utf-8"),
            "com.sonivamusic.ai"
        );

        const hash = crypto.createHash("sha512").update(Buffer.from(stringForHash, "utf-8")).digest();
        const hashSlice = this.sliceBytes(hash, 0, length);

        const result = Buffer.alloc(encryptedBytes.length);
        for (let i = 0; i < encryptedBytes.length; i++) {
            result[i] = encryptedBytes[i] ^ hashSlice[i];
        }

        return result;
    }

    /**
     * Decrypts a constant string part.
     * @returns {string} The decrypted string part.
     */
    static getDecryptedStringPart() {
        const part1 = this.xorWithStaticKey([112, 49, 100, 47, 2, 63, 58, 71]).toString(this.CHARSET);
        const part2 = this.xorWithStaticKey([105, 88, 83, 59, 118, 11, 54, 80]).toString(this.CHARSET);
        return part1 + part2;
    }

    /**
     * Combines three input strings with a decrypted constant part in a specific order.
     * @param {string} s1 - x-device-id.
     * @param {string} s2 - x-message-id.
     * @param {string} s3 - x-request-time.
     * @returns {string} The combined string used for HMAC-SHA256 signing.
     */
    static formatSignatureString(s1, s2, s3) {
        if (!s1 || !s2 || !s3) throw new Error("Signature string parameters cannot be null or empty.");
        return s2 + this.getDecryptedStringPart() + s1 + s3;
    }

    /**
     * Generates an HMAC-SHA256 signature from a formatted string using a decrypted secret key.
     * @param {string} deviceId - The x-device-id.
     * @param {string} messageId - The x-message-id.
     * @param {string} requestTime - The x-request-time.
     * @returns {string} The Base64 encoded HMAC-SHA256 signature.
     */
    static generateHmacSha256Signature(deviceId, messageId, requestTime) {
        if (!deviceId || !messageId || !requestTime) {
            throw new Error("Signature parameters cannot be null or empty.");
        }

        const stringToSign = this.formatSignatureString(deviceId, messageId, requestTime);

        try {
            const secretKey = this.decryptBytes(this.ENCRYPTED_KEY_BYTES);

            const mac = crypto.createHmac('sha256', secretKey);

            return mac.update(Buffer.from(stringToSign, 'utf-8')).digest('base64');
        } catch (e) {
            console.error("Error generating HMAC-SHA256 signature:", e);
            throw new Error("Failed to generate HMAC signature.");
        }
    }

    /**
     * Generates the necessary headers for an API request, including the signature.
     * @returns {object} An object containing the required headers.
     */
    static getApiHeaders() {
        try {
            const deviceId = this.DEVICE_ID;
            const messageId = uuidv4();
            const requestTime = String(Date.now());

            const xSignatureID = this.generateHmacSha256Signature(deviceId, messageId, requestTime)
            this.logMessage("x-signature-id", xSignatureID)

            const headers = {
                "x-signature-id": xSignatureID,
                "x-device-id": deviceId,
                "x-request-time": requestTime,
                "x-message-id": messageId,
                "platform": "android",
                "x-app-version": "1.2.6",
                "x-country": "US",
                "accept-language": "en-US",
                "user-agent": "SonivaMusic/1.2.6 (build:70; Android 10; Xiaomi Redmi Note 5)",
                "content-type": "application/json; charset=utf-8",
                "accept-encoding": "gzip",
                "host": "api.sonivamusic.com",
            }

            this.logMessage("getApiHeaders", JSON.stringify(headers, null, 2))

            return headers;
        } catch (error) {
            console.error("Error generating API headers:", error)
            throw error;
        }
    }
}

class Soniva {
    constructor(userId = null) {
        this.userId = userId;
    }

    /**
     * Ensures a user ID exists by either using the provided one or registering a new device.
     * @param {object} options - Options object which might contain a new userId.
     */
    async _ensureUserId(options) {
        const targetUserId = options.userId || this.userId;
        if (!targetUserId) {
            await this.reg();
            await new Promise((resolve) => setTimeout(() => {
              resolve()
            }, 5_000))
        } else {
            if (options.userId) {
                this.userId = options.userId;
            }
            Func.logMessage("Soniva", `🔄 Using existing User ID: ${this.userId}`);
        }
    }

    /**
     * Registers the device to get a new user ID.
     * @returns {Promise<object>} The registration response data.
     */
    async reg() {
        Func.logMessage("Soniva", "🔄 Registering device...");
        const headers = Func.getApiHeaders();

        const body = {
            device_id: headers["x-device-id"],
            push_token: "c1297x4TQVKCGjRvVoU-SN:APA91bGvoTvXHV9zGnfC4Y7QMGA4pRgDuRYVB53LV4ntz2gZB55tsUt2Q7AGS098vhi4QStxOdu3cW4QW6Vau6gNLEtTwQFvE1sShbARu4g9mGowYmJC89I",
            message_id: headers["x-message-id"],
            // AuthToken: headers["x-signature-id"]
        };

        try {
            const response = await axios.post(`${Func.BASE_URL}/v1/register`, body, {
                headers: headers,
                body,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Register", JSON.stringify(response.data));
            this.userId = response.data.user_id;
            Func.logMessage("Soniva", `✅ Registered! User ID: ${this.userId}`);
            return response.data;
        } catch (error) {
            console.error("❌ Registration failed:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    /**
     * Generates a song based on lyrics input.
     * @param {object} options - Generation parameters including lyrics, mood, genre, etc.
     * @returns {Promise<object>} The song generation job information.
     */
    async genLyrics(options = {}) {
        await this._ensureUserId(options);
        Func.logMessage("Soniva", "🎵 Generating song from lyrics...");

        const headers = Func.getApiHeaders();
        const {
            userId: _,
            ...bodyOptions
        } = options;

        const body = {
            mood: "Romantic,Motivational,Melancholic",
            genre: "Electro Pop",
            has_vocal: false,
            vocal_gender: "male",
            record_type: "live",
            title: "cinta",
            is_dual_song_enabled: true,
            ...bodyOptions,
            lyrics: options.lyrics,
            message_id: headers["x-message-id"]
        };

        try {
            const response = await axios.post(`${Func.BASE_URL}/v1/users/${this.userId}/songs/lyrics`, body, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", "✅ Song generation from lyrics started!");
            return {
                userId: this.userId,
                ...response.data
            };
        } catch (error) {
            console.error("❌ Generation from lyrics failed:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    /**
     * Generates a song based on a text prompt.
     * @param {object} options - Generation parameters including prompt, mood, genre, etc.
     * @returns {Promise<object>} The song generation job information.
     */
    async genPrompt(options = {}) {
        await this._ensureUserId(options);
        Func.logMessage("Soniva", "🎵 Generating song from prompt...");

        const headers = Func.getApiHeaders();
        const {
            userId: _,
            ...bodyOptions
        } = options;

        const body = {
            mood: "Romantic,Motivational,Melancholic",
            genre: "Electro Pop",
            has_vocal: false,
            vocal_gender: "male",
            record_type: "live",
            is_dual_song_enabled: true,
            ...bodyOptions,
            prompt: options.prompt,
            message_id: headers["x-message-id"]
        };

        try {
            const response = await axios.post(`${Func.BASE_URL}/v1/users/${this.userId}/songs/prompt`, body, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", "✅ Song generation from prompt started!");
            return {
                userId: this.userId,
                ...response.data
            };
        } catch (error) {
            console.error("❌ Generation from prompt failed:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    /**
     * Checks the status of a song generation job.
     * @param {string} jobId - The job ID for the song generation.
     * @returns {Promise<object>} The song status data.
     */
    async getSongStatus(jobId) {
        Func.logMessage("Soniva", `🔎 Fetching song status for jobId: ${jobId}...`);
        const headers = Func.getApiHeaders();

        try {
            const response = await axios.get(`${Func.BASE_URL}/v1/songs/${jobId}`, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", `✅ Song status fetched for jobId: ${jobId}`);
            return response.data;
        } catch (error) {
            console.error("❌ Failed to fetch song status:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    /**
     * Lists the songs in a user's library.
     * @param {object} options - Pagination and sorting options.
     * @returns {Promise<object>} The list of songs.
     */
    async list({
        userId,
        page = 1,
        limit = 90,
        sortAsc = false
    } = {}) {
        Func.logMessage("Soniva", "📋 Fetching song list...");
        const headers = Func.getApiHeaders();
        const targetUserId = userId || this.userId;

        if (!targetUserId) {
            throw new Error("User ID is required to list songs.");
        }

        try {
            const response = await axios.get(`${Func.BASE_URL}/v1/users/${targetUserId}/library?page=${page}&limit=${limit}&sortAsc=${sortAsc}`, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", `✅ Found ${response.data.songs?.length || 0} songs`);
            return response.data;
        } catch (error) {
            console.error("❌ List fetch failed:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    async getRecentExplore({
        page = 1,
        limit = 90
    } = {}) {
        Func.logMessage("Soniva", `🌍 Fetching recent explore songs (page: ${page}, limit: ${limit})...`);
        const headers = Func.getApiHeaders();
        try {
            const response = await axios.get(`${Func.BASE_URL}/v1/explore/recent?page=${page}&limit=${limit}`, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", `✅ Fetched ${response.data.songs?.length || 0} recent explore songs`);
            return response.data;
        } catch (error) {
            console.error("❌ Failed to fetch recent explore songs:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    async getTrendingExplore({
        page = 1,
        limit = 90,
        range = "daily"
    } = {}) {
        Func.logMessage("Soniva", `🔥 Fetching trending explore songs (page: ${page}, limit: ${limit}, range: ${range})...`);
        const headers = Func.getApiHeaders();
        try {
            const response = await axios.get(`${Func.BASE_URL}/v1/explore/trending?page=${page}&limit=${limit}&range=${range}`, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", `✅ Fetched ${response.data.songs?.length || 0} trending explore songs`);
            return response.data;
        } catch (error) {
            console.error("❌ Failed to fetch trending explore songs:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    async getPopularExplore({
        page = 1,
        limit = 90,
        range = "daily"
    } = {}) {
        Func.logMessage("Soniva", `⭐ Fetching popular explore songs (page: ${page}, limit: ${limit}, range: ${range})...`);
        const headers = Func.getApiHeaders();
        try {
            const response = await axios.get(`${Func.BASE_URL}/v1/explore/popular?page=${page}&limit=${limit}&range=${range}`, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", `✅ Fetched ${response.data.songs?.length || 0} popular explore songs`);
            return response.data;
        } catch (error) {
            console.error("❌ Failed to fetch popular explore songs:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    async updateFCMToken(fcmToken) {
        Func.logMessage("Soniva", "🔄 Updating FCM token...");
        const headers = Func.getApiHeaders();
        const body = {
            fcm_token: fcmToken
        };
        try {
            const response = await axios.patch(`${Func.BASE_URL}/v1/register`, body, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", "✅ FCM token updated successfully!");
            return response.data;
        } catch (error) {
            console.error("❌ Failed to update FCM token:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    async getUserInfo(userId = this.userId) {
        Func.logMessage("Soniva", `ℹ️ Fetching info for user ID: ${userId}...`);
        if (!userId) {
            throw new Error("User ID is required to get user info.");
        }
        const headers = Func.getApiHeaders();
        try {
            const response = await axios.get(`${Func.BASE_URL}/v1/users/${userId}/info`, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", "✅ User info fetched!");
            return response.data;
        } catch (error) {
            console.error("❌ Failed to get user info:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    async updateSongTitle(songId, newTitle, userId = this.userId) {
        Func.logMessage("Soniva", `📝 Updating title for song ID: ${songId} to "${newTitle}"...`);
        if (!userId) {
            throw new Error("User ID is required to update song title.");
        }
        const headers = Func.getApiHeaders();
        const body = {
            song_id: songId,
            title: newTitle
        };
        try {
            const response = await axios.patch(`${Func.BASE_URL}/v1/users/${userId}/songs/title`, body, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", "✅ Song title updated successfully!");
            return response.data;
        } catch (error) {
            console.error("❌ Failed to update song title:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    async deleteSongs(songIds, userId = this.userId) {
        Func.logMessage("Soniva", `🗑️ Deleting songs: ${songIds.join(", ")}...`);
        if (!userId) {
            throw new Error("User ID is required to delete songs.");
        }
        const headers = Func.getApiHeaders();
        const body = {
            song_ids: songIds
        };
        try {
            const response = await axios.delete(`${Func.BASE_URL}/v1/users/${userId}/library`, {
                headers: headers,
                validateStatus: (status) => status < 500,
                data: body
            });
            Func.logMessage("Soniva", "✅ Songs deleted successfully!");
            return response.data;
        } catch (error) {
            console.error("❌ Failed to delete songs:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    async separateSong(songId, stems, userId = this.userId) {
        Func.logMessage("Soniva", `✂️ Initiating separation for song ID: ${songId}, stems: ${stems.join(", ")}...`);
        if (!userId) {
            throw new Error("User ID is required to separate songs.");
        }
        const headers = Func.getApiHeaders();
        const body = {
            stems: stems
        };
        try {
            const response = await axios.post(`${Func.BASE_URL}/v1/users/${userId}/songs/${songId}/separate`, body, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", "✅ Song separation initiated!");
            return response.data;
        } catch (error) {
            console.error("❌ Failed to initiate song separation:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    async getSeparationStatus(jobId) {
        Func.logMessage("Soniva", `⏳ Checking separation status for jobId: ${jobId}...`);
        const headers = Func.getApiHeaders();
        try {
            const response = await axios.get(`${Func.BASE_URL}/v1/songs/separate/${jobId}`, {
                headers: headers,
                validateStatus: (status) => status < 500
            });
            Func.logMessage("Soniva", `✅ Separation status for jobId ${jobId}: ${response.data.status}`);
            return response.data;
        } catch (error) {
            console.error("❌ Failed to get separation status:", error.response?.status, error.response?.data);
            throw error;
        }
    }

    async dl({
        songPath = "0a86eceb-2722-4b47-a32b-90b893160a42.mp3"
    } = {}) {
        Func.logMessage("Soniva", `⬇️ Downloading: ${songPath}`);
        const headers = {
            "icy-metadata": "1",
            "accept-encoding": "identity",
            "user-agent": "Dalvik/2.1.0 (Linux; U; Android 10; Redmi Note 5 Build/QQ3A.200805.001)",
            "host": "d2m6kf0jl6dhrs.cloudfront.net",
            "connection": "Keep-Alive"
        };
        try {
            const response = await axios.get(`${Func.DOWNLOAD_BASE_URL}/song/${songPath}`, {
                headers: headers,
                validateStatus: (status) => status < 500,
                responseType: "arraybuffer"
            });
            Func.logMessage("Soniva", `✅ Downloaded ${(response.data.byteLength / 1024 / 1024).toFixed(2)} MB`);
            return response.data;
        } catch (error) {
            console.error("❌ Download failed:", error.response?.status);
            throw error;
        }
    }
}

/**
 * Main function to execute the API interaction logic based on action and parameters.
 * @param {object} params - Parameters for the API function.
 * @param {string} params.action - The action to perform (e.g., 'gen', 'status', 'list').
 * @param {string} [params.userId] - Optional User ID to use/set.
 * @param {boolean} log - Flag to enable or disable logging in the Func class.
 * @returns {Promise<object | void>} The result of the API call.
 */
async function mainApiHandler({
    action,
    userId,
    ...params
}, log) {
    Func.isLogEnabled = log;
    const sonivaInstance = new Soniva(userId);

    try {
        let result;
        switch (action) {
            case "reg":
                result = await sonivaInstance.reg();
                break;
            case "gen":
                if (params.lyrics) {
                    result = await sonivaInstance.genLyrics({
                        userId: userId,
                        ...params
                    });
                } else if (params.prompt) {
                    result = await sonivaInstance.genPrompt({
                        userId: userId,
                        ...params
                    });
                } else {
                    return {
                        message: "Parameter 'lyrics' or 'prompt' must be provided for song generation."
                    };
                }
                break;
            case "status":
                if (!params.jobId) {
                    return {
                        message: "Parameter 'jobId' is required to get song status."
                    };
                }
                result = await sonivaInstance.getSongStatus(params.jobId);
                break;
            case "list":
                result = await sonivaInstance.list({
                    userId: userId,
                    ...params
                });
                break;
            case "recent":
                result = await sonivaInstance.getRecentExplore(params);
                break;
            case "trending":
                result = await sonivaInstance.getTrendingExplore(params);
                break;
            case "popular":
                result = await sonivaInstance.getPopularExplore(params);
                break;
            case "fcm":
                if (!params.fcmToken) {
                    return {
                        message: "Parameter 'fcmToken' is required to update FCM token."
                    };
                }
                result = await sonivaInstance.updateFCMToken(params.fcmToken);
                break;
            case "user_info":
                if (!userId) {
                    return {
                        message: "Parameter 'userId' is required to get user info."
                    };
                }
                result = await sonivaInstance.getUserInfo(userId);
                break;
            case "up_title":
                if (!params.songId || !params.newTitle) {
                    return {
                        message: "Parameters 'songId' and 'newTitle' are required to update song title."
                    };
                }
                result = await sonivaInstance.updateSongTitle(params.songId, params.newTitle, userId);
                break;
            case "del_song":
                if (!params.songIds || !Array.isArray(params.songIds) || params.songIds.length === 0) {
                    return {
                        message: "Parameter 'songIds' (an array of song IDs) is required to delete songs."
                    };
                }
                result = await sonivaInstance.deleteSongs(params.songIds, userId);
                break;
            case "sp_song":
                if (!params.songId || !params.stems || !Array.isArray(params.stems) || params.stems.length === 0) {
                    return {
                        message: "Parameters 'songId' and 'stems' (an array of stems) are required to separate a song."
                    };
                }
                result = await sonivaInstance.separateSong(params.songId, params.stems, userId);
                break;
            case "sp_status":
                if (!params.jobId) {
                    return {
                        message: "Parameter 'jobId' is required to get separation status."
                    };
                }
                result = await sonivaInstance.getSeparationStatus(params.jobId);
                break;
            case "dl":
                if (!params.songPath) {
                    return {
                        message: "No songPath provided"
                    };
                }
                result = await sonivaInstance.dl(params);
                break;
            default:
                return {
                    error: "Invalid action. Use 'reg', 'gen', 'status', 'list', 'recent', 'trending', 'popular', 'fcm', 'user_info', 'up_title', 'del_song', 'sp_song', 'sp_status', or 'dl'."
                };
        }
        return result;
    } catch (error) {
        console.error("Handler error:", error);
        return {
            error: error.message || "Internal Server Error",
            details: error.response?.data || null
        };
    }
}

/**
 * Contoh
 */
(async () => {
    const config = {
        action: "gen", // Ubah ke 'status', 'list', 'dl', dll untuk menjalankan fitur lain
        userId: "", // Taruh user_id disini, jika ingin membuat userId baru, kosongkan saja => ''

        // Parameter untuk generate berdasarkan prompt
        mood: "Happy",
        genre: "EDM",
        has_vocal: true,
        vocal_gender: "female",
        record_type: "live",
        prompt: "A futuristic cyberpunk track with heavy bass, neon-lit synth melodies, and a sense of urgent pursuit.",
        is_dual_song_enabled: true,

        // Contoh untuk action: 'reg' ini untuk register
        // Contoh untuk action: 'status' berarti parameternya =>  jobId: "YOUR_JOB_ID_HERE"
        // Contoh untuk action: 'list' berarti parameternya =>  page: 1, limit: 10
        // Contoh untuk action: 'dl' berarti parameternya =>  songPath: "SONG_FILE_PATH.mp3"
        // Contoh untuk action: 'del_song' berarti parameternya =>  songIds: ["SONG_ID_1", "SONG_ID_2"]
        // ... dan action lainnya, pokonya sama dengan source aslinya yang di github https://github.com/AyGemuy/wudyver
    };
    // ---------------------------------

    console.log(`🚀 Starting: ${config.action}`);
    const response = await mainApiHandler(config, true); // true untuk mengaktifkan logging

    console.log(response);
})();
