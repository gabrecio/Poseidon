package com.soft.grecio.reader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.soft.grecio.reader.model.Lectura;
import com.soft.grecio.reader.model.Observacion;
import com.soft.grecio.reader.model.Ruta;
import com.soft.grecio.reader.model.Vivienda;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by grecio on 5/25/2015.
 */
public class DataBase extends SQLiteOpenHelper {
    //Database Version
    private static final int DATABASE_VERSION=1;
    //Database Name
    private static final String DATABASE_NAME="LecturaDB.db";
    private static final String DATABASE_FILE_PATH = "/android/data/";
    //Environment.DIRECTORY_DOCUMENTS;

    private SQLiteDatabase myDataBase;
   // private final Context myContext;

    public DataBase(Context context){
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
       // this.myContext =context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.i("DB PATH", db.getPath());
        //SQL statement to create table
        String Create_Rutas_Table = "CREATE TABLE rutas (" +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                " codigo TEXT NOT NULL UNIQUE," +
                " descripcion TEXT NOT NULL," +
                " zona TEXT NOT NULL )";

        //create rutas table
        db.execSQL(Create_Rutas_Table);


        String Create_Obseraciones_Table = " CREATE TABLE observaciones (" +
        "codigo INTEGER NOT NULL PRIMARY KEY UNIQUE," +
        "descripcion TEXT NOT NULL UNIQUE)";

        db.execSQL(Create_Obseraciones_Table);

        String Create_Vivienda_Table = " CREATE TABLE viviendas ( " +
        "id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
        "domicilio	TEXT NOT NULL UNIQUE," +
        "medidor TEXT NOT NULL UNIQUE," +
        "ultimaLectura	INTEGER," +
        "idRuta	INTEGER NOT NULL," +
        "FOREIGN KEY(idRuta) REFERENCES rutas ( id ))";

        db.execSQL(Create_Vivienda_Table);

        String Create_Lectura_Table = " CREATE TABLE lectura (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "idMedidor INTEGER NOT NULL UNIQUE," +
                "consumo INTEGER NOT NULL," +
                "fecha TEXT NOT NULL," +
                "codigoObservacion INTEGER," +
                "Observacion TEXT," +
                "FOREIGN KEY(codigoObservacion) REFERENCES observaciones ( codigo )" +
                ");";

        db.execSQL(Create_Lectura_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older rutas table if existed
        db.execSQL("DROP TABLE IF EXISTS rutas");

        // create fresh rutas table
        this.onCreate(db);
    }



    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
   /* public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }*/

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DATABASE_FILE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
   /* private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DATABASE_FILE_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }*/

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DATABASE_FILE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }


    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete)
     */

    // Rutas table name
    private static final String TABLE_RUTAS = "rutas";
    // Rutas Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CODIGO = "codigo";
    private static final String KEY_DESCRIPCION = "descripcion";
    private static final String KEY_ZONA = "zona";

    public void addRuta(Ruta ruta){
        Log.d("addRuta", ruta.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_CODIGO, ruta.getCodigo()); // get title
        values.put(KEY_DESCRIPCION, ruta.getDescripcion()); // get author
        values.put(KEY_ZONA, ruta.getZona()); // get author

        // 3. insert
        db.insert(TABLE_RUTAS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    // Get All Rutas
    public List<Ruta> getAllRutas() {
        List<Ruta> rutas = new LinkedList<Ruta>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_RUTAS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build ruta and add it to list
        Ruta ruta = null;
        if (cursor.moveToFirst()) {
            do {
                ruta = new Ruta();
                ruta.setId(Integer.parseInt(cursor.getString(0)));
                ruta.setCodigo(cursor.getString(1));
                ruta.setDescripcion(cursor.getString(2));
                ruta.setZona(cursor.getString(3));
                // Add ruta to rutas
                rutas.add(ruta);
            } while (cursor.moveToNext());
        }

        Log.d("getAllRutas()", rutas.toString());

        // return rutas
        return rutas;
    }

    // Get All Viviendas
    public List<Vivienda> getAllViviendasByRuta(int idRuta) {
        List<Vivienda> viviendas = new LinkedList<Vivienda>();

        // 1. build the query
        String query = "SELECT  * FROM Viviendas where idRuta = " +  String.valueOf(idRuta);

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build vivienda and add it to list
        Vivienda vivienda = null;
        if (cursor.moveToFirst()) {
            do {
                vivienda = new Vivienda();
                vivienda.setId(Integer.parseInt(cursor.getString(0)));
                vivienda.setDomicilio(cursor.getString(1));
                vivienda.setMedidor(cursor.getString(2));
                vivienda.setUltimaLectura(cursor.getString(3));
                vivienda.setIdRuta(Integer.parseInt(cursor.getString(4)));
                // Add vivienda to viviendas
                viviendas.add(vivienda);
            } while (cursor.moveToNext());
        }

        Log.d("getAllViviendasByRuta()", viviendas.toString());

        // return viviendas
        return viviendas;
    }

    public void addLectura(Lectura lectura){
        Log.d("addLectura", lectura.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("idVivienda", lectura.getIdVivienda());
        values.put("consumo", lectura.getConsumo() );
        values.put("fecha", lectura.getFecha().toString());
        values.put("codigoObservacion", lectura.getCodigoObservacion());
        values.put("Observacion", lectura.getObservacion());


        // 3. insert
        db.insert("Lectura", // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    // Get All Observaciones
    public List<Observacion> getAllObservaciones() {
        List<Observacion> observaciones = new LinkedList<Observacion>();

        // 1. build the query
        String query = "SELECT  * FROM Observaciones";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build Observaciones and add it to list
        Observacion observacion = null;
        if (cursor.moveToFirst()) {
            do {
                observacion = new Observacion();
                observacion.setCodigo(Integer.parseInt(cursor.getString(0)));
                observacion.setDescripcion(cursor.getString(1));

                // Add observacion to observaciones
                observaciones.add(observacion);
            } while (cursor.moveToNext());
        }

        Log.d("getAllObservaciones()", observaciones.toString());

        // return observaciones
        return observaciones;
    }

    // Get All Lecturas
    public List<Lectura> getAllLecturas() {
        List<Lectura> lecturas = new LinkedList<Lectura>();

        // 1. build the query
        String query = "SELECT  * FROM Lectura";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build Observaciones and add it to list
        Lectura lectura = null;
        if (cursor.moveToFirst()) {
            do {
               // DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
                DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
                java.util.Date date = new java.util.Date();
                try {
                    date = formatter.parse(cursor.getString(3));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                lectura = new Lectura();
                lectura.setId(Integer.parseInt(cursor.getString(0)));
                lectura.setIdVivienda(Integer.parseInt(cursor.getString(1)));
                lectura.setConsumo(Integer.parseInt(cursor.getString(2)));
                lectura.setFecha(sqlDate);
                lectura.setCodigoObservacion(Integer.parseInt(cursor.getString(4)));
                lectura.setObservacion(cursor.getString(5));

                // Add lectura to lectura
                lecturas.add(lectura);
            } while (cursor.moveToNext());
        }

        Log.d("getAllLectura()", lecturas.toString());

        // return lecturas
        return lecturas;
    }

}
