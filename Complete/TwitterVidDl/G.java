package I5;

import android.util.Base64;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: /home/kali/Twittee/classes4.dex */
public final class G {
    public static final G a = new G();

    public final String a(String str, String str2) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        kotlin.jvm.internal.t.f(str, "strToDecrypt");
        kotlin.jvm.internal.t.f(str2, "secret");
        Charset charset = a5.c.b;
        byte[] bytes = str2.getBytes(charset);
        kotlin.jvm.internal.t.e(bytes, "getBytes(...)");
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
        String strSubstring = str2.substring(0, 16);
        kotlin.jvm.internal.t.e(strSubstring, "substring(...)");
        byte[] bytes2 = strSubstring.getBytes(charset);
        kotlin.jvm.internal.t.e(bytes2, "getBytes(...)");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes2);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(2, secretKeySpec, ivParameterSpec);
        byte[] bArrDoFinal = cipher.doFinal(Base64.decode(str, 0));
        kotlin.jvm.internal.t.e(bArrDoFinal, "doFinal(...)");
        return a5.A.M(new String(bArrDoFinal, charset), "\n", "", false, 4, (Object) null);
    }

    public final String b(String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        kotlin.jvm.internal.t.f(str, "strToEncrypt");
        kotlin.jvm.internal.t.f(str2, "secret");
        Charset charset = a5.c.b;
        byte[] bytes = str2.getBytes(charset);
        kotlin.jvm.internal.t.e(bytes, "getBytes(...)");
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
        String strSubstring = str2.substring(0, 16);
        kotlin.jvm.internal.t.e(strSubstring, "substring(...)");
        byte[] bytes2 = strSubstring.getBytes(charset);
        kotlin.jvm.internal.t.e(bytes2, "getBytes(...)");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes2);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(1, secretKeySpec, ivParameterSpec);
        byte[] bytes3 = str.getBytes(charset);
        kotlin.jvm.internal.t.e(bytes3, "getBytes(...)");
        byte[] bArrEncode = Base64.encode(cipher.doFinal(bytes3), 0);
        kotlin.jvm.internal.t.e(bArrEncode, "encode(...)");
        return a5.A.M(new String(bArrEncode, charset), "\n", "", false, 4, (Object) null);
    }
}
