package I5;

/* renamed from: I5.j, reason: case insensitive filesystem */
/* loaded from: /home/kali/Twittee/classes4.dex */
public abstract class AbstractC0141j {
    public static final char[] a;

    static {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        kotlin.jvm.internal.t.e(charArray, "toCharArray(...)");
        a = charArray;
    }

    public static final byte[] a(String str) {
        kotlin.jvm.internal.t.f(str, "<this>");
        byte[] bArr = new byte[str.length() / 2];
        Y4.e eVarJ = Y4.i.j(Y4.i.k(0, str.length()), 2);
        int iD = eVarJ.d();
        int iE = eVarJ.e();
        int iF = eVarJ.f();
        if ((iF > 0 && iD <= iE) || (iF < 0 && iE <= iD)) {
            while (true) {
                char[] cArr = a;
                bArr[iD >> 1] = (byte) (D4.n.Z(cArr, str.charAt(iD + 1)) | (D4.n.Z(cArr, str.charAt(iD)) << 4));
                if (iD == iE) {
                    break;
                }
                iD += iF;
            }
        }
        return bArr;
    }
}
