package com.emergentes.dao;

import com.emergentes.modelo.Ingresos;
import java.sql.Date;
import java.util.List;

public interface IngresosDAO {
    public void insert_ingreso(Ingresos ingresos) throws Exception;
    public void update_ingreso(Ingresos ingresos) throws Exception;
    public void delete_ingreso(int cod) throws Exception;
    public Ingresos getId_ingreso(int cod) throws Exception;
    public List<Ingresos> getAll_ingreso_area(String area) throws Exception;    
    public double ingresos_totales()throws Exception;    
    public double ingresos_totales_areas(String area)throws Exception; 
}
