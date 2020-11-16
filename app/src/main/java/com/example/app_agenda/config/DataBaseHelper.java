package com.example.app_agenda.config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.app_agenda.model.Agenda;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String banco_dados = "Contatos";
    private static int versao = 1;

    public DataBaseHelper(Context context){
        super(context, banco_dados, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // executa na primeira vez que o aplicativo é instalado
        db.execSQL("CREATE TABLE AGENDA (ID INTEGER PRIMARY KEY," +
                "NOME VARCHAR(30)," +
                "EMPRESA VARCHAR(30)," +
                "UF VARCHAR(2));" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//         executa toda a vez que é feito uma atualização no banco de dados
//        switch (oldVersion){
//            case 1:
//                db.execSQL("ALTER TABLE AGENDA ADD EMAIL VARCHAR(100);");
//            case 2:
//                db.execSQL("ALTER TABLE AGENDA RENAME EMAIL TO ENDEMAIL;");
//            case 3:
//                db.execSQL("CREATE TABLE ....");
//        }
    }

    public long addAgenda(Agenda a){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NOME", a.getNome());
        values.put("EMPRESA", a.getEmpresa());
        values.put("UF", a.getUf());

        // no sqllite primart key é sempre auto increment
        long id = db.insert("AGENDA", null, values);

        return id;
    }

    public Agenda getAgenda(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Agenda agenda = new Agenda();

        Cursor cursor =
                db.rawQuery("SELECT * FROM AGENDA WHERE ID = ?", new String[]{String.valueOf(id)});

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            agenda.setId(cursor.getInt(0));
            agenda.setNome(cursor.getString(1));
            agenda.setEmpresa(cursor.getString(2));
            agenda.setUf(cursor.getString(3));
        }
        else {
            agenda.setId(0);
            agenda.setNome("");
            agenda.setEmpresa("");
            agenda.setUf("");
        }

        return agenda;
    }

    public long updateAgenda(Agenda a, int id_contato){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NOME", a.getNome());
        values.put("EMPRESA", a.getEmpresa());
        values.put("UF", a.getUf());

        long id = db.update("AGENDA", values, "ID = ?", new String[]{String.valueOf(id_contato)});

        return id;

    }

    public int deleteAgenda(){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete("AGENDA","1",null);

        return i;
    }
}
