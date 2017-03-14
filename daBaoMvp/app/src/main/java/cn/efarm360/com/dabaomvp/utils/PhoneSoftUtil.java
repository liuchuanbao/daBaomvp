package cn.efarm360.com.dabaomvp.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import org.xmlpull.v1.XmlPullParser;

public class PhoneSoftUtil {
    private static final String TAG = "PhoneSoftUtil";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String[] getIMSIs(Context r15) {
        /*
        r10 = 2;
        r4 = new java.lang.String[r10];
        r10 = "phone";
        r8 = r15.getSystemService(r10);	 Catch:{ IllegalArgumentException -> 0x00ca }
        r8 = (android.telephony.TelephonyManager) r8;	 Catch:{ IllegalArgumentException -> 0x00ca }
        r10 = 0;
        r11 = r8.getSubscriberId();	 Catch:{ IllegalArgumentException -> 0x00ca }
        r4[r10] = r11;	 Catch:{ IllegalArgumentException -> 0x00ca }
        r10 = r8.getClass();	 Catch:{ Exception -> 0x00c3 }
        r11 = "getSubscriberIdGemini";
        r12 = 1;
        r12 = new java.lang.Class[r12];	 Catch:{ Exception -> 0x00c3 }
        r13 = 0;
        r14 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x00c3 }
        r12[r13] = r14;	 Catch:{ Exception -> 0x00c3 }
        r6 = r10.getDeclaredMethod(r11, r12);	 Catch:{ Exception -> 0x00c3 }
        r10 = 1;
        r6.setAccessible(r10);	 Catch:{ Exception -> 0x00c3 }
        r11 = 1;
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ Exception -> 0x00c3 }
        r12 = 0;
        r13 = 1;
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Exception -> 0x00c3 }
        r10[r12] = r13;	 Catch:{ Exception -> 0x00c3 }
        r10 = r6.invoke(r8, r10);	 Catch:{ Exception -> 0x00c3 }
        r10 = (java.lang.String) r10;	 Catch:{ Exception -> 0x00c3 }
        r4[r11] = r10;	 Catch:{ Exception -> 0x00c3 }
    L_0x003c:
        r10 = 1;
        r10 = r4[r10];	 Catch:{ IllegalArgumentException -> 0x00ca }
        if (r10 == 0) goto L_0x004c;
    L_0x0041:
        r10 = "";
        r11 = 1;
        r11 = r4[r11];	 Catch:{ IllegalArgumentException -> 0x00ca }
        r10 = r10.equals(r11);	 Catch:{ IllegalArgumentException -> 0x00ca }
        if (r10 == 0) goto L_0x0088;
    L_0x004c:
        r10 = "com.android.internal.telephony.PhoneFactory";
        r1 = java.lang.Class.forName(r10);	 Catch:{ Exception -> 0x00cc }
        r10 = "getServiceName";
        r11 = 2;
        r11 = new java.lang.Class[r11];	 Catch:{ Exception -> 0x00cc }
        r12 = 0;
        r13 = java.lang.String.class;
        r11[r12] = r13;	 Catch:{ Exception -> 0x00cc }
        r12 = 1;
        r13 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x00cc }
        r11[r12] = r13;	 Catch:{ Exception -> 0x00cc }
        r5 = r1.getMethod(r10, r11);	 Catch:{ Exception -> 0x00cc }
        r10 = 2;
        r10 = new java.lang.Object[r10];	 Catch:{ Exception -> 0x00cc }
        r11 = 0;
        r12 = "phone";
        r10[r11] = r12;	 Catch:{ Exception -> 0x00cc }
        r11 = 1;
        r12 = 1;
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Exception -> 0x00cc }
        r10[r11] = r12;	 Catch:{ Exception -> 0x00cc }
        r7 = r5.invoke(r1, r10);	 Catch:{ Exception -> 0x00cc }
        r7 = (java.lang.String) r7;	 Catch:{ Exception -> 0x00cc }
        r9 = r15.getSystemService(r7);	 Catch:{ Exception -> 0x00cc }
        r9 = (android.telephony.TelephonyManager) r9;	 Catch:{ Exception -> 0x00cc }
        r10 = 1;
        r11 = r9.getSubscriberId();	 Catch:{ Exception -> 0x00cc }
        r4[r10] = r11;	 Catch:{ Exception -> 0x00cc }
    L_0x0088:
        r10 = 1;
        r10 = r4[r10];	 Catch:{ IllegalArgumentException -> 0x00ca }
        if (r10 == 0) goto L_0x0098;
    L_0x008d:
        r10 = "";
        r11 = 1;
        r11 = r4[r11];	 Catch:{ IllegalArgumentException -> 0x00ca }
        r10 = r10.equals(r11);	 Catch:{ IllegalArgumentException -> 0x00ca }
        if (r10 == 0) goto L_0x00c2;
    L_0x0098:
        r10 = r8.getClass();	 Catch:{ Exception -> 0x00d2 }
        r11 = "getSimSerialNumber";
        r12 = 1;
        r12 = new java.lang.Class[r12];	 Catch:{ Exception -> 0x00d2 }
        r13 = 0;
        r14 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x00d2 }
        r12[r13] = r14;	 Catch:{ Exception -> 0x00d2 }
        r0 = r10.getDeclaredMethod(r11, r12);	 Catch:{ Exception -> 0x00d2 }
        r10 = 1;
        r0.setAccessible(r10);	 Catch:{ Exception -> 0x00d2 }
        r11 = 1;
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ Exception -> 0x00d2 }
        r12 = 0;
        r13 = 1;
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Exception -> 0x00d2 }
        r10[r12] = r13;	 Catch:{ Exception -> 0x00d2 }
        r10 = r0.invoke(r8, r10);	 Catch:{ Exception -> 0x00d2 }
        r10 = (java.lang.String) r10;	 Catch:{ Exception -> 0x00d2 }
        r4[r11] = r10;	 Catch:{ Exception -> 0x00d2 }
    L_0x00c2:
        return r4;
    L_0x00c3:
        r2 = move-exception;
        r10 = 1;
        r11 = 0;
        r4[r10] = r11;	 Catch:{ IllegalArgumentException -> 0x00ca }
        goto L_0x003c;
    L_0x00ca:
        r10 = move-exception;
        goto L_0x00c2;
    L_0x00cc:
        r3 = move-exception;
        r10 = 1;
        r11 = 0;
        r4[r10] = r11;	 Catch:{ IllegalArgumentException -> 0x00ca }
        goto L_0x0088;
    L_0x00d2:
        r3 = move-exception;
        r10 = 1;
        r11 = 0;
        r4[r10] = r11;	 Catch:{ IllegalArgumentException -> 0x00ca }
        goto L_0x00c2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.efarm360.scan.util.PhoneSoftUtil.getIMSIs(android.content.Context):java.lang.String[]");
    }

    public static String getIccid(Context c) {
        return ((TelephonyManager) c.getSystemService("phone")).getSimSerialNumber();
    }

    public static String getImsi(Context c) {
        return ((TelephonyManager) c.getSystemService("phone")).getSubscriberId();
    }

    public static String getDeviceID(Context c) {
        return ((TelephonyManager) c.getSystemService("phone")).getDeviceId();
    }

    public static String getMacAddress(Context c) {
        String macAddress = null;
        String ip = null;
        WifiManager wifiMgr = (WifiManager) c.getSystemService("wifi");
        WifiInfo info = wifiMgr == null ? null : wifiMgr.getConnectionInfo();
        if (info != null) {
            macAddress = info.getMacAddress();
            ip = int2ip((long) info.getIpAddress());
        }
        L.d(TAG, "getMacAddress mac=" + macAddress + ",ip=" + ip);
        return macAddress;
    }

    public static String getIp(Context c) {
        String macAddress = null;
        String ip = null;
        WifiManager wifiMgr = (WifiManager) c.getSystemService("wifi");
        WifiInfo info = wifiMgr == null ? null : wifiMgr.getConnectionInfo();
        if (info != null) {
            macAddress = info.getMacAddress();
            ip = int2ip((long) info.getIpAddress());
        }
        L.d(TAG, "getMacAddress mac=" + macAddress + ",ip=" + ip);
        return ip;
    }

    public static String int2ip(long ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 255).append(".");
        sb.append((ipInt >> 8) & 255).append(".");
        sb.append((ipInt >> 16) & 255).append(".");
        sb.append((ipInt >> 24) & 255);
        return sb.toString();
    }

    public static String getVersionName(Context c) {
        String versionName = XmlPullParser.NO_NAMESPACE;
        try {
            return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return versionName;
        }
    }

    public static int getVersionCode(Context c) {
        int versionCode = 0;
        try {
            return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return versionCode;
        }
    }
}
