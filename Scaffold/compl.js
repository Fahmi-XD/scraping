import axios from "axios";
import crypto from "crypto";

class Util {
  static log = false

  static logMsg(processName, msg) {
    if (this.log) {
      console.log(`[${processName}] ${msg}`);
    }
  }

  /**
   * Generate random string hex
   * @param {number} length
   * @returns {string}
   */
  static randomHex(length = 16) {
    return crypto.randomBytes(Math.ceil(length / 2)).toString("hex").slice(0, length);
  }

  /**
   * Delay async
   * @param {number} ms
   * @returns {Promise<void>}
   */
  static sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  /**
   * Logging standar dengan tag
   * @param {string} tag
   * @param {string} msg
   * @param {boolean} [enabled=true]
   */
  static log(tag, msg, enabled = true) {
    if (enabled) console.log(`[${tag}] ${msg}`);
  }

  /**
   * Validasi URL
   * @param {string} url
   * @returns {boolean}
   */
  static isValidUrl(url) {
    try {
      new URL(url);
      return true;
    } catch {
      return false;
    }
  }
}

class Scraper {
  static BASE_URL = "";
  static HEADERS = {};

  static logEnabled = false;
  static axios = axios.create({ baseURL: this.BASE_URL, headers: this.HEADERS });

  /**
   * Fetch JSON dari URL dengan retry
   * @param {string} endpoint
   * @param {number} retries
   * @param {number} delayMs
   * @returns {Promise<any>}
   */
  static async fetchJson(endpoint, retries = 3, delayMs = 2000) {
    if (!endpoint || typeof endpoint !== "string") throw new Error("Invalid endpoint");
    for (let attempt = 1; attempt <= retries; attempt++) {
      try {
        Util.log("Scraper.fetchJson", `Attempt ${attempt}: ${endpoint}`, this.logEnabled);
        const res = await this.axios.get(endpoint, { timeout: 15000 });
        return res.data;
      } catch (err) {
        Util.log("Scraper.fetchJson", `Error: ${err.message}`, this.logEnabled);
        if (attempt < retries) await Util.sleep(delayMs);
        else throw new Error(`Failed after ${retries} attempts: ${err.message}`);
      }
    }
  }

  /**
   * Example function: Scrape data from URL
   * @param {string} url
   * @returns {Promise<any>}
   */
  static async scrape(url) {
    if (!Util.isValidUrl(url)) throw new Error("Invalid URL");
    Util.log("Scraper.scrape", `Start scraping: ${url}`, this.logEnabled);

    const data = await this.fetchJson(url);
    // Todo: parsing logic here
    Util.log("Scraper.scrape", `Scrape complete: ${url}`, this.logEnabled);
    return data;
  }
}

/**
 * Contoh penggunaan
 */
async function placeholder(params = {}, log) {
  try {
    Util.log = log
    
    // Todo ...
    const result = await Scraper.scrape("/todos/1");
    return result;
  } catch (error) {
    console.error("[placeholder] Error:", error.message);
    return null;
  }
}

/**
 * Entry point
 */
(async () => {
  const response = await placeholder({}, true);
  console.log("[Main] Result:", response);
})();
