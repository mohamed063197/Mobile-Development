package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class ScanFragment extends Fragment {

    public ScanFragment() {
        // Constructeur vide requis par Android
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Vous pouvez choisir de ne pas créer de vue spécifique ici
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Logique de numérisation à exécuter lorsque le fragment est créé
        startScanner();
    }





    private void startScanner() {
        // Initialiser le scanner avec le fragment
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scanner un code barre qui se trouve dans la boite");
        integrator.initiateScan();
    }

    // Méthode appelée lorsqu'un résultat est reçu du scanner
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                // Traiter le résultat du scan, par exemple, afficher dans un Toast
                Toast.makeText(requireContext(), "Code scanné : " + result.getContents(), Toast.LENGTH_SHORT).show();
            } else {
                // L'utilisateur a annulé le scan
                Toast.makeText(requireContext(), "Scan annulé", Toast.LENGTH_SHORT).show();
            }
        }
    }
}