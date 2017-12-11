package br.edu.usj.boaviagem.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.edu.usj.boaviagem.DAO.ViagemDAO;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "BancoViagem";
    private static int VERSAO = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +
                ViagemDAO.NOME_TABELA +"(_id INTEGER PRIMARY KEY, destino TEXT, dataChegada TEXT, " +
                "dataSaida TEXT, orcamento TEXT, quantidade INTEGER, tipoViagem TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    // DAO (Data Access Object)

    // CRUD (Create, Read, Update, Delete)
}
