import crypto from "crypto";
import { v3 as uuidv3 } from 'uuid';

function generateDeviceId(packageName, androidId) {
  const raw = `${packageName}:${androidId}`;
  const hash = crypto.createHash("sha256").update(raw).digest("hex");
  return hash; // bisa jadi server pakai ini
}

// contoh penggunaan
//const packageName = "com.example.app";
//const androidId = "a1b2c3d4e5f6g7h8"; // misal hasil dari Settings.Secure.ANDROID_ID
//const deviceId = generateDeviceId(packageName, androidId);

// console.log("X-Device-ID:", deviceId);

// Namespace bisa apa saja, tapi SDK-nya mungkin pakai konstan tertentu.
// Misalnya "android_id" atau "device".
// Contoh sederhana:
const namespace = uuidv3.URL; // pakai namespace default bawaan
const androidId = "5f1f1dfc8ee6e6e9"; // biasanya dari Settings.Secure.ANDROID_ID
const deviceId = uuidv3(androidId, namespace);

console.log(deviceId); // hasil seperti 944b5183-6124-3887-90af-d939cf3cfa87
