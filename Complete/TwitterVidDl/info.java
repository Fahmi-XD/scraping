package C5;

import A2.g;
import C4.G;
import C4.p;
import D4.AbstractC0095s;
import D4.O;
import H5.n;
import H5.r;
import I5.H;
import I5.i;
import I5.j;
import a5.C0107c;
import a5.D;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;
import androidx.preference.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.helikanonlib.admanager.AppOpenAdManager;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.AbstractC0242t;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import y2.k;
import y2.s;
import y2.t;
import y2.u;
import y2.v;
import y2.w;

/* loaded from: /home/kali/Twittee/classes5.dex */
public final class e {
    public static String A;
    public static JSONObject A0;
    public static String B;

    /* renamed from: B0, reason: collision with root package name */
    public static String f3B0;
    public static String C;

    /* renamed from: C0, reason: collision with root package name */
    public static Map f4C0;

    /* renamed from: D, reason: collision with root package name */
    public static String f5D;

    /* renamed from: D0, reason: collision with root package name */
    public static Map f6D0;
    public static String E;
    public static boolean E0;

    /* renamed from: F, reason: collision with root package name */
    public static String f7F;
    public static int F0;
    public static final String G;
    public static int G0;

    /* renamed from: H, reason: collision with root package name */
    public static final r f8H;
    public static int H0;

    /* renamed from: I, reason: collision with root package name */
    public static final HashMap f9I;
    public static int I0;
    public static final String J;
    public static JSONArray J0;
    public static String K;
    public static boolean K0;
    public static String L;
    public static boolean L0;
    public static String M;
    public static final OkHttpClient M0;
    public static JSONObject N;
    public static JSONObject O;
    public static JSONObject P;

    /* renamed from: Q, reason: collision with root package name */
    public static JSONObject f10Q;
    public static JSONObject R;

    /* renamed from: S, reason: collision with root package name */
    public static JSONObject f11S;
    public static JSONObject T;
    public static JSONObject U;
    public static String V;
    public static Date W;
    public static int X;
    public static final int Y;
    public static k Z;
    public static AppOpenAdManager a0;
    public static boolean b0;
    public static boolean c;

    /* renamed from: c0, reason: collision with root package name */
    public static boolean f12c0;

    /* renamed from: d0, reason: collision with root package name */
    public static boolean f13d0;
    public static boolean e0;
    public static long f0;

    /* renamed from: g, reason: collision with root package name */
    public static boolean f14g;
    public static int g0;
    public static int h0;
    public static int i0;
    public static boolean j;
    public static int j0;
    public static int k0;
    public static boolean l0;
    public static boolean m0;
    public static boolean n;

    /* renamed from: n0, reason: collision with root package name */
    public static boolean f16n0;

    /* renamed from: o0, reason: collision with root package name */
    public static boolean f17o0;
    public static int p0;
    public static boolean q;
    public static int q0;
    public static int r0;

    /* renamed from: s0, reason: collision with root package name */
    public static boolean f20s0;
    public static final List t0;

    /* renamed from: u, reason: collision with root package name */
    public static String f22u;
    public static String u0;
    public static String v;
    public static String v0;
    public static String w;
    public static String w0;
    public static String x;
    public static String x0;

    /* renamed from: y, reason: collision with root package name */
    public static String f23y;

    /* renamed from: y0, reason: collision with root package name */
    public static String f24y0;
    public static String z;

    /* renamed from: z0, reason: collision with root package name */
    public static String f25z0;
    public static final e a = new e();
    public static String b = "denversimpleapps@gmail.com";
    public static final String d = "all_time_inapp";
    public static final String e = "yearly_premium_subs";
    public static final String f = "monthly_premium_subs";
    public static final String h = "2401b3f2-0fdd-40ec-acad-a381e9739e47";

    /* renamed from: i, reason: collision with root package name */
    public static final int f15i = 2;
    public static String k = "";
    public static final boolean l = true;
    public static final int m = 12;
    public static String o = "";
    public static String p = "TWITTER,TWDOWN";

    /* renamed from: r, reason: collision with root package name */
    public static String f18r = "";

    /* renamed from: s, reason: collision with root package name */
    public static boolean f19s = true;

    /* renamed from: t, reason: collision with root package name */
    public static String f21t = new String(j.a("73587A446B5642716E6A6A48325742733561436D5A457847555273367A4E4B79"), C0107c.b);

    public static final class a extends v {
        @Override // y2.v
        public void c(w wVar) {
            I5.a aVar = I5.a.a;
            aVar.b("adsSuccessShowInt");
            aVar.b("adsSuccessShowInt_" + (wVar != null ? wVar.name() : null));
        }

        @Override // y2.v
        public void d(y2.a aVar, String str, w wVar) {
            if (aVar == y2.a.a) {
                I5.a aVar2 = I5.a.a;
                Bundle bundle = new Bundle();
                bundle.putString("error_message", str);
                G g2 = G.a;
                aVar2.c("showintadsFailShowInt", bundle);
                return;
            }
            I5.a aVar3 = I5.a.a;
            String str2 = "adsFailShowInt_" + (wVar != null ? wVar.name() : null);
            Bundle bundle2 = new Bundle();
            bundle2.putString("error_message", str);
            G g3 = G.a;
            aVar3.c(str2, bundle2);
        }
    }

    public static final class b extends v {
        @Override // y2.v
        public void c(w wVar) {
            I5.a aVar = I5.a.a;
            aVar.b("adsSuccessShowRwrd");
            aVar.b("adsSuccessShowRwrd_" + (wVar != null ? wVar.name() : null));
        }

        @Override // y2.v
        public void d(y2.a aVar, String str, w wVar) {
            if (aVar == y2.a.a) {
                I5.a aVar2 = I5.a.a;
                Bundle bundle = new Bundle();
                bundle.putString("error_message", str);
                G g2 = G.a;
                aVar2.c("adsFailShowRwrd", bundle);
                return;
            }
            I5.a aVar3 = I5.a.a;
            String str2 = "adsFailShowRwrd_" + (wVar != null ? wVar.name() : null);
            Bundle bundle2 = new Bundle();
            bundle2.putString("error_message", str);
            G g3 = G.a;
            aVar3.c(str2, bundle2);
        }

        @Override // y2.v
        public void e(String str, Integer num, w wVar) {
            I5.a.a.b("adsRewardedVideoFinished");
        }
    }

    public static final class c implements D5.c {
        public void a() {
            H h = H.a;
            Date dateL = h.l();
            if (dateL != null) {
                if (i.a.i(new Date(), dateL, TimeUnit.HOURS) < e.a.G()) {
                    return;
                }
            }
            D5.b bVar = D5.b.a;
            bVar.p();
            if (bVar.l()) {
                B5.a.a.a("Billing > Has Premium = True", new Object[0]);
                e eVar = e.a;
                eVar.E1(false);
                k kVarQ = eVar.q();
                if (kVarQ != null) {
                    kVarQ.F0(eVar.m0());
                }
            }
            h.I();
        }
    }

    public static final class d extends t {
        public final /* synthetic */ y2.b a;

        public d(y2.b bVar) {
            this.a = bVar;
        }

        @Override // y2.t
        public void a(y2.a aVar, String str, w wVar) {
            super.a(aVar, str, wVar);
        }

        @Override // y2.t
        public void b(w wVar) {
            super.b(wVar);
            e.f4C0.put(this.a.name(), new Date());
        }
    }

    /* renamed from: C5.e$e, reason: collision with other inner class name */
    public static final class C0002e implements OnCompleteListener {
        public final /* synthetic */ e2.i a;
        public final /* synthetic */ SharedPreferences b;
        public final /* synthetic */ Activity c;

        public C0002e(e2.i iVar, SharedPreferences sharedPreferences, Activity activity) {
            this.a = iVar;
            this.b = sharedPreferences;
            this.c = activity;
        }

        /* JADX WARN: Removed duplicated region for block: B:105:0x0527  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onComplete(com.google.android.gms.tasks.Task r27) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 1336
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: C5.e.C0002e.onComplete(com.google.android.gms.tasks.Task):void");
        }
    }

    public final void f() {
        b0 = false;
        k kVar = Z;
        if (kVar != null) {
            kVar.F0(false);
        }
        AppOpenAdManager appOpenAdManager = a0;
        if (appOpenAdManager != null) {
            appOpenAdManager.disable();
        }
    }

    static {
        I5.G g2 = I5.G.a;
        f22u = g2.a("zCKc8h6vjIegjFAS0aCfnuvf3rHIHylSXSYR0r5ORmtOTC4VuvDFZIHDUbjqQrmJOOTsDUgTkrBR05g1V9ZHEA==", f21t);
        v = "tiktok-downloader-download-tiktok-videos-without-watermark.p.rapidapi.com";
        w = "tiktok-all-in-one.p.rapidapi.com";
        x = "twitter-downloader-download-twitter-videos-gifs-and-images.p.rapidapi.com";
        f23y = "pinterest-downloader-download-pinterest-image-video-and-reels.p.rapidapi.com";
        z = g2.a("G2FkTWujnoSwT/GemxeTr+EvG4OBki2+Fnep+PJYHCU=", f21t);
        A = g2.a("UAXFoplp5H87RRX7/HvrKAvO6rkH3IE/u0931xcvThO7sxOvv1cvz7H14iRSSHXM", f21t);
        B = g2.a("FJ7lkHHe8QSX1S5rCNkYlI9eBZTYLP1s2GgJFC5ZJhG6LWX37b5p7fyiZZN07uYR", f21t);
        C = g2.a("OFx0xDz4WXeHQn+5mZtaf7eScT6WbgYRHV/cVLKIoYYbfpL0JYRPMY7G75BJQ5n2", f21t);
        f5D = g2.a("wy9V4gkza+fPVHVADo1nC8ln5otwFWqJ8xpElEXcS38=", f21t);
        E = g2.a("OOhnzPWeoSRYkllyrxGNVtXhpGBRQopjVBn11aYKGSt7mWKkFEgpCVlu8ZYCYpGK", f21t);
        f7F = "haticitwitter";
        G = "https://www.youtube.com/watch?v=PQzhNZobrIg";
        H5.a aVar = H5.a.b;
        r rVarB = aVar.b();
        f8H = rVarB;
        HashMap mapJ = O.j(new p(H5.a.a.b().b(), "Video Downloader"), new p(aVar.b().b(), "Twitter Video Downloader"), new p(H5.a.d.b().b(), "Tiktok Video Downloader"), new p(H5.a.g.b().b(), "Pinterest Video Downloader"), new p(H5.a.e.b().b(), "Dailymotion Video Downloader"), new p(H5.a.h.b().b(), "Vimeo Video Downloader"), new p(H5.a.f.b().b(), "Vk Video Downloader"), new p(H5.a.c.b().b(), "Instagram Downloader"), new p(H5.a.j.b().b(), "Instagram Story Downloader"), new p(H5.a.i.b().b(), "Facebook Downloader"));
        f9I = mapJ;
        J = (String) mapJ.get(rVarB.b());
        K = "";
        L = "{\n            \"TWITTER\": \"MY_DOWNLOADER_API_TWO,TWDOWN,RAPIDAPI_MANHG_ALLINONE,TWSAVER,MY_DOWNLOADER_API\",\n            \"INSTAGRAM\": \"INSTAGRAM,MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API,BOTDOWNLOADER_INSTAGRAM\",\n            \"INSTAGRAM_STORY\": \"INSTAGRAM,MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE\",\n            \"DAILYMOTION\": \"DAILYMOTION,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API\",\n            \"PINTEREST\": \"PINTEREST,MY_DOWNLOADER_API_TWO,PINTEREST_JUSTMOBI,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API\",\n            \"TIKTOK\": \"MUSICALLYDOWN,MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,TIKTOK_HOSSAM,MY_DOWNLOADER_API,TIKFAST\",\n            \"VK\": \"VK,MY_DOWNLOADER_API,RAPIDAPI_MANHG_ALLINONE\",\n            \"VIMEO\": \"RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API_TWO,MY_DOWNLOADER_API,BOTDOWNLOADER_VIMEO\",\n            \"FACEBOOK\": \"FACEBOOK,MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API,GETFVID_FACEBOOK,BOTDOWNLOADER_FACEBOOK\",\n            \"LIKEE\": \"MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API,BOTDOWNLOADER_LIKEE\",\n            \"MXTAKATAK\": \"MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API,BOTDOWNLOADER_MXTAKATAK\",\n            \"LINKEDIN\": \"MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API,BOTDOWNLOADER_LINKEDIN\"\n        }";
        M = "twdown";
        O = new JSONObject("{\n        \"package_name\": \"videodownloader.pinterest.video.download.pinterestdownloader\",\n        \"app_name\": \"Video Downloader For Pinterest\",\n        \"icon_url\": \"https://play-lh.googleusercontent.com/119hbZqFazfKpa2JcRXLjlo9Vtv4gVpP5a92r-KitxuQw_U1MeyPxfv07llqdZOzOw=s360-rw\"\n      }");
        R = new JSONObject("{\n            \"package_name\": \"download.video.tiktok.nowatermark.tiktokdownloader\",\n            \"app_name\": \"Remove TikTok Watermark\",\n            \"icon_url\": \"https://play-lh.googleusercontent.com/u3TNHLujrTldsoiRUdI6WQNJ-ctxGnoWzBN7tva7aMjVZaVz41A364ulmW_T-b8g_FHI=s128-rw\"\n          }");
        T = new JSONObject("{\n            \"package_name\": \"download.video.free.videodownlaoder.savevideo\",\n            \"app_name\": \"All Video Downloader\",\n            \"icon_url\": \"https://play-lh.googleusercontent.com/5NmcgB35yWVO9aFUaMO36InCcVlEMgZuTbHOUUiuTKkMIdxXBbrrUMSvHBi0rBoBzwc=s128-rw\"\n          }");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("", "");
        U = jSONObject;
        V = "Bearer AAAAAAAAAAAAAAAAAAAAANRILgAAAAAAnNwIzUejRCOuH5E6I8xnZz4puTs%3D1Zv7ttfk8LF81IUq16cHjhLTvJu4FA33AGWWjCpTnA";
        W = new Date();
        X = 8192;
        Y = 100;
        b0 = true;
        e0 = true;
        f0 = 12L;
        g0 = 35;
        h0 = 10;
        i0 = 3;
        j0 = 12;
        k0 = 2;
        l0 = true;
        m0 = true;
        f17o0 = true;
        p0 = 30;
        q0 = 240;
        t0 = new ArrayList();
        u0 = "";
        v0 = "ca-app-pub-8018256245650162~8297150006";
        w0 = "201300697";
        x0 = "4195313";
        f24y0 = "pHS9IvJQm4f3s9fPw3xpJLFIu6lLDfukm72lv_OqABUrmgxiCnDKCx4vAPNHI9lOv3oDcJZngd-ek0cHus5pBP";
        f25z0 = "{\n        \"banner\" : \"admob,applovin\",\n        \"interstitial\" : \"applovin,admob\",\n        \"mrec\" : \"admob,applovin\",\n        \"rewarded\" : \"applovin,admob\",        \n        \"native\" : \"admob,applovin\",\n        \"native_medium\" : \"admob,applovin\",\n        \"app_open\" : \"admob,applovin\"\n      }";
        A0 = new JSONObject(f25z0);
        f3B0 = "[\n            {\n\t\t\t\t\"format_banner\" : \"ca-app-pub-8018256245650162/1579202871\",\n\t\t\t\t\"format_interstitial\" : \"ca-app-pub-8018256245650162/9706988800\",\n\t\t\t\t\"format_mrec\" : \"ca-app-pub-8018256245650162/5868983439\",\n\t\t\t\t\"format_rewarded\" : \"ca-app-pub-8018256245650162/5502151927\",\t\t\t\t\n\t\t\t\t\"format_open_app_ad\" : \"ca-app-pub-8018256245650162/9270006541\",\n                \"format_native\" : \"ca-app-pub-8018256245650162/5785470372\",\n                \"format_native_medium\": \"ca-app-pub-8018256245650162/5593898684\",\n\t\t\t\t\"is_active\" : true,\n\t\t\t\t\"platform\" : \"admob\"\n\t\t\t}, {\n\t\t\t\t\"format_banner\" : \"532ebd6579c208a0\",\n                \"format_interstitial\" : \"a3ef152bd3f83e34\",\n                \"format_mrec\" : \"48980babebd47937\",\n                \"format_rewarded\" : \"0b0e7f43e81c68c7\",\n                \"format_native\" : \"b587de7082d421bd\",\n                \"format_native_medium\" : \"3c812d69b992b4c5\",\n                \"format_open_app_ad\": \"16c5942f118120dc\",\n                \"is_active\" : true,\n\t\t\t\t\"platform\" : \"applovin\"\n\t\t\t},{\n\t\t\t\t\"format_banner\" : \"HaticiTwitterDownloaderBanner\",\n                \"format_interstitial\" : \"HaticiTwitterDownloaderInterstitial\",\n                \"format_mrec\" : \"mrc\",\n                \"format_rewarded\" : \"rewardedVideo\",\n                \"is_active\" : true,\n\t\t\t\t\"platform\" : \"unityads\"\n\t\t\t}\n        ]";
        f4C0 = new LinkedHashMap();
        f6D0 = new LinkedHashMap();
        F0 = 3;
        J0 = new JSONArray(D.f1("[]").toString());
        M0 = new OkHttpClient();
    }

    public static /* synthetic */ void I0(e eVar, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = -1;
        }
        eVar.H0(i2);
    }

    public static final void Q0(Activity activity) {
        e eVar = a;
        Context applicationContext = activity.getApplicationContext();
        AbstractC0242t.e(applicationContext, "getApplicationContext(...)");
        eVar.M0(applicationContext);
    }

    public static final u Y0(u uVar) {
        return uVar == null ? new u(new A2.c(v0), false, false, false, false, false, 62, null) : uVar;
    }

    public static final u Z0(u uVar) {
        return uVar == null ? new u(new A2.j(x0), false, false, false, false, false, 62, null) : uVar;
    }

    public static final u a1(u uVar) {
        return uVar == null ? new u(new g(f24y0), false, false, false, false, false, 62, null) : uVar;
    }

    public final String A0() {
        return v;
    }

    public final void A1(int i2) {
        k0 = i2;
    }

    public final boolean B() {
        return f14g;
    }

    public final String B0() {
        return V;
    }

    public final void B1(String str) {
        AbstractC0242t.f(str, "<set-?>");
        o = str;
    }

    public final String C() {
        return L;
    }

    public final JSONObject C0() {
        return P;
    }

    public final void C1(JSONObject jSONObject) {
        O = jSONObject;
    }

    public final String D(String sourceCode) {
        AbstractC0242t.f(sourceCode, "sourceCode");
        String strOptString = new JSONObject(L).optString(sourceCode, "");
        AbstractC0242t.e(strOptString, "optString(...)");
        return strOptString;
    }

    public final String D0() {
        return x;
    }

    public final void D1(boolean z3) {
        n = z3;
    }

    public final JSONObject E() {
        return f11S;
    }

    public final String E0() {
        return M;
    }

    public final void E1(boolean z3) {
        b0 = z3;
    }

    public final JSONArray F() {
        return J0;
    }

    public final void F1(boolean z3) {
        f13d0 = z3;
    }

    public final int G() {
        return m;
    }

    public final boolean G0(Activity activity, String adSize) {
        AbstractC0242t.f(activity, "activity");
        AbstractC0242t.f(adSize, "adSize");
        return f0(activity, adSize) > 0;
    }

    public final void G1(boolean z3) {
        f12c0 = z3;
    }

    public final int H() {
        return r0;
    }

    public final void H0(int i2) {
        int i3 = G0;
        if (i2 == -1) {
            i2 = F0;
        }
        G0 = i3 + i2;
    }

    public final void H1(boolean z3) {
        f16n0 = z3;
    }

    public final JSONObject I() {
        return U;
    }

    public final void I1(boolean z3) {
        K0 = z3;
    }

    public final String J() {
        return G;
    }

    public final void J0() {
        H0++;
    }

    public final void J1(boolean z3) {
        L0 = z3;
    }

    public final boolean K() {
        return m0;
    }

    public final void K0(Context _context) throws JSONException {
        AbstractC0242t.f(_context, "_context");
        X0(_context, new JSONArray(f3B0), new JSONObject(f25z0));
        k kVar = new k();
        kVar.C0(AbstractC0095s.g("default"));
        kVar.F0(b0);
        kVar.t0(e0);
        kVar.x0(e0);
        kVar.s0(f0);
        kVar.u0(true);
        kVar.D0(h0);
        kVar.A0(g0);
        kVar.E0(g0);
        kVar.r0(t0);
        kVar.G0(false);
        kVar.v0(k);
        Z = kVar;
        kVar.y0(new a());
        k kVar2 = Z;
        if (kVar2 != null) {
            kVar2.z0(new b());
        }
    }

    public final void K1(boolean z3) {
        f20s0 = z3;
    }

    public final String L() {
        return d;
    }

    public final void L0(Context context) {
        AbstractC0242t.f(context, "context");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        f12c0 = defaultSharedPreferences.getBoolean("show_ad_on_start", f12c0);
        f13d0 = defaultSharedPreferences.getBoolean("show_ad_on_exit_activity", f13d0);
        e0 = defaultSharedPreferences.getBoolean("autoload_interstitial", e0);
        f0 = defaultSharedPreferences.getLong("autoload_interstitial_delay", f0);
        g0 = defaultSharedPreferences.getInt("interstitial_min_seconds", g0);
        h0 = defaultSharedPreferences.getInt("interstitial_random_seconds", h0);
        i0 = defaultSharedPreferences.getInt("download_count_interval_for_rewarded_video", i0);
        f25z0 = String.valueOf(defaultSharedPreferences.getString("ad_platforms_sort_json_str", f25z0));
        j0 = defaultSharedPreferences.getInt("native_ad_frequency", j0);
        k0 = defaultSharedPreferences.getInt("native_ad_load_count", k0);
        l0 = defaultSharedPreferences.getBoolean("app_open_ad_enabled", l0);
        m0 = defaultSharedPreferences.getBoolean("hide_banner_after_rewarded_earn", m0);
        f16n0 = defaultSharedPreferences.getBoolean("show_banner", f16n0);
        f17o0 = defaultSharedPreferences.getBoolean("show_native_ad", f17o0);
        c = defaultSharedPreferences.getBoolean("is_inapp_active", c);
        p0 = defaultSharedPreferences.getInt("appopenad_delay", p0);
        r0 = defaultSharedPreferences.getInt("first_native_ad_index", r0);
        f20s0 = defaultSharedPreferences.getBoolean("show_gift_ad_in_toolbar", f20s0);
        E0 = defaultSharedPreferences.getBoolean("download_quota_enabled", E0);
        F0 = defaultSharedPreferences.getInt("download_quota_per_rewarded", F0);
        n = defaultSharedPreferences.getBoolean("redirect_to_new_app", n);
        o = String.valueOf(defaultSharedPreferences.getString("new_app_package_name", o));
        f19s = defaultSharedPreferences.getBoolean("is_download_available", f19s);
        L = String.valueOf(defaultSharedPreferences.getString("extract_source_orders", L));
        f18r = String.valueOf(defaultSharedPreferences.getString("banned_countries", f18r));
        q = defaultSharedPreferences.getBoolean("check_banned_countries", q);
        V = String.valueOf(defaultSharedPreferences.getString("twitter_bearer_token", V));
        b = String.valueOf(defaultSharedPreferences.getString("contact_email", b));
        try {
            JSONObject jSONObject = N;
            N = new JSONObject(String.valueOf(defaultSharedPreferences.getString("insta_story_downloader_app_infos", jSONObject != null ? jSONObject.toString() : null)));
        } catch (Exception unused) {
        }
        try {
            JSONObject jSONObject2 = O;
            O = new JSONObject(String.valueOf(defaultSharedPreferences.getString("pinterest_downloader_app_infos", jSONObject2 != null ? jSONObject2.toString() : null)));
        } catch (Exception unused2) {
        }
        try {
            JSONObject jSONObject3 = P;
            P = new JSONObject(String.valueOf(defaultSharedPreferences.getString("twitter_downloader_app_infos", jSONObject3 != null ? jSONObject3.toString() : null)));
        } catch (Exception unused3) {
        }
        try {
            JSONObject jSONObject4 = f10Q;
            f10Q = new JSONObject(String.valueOf(defaultSharedPreferences.getString("instagram_downloader_app_infos", jSONObject4 != null ? jSONObject4.toString() : null)));
        } catch (Exception unused4) {
        }
        try {
            JSONObject jSONObject5 = R;
            R = new JSONObject(String.valueOf(defaultSharedPreferences.getString("tiktok_downloader_app_infos", jSONObject5 != null ? jSONObject5.toString() : null)));
        } catch (Exception unused5) {
        }
        try {
            JSONObject jSONObject6 = f11S;
            f11S = new JSONObject(String.valueOf(defaultSharedPreferences.getString("facebook_downloader_app_infos", jSONObject6 != null ? jSONObject6.toString() : null)));
        } catch (Exception unused6) {
        }
        try {
            JSONObject jSONObject7 = T;
            T = new JSONObject(String.valueOf(defaultSharedPreferences.getString("all_downloader_app_infos", jSONObject7 != null ? jSONObject7.toString() : null)));
        } catch (Exception unused7) {
        }
        K0 = defaultSharedPreferences.getBoolean("show_family_apps_in_main", K0);
        L0 = defaultSharedPreferences.getBoolean("show_family_apps_in_preferences", L0);
        try {
            JSONArray jSONArray = J0;
            defaultSharedPreferences.getString("family_apps", jSONArray != null ? jSONArray.toString() : null);
            JSONArray jSONArray2 = J0;
            J0 = new JSONArray(String.valueOf(defaultSharedPreferences.getString("family_apps", jSONArray2 != null ? jSONArray2.toString() : null)));
        } catch (Exception unused8) {
            J0 = new JSONArray();
        }
        try {
            JSONObject jSONObject8 = U;
            U = new JSONObject(String.valueOf(defaultSharedPreferences.getString("help_videos", jSONObject8 != null ? jSONObject8.toString() : null)));
        } catch (Exception unused9) {
        }
        z = String.valueOf(defaultSharedPreferences.getString("my_downloader_api_host", z));
        E = String.valueOf(defaultSharedPreferences.getString("my_downloader_api_host_two", E));
    }

    public final void L1(boolean z3) {
        f17o0 = z3;
    }

    public final JSONObject M() {
        return f10Q;
    }

    public final void M0(Context context) {
        AbstractC0242t.f(context, "context");
        D5.b.a.n(context, new c());
    }

    public final void M1(int i2) {
        I0 = i2;
    }

    public final JSONObject N() {
        return N;
    }

    public final void N0(Activity activity, String adSize, boolean z3) {
        AbstractC0242t.f(activity, "activity");
        AbstractC0242t.f(adSize, "adSize");
        try {
            if (b0 && f17o0) {
                y2.b bVar = AbstractC0242t.a(adSize, "small") ? y2.b.e : y2.b.f;
                Date date = (Date) f6D0.get(bVar.name());
                if (date != null) {
                    long jI = i.a.i(new Date(), date, TimeUnit.SECONDS);
                    f6D0.put(bVar.name(), new Date());
                    if (jI < 15) {
                        return;
                    }
                }
                f6D0.put(bVar.name(), new Date());
                Date date2 = (Date) f4C0.get(bVar.name());
                if (z3 && date2 != null) {
                    long jI2 = i.a.i(new Date(), date2, TimeUnit.SECONDS);
                    int i2 = q0;
                    if (f0(activity, adSize) < 1) {
                        i2 = 30;
                    }
                    if (jI2 < i2) {
                        return;
                    }
                }
                k kVar = Z;
                if (kVar != null) {
                    k.j0(kVar, activity, bVar, k0, new d(bVar), 0, 16, (Object) null);
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            B5.a.a.d(e3);
        }
    }

    public final void N1(JSONObject jSONObject) {
        R = jSONObject;
    }

    public final int O() {
        return g0;
    }

    public final void O0() {
        try {
            if (new SimpleDateFormat("yyyy-MM-dd").parse("2025-08-31").compareTo(new Date()) > 0) {
                p0 = 30;
                f12c0 = false;
                f16n0 = false;
                K0 = false;
                e0 = true;
                L = "{\n                    \"TWITTER\": \"MY_DOWNLOADER_API_TWO,TWDOWN,RAPIDAPI_MANHG_ALLINONE,TWSAVER,MY_DOWNLOADER_API\",\n                    \"INSTAGRAM\": \"INSTAGRAM,MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API,BOTDOWNLOADER_INSTAGRAM\",\n                    \"INSTAGRAM_STORY\": \"INSTAGRAM,MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE\",\n                    \"DAILYMOTION\": \"DAILYMOTION,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API\",\n                    \"PINTEREST\": \"PINTEREST,MY_DOWNLOADER_API_TWO,PINTEREST_JUSTMOBI,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API\",\n                    \"TIKTOK\": \"MUSICALLYDOWN,MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,TIKTOK_HOSSAM,MY_DOWNLOADER_API,TIKFAST\",\n                    \"VK\": \"VK,MY_DOWNLOADER_API,RAPIDAPI_MANHG_ALLINONE\",\n                    \"VIMEO\": \"RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API_TWO,MY_DOWNLOADER_API,BOTDOWNLOADER_VIMEO\",\n                    \"FACEBOOK\": \"FACEBOOK,MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API,GETFVID_FACEBOOK,BOTDOWNLOADER_FACEBOOK\",\n                    \"LIKEE\": \"MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API,BOTDOWNLOADER_LIKEE\",\n                    \"MXTAKATAK\": \"MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API,BOTDOWNLOADER_MXTAKATAK\",\n                    \"LINKEDIN\": \"MY_DOWNLOADER_API_TWO,RAPIDAPI_MANHG_ALLINONE,MY_DOWNLOADER_API,BOTDOWNLOADER_LINKEDIN\"\n                }";
            }
        } catch (Exception unused) {
        }
    }

    public final void O1(String str) {
        AbstractC0242t.f(str, "<set-?>");
        V = str;
    }

    public final int P() {
        return h0;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c3 A[Catch: Exception -> 0x0012, TryCatch #0 {Exception -> 0x0012, blocks: (B:3:0x0006, B:6:0x000d, B:24:0x006c, B:29:0x0086, B:31:0x009d, B:36:0x00ba, B:40:0x00c3, B:42:0x00e8, B:41:0x00c9, B:11:0x0015, B:13:0x002c, B:14:0x002e, B:16:0x003c, B:17:0x0046, B:19:0x004a, B:20:0x004d, B:22:0x0051, B:23:0x0054), top: B:47:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c9 A[Catch: Exception -> 0x0012, TryCatch #0 {Exception -> 0x0012, blocks: (B:3:0x0006, B:6:0x000d, B:24:0x006c, B:29:0x0086, B:31:0x009d, B:36:0x00ba, B:40:0x00c3, B:42:0x00e8, B:41:0x00c9, B:11:0x0015, B:13:0x002c, B:14:0x002e, B:16:0x003c, B:17:0x0046, B:19:0x004a, B:20:0x004d, B:22:0x0051, B:23:0x0054), top: B:47:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void P0(android.app.Activity r11) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: C5.e.P0(android.app.Activity):void");
    }

    public final void P1(JSONObject jSONObject) {
        P = jSONObject;
    }

    public final boolean Q() {
        return f19s;
    }

    public final boolean Q1(Activity activity, String adSize, ViewGroup viewGroup) {
        AbstractC0242t.f(activity, "activity");
        AbstractC0242t.f(adSize, "adSize");
        AbstractC0242t.f(viewGroup, "viewGroup");
        if (!b0) {
            return false;
        }
        y2.b bVar = AbstractC0242t.a(adSize, "small") ? y2.b.e : y2.b.f;
        k kVar = Z;
        if (kVar != null) {
            return k.R0(kVar, activity, bVar, viewGroup, (v) null, 0, 16, (Object) null);
        }
        return false;
    }

    public final boolean R() {
        return l;
    }

    public final void R0(String str) {
        AbstractC0242t.f(str, "<set-?>");
        f25z0 = str;
    }

    public final void R1(Context _context) throws JSONException {
        AbstractC0242t.f(_context, "_context");
        if (Z == null) {
            K0(_context);
            B5.a.a.c("updateAdManagerFromConstants adManager null error", new Object[0]);
            return;
        }
        X0(_context, new JSONArray(f3B0), new JSONObject(f25z0));
        k kVar = Z;
        AbstractC0242t.c(kVar);
        kVar.F0(b0);
        k kVar2 = Z;
        AbstractC0242t.c(kVar2);
        kVar2.t0(e0);
        k kVar3 = Z;
        AbstractC0242t.c(kVar3);
        kVar3.s0(f0);
        k kVar4 = Z;
        AbstractC0242t.c(kVar4);
        kVar4.D0(h0);
        k kVar5 = Z;
        AbstractC0242t.c(kVar5);
        kVar5.A0(g0);
        k kVar6 = Z;
        AbstractC0242t.c(kVar6);
        kVar6.E0(g0);
        k kVar7 = Z;
        AbstractC0242t.c(kVar7);
        kVar7.r0(t0);
    }

    public final boolean S() {
        return c;
    }

    public final void S0(JSONObject jSONObject) {
        T = jSONObject;
    }

    public final String T(Context context, int i2) {
        File filesDir;
        String str = Environment.DIRECTORY_MOVIES;
        if (i2 == n.b.b().h()) {
            str = Environment.DIRECTORY_MOVIES;
        } else if (i2 == n.c.b().h()) {
            str = Environment.DIRECTORY_MUSIC;
        } else if (i2 == n.a.b().h()) {
            str = Environment.DIRECTORY_PICTURES;
        }
        String absolutePath = (context == null || (filesDir = context.getFilesDir()) == null) ? null : filesDir.getAbsolutePath();
        String str2 = File.separator;
        return absolutePath + str2 + "private_files" + str2 + str;
    }

    public final void T0(int i2) {
        p0 = i2;
    }

    public final int U() {
        return Y;
    }

    public final void U0(boolean z3) {
        l0 = z3;
    }

    public final String V() {
        return B;
    }

    public final void V0(boolean z3) {
        e0 = z3;
    }

    public final String W() {
        return A;
    }

    public final void W0(long j2) {
        f0 = j2;
    }

    public final String X() {
        return f7F;
    }

    public final void X0(Context _context, JSONArray jSONArray, JSONObject configAdPlatformsSortJsonObj) throws JSONException {
        Map map;
        k kVar;
        k kVar2;
        k kVar3;
        k kVar4;
        k kVar5;
        k kVar6;
        JSONArray configAdPlatformsJsonObj = jSONArray;
        AbstractC0242t.f(_context, "_context");
        AbstractC0242t.f(configAdPlatformsJsonObj, "configAdPlatformsJsonObj");
        AbstractC0242t.f(configAdPlatformsSortJsonObj, "configAdPlatformsSortJsonObj");
        JSONObject jSONObject = new JSONObject(f25z0);
        A0 = jSONObject;
        u uVar = null;
        String strOptString = jSONObject.optString("interstitial", null);
        int i2 = 0;
        if (strOptString != null && (kVar6 = Z) != null) {
            kVar6.q0(0, "interstitial", strOptString);
        }
        String strOptString2 = A0.optString("banner", null);
        if (strOptString2 != null && (kVar5 = Z) != null) {
            kVar5.q0(0, "banner", strOptString2);
        }
        String strOptString3 = A0.optString("rewarded", null);
        if (strOptString3 != null && (kVar4 = Z) != null) {
            kVar4.q0(0, "rewarded", strOptString3);
        }
        String strOptString4 = A0.optString("mrec", null);
        if (strOptString4 != null && (kVar3 = Z) != null) {
            kVar3.q0(0, "mrec", strOptString4);
        }
        String strOptString5 = A0.optString("native", null);
        if (strOptString5 != null && (kVar2 = Z) != null) {
            kVar2.q0(0, "native", strOptString5);
        }
        String strOptString6 = A0.optString("native_medium", null);
        if (strOptString6 != null && (kVar = Z) != null) {
            kVar.q0(0, "native_medium", strOptString6);
        }
        String string = configAdPlatformsSortJsonObj.getString("interstitial");
        String string2 = configAdPlatformsSortJsonObj.getString("banner");
        String string3 = configAdPlatformsSortJsonObj.getString("rewarded");
        String string4 = configAdPlatformsSortJsonObj.getString("mrec");
        String strOptString7 = configAdPlatformsSortJsonObj.optString("native", "");
        configAdPlatformsSortJsonObj.optString("native_medium", "");
        k kVar7 = Z;
        u uVarK = kVar7 != null ? kVar7.K(w.b) : null;
        k kVar8 = Z;
        u uVarK2 = kVar8 != null ? kVar8.K(w.d) : null;
        k kVar9 = Z;
        Map mapK = O.k(C4.v.a("admob", new C5.b(uVarK)), C4.v.a("unityads", new C5.c(uVarK2)), C4.v.a("applovin", new C5.d(kVar9 != null ? kVar9.K(w.f) : null)));
        t0.clear();
        int length = configAdPlatformsJsonObj.length();
        while (i2 < length) {
            JSONObject jSONObject2 = configAdPlatformsJsonObj.getJSONObject(i2);
            String string5 = jSONObject2.getString("platform");
            if (jSONObject2.optBoolean("is_active", true)) {
                S4.a aVar = (S4.a) mapK.get(string5);
                u uVar2 = aVar != null ? (u) aVar.invoke() : uVar;
                if (uVar2 == null) {
                    return;
                }
                AbstractC0242t.c(string);
                AbstractC0242t.c(string5);
                uVar2.h(D.U(string, string5, true));
                AbstractC0242t.c(string2);
                uVar2.g(D.U(string2, string5, true));
                AbstractC0242t.c(string3);
                uVar2.k(D.U(string3, string5, true));
                AbstractC0242t.c(string4);
                uVar2.i(D.U(string4, string5, true));
                AbstractC0242t.c(strOptString7);
                uVar2.j(D.U(strOptString7, string5, true));
                String strOptString8 = jSONObject2.optString("format_interstitial", "ints");
                String strOptString9 = jSONObject2.optString("format_rewarded", "rew");
                String strOptString10 = jSONObject2.optString("format_banner", "ban");
                map = mapK;
                String strOptString11 = jSONObject2.optString("format_mrec", "mrc");
                String strOptString12 = jSONObject2.optString("format_native", "nat");
                String strOptString13 = jSONObject2.optString("format_open_app_ad", "aoa");
                String strOptString14 = jSONObject2.optString("format_native_medium", "natmedium");
                uVar2.a().i().add(new s("default", strOptString8 == null ? "ints" : strOptString8, strOptString9 == null ? "rew" : strOptString9, strOptString10 == null ? "ban" : strOptString10, strOptString11 == null ? "mrc" : strOptString11, strOptString12 == null ? "nat" : strOptString12, strOptString13 == null ? "aoa" : strOptString13, strOptString14 == null ? "natmedium" : strOptString14));
                t0.add(uVar2);
            } else {
                map = mapK;
            }
            i2++;
            configAdPlatformsJsonObj = jSONArray;
            mapK = map;
            uVar = null;
        }
        if (t0.size() == 0) {
            I5.a.a.b("noAdPlatforms");
        }
    }

    public final String Y() {
        return f5D;
    }

    public final String Z() {
        return z;
    }

    public final String a0() {
        return E;
    }

    public final String b0() {
        return C;
    }

    public final void b1(AppOpenAdManager appOpenAdManager) {
        a0 = appOpenAdManager;
    }

    public final int c0() {
        return j0;
    }

    public final void c1(String str) {
        AbstractC0242t.f(str, "<set-?>");
        f18r = str;
    }

    public final int d0() {
        return k0;
    }

    public final void d1(boolean z3) {
        q = z3;
    }

    public final String e0() {
        return o;
    }

    public final void e1(String str) {
        AbstractC0242t.f(str, "<set-?>");
        b = str;
    }

    public final int f0(Activity activity, String adSize) {
        AbstractC0242t.f(activity, "activity");
        AbstractC0242t.f(adSize, "adSize");
        y2.b bVar = AbstractC0242t.a(adSize, "small") ? y2.b.e : y2.b.f;
        k kVar = Z;
        if (kVar != null) {
            return k.S(kVar, activity, bVar, 0, 4, (Object) null);
        }
        return 0;
    }

    public final void f1(int i2) {
        i0 = i2;
    }

    public final JSONObject g() {
        return A0;
    }

    public final String g0() {
        return h;
    }

    public final void g1(boolean z3) {
        E0 = z3;
    }

    public final String h() {
        return f25z0;
    }

    public final OkHttpClient h0() {
        return M0;
    }

    public final void h1(int i2) {
        F0 = i2;
    }

    public final JSONObject i() {
        return T;
    }

    public final JSONObject i0() {
        return O;
    }

    public final void i1(int i2) {
        X = i2;
    }

    public final int j() {
        return p0;
    }

    public final String j0() {
        return f23y;
    }

    public final void j1(String str) {
        AbstractC0242t.f(str, "<set-?>");
        L = str;
    }

    public final int k() {
        return f15i;
    }

    public final boolean k0() {
        return n;
    }

    public final void k1(JSONObject jSONObject) {
        f11S = jSONObject;
    }

    public final boolean l() {
        return l0;
    }

    public final int l0() {
        int i2 = G0;
        if (i2 < 1) {
            G0 = i2 + F0;
        }
        return G0 - I0;
    }

    public final void l1(JSONArray jSONArray) {
        AbstractC0242t.f(jSONArray, "<set-?>");
        J0 = jSONArray;
    }

    public final String m() {
        return f22u;
    }

    public final boolean m0() {
        return b0;
    }

    public final void m1(int i2) {
        r0 = i2;
    }

    public final r n() {
        return f8H;
    }

    public final boolean n0() {
        return f13d0;
    }

    public final void n1(JSONObject jSONObject) {
        U = jSONObject;
    }

    public final boolean o() {
        return e0;
    }

    public final boolean o0() {
        return f12c0;
    }

    public final void o1(boolean z3) {
        m0 = z3;
    }

    public final long p() {
        return f0;
    }

    public final boolean p0() {
        return f16n0;
    }

    public final void p1(JSONObject jSONObject) {
        f10Q = jSONObject;
    }

    public final k q() {
        return Z;
    }

    public final boolean q0() {
        return K0;
    }

    public final void q1(JSONObject jSONObject) {
        N = jSONObject;
    }

    public final AppOpenAdManager r() {
        return a0;
    }

    public final boolean r0() {
        return L0;
    }

    public final void r1(int i2) {
        g0 = i2;
    }

    public final String s() {
        return f18r;
    }

    public final boolean s0() {
        return f20s0;
    }

    public final void s1(int i2) {
        h0 = i2;
    }

    public final boolean t() {
        return q;
    }

    public final boolean t0() {
        return f17o0;
    }

    public final void t1(boolean z3) {
        f19s = z3;
    }

    public final String u() {
        return b;
    }

    public final String u0() {
        return f;
    }

    public final void u1(boolean z3) {
        j = z3;
    }

    public final String v() {
        return K;
    }

    public final String v0() {
        return e;
    }

    public final void v1(boolean z3) {
        c = z3;
    }

    public final int w() {
        return i0;
    }

    public final int w0() {
        return H0;
    }

    public final void w1(Date date) {
        AbstractC0242t.f(date, "<set-?>");
        W = date;
    }

    public final boolean x() {
        return E0;
    }

    public final int x0() {
        return I0;
    }

    public final void x1(String str) {
        AbstractC0242t.f(str, "<set-?>");
        z = str;
    }

    public final int y() {
        return F0;
    }

    public final JSONObject y0() {
        return R;
    }

    public final void y1(String str) {
        AbstractC0242t.f(str, "<set-?>");
        E = str;
    }

    public final int z() {
        return X;
    }

    public final String z0() {
        return w;
    }

    public final void z1(int i2) {
        j0 = i2;
    }

    public final String A(Context context, int i2) {
        String absolutePath;
        File filesDir;
        String DIRECTORY_DOWNLOADS;
        if (AbstractC0242t.a(Environment.getExternalStorageState(), "mounted")) {
            if (i2 == n.b.b().h()) {
                DIRECTORY_DOWNLOADS = Environment.DIRECTORY_MOVIES;
                AbstractC0242t.e(DIRECTORY_DOWNLOADS, "DIRECTORY_MOVIES");
            } else if (i2 == n.c.b().h()) {
                DIRECTORY_DOWNLOADS = Environment.DIRECTORY_MUSIC;
                AbstractC0242t.e(DIRECTORY_DOWNLOADS, "DIRECTORY_MUSIC");
            } else if (i2 == n.a.b().h()) {
                DIRECTORY_DOWNLOADS = Environment.DIRECTORY_PICTURES;
                AbstractC0242t.e(DIRECTORY_DOWNLOADS, "DIRECTORY_PICTURES");
            } else {
                DIRECTORY_DOWNLOADS = Environment.DIRECTORY_DOWNLOADS;
                AbstractC0242t.e(DIRECTORY_DOWNLOADS, "DIRECTORY_DOWNLOADS");
            }
            if (i.a.s()) {
                String absolutePath2 = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS + File.separator + J).getAbsolutePath();
                AbstractC0242t.e(absolutePath2, "getAbsolutePath(...)");
                return absolutePath2;
            }
            return DIRECTORY_DOWNLOADS + File.separator + J;
        }
        if (context != null && (filesDir = context.getFilesDir()) != null) {
            absolutePath = filesDir.getAbsolutePath();
        } else {
            absolutePath = null;
        }
        return absolutePath + File.separator + J;
    }

    public final boolean F0() {
        if (l0() > 0) {
            return true;
        }
        return false;
    }
}
