package br.com.lrssoftwares.mylifeback;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    //region [ VARIAVEIS ]
    private static final String nomeBase = "BaseDados_MyLifeBack";
    private static final int versaoBase = 3;
    //endregion

    SQLiteOpenHelper(Context contexto) {
        super(contexto, nomeBase, null, versaoBase);
    }

    //region [ EVENTOS ]
    @Override
    public void onCreate(SQLiteDatabase sdBaseDados) {
        // Criando tabela redes sociais
        sdBaseDados.execSQL("create table redessociais (id integer, nome text not null, dia int not null, tempo text, ativo int not null);");

        // Inserindo as redes sociais
        // Domingo
        sdBaseDados.execSQL("insert into redessociais values (1, 'Facebook', 1, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (2, 'Instagram', 1, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (3, 'Linkedin', 1, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (4, 'Twitter', 1, '', 0);");

        // Segunda
        sdBaseDados.execSQL("insert into redessociais values (1, 'Facebook', 2, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (2, 'Instagram', 2, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (3, 'Linkedin', 2, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (4, 'Twitter', 2, '', 0);");

        // Terça
        sdBaseDados.execSQL("insert into redessociais values (1, 'Facebook', 3, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (2, 'Instagram', 3, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (3, 'Linkedin', 3, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (4, 'Twitter', 3, '', 0);");

        // Quarta
        sdBaseDados.execSQL("insert into redessociais values (1, 'Facebook', 4, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (2, 'Instagram', 4, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (3, 'Linkedin', 4, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (4, 'Twitter', 4, '', 0);");

        // Quinta
        sdBaseDados.execSQL("insert into redessociais values (1, 'Facebook', 5, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (2, 'Instagram', 5, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (3, 'Linkedin', 5, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (4, 'Twitter', 5, '', 0);");

        // Sexta
        sdBaseDados.execSQL("insert into redessociais values (1, 'Facebook', 6, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (2, 'Instagram', 6, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (3, 'Linkedin', 6, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (4, 'Twitter', 6, '', 0);");

        // Sábado
        sdBaseDados.execSQL("insert into redessociais values (1, 'Facebook', 7, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (2, 'Instagram', 7, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (3, 'Linkedin', 7, '', 0);");
        sdBaseDados.execSQL("insert into redessociais values (24, 'Twitter', 7, '', 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdBaseDados, int versaoAnterior, int versaoAtual) {

    }
    //endregion
}
