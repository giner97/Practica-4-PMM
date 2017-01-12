package com.example.giner.pmm_practica_4_alvaroginer;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnClickListener, FragmentoLogin.FragmentoLoginListener, DialogoGameOver.FragmentoGameOverListener{

    private TextView nicknameTextView;
    private TareaBotones tarea;
    private int numDificultad=0;
    private int contadorBotones=1;
    private ArrayList <Button> botones;
    private ArrayList <Integer> numeros;
    private ProgressBar barraDeCarga;
    private TextView numCarga;
    private int nivelFase=1;
    private int aumentoDificultad=0;
    private TextView level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //ArrayList botones y ArrayList numeros
            botones = new ArrayList<Button>();
            numeros = new ArrayList<Integer>();
            numeros.add(1);
            numeros.add(2);
            numeros.add(3);
            numeros.add(4);

        //Llamamos al metodo llamaLogin para mostrar el dialogo del login

            llamaLogin();

        //Instanciamos los objetos
            nicknameTextView = (TextView)findViewById(R.id.nickname);
            botones.add((Button)findViewById(R.id.button));
            botones.add((Button)findViewById(R.id.button2));
            botones.add((Button)findViewById(R.id.button3));
            botones.add((Button)findViewById(R.id.button4));
            barraDeCarga = (ProgressBar)findViewById(R.id.progressBar);
            numCarga = (TextView)findViewById(R.id.carga);
            level = (TextView)findViewById(R.id.level);

        //OnClickListener de los botones

            for(int i=0;i<numeros.size();i++){

                botones.get(i).setOnClickListener(this);

            }


    }

    @Override
    public void onClick(View v) {

        Button botonPresionado = (Button) v;
        if(Integer.parseInt(botonPresionado.getText().toString())==contadorBotones){
            botonPresionado.setEnabled(false);
            contadorBotones++;

            if(contadorBotones==5){

                tarea.cancel(false);
                contadorBotones=1;
                nivelFase++;
                aumentoDificultad++;
                llamaTarea(numDificultad,aumentoDificultad);

            }

        }

    }

    private class TareaBotones extends AsyncTask<Integer,Integer,Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Reiniciamos los widgets

            level.setText("Nivel "+nivelFase);
            numerarBotones(numeros);
            barraDeCarga.setProgress(0);
            numCarga.setText(String.valueOf("0/100"));

        }

        @Override
        protected Integer doInBackground(Integer... params) {

            int i;
            for(i=0;i<100;i++){

                publishProgress(i);
                SystemClock.sleep(params[0]);

                if(isCancelled()){
                    break;
                }

            }

            return i;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //Capturamos el valor para mostrarlo en el textView para ver el porcentaje de carga
                int porcentaje = values[0];
                barraDeCarga.setProgress(porcentaje);
                numCarga.setText(String.valueOf(porcentaje)+"/100");

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            DialogoGameOver fragmentoGameOver = DialogoGameOver.newInstance(nicknameTextView.getText().toString(), nivelFase);
            fragmentoGameOver.setFragmentoGameOverListener(SecondActivity.this);
            fragmentoGameOver.show(getFragmentManager(),null);
            fragmentoGameOver.setCancelable(false);


        }
    }

    //Metodo que llama al dialogo del login

        public void llamaLogin(){

            //Creamos una instancia de la clase fragmentoLogin
                FragmentoLogin alertDialogLogin = new FragmentoLogin();

            //No dejamos que se cierre el dialogo con back
                alertDialogLogin.setCancelable(false);

            //Nos suscribimos al evento FragmentoDialogoListener
                alertDialogLogin.setFragmentoLoginListener(this);

            //Mostramos el dialogo del login.
                alertDialogLogin.show(getFragmentManager(),null);

        }

    //Metodos FragmentoLogin

    @Override
    public void nivel(int dificultad) {

        numDificultad = dificultad;

    }

    @Override
    public void nick(String nickname) {

        nicknameTextView.setText(nickname);

    }

    @Override
    public void botonPulsadoVolver() {

        finish();

    }

    @Override
    public void llamaTarea(int dificultad, int masDificultad) {

        int tiempo=dificultad*20;
        //tiempo = tiempo-masDificultad;

        //Ejecutamos la tarea
            tarea = new TareaBotones();
            tarea.execute(tiempo);

    }

    //Metodo para poner los numeros a los botones

    private void numerarBotones(ArrayList<Integer>numeros){

        //Shufleamos el arrayList
        Collections.shuffle(numeros);

        for(int i=0;i<numeros.size();i++){

            botones.get(i).setEnabled(true);
            botones.get(i).setText(String.valueOf(numeros.get(i)));

        }

    }

    //Metodos DialogoGameOver

    @Override
    public void reiniciar() {


        llamaLogin();
        contadorBotones=1;
        nivelFase=1;
        aumentoDificultad=0;

    }

    @Override
    public void salir() {

        finish();

    }

    @Override
    public void onBackPressed() {

        tarea.cancel(false);
        finish();

    }
}
