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
        sdBaseDados.execSQL("create table redessociais (id integer, nome text, dia date, hoje int, mes int, total int, alertaAtivo int, tempoAlerta int, ultimoAlerta date);");

        // Inserindo as redes sociais
        sdBaseDados.execSQL("insert into redessociais values (1, 'Facebook', '2019/01/01', 0, 0, 0, 0, 0, '2019/01/01');");
        sdBaseDados.execSQL("insert into redessociais values (2, 'Instagram', '2019/01/01', 0, 0, 0, 0, 0, '2019/01/01');");
        sdBaseDados.execSQL("insert into redessociais values (3, 'Linkedin', '2019/01/01', 0, 0, 0, 0, 0, '2019/01/01');");
        sdBaseDados.execSQL("insert into redessociais values (4, 'Twitter', '2019/01/01', 0, 0, 0, 0, 0, '2019/01/01');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdBaseDados, int versaoAnterior, int versaoAtual) {

    }
    //endregion
}
