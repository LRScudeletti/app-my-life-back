import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class CrudClass {
    private SQLiteDatabase sqLiteDatabase;
    private final SQLiteOpenHelper baseDadosSQLiteOpenHelper;

    CrudClass(Context contexto) {
        baseDadosSQLiteOpenHelper = new SQLiteOpenHelper(contexto);
    }

    // Listar dados da tabela redessociais
    List<RedesSociaisClass> listarRedesSociais(int dia) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        List<RedesSociaisClass> lista = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query("redessociais", null, "dia = ?", new String[]{"" + dia}, null, null, "id COLLATE NOCASE ASC;");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                RedesSociaisClass redesSociaisClass = new RedesSociaisClass();
                redesSociaisClass.setId(cursor.getInt(0));
                // redesSociaisClass.setNome(cursor.getString(1));
                redesSociaisClass.setDia(cursor.getInt(2));
                redesSociaisClass.setTempo(cursor.getString(3));
                redesSociaisClass.setAtivo(cursor.getInt(4));
                lista.add(redesSociaisClass);

            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return lista;
    }
    //endregion

    // Atualizar dados da tabela redessociais
    void atualizarRedeSocial(RedesSociaisClass redesSociaisClass) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("tempo", redesSociaisClass.getTempo());
        valores.put("ativo", redesSociaisClass.getAtivo());

        sqLiteDatabase.update("redessociais", valores, "id = ? and dia = ?", new String[]{"" + redesSociaisClass.getId() , "" + redesSociaisClass.getDia()});
        sqLiteDatabase.close();
    }
}
