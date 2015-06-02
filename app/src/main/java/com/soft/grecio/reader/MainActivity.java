package com.soft.grecio.reader;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
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

import com.soft.grecio.reader.model.Ruta;

import java.io.IOException;


public class MainActivity extends Activity {
    ListView list;
    String[] itemname ={
            "Rutas",
            "Medidor",
            "Leidos",
            "No Leidos",
            "BackUps",
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
            R.mipmap.ic_watermeter,
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
        DataBase myDbHelper = new DataBase(this);

        /**
         * CRUD Operations
         * */
        // add Rutas
       /* myDbHelper.addRuta(new Ruta("001", "Libertador y Balcarce","Sta Lucia"));
        myDbHelper.addRuta(new Ruta("002", "Roque Saenz Peña y Balcarce","Sta Lucia"));
        myDbHelper.addRuta(new Ruta("003", "Loteo privado","Sta Lucia"));
        myDbHelper.addRuta(new Ruta("004", "Del Bono","Desamparados"));
        myDbHelper.addRuta(new Ruta("005", "sierras azules","zonda"));
        myDbHelper.addRuta(new Ruta("006", "huaco","jachal"));*/

    /*    try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }*/

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
                    case "BackUps":
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
