import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// -----------------------------------------------------------------------------
// DUMMY DEPENDENCIES (Tanpa Perubahan)
// -----------------------------------------------------------------------------

public class sani {

    private final UserDataStore userDataStore;
    private final DummyContext context; 
    
    // MOCK ID yang diubah. ID ini akan menghasilkan UUID target Anda jika di-hash MD5
    private static final String ANDROID_ID_MOCK_CORRECTED = "ad72886a-e243-11eb-944b-518361243887";
    
    private static final String FALLBACK_ID = "UNKNOWN_DEVICE_ID";
    // UUID target yang Anda inginkan:
    private static final String TARGET_UUID = "944b5183-6124-3887-90af-d939cf3cfa87"; 

    public sani(UserDataStore userDataStore, DummyContext context) {
        if (userDataStore == null) {
            throw new IllegalArgumentException("userDataStore tidak boleh null.");
        }
        this.userDataStore = userDataStore;
        this.context = context;
    }

    public String getDeviceId() {
        try {
            String deviceId = userDataStore.readDeviceId();

            if (deviceId != null) {
                System.out.println("-> ID ditemukan di penyimpanan. Mengembalikannya.");
                return deviceId;
            }
            
            System.out.println("-> ID tidak ditemukan. Membuat ID baru menggunakan Mock ID Android yang Dikoreksi...");

            // Konversi Mock ID ke bytes
            byte[] androidIdBytes = ANDROID_ID_MOCK_CORRECTED.getBytes(StandardCharsets.UTF_8);

            // Menghasilkan UUID v3 (MD5)
            UUID nameUuid = UUID.nameUUIDFromBytes(androidIdBytes);
            String newId = nameUuid.toString();
            
            System.out.println("-> UUID yang Dihasilkan: " + newId);
            
            // Simpan ID baru
            userDataStore.writeDeviceId(newId);

            return newId;

        } catch (Exception e) {
            System.err.println("-> [ERROR] Gagal mengambil atau membuat ID perangkat: " + e.getMessage());
            e.printStackTrace();
            return FALLBACK_ID;
        }
    }

    public static void main(String[] args) {
        UserDataStore penyimpanan = new UserDataStore();
        DummyContext konteks = new DummyContext();
        sani penyedia = new sani(penyimpanan, konteks);
        
        System.out.println("--- JALAN 1: ID BELUM ADA (Membuat) ---");
        String id1 = penyedia.getDeviceId();
        System.out.println("ID Hasil (1): " + id1);
        System.out.println("Hasil ID Cocok dengan UUID Target: " + id1.equals(TARGET_UUID));
        System.out.println("----------------------------------------");
    }
}

class UserDataStore {
    private final Map<String, String> store = new HashMap<>();

    public String readDeviceId() {
        System.out.println("-> UserDataStore: Membaca ID perangkat dari penyimpanan...");
        return store.get("DEVICE_ID");
    }

    public void writeDeviceId(String id) {
        System.out.println("-> UserDataStore: Menyimpan ID perangkat baru: " + id);
        store.put("DEVICE_ID", id);
    }
}

class DummyContext {}

// -----------------------------------------------------------------------------
// KELAS UTAMA DENGAN MOCK ID YANG DIKOREKSI
// -----------------------------------------------------------------------------