package com.appmoviles.muriel.practico_1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE = 11;

    private GoogleMap mMap;

    private LocationManager manager;

    private Marker marcadorMiPosicion;

    //Para biblioteca
    private LatLng latLng1;

    private LatLng latLng2;

    private LatLng latLng3;

    private LatLng latLng4;

    //Para edificio M
    private LatLng latLng5;

    private LatLng latLng6;

    private LatLng latLng7;

    private LatLng latLng8;

    //Para la Cafetería
    private LatLng latLng9;

    private LatLng latLng10;

    private LatLng latLng11;

    private LatLng latLng12;

    private final static int BIBLIOTECA = 20;

    private final static int EDIFICIO_M = 10;

    private final static int CAFETERIA = 15;

    private Button btn_biblioteca;

    private Button btn_edificio_M;

    private Button btn_cafeteria;

    private Switch sw_maps_lugares;

    //Relaciones con el generador
    private GeneradorPreguntas generadorPreguntas;
    List<String> pregunta;

    List<Integer> respuestas;

    public final static String OPERADOR = "operador";

    public final static String OPERANDO_1 = "operando 1";

    public final static String OPERANDO_2 = "operando 2";

    public final static String RESPUESTA_BUENA = "respuesta buena";

    public final static String RESPUESTA_INCORRECTA_1 = "respuesta incorrecta 1";

    public final static String RESPUESTA_INCORRECTA_2 = "respuesta incorrecta 2";

    public final static String RESPUESTA_INCORRECTA_3 = "respuesta incorrecta 3";

    public final static String PUNTOS_TOTALES = "puntos totales";

    public final static String PUNTOS_OBTENIDOS = "puntos obtenidos";

    private int puntos_totales;

    private TextView tv_maps_puntos_totales;

    //startActivityForResult

    public final static int REQUEST_CODE_EDIFICIO = 1;

    public final static int REQUEST_CODE_CAFETERIA = 2;

    public final static int REQUEST_CODE_BIBLIOTECA = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Puntos con los que inica un jugador
        puntos_totales = 0;

        //Generador de preguntas
        generadorPreguntas = new GeneradorPreguntas();
        pregunta = null;
        respuestas = null;

        //MANAGER DE CONEXIÓN
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        btn_biblioteca = findViewById(R.id.btn_biblioteca);

        btn_edificio_M = findViewById(R.id.btn_edificio_M);

        btn_cafeteria = findViewById((R.id.btn_cafeteria));

        tv_maps_puntos_totales = findViewById((R.id.tv_maps_puntos_totales));

        sw_maps_lugares = findViewById(R.id.sw_maps_lugares);

        sw_maps_lugares.setEnabled(false);

        btn_edificio_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity.this, Edificio.class);

                pregunta = generadorPreguntas.construirPregunta(GeneradorPreguntas.FACIL);
                respuestas = generadorPreguntas.darRespuestas(pregunta);

                i.putExtra(OPERANDO_1, pregunta.get(0));
                i.putExtra(OPERADOR, pregunta.get(1));
                i.putExtra(OPERANDO_2, pregunta.get(2));
                //DEBE TENER EL "" SI NO, NO PASA LA INFO :S
                i.putExtra(RESPUESTA_BUENA, "" + respuestas.get(0));
                i.putExtra(RESPUESTA_INCORRECTA_1, "" + respuestas.get(1));
                i.putExtra(RESPUESTA_INCORRECTA_2, "" + respuestas.get(2));
                i.putExtra(RESPUESTA_INCORRECTA_3, "" + respuestas.get(3));

                startActivityForResult(i, REQUEST_CODE_EDIFICIO);
            }
        });


        btn_biblioteca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity.this, Biblioteca.class);

                //DEBE TENER EL "" SI NO, NO PASA LA INFO :S
                i.putExtra(PUNTOS_TOTALES, "" + puntos_totales);

                startActivityForResult(i, REQUEST_CODE_BIBLIOTECA);
            }
        });

        btn_cafeteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity.this, Cafeteria.class);

                pregunta = generadorPreguntas.construirPregunta(GeneradorPreguntas.DIFICIL);
                respuestas = generadorPreguntas.darRespuestas(pregunta);

                i.putExtra(OPERANDO_1, pregunta.get(0));
                i.putExtra(OPERADOR, pregunta.get(1));
                i.putExtra(OPERANDO_2, pregunta.get(2));
                //DEBE TENER EL "" SI NO, NO PASA LA INFO :S
                i.putExtra(RESPUESTA_BUENA, "" + respuestas.get(0));
                i.putExtra(RESPUESTA_INCORRECTA_1, "" + respuestas.get(1));
                i.putExtra(RESPUESTA_INCORRECTA_2, "" + respuestas.get(2));
                i.putExtra(RESPUESTA_INCORRECTA_3, "" + respuestas.get(3));

                startActivityForResult(i, REQUEST_CODE_CAFETERIA);
            }
        });

        sw_maps_lugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llama al método encargado de activar o desactivar los botones teniendo en cuenta el switch
                verificarBotones();
            }
        });

        tv_maps_puntos_totales.setText("" + puntos_totales);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;


        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_CODE);

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


                if (marcadorMiPosicion != null) {
                    //Borra mi posición antigua
                    marcadorMiPosicion.remove();
                }

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title("Usted se encuentra aquí");

                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                markerOptions.position(latLng);

                //Icono
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("current_position_2", 130, 130)));

                marcadorMiPosicion = mMap.addMarker(markerOptions);

                //Toast.makeText(MapsActivity.this, "LAT: " + marcadorMiPosicion.getPosition().latitude + " LON: " + marcadorMiPosicion.getPosition().longitude, Toast.LENGTH_LONG).show();

                //Mueve la camara
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                //Para activar el swtich debe existir el marcadorMiPosición antes
                if(!sw_maps_lugares.isEnabled()){
                    Toast.makeText(MapsActivity.this, "Se activo el switch", Toast.LENGTH_SHORT);
                    sw_maps_lugares.setEnabled(true);
                }

                //Llama al método encargado de activar o desactivar los botones teniendo en cuenta el switch
                verificarBotones();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

        //Add a marker in Sydney and move the camera
        LatLng ICESI = new LatLng(3.341659, -76.530733);
        mMap.addMarker(new MarkerOptions().position(ICESI).title("Universidad ICESI"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ICESI));
        mMap.setMinZoomPreference(17);


        //PUNTO QUE ENCIERRAN A LA BIBLIOTECA
        latLng1 = new LatLng(3.341932, -76.530100);

        latLng2 = new LatLng(3.341932, -76.529800);

        latLng3 = new LatLng(3.341638, -76.529800);

        latLng4 = new LatLng(3.341638, -76.530100);


        //BIBLIOTECA
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng1.latitude, latLng1.longitude), new LatLng(latLng2.latitude, latLng2.longitude))
                .width(5)
                .color(Color.GREEN));


        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng2.latitude, latLng2.longitude), new LatLng(latLng3.latitude, latLng3.longitude))
                .width(5)
                .color(Color.GREEN));

        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng3.latitude, latLng3.longitude), new LatLng(latLng4.latitude, latLng4.longitude))
                .width(5)
                .color(Color.GREEN));

        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng4.latitude, latLng4.longitude), new LatLng(latLng1.latitude, latLng1.longitude))
                .width(5)
                .color(Color.GREEN));


        //PUNTO QUE ENCIERRAN A EL EDIFICIO M
        latLng5 = new LatLng(3.342827, -76.530508);

        latLng6 = new LatLng(3.342827, -76.530143);

        latLng7 = new LatLng(3.342484, -76.530143);

        latLng8 = new LatLng(3.342484, -76.530508);


        //EDIFICIO M
        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng5.latitude, latLng5.longitude), new LatLng(latLng6.latitude, latLng6.longitude))
                .width(5)
                .color(Color.BLUE));


        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng6.latitude, latLng6.longitude), new LatLng(latLng7.latitude, latLng7.longitude))
                .width(5)
                .color(Color.BLUE));

        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng7.latitude, latLng7.longitude), new LatLng(latLng8.latitude, latLng8.longitude))
                .width(5)
                .color(Color.BLUE));

        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng8.latitude, latLng8.longitude), new LatLng(latLng5.latitude, latLng5.longitude))
                .width(5)
                .color(Color.BLUE));


        //PUNTO QUE ENCIERRAN A LA CAFETERÍA
        latLng9 = new LatLng(3.342264, -76.529709);

        latLng10 = new LatLng(3.342264, -76.529473);

        latLng11 = new LatLng(3.341900, -76.529473);

        latLng12 = new LatLng(3.341900, -76.529709);


        //CAFETERÍA
        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng9.latitude, latLng9.longitude), new LatLng(latLng10.latitude, latLng10.longitude))
                .width(5)
                .color(Color.RED));


        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng10.latitude, latLng10.longitude), new LatLng(latLng11.latitude, latLng11.longitude))
                .width(5)
                .color(Color.RED));

        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng11.latitude, latLng11.longitude), new LatLng(latLng12.latitude, latLng12.longitude))
                .width(5)
                .color(Color.RED));

        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latLng12.latitude, latLng12.longitude), new LatLng(latLng9.latitude, latLng9.longitude))
                .width(5)
                .color(Color.RED));

    }

    //Llama al método encargado de activar o desactivar los botones teniendo en cuenta el switch
    public void verificarBotones() {

        if (verificarLugar(EDIFICIO_M)) {

            btn_edificio_M.setVisibility(View.VISIBLE);
            //Toast.makeText(MapsActivity.this, "ESTA EN EDIFICO M", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(MapsActivity.this, "NO ESTA EN EDIFICO M", Toast.LENGTH_SHORT).show();

            if (sw_maps_lugares.isChecked()) {
                btn_edificio_M.setVisibility(View.GONE);
            } else {
                btn_edificio_M.setVisibility(View.VISIBLE);
            }

        }

        if (verificarLugar(BIBLIOTECA)) {

            btn_biblioteca.setVisibility(View.VISIBLE);
            //Toast.makeText(MapsActivity.this, "ESTA EN BIBLIOTECA", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(MapsActivity.this, "NO ESTA EN BIBLIOTECA", Toast.LENGTH_SHORT).show();

            if (sw_maps_lugares.isChecked()) {
                btn_biblioteca.setVisibility(View.GONE);
            } else {
                btn_biblioteca.setVisibility(View.VISIBLE);
            }

        }

        if (verificarLugar(CAFETERIA)) {

            btn_cafeteria.setVisibility(View.VISIBLE);
            //Toast.makeText(MapsActivity.this, "ESTA EN LA CAFETERÍA CENTRAL", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(MapsActivity.this, "NO ESTA CAFETERÍA CENTRAL", Toast.LENGTH_SHORT).show();

            if (sw_maps_lugares.isChecked()) {
                btn_cafeteria.setVisibility(View.GONE);
            } else {
                btn_cafeteria.setVisibility(View.VISIBLE);
            }

        }
    }

    public boolean verificarLugar(int lugar) {

        boolean adentro = false;

        double miLatitud = marcadorMiPosicion.getPosition().latitude;

        double miLongitud = marcadorMiPosicion.getPosition().longitude;

        switch (lugar) {


            case BIBLIOTECA:
                //SI CUMPLE ESTÁ ADENTRO DE BIBLIOTECA
                if (miLatitud < latLng1.latitude && miLatitud > latLng3.latitude && miLongitud < latLng2.longitude && miLongitud > latLng4.longitude) {
                    adentro = true;
                    break;
                }
                break;

            case EDIFICIO_M:
                //SI CUMPLE ESTÁ ADENTRO DEL EDIFICO M
                if (miLatitud < latLng5.latitude && miLatitud > latLng7.latitude && miLongitud < latLng6.longitude && miLongitud > latLng8.longitude) {
                    adentro = true;
                    break;
                }
                break;

            case CAFETERIA:
                //SI CUMPLE ESTÁ ADENTRO DE LA CAFETERÍA
                if (miLatitud < latLng9.latitude && miLatitud > latLng11.latitude && miLongitud < latLng10.longitude && miLongitud > latLng12.longitude) {
                    adentro = true;
                    break;
                }
                break;
        }

        return adentro;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String puntos_obtenidos = "";

        if (requestCode == REQUEST_CODE_EDIFICIO) {

            puntos_obtenidos = data.getStringExtra(PUNTOS_OBTENIDOS);
            puntos_totales += Integer.parseInt(puntos_obtenidos);

        } else if (requestCode == REQUEST_CODE_CAFETERIA) {

            puntos_obtenidos = data.getStringExtra(PUNTOS_OBTENIDOS);
            puntos_totales += Integer.parseInt(puntos_obtenidos);

        } else if (requestCode == REQUEST_CODE_BIBLIOTECA) {

            puntos_obtenidos = data.getStringExtra(PUNTOS_OBTENIDOS);
            puntos_totales = Integer.parseInt(puntos_obtenidos);
        }

        tv_maps_puntos_totales.setText(puntos_totales + "");

    }

    //PARA AJUSTAR EL TAMAÑO DEL ICONO DEL MARCADOR MIO
    public Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

}
