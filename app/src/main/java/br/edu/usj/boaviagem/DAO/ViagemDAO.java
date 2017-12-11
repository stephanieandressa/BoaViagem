package br.edu.usj.boaviagem.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.usj.boaviagem.entity.Viagem;
import br.edu.usj.boaviagem.util.DatabaseHelper;


public class ViagemDAO implements DAO<Viagem> {

    private DatabaseHelper helper;

    public static final String NOME_TABELA = "viagem";

    public ViagemDAO(Context contexto){
        helper = new DatabaseHelper(contexto);
    }

    @Override
    public Boolean salvar(Viagem p) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("tipoViagem", p.getTipoViagem());
        values.put("destino", p.getDestino());
        values.put("orcamento", p.getOrcamento());
        values.put("quantidade", p.getQuantidade());
        values.put("dataChegada", p.getDataChegada());
        values.put("dataSaida", p.getDataSaida());


        long inseriu = db.insert(NOME_TABELA, null,values);

        if(inseriu > 0){
            return true;
        }

        return false;
    }


    @Override
    public Boolean remover(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String idString = String.valueOf(id);
        int removeu = db.delete(NOME_TABELA, "_id = ?", new String[]{idString});

        if (removeu > 0){
            return true;
        }

        return false;
    }

    @Override
    public Boolean atualizar(Viagem p) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", p.getId());
        values.put("tipoViagem", p.getTipoViagem());
        values.put("destino", p.getDestino());
        values.put("orcamento", p.getOrcamento());
        values.put("quantidade", p.getQuantidade());
        values.put("dataChegada", p.getDataChegada());
        values.put("dataSaida", p.getDataSaida());


        String idString = String.valueOf(p.getId());

        int atualizou = db.update(NOME_TABELA, values, "_id = ?", new String[]{idString});

        if (atualizou > 0){
            return true;
        }

        return false;
    }

    @Override
    public List<Viagem> listar() {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT _id, tipoViagem, destino, orcamento, quantidade, dataChegada, dataSaida, destino  FROM "+ NOME_TABELA, null);

        cursor.moveToFirst();

        List<Viagem> viagens = new ArrayList<Viagem>();

        for(int i = 0; i < cursor.getCount(); i++){
            Viagem p = new Viagem();
            p.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            p.setTipoViagem(cursor.getString(cursor.getColumnIndex("tipoViagem")));
            p.setDestino(cursor.getString(cursor.getColumnIndex("destino")));
            p.setOrcamento(cursor.getString(cursor.getColumnIndex("orcamento")));
            p.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            p.setDataChegada(cursor.getString(cursor.getColumnIndex("dataChegada")));
            p.setDataSaida(cursor.getString(cursor.getColumnIndex("dataSaida")));



            viagens.add(p);
            cursor.moveToNext();
        }

        cursor.close();

        return viagens;
    }

    @Override
    public Viagem obterPorId(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String idString = String.valueOf(id);

        Cursor cursor = db.rawQuery("SELECT _id, tipoViagem, destino, orcamento, quantidade, dataChegada, dataSaida, destino  FROM "+ NOME_TABELA, null);

        Viagem p = null;

        if (cursor.moveToNext()){
            p = new Viagem();
            p.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            p.setTipoViagem(cursor.getString(cursor.getColumnIndex("tipoViagem")));
            p.setDestino(cursor.getString(cursor.getColumnIndex("destino")));
            p.setOrcamento(cursor.getString(cursor.getColumnIndex("orcamento")));
            p.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            p.setDataChegada(cursor.getString(cursor.getColumnIndex("dataChegada")));
            p.setDataSaida(cursor.getString(cursor.getColumnIndex("dataSaida")));

        }

        cursor.close();

        return p;
    }
}
