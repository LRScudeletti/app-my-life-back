package br.com.lrssoftwares.mylifeback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.TaskStackBuilder;

public class SplashActivity extends Activity implements Runnable {
    //region [ EVENTOS ]
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(this, 1000);
    }
    //endregion

    //region [ METODOS ]
    public void run() {
        SharedPreferences pegarIntro = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        // String intro = pegarIntro.getString("intro", "0");

        String intro = "2";

        if (intro.equals("1")) {
            startActivity(new Intent(this, PrincipalActivity.class));
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            finish();

        } else {
            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(new Intent(this, PrincipalActivity.class))
                    // .addNextIntent(new Intent(this, AjudaActivity.class))
                    .startActivities();
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            finish();
        }
    }
    //endregion
}