package com.appmoviles.muriel.practico_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Edificio extends AppCompatActivity {

    private TextView tv_edificio_pregunta;

    private RadioButton rb_edificio_a;

    private RadioButton rb_edificio_b;

    private RadioButton rb_edificio_c;

    private RadioButton rb_edificio_d;

    private Button btn_edificio_responder;

    private Button btn_edificio_atras;

    private RadioGroup rg_edificio_conjunto_respuestas;

    private String respuesta_buena;

    public final static int PUNTOS_OBTENIDOS_FACIL = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edificio);

        //Se buscan en el xml

        tv_edificio_pregunta = findViewById(R.id.tv_edificio_pregunta);
        rb_edificio_a = findViewById(R.id.rb_edificio_a);
        rb_edificio_b = findViewById(R.id.rb_edificio_b);
        rb_edificio_c = findViewById(R.id.rb_edificio_c);
        rb_edificio_d = findViewById(R.id.rb_edificio_d);
        btn_edificio_responder = findViewById(R.id.btn_edificio_responder);
        rg_edificio_conjunto_respuestas = findViewById(R.id.rg_edificio_conjunto_respuestas);
        btn_edificio_atras = findViewById(R.id.btn_edificio_atras);

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

        tv_edificio_pregunta.setText(operando_1 + " " + operador + " " + operando_2);

        List<String> respuestasSinOrden = new ArrayList<String>();

        //Las agreamos a una lista las respuestas
        respuestasSinOrden.add(respuesta_buena);
        respuestasSinOrden.add(respuesta_mala_1);
        respuestasSinOrden.add(respuesta_mala_2);
        respuestasSinOrden.add(respuesta_mala_3);

        //Las ordena en orden aleatorio
        Collections.shuffle(respuestasSinOrden);
        Collections.shuffle(respuestasSinOrden);

        rb_edificio_a.setText(respuestasSinOrden.get(0));
        rb_edificio_b.setText(respuestasSinOrden.get(1));
        rb_edificio_c.setText(respuestasSinOrden.get(2));
        rb_edificio_d.setText(respuestasSinOrden.get(3));


        btn_edificio_responder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int puntos_ganados = 0;

                if (rb_edificio_a.isChecked()) {

                    if (rb_edificio_a.getText().equals(respuesta_buena)) {
                        Toast.makeText(Edificio.this, "RESPUESTA CORRECTA + " + PUNTOS_OBTENIDOS_FACIL + " PUNTOS", Toast.LENGTH_SHORT).show();
                        puntos_ganados = PUNTOS_OBTENIDOS_FACIL;
                    } else {
                        Toast.makeText(Edificio.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    }

                } else if (rb_edificio_b.isChecked()) {

                    if (rb_edificio_b.getText().equals(respuesta_buena)) {
                        Toast.makeText(Edificio.this, "RESPUESTA CORRECTA + " + PUNTOS_OBTENIDOS_FACIL + " PUNTOS", Toast.LENGTH_SHORT).show();
                        puntos_ganados = PUNTOS_OBTENIDOS_FACIL;
                    } else {
                        Toast.makeText(Edificio.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    }

                } else if (rb_edificio_c.isChecked()) {

                    if (rb_edificio_c.getText().equals(respuesta_buena)) {
                        Toast.makeText(Edificio.this, "RESPUESTA CORRECTA + " + PUNTOS_OBTENIDOS_FACIL + " PUNTOS", Toast.LENGTH_SHORT).show();
                        puntos_ganados = PUNTOS_OBTENIDOS_FACIL;
                    } else {
                        Toast.makeText(Edificio.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    }

                } else if (rb_edificio_d.isChecked()) {

                    if (rb_edificio_d.getText().equals(respuesta_buena)) {
                        Toast.makeText(Edificio.this, "RESPUESTA CORRECTA + " + PUNTOS_OBTENIDOS_FACIL + " PUNTOS", Toast.LENGTH_SHORT).show();
                        puntos_ganados = PUNTOS_OBTENIDOS_FACIL;
                    } else {
                        Toast.makeText(Edificio.this, "RESPUESTA INCORRECTA", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Edificio.this, "DEBE SELECCIONAR UNA PREGUNTA", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent();
                intent.putExtra(MapsActivity.PUNTOS_OBTENIDOS, "" + puntos_ganados);
                setResult(MapsActivity.REQUEST_CODE_EDIFICIO, intent);
                //Regresar al activity anterior
                finish();
            }
        });

        btn_edificio_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MapsActivity.PUNTOS_OBTENIDOS, "" + 0);
                setResult(MapsActivity.REQUEST_CODE_EDIFICIO, intent);
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
        setResult(MapsActivity.REQUEST_CODE_EDIFICIO, intent);
        //Regresar al activity anterior
        finish();
    }
}
