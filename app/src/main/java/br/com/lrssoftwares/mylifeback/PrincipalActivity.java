package br.com.lrssoftwares.mylifeback;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbarPrincipal = findViewById(R.id.tPrincipal);
        setSupportActionBar(toolbarPrincipal);

        DrawerLayout drawerPrincipal = findViewById(R.id.drawer_layout);
        NavigationView navigationViewPrincipal = findViewById(R.id.nav_view);

        ActionBarDrawerToggle togglePrincipal = new ActionBarDrawerToggle(this, drawerPrincipal, toolbarPrincipal, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerPrincipal.addDrawerListener(togglePrincipal);
        togglePrincipal.syncState();
        navigationViewPrincipal.setNavigationItemSelectedListener(this);

        carregarTabs();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemCopiarConfiguracoes) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemCompartilhar) {
            String appPackageName = getPackageName();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String descricao = "\n" + getString(R.string.recomendo_aplicativo) + "\n";
            descricao += "https://play.google.com/store/apps/details?id=" + appPackageName + "\n\n";
            intent.putExtra(Intent.EXTRA_TEXT, descricao);
            startActivity(Intent.createChooser(intent, getString(R.string.selecione_opcao_compartilhar)));
        } else if (id == R.id.itemAvaliar) {
            final String appPackageName = getPackageName();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        } else if (id == R.id.itemContato) {
            Intent intentContato = new Intent(PrincipalActivity.this, ContatoActivity.class);
            startActivity(intentContato);
        } else if (id == R.id.itemSobre) {
            Intent intentSobre = new Intent(PrincipalActivity.this, SobreActivity.class);
            startActivity(intentSobre);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void carregarTabs() {
        ViewPager viewPagerPrincipal = findViewById(R.id.vpPrincipal);
        ViewPagerAdapter vpAdapterPrincipal = new ViewPagerAdapter(getSupportFragmentManager());

        // DOMINGO
        ConfiguracaoFragment domingoFragmento = new ConfiguracaoFragment();
        Bundle argsDomingo = new Bundle();
        argsDomingo.putInt("posicao", 1);
        domingoFragmento.setArguments(argsDomingo);
        vpAdapterPrincipal.addFragment(domingoFragmento, getString(R.string.domingo));

        // SEGUNDA
        ConfiguracaoFragment segundaFragmento = new ConfiguracaoFragment();
        Bundle argsSegunda = new Bundle();
        argsSegunda.putInt("posicao", 2);
        segundaFragmento.setArguments(argsSegunda);
        vpAdapterPrincipal.addFragment(segundaFragmento, getString(R.string.segunda));

        // TERÇA
        ConfiguracaoFragment tercaFragmento = new ConfiguracaoFragment();
        Bundle argsTerca = new Bundle();
        argsTerca.putInt("posicao", 3);
        tercaFragmento.setArguments(argsTerca);
        vpAdapterPrincipal.addFragment(tercaFragmento, getString(R.string.terca));

        // QUARTA
        ConfiguracaoFragment quartaFragmento = new ConfiguracaoFragment();
        Bundle argsQuarta = new Bundle();
        argsQuarta.putInt("posicao", 4);
        quartaFragmento.setArguments(argsQuarta);
        vpAdapterPrincipal.addFragment(quartaFragmento, getString(R.string.quarta));

        // QUINTA
        ConfiguracaoFragment quintaFragmento = new ConfiguracaoFragment();
        Bundle argsQuinta = new Bundle();
        argsQuinta.putInt("posicao", 5);
        quintaFragmento.setArguments(argsQuinta);
        vpAdapterPrincipal.addFragment(quintaFragmento, getString(R.string.quinta));

        // SEXTA
        ConfiguracaoFragment sextaFragmento = new ConfiguracaoFragment();
        Bundle argsSexta = new Bundle();
        argsSexta.putInt("posicao", 6);
        sextaFragmento.setArguments(argsSexta);
        vpAdapterPrincipal.addFragment(sextaFragmento, getString(R.string.sexta));

        // SABADO
        ConfiguracaoFragment sabadoFragmento = new ConfiguracaoFragment();
        Bundle argsSabado = new Bundle();
        argsSabado.putInt("posicao", 7);
        sabadoFragmento.setArguments(argsSabado);
        vpAdapterPrincipal.addFragment(sabadoFragmento, getString(R.string.sabado));

        TabLayout tabLayoutPrincipal = findViewById(R.id.tlPrincipal);
        tabLayoutPrincipal.setupWithViewPager(viewPagerPrincipal);

        viewPagerPrincipal.setAdapter(vpAdapterPrincipal);
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

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }
}
