package com.example.przelicznikwalutlubjednostek;

import android.widget.EditText;
import android.widget.Spinner;

import java.time.ZoneId;
import java.util.Locale;

public class Funkcje {
    public static void ZamianaWartosciSpinnerow(Spinner a, Spinner b) {
        int posA = a.getSelectedItemPosition();
        int posB = b.getSelectedItemPosition();

        a.setSelection(posB);
        b.setSelection(posA);
    }

    public static void ObliczWaluty(EditText textA, EditText textB, Spinner spinnerA, Spinner spinnerB)
    {
        double przelicznik = 1.0;
        String walutaA = spinnerA.getSelectedItem().toString();
        String walutaB = spinnerB.getSelectedItem().toString();

        double wartosc;
        try {
            wartosc = Double.parseDouble(textA.getText().toString());
        } catch (Exception e) {
            textB.setText("");
            return;
        }

        switch (walutaA) {
            case "PLN":
                switch (walutaB) {
                    case "EUR": przelicznik = 0.24; break;
                    case "USD": przelicznik = 0.27; break;
                }
                break;

            case "EUR":
                switch (walutaB) {
                    case "PLN": przelicznik = 4.24; break;
                    case "USD": przelicznik = 1.16; break;
                }
                break;

            case "USD":
                switch (walutaB) {
                    case "PLN": przelicznik = 3.64; break;
                    case "EUR": przelicznik = 0.86; break;
                }
                break;
        }

        double wynik = wartosc * przelicznik;
        textB.setText(String.format("%.2f", wynik));
    }
    public static void ObliczJednostki(EditText textA, EditText textB, Spinner spinnerA, Spinner spinnerB)
    {
        double przelicznik = 1.0;
        String jednostkaA = spinnerA.getSelectedItem().toString();
        String jednostkaB = spinnerB.getSelectedItem().toString();

        double wartosc;
        try {
            wartosc = Double.parseDouble(textA.getText().toString());
        } catch (Exception e) {
            textB.setText("");
            return;
        }

        switch (jednostkaA)
        {
            case "mm":
                switch (jednostkaB){
                    case "cm": przelicznik = 0.1; break;
                    case "dm": przelicznik = 0.01; break;
                    case "m":  przelicznik = 0.001; break;
                    case "km": przelicznik = 0.000001; break;
                }
                break;

            case "cm":
                switch (jednostkaB){
                    case "mm": przelicznik = 10.0; break;
                    case "dm": przelicznik = 0.1; break;
                    case "m":  przelicznik = 0.01; break;
                    case "km": przelicznik = 0.00001; break;
                }
                break;

            case "dm":
                switch (jednostkaB){
                    case "mm": przelicznik = 100.0; break;
                    case "cm": przelicznik = 10.0; break;
                    case "m":  przelicznik = 0.1; break;
                    case "km": przelicznik = 0.0001; break;
                }
                break;

            case "m":
                switch (jednostkaB){
                    case "mm": przelicznik = 1000.0; break;
                    case "cm": przelicznik = 100.0; break;
                    case "dm": przelicznik = 10.0; break;
                    case "km": przelicznik = 0.001; break;
                }
                break;

            case "km":
                switch (jednostkaB){
                    case "mm": przelicznik = 1_000_000.0; break;
                    case "cm": przelicznik = 100_000.0; break;
                    case "dm": przelicznik = 10_000.0; break;
                    case "m":  przelicznik = 1000.0; break;
                }
                break;
        }

        double wynik = wartosc * przelicznik;
        textB.setText(String.format(Locale.US, "%.2f", wynik));
    }

}
