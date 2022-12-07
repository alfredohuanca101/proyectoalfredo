
package com.emergentes.controlador;

import com.emergentes.dao.GastosDAO;
import com.emergentes.dao.GastosDAOimpl;
import com.emergentes.modelo.Gastos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Controlador_gastos", urlPatterns = {"/Controlador_gastos"})
public class Controlador_gastos extends HttpServlet {

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
            out.println("<title>Servlet Controlador_gastos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador_gastos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
         try {                                    
            GastosDAO dao = new GastosDAOimpl();
            Gastos gastos = new Gastos();
            int id;
            HttpSession ses = request.getSession();                     
            String action = (request.getParameter("action2")!=null) ? request.getParameter("action2") : "view";
            String area = request.getParameter("area_gasto");
            switch(action){
                case "add":                                    
                System.out.println("Adicionando");                
                String descripcion = request.getParameter("descripcion_gasto");
                String area_ingreso = request.getParameter("area_gasto");               
                Double monto = Double.parseDouble(request.getParameter("monto_gasto"));                                
                System.out.println("SESION:"+ses.getAttribute("cod_usuario"));
                String usu = String.valueOf(ses.getAttribute("cod_usuario"));
                
                int usuario = Integer.parseInt(usu);
                
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fec = request.getParameter("fecha_gasto");
                 
                Date fecha;
                
                java.util.Date nfecha = null;
                try {
                    nfecha = formato.parse(fec);
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador_ingresos.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                fecha = new java.sql.Date(nfecha.getTime());
                
                Gastos gast = new Gastos();
                gast.setDescripcion(descripcion);
                gast.setArea(area_ingreso);
                gast.setMonto(monto);
                gast.setFecha(fecha);
                gast.setUsuario(usuario);                
            try {                
                dao.insert_gasto(gast);
                response.sendRedirect("Controlador_estados?action="+area+"&area="+area);
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
                response.sendRedirect("Controlador_estados?action="+area+"&area="+area);
            }                    
            request.setAttribute("gastos", gastos);
            request.getRequestDispatcher("Controlador_gastos").forward(request, response);
                    break;
                case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                Gastos gasto_buscado = new Gastos();
                gasto_buscado = dao.getId_gasto(id);
                request.setAttribute("gasto_buscado", gasto_buscado);
                request.getRequestDispatcher("modifica_informacion_gastos.jsp").forward(request, response);
                    break;                    
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete_gasto(id);
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
                Gastos gasto = new Gastos();
                GastosDAO dao = new GastosDAOimpl();
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
                                               
                gasto.setCod_gasto(id);
                gasto.setDescripcion(descripcion_nuevo);
                gasto.setMonto(monto_nuevo);
                gasto.setFecha(fecha_nuevo);

                dao.update_gasto(gasto);
                
                response.sendRedirect("Controlador_estados?action="+area+"&area="+area);                
        } catch (Exception e) {                
                response.sendRedirect("modifica_informacion.jsp");
        }                                                                    
    }
}
