package com.soft.grecio.reader;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
    ListView list;
    String[] itemname ={
            "Rutas",
            "Medidor",
            "Leidos",
            "No Leidos",
            "Sincronizar",
            "Acerca de",
            "Salir"
    };
    String[] description ={
            "Rutas disponibles para lectura",
            "Busca por número de medidor",
            "Cantidad de medidores leidos",
            "Cantidad de medidores No Leidos",
            "Sincronizar los datos leidos",
            "Acerca de",
            "Finalizar la aplicación"
    };

    Integer[] imgid={
            R.mipmap.ic_ruta,
            R.mipmap.ic_medidor,
            R.mipmap.ic_leidos,
            R.mipmap.ic_launcher,
            R.mipmap.ic_sincronizar,
            R.mipmap.ic_water,
            R.mipmap.ic_logout
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid, description);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Selecteditem= itemname[+position];
                switch (Selecteditem){
                    case "Salir":
                        finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                        break;
                    case "Rutas":
                        startActivity(new Intent(MainActivity.this, Rutas.class));
                        break;
                    case "Medidor":
                        Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case "Leidos":
                        startActivity(new Intent(MainActivity.this, Leidos.class));
                        break;
                    case "No Leidos":
                        Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case "Sincronizar":
                        startActivity(new Intent(MainActivity.this, Sincronizar.class));
                        break;
                    case "Acerca de":
                        Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();
                        break;
                }



            }
        });
    }
}
