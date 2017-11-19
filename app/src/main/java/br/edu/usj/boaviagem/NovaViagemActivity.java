package br.edu.usj.boaviagem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by rafael on 18/10/17.
 */

public class NovaViagemActivity extends Activity {

    private int ano, mes, dia;
    private Button dataSaida;
    private Button dataChegada;

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
    }
}
