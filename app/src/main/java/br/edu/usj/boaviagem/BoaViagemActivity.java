package br.edu.usj.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rafael on 04/10/17.
 */

public class BoaViagemActivity extends Activity {

    private EditText usuario, senha;
    private CheckBox manterConectado;
    private static final String MANTER_CONECTADO = "manter_conectado";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        this.usuario = (EditText) findViewById(R.id.id_usuario);
        this.senha = (EditText) findViewById(R.id.id_senha);
        manterConectado = (CheckBox) findViewById(R.id.id_manter_conectado);

        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        boolean conectado = preferencias.getBoolean(MANTER_CONECTADO,false);
        if (conectado){
            startActivity(new Intent(this, DashboardActivity.class));
        }
    }

    public void logar(View view) {
        String usuarioInformado = this.usuario.getText().toString();
        String senhaInformada = this.senha.getText().toString();

        if(usuarioInformado.equals("usj")
                && senhaInformada.equals("usj")){

            SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putBoolean(MANTER_CONECTADO, manterConectado.isChecked());
            editor.commit();

            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        }
        else{
            //exibir mensagem erro
            String msg = getString(R.string.erro_autenticacao);
            Toast t = Toast.makeText(this,msg, Toast.LENGTH_SHORT);
            t.show();
        }

    }
}
