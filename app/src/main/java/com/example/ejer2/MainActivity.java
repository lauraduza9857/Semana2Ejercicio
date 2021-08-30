package com.example.ejer2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TextView preguntaText;
    private EditText respuestaText;
    private TextView contadorText;
    private TextView puntajeText;
    private Button answerText;
    private Pregunta preguntaActual;
    private int tiempoRonda;
    private int puntaje;
    private int tiempoPulsado;
    private boolean isPressing;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preguntaText = findViewById(R.id.preguntaText);
        respuestaText = findViewById(R.id.respuestaText);
        contadorText = findViewById(R.id.contadorText);
        puntajeText = findViewById(R.id.puntajeText);
        answerText = findViewById(R.id.answerText);

        puntaje = 0;
        puntajeText.setText("puntaje:"+ puntaje);

        tiempoRonda = 30;
        contadorText.setText("" + tiempoRonda);
        new Thread(
                () -> {
                    while (tiempoRonda > 0){
                    try {
                        tiempoRonda--;
                        runOnUiThread(
                                () -> {
                                        contadorText.setText(""+tiempoRonda);
                                }
                        );
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        Log.e("Error", e.toString());
                    }
                }


        }).start();


        generarPregunta();

        answerText.setOnClickListener(
                (view) -> {
                     verificarRespuesta();
                } );

        //skip pregunta
        tiempoPulsado =0;
        preguntaText.setOnTouchListener(
                (view, event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            isPressing = true;
                            tiempoPulsado = 0;

                            new Thread(() -> {
                                while (tiempoPulsado > 1500 ){
                                    try {
                                        Thread.sleep(1);
                                        tiempoPulsado+=150;
                                        if (!isPressing){
                                            return;
                                        }
                                    }catch (InterruptedException e){

                                    }
                                }
                                runOnUiThread(()->{

                                Toast.makeText(this, "Pasaron1.5", Toast.LENGTH_SHORT).show();
                                });

                            }).start();
                            break;

                        case MotionEvent.ACTION_UP:

                            isPressing = false;

                            break;
                    }
                    return true;
        });

    }

    public void verificarRespuesta(){

        String restUsuario = answerText.getText().toString();
        int respuestaInt = (int) Integer.parseInt(restUsuario);

        if (respuestaInt == preguntaActual.getanswerText()) {
            Toast.makeText(this, "correcto", Toast.LENGTH_SHORT).show();
            puntaje += 5;
            puntajeText.setText("puntaje: "+puntaje);

        } else {

            Toast.makeText(this, "incorrecto", Toast.LENGTH_SHORT).show();

        }
        generarPregunta();
    }

    public void generarPregunta(){

        preguntaActual = new Pregunta();
        preguntaText.setText(preguntaActual.getPregunta());

    }





}