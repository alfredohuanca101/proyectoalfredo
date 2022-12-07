package com.emergentes.dao;

import com.emergentes.modelo.Usuarios;
import com.emergentes.utiles.conexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuariosDAOimpl extends conexionDB implements UsuariosDAO{

    @Override
    public void insert_usuario(Usuarios usuarios) throws Exception {
        try {
            this.conectar();
            String sql = "insert into usuario (propietario, empresa, email, password) values (?, ?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, usuarios.getPropietario());
            ps.setString(2, usuarios.getEmpresa());
            ps.setString(3, usuarios.getEmail());
            ps.setString(4, usuarios.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }  
    }

    @Override
    public void update_usuario(Usuarios usuarios) throws Exception {
        try {
            this.conectar();
            String sql = "update usuario set propietario = ?, empresa = ?, email = ?, password = ? where cod_usuario = ? ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, usuarios.getPropietario());
            ps.setString(2, usuarios.getEmpresa());
            ps.setString(3, usuarios.getEmail());
            ps.setString(4, usuarios.getPassword());
            ps.setInt(5, usuarios.getCod_usuario());           
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }       
    }

    @Override
    public void delete_usuario(int cod) throws Exception {
        try {
            this.conectar();
            String sql = "delete from usuario where cod_usuario =?";
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
    public Usuarios getId_usuario(String email, String password) throws Exception {        
        Usuarios usuario = new Usuarios();
        try {
            this.conectar();
            String sql = "select * from usuario where email = ? and password = ? ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                usuario.setCod_usuario(rs.getInt("cod_usuario"));
                usuario.setPropietario(rs.getString("propietario"));
                usuario.setEmpresa(rs.getString("empresa"));
                usuario.setEmail(rs.getString("email"));                
                usuario.setPassword(rs.getString("password"));                
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return usuario;
    }

    @Override
    public Usuarios getId_usuario_informacion(int cod) throws Exception {
        Usuarios usuario = new Usuarios();
        try {
            this.conectar();
            String sql = "select * from usuario where cod_usuario = ? ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, cod);
            ResultSet rs = ps.executeQuery();            
            if (rs.next()) {
                usuario.setCod_usuario(rs.getInt("cod_usuario"));
                usuario.setPropietario(rs.getString("propietario"));
                usuario.setEmpresa(rs.getString("empresa"));
                usuario.setEmail(rs.getString("email"));                
                usuario.setPassword(rs.getString("password"));                
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return usuario;
    }
    
}
