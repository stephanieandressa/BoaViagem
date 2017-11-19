package br.edu.usj.boaviagem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * Created by rafael on 25/10/17.
 */

public class NovoGastoActivity extends Activity {

    private int ano, mes, dia;
    private Button dataGasto;
    private Spinner categoria;

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            ano = year;
            mes = month;
            dia = day;
            dataGasto.setText(dia + "/" + (mes+1) + "/" + ano);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_novo_gasto);

        Calendar cal = Calendar.getInstance();
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);

        dataGasto = (Button) findViewById(R.id.id_data);
        dataGasto.setText(dia + "/" + (mes+1) + "/" + ano);

        categoria = (Spinner) findViewById(R.id.id_categoria);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                        R.array.categoria_gasto,
                        android.R.layout.simple_spinner_item);
        categoria.setAdapter(adapter);

    }

    public void selecionarData(View view){
        showDialog(view.getId());
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        if (R.id.id_data == id){
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }

    public void salvar(View view) {
    }
}
