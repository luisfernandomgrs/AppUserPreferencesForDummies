package com.luisf.learning.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private static final String SHARED_PREFERENCES = "FileAppPreference";
    private TextInputEditText textEnterYourName;
    private Button buttonSave;
    private TextView textPreferenceLoadedData;
    private Snackbar snackbar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Preferências do Usuário");

        textEnterYourName = findViewById(R.id.textEnterYourName);
        buttonSave = findViewById(R.id.buttonSave);
        textPreferenceLoadedData = findViewById(R.id.textPreferenceLoadedData);

        //mode = 0, indica um modo privado onde somente a aplicação poderá ler/salvar neste arquivo
        preferences = getSharedPreferences(SHARED_PREFERENCES, 0);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNome = textEnterYourName.getText().toString();

                //nescessário quando deseja alterar dados no arquivo
                SharedPreferences.Editor editPreference = preferences.edit();

                if (sNome.isEmpty()) {
                    /*
                    // formas de instanciar um Snackbar ao invés de um Toast...

                    snackbar = Snackbar.make(v, "Você precisa digitar um texto antes de salvar!", Snackbar.LENGTH_LONG); //.setAction("Ok", null);
                    snackbar.show();
                    */
                    Snackbar.make(v, "Você precisa digitar um texto antes de salvar!", Snackbar.LENGTH_LONG).show();
                }
                else {
                    editPreference.putString("nome", sNome);
                    editPreference.commit();

                    textPreferenceLoadedData.setText("Olá, " + sNome);
                    Snackbar.make(v, "Processo executado com sucesso", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        textPreferenceLoadedData.setText(R.string.title_Preference_Loaded_Data);
        //get data from user preference
        if (preferences.contains("nome")) {
            textPreferenceLoadedData.setText("Olá, " +
                    preferences.getString("nome",
                    (String)getText(R.string.sub_title_Preference_Loaded_Data)
            ));
        }
    }
}