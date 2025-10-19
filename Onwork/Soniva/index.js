import axios from "axios";
import crypto from "crypto";

class Util {
  static SECRET_KEY_BUFFER = Buffer.from("ISI_KEY_DARI_AbstractC2493b_a_AbstractC2787a_f27593b", "utf8");
  static SIGNAL_HANDLER = {
    getRsfb3: () => "KEY_D",
    getGCRQ7: () => "KEY_E",
    getSsmhqc2: () => "KEY_F",
  };

  static log = false

  static logMsg(processName, msg) {
    if (this.log) {
      console.log(`[${processName}] ${msg}`);
    }
  }

  static generateSignature(type, s1, s2 = "", s3 = "") {
    let key = "";
    let payload = "";

    if (type === "A") {
      key = SIGNAL_HANDLER.getSsmhqc2();
      payload = `${s1}${s3}${s2}`;
    } else if (type === "B") {
      key = SIGNAL_HANDLER.getGCRQ7();
      payload = `${s1}${s3}${s2}`;
    } else if (type === "C") {
      key = SIGNAL_HANDLER.getRsfb3();
      payload = s1;
    } else {
      throw new Error("Unknown signature type");
    }

    const hmac = crypto.createHmac("sha256", key);
    hmac.update(payload);
    return hmac.digest("base64");
  }

  /**
   * generateSignature(s1, s2, s3)
   * - Menghasilkan HMAC-SHA256 (Base64) dari gabungan input.
   * - Key diambil dari konstanta byte array (dari Java).
   * - Simple, satu fungsi, jelas namanya — ubah buildMessage() jika perlu.
   *
   * @param {string} s1
   * @param {string} s2
   * @param {string} s3
   * @returns {string} base64 HMAC; kosong jika error
   */
  static generateSignature(s1, s2, s3) {
    if (s1 == null || s2 == null || s3 == null) {
      throw new Error('s1, s2, s3 harus diberikan');
    }

    const KEY_BYTES = [
      9, -68, 74, 103, -109, 76, 23, -83, -95, -36, 42, 35, 77, 77, 59, 59,
      16, -117, 112, 47, -109, 65, -74, -86, 60, -100, 22, 87, 22, 46, -78,
      86, -34, -5, -56, -124, 31, 57, 72, 117, -22, -50, -92, 93, 29, 125,
      -11, 126, -13, 40, 51, -94, -69, -79, 17, -109, 25, 33, 100, -115, 27,
      127, -47, 78
    ];

    const keyBuf = Buffer.from(KEY_BYTES.map(b => (b < 0 ? b + 256 : b)));

    try {
      // --- build message (ganti bila C2788b.f27597a.a(...) punya aturan lain) ---
      const buildMessage = (a, b, c) => `${a}|${b}|${c}`; // simpel & jelas
      const msg = buildMessage(s1, s2, s3);

      // --- buat HMAC SHA256 dan encode base64 ---
      const hmac = crypto.createHmac('sha256', keyBuf);
      hmac.update(Buffer.from(msg, 'utf8'));
      const signature = hmac.digest('base64');

      // zero-out key buffer (mirip Arrays.fill di Java)
      keyBuf.fill(0);

      return signature;
    } catch (err) {
      // jangan crash — kembalikan string kosong seperti versi Java
      return '';
    }
  }

}

async function placeholder(params, log) {
  try {
    Util.log = log

    // Todo ...
  } catch (error) {
    console.error()
  }
}

/**
 * Contoh
 */
(async () => {
  const response = await placeholder({}, true)
  console.log(response)
});

// (async () => {
//   try {
//     const response = await axios.post(
//       'https://api.sonivamusic.com/musicai/v1/users/4bf3b672-705e-47c4-bbd3-be6097f87aaf/songs/prompt',
//       {
//         mood: "Happy",
//         genre: "EDM",
//         has_vocal: false,
//         vocal_gender: null,
//         record_type: null,
//         prompt: "Write an empowering anthem for overcoming the challenges of moving to a new city, embracing the excitement and fears, and finding new beginnings",
//         is_dual_song_enabled: true,
//         message_id: "34d5af5b-3161-43d1-a1eb-bd254ed4fc7d"
//       },
//       {
//         headers: {
//           'User-Agent': 'SonivaMusic/1.3.6 (build:87; Android 12; Samsung SM-A235F)',
//           'Accept-Encoding': 'gzip',
//           'Content-Type': 'application/json',
//           'x-signature-id': 'gYsglIG3ST/OPxbY0T4EqkwshBQmbvxQSu0YNt/EC2o=',
//           'x-device-id': '93bc0270-8ec2-36bf-9c60-767d332602a3',
//           'x-request-time': '1760857375385',
//           'x-message-id': '34d5af5b-3161-43d1-a1eb-bd254ed4fc7d',
//           'platform': 'android',
//           'x-app-version': '1.3.6',
//           'x-version-code': '87',
//           'x-country': 'US',
//           'accept-language': 'en-US'
//         },
//       }
//     );

//     console.log(response.data);
//   } catch (error) {
//     console.error(error.response?.data || error.message);
//   }
// })();
