package br.com.lrssoftwares.mylifeback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class SobreActivity extends AppCompatActivity {

    //region [ EVENTOS ]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setTitle(R.string.tela_sobre);
            }

            TextView txtSobre = findViewById(R.id.txtSobre);
            String nomeVersao = BuildConfig.VERSION_NAME;

            txtSobre.setText(getString(R.string.versão, nomeVersao));

            TextView txtPrivacidade = findViewById(R.id.txtPrivacidade);
            txtPrivacidade.setText(Html.fromHtml("<a href=\"http://academiamaniaapp.blogspot.com.br/2017/02/politicas-de-privacidade-do-aplicativo.html\">Políticas de Privacidade</a>"));
            txtPrivacidade.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception erro) {
            new UtilidadesClass().enviarMensagemContato(this, erro);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    //endregion
}
