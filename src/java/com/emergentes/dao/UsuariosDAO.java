package com.emergentes.dao;

import com.emergentes.modelo.Usuarios;

public interface UsuariosDAO {
    public void insert_usuario(Usuarios usuarios) throws Exception;
    public void update_usuario(Usuarios usuarios) throws Exception;
    public void delete_usuario(int cod) throws Exception;    
    public Usuarios getId_usuario_informacion(int cod) throws Exception;
    public Usuarios getId_usuario(String email, String password) throws Exception;    
}
