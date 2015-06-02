package com.soft.grecio.reader.model;

/**
 * Created by grecio on 5/31/2015.
 */
public class Observacion {
    private int codigo;
    private String descripcion;



    public Observacion(){}

    public Observacion(int codigo,String descripcion){
        super();

        this.codigo=codigo;
        this.descripcion=descripcion;


    }


    //getter & setter
    public int getCodigo() { return this.codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public String getDescripcion() { return this.descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }



}
