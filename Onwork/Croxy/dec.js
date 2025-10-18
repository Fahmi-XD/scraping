function decodeParam(b64) {
  const buf = Buffer.from(b64, 'base64');
  const bytes = new Uint8Array(buf.length);
  for (let i = 0; i < buf.length; i++) {
    bytes[i] = buf[i] ^ 123;
  }
  return new TextDecoder().decode(bytes);
}

const encoded = "GgkeGkYuKF0ORhMPDwsIQVRUHBIPEw4ZVRgUFlQ9GhMWElYjPw==";
console.log(decodeParam(encoded));
