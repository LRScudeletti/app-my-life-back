package br.com.lrssoftwares.mylifeback;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import static com.rvalerio.fgchecker.Utils.hasUsageStatsPermission;

public class ConfiguracoesFragment extends Fragment {

    private CrudClass crudClass;
    int dia;
    private boolean controlarOnResume = false;

    Switch swtFacebook;
    Switch swtInstagram;
    Switch swtLinkedin;
    Switch swtTwitter;

    EditText txtAlertaFacebook;
    EditText txtAlertaInstagram;
    EditText txtAlertaLinkedin;
    EditText txtAlertaTwitter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        crudClass = new CrudClass(getActivity());
    }

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_monitoramento, container, false);

        dia = Objects.requireNonNull(getArguments()).getInt("dia", 1);

        swtFacebook = rootView.findViewById(R.id.swtFacebook);
        swtInstagram = rootView.findViewById(R.id.swtInstagram);
        swtLinkedin = rootView.findViewById(R.id.swtLinkedin);
        swtTwitter = rootView.findViewById(R.id.swtTwitter);

        txtAlertaFacebook = rootView.findViewById(R.id.txtAlertaFacebook);
        txtAlertaInstagram = rootView.findViewById(R.id.txtAlertaInstagram);
        txtAlertaLinkedin = rootView.findViewById(R.id.txtAlertaLinkedin);
        txtAlertaTwitter = rootView.findViewById(R.id.txtAlertaTwitter);

        carregarDados();

        // Botão que inicia o serviço de monitoramento
        Button btnSalvar = rootView.findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(v -> {
            if (!hasUsageStatsPermission(Objects.requireNonNull(getActivity()))) {
                AlertDialog.Builder dialogPermissao = new AlertDialog.Builder(getActivity());
                TextView mensagem = new TextView(getActivity());
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
            } else if (hasUsageStatsPermission(Objects.requireNonNull(getActivity()))) {
                atualizarDados();

                if (verificarChecks())
                    chamarServico();
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_monitoramento, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemCopiarConfiguracoes) {
            PopupCopiarConfiguracoes();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        if (hasUsageStatsPermission(Objects.requireNonNull(getActivity())) && controlarOnResume) {
            controlarOnResume = false;

            atualizarDados();

            if (verificarChecks())
                chamarServico();

        } else if (!hasUsageStatsPermission(Objects.requireNonNull(getActivity())) && controlarOnResume) {
            Snackbar.make(Objects.requireNonNull(getView()), getString(R.string.permissao_cancelada), Snackbar.LENGTH_LONG).setAction("", null).show();
        }
    }

    private void carregarDados() {
        try {
            List<RedesSociaisClass> listarRedesSociais = crudClass.listarRedesSociais(dia);

            swtFacebook.setChecked(listarRedesSociais.get(0).getAtivo() != 0);
            swtInstagram.setChecked(listarRedesSociais.get(1).getAtivo() != 0);
            swtLinkedin.setChecked(listarRedesSociais.get(2).getAtivo() != 0);
            swtTwitter.setChecked(listarRedesSociais.get(3).getAtivo() != 0);

            txtAlertaFacebook.setText(listarRedesSociais.get(0).getTempo());
            txtAlertaInstagram.setText(listarRedesSociais.get(1).getTempo());
            txtAlertaLinkedin.setText(listarRedesSociais.get(2).getTempo());
            txtAlertaTwitter.setText(listarRedesSociais.get(3).getTempo());

        } catch (Exception erro) {
            new UtilidadesClass().enviarMensagemContato(getActivity(), erro);
        }
    }

    private void atualizarDados() {
        try {
            RedesSociaisClass redesSociaisClass = new RedesSociaisClass();

            redesSociaisClass.setDia(dia);

            for (int i = 1; i <= 4; i++) {
                switch (i) {
                    case 1:
                        redesSociaisClass.setId(1);
                        redesSociaisClass.setTempo(txtAlertaFacebook.getText().toString());
                        redesSociaisClass.setAtivo(swtFacebook.isChecked() ? 1 : 0);
                        break;
                    case 2:
                        redesSociaisClass.setId(2);
                        redesSociaisClass.setTempo(txtAlertaInstagram.getText().toString());
                        redesSociaisClass.setAtivo(swtInstagram.isChecked() ? 1 : 0);
                        break;
                    case 3:
                        redesSociaisClass.setId(3);
                        redesSociaisClass.setTempo(txtAlertaLinkedin.getText().toString());
                        redesSociaisClass.setAtivo(swtLinkedin.isChecked() ? 1 : 0);
                        break;
                    case 4:
                        redesSociaisClass.setId(4);
                        redesSociaisClass.setTempo(txtAlertaTwitter.getText().toString());
                        redesSociaisClass.setAtivo(swtTwitter.isChecked() ? 1 : 0);
                        break;
                }
                crudClass.atualizarRedeSocial(redesSociaisClass);
            }

            Snackbar.make(Objects.requireNonNull(getView()), getString(R.string.configuracoes_sucesso), Snackbar.LENGTH_LONG).setAction("", null).show();

        } catch (Exception erro) {
            new UtilidadesClass().enviarMensagemContato(getActivity(), erro);
        }
    }

    private void verificarPermissao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !hasUsageStatsPermission(Objects.requireNonNull(getActivity()))) {
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }
    }

    private void chamarServico() {
        Intent startIntent = new Intent(getActivity(), MonitorService.class);
        startIntent.setAction("Monitor");
        Objects.requireNonNull(getActivity()).startService(startIntent);
    }

    private boolean verificarChecks() {
        return swtFacebook.isChecked() || swtInstagram.isChecked() || swtLinkedin.isChecked() || swtTwitter.isChecked();
    }

    private void PopupCopiarConfiguracoes() {
        try {
            AlertDialog.Builder dialogoCopiar = new UtilidadesClass().CabecalhoDialogo(getActivity(), getString(R.string.copiar_configuracoes_para));

            LayoutInflater inflaterCopiar = (LayoutInflater) Objects.requireNonNull(getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View vLayoutDialogo = inflaterCopiar.inflate(R.layout.dialog_copiar_configuracoes, null);

            dialogoCopiar.setView(vLayoutDialogo)
                    .setPositiveButton(getString(R.string.botao_ok), (dialog, id) -> {

                    })
                    .setNegativeButton(getString(R.string.botao_cancelar), (dialog, id) -> {

                    });

            dialogoCopiar.create();
            dialogoCopiar.show();

        } catch (Exception erro) {

        }
    }
}
