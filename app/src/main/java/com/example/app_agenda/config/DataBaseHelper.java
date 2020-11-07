package com.example.app_agenda.config;

import android.content.ContentValues;
import android.content.Context;
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
}
