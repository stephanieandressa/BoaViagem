package br.edu.usj.boaviagem.activities;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.usj.boaviagem.DAO.ViagemDAO;
import br.edu.usj.boaviagem.activities.NovoGastoActivity;
import br.edu.usj.boaviagem.R;
import br.edu.usj.boaviagem.activities.GastoListActivity;
import br.edu.usj.boaviagem.activities.NovaViagemActivity;
import br.edu.usj.boaviagem.entity.Viagem;

/**
 * Created by rafael on 25/10/17.
 */

public class ViagemListActivity extends ListActivity {

    private List<Map<String, Object>> viagens;
    private AlertDialog alertDialog, dialogConfirmacao;
    private int viagemSelecionada;
    private ViagemDAO dao;

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

        dao = new ViagemDAO(this);

        String[] de = {"imagem", "destino", "data", "total"};
        int[] para = {R.id.id_tipo_viagem, R.id.id_destino,
                    R.id.id_data, R.id.id_valor};

        SimpleAdapter adapter = new SimpleAdapter(this,
                listarViagens(), R.layout.layout_lista_viagem,
                de, para);

       setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(listener);

        this.alertDialog = criaAlertDialog();
        this.dialogConfirmacao = criaDialogConfirmacao();
    }

    private List<Map<String, Object>> listarViagens(){

        viagens = new ArrayList<Map<String, Object>>();

        List<Viagem> listaPessoas = dao.listar();

        for(Viagem p: listaPessoas){
            Map<String, Object> item =
                    new HashMap<String, Object>();
            item.put("id", p.getId());
            item.put("imagem", p.getTipoViagem());
            item.put("destino", p.getDestino());
            item.put("data", p.getDataSaida());
            item.put("total", p.getOrcamento());
            viagens.add(item);
        }

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
