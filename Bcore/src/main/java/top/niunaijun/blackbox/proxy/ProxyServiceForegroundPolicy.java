package top.niunaijun.blackbox.proxy;

import android.os.Build;

public final class ProxyServiceForegroundPolicy {
    public static final String EXTRA_START_FOREGROUND = "top.niunaijun.blackbox.proxy.extra.START_FOREGROUND";
    private static final int NOTIFICATION_ID_BASE = 10000;
    private static final int PROXY_COUNT = 50;

    private ProxyServiceForegroundPolicy() {
    }

    public static boolean shouldPromote(boolean requireForeground, int sdkInt) {
        return requireForeground && sdkInt >= Build.VERSION_CODES.O;
    }

    public static boolean shouldDemote(boolean foregroundRequest, boolean hasActiveServices) {
        return foregroundRequest && !hasActiveServices;
    }

    public static int notificationId(int proxyIndex, int daemonNotificationId) {
        if (proxyIndex < 0 || proxyIndex >= PROXY_COUNT) {
            throw new IllegalArgumentException("Proxy index out of range: " + proxyIndex);
        }
        int candidate = NOTIFICATION_ID_BASE + proxyIndex;
        return candidate == daemonNotificationId ? candidate + PROXY_COUNT : candidate;
    }
}
