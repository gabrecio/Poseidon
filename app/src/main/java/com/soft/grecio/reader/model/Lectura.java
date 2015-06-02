package com.soft.grecio.reader.model;

/**
 * Created by grecio on 6/1/2015.
 */
public class Lectura {

    private int id;
    private int idVivienda;
    private int consumo;
    private java.util.Date fecha;
    private int codigoObservacion;
    private String observacion;



    public Lectura(){}

    public Lectura(int id,int idVivienda, int consumo,java.util.Date fecha, int codigoObservacion,String observacion){
        super();

        this.id=id;
        this.idVivienda=idVivienda;
        this.consumo=consumo;
        this.fecha=fecha;
        this.codigoObservacion=codigoObservacion;
        this.observacion=observacion;



    }


    //getter & setter
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
    public int getIdVivienda() { return this.idVivienda; }
    public void setIdVivienda(int idVivienda) { this.idVivienda = idVivienda; }
    public int getConsumo() { return this.consumo; }
    public void setConsumo(int consumo) { this.consumo = consumo; }
    public java.util.Date getFecha() { return this.fecha; }
    public void setFecha(java.util.Date fecha) { this.fecha = fecha; }
    public int getCodigoObservacion() { return this.codigoObservacion; }
    public void setCodigoObservacion(int codigoObservacion) { this.codigoObservacion = codigoObservacion; }
    public String getObservacion() { return this.observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }


}
