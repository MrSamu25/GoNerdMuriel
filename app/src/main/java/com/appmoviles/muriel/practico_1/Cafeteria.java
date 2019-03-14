package com.appmoviles.muriel.practico_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cafeteria extends AppCompatActivity {

    private TextView tv_cafeteria_pregunta;

    private RadioButton rb_cafeteria_a;

    private RadioButton rb_cafeteria_b;

    private RadioButton rb_cafeteria_c;

    private RadioButton rb_cafeteria_d;

    private Button btn_cafeteria_responder;

    private Button btn_cafeteria_atras;

    private String respuesta_buena;

    public final static int PUNTOS_OBTENIDOS_DIFICIL = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria);

        //Se buscan en el xml

        tv_cafeteria_pregunta = findViewById(R.id.tv_cafeteria_pregunta);
        rb_cafeteria_a = findViewById(R.id.rb_cafeteria_a);
        rb_cafeteria_b = findViewById(R.id.rb_cafeteria_b);
        rb_cafeteria_c = findViewById(R.id.rb_cafeteria_c);
        rb_cafeteria_d = findViewById(R.id.rb_cafeteria_d);
        btn_cafeteria_responder = findViewById(R.id.btn_cafeteria_responder);
        btn_cafeteria_atras = findViewById(R.id.btn_cafeteria_atras);

        //Se obtienen los valores
        String operando_1 = getIntent().getStringExtra(MapsActivity.OPERANDO_1);
        String operador = getIntent().getStringExtra(MapsActivity.OPERADOR);
        String operando_2 = getIntent().getStringExtra(MapsActivity.OPERANDO_2);

        //DEBE SER UN ATRIBUTO PARA DESPUÉS COMPARAR LA RESPUESTA DEL USUARIO
        respuesta_buena = getIntent().getStringExtra(MapsActivity.RESPUESTA_BUENA);
        String respuesta_mala_1 = getIntent().getStringExtra(MapsActivity.RESPUESTA_INCORRECTA_1);
        String respuesta_mala_2 = getIntent().getStringExtra(MapsActivity.RESPUESTA_INCORRECTA_2);
        String respuesta_mala_3 = getIntent().getStringExtra(MapsActivity.RESPUESTA_INCORRECTA_3);

        //Se setean los valores
        tv_cafeteria_pregunta.setText(operando_1 + " " + operador + " " + operando_2);

        List<String> respuestasSinOrden = new ArrayList<String>();

        //Las agreamos a una lista las respuestas
        respuestasSinOrden.add(respuesta_buena);
        respuestasSinOrden.add(respuesta_mala_1);
        respuestasSinOrden.add(respuesta_mala_2);
        respuestasSinOrden.add(respuesta_mala_3);

        //Las ordena en orden aleatorio
        Collections.shuffle(respuestasSinOrden);
        Collections.shuffle(respuestasSinOrden);

        rb_cafeteria_a.setText(respuestasSinOrden.get(0));
        rb_cafeteria_b.setText(respuestasSinOrden.get(1));
        rb_cafeteria_c.setText(respuestasSinOrden.get(2));
        rb_cafeteria_d.setText(respuestasSinOrden.get(3));

        btn_cafeteria_responder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int puntos_ganados = 0;

                if (rb_cafeteria_a.isChecked()) {

                    if (rb_cafeteria_a.getText().equals(respuesta_buena)) {
                        Toast.makeText(Cafeteria.this, "RESPUESTA CORRECTA + " + PUNTOS_OBTENIDOS_DIFICIL + " PUNTOS", Toast.LENGTH_SHORT).show();
                        puntos_ganados = PUNTOS_OBTENIDOS_DIFICIL;
                    } else {
                        Toast.makeText(Cafeteria.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    }

                } else if (rb_cafeteria_b.isChecked()) {

                    if (rb_cafeteria_b.getText().equals(respuesta_buena)) {
                        Toast.makeText(Cafeteria.this, "RESPUESTA CORRECTA + " + PUNTOS_OBTENIDOS_DIFICIL + " PUNTOS", Toast.LENGTH_SHORT).show();
                        puntos_ganados = PUNTOS_OBTENIDOS_DIFICIL;
                    } else {
                        Toast.makeText(Cafeteria.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    }

                } else if (rb_cafeteria_c.isChecked()) {

                    if (rb_cafeteria_c.getText().equals(respuesta_buena)) {
                        Toast.makeText(Cafeteria.this, "RESPUESTA CORRECTA + " + PUNTOS_OBTENIDOS_DIFICIL + " PUNTOS", Toast.LENGTH_SHORT).show();
                        puntos_ganados = PUNTOS_OBTENIDOS_DIFICIL;
                    } else {
                        Toast.makeText(Cafeteria.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    }

                } else if (rb_cafeteria_d.isChecked()) {

                    if (rb_cafeteria_d.getText().equals(respuesta_buena)) {
                        Toast.makeText(Cafeteria.this, "RESPUESTA CORRECTA + " + PUNTOS_OBTENIDOS_DIFICIL + " PUNTOS", Toast.LENGTH_SHORT).show();
                        puntos_ganados = PUNTOS_OBTENIDOS_DIFICIL;
                    } else {
                        Toast.makeText(Cafeteria.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Cafeteria.this, "DEBE SELECCIONAR UNA PREGUNTA", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent();
                intent.putExtra(MapsActivity.PUNTOS_OBTENIDOS, "" + puntos_ganados);
                setResult(MapsActivity.REQUEST_CODE_CAFETERIA, intent);
                //Regresar al activity anterior
                finish();
            }
        });

        btn_cafeteria_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MapsActivity.PUNTOS_OBTENIDOS, "" + 0);
                setResult(MapsActivity.REQUEST_CODE_CAFETERIA, intent);
                //Regresar al activity anterior
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra(MapsActivity.PUNTOS_OBTENIDOS, "" + 0);
        setResult(MapsActivity.REQUEST_CODE_CAFETERIA, intent);
        //Regresar al activity anterior
        finish();
    }
}
