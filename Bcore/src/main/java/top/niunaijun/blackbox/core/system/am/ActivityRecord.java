package top.niunaijun.blackbox.core.system.am;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Binder;
import android.os.IBinder;

import top.niunaijun.blackbox.core.system.ProcessRecord;



public class ActivityRecord extends Binder {
    public TaskRecord task;
    public IBinder token;
    public IBinder resultTo;
    public ActivityInfo info;
    public ComponentName component;
    public Intent intent;
    public int userId;
    public boolean finished;
    public ProcessRecord processRecord;
    /** Intents que llegaron mientras la actividad se creaba; se entregan con onNewIntent. MULTICLONE_PENDING_NEW_INTENTS */
    public final java.util.List<Intent> pendingNewIntents = new java.util.ArrayList<>();

    public static ActivityRecord create(Intent intent, ActivityInfo info, IBinder resultTo, int userId) {
        ActivityRecord record = new ActivityRecord();
        record.intent = intent;
        record.info = info;
        record.component = new ComponentName(info.packageName, info.name);
        record.resultTo = resultTo;
        record.userId = userId;
        return record;
    }


}
