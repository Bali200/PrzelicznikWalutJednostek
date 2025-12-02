package com.example.przelicznikwalutlubjednostek;

import android.os.Bundle;
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

        Spinner waluta1 = findViewById(R.id.waluta1);
        Spinner waluta2 = findViewById(R.id.waluta2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.waluty_opcje,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        waluta1.setAdapter(adapter);
        waluta2.setAdapter(adapter);

        EditText waluta1Text = findViewById(R.id.waluta1Text);
        EditText waluta2Text = findViewById(R.id.waluta2Text);

        Button obliczButton = findViewById(R.id.buttonOblicz);
        Button zamienWalutyButton = findViewById(R.id.buttonSwitch);

        zamienWalutyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wybor1 = waluta1.getSelectedItemPosition();
                int wybor2 = waluta2.getSelectedItemPosition();

                waluta1.setSelection(wybor2);
                waluta2.setSelection(wybor1);
            }
        });

        obliczButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double przelicznik = 1.0;
                double wartosc1 = Float.parseFloat(waluta1Text.getText().toString());
                String wybranaWaluta1 = waluta1.getSelectedItem().toString();
                String wybranaWaluta2 = waluta2.getSelectedItem().toString();

                //stan na 02.12.2025
                if (wybranaWaluta1.equals("PLN")) {
                    if (wybranaWaluta2.equals("EUR")) {
                        przelicznik = 0.24;  // 1 PLN = 0.24 EUR
                    } else if (wybranaWaluta2.equals("USD")) {
                        przelicznik = 0.27;  // 1 PLN = 0.27 USD
                    }
                } else if (wybranaWaluta1.equals("EUR")) {
                    if (wybranaWaluta2.equals("PLN")) {
                        przelicznik = 4.24;  // 1 EUR = 4.24 PLN
                    } else if (wybranaWaluta2.equals("USD")) {
                        przelicznik = 1.16;  // 1 EUR = 1.16 USD
                    }
                } else if (wybranaWaluta1.equals("USD")) {
                    if (wybranaWaluta2.equals("PLN")) {
                        przelicznik = 3.65;  // 1 USD = 3.65 PLN
                    } else if (wybranaWaluta2.equals("EUR")) {
                        przelicznik = 0.86;  // 1 USD = 0.86 EUR
                    }
                }

                double wynik = wartosc1 * przelicznik;
                waluta2Text.setText(String.valueOf(Math.round(wynik * 100.0) / 100.0));
            }
        });

    }
}