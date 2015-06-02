package com.soft.grecio.reader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.soft.grecio.reader.model.Lectura;

import java.util.List;


public class Leidos extends Activity {

    int posicion = 0;
    List<Lectura> lecturas;
    public enum Movimiento {
        NINGUNO, ADELANTE,ATRAS
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leidos);

        DataBase db = new DataBase(this);

        // get all lecturas
        lecturas = db.getAllLecturas();
        if(lecturas.size()>0) {
            moverLectura(Movimiento.NINGUNO);


        }

        findViewById(R.id.btnAnterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startActivity(new Intent(RelevaMedidor.this, Rutas.class));
                if(posicion > 0) {
                    moverLectura(Movimiento.ATRAS);
                }
            }
        });
        findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(lecturas.size() - 1 > posicion) {
                    moverLectura(Movimiento.ADELANTE);
                }
            }
        });

    }

    private void moverLectura(Movimiento movimiento)
    {
        switch (movimiento) {
            case ADELANTE:
                    posicion++;
                    break;

            case ATRAS:
                    posicion--;
                    break;
            case NINGUNO:
                    posicion=0;
                    break;
        }


                TextView lblRuta = (TextView) findViewById(R.id.lblRuta);
                lblRuta.setText("Ruta: ");
                TextView tvVivienda = (TextView) findViewById(R.id.lblVivienda);
                tvVivienda.setText("Vivienda: " + lecturas.get(posicion).getIdVivienda());
                TextView tvDireccion = (TextView) findViewById(R.id.lblDireccion);
                tvDireccion.setText("Direccion: " + lecturas.get(posicion).getIdVivienda());

                TextView tvMedidor = (TextView) findViewById(R.id.lblMedidor);
                tvMedidor.setText("Medidor: " + lecturas.get(posicion).getIdVivienda());

                TextView tvConsumo = (TextView) findViewById(R.id.lblConsumo);
                tvConsumo.setText("Consumo: " + lecturas.get(posicion).getConsumo());

                TextView tvFecha = (TextView) findViewById(R.id.lblFecha);
                tvFecha.setText("Fecha: " + lecturas.get(posicion).getFecha());

                TextView tvObservacion = (TextView) findViewById(R.id.lblObservaciones);
                tvObservacion.setText("Observacion: " + lecturas.get(posicion).getObservacion());

                TextView tvCodigoObservacion = (TextView) findViewById(R.id.lblCodigoObservacion);
                tvCodigoObservacion.setText("Codigo Observacion: " + lecturas.get(posicion).getCodigoObservacion());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leidos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
