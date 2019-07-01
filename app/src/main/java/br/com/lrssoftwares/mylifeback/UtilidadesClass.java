package br.com.lrssoftwares.mylifeback;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class UtilidadesClass {

    boolean verificarConexao(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null;
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



