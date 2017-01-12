package com.example.giner.pmm_practica_4_alvaroginer;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class DialogoGameOver extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Creamos el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Titulo
        builder.setTitle("GAME OVER");
        //Mensaje
        builder.setMessage("");

        return builder.create();

    }

}
