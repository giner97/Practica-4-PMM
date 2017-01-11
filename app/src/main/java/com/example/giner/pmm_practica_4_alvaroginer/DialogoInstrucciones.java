package com.example.giner.pmm_practica_4_alvaroginer;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class DialogoInstrucciones extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Creamos el dialogo
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Titulo
            builder.setTitle("Instrucciones del juego");
        //Mensaje
            builder.setMessage("Debes de presionar los botones en el orden correcto para poder pasar de nivel. En cada nivel tienes menos tiempo para darle a los botones. Tienes tres dificultades a elegir en el login.");

        return builder.create();
    }
}
