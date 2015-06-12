package com.soft.grecio.reader;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import com.soft.grecio.reader.model.Lectura;
import android.support.v4.app.FragmentTabHost;

import java.util.List;

@SuppressWarnings("deprecation")
public class Leidos extends FragmentActivity {

    private FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_leidos);

        //creamos el contenedor de Tabs
     /*   TabHost host = getTabHost();
        //Añadimos cada tab, que al ser pulsadas abren sus respectivas Activities
        TabHost.TabSpec photospec = host.newTabSpec("Buscar");
        // setting Title and Icon for the Tab
        photospec.setIndicator("Buscar2", getResources().getDrawable(R.drawable.icon_search_tab));
        Intent photosIntent = new Intent(this, LeidosBuscar.class);
        photospec.setContent(photosIntent);
        // Adding all TabSpec to TabHost
        host.addTab(photospec); // Adding photos tab


        TabHost.TabSpec spec2=host.newTabSpec("mitab2");
        spec2=host.newTabSpec("mitab2");
        spec2.setIndicator("TAB2",getResources().getDrawable(R.drawable.icon_read_tab));
        Intent sssIntent2 = new Intent(this, LeidosMostrar.class);
        spec2.setContent(sssIntent2 );
        host.addTab(spec2);*/


       // host.addTab(host.newTabSpec("Buscar").setIndicator("Buscar", getResources().getDrawable(R.drawable.icon_search_tab)).setContent(new Intent(this, LeidosBuscar.class)));
       // host.addTab(host.newTabSpec("Navegar").setIndicator("Navegar2",getResources().getDrawable(R.drawable.icon_read_tab)).setContent(new Intent(this, LeidosMostrar.class)));
       // host.setCurrentTab(0);
        tabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,
                getSupportFragmentManager(), android.R.id.tabcontent);
        View tabIndicatorLeidos = LayoutInflater.from(this).inflate(R.layout.tab_indicator,tabHost.getTabWidget(), false);
        View tabIndicatorBuscar = LayoutInflater.from(this).inflate(R.layout.tab_indicator_buscar,tabHost.getTabWidget(), false);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(tabIndicatorLeidos),
                LeidosMostrar.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(tabIndicatorBuscar),
                 LeidosBuscar.class, null);


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
