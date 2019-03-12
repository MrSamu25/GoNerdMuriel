package com.appmoviles.muriel.practico_1;

import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class GeneradorPreguntas {


    public GeneradorPreguntas() {

    }

    public final static int DIFICIL = 1;

    public final static int FACIL = 0;


    public final static String SUMA = "+";

    public final static String RESTA = "-";

    public final static String MULTIPLICACION = "*";

    public final static String DIVISION = "/";


    //FALTARÍA PONER CONSTANTES RESPECTO AL ORDEN QUE OCUPAN LOS ELEMENTOS QUE CONFORMAN A LA PREGUNTA, OPERANDO 1, 2 Y OPERADOR
    //LO NOTÉ TARDE


    //CONSTANTES RESPECTO A A POSICIÓN QUE OCUPAN LAS RESPUESTAS (BUENAS Y MALAS) EN UNA LISTA
    public final static int RESPUESTA_BUENA = 0;

    public final static int RESPUESTA_INCORRECTA_1 = 1;

    public final static int RESPUESTA_INCORRECTA_2 = 2;

    public final static int RESPUESTA_INCORRECTA_3 = 3;

    public List<String> construirPregunta(int dificultad) {

        List<String> pregunta = new ArrayList<String>();

        int operando1 = -1;

        int operando2 = -1;


        if (dificultad == FACIL) {

            operando1 = (int) (Math.random() * 150 + 1);

            operando2 = (int) (Math.random() * 150 + 1);


            //OJO EL ORDEN IMPORTA
            pregunta.add(operando1 + "");

            pregunta.add(darOperación(FACIL));

            pregunta.add(operando2 + "");


        } else if (dificultad == DIFICIL) {

            operando1 = (int) (Math.random() * 80 + 1);

            operando2 = (int) (Math.random() * 15 + 1);

            //OJO EL ORDEN IMPORTA
            pregunta.add(operando1 + "");

            pregunta.add(darOperación(DIFICIL));

            pregunta.add(operando2 + "");
        }

        return pregunta;
    }

    private String darOperación(int dificultad) {

        String operacion = "";

        int posicion = -1;

        if (dificultad == FACIL) {

            posicion = (int) (Math.random() * 2 + 0);


        } else if (dificultad == DIFICIL) {

            posicion = (int) (Math.random() * 2 + 2);

        }

        if (posicion == 0) {
            operacion = SUMA;
        } else if (posicion == 1) {
            operacion = RESTA;
        } else if (posicion == 2) {
            operacion = MULTIPLICACION;
        } else {
            operacion = DIVISION;
        }

        return operacion;
    }


    public List<Integer> darRespuestas(List<String> pregunta) {

        List<Integer> respuestas = new ArrayList<Integer>();

        int resultado = -1;

        //OJO EL ORDEN IMPORTA

        int operando_1 = Integer.parseInt(pregunta.get(0));

        String operador = pregunta.get(1);

        int operando_2 = Integer.parseInt(pregunta.get(2));


        switch (operador) {

            case SUMA:
                resultado = operando_1 + operando_2;
                break;

            case RESTA:
                resultado = operando_1 - operando_2;
                break;

            case MULTIPLICACION:
                resultado = operando_1 * operando_2;
                break;

            case DIVISION:
                resultado = operando_1 / operando_2;
                break;
        }

        respuestas.add(RESPUESTA_BUENA, resultado);

        int variabilidad = (int)(Math.random() * 20 + 1);

        respuestas.add(RESPUESTA_INCORRECTA_1, (resultado + variabilidad));

        variabilidad = (int)(Math.random() * 20 + 1);

        respuestas.add(RESPUESTA_INCORRECTA_2, (resultado - variabilidad));

        variabilidad = (int)(Math.random() * 10 + 1);

        respuestas.add(RESPUESTA_INCORRECTA_3, (resultado + variabilidad));


        return respuestas;
    }

}
