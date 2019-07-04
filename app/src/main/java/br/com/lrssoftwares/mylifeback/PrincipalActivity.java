package br.com.lrssoftwares.mylifeback;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbarPrincipal = findViewById(R.id.tPrincipal);
        toolbarPrincipal.setTitle(R.string.app_name);

        DrawerLayout drawerPrincipal = findViewById(R.id.drawer_layout);
        NavigationView navigationViewPrincipal = findViewById(R.id.nav_view);

        ActionBarDrawerToggle togglePrincipal = new ActionBarDrawerToggle(this, drawerPrincipal, toolbarPrincipal, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerPrincipal.addDrawerListener(togglePrincipal);
        togglePrincipal.syncState();
        navigationViewPrincipal.setNavigationItemSelectedListener(this);

        CardView cvConfiguracoes = findViewById(R.id.cvConfiguracoes);
        cvConfiguracoes.setOnClickListener(view -> {
            Intent intentMonitoramento = new Intent(PrincipalActivity.this, ConfiguracoesTabsActivity.class);
            startActivity(intentMonitoramento);
        });

        CardView cvVisualizarHistorico = findViewById(R.id.cvVisualizarHistorico);
        cvVisualizarHistorico.setOnClickListener(view -> {
            Intent intentHistorico = new Intent(PrincipalActivity.this, HistoricoActivity.class);
            startActivity(intentHistorico);
        });

        FloatingActionButton fabContato = findViewById(R.id.fabContato);
        fabContato.setOnClickListener(view -> {
            Intent intentContato = new Intent(PrincipalActivity.this, ContatoActivity.class);
            startActivity(intentContato);
        });
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
        } else if (id == R.id.itemSobre) {
            Intent intentSobre = new Intent(this, SobreActivity.class);
            startActivity(intentSobre);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
