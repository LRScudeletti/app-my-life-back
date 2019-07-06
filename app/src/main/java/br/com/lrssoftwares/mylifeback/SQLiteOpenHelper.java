package br.com.lrssoftwares.mylifeback;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    //region [ VARIAVEIS ]
    private static final String nomeBase = "BaseDados_MyLifeBack";
    private static final int versaoBase = 1;
    //endregion

    SQLiteOpenHelper(Context contexto) {
        super(contexto, nomeBase, null, versaoBase);
    }

    //region [ EVENTOS ]
    @Override
    public void onCreate(SQLiteDatabase sdBaseDados) {
        // Criando tabela redes sociais
        sdBaseDados.execSQL("create table redessociais (id integer, nome text, diaAtual text, hoje int, mes int, total int, alertaAtivo int, tempoAlerta int, notificouHoje int);");

        // Inserindo as redes sociais
        sdBaseDados.execSQL("insert into redessociais values (1, 'Facebook', '', 0, 0, 0, 0, 0, 0);");
        sdBaseDados.execSQL("insert into redessociais values (2, 'Instagram', '', 0, 0, 0, 0, 0, 0);");
        sdBaseDados.execSQL("insert into redessociais values (3, 'Linkedin', '', 0, 0, 0, 0, 0, 0);");
        sdBaseDados.execSQL("insert into redessociais values (4, 'Twitter', '', 0, 0, 0, 0, 0, 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdBaseDados, int versaoAnterior, int versaoAtual) {

    }
    //endregion
}
