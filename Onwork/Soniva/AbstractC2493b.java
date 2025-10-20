package n8;

import C8.l;
import H6.u0;
import U9.o;
import W.A;
import android.os.Build;
import f6.AbstractC1921d;
import h7.C2109e;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import r8.AbstractC2787a;
import r8.C2788b;

/* compiled from: r8-map-id-37511a1e93c48db0e7965aa91a49d5398fe0489e78b274de2f036fe967d5abce */
/* renamed from: n8.b, reason: case insensitive filesystem */
/* loaded from: /home/kali/Soniva/classes.dex */
public abstract class AbstractC2493b {

    /* renamed from: a, reason: collision with root package name */
    public static final l f25811a = new l("1", "123", "Electric Night Awakening", "An energetic track for an exciting night.", "https://d2m6kf0jl6dhrs.cloudfront.net/image/4C19AE5D-B6D3-4165-9C52-DB8E7EB2ECCA.png", "https://d2m6kf0jl6dhrs.cloudfront.net/image/4C19AE5D-B6D3-4165-9C52-DB8E7EB2ECCA.png", "https://example.com/audio/electric-night-awakening.mp3", "https://example.com/videos/electric-night-awakening.mp4", "These are the original lyrics for the song...", Double.valueOf(240.0d), "https://example.com/share/electric-night-awakening", 8, 2);

    /* renamed from: b, reason: collision with root package name */
    public static final byte[] f25812b;

    static {
        Intrinsics.checkNotNullParameter("1", "songId");
        Intrinsics.checkNotNullParameter("123", "variantId");
        Intrinsics.checkNotNullParameter("Rock Separation", "title");
        f25812b = new byte[]{112, 49, 100, 47, 2, 63, 58, 71};
    }

    public static final byte[] a(byte[] encryptedBytes) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(encryptedBytes, "encryptedBytes");
        int length = encryptedBytes.length;
        C2788b c2788b = C2788b.f27597a;
        byte[] bArrB = c2788b.b(AbstractC2787a.f27594c);
        Charset charset = U9.a.f12485a;
        String strY = R7.h.y(new String(bArrB, charset), new String(c2788b.b(AbstractC2787a.f27595d), charset), "com.sonivamusic.ai");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] bytes = strY.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNull(bArrDigest);
        byte[] bArrR = u0.r(bArrDigest, 0, length);
        byte[] bArr = new byte[encryptedBytes.length];
        int length2 = encryptedBytes.length;
        for (int i5 = 0; i5 < length2; i5++) {
            bArr[i5] = (byte) (encryptedBytes[i5] ^ bArrR[i5]);
        }
        return bArr;
    }

    public static final boolean b(String encryptedData, T5.a aead) {
        Intrinsics.checkNotNullParameter(encryptedData, "encryptedData");
        Intrinsics.checkNotNullParameter(aead, "aead");
        try {
            Boolean bool = null;
            byte[] bArrB = aead.b(f(encryptedData), null);
            Intrinsics.checkNotNull(bArrB);
            String str = new String(bArrB, U9.a.f12485a);
            Intrinsics.checkNotNullParameter(str, "<this>");
            if (Intrinsics.areEqual(str, "true")) {
                bool = Boolean.TRUE;
            } else if (Intrinsics.areEqual(str, "false")) {
                bool = Boolean.FALSE;
            }
            if (bool != null) {
                return bool.booleanValue();
            }
        } catch (IllegalArgumentException unused) {
            Ja.a.f5947a.getClass();
            C2109e.w(new Object[0]);
        } catch (GeneralSecurityException unused2) {
            Ja.a.f5947a.getClass();
            C2109e.w(new Object[0]);
        }
        return false;
    }

    public static void c() {
        Intrinsics.checkNotNullParameter("", "message");
    }

    public static final String d(boolean z10, T5.a aead) {
        Intrinsics.checkNotNullParameter(aead, "aead");
        byte[] bytes = String.valueOf(z10).getBytes(U9.a.f12485a);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrA = aead.a(bytes, null);
        Intrinsics.checkNotNull(bArrA);
        Intrinsics.checkNotNullParameter(bArrA, "<this>");
        try {
            String str = new String(AbstractC1921d.a(bArrA), "US-ASCII");
            Intrinsics.checkNotNullExpressionValue(str, "encodeToString(...)");
            return str;
        } catch (UnsupportedEncodingException e10) {
            throw new AssertionError(e10);
        }
    }

    public static final String e(int i5) {
        double d8 = i5;
        DecimalFormat decimalFormat = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
        return d8 >= 1000000.0d ? R7.h.i(decimalFormat.format(d8 / 1000000), "M") : d8 >= 1000.0d ? R7.h.i(decimalFormat.format(d8 / 1000), "K") : String.valueOf(i5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x00e7, code lost:
    
        if (r7 != 4) goto L58;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final byte[] f(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n8.AbstractC2493b.f(java.lang.String):byte[]");
    }

    public static final String g() {
        String str = Build.MANUFACTURER;
        String strY = Build.MODEL;
        Intrinsics.checkNotNull(strY);
        Intrinsics.checkNotNull(str);
        if (!o.a0(strY, str, false)) {
            strY = R7.h.y(str, " ", strY);
        }
        A a10 = AbstractC2492a.f25810a;
        return R7.h.l("SonivaMusic/1.3.6 (build:87; Android ", Build.VERSION.RELEASE, "; ", strY, ")");
    }
}
