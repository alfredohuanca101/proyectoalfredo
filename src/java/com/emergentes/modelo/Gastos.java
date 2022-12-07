package com.emergentes.modelo;

import java.sql.Date;

public class Gastos extends Usuarios {
    private int cod_gasto;
    private String area;
    private String descripcion;
    private double monto;
    private Date fecha;
    private int usuario; 
    
    public int getCod_gasto() {
        return cod_gasto;
    }

    public void setCod_gasto(int cod_gasto) {
        this.cod_gasto = cod_gasto;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
   
}
