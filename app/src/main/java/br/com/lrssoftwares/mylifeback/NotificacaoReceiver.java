package br.com.lrssoftwares.mylifeback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificacaoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // Para o servico através da notificação
        Intent service = new Intent(context, MonitorService.class);
        context.stopService(service);
    }
}
