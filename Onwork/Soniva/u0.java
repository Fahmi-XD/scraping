package H6;

import D.InterfaceC0194w;
import E.C0225c;
import M.AbstractC0424i;
import M.C0421f;
import M.InterfaceC0427l;
import W.C0811e;
import W.C0827m;
import W.C0837r0;
import W.C0847z;
import W.InterfaceC0808c0;
import W.InterfaceC0829n;
import X9.C0878h;
import a1.AbstractC0942g;
import a1.C0948m;
import a1.EnumC0947l;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import androidx.compose.ui.graphics.Fields;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.InterfaceC1160t;
import ca.C1358d;
import com.google.android.gms.internal.measurement.AbstractC1395h;
import com.google.android.gms.internal.measurement.C1375d;
import com.google.android.gms.internal.measurement.C1385f;
import com.google.android.gms.internal.measurement.C1390g;
import com.google.android.gms.internal.measurement.C1420m;
import com.google.android.gms.internal.measurement.C1454t;
import com.google.android.gms.internal.measurement.InterfaceC1425n;
import d.C1639A;
import d.C1641C;
import d.InterfaceC1640B;
import e.AbstractC1730e;
import e.C1734i;
import e.C1735j;
import i0.C2128l;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import o0.C2546c;
import o0.C2547d;
import qa.C2734i;
import t0.AbstractC2948B;
import t0.C2951E;
import x0.InterfaceC3486t;
import z9.C3657B;
import z9.C3677s;
import z9.C3679u;
import z9.C3681w;
import z9.C3684z;

/* compiled from: r8-map-id-37511a1e93c48db0e7965aa91a49d5398fe0489e78b274de2f036fe967d5abce */
/* loaded from: /home/kali/Soniva/classes.dex */
public abstract class u0 {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f4451a = 0;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f4452b = 0;

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f4453c = 0;

    public static final String A(long j) {
        String strH;
        if (j <= -999500000) {
            strH = R7.h.h((j - 500000000) / 1000000000, " s ", new StringBuilder());
        } else if (j <= -999500) {
            strH = R7.h.h((j - 500000) / 1000000, " ms", new StringBuilder());
        } else if (j <= 0) {
            strH = R7.h.h((j - 500) / 1000, " µs", new StringBuilder());
        } else if (j < 999500) {
            strH = R7.h.h((j + 500) / 1000, " µs", new StringBuilder());
        } else if (j < 999500000) {
            strH = R7.h.h((j + 500000) / 1000000, " ms", new StringBuilder());
        } else {
            strH = R7.h.h((j + 500000000) / 1000000000, " s ", new StringBuilder());
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%6s", Arrays.copyOf(new Object[]{strH}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return str;
    }

    public static qa.p B(SSLSession sSLSession) throws IOException {
        Certificate[] peerCertificates;
        List listK = A9.x.f1196a;
        Intrinsics.checkNotNullParameter(sSLSession, "<this>");
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        if (Intrinsics.areEqual(cipherSuite, "TLS_NULL_WITH_NULL_NULL") ? true : Intrinsics.areEqual(cipherSuite, "SSL_NULL_WITH_NULL_NULL")) {
            throw new IOException("cipherSuite == ".concat(cipherSuite));
        }
        C2734i c2734iC = C2734i.f27196b.c(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        if (Intrinsics.areEqual("NONE", protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        qa.I iP = U1.c.p(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
        }
        List listK2 = peerCertificates != null ? ra.c.k(Arrays.copyOf(peerCertificates, peerCertificates.length)) : listK;
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            listK = ra.c.k(Arrays.copyOf(localCertificates, localCertificates.length));
        }
        return new qa.p(iP, c2734iC, listK, new C0225c(listK2, 1));
    }

    public static final int C(int[] iArr) {
        return Math.min(iArr[2] - iArr[0], iArr[3] - iArr[1]);
    }

    public static final long D(double d8) {
        return H((float) d8, 4294967296L);
    }

    public static final long E(int i5) {
        return H(i5, 4294967296L);
    }

    public static final boolean F(M.P p10, boolean z10) {
        InterfaceC3486t interfaceC3486tC;
        I.Y y4 = p10.f6834d;
        if (y4 == null || (interfaceC3486tC = y4.c()) == null) {
            return false;
        }
        C2547d c2547dA = M.F.a(interfaceC3486tC);
        long jI = p10.i(z10);
        float f10 = c2547dA.f26092a;
        float f11 = c2547dA.f26094c;
        float fD = C2546c.d(jI);
        if (f10 > fD || fD > f11) {
            return false;
        }
        float f12 = c2547dA.f26093b;
        float f13 = c2547dA.f26095d;
        float fE = C2546c.e(jI);
        return f12 <= fE && fE <= f13;
    }

    public static final boolean G(long j) {
        V0.o[] oVarArr = V0.n.f12763b;
        return (j & 1095216660480L) == 0;
    }

    public static final long H(float f10, long j) {
        long jFloatToIntBits = j | (Float.floatToIntBits(f10) & 4294967295L);
        V0.o[] oVarArr = V0.n.f12763b;
        return jFloatToIntBits;
    }

    public static InterfaceC1425n J(C1375d c1375d, K3.n nVar, ArrayList arrayList, boolean z10) {
        InterfaceC1425n interfaceC1425nA;
        O9.a.Q("reduce", 1, arrayList);
        O9.a.R(2, "reduce", arrayList);
        InterfaceC1425n interfaceC1425nC = ((C1454t) nVar.f6128b).c(nVar, (InterfaceC1425n) arrayList.get(0));
        if (!(interfaceC1425nC instanceof AbstractC1395h)) {
            throw new IllegalArgumentException("Callback should be a method");
        }
        if (arrayList.size() == 2) {
            interfaceC1425nA = ((C1454t) nVar.f6128b).c(nVar, (InterfaceC1425n) arrayList.get(1));
            if (interfaceC1425nA instanceof C1385f) {
                throw new IllegalArgumentException("Failed to parse initial value");
            }
        } else {
            if (c1375d.v() == 0) {
                throw new IllegalStateException("Empty array with no initial value error");
            }
            interfaceC1425nA = null;
        }
        AbstractC1395h abstractC1395h = (AbstractC1395h) interfaceC1425nC;
        int iV = c1375d.v();
        int i5 = z10 ? 0 : iV - 1;
        int i10 = z10 ? iV - 1 : 0;
        int i11 = true == z10 ? 1 : -1;
        if (interfaceC1425nA == null) {
            interfaceC1425nA = c1375d.w(i5);
            i5 += i11;
        }
        while ((i10 - i5) * i11 >= 0) {
            if (c1375d.y(i5)) {
                interfaceC1425nA = abstractC1395h.a(nVar, Arrays.asList(interfaceC1425nA, c1375d.w(i5), new C1390g(Double.valueOf(i5)), c1375d));
                if (interfaceC1425nA instanceof C1385f) {
                    throw new IllegalStateException("Reduce operation failed");
                }
                i5 += i11;
            } else {
                i5 += i11;
            }
        }
        return interfaceC1425nA;
    }

    public static C1375d K(C1375d c1375d, K3.n nVar, C1420m c1420m, Boolean bool, Boolean bool2) {
        C1375d c1375d2 = new C1375d();
        Iterator itR = c1375d.r();
        while (itR.hasNext()) {
            int iIntValue = ((Integer) itR.next()).intValue();
            if (c1375d.y(iIntValue)) {
                InterfaceC1425n interfaceC1425nA = c1420m.a(nVar, Arrays.asList(c1375d.w(iIntValue), new C1390g(Double.valueOf(iIntValue)), c1375d));
                if (interfaceC1425nA.d().equals(bool)) {
                    break;
                }
                if (bool2 == null || interfaceC1425nA.d().equals(bool2)) {
                    c1375d2.x(iIntValue, interfaceC1425nA);
                }
            }
        }
        return c1375d2;
    }

    public static final void a(boolean z10, N9.n nVar, InterfaceC0829n interfaceC0829n, int i5) {
        W.r rVar = (W.r) interfaceC0829n;
        rVar.V(-642000585);
        int i10 = (rVar.g(z10) ? 4 : 2) | i5 | (rVar.h(nVar) ? 32 : 16);
        if ((i10 & 19) == 18 && rVar.z()) {
            rVar.N();
        } else {
            InterfaceC0808c0 interfaceC0808c0N = C0811e.N(nVar, rVar);
            Object objI = rVar.I();
            W.W w2 = C0827m.f13381a;
            if (objI == w2) {
                objI = c7.j.f(C0811e.w(rVar), rVar);
            }
            C1358d c1358d = ((C0847z) objI).f13531a;
            Object objI2 = rVar.I();
            Object obj = objI2;
            if (objI2 == w2) {
                N9.n nVar2 = (N9.n) interfaceC0808c0N.getValue();
                C1734i c1734i = new C1734i(z10);
                c1734i.f21557d = c1358d;
                c1734i.f21558e = nVar2;
                rVar.d0(c1734i);
                obj = c1734i;
            }
            C1734i c1734i2 = (C1734i) obj;
            boolean zF = rVar.f((N9.n) interfaceC0808c0N.getValue()) | rVar.f(c1358d);
            Object objI3 = rVar.I();
            if (zF || objI3 == w2) {
                c1734i2.f21558e = (N9.n) interfaceC0808c0N.getValue();
                c1734i2.f21557d = c1358d;
                rVar.d0(C3657B.f31911a);
            }
            Boolean boolValueOf = Boolean.valueOf(z10);
            boolean zH = ((i10 & 14) == 4) | rVar.h(c1734i2);
            Object objI4 = rVar.I();
            Object obj2 = null;
            if (zH || objI4 == w2) {
                objI4 = new C3.o(c1734i2, z10, (D9.d) null);
                rVar.d0(objI4);
            }
            C0811e.e((N9.n) objI4, rVar, boolValueOf);
            InterfaceC1640B interfaceC1640B = (InterfaceC1640B) rVar.k(AbstractC1730e.f21549a);
            if (interfaceC1640B == null) {
                rVar.T(544166745);
                View view = (View) rVar.k(AndroidCompositionLocals_androidKt.f17063f);
                Intrinsics.checkNotNullParameter(view, "<this>");
                interfaceC1640B = (InterfaceC1640B) T9.j.z(T9.j.B(T9.j.A(view, C1641C.f20851c), C1641C.f20852d));
                rVar.p(false);
            } else {
                rVar.T(544164296);
                rVar.p(false);
            }
            if (interfaceC1640B == null) {
                rVar.T(544168748);
                Context baseContext = (Context) rVar.k(AndroidCompositionLocals_androidKt.f17059b);
                while (true) {
                    if (!(baseContext instanceof ContextWrapper)) {
                        break;
                    }
                    if (baseContext instanceof InterfaceC1640B) {
                        obj2 = baseContext;
                        break;
                    }
                    baseContext = ((ContextWrapper) baseContext).getBaseContext();
                }
                interfaceC1640B = (InterfaceC1640B) obj2;
                rVar.p(false);
            } else {
                rVar.T(544164377);
                rVar.p(false);
            }
            if (interfaceC1640B == null) {
                throw new IllegalStateException("No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner");
            }
            C1639A c1639aA = interfaceC1640B.a();
            InterfaceC1160t interfaceC1160t = (InterfaceC1160t) rVar.k(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            boolean zH2 = rVar.h(c1639aA) | rVar.h(interfaceC1160t) | rVar.h(c1734i2);
            Object objI5 = rVar.I();
            if (zH2 || objI5 == w2) {
                objI5 = new A.T(c1639aA, interfaceC1160t, c1734i2, 11);
                rVar.d0(objI5);
            }
            C0811e.d(interfaceC1160t, c1639aA, (N9.k) objI5, rVar);
        }
        C0837r0 c0837r0T = rVar.t();
        if (c0837r0T != null) {
            c0837r0T.f13449d = new C1735j(z10, nVar, i5);
        }
    }

    public static final void b(boolean z10, T0.h hVar, M.P p10, InterfaceC0829n interfaceC0829n, int i5) {
        int i10;
        boolean z11;
        T0.h hVar2;
        W.r rVar = (W.r) interfaceC0829n;
        rVar.V(-1344558920);
        if ((i5 & 6) == 0) {
            i10 = (rVar.g(z10) ? 4 : 2) | i5;
        } else {
            i10 = i5;
        }
        if ((i5 & 48) == 0) {
            i10 |= rVar.f(hVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i10 |= rVar.h(p10) ? Fields.RotationX : Fields.SpotShadowColor;
        }
        if ((i10 & 147) == 146 && rVar.z()) {
            rVar.N();
            z11 = z10;
            hVar2 = hVar;
        } else {
            int i11 = i10 & 14;
            boolean zF = (i11 == 4) | rVar.f(p10);
            Object objI = rVar.I();
            W.W w2 = C0827m.f13381a;
            if (zF || objI == w2) {
                objI = new M.N(p10, z10);
                rVar.d0(objI);
            }
            I.e0 e0Var = (I.e0) objI;
            boolean zH = rVar.h(p10) | (i11 == 4);
            Object objI2 = rVar.I();
            if (zH || objI2 == w2) {
                objI2 = new M.Q(p10, z10);
                rVar.d0(objI2);
            }
            InterfaceC0427l interfaceC0427l = (InterfaceC0427l) objI2;
            boolean zF2 = I0.N.f(p10.j().f8017b);
            boolean zH2 = rVar.h(e0Var);
            Object objI3 = rVar.I();
            if (zH2 || objI3 == w2) {
                objI3 = new K8.y(e0Var, null, 4);
                rVar.d0(objI3);
            }
            z11 = z10;
            hVar2 = hVar;
            AbstractC0424i.b(interfaceC0427l, z11, hVar2, zF2, 0L, AbstractC2948B.a(C2128l.f23537a, e0Var, (N9.n) objI3), rVar, (i10 << 3) & 1008);
        }
        C0837r0 c0837r0T = rVar.t();
        if (c0837r0T != null) {
            c0837r0T.f13449d = new C0421f(z11, hVar2, p10, i5);
        }
    }

    public static final void c(ta.a aVar, ta.c cVar, String str) {
        Logger logger = ta.d.f28342i;
        StringBuilder sb = new StringBuilder();
        sb.append(cVar.f28336b);
        sb.append(' ');
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        sb.append(str2);
        sb.append(": ");
        sb.append(aVar.f28329a);
        logger.fine(sb.toString());
    }

    public static List d(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        List listAsList = Arrays.asList(objArr);
        Intrinsics.checkNotNullExpressionValue(listAsList, "asList(...)");
        return listAsList;
    }

    public static final Object e(R5.x xVar, F9.c frame) throws Throwable {
        try {
            if (xVar.isDone()) {
                return AbstractC0942g.h(xVar);
            }
            C0878h c0878h = new C0878h(1, K3.f.q(frame));
            c0878h.s();
            xVar.a(new C3.m(xVar, c0878h, 1), EnumC0947l.f15361a);
            c0878h.u(new C0948m(0, xVar));
            Object objR = c0878h.r();
            if (objR == E9.a.f3145a) {
                Intrinsics.checkNotNullParameter(frame, "frame");
            }
            return objR;
        } catch (ExecutionException e10) {
            Throwable cause = e10.getCause();
            Intrinsics.checkNotNull(cause);
            throw cause;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0056 -> B:21:0x0059). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object f(t0.C2950D r7, F9.a r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof x.C3430d0
            if (r0 == 0) goto L13
            r0 = r8
            x.d0 r0 = (x.C3430d0) r0
            int r1 = r0.f30549c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f30549c = r1
            goto L18
        L13:
            x.d0 r0 = new x.d0
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f30548b
            E9.a r1 = E9.a.f3145a
            int r2 = r0.f30549c
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L32
            if (r2 != r4) goto L2a
            t0.D r7 = r0.f30547a
            b3.AbstractC1220f.H(r8)
            goto L59
        L2a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L32:
            b3.AbstractC1220f.H(r8)
            t0.E r8 = r7.f27984w
            t0.i r8 = r8.f27990w
            java.lang.Object r8 = r8.f28017a
            int r2 = r8.size()
            r5 = r3
        L40:
            if (r5 >= r2) goto L75
            java.lang.Object r6 = r8.get(r5)
            t0.u r6 = (t0.u) r6
            boolean r6 = r6.f28036d
            if (r6 == 0) goto L72
        L4c:
            r0.f30547a = r7
            r0.f30549c = r4
            t0.j r8 = t0.EnumC2961j.f28023c
            java.lang.Object r8 = r7.a(r8, r0)
            if (r8 != r1) goto L59
            return r1
        L59:
            t0.i r8 = (t0.C2960i) r8
            java.lang.Object r8 = r8.f28017a
            int r2 = r8.size()
            r5 = r3
        L62:
            if (r5 >= r2) goto L75
            java.lang.Object r6 = r8.get(r5)
            t0.u r6 = (t0.u) r6
            boolean r6 = r6.f28036d
            if (r6 == 0) goto L6f
            goto L4c
        L6f:
            int r5 = r5 + 1
            goto L62
        L72:
            int r5 = r5 + 1
            goto L40
        L75:
            z9.B r7 = z9.C3657B.f31911a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: H6.u0.f(t0.D, F9.a):java.lang.Object");
    }

    public static final Object g(C2951E c2951e, N9.n nVar, D9.d dVar) {
        Object objJ = c2951e.J(new I.b0(dVar.getContext(), nVar, null), dVar);
        return objJ == E9.a.f3145a ? objJ : C3657B.f31911a;
    }

    public static void h(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [long[]] */
    /* JADX WARN: Type inference failed for: r5v4, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v6, types: [short[]] */
    public static boolean i(Object[] objArr, Object[] objArr2) {
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        int length = objArr.length;
        for (int i5 = 0; i5 < length; i5++) {
            Object obj = objArr[i5];
            Object obj2 = objArr2[i5];
            if (obj != obj2) {
                if (obj == null || obj2 == null) {
                    return false;
                }
                if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
                    if (!i((Object[]) obj, (Object[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
                    if (!Arrays.equals((byte[]) obj, (byte[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof short[]) && (obj2 instanceof short[])) {
                    if (!Arrays.equals((short[]) obj, (short[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof int[]) && (obj2 instanceof int[])) {
                    if (!Arrays.equals((int[]) obj, (int[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof long[]) && (obj2 instanceof long[])) {
                    if (!Arrays.equals((long[]) obj, (long[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof float[]) && (obj2 instanceof float[])) {
                    if (!Arrays.equals((float[]) obj, (float[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof double[]) && (obj2 instanceof double[])) {
                    if (!Arrays.equals((double[]) obj, (double[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof char[]) && (obj2 instanceof char[])) {
                    if (!Arrays.equals((char[]) obj, (char[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof boolean[]) && (obj2 instanceof boolean[])) {
                    if (!Arrays.equals((boolean[]) obj, (boolean[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof C3677s) && (obj2 instanceof C3677s)) {
                    byte[] bArr = ((C3677s) obj).f31940a;
                    byte[] bArr2 = ((C3677s) obj2).f31940a;
                    if (bArr == null) {
                        bArr = null;
                    }
                    if (!Arrays.equals(bArr, bArr2 != null ? bArr2 : null)) {
                        return false;
                    }
                } else if ((obj instanceof C3684z) && (obj2 instanceof C3684z)) {
                    short[] sArr = ((C3684z) obj).f31950a;
                    ?? r5 = ((C3684z) obj2).f31950a;
                    if (sArr == null) {
                        sArr = null;
                    }
                    if (!Arrays.equals(sArr, (short[]) (r5 != 0 ? r5 : null))) {
                        return false;
                    }
                } else if ((obj instanceof C3679u) && (obj2 instanceof C3679u)) {
                    int[] iArr = ((C3679u) obj).f31943a;
                    ?? r52 = ((C3679u) obj2).f31943a;
                    if (iArr == null) {
                        iArr = null;
                    }
                    if (!Arrays.equals(iArr, (int[]) (r52 != 0 ? r52 : null))) {
                        return false;
                    }
                } else if ((obj instanceof C3681w) && (obj2 instanceof C3681w)) {
                    long[] jArr = ((C3681w) obj).f31946a;
                    ?? r53 = ((C3681w) obj2).f31946a;
                    if (jArr == null) {
                        jArr = null;
                    }
                    if (!Arrays.equals(jArr, (long[]) (r53 != 0 ? r53 : null))) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(obj, obj2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void j(int i5, int i10, int i11, byte[] bArr, byte[] destination) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(bArr, i10, destination, i5, i11 - i10);
    }

    public static void k(int i5, int i10, int i11, int[] iArr, int[] destination) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(iArr, i10, destination, i5, i11 - i10);
    }

    public static void l(int i5, int i10, int i11, Object[] objArr, Object[] destination) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(objArr, i10, destination, i5, i11 - i10);
    }

    public static void m(char[] cArr, char[] destination, int i5, int i10, int i11) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(cArr, i10, destination, i5, i11 - i10);
    }

    public static void n(float[] fArr, float[] destination, int i5, int i10, int i11) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(fArr, i10, destination, i5, i11 - i10);
    }

    public static /* synthetic */ void o(int i5, int i10, int i11, int[] iArr, int[] iArr2) {
        if ((i11 & 2) != 0) {
            i5 = 0;
        }
        if ((i11 & 8) != 0) {
            i10 = iArr.length;
        }
        k(i5, 0, i10, iArr, iArr2);
    }

    public static /* synthetic */ void p(int i5, int i10, int i11, Object[] objArr, Object[] objArr2) {
        if ((i11 & 4) != 0) {
            i5 = 0;
        }
        if ((i11 & 8) != 0) {
            i10 = objArr.length;
        }
        l(0, i5, i10, objArr, objArr2);
    }

    public static byte[] r(byte[] bArr, int i5, int i10) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        t(i10, bArr.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i5, i10);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOfRange, "copyOfRange(...)");
        return bArrCopyOfRange;
    }

    public static Object[] s(int i5, int i10, Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        t(i10, objArr.length);
        Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, i5, i10);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOfRange, "copyOfRange(...)");
        return objArrCopyOfRange;
    }

    public static final void t(int i5, int i10) {
        if (i5 > i10) {
            throw new IndexOutOfBoundsException(A.f0.h("toIndex (", i5, ") is greater than size (", i10, ")."));
        }
    }

    public static androidx.lifecycle.T u(Class modelClass) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        try {
            Object objNewInstance = modelClass.getDeclaredConstructor(null).newInstance(null);
            Intrinsics.checkNotNullExpressionValue(objNewInstance, "{\n            modelClass…).newInstance()\n        }");
            return (androidx.lifecycle.T) objNewInstance;
        } catch (IllegalAccessException e10) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e10);
        } catch (InstantiationException e11) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e11);
        } catch (NoSuchMethodException e12) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e12);
        }
    }

    public static void v(int i5, int i10, Object obj, Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Arrays.fill(objArr, i5, i10, obj);
    }

    public static void w(int[] iArr, int i5) {
        int length = iArr.length;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Arrays.fill(iArr, 0, length, i5);
    }

    public static void x(long[] jArr) {
        int length = jArr.length;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Arrays.fill(jArr, 0, length, -9187201950435737472L);
    }

    public static final int z(int i5, InterfaceC0194w interfaceC0194w, Object obj) {
        int iA;
        return (obj == null || interfaceC0194w.b() == 0 || (i5 < interfaceC0194w.b() && Intrinsics.areEqual(obj, interfaceC0194w.c(i5))) || (iA = interfaceC0194w.a(obj)) == -1) ? i5 : iA;
    }

    public abstract void I(String str);
}
