package top.niunaijun.blackbox.utils.compat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.Locale;

import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.utils.DrawableUtils;

public class TaskDescriptionCompat {
    public static ActivityManager.TaskDescription fix(ActivityManager.TaskDescription td) {
        String label = customLabel();
        Bitmap icon = customIcon();
        if (label == null && icon == null && td.getLabel() != null && td.getIcon() != null)
            return td;

        if (label == null)
            label = td.getLabel() != null ? td.getLabel() : getTaskDescriptionLabel(BlackBoxCore.getUserId(), getApplicationLabel());
        if (icon == null)
            icon = td.getIcon();
        return new ActivityManager.TaskDescription(label, icon, td.getPrimaryColor());
    }

    /** Nombre en «Recientes»: el que da el host para este clon o, si no da ninguno, «[B<usuario>]<app>». */
    public static String getTaskDescriptionLabel(int userId, CharSequence label) {
        String custom = customLabel();
        return custom != null ? custom : String.format(Locale.CHINA, "[B%d]%s", userId, label);
    }

    /** Icono en «Recientes» que da el host para este clon, o null. */
    public static Bitmap customIcon() {
        try {
            return BlackBoxCore.get().getTaskIcon(BlackBoxCore.getAppPackageName(), BlackBoxCore.getUserId());
        } catch (Throwable e) {
            return null;
        }
    }

    private static String customLabel() {
        try {
            return BlackBoxCore.get().getTaskLabel(BlackBoxCore.getAppPackageName(), BlackBoxCore.getUserId());
        } catch (Throwable e) {
            return null;
        }
    }

    private static CharSequence getApplicationLabel() {
        try {
            PackageManager pm = BlackBoxCore.getPackageManager();
            return pm.getApplicationLabel(pm.getApplicationInfo(BlackBoxCore.getAppPackageName(), 0));
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private static Drawable getApplicationIcon() {
        try {
            
            return null;
        } catch (Exception ignore) {
            return null;
        }
    }
}
