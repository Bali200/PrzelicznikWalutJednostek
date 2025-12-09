package com.example.przelicznikwalutlubjednostek;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner jednostka1 = findViewById(R.id.jednostka1);
        Spinner jednostka2 = findViewById(R.id.jednostka2);

        ArrayAdapter<CharSequence> adapterJ = ArrayAdapter.createFromResource(
                this,
                R.array.jednostki_opcje,
                android.R.layout.simple_spinner_item
        );

        adapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        jednostka1.setAdapter(adapterJ);
        jednostka2.setAdapter(adapterJ);

        EditText jednostka1Text = findViewById(R.id.jednostka1Text);
        EditText jednostka2Text = findViewById(R.id.jednostka2Text);

        Button przelaczWidokButton = findViewById(R.id.zmienWidok);

        przelaczWidokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(i);
            }
        });

        Button zamienButton = findViewById(R.id.buttonSwitch);

        zamienButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Funkcje.ZamianaWartosciSpinnerow(jednostka1, jednostka2);
                Log.d("MainActivity2", "Zamieniono jednostki");
            }
        });

        jednostka1Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    Funkcje.ObliczJednostki(jednostka1Text, jednostka2Text, jednostka1, jednostka2);
                } else {
                    jednostka2Text.setText("0.0");
                }
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        jednostka1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!jednostka1Text.getText().toString().isEmpty()) {
                    Funkcje.ObliczJednostki(jednostka1Text, jednostka2Text, jednostka1, jednostka2);
                }
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        jednostka2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!jednostka1Text.getText().toString().isEmpty()) {
                    Funkcje.ObliczJednostki(jednostka1Text, jednostka2Text, jednostka1, jednostka2);
                }
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
