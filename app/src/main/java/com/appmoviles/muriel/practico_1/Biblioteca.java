package com.appmoviles.muriel.practico_1;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Biblioteca extends AppCompatActivity {


    private TextView tv_bb_puntos_totales;

    private int puntos_totales;

    private RadioButton rb_bb_lapicero;

    private RadioButton rb_bb_cuaderno;

    private RadioButton rb_bb_libreta;

    private RadioButton rb_bb_camisa;

    private RadioButton rb_bb_saco;

    private Button btn_bb_canjear;

    private Button btn_bb_atras;

    public final static int PUNTOS_LAPICERO = 20;

    public final static int PUNTOS_CUADERNO = 30;

    public final static int PUNTOS_LIBRETA = 40;

    public final static int PUNTOS_CAMISA = 80;

    public final static int PUNTOS_SACO = 100;

    public final static String NOMBRE_PRODUCTO = "nombre del producto";

    public final static String CODIGO_QR = "codigo qr";


    public final static String LAPICERO = "Lapicero";

    public final static String CUADERNO = "Cuaderno";

    public final static String LIBRETA = "Libreta";

    public final static String CAMISA = "Camisa";

    public final static String SACO = "Saco";

    public final static String QR_LAPICERO = "qr_lapicero";

    public final static String QR_CUADERNO = "qr_cuaderno";

    public final static String QR_LIBRETA = "qr_libreta";

    public final static String QR_CAMISA = "qr_camisa";

    public final static String QR_SACO = "qr_saco";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca);

        //Se buscan los componentes

        tv_bb_puntos_totales = findViewById(R.id.tv_bb_puntos_totales);
        rb_bb_lapicero = findViewById(R.id.rb_bb_lapicero);
        rb_bb_cuaderno = findViewById(R.id.rb_bb_cuaderno);
        rb_bb_libreta = findViewById(R.id.rb_bb_libreta);
        rb_bb_camisa = findViewById(R.id.rb_bb_camisa);
        rb_bb_saco = findViewById(R.id.rb_bb_saco);
        btn_bb_canjear = findViewById(R.id.btn_bb_canjear);
        btn_bb_atras = findViewById(R.id.btn_bb_atras);

        puntos_totales = 0;

        puntos_totales = Integer.parseInt(getIntent().getStringExtra(MapsActivity.PUNTOS_TOTALES));

        tv_bb_puntos_totales.setText("" + puntos_totales);

        btn_bb_canjear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb_bb_lapicero.isChecked()) {

                    if (puntos_totales >= PUNTOS_LAPICERO) {
                        Toast.makeText(Biblioteca.this, "CANJEÓ UN LAPICERO CON ÉXITO", Toast.LENGTH_SHORT).show();
                        puntos_totales -= PUNTOS_LAPICERO;
                        mostrarDialogoCodigo(LAPICERO, QR_LAPICERO);
                    } else {
                        Toast.makeText(Biblioteca.this, "NO TIENE LOS PUNTOS SUFICIENTES", Toast.LENGTH_SHORT).show();
                    }

                } else if (rb_bb_cuaderno.isChecked()) {

                    if (puntos_totales >= PUNTOS_CUADERNO) {
                        Toast.makeText(Biblioteca.this, "CANJEÓ UN CUADERNO CON ÉXITO", Toast.LENGTH_SHORT).show();
                        puntos_totales -= PUNTOS_CUADERNO;
                        mostrarDialogoCodigo(CUADERNO, QR_CUADERNO);
                    } else {
                        Toast.makeText(Biblioteca.this, "NO TIENE LOS PUNTOS SUFICIENTES", Toast.LENGTH_SHORT).show();
                    }

                } else if (rb_bb_libreta.isChecked()) {

                    if (puntos_totales >= PUNTOS_LIBRETA) {
                        Toast.makeText(Biblioteca.this, "CANJEÓ UNA LIBRETA CON ÉXITO", Toast.LENGTH_SHORT).show();
                        puntos_totales -= PUNTOS_LIBRETA;
                        mostrarDialogoCodigo(LIBRETA, QR_LIBRETA);
                    } else {
                        Toast.makeText(Biblioteca.this, "NO TIENE LOS PUNTOS SUFICIENTES", Toast.LENGTH_SHORT).show();
                    }

                } else if (rb_bb_camisa.isChecked()) {

                    if (puntos_totales >= PUNTOS_CAMISA) {
                        Toast.makeText(Biblioteca.this, "CANJEÓ UNA CAMISA CON ÉXITO", Toast.LENGTH_SHORT).show();
                        puntos_totales -= PUNTOS_CAMISA;
                        mostrarDialogoCodigo(CAMISA, QR_CAMISA);
                    } else {
                        Toast.makeText(Biblioteca.this, "NO TIENE LOS PUNTOS SUFICIENTES", Toast.LENGTH_SHORT).show();
                    }

                } else if (rb_bb_saco.isChecked()) {

                    if (puntos_totales >= PUNTOS_SACO) {
                        Toast.makeText(Biblioteca.this, "CANJEÓ UN SACO CON ÉXITO", Toast.LENGTH_SHORT).show();
                        puntos_totales -= PUNTOS_SACO;
                        mostrarDialogoCodigo(SACO, QR_SACO);
                    } else {
                        Toast.makeText(Biblioteca.this, "NO TIENE LOS PUNTOS SUFICIENTES", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Biblioteca.this, "DEBE SELECCIONAR UN ARTÍCULO", Toast.LENGTH_SHORT).show();
                }

                tv_bb_puntos_totales.setText("" + puntos_totales);



            }
        });

        btn_bb_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MapsActivity.PUNTOS_OBTENIDOS, "" + puntos_totales);
                setResult(MapsActivity.REQUEST_CODE_BIBLIOTECA, intent);
                //Regresar al activity anterior
                finish();
            }
        });
    }

    public void mostrarDialogoCodigo(String nombre_producto, String codigo_qr) {

        DialogFragment newFragment = new DialogoCodigo();
        Bundle bundle = new Bundle();
        bundle.putString(NOMBRE_PRODUCTO, nombre_producto);
        bundle.putString(CODIGO_QR, codigo_qr);
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "DialogoCodigo");

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra(MapsActivity.PUNTOS_OBTENIDOS, "" + puntos_totales);
        setResult(MapsActivity.REQUEST_CODE_BIBLIOTECA, intent);
        //Regresar al activity anterior
        finish();
    }
}
