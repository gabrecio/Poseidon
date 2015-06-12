package com.soft.grecio.reader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.soft.grecio.reader.model.Lectura;

import java.util.List;


public class LeidosMostrar extends Fragment {
    TextView lblRuta;
    TextView tvVivienda;
    TextView tvDireccion;
    TextView tvMedidor;
    TextView tvConsumo;
    TextView tvFecha;
    TextView tvObservacion;
    TextView tvCodigoObservacion;
    Button btnAnterior;
    Button btnSiguiente;

    int posicion = 0;
    List<Lectura> lecturas;
    public enum Movimiento {
        NINGUNO, ADELANTE,ATRAS
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_leidos_mostrar, container, false);
        lblRuta = (TextView)  rootView.findViewById(R.id.lblRuta);
        tvVivienda = (TextView) rootView.findViewById(R.id.lblVivienda);
        tvDireccion = (TextView)  rootView.findViewById(R.id.lblDireccion);
        tvMedidor = (TextView)  rootView.findViewById(R.id.lblMedidor);
        tvConsumo = (TextView)  rootView.findViewById(R.id.lblConsumo);
        tvFecha = (TextView)  rootView.findViewById(R.id.lblFecha);
        tvObservacion = (TextView)  rootView.findViewById(R.id.lblObservaciones);
        tvCodigoObservacion = (TextView)  rootView.findViewById(R.id.lblCodigoObservacion);
        btnAnterior  = (Button)rootView.findViewById(R.id.btnAnterior);
        btnSiguiente  = (Button)rootView.findViewById(R.id.btnSiguiente);


        DataBase db = new DataBase( getActivity());
        // get all lecturas
        lecturas = db.getAllLecturas();
        if(lecturas.size()>0) {
            moverLectura(Movimiento.NINGUNO);
        }
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_leidos_mostrar);
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
        lblRuta.setText("Ruta: ");

        tvVivienda.setText("Vivienda: " + lecturas.get(posicion).getIdVivienda());

        tvDireccion.setText("Direccion: " + lecturas.get(posicion).getIdVivienda());

        tvMedidor.setText("Medidor: " + lecturas.get(posicion).getIdVivienda());

        tvConsumo.setText("Consumo: " + lecturas.get(posicion).getConsumo());

        tvFecha.setText("Fecha: " + lecturas.get(posicion).getFecha());

        tvObservacion.setText("Observacion: " + lecturas.get(posicion).getObservacion());

        tvCodigoObservacion.setText("Codigo Observacion: " + lecturas.get(posicion).getCodigoObservacion());

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        this.getActivity();
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posicion > 0) {
                    moverLectura(Movimiento.ATRAS);
                }
            }
        });
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lecturas.size() - 1 > posicion) {
                    moverLectura(Movimiento.ADELANTE);
                }
            }
        });
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
