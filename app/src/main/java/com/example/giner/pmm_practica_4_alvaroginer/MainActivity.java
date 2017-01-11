package com.example.giner.pmm_practica_4_alvaroginer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Creamos los objetos

        private ImageButton botonInstrucciones;
        private ImageButton botonJugar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciamos los objetos

            botonJugar = (ImageButton)findViewById(R.id.botonJugar);
            botonInstrucciones = (ImageButton)findViewById(R.id.instrucciones);
            botonJugar.setOnClickListener(this);
            botonInstrucciones.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent intencion1 = new Intent(this,SecondActivity.class);

        if(v.getId()==R.id.botonJugar){

            startActivity(intencion1);

        }

        else if(v.getId()==R.id.instrucciones){

            DialogoInstrucciones dialogoInstrucciones = new DialogoInstrucciones();
            dialogoInstrucciones.show(getFragmentManager(),null);

        }

    }
}
