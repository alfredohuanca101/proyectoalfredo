package com.emergentes.controlador;

import com.emergentes.dao.IngresosDAO;
import com.emergentes.dao.IngresosDAOimpl;
import com.emergentes.modelo.Ingresos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controlador_ingresos", urlPatterns = {"/Controlador_ingresos"})
public class Controlador_ingresos extends HttpServlet {

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
            out.println("<title>Servlet Controlador_ingresos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador_ingresos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {            
            IngresosDAO dao = new IngresosDAOimpl();
            Ingresos ingreso = new Ingresos();
            int id;
            HttpSession ses = request.getSession();                     
            String action = (request.getParameter("action")!=null) ? request.getParameter("action") : "view";
            String area = request.getParameter("area_ingreso");
            switch(action){
                case "add":                  
                  
                System.out.println("Adicionando");
                
                String descripcion = request.getParameter("descripcion_ingreso");
                String area_ingreso = request.getParameter("area_ingreso");               
                Double monto = Double.parseDouble(request.getParameter("monto_ingreso"));                

                System.out.println("SESION:"+ses.getAttribute("cod_usuario"));
                String usu = String.valueOf(ses.getAttribute("cod_usuario"));
                
                int usuario = Integer.parseInt(usu);
                
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fe = request.getParameter("fecha_ingreso");
                 
                Date fecha;
                
                java.util.Date nfecha = null;
                try {
                    nfecha = formato.parse(fe);
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador_ingresos.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                fecha = new java.sql.Date(nfecha.getTime());
                
                Ingresos ing = new Ingresos();
                ing.setDescripcion(descripcion);
                ing.setArea(area_ingreso);
                ing.setMonto(monto);
                ing.setFecha(fecha);
                ing.setUsuario(usuario);
                
                System.out.println("aqui"+descripcion);
                System.out.println("aqui"+area_ingreso);
                System.out.println("aqui"+fecha);
                System.out.println("aqui"+usuario);
                System.out.println("aqui"+monto);
            try {                
                dao.insert_ingreso(ing);
                System.out.println("Registrado exitosamente");
                System.out.println("LA RUTA     Controlador_estados?action="+area);
                response.sendRedirect("Controlador_estados?action="+area+"&area="+area);
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
                System.out.println("Cod_usu: "+usuario);
                response.sendRedirect("Controlador_estados?action="+area+"&area="+area);
            }                    
                    request.setAttribute("ingreso", ingreso);
                    request.getRequestDispatcher("Controlador_ingresos").forward(request, response);
                    break;
                case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                Ingresos ingreso_buscado = new Ingresos();
                ingreso_buscado = dao.getId_ingreso(id);
                request.setAttribute("ingreso_buscado", ingreso_buscado);
                request.getRequestDispatcher("modifica_informacion.jsp").forward(request, response);                                
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete_ingreso(id);
                    response.sendRedirect("Controlador_estados?action="+area+"&area="+area);
                    break;
                default:
                                        
                    break;
                
            }
        } catch (Exception e) {
            System.out.println("Error"+ e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {               
                System.out.println("MODIFICANDO");
                Ingresos ingreso = new Ingresos();
                IngresosDAO dao = new IngresosDAOimpl();
                int id = Integer.parseInt(request.getParameter("id"));
                String area = request.getParameter("area");
                String descripcion_nuevo = request.getParameter("descripcion");
                double monto_nuevo = Double.parseDouble(request.getParameter("monto"));
                
                SimpleDateFormat formato_nuevo = new SimpleDateFormat("yyyy-MM-dd");
                String fe_nuevo = request.getParameter("fecha");
                 
                Date fecha_nuevo;
                
                java.util.Date nfecha_nuevo = null;
                try {
                    nfecha_nuevo = formato_nuevo.parse(fe_nuevo);
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador_ingresos.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                fecha_nuevo = new java.sql.Date(nfecha_nuevo.getTime());
                                               
                ingreso.setCod_ingreso(id);
                ingreso.setDescripcion(descripcion_nuevo);
                ingreso.setMonto(monto_nuevo);
                ingreso.setFecha(fecha_nuevo);
                System.out.println("POST:"+id);
                System.out.println("POST:"+descripcion_nuevo);
                System.out.println("POST:"+fecha_nuevo);
                System.out.println("POST:"+monto_nuevo);
                System.out.println("URL URL Controlador_estados?action="+area+"&area="+area );
                dao.update_ingreso(ingreso);
                //request.getRequestDispatcher("Controlador_estados?action="+area+"&area="+area).forward(request, response);
                response.sendRedirect("Controlador_estados?action="+area+"&area="+area);
                
        } catch (Exception e) {                
                response.sendRedirect("modifica_informacion.jsp");
        }                                                                    
    }
}
