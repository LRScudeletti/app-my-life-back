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
                // redesSociaisClass.setNome(cursor.getString(1));
                redesSociaisClass.setDiaAtual(cursor.getInt(2));
                redesSociaisClass.setMesAtual(cursor.getInt(3));
                redesSociaisClass.setHoje(cursor.getInt(4));
                redesSociaisClass.setMes(cursor.getInt(5));
                redesSociaisClass.setTotal(cursor.getInt(6));
                redesSociaisClass.setAlertaAtivo(cursor.getInt(7));
                redesSociaisClass.setTempoAlerta(cursor.getInt(8));
                redesSociaisClass.setNotificouHoje(cursor.getInt(9));
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
        valores.put("id", redesSociaisClass.getId());
        valores.put("alertaAtivo", redesSociaisClass.getAlertaAtivo());
        valores.put("tempoAlerta", redesSociaisClass.getTempoAlerta());

        sqLiteDatabase.update("redessociais", valores, "id = ?", new String[]{"" + redesSociaisClass.getId()});
        sqLiteDatabase.close();
    }

    void atualizarRedeSocialTempo(RedesSociaisClass redesSociaisClass) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("id", redesSociaisClass.getId());
        valores.put("hoje", redesSociaisClass.getHoje());
        valores.put("mes", redesSociaisClass.getMes());
        valores.put("total", redesSociaisClass.getTotal());

        if (redesSociaisClass.getNotificouHoje() == 1)
            valores.put("notificouHoje", redesSociaisClass.getNotificouHoje());

        sqLiteDatabase.update("redessociais", valores, "id = ?", new String[]{"" + redesSociaisClass.getId()});
        sqLiteDatabase.close();
    }

    void atualizarDiaAtual(int diaAtual) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("hoje", 0);
        valores.put("diaAtual", diaAtual);
        valores.put("notificouHoje", 0);

        sqLiteDatabase.update("redessociais", valores, null, null);
        sqLiteDatabase.close();
    }

    void atualizarMesAtual(int mesAtual) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("mes", 0);
        valores.put("mesAtual", mesAtual);

        sqLiteDatabase.update("redessociais", valores, null, null);
        sqLiteDatabase.close();
    }
}
