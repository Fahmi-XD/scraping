package t8;

import android.security.keystore.KeyProtection;
import android.util.Base64;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import n8.AbstractC2493b;
import r8.AbstractC2787a;
import r8.C2788b;

/* compiled from: r8-map-id-37511a1e93c48db0e7965aa91a49d5398fe0489e78b274de2f036fe967d5abce */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J%\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lt8/g;", "", "<init>", "()V", "Ljavax/crypto/SecretKey;", "a", "()Ljavax/crypto/SecretKey;", "", "s1", "s2", "s3", "b", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "app_prodRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: /home/kali/Soniva/classes.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public static final g f28302a = new g();

    /* renamed from: b, reason: collision with root package name */
    public static final KeyStore f28303b;

    static {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        f28303b = keyStore;
    }

    private g() {
    }

    private final SecretKey a() throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        KeyStore keyStore = f28303b;
        byte[] bArrA = AbstractC2493b.a(AbstractC2787a.f27592a);
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        String str = new String(bArrA, UTF_8);
        try {
            Key key = keyStore.getKey(str, null);
            SecretKey secretKey = key instanceof SecretKey ? (SecretKey) key : null;
            if (secretKey != null) {
                return secretKey;
            }
        } catch (Exception unused) {
            keyStore.deleteEntry(str);
        }
        byte[] bArrA2 = AbstractC2493b.a(AbstractC2787a.f27593b);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArrA2, "HmacSHA256");
        KeyProtection keyProtectionBuild = new KeyProtection.Builder(4).setDigests("SHA-256").build();
        Intrinsics.checkNotNullExpressionValue(keyProtectionBuild, "build(...)");
        keyStore.setEntry(str, new KeyStore.SecretKeyEntry(secretKeySpec), keyProtectionBuild);
        int length = bArrA2.length;
        Intrinsics.checkNotNullParameter(bArrA2, "<this>");
        Arrays.fill(bArrA2, 0, length, (byte) 0);
        return secretKeySpec;
    }

    public final String b(String s12, String s2, String s32) throws NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(s12, "s1");
        Intrinsics.checkNotNullParameter(s2, "s2");
        Intrinsics.checkNotNullParameter(s32, "s3");
        String strA = C2788b.f27597a.a(s12, s2, s32);
        try {
            SecretKey secretKeyA = a();
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeyA);
            byte[] bytes = strA.getBytes(U9.a.f12485a);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            String strEncodeToString = Base64.encodeToString(mac.doFinal(bytes), 2);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(...)");
            return strEncodeToString;
        } catch (Exception e10) {
            e10.printStackTrace();
            return "";
        }
    }
}
