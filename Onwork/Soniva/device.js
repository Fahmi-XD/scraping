// nodejs-deviceid-generator.js
import crypto from 'crypto';

/**
 * Membuat UUID v3 (name-based MD5) dari bytes input (sama dengan Java UUID.nameUUIDFromBytes(bytes))
 * @param {Buffer} inputBytes
 * @returns {string} uuid string lower-case
 */
function nameUUIDFromBytes(inputBytes) {
    // MD5 digest (16 bytes)
    const md5 = crypto.createHash('md5').update(inputBytes).digest();

    // Convert to Buffer (should be 16 bytes)
    const buf = Buffer.from(md5);

    // Set version to 3 --> time_hi_and_version (byte index 6) upper 4 bits = 0x3
    buf[6] = (buf[6] & 0x0f) | 0x30; // 0x30 = 0011 0000

    // Set variant to RFC4122 --> clock_seq_hi_and_reserved (byte index 8) upper 2 bits = 10
    buf[8] = (buf[8] & 0x3f) | 0x80; // 0x80 = 1000 0000

    // Format as UUID string: 8-4-4-4-12 (bytes -> hex)
    const hex = buf.toString('hex');
    const uuid = [
        hex.slice(0, 8),
        hex.slice(8, 12),
        hex.slice(12, 16),
        hex.slice(16, 20),
        hex.slice(20, 32)
    ].join('-');

    return uuid.toLowerCase();
}

/**
 * Public helper: given androidId string -> return device-id per APK logic
 * (java: UUID.nameUUIDFromBytes(androidId.getBytes(UTF_8)).toString())
 * @param {string} androidId
 * @returns {string}
 */
export function generateDeviceIdFromAndroidId(androidId) {
    if (!androidId) throw new Error('androidId required');
    const bytes = Buffer.from(androidId, 'utf8');
    return nameUUIDFromBytes(bytes);
}

const sampleAndroidId = '8d08c20553ed3cc6'; // contoh emulator default
console.log('android_id =', sampleAndroidId);
console.log('device id =', generateDeviceIdFromAndroidId(sampleAndroidId));