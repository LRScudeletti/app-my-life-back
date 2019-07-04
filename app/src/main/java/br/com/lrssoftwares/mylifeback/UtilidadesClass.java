package br.com.lrssoftwares.mylifeback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.TextView;

class UtilidadesClass {

    boolean verificarConexao(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null;
    }

    AlertDialog.Builder CabecalhoDialogo(Activity activity, String tituloDialogo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        TextView titulo = new TextView(activity);

        titulo.setText(tituloDialogo);
        titulo.setBackgroundColor(Color.parseColor("#0c69ac"));
        titulo.setPadding(20, 20, 20, 20);
        titulo.setGravity(Gravity.CENTER);
        titulo.setTextColor(Color.WHITE);
        titulo.setTextSize(18);

        builder.setCustomTitle(titulo);

        return builder;
    }

    void enviarMensagemContato(final Context context, final Exception erro) {
        new android.app.AlertDialog.Builder(context)
                .setTitle(R.string.mensagem_erro_titulo)
                .setMessage(R.string.mensagem_erro)
                .setPositiveButton(R.string.botao_ok, (dialog, which) -> {
                    Intent intent = new Intent(context, ContatoActivity.class);
                    intent.putExtra("erro", erro.getMessage());
                    context.startActivity(intent);
                })
                .setNegativeButton(R.string.botao_cancelar, null)
                .show();
    }
}



