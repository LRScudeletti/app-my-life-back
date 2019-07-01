package br.com.lrssoftwares.mylifeback;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.rvalerio.fgchecker.AppChecker;

import java.util.Objects;

public class MonitorService extends Service {
    static final int NOTIFICATION_ID = 1;
    public static boolean servicoExecutando = false;
    Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        iniciarServico();
        handler.post(runnableCode);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && Objects.requireNonNull(intent.getAction()).equals("Monitor")) {
            iniciarServico();
        } else pararServico();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        servicoExecutando = false;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    void iniciarServico() {
        if (servicoExecutando) {
            ExibirNotificacao();
            return;
        }
        servicoExecutando = true;
    }

    void pararServico() {
        stopForeground(true);
        stopSelf();
        servicoExecutando = false;
    }

    private void ExibirNotificacao() {
        try {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            String idCanal = "1";
            String nomeCanal = "Monitor";

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel canal = new NotificationChannel(idCanal, nomeCanal, NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(canal);
            }

            Intent intentHide = new Intent(this, NotificacaoReceiver.class);

            PendingIntent removerNotificacao = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), intentHide, PendingIntent.FLAG_CANCEL_CURRENT);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), idCanal)
                    .setSmallIcon(R.drawable.notificacao_icone)
                    .setContentTitle(getString(R.string.monitor))
                    .addAction(R.drawable.fechar_icone, getString(R.string.parar_servico), removerNotificacao)
                    .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                    .setContentText(getString(R.string.tempo_gasto));

            //Intent intent = new Intent();

            //TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
            //stackBuilder.addNextIntent(intent);
            //PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            //mBuilder.setContentIntent(resultPendingIntent);

            startForeground(NOTIFICATION_ID, mBuilder.build());
        } catch (Exception erro) {
            new UtilidadesClass().enviarMensagemContato(getApplicationContext(), erro);
        }
    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // ExibirNotificacao();
            verificarProcessosRedesSociais();
            handler.postDelayed(this, 10000);
        }
    };

    private void verificarProcessosRedesSociais() {
        AppChecker appChecker = new AppChecker();
        String packageName = appChecker.getForegroundApp(getApplicationContext());
    }
}