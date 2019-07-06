package br.com.lrssoftwares.mylifeback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    TextView txtHojeFacebook;
    TextView txtMesFacebook;
    TextView txtTotalFacebook;

    TextView txtHojeInstagram;
    TextView txtMesInstagram;
    TextView txtTotalInstagram;

    TextView txtHojeLinkedin;
    TextView txtMesLinkedin;
    TextView txtTotalLinkedin;

    TextView txtHojeTwitter;
    TextView txtMesTwitter;
    TextView txtTotalTwitter;

    TextView txtTotalHoje;
    TextView txtTotalMes;
    TextView txtTotalGeral;

    boolean controlarOnResume = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.tela_historico);
        }

        txtHojeFacebook = findViewById(R.id.txtHojeFacebook);
        txtMesFacebook = findViewById(R.id.txtMesFacebook);
        txtTotalFacebook = findViewById(R.id.txtTotalFacebook);

        txtHojeInstagram = findViewById(R.id.txtHojeInstagram);
        txtMesInstagram = findViewById(R.id.txtMesInstagram);
        txtTotalInstagram = findViewById(R.id.txtTotalInstagram);

        txtHojeLinkedin = findViewById(R.id.txtHojeLinkedin);
        txtMesLinkedin = findViewById(R.id.txtMesLinkedin);
        txtTotalLinkedin = findViewById(R.id.txtTotalLinkedin);

        txtHojeTwitter = findViewById(R.id.txtHojeTwitter);
        txtMesTwitter = findViewById(R.id.txtMesTwitter);
        txtTotalTwitter = findViewById(R.id.txtTotalTwitter);

        txtTotalHoje = findViewById(R.id.txtTotalHoje);
        txtTotalMes = findViewById(R.id.txtTotalMes);
        txtTotalGeral = findViewById(R.id.txtTotalGeral);

        carregarDados();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void carregarDados() {
        try {
            CrudClass crudClass = new CrudClass(this);

            List<RedesSociaisClass> redesSociaisClass = crudClass.listarRedesSociais();

            int totalHoje = 0;
            int totalMes = 0;
            int totalGeral = 0;

            for (int i = 0; i < redesSociaisClass.size(); i++) {
                switch (redesSociaisClass.get(i).getId()) {
                    case 1:
                        txtHojeFacebook.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getHoje() / 3600), String.valueOf((redesSociaisClass.get(i).getHoje() % 3600) / 60)));
                        txtMesFacebook.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getMes() / 3600), String.valueOf((redesSociaisClass.get(i).getMes() % 3600) / 60)));
                        txtTotalFacebook.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getTotal() / 3600), String.valueOf((redesSociaisClass.get(i).getTotal() % 3600) / 60)));
                        totalHoje = totalHoje + redesSociaisClass.get(i).getHoje();
                        totalMes = totalMes + redesSociaisClass.get(i).getMes();
                        totalGeral = totalGeral + redesSociaisClass.get(i).getTotal();
                        break;

                    case 2:
                        txtHojeInstagram.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getHoje() / 3600), String.valueOf((redesSociaisClass.get(i).getHoje() % 3600) / 60)));
                        txtMesInstagram.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getMes() / 3600), String.valueOf((redesSociaisClass.get(i).getMes() % 3600) / 60)));
                        txtTotalInstagram.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getTotal() / 3600), String.valueOf((redesSociaisClass.get(i).getTotal() % 3600) / 60)));
                        totalHoje = totalHoje + redesSociaisClass.get(i).getHoje();
                        totalMes = totalMes + redesSociaisClass.get(i).getMes();
                        totalGeral = totalGeral + redesSociaisClass.get(i).getTotal();
                        break;

                    case 3:
                        txtHojeLinkedin.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getHoje() / 3600), String.valueOf((redesSociaisClass.get(i).getHoje() % 3600) / 60)));
                        txtMesLinkedin.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getMes() / 3600), String.valueOf((redesSociaisClass.get(i).getMes() % 3600) / 60)));
                        txtTotalLinkedin.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getTotal() / 3600), String.valueOf((redesSociaisClass.get(i).getTotal() % 3600) / 60)));
                        totalHoje = totalHoje + redesSociaisClass.get(i).getHoje();
                        totalMes = totalMes + redesSociaisClass.get(i).getMes();
                        totalGeral = totalGeral + redesSociaisClass.get(i).getTotal();
                        break;

                    case 4:
                        txtHojeTwitter.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getHoje() / 3600), String.valueOf((redesSociaisClass.get(i).getHoje() % 3600) / 60)));
                        txtMesTwitter.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getMes() / 3600), String.valueOf((redesSociaisClass.get(i).getMes() % 3600) / 60)));
                        txtTotalTwitter.setText(getString(R.string.resultado, String.valueOf(redesSociaisClass.get(i).getTotal() / 3600), String.valueOf((redesSociaisClass.get(i).getTotal() % 3600) / 60)));
                        totalHoje = totalHoje + redesSociaisClass.get(i).getHoje();
                        totalMes = totalMes + redesSociaisClass.get(i).getMes();
                        totalGeral = totalGeral + redesSociaisClass.get(i).getTotal();
                        break;
                }
            }

            txtTotalHoje.setText(getString(R.string.resultado, String.valueOf(totalHoje / 3600), String.valueOf((totalHoje % 3600) / 60)));
            txtTotalMes.setText(getString(R.string.resultado, String.valueOf(totalMes / 3600), String.valueOf((totalMes % 3600) / 60)));
            txtTotalGeral.setText(getString(R.string.resultado, String.valueOf(totalGeral / 3600), String.valueOf((totalGeral % 3600) / 60)));

        } catch (Exception erro) {
            new UtilidadesClass().enviarMensagemContato(this, erro);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (controlarOnResume) {
            controlarOnResume = false;
            carregarDados();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        controlarOnResume = true;
    }
}
