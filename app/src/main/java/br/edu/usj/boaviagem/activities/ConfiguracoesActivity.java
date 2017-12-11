package br.edu.usj.boaviagem.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import br.edu.usj.boaviagem.R;

/**
 * Created by rafael on 26/10/17.
 */

public class ConfiguracoesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
