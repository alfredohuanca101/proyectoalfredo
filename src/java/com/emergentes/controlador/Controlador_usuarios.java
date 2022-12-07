package com.emergentes.controlador;

import com.emergentes.dao.GastosDAO;
import com.emergentes.dao.GastosDAOimpl;
import com.emergentes.dao.IngresosDAO;
import com.emergentes.dao.IngresosDAOimpl;
import com.emergentes.dao.UsuariosDAO;
import com.emergentes.dao.UsuariosDAOimpl;
import com.emergentes.modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controlador_usuarios", urlPatterns = {"/Controlador_usuarios"})
public class Controlador_usuarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador_usuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador_usuarios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {            
            UsuariosDAO dao = new UsuariosDAOimpl();                                   
            Usuarios usu = new Usuarios();
            int id;
            String action = (request.getParameter("action")!=null) ? request.getParameter("action") : "view";
            
            IngresosDAO daoingresos = new IngresosDAOimpl();
            GastosDAO daogastos = new GastosDAOimpl();
            double total_ingresos = 0.0;
            double total_gastos = 0.0;
            
            switch(action){
                case "add":
                    request.setAttribute("usuario", usu);
                    request.getRequestDispatcher("registrarse.jsp").forward(request, response);
                    break;
                case "edit":
                    //id = Integer.parseInt(request.getParameter("cod"));
                    //usu = dao.getId_usuario_informacion(id);
                    //request.setAttribute("usuario", usu);                    
                    //request.getRequestDispatcher("configuracion.jsp").forward(request, response);
                    Usuarios usuario_validado1 = new Usuarios();
                    HttpSession sesion1 = request.getSession();
        try {
            //usuario_validado = dao.getId_usuario(sesion.getAttribute("email"), sesion.getAttribute("password"));
            //if (usuario_validado.getEmail().equals(email) && usuario_validado.getPassword().equals(contrasena)){
                int cod = Integer.parseInt(request.getParameter("cod"));
                String propietario = request.getParameter("propietario");
                String empresa = request.getParameter("empresa");
                String email = request.getParameter("email");
                String contrasena = request.getParameter("contrasena");                
                
                usuario_validado1.setCod_usuario(cod);
                usuario_validado1.setPropietario(propietario);
                usuario_validado1.setEmpresa(empresa);
                usuario_validado1.setEmail(email);
                usuario_validado1.setPassword(contrasena);
                
                dao.update_usuario(usuario_validado1);
                
                sesion1.setAttribute("login", "OK");
                sesion1.setAttribute("cod_usuario", usuario_validado1.getCod_usuario());
                sesion1.setAttribute("propietario", usuario_validado1.getPropietario());
                sesion1.setAttribute("empresa", usuario_validado1.getEmpresa());
                sesion1.setAttribute("email", usuario_validado1.getEmail());
                sesion1.setAttribute("password", usuario_validado1.getPassword());
                sesion1.setAttribute("confirmacion", "conf_si");
                response.sendRedirect("configuracion.jsp");
            //}
        } catch (Exception e) {
                sesion1.setAttribute("error", "NoEncontrado");
                response.sendRedirect("configuracion.jsp");
        }                    
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("cod"));
                    dao.delete_usuario(id);
                    response.sendRedirect("Controlador_usuarios");
                    break;
                case "inicio":
                IngresosDAO daoingresos_inicio = new IngresosDAOimpl();
                GastosDAO daogastos_inicio = new GastosDAOimpl();
                double total_ingresos_inicio = 0.0;
                double total_gastos_inicio = 0.0;                    
                total_ingresos_inicio = daoingresos_inicio.ingresos_totales();
                request.setAttribute("total_ingresos", total_ingresos_inicio);
                
                total_gastos_inicio = daogastos_inicio.gastos_totales();
                request.setAttribute("total_gastos", total_gastos_inicio);                
                
                request.getRequestDispatcher("inicio_admin.jsp").forward(request, response);                    
                    break;
                case "cerrar_sesion":
                    HttpSession ses = request.getSession();
                    String login = (String) ses.getAttribute("login");
                    if (login.equals("OK")) {
                        ses.invalidate();
                        response.sendRedirect("inicio.jsp");
                    }                    
                    break;
                //case "configurar":
                    
                  //  break;
                case "inicio_sesion":
                    String email = request.getParameter("email");
                    String contrasena = request.getParameter("contrasena");                                                                  
                    Usuarios usuario_validado = new Usuarios();
                    HttpSession sesion = request.getSession();
        try {
            usuario_validado = dao.getId_usuario(email, contrasena);
            if (usuario_validado.getEmail().equals(email) && usuario_validado.getPassword().equals(contrasena)){
                System.out.println("Usuario validado");
                System.out.println("VAriable email"+email);
                System.out.println("Varialbe contraseña"+contrasena);
                
                sesion.setAttribute("login", "OK");
                sesion.setAttribute("cod_usuario", usuario_validado.getCod_usuario());
                sesion.setAttribute("propietario", usuario_validado.getPropietario());
                sesion.setAttribute("empresa", usuario_validado.getEmpresa());
                sesion.setAttribute("email", usuario_validado.getEmail());
                sesion.setAttribute("password", usuario_validado.getPassword());
                
                System.out.println("SESION"+sesion.getAttribute("login"));
                System.out.println("SESION"+sesion.getAttribute("cod_usuario"));
                System.out.println("SESION"+sesion.getAttribute("propietario"));
                System.out.println("SESION"+sesion.getAttribute("empresa"));
                System.out.println("SESION"+sesion.getAttribute("email"));
                System.out.println("SESION"+sesion.getAttribute("password"));

                
                total_ingresos = daoingresos.ingresos_totales();
                total_gastos = daogastos.gastos_totales();
                request.setAttribute("total_gastos", total_gastos);
                request.setAttribute("total_ingresos", total_ingresos); 
                
                //total_gastos = daogastos.gastos_totales();
                //request.setAttribute("total_gastos", total_gastos);
                
                System.out.println("total_ingrsos:"+total_ingresos);
                System.out.println("total_gastos:"+total_gastos);
                System.out.println("ENVIADO");
                
                request.getRequestDispatcher("inicio_admin.jsp").forward(request, response);
            }
        } catch (Exception e) {
                sesion.setAttribute("error", "NoEncontrado");
                System.out.println("NO ENVIADO");
                response.sendRedirect("inicio.jsp");
        }
                    break;
                default:
                    //List<Usuarios> lista = dao.getAll_vecino();
                    //request.setAttribute("vecino", lista);
                    request.getRequestDispatcher("inicio.jsp").forward(request, response);
                    break;
                
            }
        } catch (Exception e) {
            System.out.println("Error"+ e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuariosDAO dao = new UsuariosDAOimpl();
        //int id = Integer.parseInt(request.getParameter("cod"));  
        String propietario = request.getParameter("propietario");
        String empresa = request.getParameter("empresa");
        String email = request.getParameter("email");
        String contraseña = request.getParameter("contrasena");        
        Usuarios usu = new Usuarios();
        HttpSession sesion = request.getSession();                
        usu.setPropietario(propietario);
        usu.setEmpresa(empresa);
        usu.setEmail(email);
        usu.setPassword(contraseña);        
        //if (id == 0) {
            try {
                dao.insert_usuario(usu);                
                sesion.setAttribute("creado", "registrado");
                response.sendRedirect("registrarse.jsp");
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
                sesion.setAttribute("creado", "fallo");
            }
        /*}else{
            //edicion o actualizacion de un registro
            try {
                dao.update_vecino(vec);
                response.sendRedirect("Controlador_vecinos");
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
            }            */
        }
    //}   
}
