package com.soft.grecio.reader.model;

/**
 * Created by grecio on 5/25/2015.
 */
public class Ruta {

    private int id;
    private String codigo;
    private String descripcion;
    private String zona;


    public Ruta(){}

    public Ruta(String codigo,String descripcion,String zona){
        super();

        this.codigo=codigo;
        this.descripcion=descripcion;
        this.zona=zona ;
    }


    //getter & setter
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
    public String getCodigo() { return this.codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return this.descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getZona() { return this.zona; }
    public void setZona(String zona) { this.zona = zona; }

}
