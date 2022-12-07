package com.emergentes.controlador;

import com.emergentes.dao.GastosDAO;
import com.emergentes.dao.GastosDAOimpl;
import com.emergentes.dao.IngresosDAO;
import com.emergentes.dao.IngresosDAOimpl;
import com.emergentes.modelo.Gastos;
import com.emergentes.modelo.Ingresos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controlador_estados", urlPatterns = {"/Controlador_estados"})
public class Controlador_estados extends HttpServlet {

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
            out.println("<title>Servlet Controlador_estados</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador_estados at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            IngresosDAO daoingresos = new IngresosDAOimpl();
            GastosDAO daogastos = new GastosDAOimpl();
            double total_ingresos_finanzas = 0.0;
            double total_ingresos_r_humanos = 0.0;
            double total_ingresos_marketing = 0.0;
            double total_gastos_finanzas = 0.0;
            double total_gastos_r_humanos = 0.0;
            double total_gastos_marketing = 0.0;
         try {                        
            String action = (request.getParameter("action")!=null) ? request.getParameter("action") : "view";
            String area = request.getParameter("area");
            switch(action){                
                case "finanzas":
                total_ingresos_finanzas = daoingresos.ingresos_totales_areas(area);
                total_gastos_finanzas = daogastos.gastos_totales_areas(area);
                System.out.println("TOTAL_INGRESOS:"+total_ingresos_finanzas);
                System.out.println("TOTAL_INGRESOS:"+total_gastos_finanzas);
                request.setAttribute("total_ingresos_area", total_ingresos_finanzas);
                request.setAttribute("total_gastos_area", total_gastos_finanzas);
                
                IngresosDAO dao = new IngresosDAOimpl();
                List<Ingresos> lista = dao.getAll_ingreso_area(area);
                request.setAttribute("ingreso", lista);
                GastosDAO dao1 = new GastosDAOimpl();
                List<Gastos> lista1 = dao1.getAll_gasto_area(area);
                request.setAttribute("gasto", lista1);                
                request.getRequestDispatcher("finanzas.jsp").forward(request, response);
           
                    break;
                case "recursos":
                total_ingresos_finanzas = daoingresos.ingresos_totales_areas(area);
                total_gastos_finanzas = daogastos.gastos_totales_areas(area);
                System.out.println("TOTAL_INGRESOS:"+total_ingresos_finanzas);
                System.out.println("TOTAL_INGRESOS:"+total_gastos_finanzas);
                request.setAttribute("total_ingresos_area", total_ingresos_finanzas);
                request.setAttribute("total_gastos_area", total_gastos_finanzas);
                
                IngresosDAO daorh = new IngresosDAOimpl();
                List<Ingresos> listarh = daorh.getAll_ingreso_area(area);
                request.setAttribute("ingreso", listarh);
                GastosDAO daogrh = new GastosDAOimpl();
                List<Gastos> listagrh = daogrh.getAll_gasto_area(area);
                request.setAttribute("gasto", listagrh);                
                request.getRequestDispatcher("recursos_humanos.jsp").forward(request, response);
                    break;
                case "marketing":
                total_ingresos_finanzas = daoingresos.ingresos_totales_areas(area);
                total_gastos_finanzas = daogastos.gastos_totales_areas(area);
                System.out.println("TOTAL_INGRESOS:"+total_ingresos_finanzas);
                System.out.println("TOTAL_INGRESOS:"+total_gastos_finanzas);
                request.setAttribute("total_ingresos_area", total_ingresos_finanzas);
                request.setAttribute("total_gastos_area", total_gastos_finanzas);
                
                IngresosDAO daom = new IngresosDAOimpl();
                List<Ingresos> listam = daom.getAll_ingreso_area(area);
                request.setAttribute("ingreso", listam);
                GastosDAO daogm = new GastosDAOimpl();
                List<Gastos> listagm = daogm.getAll_gasto_area(area);
                request.setAttribute("gasto", listagm);                
                request.getRequestDispatcher("marketing.jsp").forward(request, response);
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
        processRequest(request, response);
    }
}
