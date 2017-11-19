package br.edu.usj.boaviagem;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rafael on 26/10/17.
 */

public class GastoListActivity extends ListActivity {

    private List<Map<String, Object>> gastos;
    private String dataAnterior = "";

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, Object> map = gastos.get(position);
            String descricao = (String) map.get("descricao");
            String mensagem = "Gasto selecionado: "+descricao;
            Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] de = {"data", "descricao", "valor", "categoria"};
        int[] para = {R.id.id_data, R.id.id_descricao, R.id.id_valor, R.id.id_categoria};

        SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(),
                R.layout.layout_lista_gasto, de, para);
        adapter.setViewBinder(new GastoViewBinder());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(listener);
    }

    private List<Map<String, Object>> listarGastos(){
        gastos = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("data", "04/02/2017");
        item.put("descricao", "Diária Hotel");
        item.put("valor", "R$ 260,00");
        item.put("categoria", R.color.categoria_hospedagem);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data", "09/05/2017");
        item.put("descricao", "Lanche");
        item.put("valor", "R$ 20,00");
        item.put("categoria", R.color.categoria_alimentacao);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data", "09/05/2017");
        item.put("descricao", "Taxi");
        item.put("valor", "R$ 35,00");
        item.put("categoria", R.color.categoria_transporte);
        gastos.add(item);

        return gastos;
    }

    private class GastoViewBinder implements SimpleAdapter.ViewBinder{

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {

            if (view.getId() == R.id.id_data){
                if (!dataAnterior.equals(data)){
                    TextView textView = (TextView) view;
                    textView.setText(textRepresentation);
                    dataAnterior = textRepresentation;
                    view.setVisibility(View.VISIBLE);
                }
                else{
                    view.setVisibility(View.GONE);
                }
                return true;
            }

            if (view.getId() == R.id.id_categoria){
                Integer id = (Integer) data;
                view.setBackgroundColor(getResources().getColor(id));
                return true;
            }
            return false;
        }
    }
}
