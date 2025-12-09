package com.example.przelicznikwalutlubjednostek;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner waluta1 = findViewById(R.id.jednostka1);
        Spinner waluta2 = findViewById(R.id.jednostka2);

        ArrayAdapter<CharSequence> adapterW = ArrayAdapter.createFromResource(this, R.array.waluty_opcje,
                android.R.layout.simple_spinner_item);

        adapterW.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        waluta1.setAdapter(adapterW);
        waluta2.setAdapter(adapterW);

        EditText waluta1Text = findViewById(R.id.jednostka1Text);
        EditText waluta2Text = findViewById(R.id.jednostka2Text);

        Button zamienWalutyButton = findViewById(R.id.buttonSwitch);
        Button przelaczWidokButton = findViewById(R.id.ZmienWidok);

        przelaczWidokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });

        zamienWalutyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Funkcje.ZamianaWartosciSpinnerow(waluta1, waluta2);
                Log.d("MainActivity", "Zamieniono waluty");
            }
        });


        waluta1Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0)
                {
                    Funkcje.ObliczWaluty(waluta1Text, waluta2Text, waluta1, waluta2);
                } else {
                    waluta2Text.setText("0.0");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        waluta1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!waluta1Text.getText().toString().isEmpty()) {
                    Funkcje.ObliczWaluty(waluta1Text, waluta2Text, waluta1, waluta2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        waluta2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!waluta1Text.getText().toString().isEmpty()) {
                    Funkcje.ObliczWaluty(waluta1Text, waluta2Text, waluta1, waluta2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}