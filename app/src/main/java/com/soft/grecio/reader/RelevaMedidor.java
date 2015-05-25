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
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;


public class RelevaMedidor extends Activity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imVCature_pic;

    // create an instance of ListSelectorDialog.
   ListSelectorDialog dlg = new ListSelectorDialog(this, "Select an Operator");
    // create our arrays of keys and values to send to the dialog.
    String[] listk = new String[] {"00", "01", "02", "03", "04", "05"};

    String[] listv = new String[] {"00 Sin incidencia", "01 Med. en cero", "02 Med. roto sin lectura", "03 Med. detenido", "04 Med. roto c/lectura","05 Med. sucio c/lectura"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_releva_medidor);
        findViewById(R.id.btnLectura).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelevaMedidor.this, lectura.class));
                //startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:2644744344")));
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
                startActivity(new Intent(RelevaMedidor.this, Rutas.class));
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
                                "User Canceled the request!", Toast.LENGTH_SHORT).show();
                    }

                    // procedure for when a user selects an item in the dialog.
                    public void selectedItem(String key, String item) {
                        //Toast.makeText(getApplicationContext(), "User Has selected item '" + item + "' having key '" + key + "'!", Toast.LENGTH_SHORT).show();
                        TextView tv1 = (TextView)findViewById(R.id.lblCodigoObservacion);
                        tv1.setText("Código de observacion: "  +  item);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.w("ANDROID MENU TUTORIAL:", "onOptionsItemSelected(MenuItem item)");

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cero:
                Toast.makeText(this, "Clicked: Menu No. 0", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.uno:
                Toast.makeText(this, "Clicked: Menu No. 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.dos:
                Toast.makeText(this, "Clicked: Menu No. 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.tres:
                Toast.makeText(this, "Clicked: Menu No. 3", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cuatro:
                Toast.makeText(this, "Clicked: Menu No. 4", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cinco:
                Toast.makeText(this, "Clicked: Menu No. 5", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
