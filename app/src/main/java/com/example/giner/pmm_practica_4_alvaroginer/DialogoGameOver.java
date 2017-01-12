package com.example.giner.pmm_practica_4_alvaroginer;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

public class DialogoGameOver extends DialogFragment implements DialogInterface.OnClickListener{

    //Variables
        private String nombreJugador;
        private int nivel;
        private FragmentoGameOverListener escuchador;

    //Metodo estatico para pasarle al dialogo el nickname del jugador y el nivel que ha conseguido

    public static DialogoGameOver newInstance(String nickname, int nivel){

        Bundle args = new Bundle();
        args.putString("nickname",nickname);
        args.putInt("nivel",nivel);
        DialogoGameOver fragmento = new DialogoGameOver();
        fragmento.setArguments(args);
        return fragmento;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Recuperamos los datos del metodo

            nombreJugador = getArguments().getString("nickname");
            nivel = getArguments().getInt("nivel");

        //Creamos el dialogo
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Titulo
            builder.setTitle("GAME OVER");

        //Mensaje
            builder.setMessage("Nickname: "+nombreJugador+"\nHas llegado hasta el nivel: "+nivel);

        //Botones
            builder.setPositiveButton("Volver a jugar", this);
            builder.setNegativeButton("Salir",this);

        return builder.create();

    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

        if(which==DialogInterface.BUTTON_POSITIVE){

            escuchador.reiniciar();
            dismiss();

        }

        else if(which==DialogInterface.BUTTON_NEGATIVE){

            escuchador.salir();
            dismiss();

        }

    }

    public interface FragmentoGameOverListener{

        void reiniciar();
        void salir();

    }

    public void setFragmentoGameOverListener(FragmentoGameOverListener escuchador){

        this.escuchador=escuchador;

    }

}
