package I5;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.webkit.URLUtil;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.mbridge.msdk.foundation.download.Command;
import com.safedk.android.utils.Logger;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLProtocolException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* renamed from: I5.i, reason: case insensitive filesystem */
/* loaded from: /home/kali/Twittee/classes4.dex */
public final class C0140i {
    public static final C0140i a = new C0140i();

    public static void safedk_Activity_startActivity_9d898b58165fa4ba0e12c3900a2b8533(Activity p0, Intent p1) {
        Logger.d("SafeDK-Special|SafeDK: Call> Landroid/app/Activity;->startActivity(Landroid/content/Intent;)V");
        if (p1 == null) {
            return;
        }
        p0.startActivity(p1);
    }

    public static void safedk_Context_startActivity_97cb3195734cf5c9cc3418feeafa6dd6(Context p0, Intent p1) {
        Logger.d("SafeDK-Special|SafeDK: Call> Landroid/content/Context;->startActivity(Landroid/content/Intent;)V");
        if (p1 == null) {
            return;
        }
        p0.startActivity(p1);
    }

    public final boolean r() {
        return false;
    }

    public final void A(Fragment fragment, int i) {
        kotlin.jvm.internal.t.f(fragment, "fragment");
        if (Build.VERSION.SDK_INT >= 33) {
            fragment.requestPermissions(new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_AUDIO", "android.permission.READ_MEDIA_VIDEO"}, i);
        } else {
            fragment.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, i);
        }
    }

    public final void B(Activity activity, String str, String str2, String str3) {
        kotlin.jvm.internal.t.f(activity, "activity");
        kotlin.jvm.internal.t.f(str, "to");
        kotlin.jvm.internal.t.f(str2, "subject");
        kotlin.jvm.internal.t.f(str3, "message");
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra("android.intent.extra.EMAIL", (String[]) D4.s.g(new String[]{str}).toArray(new String[0]));
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.TEXT", str3);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            safedk_Activity_startActivity_9d898b58165fa4ba0e12c3900a2b8533(activity, intent);
        }
    }

    public final void C(Activity activity, String str) {
        kotlin.jvm.internal.t.f(activity, "activity");
        kotlin.jvm.internal.t.f(str, "title");
        D(activity, "http://play.google.com/store/apps/details?id=" + activity.getPackageName(), str);
    }

    public final void D(Activity activity, String str, String str2) {
        kotlin.jvm.internal.t.f(activity, "activity");
        kotlin.jvm.internal.t.f(str, "url");
        kotlin.jvm.internal.t.f(str2, "title");
        ShareCompat.IntentBuilder.from(activity).setType("text/plain").setChooserTitle(str2).setText(str).startChooser();
    }

    public final Context a(Context context, String str) {
        kotlin.jvm.internal.t.f(context, "context");
        kotlin.jvm.internal.t.f(str, "language");
        Locale locale = new Locale(str);
        Configuration configuration = context.getResources().getConfiguration();
        Locale.setDefault(locale);
        configuration.setLocale(locale);
        Context contextCreateConfigurationContext = context.createConfigurationContext(configuration);
        kotlin.jvm.internal.t.e(contextCreateConfigurationContext, "createConfigurationContext(...)");
        return contextCreateConfigurationContext;
    }

    public final String b(String str) {
        if (str == null) {
            return "";
        }
        try {
            byte[] bArrDecode = Base64.decode(str, 0);
            kotlin.jvm.internal.t.e(bArrDecode, "decode(...)");
            Charset charset = StandardCharsets.UTF_8;
            kotlin.jvm.internal.t.e(charset, "UTF_8");
            return new String(bArrDecode, charset);
        } catch (Exception unused) {
            return "";
        }
    }

    public final boolean d(AppCompatActivity appCompatActivity) {
        kotlin.jvm.internal.t.f(appCompatActivity, "activity");
        return Build.VERSION.SDK_INT >= 33 ? ContextCompat.checkSelfPermission(appCompatActivity, "android.permission.READ_MEDIA_IMAGES") == 0 && ContextCompat.checkSelfPermission(appCompatActivity, "android.permission.READ_MEDIA_AUDIO") == 0 && ContextCompat.checkSelfPermission(appCompatActivity, "android.permission.READ_MEDIA_VIDEO") == 0 : ContextCompat.checkSelfPermission(appCompatActivity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(appCompatActivity, "android.permission.READ_EXTERNAL_STORAGE") == 0;
    }

    public final boolean e(Activity activity) {
        kotlin.jvm.internal.t.f(activity, "activity");
        return !(Build.VERSION.SDK_INT >= 33 ? activity.shouldShowRequestPermissionRationale("android.permission.READ_MEDIA_IMAGES") && activity.shouldShowRequestPermissionRationale("android.permission.READ_MEDIA_VIDEO") && activity.shouldShowRequestPermissionRationale("android.permission.READ_MEDIA_AUDIO") : activity.shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE"));
    }

    public final String f(String str) {
        kotlin.jvm.internal.t.f(str, "url");
        int iI0 = a5.D.i0(str, "?", 0, false, 6, null);
        if (iI0 <= -1) {
            return str;
        }
        String strSubstring = str.substring(0, iI0);
        kotlin.jvm.internal.t.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final void g(Activity activity, String str) {
        kotlin.jvm.internal.t.f(activity, "activity");
        kotlin.jvm.internal.t.f(str, "str");
        Object systemService = activity.getSystemService("clipboard");
        kotlin.jvm.internal.t.d(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipData clipDataNewPlainText = ClipData.newPlainText("simpletext", str);
        kotlin.jvm.internal.t.e(clipDataNewPlainText, "newPlainText(...)");
        ((ClipboardManager) systemService).setPrimaryClip(clipDataNewPlainText);
    }

    public final long h() {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() / 1000;
    }

    public final long i(Date date, Date date2, TimeUnit timeUnit) {
        kotlin.jvm.internal.t.f(date, "dateBig");
        kotlin.jvm.internal.t.f(date2, "dateSmall");
        kotlin.jvm.internal.t.f(timeUnit, "timeUnit");
        return timeUnit.convert(date.getTime() - date2.getTime(), TimeUnit.MILLISECONDS);
    }

    public final String j(Activity activity) {
        ClipData.Item itemAt;
        kotlin.jvm.internal.t.f(activity, "activity");
        try {
            Object systemService = activity.getSystemService("clipboard");
            kotlin.jvm.internal.t.d(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
            ClipboardManager clipboardManager = (ClipboardManager) systemService;
            if (!clipboardManager.hasPrimaryClip()) {
                return "";
            }
            ClipData primaryClip = clipboardManager.getPrimaryClip();
            return String.valueOf((primaryClip == null || (itemAt = primaryClip.getItemAt(0)) == null) ? null : itemAt.getText());
        } catch (Exception unused) {
            return "";
        }
    }

    public final String k(String str) {
        kotlin.jvm.internal.t.f(str, "url");
        try {
            String host = new URI(str).getHost();
            String strB0 = host != null ? a5.D.B0(host, "www.") : null;
            List listK0 = strB0 != null ? a5.D.K0(strB0, new String[]{"."}, false, 0, 6, null) : null;
            List list = listK0;
            if (list != null && !list.isEmpty()) {
                return (String) listK0.get(0);
            }
            return strB0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final long l(String str, String str2, String str3) throws Exception {
        Response responseExecute;
        String strHeader$default;
        kotlin.jvm.internal.t.f(str, "url");
        long j = -1;
        try {
            OkHttpClient.Builder builderNewBuilder = C5.e.a.h0().newBuilder();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            OkHttpClient okHttpClientBuild = builderNewBuilder.connectTimeout(6L, timeUnit).readTimeout(6L, timeUnit).build();
            Request.Builder builder = new Request.Builder();
            if (str2 == null || str2.length() == 0) {
                str2 = "https://google.com";
            }
            Request.Builder builderUrl = builder.addHeader("Referer", str2).addHeader(Command.HTTP_HEADER_USER_AGENT, I.a.a()).addHeader("Connection", "keep-alive").head().url(str);
            if (str3 != null && str3.length() != 0) {
                builderUrl.addHeader("Cookie", str3);
            }
            try {
                responseExecute = okHttpClientBuild.newCall(builderUrl.build()).execute();
            } catch (Exception e) {
                if (!(e instanceof SSLProtocolException) && !(e instanceof SSLHandshakeException)) {
                    throw e;
                }
                builderUrl.removeHeader("Cookie");
                responseExecute = okHttpClientBuild.newCall(builderUrl.build()).execute();
            }
            if (responseExecute.isSuccessful() && (strHeader$default = Response.header$default(responseExecute, "Content-Length", (String) null, 2, (Object) null)) != null) {
                j = Long.parseLong(strHeader$default);
            }
            responseExecute.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return j;
    }

    public final int m(String str) throws IOException {
        kotlin.jvm.internal.t.f(str, "str");
        try {
            StringBuilder sb = new StringBuilder();
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (Character.isDigit(cCharAt)) {
                    sb.append(cCharAt);
                }
            }
            return Integer.parseInt(sb.toString());
        } catch (Exception unused) {
            return 0;
        }
    }

    public final String n(String str, String str2) {
        kotlin.jvm.internal.t.f(str, "url");
        kotlin.jvm.internal.t.f(str2, "paramName");
        return Uri.parse(str).getQueryParameter(str2);
    }

    public final void o(Activity activity) {
        kotlin.jvm.internal.t.f(activity, "activity");
        String packageName = activity.getPackageName();
        kotlin.jvm.internal.t.e(packageName, "getPackageName(...)");
        w(activity, packageName);
    }

    public final boolean p(Context context, String str) throws PackageManager.NameNotFoundException {
        kotlin.jvm.internal.t.f(context, "context");
        kotlin.jvm.internal.t.f(str, "packageName");
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                com.unity3d.ads.core.data.datasource.c.a(context.getPackageManager(), str, com.unity3d.ads.core.data.datasource.b.a(0L));
                return true;
            }
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final boolean q(String str) {
        kotlin.jvm.internal.t.f(str, "url");
        return URLUtil.isHttpUrl(str) || URLUtil.isHttpsUrl(str);
    }

    public final boolean s() {
        return Build.VERSION.SDK_INT < 29;
    }

    public final boolean t(Context context) {
        kotlin.jvm.internal.t.f(context, "context");
        Object systemService = context.getSystemService("connectivity");
        kotlin.jvm.internal.t.d(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public final String u(long j) {
        String strValueOf;
        String str = "";
        if (j <= 1) {
            return "";
        }
        long j2 = 3600000;
        int i = (int) (j / j2);
        long j3 = j % j2;
        int i2 = ((int) j3) / 60000;
        int i4 = (int) ((j3 % 60000) / 1000);
        if (i > 0) {
            str = i + ":";
        }
        if (i4 < 10) {
            strValueOf = "0" + i4;
        } else {
            strValueOf = String.valueOf(i4);
        }
        return str + i2 + ":" + strValueOf;
    }

    public final void v(Activity activity, String str) throws PackageManager.NameNotFoundException {
        boolean z;
        kotlin.jvm.internal.t.f(activity, "activity");
        kotlin.jvm.internal.t.f(str, "packageName");
        Context applicationContext = activity.getApplicationContext();
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                com.unity3d.ads.core.data.datasource.c.a(activity.getPackageManager(), str, com.unity3d.ads.core.data.datasource.b.a(0L));
            } else {
                activity.getPackageManager().getPackageInfo(str, 0);
            }
            z = true;
        } catch (Exception unused) {
            z = false;
        }
        Intent launchIntentForPackage = activity.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(268435456);
            safedk_Activity_startActivity_9d898b58165fa4ba0e12c3900a2b8533(activity, launchIntentForPackage);
        } else {
            Toast.makeText(activity, "No application installed", 0).show();
        }
        if (!z) {
            Toast.makeText(applicationContext, "No application installed", 0).show();
            return;
        }
        Intent launchIntentForPackage2 = applicationContext.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage2 == null) {
            Toast.makeText(applicationContext, "No application installed", 0).show();
        } else {
            launchIntentForPackage2.addFlags(268435456);
            safedk_Context_startActivity_97cb3195734cf5c9cc3418feeafa6dd6(applicationContext, launchIntentForPackage2);
        }
    }

    public final void w(Activity activity, String str) {
        kotlin.jvm.internal.t.f(activity, "activity");
        kotlin.jvm.internal.t.f(str, "appPackageName");
        try {
            safedk_Activity_startActivity_9d898b58165fa4ba0e12c3900a2b8533(activity, new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
        } catch (Exception unused) {
            safedk_Activity_startActivity_9d898b58165fa4ba0e12c3900a2b8533(activity, new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + str)));
        }
    }

    public final void x(String str, Activity activity) {
        kotlin.jvm.internal.t.f(str, "url");
        kotlin.jvm.internal.t.f(activity, "activity");
        try {
            Uri uri = Uri.parse(str);
            kotlin.jvm.internal.t.e(uri, "parse(...)");
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                safedk_Activity_startActivity_9d898b58165fa4ba0e12c3900a2b8533(activity, intent);
            } else {
                Toast.makeText(activity, "No Browser Installed", 0).show();
            }
        } catch (Exception e) {
            B5.a.a.d(e);
        }
    }

    public final void y(AppCompatActivity appCompatActivity, int i) {
        kotlin.jvm.internal.t.f(appCompatActivity, "activity");
        if (Build.VERSION.SDK_INT >= 33) {
            ActivityCompat.requestPermissions(appCompatActivity, new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_AUDIO", "android.permission.READ_MEDIA_VIDEO"}, i);
        } else {
            ActivityCompat.requestPermissions(appCompatActivity, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, i);
        }
    }

    public final void z(AppCompatActivity appCompatActivity, int i, ActivityResultLauncher activityResultLauncher) {
        kotlin.jvm.internal.t.f(appCompatActivity, "activity");
        if (Build.VERSION.SDK_INT >= 33) {
            if (activityResultLauncher != null) {
                activityResultLauncher.launch(new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_AUDIO", "android.permission.READ_MEDIA_VIDEO"});
                return;
            } else {
                ActivityCompat.requestPermissions(appCompatActivity, new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_AUDIO", "android.permission.READ_MEDIA_VIDEO"}, i);
                return;
            }
        }
        if (activityResultLauncher != null) {
            activityResultLauncher.launch(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"});
        } else {
            ActivityCompat.requestPermissions(appCompatActivity, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, i);
        }
    }

    public final String E() {
        String string = UUID.randomUUID().toString();
        kotlin.jvm.internal.t.e(string, "toString(...)");
        return string;
    }

    public final String c(long j) {
        if (j < 1048576) {
            try {
                return ((int) (j / 1024)) + "KB";
            } catch (Exception e) {
                B5.a.a.d(e);
            }
        }
        return new DecimalFormat("0.0#").format(j / 1048576.0d) + "MB";
    }
}
