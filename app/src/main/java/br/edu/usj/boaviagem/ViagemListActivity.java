package br.edu.usj.boaviagem;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rafael on 25/10/17.
 */

public class ViagemListActivity extends ListActivity {

    private List<Map<String, Object>> viagens;
    private AlertDialog alertDialog, dialogConfirmacao;
    private int viagemSelecionada;

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(
                AdapterView<?> adapterView,
                View view, int position, long id) {
            viagemSelecionada = position;
            alertDialog.show();
        }
    };

    private DialogInterface.OnClickListener listener2 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int item) {
            switch (item){
                case 0:
                    startActivity(new Intent(getApplicationContext(), NovaViagemActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getApplicationContext(), NovoGastoActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getApplicationContext(), GastoListActivity.class));
                    break;
                case 3:
                    dialogConfirmacao.show();
                    break;
                case DialogInterface.BUTTON_POSITIVE:
                    viagens.remove(viagemSelecionada);
                    getListView().invalidateViews();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialogConfirmacao.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle
                                        savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] de = {"imagem", "destino", "data", "total"};
        int[] para = {R.id.id_tipo_viagem, R.id.id_destino,
                    R.id.id_data, R.id.id_valor};

        SimpleAdapter adapter = new SimpleAdapter(this,
                listarViagens(), R.layout.layout_lista_viagem,
                de, para);

//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this,
//                        android.R.layout.simple_list_item_1,
//                        listarViagens());
       setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(listener);

        this.alertDialog = criaAlertDialog();
        this.dialogConfirmacao = criaDialogConfirmacao();
    }

    private List<Map<String, Object>> listarViagens(){

        viagens = new ArrayList<Map<String, Object>>();

        Map<String, Object> item =
                new HashMap<String, Object>();
        item.put("imagem", R.drawable.negocios);
        item.put("destino", "São Paulo");
        item.put("data", "02/02/2017 a 04/02/2017");
        item.put("total", "Gasto Total R$ 314,98");
        viagens.add(item);

        item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.lazer);
        item.put("destino", "Floripa");
        item.put("data", "14/05/2017 a 22/05/2017");
        item.put("total", "Gasto Total R$ 25.834,67");
        viagens.add(item);

        return viagens;
    }

    private AlertDialog criaAlertDialog(){
        final CharSequence[] items = {
                getString(R.string.editar),
                getString(R.string.novo_gasto),
                getString(R.string.gastos_realizados),
                getString(R.string.remover)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(items, listener2);

        return builder.create();
    }

    private AlertDialog criaDialogConfirmacao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmacao_exclusao_viagem);
        builder.setPositiveButton(getString(R.string.sim), listener2);
        builder.setNegativeButton(getString(R.string.nao), listener2);

        return builder.create();
    }
}
