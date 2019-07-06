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

    List<RedesSociaisClass> listarRedesSociais() {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        List<RedesSociaisClass> lista = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query("redessociais", null, null, null, null, null, "id COLLATE NOCASE ASC;");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                RedesSociaisClass redesSociaisClass = new RedesSociaisClass();
                redesSociaisClass.setId(cursor.getInt(0));
                redesSociaisClass.setNome(cursor.getString(1));
                //redesSociaisClass.setDia(cursor.getDate(2));

                redesSociaisClass.setHoje(cursor.getInt(3));
                redesSociaisClass.setMes(cursor.getInt(4));
                redesSociaisClass.setTotal(cursor.getInt(5));
                redesSociaisClass.setAlertaAtivo(cursor.getInt(6));
                redesSociaisClass.setTempoAlerta(cursor.getInt(7));
                //redesSociaisClass.setUltimoAlerta(cursor.getInt(9));
                lista.add(redesSociaisClass);

            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return lista;
    }

    void atualizarRedeSocial(RedesSociaisClass redesSociaisClass) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("tempoAlerta", redesSociaisClass.getTempoAlerta());
        valores.put("alertaAtivo", redesSociaisClass.getAlertaAtivo());

        sqLiteDatabase.update("redessociais", valores, "id = ?", new String[]{"" + redesSociaisClass.getId()});
        sqLiteDatabase.close();
    }
}
