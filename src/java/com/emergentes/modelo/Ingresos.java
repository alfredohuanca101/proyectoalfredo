package com.emergentes.modelo;

import java.sql.Date;

public class Ingresos extends Usuarios {
    private int cod_ingreso;
    private String area;
    private String descripcion;
    private double monto;
    private Date fecha;
    private int usuario;

    public int getCod_ingreso() {
        return cod_ingreso;
    }

    public void setCod_ingreso(int cod_ingreso) {
        this.cod_ingreso = cod_ingreso;
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
