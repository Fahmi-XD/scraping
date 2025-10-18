import axios from "axios";
import crypto from "crypto";

class Util {
    /**
     * Decrypt a Base64 encoded AES string.
     *
     * @param {string} strToDecrypt - The encrypted string in Base64.
     * @param {string} secret - The 16+ character secret key.
     * @returns {string} Decrypted plaintext.
     * @throws {Error} If decryption fails.
     */
    static decrypt(strToDecrypt, secret) {
        if (typeof strToDecrypt !== 'string' || typeof secret !== 'string') {
            throw new TypeError('Both strToDecrypt and secret must be strings.');
        }

        try {
            const key = Buffer.from(secret, 'utf-8');
            const iv = Buffer.from(secret.substring(0, 16), 'utf-8');
            const encryptedText = Buffer.from(strToDecrypt, 'base64');

            const decipher = crypto.createDecipheriv('aes-256-cbc', key, iv);
            let decrypted = decipher.update(encryptedText, null, 'utf8');
            decrypted += decipher.final('utf8');

            return decrypted.replace(/\n/g, '');
        } catch (error) {
            throw new Error(`Decryption failed: ${error.message}`);
        }
    }

    /**
     * Encrypt a string using AES and encode it with Base64.
     *
     * @param {string} strToEncrypt - The plaintext string to encrypt.
     * @param {string} secret - The 16+ character secret key.
     * @returns {string} Encrypted Base64 string.
     * @throws {Error} If encryption fails.
     */
    static encrypt(strToEncrypt, secret) {
        if (typeof strToEncrypt !== 'string' || typeof secret !== 'string') {
            throw new TypeError('Both strToEncrypt and secret must be strings.');
        }

        try {
            const key = Buffer.from(secret, 'utf-8');
            const iv = Buffer.from(secret.substring(0, 16), 'utf-8');
            const cipher = crypto.createCipheriv('aes-256-cbc', key, iv);

            let encrypted = cipher.update(strToEncrypt, 'utf8', 'base64');
            encrypted += cipher.final('base64');

            return encrypted.replace(/\n/g, '');
        } catch (error) {
            throw new Error(`Encryption failed: ${error.message}`);
        }
    }

    /**
     * Convert a hexadecimal string to a byte array (Buffer).
     *
     * @param {string} hexString - The hex string to convert (must be even-length).
     * @returns {Buffer} The byte array (Buffer).
     * @throws {Error} If input is not a valid hex string.
     */
    static hexToBytes(hexString) {
        if (typeof hexString !== 'string') {
            throw new TypeError('Input must be a string.');
        }

        if (hexString.length % 2 !== 0) {
            throw new Error('Hex string must have an even length.');
        }

        // Validate that string only contains hex characters
        if (!/^[0-9a-fA-F]+$/.test(hexString)) {
            throw new Error('Hex string contains invalid characters.');
        }

        try {
            return Buffer.from(hexString, 'hex');
        } catch (err) {
            throw new Error(`Failed to convert hex to bytes: ${err.message}`);
        }
    }

    /**
     * Get current UTC time from worldtimeapi.org
     * 
     * @returns {Promise<number|null>} Unix time in seconds, or null on failure
     */
    static async getUtcTimeFromAPI() {
        const referer = 'https://worldtimeapi.org/';
        const headers = {
            'Referer': referer,
            'User-Agent': "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36",
            'Cache-Control': 'no-cache',
        };

        const url = 'https://worldtimeapi.org/api/timezone/UTC';

        try {
            const response = await axios.get(url, {
                headers,
                timeout: 2000,
            });

            if (response.status !== 200) {
                return null;
            }

            const unixtime = response.data.unixtime;
            if (typeof unixtime !== 'number') {
                console.log('getUtcTimeFromAPI_ERROR');
                return null;
            }

            return unixtime;
        } catch (err) {
            console.log(err);
            return null;
        }
    }
}

/**
 * Fetch data from a given URL using the configured API.
 * 
 * @param {string} targetUrl - The URL to fetch (e.g., Twitter/X post link)
 * @param {object} options - Optional config for headers and cookie
 * @param {string} options.cookie - Cookie string if required
 * @returns {Promise<object>} Response data from the API
 */
export async function fetchUrlData(targetUrl, options = {}) {
    if (!targetUrl || typeof targetUrl !== "string") {
        throw new Error("❌ Invalid input: 'targetUrl' must be a non-empty string.");
    }

    try {
        new URL(targetUrl);
    } catch {
        throw new Error("❌ Invalid URL format. Please provide a valid URL.");
    }

    const { cookie = "" } = options;

    const data = new URLSearchParams();
    data.append("url", targetUrl);
    data.append("cookie", cookie);

    const config = {
        method: "POST",
        maxBodyLength: Infinity,
        url: "https://downloaderapi.densavedownloader.app/index.php?action=extract",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "x-token": "yYZykcBmkPRNI5ZIv6hR6gl0fpiC5pT3TSdCx+b2bHquqh0SoiVJR8TZwtaoaLAfkJEq+9wGwHuNAdMDI+ze8Q==",
            "x-appkey": "hYsnMLnhN7g7TA4lTLngCdHA2S53ApiYoytxOcINKJGqbafPff3kaWzAGLkgW+og",
            "x-appcode": "haticitwitter",
        },
        data,
        timeout: 30000,
    };

    try {
        const response = await axios.request(config);
        return response.data;
    } catch (error) {
        if (error.response) {
            throw new Error(
                `⚠️ API Error (${error.response.status}): ${error.response.data?.message || "Unknown error"}`
            );
        } else if (error.request) {
            throw new Error("❌ No response from server. Please try again later.");
        } else {
            throw new Error(`❌ Request failed: ${error.message}`);
        }
    }
}

(async () => {

    /**
     * Contoh penggunaan
     */
    //   try {
    //     const result = await fetchUrlData("https://x.com/HumansNoContext/status/1977858169821896891?t=L_tPy-1JaOexoYpJc9tfqw&s=19");
    //     console.log("✅ Success:", result);
    //   } catch (err) {
    //     console.error("❌ Error:", err.message);
    //   }

    const secretBase = Util.hexToBytes("73587A446B5642716E6A6A48325742733561436D5A457847555273367A4E4B79");
    const secret = secretBase.toString("utf-8");
    const encodeBase = 'OFx0xDz4WXeHQn+5mZtaf7eScT6WbgYRHV/cVLKIoYYbfpL0JYRPMY7G75BJQ5n2';

    try {
        const decrypted = Util.decrypt(encodeBase, secret);
        const time = await Util.getUtcTimeFromAPI();
        console.log(time);
        console.log('Decrypted:', decrypted + "___");
    } catch (err) {
        console.error(err.message);
    }


})();