package com.soft.grecio.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.soft.grecio.reader.model.Ruta;

import java.util.List;


public class Rutas extends Activity {

   // String rutaSelected="00";
    String[] arrayRutaCodigo;
    String[] arrayRutaDescripcion;
    List<Ruta> list;
    int rutaId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);

        DataBase db = new DataBase(this);
        ListView lista;
        // get all rutas
        list = db.getAllRutas();
        Integer[] imgid=new Integer[list.size()];
        arrayRutaCodigo = new String[list.size()];
        arrayRutaDescripcion = new String[list.size()];
        int index = 0;
        for (Ruta value : list) {
            arrayRutaCodigo[index] = (String) value.getCodigo();
            arrayRutaDescripcion[index] = (String) value.getDescripcion();
            imgid[index]=  R.mipmap.ic_ruta;
            index++;
        }

        //cargar lista con rutas....groso;
        CustomListAdapter adapter=new CustomListAdapter(this, arrayRutaCodigo, imgid, arrayRutaDescripcion);
        lista=(ListView)findViewById(R.id.listRutas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Selecteditem = arrayRutaCodigo[+position];

                for (Ruta value : list) {
                  if (value.getCodigo()== Selecteditem)
                  {
                      rutaId = value.getId();
                      break;
                  }
                }
                //rutaSelected=Selecteditem;
                Intent intent = new Intent(Rutas.this, RelevaMedidor.class);
                Bundle b = new Bundle();
                b.putInt("ruta", rutaId);
                intent.putExtras(b);
                startActivity(intent);


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rutas, menu);
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
