package com.emergentes.dao;

import com.emergentes.modelo.Gastos;
import com.emergentes.utiles.conexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GastosDAOimpl extends conexionDB implements GastosDAO{

    @Override
    public void insert_gasto(Gastos gastos) throws Exception {
        try {
            this.conectar();
            String sql = "insert into gastos (area, descripcion, monto, fecha, usuario) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, gastos.getArea());
            ps.setString(2, gastos.getDescripcion());
            ps.setDouble(3, gastos.getMonto());
            ps.setDate(4, gastos.getFecha());
            ps.setInt(5, gastos.getCod_usuario());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }        
    }

    @Override
    public void update_gasto(Gastos gastos) throws Exception {
    try {
            this.conectar();
            String sql = "update gastos set descripcion = ?, monto = ?, fecha = ? where cod_gasto = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, gastos.getDescripcion());
            ps.setDouble(2, gastos.getMonto());
            ps.setDate(3, gastos.getFecha());
            ps.setInt(4, gastos.getCod_gasto());
            ps.executeUpdate();
            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }        
    }

    @Override
    public void delete_gasto(int cod) throws Exception {
        try {
            this.conectar();
            String sql = "delete from gastos where cod_gasto = ?";
            PreparedStatement ps= this.conn.prepareStatement(sql);
            ps.setInt(1, cod);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }          
    }

    @Override
    public Gastos getId_gasto(int cod) throws Exception {
        Gastos gasto = new Gastos();
        try {
            this.conectar();            
            String sql = "select * from gastos where cod_gasto = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, cod);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                gasto.setCod_gasto(rs.getInt("cod_gasto"));
                gasto.setArea(rs.getString("area"));
                gasto.setDescripcion(rs.getString("descripcion"));
                gasto.setMonto(rs.getInt("monto"));
                gasto.setFecha(rs.getDate("fecha"));                
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return gasto;          
    }

    @Override
    public List<Gastos> getAll_gasto_area(String area) throws Exception {
        List<Gastos> lista = null;
        try {
            this.conectar();
            String sql = "select * from gastos where area = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, area);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Gastos>();
            while (rs.next()) {                
                Gastos gasto = new Gastos();                
                gasto.setCod_gasto(rs.getInt("cod_gasto"));
                gasto.setArea(rs.getString("area"));
                gasto.setDescripcion(rs.getString("descripcion"));
                gasto.setMonto(rs.getDouble("monto"));
                gasto.setFecha(rs.getDate("fecha"));            
                lista.add(gasto);
            }            
        } catch (Exception e) {
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;                 
    }

    @Override
    public double gastos_totales() throws Exception {
        double ingreso_total=0.0;
        try {
            this.conectar();
            Statement st = conn.createStatement();
            String sql = "select SUM(monto) as monto from gastos";            
            ResultSet rs = st.executeQuery(sql);            
            while (rs.next()) {
                ingreso_total = rs.getDouble("monto");
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return ingreso_total;         
    }

    @Override
    public double gastos_totales_areas(String area) throws Exception {
        double gasto_total=0.0;
        System.out.println("EL AREA EN GASTOS :"+area);
        try {
            this.conectar();
            Statement st = conn.createStatement();
            String sql = "select SUM(monto) as monto from gastos where area = ?";            
            PreparedStatement ps= this.conn.prepareStatement(sql);
            //ps.setString(1, area); 
            ps.setString(1, area);
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
                gasto_total = rs.getDouble("monto");
                System.out.println("EL monto:"+gasto_total);
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return gasto_total;                        
    }
}
