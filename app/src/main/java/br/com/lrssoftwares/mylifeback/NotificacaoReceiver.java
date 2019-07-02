package br.com.lrssoftwares.mylifeback;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificacaoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int notificationId = intent.getIntExtra("notificationId", 0);

        // Se o id for igual a 1, remove a notificação e para o serviço.
        if (notificationId == 1) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(notificationId);

            Intent service = new Intent(context, MonitorService.class);
            context.stopService(service);
        }
        // Se for qualquer outro valor, cancela a notificação
        else {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(notificationId);
        }
    }
}
