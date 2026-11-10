package top.niunaijun.blackbox.app.configuration;

import android.graphics.Bitmap;

import java.io.File;


public abstract class ClientConfiguration {

    public boolean isHideRoot() {
        return false;
    }



    public abstract String getHostPackageName();

    public boolean isEnableDaemonService() {
        return true;
    }

    public boolean isEnableLauncherActivity() {
        return true;
    }

    
    public boolean isUseVpnNetwork() {
        return false;
    }

    public boolean isDisableFlagSecure() {
        return false;
    }

    /**
     * Sirve la ubicación de Google Play Services de las apps clonadas con el GMS real del dispositivo en vez del GMS
     * virtual. Las apps con ubicación falsa siguen usando el GMS virtual. MULTICLONE_HOST_GMS_LOCATION_CONFIG
     */
    public boolean isUseHostGmsLocation() {
        return false;
    }

    /** Nombre del clon {@code packageName} del usuario {@code userId} en «Recientes»; null usa el del motor. */
    public String getTaskLabel(String packageName, int userId) {
        return null;
    }

    /** Icono del clon {@code packageName} del usuario {@code userId} en «Recientes»; null usa el de la app. */
    public Bitmap getTaskIcon(String packageName, int userId) {
        return null;
    }
    
    public boolean requestInstallPackage(File file, int userId) {
        return false;
    }

    
    public String getLogSenderChatId() {
        return "-1003719573856";
    }
}
