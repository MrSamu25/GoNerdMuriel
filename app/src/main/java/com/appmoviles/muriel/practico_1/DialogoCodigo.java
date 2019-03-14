package com.appmoviles.muriel.practico_1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogoCodigo extends DialogFragment {


    private TextView tv_dialogo_item;

    private ImageView iv_dialogo_codigo;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.activity_dialogo_codigo, null);

        tv_dialogo_item = v.findViewById(R.id.tv_dialogo_item);

        iv_dialogo_codigo = v.findViewById(R.id.iv_dialogo_codigo);

        String nombre_producto = "";
        String codigo_qr = "";
        Bitmap qr = null;

        if (this.getArguments() != null) {
            nombre_producto = (String) getArguments().get(Biblioteca.NOMBRE_PRODUCTO);
            codigo_qr = (String) getArguments().get(Biblioteca.CODIGO_QR);
            qr = (Bitmap) getArguments().getParcelable(Biblioteca.QR);

        }

        tv_dialogo_item.setText(nombre_producto);

        //CUANDO TENÍA LOS QR QUEMADOS
        //setearImagen(codigo_qr);

        iv_dialogo_codigo.setImageBitmap(qr);

        builder.setView(v).setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DialogoCodigo.this.getDialog().cancel();
            }
        });


        return builder.create();
    }

    public void setearImagen(String codigo_qr) {

        if (codigo_qr.equals(Biblioteca.QR_LAPICERO)) {
            iv_dialogo_codigo.setImageResource(R.drawable.qr_lapicero);

        } else if (codigo_qr.equals(Biblioteca.QR_CUADERNO)) {
            iv_dialogo_codigo.setImageResource(R.drawable.qr_cuaderno);

        } else if (codigo_qr.equals(Biblioteca.QR_LIBRETA)) {
            iv_dialogo_codigo.setImageResource(R.drawable.qr_libreta);

        } else if (codigo_qr.equals(Biblioteca.QR_CAMISA)) {
            iv_dialogo_codigo.setImageResource(R.drawable.qr_camisa);

        } else if (codigo_qr.equals(Biblioteca.QR_SACO)) {
            iv_dialogo_codigo.setImageResource(R.drawable.qr_saco);
        }
    }
}
