package br.com.lrssoftwares.mylifeback;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracoesTabsActivity extends AppCompatActivity {
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

        carregarTabs();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void carregarTabs() {
        ViewPager viewPagerPrincipal = findViewById(R.id.vpPrincipal);
        ViewPagerAdapter vpAdapterPrincipal = new ViewPagerAdapter(getSupportFragmentManager());

        // DOMINGO
        ConfiguracoesFragment domingoFragmento = new ConfiguracoesFragment();
        Bundle argsDomingo = new Bundle();
        argsDomingo.putInt("dia", 1);
        domingoFragmento.setArguments(argsDomingo);
        vpAdapterPrincipal.addFragment(domingoFragmento, getString(R.string.domingo));

        // SEGUNDA
        ConfiguracoesFragment segundaFragmento = new ConfiguracoesFragment();
        Bundle argsSegunda = new Bundle();
        argsSegunda.putInt("dia", 2);
        segundaFragmento.setArguments(argsSegunda);
        vpAdapterPrincipal.addFragment(segundaFragmento, getString(R.string.segunda));

        // TERÇA
        ConfiguracoesFragment tercaFragmento = new ConfiguracoesFragment();
        Bundle argsTerca = new Bundle();
        argsTerca.putInt("dia", 3);
        tercaFragmento.setArguments(argsTerca);
        vpAdapterPrincipal.addFragment(tercaFragmento, getString(R.string.terca));

        // QUARTA
        ConfiguracoesFragment quartaFragmento = new ConfiguracoesFragment();
        Bundle argsQuarta = new Bundle();
        argsQuarta.putInt("dia", 4);
        quartaFragmento.setArguments(argsQuarta);
        vpAdapterPrincipal.addFragment(quartaFragmento, getString(R.string.quarta));

        // QUINTA
        ConfiguracoesFragment quintaFragmento = new ConfiguracoesFragment();
        Bundle argsQuinta = new Bundle();
        argsQuinta.putInt("dia", 5);
        quintaFragmento.setArguments(argsQuinta);
        vpAdapterPrincipal.addFragment(quintaFragmento, getString(R.string.quinta));

        // SEXTA
        ConfiguracoesFragment sextaFragmento = new ConfiguracoesFragment();
        Bundle argsSexta = new Bundle();
        argsSexta.putInt("dia", 6);
        sextaFragmento.setArguments(argsSexta);
        vpAdapterPrincipal.addFragment(sextaFragmento, getString(R.string.sexta));

        // SABADO
        ConfiguracoesFragment sabadoFragmento = new ConfiguracoesFragment();
        Bundle argsSabado = new Bundle();
        argsSabado.putInt("dia", 7);
        sabadoFragmento.setArguments(argsSabado);
        vpAdapterPrincipal.addFragment(sabadoFragmento, getString(R.string.sabado));

        viewPagerPrincipal.setAdapter(vpAdapterPrincipal);

        TabLayout tabLayoutPrincipal = findViewById(R.id.tlPrincipal);
        tabLayoutPrincipal.setupWithViewPager(viewPagerPrincipal);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> listaFragmentos = new ArrayList<>();
        private final List<String> listaTitulos = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return listaFragmentos.get(position);
        }

        @Override
        public int getItemPosition(@NonNull Object objeto) {
            if (objeto instanceof ConfiguracoesFragment) {
                ((ConfiguracoesFragment)objeto).carregarDados();
            }
            return super.getItemPosition(objeto);
        }

        @Override
        public int getCount() {
            return listaFragmentos.size();
        }

        void addFragment(Fragment fragment, String title) {
            listaFragmentos.add(fragment);
            listaTitulos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listaTitulos.get(position);
        }
    }
}
