package com.emergentes.dao;

import com.emergentes.modelo.Ingresos;
import com.emergentes.utiles.conexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngresosDAOimpl extends conexionDB implements IngresosDAO{

    @Override
    public void insert_ingreso(Ingresos ingresos) throws Exception {
        try {
            this.conectar();
            String sql = "insert into ingresos (area, descripcion, monto, fecha, usuario) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, ingresos.getArea());
            ps.setString(2, ingresos.getDescripcion());
            ps.setDouble(3, ingresos.getMonto());
            ps.setDate(4, ingresos.getFecha());
            ps.setInt(5, ingresos.getCod_usuario());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;            
        } finally{
            this.desconectar();
        }        
    }

    @Override
    public void update_ingreso(Ingresos ingresos) throws Exception {
    try {
            this.conectar();
            String sql = "update ingresos set descripcion = ?, monto = ?, fecha = ? where cod_ingreso = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, ingresos.getDescripcion());
            ps.setDouble(2, ingresos.getMonto());
            ps.setDate(3, ingresos.getFecha());
            ps.setInt(4, ingresos.getCod_ingreso());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }        
    }

    @Override
    public void delete_ingreso(int cod) throws Exception {
        try {
            this.conectar();
            String sql = "delete from ingresos where cod_ingreso = ?";
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
    public Ingresos getId_ingreso(int cod) throws Exception {
           Ingresos ingreso = new Ingresos();
        try {
            this.conectar();            
            String sql = "select * from ingresos where cod_ingreso = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, cod);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                ingreso.setCod_ingreso(rs.getInt("cod_ingreso"));
                ingreso.setArea(rs.getString("area"));
                ingreso.setDescripcion(rs.getString("descripcion"));
                ingreso.setMonto(rs.getInt("monto"));
                ingreso.setFecha(rs.getDate("fecha"));                
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return ingreso;          
    }

    @Override
    public List<Ingresos> getAll_ingreso_area(String area) throws Exception {
        List<Ingresos> lista = null;
        try {
            this.conectar();
            String sql = "select * from ingresos where area = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, area);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Ingresos>();
            while (rs.next()) {                
                Ingresos ingreso = new Ingresos();                
                ingreso.setCod_ingreso(rs.getInt("cod_ingreso"));
                ingreso.setArea(rs.getString("area"));
                ingreso.setDescripcion(rs.getString("descripcion"));
                ingreso.setMonto(rs.getDouble("monto"));
                ingreso.setFecha(rs.getDate("fecha"));            
                lista.add(ingreso);
            }
            
        } catch (Exception e) {
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;         
    }

    @Override
    public double ingresos_totales() throws Exception {
        double ingreso_total=0.0;
        try {
            this.conectar();
            Statement st = conn.createStatement();
            String sql = "select SUM(monto) as monto from ingresos";            
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
    public double ingresos_totales_areas(String area) throws Exception {
        double ingreso_total=0.0;
        /*try {
            this.conectar();
            Statement st = conn.createStatement();
            String sql = "select SUM(monto) as monto from ingresos where area = ? ";            
            PreparedStatement ps= this.conn.prepareStatement(sql);
            ps.setString(1, area);           
            ResultSet rs = ps.executeQuery(sql);            
            while (rs.next()) {
                ingreso_total = rs.getDouble("monto");
            }      
        } catch (Exception e) {
            System.out.println("ERROR EN LA CONSULTA");
            throw e;
        } finally{
            this.desconectar();
            System.out.println("ERROR EN LA CONSULTA");
        }
        return ingreso_total;                */
        System.out.println("EL AREA EN INGRESOS :"+area);
        try {
            this.conectar();            
            String sql = "select SUM(monto) as monto from ingresos where area = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, area);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {                
                ingreso_total = rs.getDouble("monto");
                System.out.println("EL monto:"+ingreso_total);
            }            
        } catch (Exception e) {
            System.out.println("ERROR EN LA CONSULTA");
            throw e;
        } finally{
            this.desconectar();
        }
        return ingreso_total;                  
    }
}
