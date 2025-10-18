import axios from "axios";

class Util {
  static generateParam(str) {
    const data = (new TextEncoder).encode(str)
    for (let i = 0; i < data.length; i++) {
      data[i] = 123 ^ data[i];
    }

    return Buffer.from(String.fromCharCode.apply(null, data)).toString("base64");
  }

  static reverseGenerateParam(str) {
    const buf = Buffer.from(str, "base64")

    const data = new Uint8Array(buf.length)
    for (let i = 0; i < data.length; i++) {
      data[i] = buf[i] ^ 123;
    }

    return (new TextDecoder).decode(data);
  }
}

async function croxy(area, url) {
  try {
    const encodeParams = Util.generateParam(`area=${area}&u=${url}`)
    console.log(encodeParams)
  } catch (error) {
    console.log(error)
  }
}

(async () => {
  const response = await croxy("US", "https://github.com/Fahmii-XD")
  console.log(response)
})()