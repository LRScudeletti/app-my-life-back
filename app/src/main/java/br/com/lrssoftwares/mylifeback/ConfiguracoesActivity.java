package br.com.lrssoftwares.mylifeback;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import static com.rvalerio.fgchecker.Utils.hasUsageStatsPermission;

public class ConfiguracoesActivity extends AppCompatActivity {

    private CrudClass crudClass;
    private boolean controlarOnResume = false;

    Switch swtFacebook;
    Switch swtInstagram;
    Switch swtLinkedin;
    Switch swtTwitter;

    EditText txtAlertaFacebook;
    EditText txtAlertaInstagram;
    EditText txtAlertaLinkedin;
    EditText txtAlertaTwitter;

    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.tela_configuracoes);
            getSupportActionBar().setElevation(0);
        }

        crudClass = new CrudClass(this);

        swtFacebook = findViewById(R.id.swtFacebook);
        swtInstagram = findViewById(R.id.swtInstagram);
        swtLinkedin = findViewById(R.id.swtLinkedin);
        swtTwitter = findViewById(R.id.swtTwitter);

        txtAlertaFacebook = findViewById(R.id.txtAlertaFacebook);
        txtAlertaInstagram = findViewById(R.id.txtAlertaInstagram);
        txtAlertaLinkedin = findViewById(R.id.txtAlertaLinkedin);
        txtAlertaTwitter = findViewById(R.id.txtAlertaTwitter);

        // Botão que inicia o serviço de monitoramento
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setText(MonitorService.servicoExecutando ? getText(R.string.botao_parar) : getText(R.string.botao_iniciar));
        btnSalvar.setOnClickListener(v -> {
            if (!MonitorService.servicoExecutando) {
                if (!hasUsageStatsPermission(this)) {
                    AlertDialog.Builder dialogPermissao = new AlertDialog.Builder(this);
                    TextView mensagem = new TextView(this);
                    mensagem.setText(getString(R.string.habilitar_permissao));
                    mensagem.setGravity(Gravity.CENTER_HORIZONTAL);
                    mensagem.setTextSize(14);
                    mensagem.setPadding(50, 40, 50, 5);
                    mensagem.setTextColor(getResources().getColor(R.color.colorBlack));
                    dialogPermissao.setView(mensagem)
                            .setPositiveButton(R.string.botao_ok, (dialog, id) -> {
                                controlarOnResume = true;
                                verificarPermissao();
                            })
                            .setNegativeButton(R.string.botao_cancelar, (dialog, id) -> {

                            });

                    dialogPermissao.create();
                    dialogPermissao.show();
                } else if (hasUsageStatsPermission(this)) {
                    atualizarDados();
                    btnSalvar.setText(getText(R.string.botao_parar));
                    Snackbar.make(findViewById(android.R.id.content), getString(R.string.monitoramento_iniciado), Snackbar.LENGTH_LONG).setAction("", null).show();
                    chamarServico();
                }
            } else {
                btnSalvar.setText(getText(R.string.botao_iniciar));
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.monitoramento_parado), Snackbar.LENGTH_LONG).setAction("", null).show();

                MonitorService.pararAlarme();

                Intent service = new Intent(this, MonitorService.class);
                this.stopService(service);
            }
        });

        carregarDados();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onResume() {
        super.onResume();
        if (hasUsageStatsPermission(this) && controlarOnResume) {
            controlarOnResume = false;

            atualizarDados();
            btnSalvar.setText(getText(R.string.botao_parar));
            Snackbar.make(findViewById(android.R.id.content), getString(R.string.monitoramento_iniciado), Snackbar.LENGTH_LONG).setAction("", null).show();
            chamarServico();
        } else if (!hasUsageStatsPermission(this) && controlarOnResume) {
            Snackbar.make(findViewById(android.R.id.content), getString(R.string.permissao_cancelada), Snackbar.LENGTH_LONG).setAction("", null).show();
        }
    }

    public void carregarDados() {
        try {
            List<RedesSociaisClass> listarRedesSociais = crudClass.listarRedesSociais();

            swtFacebook.setChecked(listarRedesSociais.get(0).getAlertaAtivo() != 0);
            swtInstagram.setChecked(listarRedesSociais.get(1).getAlertaAtivo() != 0);
            swtLinkedin.setChecked(listarRedesSociais.get(2).getAlertaAtivo() != 0);
            swtTwitter.setChecked(listarRedesSociais.get(3).getAlertaAtivo() != 0);

            txtAlertaFacebook.setText(listarRedesSociais.get(0).getTempoAlerta() == 0 ? "" : String.valueOf(listarRedesSociais.get(0).getTempoAlerta()));
            txtAlertaInstagram.setText(listarRedesSociais.get(1).getTempoAlerta() == 0 ? "" : String.valueOf(listarRedesSociais.get(1).getTempoAlerta()));
            txtAlertaLinkedin.setText(listarRedesSociais.get(2).getTempoAlerta() == 0 ? "" : String.valueOf(listarRedesSociais.get(2).getTempoAlerta()));
            txtAlertaTwitter.setText(listarRedesSociais.get(3).getTempoAlerta() == 0 ? "" : String.valueOf(listarRedesSociais.get(3).getTempoAlerta()));

        } catch (Exception erro) {
            new UtilidadesClass().enviarMensagemContato(this, erro);
        }
    }

    private void atualizarDados() {
        try {
            RedesSociaisClass redesSociaisClass = new RedesSociaisClass();

            for (int i = 1; i <= 4; i++) {
                switch (i) {
                    case 1:
                        redesSociaisClass.setId(1);
                        redesSociaisClass.setTempoAlerta(txtAlertaFacebook.getText().toString().equals("") ? 0 : Integer.parseInt(txtAlertaFacebook.getText().toString()));
                        redesSociaisClass.setAlertaAtivo(swtFacebook.isChecked() ? 1 : 0);
                        break;
                    case 2:
                        redesSociaisClass.setId(2);
                        redesSociaisClass.setTempoAlerta(txtAlertaInstagram.getText().toString().equals("") ? 0 : Integer.parseInt(txtAlertaInstagram.getText().toString()));
                        redesSociaisClass.setAlertaAtivo(swtInstagram.isChecked() ? 1 : 0);
                        break;
                    case 3:
                        redesSociaisClass.setId(3);
                        redesSociaisClass.setTempoAlerta(txtAlertaLinkedin.getText().toString().equals("") ? 0 : Integer.parseInt(txtAlertaLinkedin.getText().toString()));
                        redesSociaisClass.setAlertaAtivo(swtLinkedin.isChecked() ? 1 : 0);
                        break;
                    case 4:
                        redesSociaisClass.setId(4);
                        redesSociaisClass.setTempoAlerta(txtAlertaTwitter.getText().toString().equals("") ? 0 : Integer.parseInt(txtAlertaTwitter.getText().toString()));
                        redesSociaisClass.setAlertaAtivo(swtTwitter.isChecked() ? 1 : 0);
                        break;
                }
                crudClass.atualizarRedeSocial(redesSociaisClass);
            }
        } catch (Exception erro) {
            new UtilidadesClass().enviarMensagemContato(this, erro);
        }
    }

    private void verificarPermissao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !hasUsageStatsPermission(this)) {
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }
    }

    private void chamarServico() {
        Intent startIntent = new Intent(this, MonitorService.class);
        startIntent.setAction("Monitor");
        startService(startIntent);
    }
}
