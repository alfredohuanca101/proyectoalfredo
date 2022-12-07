package com.emergentes.dao;

import com.emergentes.modelo.Gastos;
import java.util.List;

public interface GastosDAO {
    public void insert_gasto(Gastos gastos) throws Exception;
    public void update_gasto(Gastos gastos) throws Exception;
    public void delete_gasto(int cod) throws Exception;
    public Gastos getId_gasto(int cod) throws Exception;
    public List<Gastos> getAll_gasto_area(String area) throws Exception;
    
    public double gastos_totales()throws Exception;    
    public double gastos_totales_areas(String area)throws Exception; 
}
