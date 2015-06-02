package com.soft.grecio.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.soft.grecio.reader.model.Lectura;

import java.util.Date;


public class lectura extends Activity {
    DataBase db;
    int idVivienda=0;
    EditText mObservacion;
    EditText mConsumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura);
        db = new DataBase(this);
        Bundle b = getIntent().getExtras();
        idVivienda = b.getInt("idVivienda");

        mObservacion   = (EditText)findViewById(R.id.txtObservacion);
        mConsumo   = (EditText)findViewById(R.id.txtConsumo);
        findViewById(R.id.btnAceptar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lectura lectura = new Lectura();
                lectura.setIdVivienda(idVivienda);
                lectura.setFecha(new Date());
                lectura.setConsumo(Integer.parseInt(mConsumo.getText().toString()));
                lectura.setCodigoObservacion(2);
                lectura.setObservacion(mObservacion.getText().toString());
                db.addLectura(lectura);
                Toast.makeText(getApplicationContext(), "Guardado con exito", Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(lectura.this, RelevaMedidor.class));
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lectura, menu);
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
