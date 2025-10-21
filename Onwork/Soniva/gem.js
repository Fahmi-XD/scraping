import crypto from "crypto"
import { Buffer } from "buffer";

// -----------------------------------------------------------------------------
// KELAS MOCK (MENIRU DEPENDENSI JAVA)
// -----------------------------------------------------------------------------

/**
 * Meniru antarmuka q8.b0 (UserDataStore) untuk penyimpanan asinkron.
 */
class UserDataStore {
    constructor() {
        // Menyimpan data (seperti DataStore/SharedPreferences)
        this.store = {};
        this.DEVICE_ID_KEY = 'device_id'; 
    }

    // Setara dengan 'q8.b0.a(r0)' (operasi baca)
    async bacaIdPerangkat() {
        console.log("-> UserDataStore: Membaca ID perangkat dari penyimpanan...");
        // Simulasikan penundaan asinkron
        await new Promise(resolve => setTimeout(resolve, 10)); 
        // Mengembalikan ID tersimpan atau null
        return this.store[this.DEVICE_ID_KEY] || null;
    }

    // Setara dengan operasi tulis DataStore
    async tulisIdPerangkat(id) {
        console.log(`-> UserDataStore: Menyimpan ID perangkat baru: ${id}`);
        // Simulasikan penundaan asinkron
        await new Promise(resolve => setTimeout(resolve, 10));
        this.store[this.DEVICE_ID_KEY] = id;
        return true; 
    }
}

// -----------------------------------------------------------------------------
// KELAS UTAMA (MENIRU n8.C2496e)
// -----------------------------------------------------------------------------

/**
 * Meniru logika dari kelas dekompilasi Java n8.C2496e.
 * Menggunakan MD5 untuk replikasi akurat dari UUID.nameUUIDFromBytes() Java.
 */
class PenyediaIdPerangkat {
    
    // ID Android yang Anda sediakan, meniru hasil dari Settings.Secure.getString()
    static ANDROID_ID_MOCK = "9774d56d682e549e";
    
    // Namespace untuk UUID v3/v5. (Dalam kasus ini hanya diperlukan hash MD5 dari data)
    // UUID.nameUUIDFromBytes(byte[]) di Java menggunakan MD5 (UUID v3).

    /**
     * @param {UserDataStore} userDataStore - Antarmuka penyimpanan.
     * @param {Object} context - Placeholder untuk Context Android.
     */
    constructor(userDataStore, context) {
        if (!userDataStore) throw new Error("userDataStore tidak boleh null.");
        this.userDataStore = userDataStore;
        this.context = context;
    }

    /**
     * Logika inti, setara dengan metode statis Java 'a(...)'.
     * @returns {Promise<string>} ID perangkat atau "UNKNOWN_DEVICE_ID" jika gagal.
     */
    async dapatkanIdPerangkat() {
        try {
            // 1. Cek apakah ID ada di penyimpanan (Setara dengan L3e - L4e)
            let deviceId = await this.userDataStore.bacaIdPerangkat();

            if (deviceId) {
                console.log("-> ID ditemukan di penyimpanan. Mengembalikannya.");
                return deviceId; // L94: return r6
            }
            
            // ----------------------------------------------------------
            // 2. Logika Pembuatan ID (Jika ID TIDAK ditemukan)
            // ----------------------------------------------------------

            console.log("-> ID tidak ditemukan. Membuat ID baru menggunakan Mock ID Android...");

            // L57: java.lang.String r6 = android.provider.Settings.Secure.getString(r6, r2)
            const androidId = PenyediaIdPerangkat.ANDROID_ID_MOCK;
            
            // L59: byte[] r6 = r6.getBytes(r2)
            // Konversi ID Android string ke byte (Buffer) menggunakan UTF-8
            const androidIdBytes = Buffer.from(androidId, 'utf8');
            
            // L62: java.util.UUID r6 = java.util.UUID.nameUUIDFromBytes(r6)
            // Meniru UUID v3 (MD5) yang digunakan oleh Java's nameUUIDFromBytes.
            const hash = crypto.createHash('md5');
            hash.update(androidIdBytes);
            let hashBytes = hash.digest(); // 16 bytes (128 bits)

            // Membangun UUID v3 secara manual (RFC 4122):
            let uuidBytes = hashBytes.subarray(0, 16);

            // Set bit versi (M) ke 3 (0011) di byte ke-6 (0-indeks)
            uuidBytes[6] = (uuidBytes[6] & 0x0f) | 0x30; 
            // Set bit varian (N) ke 10xx di byte ke-8 (0-indeks)
            uuidBytes[8] = (uuidBytes[8] & 0x3f) | 0x80; 

            // Format byte menjadi string UUID standar (8-4-4-4-12)
            const newId = [
                uuidBytes.subarray(0, 4).toString('hex'),
                uuidBytes.subarray(4, 6).toString('hex'),
                uuidBytes.subarray(6, 8).toString('hex'),
                uuidBytes.subarray(8, 10).toString('hex'),
                uuidBytes.subarray(10, 16).toString('hex')
            ].join('-');

            // 3. Simpan ID baru (Setara dengan L68 - L8f)
            await this.userDataStore.tulisIdPerangkat(newId);

            // 4. Kembalikan ID baru
            return newId; // L94: return r6

        } catch (e) {
            // L95: catch block
            console.error("-> [ERROR] Gagal mengambil atau membuat ID perangkat:", e.message);
            
            // L9d: java.lang.String r5 = "UNKNOWN_DEVICE_ID"
            return "UNKNOWN_DEVICE_ID";
        }
    }
}

// -----------------------------------------------------------------------------
// CONTOH EKSEKUSI
// -----------------------------------------------------------------------------

async function jalankanContoh() {
    const penyimpanan = new UserDataStore();
    const penyedia = new PenyediaIdPerangkat(penyimpanan, {}); 

    // UUID V3 (MD5) yang dihasilkan dari string "9774d56d682e549c" adalah:
    // 6a4f4790-2856-3392-a1f7-e7e0e7a46f61

    console.log("--- JALAN 1: ID BELUM ADA (Membuat) ---");
    let id1 = await penyedia.dapatkanIdPerangkat();
    console.log(`ID Hasil (1): ${id1}`); 
    
    console.log("\n--- JALAN 2: ID DITEMUKAN (Membaca dari penyimpanan) ---");
    let id2 = await penyedia.dapatkanIdPerangkat();
    console.log(`ID Hasil (2): ${id2}`);
    console.log(`ID Sama: ${id1 === id2}`);

    console.log("\n--- JALAN 3: SIMULASI PENGECUALIAN (Exception) ---");
    penyimpanan.bacaIdPerangkat = async () => { throw new Error("Koneksi penyimpanan hilang."); };
    let id3 = await penyedia.dapatkanIdPerangkat();
    console.log(`ID Hasil (3): ${id3}`); 
}

jalankanContoh();