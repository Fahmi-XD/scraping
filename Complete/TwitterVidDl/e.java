package tweeter.savetwitter.twittervideodownloader.downloadtwittervideos.extractors;

import B5.a;
import D4.s;
import H5.m;
import H5.q;
import H5.r;
import I5.G;
import I5.I;
import I5.i;
import I5.n;
import com.google.android.gms.common.internal.ImagesContract;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.k;
import kotlin.jvm.internal.t;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /home/kali/Twittee/classes6.dex */
public final class e {
    public final H5.b a(r rVar, q qVar, boolean z, long j2) throws JSONException {
        int i;
        Long lValueOf;
        Response responseExecute;
        String str;
        int i3;
        String str2;
        t.f(rVar, "source");
        t.f(qVar, "urlItem");
        ArrayList arrayList = new ArrayList();
        H5.b bVar = new H5.b(qVar.b(), "", "", "", 0, (String) null, arrayList, false, false, (JSONObject) null, 768, (k) null);
        String str3 = rVar.a() + "/";
        try {
            OkHttpClient.Builder builderNewBuilder = C5.e.a.h0().newBuilder();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            OkHttpClient okHttpClientBuild = builderNewBuilder.connectTimeout(8L, timeUnit).readTimeout(10L, timeUnit).build();
            try {
                lValueOf = b(rVar);
                if (lValueOf == null) {
                    lValueOf = Long.valueOf(i.a.h());
                }
            } catch (Exception unused) {
                lValueOf = Long.valueOf(i.a.h());
            }
            G g = G.a;
            C5.e eVar = C5.e.a;
            String strB = g.b(eVar.b0() + "___" + lValueOf, eVar.W());
            String strB2 = g.b(eVar.Y() + "___" + lValueOf, eVar.V());
            String strX = eVar.X();
            responseExecute = okHttpClientBuild.newCall(new Request.Builder().addHeader("User-Agent", I.a.a()).addHeader("Referer", str3).addHeader("cache-control", "no-cache").addHeader("x-token", strB).addHeader("x-appkey", strB2).addHeader("x-appcode", strX).url(rVar.a() + "/index.php?action=extract").post(new FormBody.Builder((Charset) null, 1, (k) null).add(ImagesContract.URL, qVar.b()).add("cookie", qVar.a()).build()).build()).execute();
        } catch (Exception e2) {
            a.C0001a c0001a = a.f91a;
            c0001a.d(e2);
            i = 0;
            c0001a.a("MY_DOWNLOADER_API error >> " + e2.getMessage(), new Object[0]);
        }
        if (responseExecute.isSuccessful()) {
            ResponseBody responseBodyBody = responseExecute.body();
            JSONObject jSONObject = new JSONObject(responseBodyBody != null ? responseBodyBody.string() : null);
            ResponseBody responseBodyBody2 = responseExecute.body();
            if (responseBodyBody2 != null) {
                responseBodyBody2.close();
            }
            JSONArray jSONArray = jSONObject.getJSONArray("formats");
            if (t.a(jSONObject.getString("status"), "success")) {
                try {
                    String string = jSONObject.getString("thumbnail");
                    str = "getString(...)";
                    try {
                        t.e(string, str);
                        bVar.q(string);
                    } catch (Exception unused2) {
                    }
                } catch (Exception unused3) {
                    str = "getString(...)";
                }
                try {
                    String strOptString = jSONObject.optString("title", "");
                    t.e(strOptString, "optString(...)");
                    bVar.u(strOptString);
                } catch (Exception e3) {
                    a.f91a.d(e3);
                }
                try {
                    bVar.o(jSONObject.getInt("duration") * 1000);
                    bVar.p(bVar.b() > 0 ? i.a.u(bVar.b()) : jSONObject.optString("duration_string", ""));
                } catch (Exception e4) {
                    a.f91a.d(e4);
                }
                try {
                    bVar.v(jSONObject.getString("display_id"));
                } catch (Exception e6) {
                    a.f91a.d(e6);
                }
                try {
                    String string2 = jSONObject.getString("web_page_url");
                    t.e(string2, str);
                    bVar.s(string2);
                } catch (Exception e7) {
                    a.f91a.d(e7);
                }
                try {
                    int length = jSONArray.length();
                    for (int i5 = 0; i5 < length; i5++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i5);
                        String string3 = jSONObject2.getString("ext");
                        n nVar = n.a;
                        t.c(string3);
                        m mVarJ = nVar.j(string3);
                        String string4 = jSONObject2.getString(ImagesContract.URL);
                        String string5 = jSONObject2.getString("resolution");
                        String strOptString2 = jSONObject2.optString("label", "");
                        if (strOptString2 == null || strOptString2.length() == 0) {
                            if (string5 != null && string5.length() != 0) {
                                String upperCase = string3.toUpperCase(Locale.ROOT);
                                t.e(upperCase, "toUpperCase(...)");
                                strOptString2 = string5 + " / " + upperCase;
                                str2 = strOptString2;
                            }
                            str2 = string3;
                        } else {
                            str2 = strOptString2;
                        }
                        String string6 = jSONObject2.getString("cookies");
                        JSONObject jSONObject3 = jSONObject2.getJSONObject("http_headers");
                        long j3 = jSONObject2.getLong("filesize");
                        String strOptString3 = jSONObject2.optString("thumbnail", null);
                        if (strOptString3 == null || strOptString3.length() == 0) {
                            strOptString3 = bVar.e();
                        }
                        String str4 = !mVarJ.l() ? strOptString3 : "";
                        t.c(string4);
                        t.c(str2);
                        String strC = i.a.c(j3);
                        t.c(string5);
                        jSONObject3.put("Cookie", string6);
                        C4.G g3 = C4.G.a;
                        t.e(jSONObject3, "apply(...)");
                        arrayList.add(new H5.c(string4, str2, mVarJ, string3, strC, j3, string5, jSONObject3, str4));
                    }
                } catch (Exception e8) {
                    a.f91a.d(e8);
                }
                if (arrayList.size() >= 1) {
                    ArrayList arrayListA = bVar.a();
                    if (arrayListA == null || !arrayListA.isEmpty()) {
                        Iterator it = arrayListA.iterator();
                        i3 = 0;
                        while (it.hasNext()) {
                            if (t.a(((H5.c) it.next()).e(), H5.n.b.b()) && (i3 = i3 + 1) < 0) {
                                s.t();
                            }
                        }
                    } else {
                        i3 = 0;
                    }
                    if (i3 > 0) {
                        if (bVar.e().length() > 0) {
                            try {
                                arrayList.add(new H5.c(bVar.e(), "Video Cover Photo", H5.n.a.b(), n.a.c(bVar.e()), "", 0L, "", (JSONObject) null, (String) null, 384, (k) null));
                            } catch (Exception e9) {
                                a.f91a.d(e9);
                            }
                        }
                    }
                    String strH = bVar.h();
                    if (strH == null || strH.length() == 0) {
                        String strI = bVar.i();
                        if (strI == null || strI.length() == 0) {
                            bVar.u(i.a.E());
                        } else {
                            bVar.u(String.valueOf(bVar.i()));
                        }
                    }
                    bVar.n(arrayList);
                    bVar.t(true);
                    i = 0;
                    a.f91a.a("MY_DOWNLOADER_API end >>", new Object[i]);
                    return bVar;
                }
            }
        }
        return bVar;
    }

    public final Long b(r rVar) {
        Response responseExecute;
        t.f(rVar, "source");
        OkHttpClient.Builder builderNewBuilder = C5.e.a.h0().newBuilder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        OkHttpClient okHttpClientBuild = builderNewBuilder.connectTimeout(2L, timeUnit).readTimeout(2L, timeUnit).build();
        Request.Builder builderAddHeader = new Request.Builder().addHeader("Referer", rVar.a() + "/").addHeader("User-Agent", I.a.a()).addHeader("cache-control", "no-cache");
        builderAddHeader.url("https://worldtimeapi.org/api/timezone/UTC");
        Long lValueOf = null;
        try {
            responseExecute = okHttpClientBuild.newCall(builderAddHeader.build()).execute();
        } catch (Exception e2) {
            a.f91a.d(e2);
        }
        if (!responseExecute.isSuccessful()) {
            return null;
        }
        ResponseBody responseBodyBody = responseExecute.body();
        lValueOf = Long.valueOf(new JSONObject(responseBodyBody != null ? responseBodyBody.string() : null).getLong("unixtime"));
        if (lValueOf == null) {
            I5.a.a.b("getUtcTimeFromAPI_ERROR");
        } else {
            I5.a.a.b("getUtcTimeFromAPI_SUCCESS");
        }
        return lValueOf;
    }
}
