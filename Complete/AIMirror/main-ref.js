import axios from "axios";
import crypto from "crypto";
import FormData from "form-data";

class Helper {
  static BASE_URL = "https://be.aimirror.fun"
  static UID = this.generateHash()
  static HEADERS = {
    'User-Agent': 'AIMirror/6.8.4+179 (android)',
    'store': 'googleplay',
    'uid': this.UID,
    'env': 'PRO',
    'accept-language': 'en',
    'accept-encoding': 'gzip',
    'package-name': 'com.ai.polyverse.mirror',
    'host': 'be.aimirror.fun',
    'content-type': 'application/json',
    'app-version': '6.8.4+179'
  };

  static hash = ""
  static imageKey = ""
  static log = false

  /**
   * @param {string} str
   * @returns {string} hex SHA-1
   */
  static sha1FromString(str) {
    return crypto.createHash('sha1').update(str, 'utf8').digest('hex');
  }

  /**
   * @param {string} hexHash
   * @param {string} [ext='.jpg'] optional extension
   * @returns {string}
   */
  static withExt(hexHash, ext = '.jpg') {
    return `${hexHash}${ext}`;
  }

  /**
   * @param {string} imageUrl - URL gambar (http/https)
   * @param {object} [headers] - Optional headers (mis. User-Agent)
   * @returns {Promise<Buffer>}
   */
  static async urlToBuffer(imageUrl, headers = {}) {
    const res = await axios.get(imageUrl, {
      responseType: 'arraybuffer',
      headers,
      timeout: 20000
    });

    return Buffer.from(res.data);
  }

  static generateHash() {
    const hexChars = "0123456789abcdef";
    const hexCharsUpper = "0123456789ABCDEF";

    const randHex = (len, upper = false) => {
      const chars = upper ? hexCharsUpper : hexChars;
      let str = "";
      for (let i = 0; i < len; i++) {
        str += chars[Math.floor(Math.random() * chars.length)];
      }
      return str;
    };

    return randHex(16);
  }

  static async fetchAppToken() {
    const url = `${this.BASE_URL}/app_token/v2`;
    const params = {
      cropped_image_hash: this.withExt(this.hash),
      uid: this.UID
    };

    console.log(params);

    try {
      const res = await axios.get(url, {
        params,
        headers: this.HEADERS,
        timeout: 10000
      });

      return res.data
    } catch (err) {
      if (err.response) {
        console.error('Error Response:', err.response.status, err.response.data);
      } else {
        console.error('Request Error:', err.message);
      }
    }
  }

  static async uploadPhoto(payload) {
    const { name, key, policy, OSSAccessKeyId, success_action_status, signature, backend_type, region, file, upload_host } = payload;

    const body = new FormData();
    body.append("name", name)
    body.append("key", key)
    body.append("policy", policy)
    body.append("OSSAccessKeyId", OSSAccessKeyId)
    body.append("success_action_status", success_action_status)
    body.append("signature", signature)
    body.append("backend_type", backend_type)
    body.append("region", region)
    body.append("file", file, {
      filename: this.withExt(this.hash),
      contentType: "application/octet-stream",
    }) // Buffer file

    const headers = {
      "User-Agent": "Dart/3.6 (dart:io)",
      "Accept-Encoding": "gzip",
    };

    try {
      const res = await axios.post(upload_host, body, {
        headers: {
          ...body.getHeaders(),
          ...headers
        },
        maxBodyLength: Infinity,
        maxContentLength: Infinity,
        timeout: 10000
      });

      return res.data
    } catch (err) {
      if (err.response) {
        console.error('Error Response:', err.response.status, err.response.data);
      } else {
        console.error('Request Error:', err.message);
      }
    }
  }

  // {
  //   "upload_host": "https://aimirror-images-sg.oss-ap-southeast-1.aliyuncs.com",
  //   "name": "c341571aba6f12dee52a37d4d022eaa88c9897b1.jpg",
  //   "key": "sg_al/20251018/d7914c11c177cfda/c341571aba6f12dee52a37d4d022eaa88c9897b1.jpg",
  //   "policy": "eyJleHBpcmF0aW9uIjogIjIwMjUtMTAtMThUMDA6MjM6MDFaIiwgImNvbmRpdGlvbnMiOiBbWyJlcSIsICIka2V5IiwgInNnX2FsLzIwMjUxMDE4L2Q3OTE0YzExYzE3N2NmZGEvYzM0MTU3MWFiYTZmMTJkZWU1MmEzN2Q0ZDAyMmVhYTg4Yzk4OTdiMS5qcGciXSwgWyJjb250ZW50LWxlbmd0aC1yYW5nZSIsIDAsIDEwNDg1NzYwXV19",
  //   "OSSAccessKeyId": "LTAI5tBke2XQsHo9bgxePkq4",
  //   "success_action_status": 200,
  //   "signature": "QCH4S1kI9eMpYUQU8mreCyDsjPk=",
  //   "backend_type": "ALIYUN",
  //   "region": "ap-southeast-1"
  // }
  static async drawImage(payload) {
    const url = `${this.BASE_URL}/draw?uid=${this.UID}`;

    const { cropped_height = 1024, cropped_width = 768, model_id = 204, custom_prompt = "", imagine_value2 = 50, force_default_pose = false, is_free_trial = true } = payload

    const data = {
      model_id,
      cropped_image_key: this.imageKey,
      cropped_height,
      cropped_width,
      package_name: "com.ai.polyverse.mirror",
      ext_args: {
        imagine_value2,
        custom_prompt,
      },
      version: "6.8.4",
      force_default_pose,
      is_free_trial,
    };

    console.log(data)

    try {
      const res = await axios.post(url, data, {
        headers: this.HEADERS,
        timeout: 20000,
        validateStatus: (status) => true,
      });

      return res.data
    } catch (err) {
      console.error("❌ Request error:", err.message);
    }
  }

  /**
   * @param {string} draw_request_id
   * @param {string} uid
   * @param {number} delaySec - delay antar poll (detik)
   * @returns {Promise<string[]>} array generated_image_addresses
   */
  static async waitForDraw(draw_request_id, delaySec) {
    const url = "https://be.aimirror.fun/draw/process";

    while (true) {
      try {
        const res = await axios.get(url, {
          headers: this.HEADERS,
          params: { draw_request_id, uid: this.UID },
          timeout: 15000,
        });

        const data = res.data;
        console.log(
          `Progress: ${(data.progress.process * 100).toFixed(2)}%, Status: ${data.draw_status}`
        );

        if (data.draw_status === "SUCCEED") {
          console.log("✅ Draw succeeded!");
          return data.generated_image_addresses;
        } else if (data.draw_status === "FAILED") {
          throw new Error("Draw failed!");
        } else {
          await new Promise((r) => setTimeout(r, delaySec * 1000));
        }
      } catch (err) {
        console.error("Error polling draw process:", err.message);
        await new Promise((r) => setTimeout(r, delaySec * 1000));
      }
    }
  }
}

async function create(hyperParameter, delaySec = 7, log = false) {
  try {
    Helper.log = log
    Helper.hash = Helper.sha1FromString(crypto.randomUUID().toString())
    console.log(Helper.hash)
    console.log(Helper.UID)
    const appToken = await Helper.fetchAppToken()
    console.log(appToken)
    Helper.imageKey = appToken.key
    const bufferImage = await Helper.urlToBuffer("https://i.pinimg.com/736x/61/4c/7d/614c7dd0c5fa56738a7cfad1f137eb78.jpg")
    console.log(bufferImage.length)
    appToken["file"] = bufferImage
    const upload = await Helper.uploadPhoto(appToken)
    console.log(upload)
    const generate = await Helper.drawImage({})
    console.log(JSON.stringify(generate))
    const wait = await Helper.waitForDraw(generate.draw_request_id, delaySec)
    console.log(wait)
  } catch (error) {
    console.error(error)
  }
}

create({}, 7, true).then((result) => console.log(result));