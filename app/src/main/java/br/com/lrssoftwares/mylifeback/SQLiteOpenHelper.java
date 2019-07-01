package br.com.lrssoftwares.mylifeback;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    //region [ VARIAVEIS ]
    private static final String nomeBase = "BaseDados_MyLifeBack";
    private static final int versaoBase = 2;
    //endregion

    SQLiteOpenHelper(Context contexto) {
        super(contexto, nomeBase, null, versaoBase);
    }

    //region [ EVENTOS ]
    @Override
    public void onCreate(SQLiteDatabase sdBaseDados) {
        // Criando tabela redes sociais
        sdBaseDados.execSQL("create table redessociais (_id integer primary key autoincrement, nome text not null, processo text, tempo int not null, ativo int not null);");

        // Inserindo as redes sociais
        sdBaseDados.execSQL("insert into redessociais values (1, 'Facebook', '', 60, 1);");
        sdBaseDados.execSQL("insert into redessociais values (2, 'Instagram', '', 60, 1);");
        sdBaseDados.execSQL("insert into redessociais values (3, 'Linkedin', '', 60, 1);");
        sdBaseDados.execSQL("insert into redessociais values (4, 'Twitter', '', 60, 1);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdBaseDados, int versaoAnterior, int versaoAtual) {

    }
    //endregion
}
