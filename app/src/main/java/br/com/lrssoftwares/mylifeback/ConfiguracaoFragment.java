package br.com.lrssoftwares.mylifeback;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import static com.rvalerio.fgchecker.Utils.hasUsageStatsPermission;

public class ConfiguracaoFragment extends Fragment {

    private boolean controlarOnResume = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_configuracao, container, false);

        // Botão que inicia o serviço de monitoramento
        Button btnSalvar = rootView.findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(v -> {
            if (!hasUsageStatsPermission(Objects.requireNonNull(getActivity()))) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(getString(R.string.habilitar_permissao))
                        .setPositiveButton(R.string.botao_ok, (dialog, id) -> {
                            controlarOnResume = true;
                            verificarPermissao();
                        })
                        .setNegativeButton(R.string.botao_cancelar, (dialog, id) -> {

                        });

                builder.create();
                builder.show();
            } else if (hasUsageStatsPermission(Objects.requireNonNull(getActivity()))) {
                chamarServico();
            }
        });

        return rootView;
    }

    public void onResume() {
        super.onResume();
        if (hasUsageStatsPermission(Objects.requireNonNull(getActivity())) && controlarOnResume) {
            controlarOnResume = false;
            chamarServico();
        } else if (!hasUsageStatsPermission(Objects.requireNonNull(getActivity())) && controlarOnResume) {
            Snackbar.make(Objects.requireNonNull(getView()), getString(R.string.permissao_cancelada), Snackbar.LENGTH_LONG).setAction("", null).show();
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
}
