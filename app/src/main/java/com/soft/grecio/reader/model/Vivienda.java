package com.soft.grecio.reader.model;

/**
 * Created by grecio on 5/31/2015.
 */
public class Vivienda {

    private int id;
    private String domicilio;
    private String medidor;
    private String ultimaLectura;
    private int idRuta;


    public Vivienda(){}

    public Vivienda(String domicilio,String medidor,String ultimaLectura, int idRuta){
        super();

        this.domicilio=domicilio;
        this.medidor=medidor;
        this.ultimaLectura=ultimaLectura ;
        this.idRuta = idRuta;

    }


    //getter & setter
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
    public String getDomicilio() { return this.domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    public String getMedidor() { return this.medidor; }
    public void setMedidor(String medidor) { this.medidor = medidor; }
    public String getUltimaLectura() { return this.ultimaLectura; }
    public void setUltimaLectura(String ultimaLectura) { this.ultimaLectura = ultimaLectura; }
    public int getIdRuta() { return this.idRuta; }
    public void setIdRuta(int idRuta) { this.idRuta = idRuta; }

}
