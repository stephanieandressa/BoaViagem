package br.edu.usj.boaviagem;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

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
