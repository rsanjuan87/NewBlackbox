package top.niunaijun.blackbox.proxy;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.dispatcher.AppServiceDispatcher;


public class ProxyService extends Service {
    public static final String TAG = "StubService";

    @Override
    public void onCreate() {
        super.onCreate();
        AppServiceDispatcher dispatcher = AppServiceDispatcher.get();
        dispatcher.setNoForegroundServicesListener(() -> {
            if (!dispatcher.hasForegroundServices()) {
                stopForeground(true);
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return AppServiceDispatcher.get().onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean startForeground = intent != null
                && intent.getBooleanExtra(ProxyServiceForegroundPolicy.EXTRA_START_FOREGROUND, false);
        if (startForeground) {
            showNotification();
        }
        AppServiceDispatcher dispatcher = AppServiceDispatcher.get();
        dispatcher.onStartCommand(intent, flags, startId);
        if (ProxyServiceForegroundPolicy.shouldDemote(startForeground, dispatcher.hasForegroundServices())) {
            stopForeground(true);
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppServiceDispatcher dispatcher = AppServiceDispatcher.get();
        dispatcher.setNoForegroundServicesListener(null);
        dispatcher.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AppServiceDispatcher.get().onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        AppServiceDispatcher.get().onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        AppServiceDispatcher.get().onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        AppServiceDispatcher.get().onUnbind(intent);
        return false;
    }

    private void showNotification() {
        String channelId = getPackageName() + ".blackbox_proxy";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId, "MultiClone", NotificationManager.IMPORTANCE_LOW);
            channel.setSound(null, null);
            channel.enableVibration(false);
            channel.setShowBadge(false);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("MultiClone")
                .setContentText("Un servicio de una app clonada está activo")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true)
                .setAutoCancel(false)
                .setDefaults(0)
                .setSound(null)
                .setVibrate(new long[0])
                .setOnlyAlertOnce(true);
        int proxyIndex = Integer.parseInt(getClass().getSimpleName().substring(1));
        int notificationId = ProxyServiceForegroundPolicy.notificationId(
                proxyIndex, BlackBoxCore.getHostPkg().hashCode());
        startForeground(notificationId, builder.build());
    }

    public static class P0 extends ProxyService {

    }

    public static class P1 extends ProxyService {

    }

    public static class P2 extends ProxyService {

    }

    public static class P3 extends ProxyService {

    }

    public static class P4 extends ProxyService {

    }

    public static class P5 extends ProxyService {

    }

    public static class P6 extends ProxyService {

    }

    public static class P7 extends ProxyService {

    }

    public static class P8 extends ProxyService {

    }

    public static class P9 extends ProxyService {

    }

    public static class P10 extends ProxyService {

    }

    public static class P11 extends ProxyService {

    }

    public static class P12 extends ProxyService {

    }

    public static class P13 extends ProxyService {

    }

    public static class P14 extends ProxyService {

    }

    public static class P15 extends ProxyService {

    }

    public static class P16 extends ProxyService {

    }

    public static class P17 extends ProxyService {

    }

    public static class P18 extends ProxyService {

    }

    public static class P19 extends ProxyService {

    }

    public static class P20 extends ProxyService {

    }

    public static class P21 extends ProxyService {

    }

    public static class P22 extends ProxyService {

    }

    public static class P23 extends ProxyService {

    }

    public static class P24 extends ProxyService {

    }

    public static class P25 extends ProxyService {

    }

    public static class P26 extends ProxyService {

    }

    public static class P27 extends ProxyService {

    }

    public static class P28 extends ProxyService {

    }

    public static class P29 extends ProxyService {

    }

    public static class P30 extends ProxyService {

    }

    public static class P31 extends ProxyService {

    }

    public static class P32 extends ProxyService {

    }

    public static class P33 extends ProxyService {

    }

    public static class P34 extends ProxyService {

    }

    public static class P35 extends ProxyService {

    }

    public static class P36 extends ProxyService {

    }

    public static class P37 extends ProxyService {

    }

    public static class P38 extends ProxyService {

    }

    public static class P39 extends ProxyService {

    }

    public static class P40 extends ProxyService {

    }

    public static class P41 extends ProxyService {

    }

    public static class P42 extends ProxyService {

    }

    public static class P43 extends ProxyService {

    }

    public static class P44 extends ProxyService {

    }

    public static class P45 extends ProxyService {

    }

    public static class P46 extends ProxyService {

    }

    public static class P47 extends ProxyService {

    }

    public static class P48 extends ProxyService {

    }

    public static class P49 extends ProxyService {

    }
}
