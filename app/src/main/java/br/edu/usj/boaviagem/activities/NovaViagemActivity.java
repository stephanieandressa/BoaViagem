package br.edu.usj.boaviagem.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

import br.edu.usj.boaviagem.DAO.ViagemDAO;
import br.edu.usj.boaviagem.R;
import br.edu.usj.boaviagem.activities.ViagemListActivity;
import br.edu.usj.boaviagem.entity.Viagem;

/**
 * Created by rafael on 18/10/17.
 */

public class NovaViagemActivity extends Activity {

    private ViagemDAO dao;

    private int ano, mes, dia;
    private Button dataSaida;
    private Button dataChegada;
    private RadioGroup tipoViagem;
    private EditText destino;
    private EditText orcamento;
    private EditText quantidade;

    private DatePickerDialog.OnDateSetListener listenerSaida = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            ano = year;
            mes = month;
            dia = day;
            dataSaida.setText(dia + "/" + (mes+1) + "/" + ano);
        }
    };

    private DatePickerDialog.OnDateSetListener listenerChegada = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            ano = year;
            mes = month;
            dia = day;
            dataChegada.setText(dia + "/" + (mes+1) + "/" + ano);
        }
    };
    @SuppressLint({"WrongViewCast", "ResourceType"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nova_viagem);


        Calendar cal = Calendar.getInstance();
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);

        dataSaida = (Button) findViewById(R.id.id_data_saida);
        dataSaida.setText(dia + "/" + (mes+1) + "/" + ano);

        dataChegada = (Button) findViewById(R.id.id_data_chegada);
        dataChegada.setText(dia + "/" + (mes+1) + "/" + ano);

        tipoViagem = findViewById(R.id.id_tipo_viagem);


        destino = (EditText) findViewById(R.id.id_destino);
        destino.setText("");

        orcamento = (EditText) findViewById(R.id.id_orcamento);
        orcamento.setText("");

        quantidade = (EditText) findViewById(R.id.id_quantidade_pessoas);
        quantidade.setText("");

        dao = new ViagemDAO(this);

    }


    public void selecionarData(View view){
        showDialog(view.getId());
    }


    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        if (R.id.id_data_saida == id){
            return new DatePickerDialog(this, listenerSaida, ano, mes, dia);
        }

        if (R.id.id_data_chegada == id){
            return new DatePickerDialog(this, listenerChegada, ano, mes, dia);
        }
        return null;


    }
    public void salvarViagem(View view) {


        String dataSaida = this.dataSaida.getText().toString();
        String dataChegada = this.dataChegada.getText().toString();
        String tipoViagem = ((RadioButton)findViewById(this.tipoViagem.getCheckedRadioButtonId())).getText().toString();
        String destino = this.destino.getText().toString();
        String orcamento = this.orcamento.getText().toString();
        int quantidade = Integer.valueOf(this.quantidade.getText().toString());

        Viagem p = new Viagem();
        p.setDataChegada(dataChegada);
        p.setDataSaida(dataSaida);
        p.setTipoViagem(tipoViagem);
        p.setDestino(destino);
        p.setOrcamento(orcamento);
        p.setQuantidade(quantidade);

        boolean sucesso = dao.salvar(p);

        if (sucesso){
            Toast.makeText(this,
                    "Viagem cadastrada com sucesso", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(this,
                    "Erro ao cadastrar viagem", Toast.LENGTH_LONG).show();
        }


    }

    public void listar(View view) {
        Intent intent = new Intent(this, ViagemListActivity.class);
        startActivity(intent);
    }
}
