package br.com.lrssoftwares.mylifeback;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

class CrudClass {
    private SQLiteDatabase sqLiteDatabase;
    private final SQLiteOpenHelper baseDadosSQLiteOpenHelper;

    CrudClass(Context contexto) {
        baseDadosSQLiteOpenHelper = new SQLiteOpenHelper(contexto);
    }

    // Atualizar dados da tabela redessociais
    void atualizarRedeSocial(RedesSociaisClass redesSociaisClass) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("tempo", redesSociaisClass.getTempo());
        valores.put("ativo", redesSociaisClass.getAtivo());

        sqLiteDatabase.update("redessociais", valores, "_id = ?", new String[]{"" + redesSociaisClass.getId()});
        sqLiteDatabase.close();
    }

    // Listar dados da tabela redessociais
    List<RedesSociaisClass> listarRedesSociais(int ordenacao) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        List<RedesSociaisClass> lista = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query("redessociais", null, null, null, null, null, "nome COLLATE NOCASE ASC;");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                RedesSociaisClass redesSociaisClass = new RedesSociaisClass();
                redesSociaisClass.setId(cursor.getInt(0));
                redesSociaisClass.setProcesso(cursor.getString(2));
                redesSociaisClass.setTempo(cursor.getDouble(3));
                redesSociaisClass.setAtivo(cursor.getInt(4));
                lista.add(redesSociaisClass);

            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return lista;
    }
    //endregion
}
