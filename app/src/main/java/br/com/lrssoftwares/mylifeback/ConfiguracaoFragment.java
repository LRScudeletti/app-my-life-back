package br.com.lrssoftwares.mylifeback;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

public class ConfiguracaoFragment extends Fragment {

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
            Intent startIntent = new Intent(getActivity(), MonitorService.class);
            startIntent.setAction("Monitor");
            Objects.requireNonNull(getActivity()).startService(startIntent);
        });

        return rootView;
    }
}
