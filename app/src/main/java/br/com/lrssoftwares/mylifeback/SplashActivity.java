package br.com.lrssoftwares.mylifeback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
        TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(new Intent(this, PrincipalActivity.class))
                // .addNextIntent(new Intent(this, AjudaActivity.class))
                .startActivities();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
    //endregion
}