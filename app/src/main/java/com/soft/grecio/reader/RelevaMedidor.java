package com.soft.grecio.reader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.soft.grecio.reader.model.Observacion;
import com.soft.grecio.reader.model.Ruta;
import com.soft.grecio.reader.model.Vivienda;

import java.net.URI;
import java.util.List;


public class RelevaMedidor extends Activity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imVCature_pic;
    int posicion = 0;
    String[]  listv;
    String[]  listk;
    List<Vivienda> listViviendas;
    int idRuta=0;
    // create an instance of ListSelectorDialog.
    ListSelectorDialog dlg = new ListSelectorDialog(this, "Selecciona una codigo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_releva_medidor);
        if(idRuta == 0) {
            Bundle b = getIntent().getExtras();
            idRuta = b.getInt("ruta");
        }
        DataBase db = new DataBase(this);

        // get all Viviendas
        listViviendas = db.getAllViviendasByRuta(idRuta);
        if(listViviendas.size()>0) {
            TextView tvDireccion = (TextView) findViewById(R.id.lblDireccion);
            tvDireccion.setText("Calle: " + listViviendas.get(0).getDomicilio());
            TextView tvMedidor = (TextView) findViewById(R.id.lblNroMedidor);
            tvMedidor.setText("Medidor: " + listViviendas.get(0).getMedidor());
            TextView tvUltimaLectura = (TextView) findViewById(R.id.lblUltimaLectura);
            tvUltimaLectura.setText("\u00daltima lectura: " + listViviendas.get(0).getUltimaLectura());
        }
        // get all Observaciones
        List<Observacion> listaObservaciones = db.getAllObservaciones();


        // create our arrays of keys and values to send to the dialog.

        listv = new String[listaObservaciones.size() ];
        listk = new String[listaObservaciones.size() ];

        int index = 0;

        for(Observacion value: listaObservaciones) {
            listv[index] =  String.valueOf(value.getCodigo()) + " - " + value.getDescripcion();
            listk[index] = String.valueOf(value.getCodigo());
            index++;
        }

        findViewById(R.id.btnLectura).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(RelevaMedidor.this, lectura.class));
                Intent intent = new Intent(RelevaMedidor.this, lectura.class);
                Bundle b = new Bundle();
                b.putInt("idVivienda", listViviendas.get(posicion).getId());
                intent.putExtras(b);
                startActivity(intent);
                //finish();

            }
        });
        findViewById(R.id.btnTomarImagen ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        findViewById(R.id.btnAnterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(RelevaMedidor.this, Rutas.class));
                if(posicion > 0) {
                    posicion--;
                    TextView tvDireccion = (TextView) findViewById(R.id.lblDireccion);
                    tvDireccion.setText("Calle: " + listViviendas.get(posicion).getDomicilio());
                    TextView tvMedidor = (TextView) findViewById(R.id.lblNroMedidor);
                    tvMedidor.setText("Medidor: " + listViviendas.get(posicion).getMedidor());
                    TextView tvUltimaLectura = (TextView) findViewById(R.id.lblUltimaLectura);
                    tvUltimaLectura.setText("\u00daltima lectura: " + listViviendas.get(posicion).getUltimaLectura());
                }
            }
        });
        findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listViviendas.size() - 1 > posicion) {
                    posicion++;
                    TextView tvDireccion = (TextView) findViewById(R.id.lblDireccion);
                    tvDireccion.setText("Calle: " + listViviendas.get(posicion).getDomicilio());
                    TextView tvMedidor = (TextView) findViewById(R.id.lblNroMedidor);
                    tvMedidor.setText("Medidor: " + listViviendas.get(posicion).getMedidor());
                    TextView tvUltimaLectura = (TextView) findViewById(R.id.lblUltimaLectura);
                    tvUltimaLectura.setText("\u00daltima lectura: " + listViviendas.get(posicion).getUltimaLectura());
                }
            }
        });

        findViewById(R.id.codigoObservacion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //RelevaMedidor.this.openOptionsMenu();
                dlg.show(listv, listk, new ListSelectorDialog.listSelectorInterface() {
                    // procedure if user cancels the dialog.
                    public void selectorCanceled() {
                        Toast.makeText(getApplicationContext(),
                                "Cancelado por el usuario", Toast.LENGTH_SHORT).show();
                    }

                    // procedure for when a user selects an item in the dialog.
                    public void selectedItem(String key, String item) {
                        //Toast.makeText(getApplicationContext(), "User Has selected item '" + item + "' having key '" + key + "'!", Toast.LENGTH_SHORT).show();
                        TextView tv1 = (TextView)findViewById(R.id.lblCodigoObservacion);
                        tv1.setText("C\u00f3digo de observaci\u00f3n: "  +  item);
                    }
                });


            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imVCature_pic=(ImageView) findViewById(R.id.mImageView);
            imVCature_pic.setImageBitmap(imageBitmap);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_releva_medidor, menu);
        return true;
    }

}
