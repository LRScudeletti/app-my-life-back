package br.com.lrssoftwares.mylifeback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, MonitorService.class);
            context.startService(pushIntent);
        }
    }
}
