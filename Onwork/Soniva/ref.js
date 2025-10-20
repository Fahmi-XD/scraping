import axios from "axios";
import crypto from "crypto";
import { v4 as uuidv4 } from "uuid";

class Func {
  static BASE_URL = "https://api.sonivamusic.com"
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
    // Ensure it works for both Uint8Array and Buffer
    return Buffer.from(arr.slice(start, end));
  }

  /**
   * Decrypts a byte array using a SHA-512 based XOR mechanism.
   * This is equivalent to the 'a' function in the original Java code.
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
   * This is equivalent to the 'c' function in the original Java code.
   * @returns {string} The decrypted string part.
   */
  static getDecryptedStringPart() {
    const part1 = this.xorWithStaticKey([112, 49, 100, 47, 2, 63, 58, 71]).toString(this.CHARSET);
    const part2 = this.xorWithStaticKey([105, 88, 83, 59, 118, 11, 54, 80]).toString(this.CHARSET);
    return part1 + part2;
  }

  /**
   * Combines three input strings with a decrypted constant part in a specific order.
   * @param {string} s1 - The first string component (e.g., a signature part).
   * @param {string} s2 - The second string component (e.g., a signature part).
   * @param {string} s3 - The third string component (e.g., a signature part).
   * @returns {string} The combined string used for HMAC-SHA256 signing.
   * @throws {Error} If any parameter is null or undefined.
   */
  static formatSignatureString(s1, s2, s3) {
    if (!s1 || !s2 || !s3) throw new Error("Signature string parameters cannot be null or empty.");
    return s2 + this.getDecryptedStringPart() + s1 + s3;
  }

  /**
   * Generates an HMAC-SHA256 signature from a formatted string using a decrypted secret key.
   * @param {string} s1 - The first part of the signature input.
   * @param {string} s2 - The second part of the signature input.
   * @param {string} s3 - The third part of the signature input.
   * @returns {string} The Base64 encoded HMAC-SHA256 signature.
   * @throws {Error} If any parameter is null or undefined.
   */
  static generateHmacSha256Signature(s1, s2, s3) {
    if (!s1 || !s2 || !s3) {
      throw new Error("Signature parameters cannot be null or empty.");
    }

    const stringToSign = this.formatSignatureString(s1, s2, s3);

    try {
      // Decrypt the static key to get the HMAC secret key.
      const secretKey = this.decryptBytes(this.ENCRYPTED_KEY_BYTES);

      // Create Mac HmacSHA256
      const mac = crypto.createHmac('sha256', secretKey);

      // Encode string to Buffer
      const buffer = Buffer.from(stringToSign, 'utf-8');

      // Calculate HMAC and return Base64 encoded result (without line wrapping).
      return mac.update(buffer).digest('base64');
    } catch (e) {
      console.error("Error generating HMAC-SHA256 signature:", e);
      return "";
    }
  }

  /**
   * Signs arbitrary data using HMAC-SHA256 and the decrypted static key.
   * This is an alternative signing utility, though `generateHmacSha256Signature` is used for the API header.
   * @param {string} data - The data string to sign.
   * @returns {string} The Base64 encoded signature.
   */
  static signData(data) {
    const decryptedKey = this.decryptBytes(this.ENCRYPTED_KEY_BYTES)
    const hmac = crypto.createHmac("sha256", decryptedKey);
    hmac.update(data, "utf8");
    return Buffer.from(hmac.digest()).toString("base64");
  }

  /**
   * Generates the necessary headers for an API request, including the signature.
   * @returns {object} An object containing the required headers.
   */
  static getApiHeaders() {
    try {
      const param1 = this.DEVICE_ID;
      const param2 = uuidv4();
      const param3 = String(Date.now());

      const xSignatureID = this.generateHmacSha256Signature(param1, param2, param3)
      this.logMessage("x-signature-id", xSignatureID)

      const headers = {
        "x-signature-id": xSignatureID,
        "x-device-id": param1,
        "x-request-time": param3,
        "x-message-id": param2,
        "platform": "android",
        "x-app-version": "1.3.6",
        "x-version-code": "87",
        "x-country": "US",
        "accept-language": "en-US",
        "user-agent": "SonivaMusic/1.3.6 (build:87; Android 12; Asus ASUS_AI2401_D)",
        "content-type": "application/json; charset=utf-8",
        "accept-encoding": "gzip"
      }

      this.logMessage("getApiHeaders", JSON.stringify(headers))

      return headers;
    } catch (error) {
      console.error("Error generating API headers:", error)
      return {};
    }
  }

  /**
   * Placeholder function for a 'register' API call.
   * It demonstrates header generation but currently does not make an HTTP request.
   * @returns {Promise<void>}
   */
  static async registerDevice() {
    try {
      const headers = this.getApiHeaders();
      const body = {
        "device_id": headers["x-device-id"],
        "push_token": headers["x-signature-id"],
        "message_id": headers["x-message-id"]
      }

      const response = await axios.post(`${this.BASE_URL}/musicai/v1/register`, body, {
        headers,
        validateStatus: (status) => status < 500
      });

      return response.data;
    } catch (error) {
      console.error("Error during device registration:", error)
    }
  }

}

/**
 * Main function to execute the API interaction logic.
 * @param {object} params - Parameters for the API function (currently unused).
 * @param {boolean} log - Flag to enable or disable logging in the Func class.
 * @returns {Promise<object | void>} The result of the Func.registerDevice call.
 */
async function mainApiHandler(params, log) {
  try {
    Func.isLogEnabled = log

    const registerResult = await Func.registerDevice();
    return registerResult;
  } catch (error) {
    console.error("Error in mainApiHandler:", error)
  }
}

/**
 * Contoh
 */
(async () => {
  const response = await mainApiHandler({}, true)
  console.log(response)
})();