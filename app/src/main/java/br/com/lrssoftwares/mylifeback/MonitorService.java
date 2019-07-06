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

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MonitorService extends Service {
    public static boolean servicoExecutando = false;
    Handler handler = new Handler();

    int intervalo = 10;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && Objects.requireNonNull(intent.getAction()).equals("Monitor")) {
            if (!servicoExecutando) {
                ExibirNotificacao(1, getString(R.string.monitor), getString(R.string.historico_detalhes));
                handler.post(runnableCode);
                servicoExecutando = true;
            }
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        servicoExecutando = false;
    }

    private void ExibirNotificacao(int notificationId, String titulo, String mensagem) {
        try {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder notificationBuilder;

            String idCanal = "1";
            String nomeCanal = "Monitor";

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel canal = new NotificationChannel(idCanal, nomeCanal, NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(canal);
            }

            Intent intentHide = new Intent(this, NotificacaoReceiver.class);
            intentHide.putExtra("notificationId", notificationId);
            PendingIntent removerNotificacao = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), intentHide, PendingIntent.FLAG_CANCEL_CURRENT);

            notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), idCanal)
                    .setSmallIcon(R.drawable.notificacao_icone)
                    .setContentTitle(titulo)
                    .addAction(notificationId == 1 ? R.drawable.fechar_icone : R.drawable.ok_icone,
                            notificationId == 1 ? getString(R.string.parar_servico) : getString(R.string.ok_notificação),
                            removerNotificacao)
                    .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(mensagem))
                    .setContentText(mensagem)
                    .setOngoing(notificationId == 1);

            //Intent intent = new Intent();

            //TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
            //stackBuilder.addNextIntent(intent);
            //PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            //mBuilder.setContentIntent(resultPendingIntent);

            if (notificationId == 1)
                startForeground(notificationId, notificationBuilder.build());
            else
                notificationManager.notify(notificationId, notificationBuilder.build());
        } catch (Exception erro) {
            new UtilidadesClass().enviarMensagemContato(getApplicationContext(), erro);
        }
    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            if (servicoExecutando) {
                verificarProcessosRedesSociais();
                handler.postDelayed(this, intervalo * 1000);
            } else {
                handler.removeCallbacks(this);
            }
        }
    };

    private void verificarProcessosRedesSociais() {
        try {
            AppChecker appChecker = new AppChecker();
            String packageName = appChecker.getForegroundApp(getApplicationContext());

            CrudClass crudClass = new CrudClass(getApplication());
            List<RedesSociaisClass> redesSociaisClass = crudClass.listarRedesSociais();
            RedesSociaisClass redesSociaisClassAtualizar = new RedesSociaisClass();

            // verificarData(redesSociaisClass);

            switch (packageName) {
                case "com.facebook.katana":
                case "com.facebook.lite": {
                    redesSociaisClassAtualizar.setId(1);
                    redesSociaisClassAtualizar.setHoje(redesSociaisClass.get(0).getHoje() + intervalo);
                    redesSociaisClassAtualizar.setMes(redesSociaisClass.get(0).getMes() + intervalo);
                    redesSociaisClassAtualizar.setTotal(redesSociaisClass.get(0).getTotal() + intervalo);

                    if (redesSociaisClass.get(0).getNotificouHoje() == 0 && redesSociaisClass.get(0).getAlertaAtivo() == 1 && redesSociaisClass.get(0).getHoje() > (redesSociaisClass.get(0).getTempoAlerta() * 60)) {
                        ExibirNotificacao(2, getString(R.string.notificacao_alerta, getString(R.string.facebook)), getString(R.string.tempo_excedido));
                        redesSociaisClassAtualizar.setNotificouHoje(1);
                    }
                    break;
                }
                case "com.instagram.android": {
                    redesSociaisClassAtualizar.setId(2);
                    redesSociaisClassAtualizar.setHoje(redesSociaisClass.get(1).getHoje() + intervalo);
                    redesSociaisClassAtualizar.setMes(redesSociaisClass.get(1).getMes() + intervalo);
                    redesSociaisClassAtualizar.setTotal(redesSociaisClass.get(1).getTotal() + intervalo);

                    if (redesSociaisClass.get(1).getNotificouHoje() == 0 && redesSociaisClass.get(1).getAlertaAtivo() == 1 && redesSociaisClass.get(1).getHoje() > (redesSociaisClass.get(1).getTempoAlerta() * 60)) {
                        ExibirNotificacao(3, getString(R.string.notificacao_alerta, getString(R.string.instagram)), getString(R.string.tempo_excedido));
                        redesSociaisClassAtualizar.setNotificouHoje(1);
                    }
                    break;
                }
                case "com.linkedin.android":
                case "com.linkedin.android.lite": {
                    redesSociaisClassAtualizar.setId(3);
                    redesSociaisClassAtualizar.setHoje(redesSociaisClass.get(2).getHoje() + intervalo);
                    redesSociaisClassAtualizar.setMes(redesSociaisClass.get(2).getMes() + intervalo);
                    redesSociaisClassAtualizar.setTotal(redesSociaisClass.get(2).getTotal() + intervalo);

                    if (redesSociaisClass.get(2).getNotificouHoje() == 0 && redesSociaisClass.get(2).getAlertaAtivo() == 1 && redesSociaisClass.get(2).getHoje() > (redesSociaisClass.get(2).getTempoAlerta() * 60)) {
                        ExibirNotificacao(3, getString(R.string.notificacao_alerta, getString(R.string.linkedin)), getString(R.string.tempo_excedido));
                        redesSociaisClassAtualizar.setNotificouHoje(1);
                    }
                    break;
                }
                case "com.twitter.android":
                case "com.twitter.android.lite": {
                    redesSociaisClassAtualizar.setId(4);
                    redesSociaisClassAtualizar.setHoje(redesSociaisClass.get(3).getHoje() + intervalo);
                    redesSociaisClassAtualizar.setMes(redesSociaisClass.get(3).getMes() + intervalo);
                    redesSociaisClassAtualizar.setTotal(redesSociaisClass.get(3).getTotal() + intervalo);

                    if (redesSociaisClass.get(3).getNotificouHoje() == 0 && redesSociaisClass.get(3).getAlertaAtivo() == 1 && redesSociaisClass.get(3).getHoje() > (redesSociaisClass.get(3).getTempoAlerta() * 60)) {
                        ExibirNotificacao(4, getString(R.string.notificacao_alerta, getString(R.string.twitter)), getString(R.string.tempo_excedido));
                        redesSociaisClassAtualizar.setNotificouHoje(1);
                    }
                    break;
                }
            }

            crudClass.atualizarRedeSocialTempo(redesSociaisClassAtualizar);

        } catch (Exception erro) {
            new UtilidadesClass().enviarMensagemContato(getApplicationContext(), erro);
        }
    }

    private void verificarData(List<RedesSociaisClass> redesSociaisClass) {
        try {
            Calendar dia = Calendar.getInstance();

            if (!dia.toString().equals(redesSociaisClass.get(0).getDiaAtual())) {
                CrudClass crudClass = new CrudClass(getApplication());
                crudClass.atualizarDataHoje(dia.toString());
            }

        } catch (Exception erro) {
            new UtilidadesClass().enviarMensagemContato(getApplicationContext(), erro);

        }
    }
}