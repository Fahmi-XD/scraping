/**
 * Nah ini source kodenya dari github https://github.com/AyGemuy
 * tepatnya pada file https://github.com/AyGemuy/wudyver/blob/ac217bceff364161b310c56c8f4c760530009c23/pages/api/ai/music/soniva.js#L14
 */


import axios from "axios";
import {
    v4 as uuidv4
} from "uuid";
import {
    createHmac
} from "crypto";

class Soniva {
    constructor(userId = null) {
        this.SECRET_KEY = process.env.SONIVA_SECRET_KEY;
        this.deviceId = uuidv4();
        this.userId = userId;
        this.BASE_URL = "https://api.sonivamusic.com/musicai";
        this.DOWNLOAD_BASE_URL = "https://d2m6kf0jl6dhrs.cloudfront.net";
    }
    _sign(data) {
        const hmac = createHmac("sha256", this.SECRET_KEY);
        hmac.update(data, "utf8");
        return Buffer.from(hmac.digest()).toString("base64");
    }
    _headers(messageId, requestTime, xRequestId) {
        return {
            host: "api.sonivamusic.com",
            "x-request-id": xRequestId,
            "x-device-id": this.deviceId,
            "x-request-time": requestTime,
            "x-message-id": messageId,
            platform: "android",
            "x-app-version": "1.2.6",
            "x-country": "ID",
            "accept-language": "id-ID",
            "user-agent": "SonivaMusic/1.2.6 (build:70; Android 10; Xiaomi Redmi Note 5)",
            "content-type": "application/json; charset=utf-8",
            "accept-encoding": "gzip"
        };
    }
    async _ensureUserId(options) {
        const targetUserId = options.userId || this.userId;
        if (!targetUserId) {
            await this.reg();
        } else {
            if (options.userId) {
                this.userId = options.userId;
            }
            console.log(`🔄 Using existing User ID: ${this.userId}`);
        }
    }
    async reg() {
        console.log("🔄 Registering device...");
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const body = {
            device_id: this.deviceId,
            push_token: signature,
            message_id: messageId,
            AuthToken: signature
        };
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.post(`${this.BASE_URL}/v1/register`, body, {
                headers: headers
            });
            this.userId = response.data.user_id;
            console.log(`✅ Registered! User ID: ${this.userId}`);
            return response.data;
        } catch (error) {
            console.error("❌ Registration failed:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async genLyrics(options = {}) {
        await this._ensureUserId(options);
        console.log("🎵 Generating song from lyrics...");
        const requestTime = String(Date.now());
        const messageId = options.message_id || uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
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
            message_id: messageId
        };
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.post(`${this.BASE_URL}/v1/users/${this.userId}/songs/lyrics`, body, {
                headers: headers
            });
            console.log("✅ Song generation from lyrics started!");
            return {
                userId: this.userId,
                ...response.data
            };
        } catch (error) {
            console.error("❌ Generation from lyrics failed:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async genPrompt(options = {}) {
        await this._ensureUserId(options);
        console.log("🎵 Generating song from prompt...");
        const requestTime = String(Date.now());
        const messageId = options.message_id || uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
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
            message_id: messageId
        };
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.post(`${this.BASE_URL}/v1/users/${this.userId}/songs/prompt`, body, {
                headers: headers
            });
            console.log("✅ Song generation from prompt started!");
            return {
                userId: this.userId,
                ...response.data
            };
        } catch (error) {
            console.error("❌ Generation from prompt failed:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async getSongStatus(jobId) {
        console.log(`🔎 Fetching song status for jobId: ${jobId}...`);
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.get(`${this.BASE_URL}/v1/songs/${jobId}`, {
                headers: headers
            });
            console.log(`✅ Song status fetched for jobId: ${jobId}`);
            return response.data;
        } catch (error) {
            console.error("❌ Failed to fetch song status:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async list({
        userId,
        page = 1,
        limit = 90,
        sortAsc = false
    } = {}) {
        console.log("📋 Fetching song list...");
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const headers = this._headers(messageId, requestTime, signature);
        const targetUserId = userId || this.userId;
        if (!targetUserId) {
            throw new Error("User ID is required to list songs.");
        }
        try {
            const response = await axios.get(`${this.BASE_URL}/v1/users/${targetUserId}/library?page=${page}&limit=${limit}&sortAsc=${sortAsc}`, {
                headers: headers
            });
            console.log(`✅ Found ${response.data.songs?.length || 0} songs`);
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
        console.log(`🌍 Fetching recent explore songs (page: ${page}, limit: ${limit})...`);
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.get(`${this.BASE_URL}/v1/explore/recent?page=${page}&limit=${limit}`, {
                headers: headers
            });
            console.log(`✅ Fetched ${response.data.songs?.length || 0} recent explore songs`);
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
        console.log(`🔥 Fetching trending explore songs (page: ${page}, limit: ${limit}, range: ${range})...`);
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.get(`${this.BASE_URL}/v1/explore/trending?page=${page}&limit=${limit}&range=${range}`, {
                headers: headers
            });
            console.log(`✅ Fetched ${response.data.songs?.length || 0} trending explore songs`);
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
        console.log(`⭐ Fetching popular explore songs (page: ${page}, limit: ${limit}, range: ${range})...`);
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.get(`${this.BASE_URL}/v1/explore/popular?page=${page}&limit=${limit}&range=${range}`, {
                headers: headers
            });
            console.log(`✅ Fetched ${response.data.songs?.length || 0} popular explore songs`);
            return response.data;
        } catch (error) {
            console.error("❌ Failed to fetch popular explore songs:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async updateFCMToken(fcmToken) {
        console.log("🔄 Updating FCM token...");
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const body = {
            fcm_token: fcmToken
        };
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.patch(`${this.BASE_URL}/v1/register`, body, {
                headers: headers
            });
            console.log("✅ FCM token updated successfully!");
            return response.data;
        } catch (error) {
            console.error("❌ Failed to update FCM token:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async getUserInfo(userId = this.userId) {
        console.log(`ℹ️ Fetching info for user ID: ${userId}...`);
        if (!userId) {
            throw new Error("User ID is required to get user info.");
        }
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.get(`${this.BASE_URL}/v1/users/${userId}/info`, {
                headers: headers
            });
            console.log("✅ User info fetched!");
            return response.data;
        } catch (error) {
            console.error("❌ Failed to get user info:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async updateSongTitle(songId, newTitle, userId = this.userId) {
        console.log(`📝 Updating title for song ID: ${songId} to "${newTitle}"...`);
        if (!userId) {
            throw new Error("User ID is required to update song title.");
        }
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const body = {
            song_id: songId,
            title: newTitle
        };
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.patch(`${this.BASE_URL}/v1/users/${userId}/songs/title`, body, {
                headers: headers
            });
            console.log("✅ Song title updated successfully!");
            return response.data;
        } catch (error) {
            console.error("❌ Failed to update song title:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async deleteSongs(songIds, userId = this.userId) {
        console.log(`🗑️ Deleting songs: ${songIds.join(", ")}...`);
        if (!userId) {
            throw new Error("User ID is required to delete songs.");
        }
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const body = {
            song_ids: songIds
        };
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.delete(`${this.BASE_URL}/v1/users/${userId}/library`, {
                headers: headers,
                data: body
            });
            console.log("✅ Songs deleted successfully!");
            return response.data;
        } catch (error) {
            console.error("❌ Failed to delete songs:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async separateSong(songId, stems, userId = this.userId) {
        console.log(`✂️ Initiating separation for song ID: ${songId}, stems: ${stems.join(", ")}...`);
        if (!userId) {
            throw new Error("User ID is required to separate songs.");
        }
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const body = {
            stems: stems
        };
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.post(`${this.BASE_URL}/v1/users/${userId}/songs/${songId}/separate`, body, {
                headers: headers
            });
            console.log("✅ Song separation initiated!");
            return response.data;
        } catch (error) {
            console.error("❌ Failed to initiate song separation:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async getSeparationStatus(jobId) {
        console.log(`⏳ Checking separation status for jobId: ${jobId}...`);
        const requestTime = String(Date.now());
        const messageId = uuidv4();
        const dataToSign = `${this.deviceId}${messageId}${requestTime}`;
        const signature = this._sign(dataToSign);
        const headers = this._headers(messageId, requestTime, signature);
        try {
            const response = await axios.get(`${this.BASE_URL}/v1/songs/separate/${jobId}`, {
                headers: headers
            });
            console.log(`✅ Separation status for jobId ${jobId}: ${response.data.status}`);
            return response.data;
        } catch (error) {
            console.error("❌ Failed to get separation status:", error.response?.status, error.response?.data);
            throw error;
        }
    }
    async dl({
        songPath = "0a86eceb-2722-4b47-a32b-90b893160a42.mp3"
    } = {}) {
        console.log(`⬇️ Downloading: ${songPath}`);
        const headers = {
            "icy-metadata": "1",
            "accept-encoding": "identity",
            "user-agent": "Dalvik/2.1.0 (Linux; U; Android 10; Redmi Note 5 Build/QQ3A.200805.001)",
            host: "d2m6kf0jl6dhrs.cloudfront.net",
            connection: "Keep-Alive"
        };
        try {
            const response = await axios.get(`${this.DOWNLOAD_BASE_URL}/song/${songPath}`, {
                headers: headers,
                responseType: "arraybuffer"
            });
            console.log(`✅ Downloaded ${(response.data.byteLength / 1024 / 1024).toFixed(2)} MB`);
            return response.data;
        } catch (error) {
            console.error("❌ Download failed:", error.response?.status);
            throw error;
        }
    }
}

async function soniva() {
    const {
        action,
        userId,
        ...params
    } = {
        action: "gen",
        userId: "",
        mood: "Happy",
        genre: "EDM",
        has_vocal: false,
        vocal_gender: null,
        record_type: null,
        prompt: "Write an empowering anthem for overcoming the challenges of moving to a new city, embracing the excitement and fears, and finding new beginnings",
        is_dual_song_enabled: true,
    };
    const soniva = new Soniva(userId);
    try {
        let result;
        switch (action) {
            case "gen":
                if (params.lyrics) {
                    result = await soniva.genLyrics({
                        userId: userId,
                        ...params
                    });
                } else if (params.prompt) {
                    result = await soniva.genPrompt({
                        userId: userId,
                        ...params
                    });
                } else {
                    return console.log({
                        message: "Parameter 'lyrics' or 'prompt' must be provided for song generation."
                    });
                }
                break;
            case "status":
                if (!params.jobId) {
                    return console.log({
                        message: "Parameter 'jobId' is required to get song status."
                    });
                }
                result = await soniva.getSongStatus(params.jobId);
                break;
            case "list":
                if (!userId && !soniva.userId) {
                    return console.log({
                        message: "No userId provided"
                    });
                }
                result = await soniva.list({
                    userId: userId,
                    ...params
                });
                break;
            case "recent":
                result = await soniva.getRecentExplore(params);
                break;
            case "trending":
                result = await soniva.getTrendingExplore(params);
                break;
            case "popular":
                result = await soniva.getPopularExplore(params);
                break;
            case "fcm":
                if (!params.fcmToken) {
                    return console.log({
                        message: "Parameter 'fcmToken' is required to update FCM token."
                    });
                }
                result = await soniva.updateFCMToken(params.fcmToken);
                break;
            case "user_info":
                if (!userId) {
                    return console.log({
                        message: "Parameter 'userId' is required to get user info."
                    });
                }
                result = await soniva.getUserInfo(userId);
                break;
            case "up_title":
                if (!params.songId || !params.newTitle) {
                    return console.log({
                        message: "Parameters 'songId' and 'newTitle' are required to update song title."
                    });
                }
                result = await soniva.updateSongTitle(params.songId, params.newTitle, userId);
                break;
            case "del_song":
                if (!params.songIds || !Array.isArray(params.songIds) || params.songIds.length === 0) {
                    return console.log({
                        message: "Parameter 'songIds' (an array of song IDs) is required to delete songs."
                    });
                }
                result = await soniva.deleteSongs(params.songIds, userId);
                break;
            case "sp_song":
                if (!params.songId || !params.stems || !Array.isArray(params.stems) || params.stems.length === 0) {
                    return console.log({
                        message: "Parameters 'songId' and 'stems' (an array of stems) are required to separate a song."
                    });
                }
                result = await soniva.separateSong(params.songId, params.stems, userId);
                break;
            case "sp_status":
                if (!params.jobId) {
                    return console.log({
                        message: "Parameter 'jobId' is required to get separation status."
                    });
                }
                result = await soniva.getSeparationStatus(params.jobId);
                break;
            case "dl":
                if (!params.songPath) {
                    return console.log({
                        message: "No songPath provided"
                    });
                }
                result = await soniva.dl(params);
                break;
            default:
                return console.log({
                    error: "Action tidak valid. Gunakan ?action=gen, ?action=status, ?action=list, ?action=recent, ?action=trending, ?action=popular, ?action=fcm, ?action=user_info, ?action=up_title, ?action=del_song, ?action=sp_song, ?action=sp_status, atau ?action=dl"
                });
        }
        return console.log(result);
    } catch (error) {
        console.error("Handler error:", error);
        console.log({
            error: error.message || "Internal Server Error",
            details: error.response?.data || null
        });
    }
}

soniva().then(x => console.log(x));