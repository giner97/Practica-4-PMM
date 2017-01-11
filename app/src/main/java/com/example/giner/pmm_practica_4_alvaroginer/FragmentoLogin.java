package com.example.giner.pmm_practica_4_alvaroginer;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FragmentoLogin extends DialogFragment implements View.OnClickListener{

    //widgets
        private EditText nickname;
        private RadioGroup grupoRadioButtons;

    //Variables
        int dificultad=1;
        int idRadioButton;

    private FragmentoLoginListener escuchador;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Construimos el dialogo
            AlertDialog.Builder constructor = new AlertDialog.Builder(getActivity());

        //Titulo del alertDialog
            constructor.setTitle("Login:");

        //Inflamos el layout del dialogo
            View layoutDialogoLogin = getActivity().getLayoutInflater().inflate(R.layout.fragmento_dialogo_login, null);

        //Instanciamos los widgets
            nickname = (EditText)layoutDialogoLogin.findViewById(R.id.nickname);

        //Nos suscribimos a los botones
            layoutDialogoLogin.findViewById(R.id.jugar).setOnClickListener(this);
            layoutDialogoLogin.findViewById(R.id.volver).setOnClickListener(this);

        grupoRadioButtons = (RadioGroup)layoutDialogoLogin.findViewById(R.id.radioGroup);

        //Mostramos el fragmento del dialogo en el dialogo
            constructor.setView(layoutDialogoLogin);

        //Creamos el alertDialog
            AlertDialog alertDialog = constructor.create();

        //No dejamos que cierre el el dialogo cuando pulse fuera de este
            alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.jugar){

            String nicknameCadena = nickname.getText().toString();

            if(nicknameCadena.isEmpty()){

                Toast.makeText(getActivity(), "No se ha introducido ningun nickname.",Toast.LENGTH_SHORT).show();

            }

            else{

                idRadioButton=grupoRadioButtons.getCheckedRadioButtonId();

                if(idRadioButton==R.id.radioFacil){
                    dificultad=3;
                }
                else if(idRadioButton==R.id.radioMEdio){
                    dificultad=2;
                }
                else if(idRadioButton==R.id.radioDificil){
                    dificultad=1;
                }

                escuchador.nivel(dificultad);
                escuchador.nick(nicknameCadena);
                escuchador.llamaTarea(dificultad);
                dismiss();

            }

        }

        else if(v.getId()==R.id.volver){

            escuchador.botonPulsadoVolver();

        }

    }


    //Interfaz para el callback

        public interface FragmentoLoginListener{

            public void nivel(int dificultad);
            public void nick(String nickname);
            public void botonPulsadoVolver();
            public void llamaTarea(int dificultad);

        }

    //Metodo para suscribirse a las actividades del metodo

        public void setFragmentoLoginListener (FragmentoLoginListener escuchador){

            this.escuchador=escuchador;

        }

}
