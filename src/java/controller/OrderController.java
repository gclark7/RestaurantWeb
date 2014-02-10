/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import domainobject.MenuDAO;
import domainobject.MenuDBAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Gratuity_15percent;
import model.RestaurantMenuItem;
import model.Tax_WI;

/**
 *
 * @author gcDataTechnology
 */
@WebServlet(name = "order", urlPatterns = {"/order"})
//@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

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
            out.println("<title>Servlet OrderController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String defaultPage="index.jsp";
        String summaryPage="orderSummary.jsp";
        String nextPage=defaultPage;
        String menu="menu";
        String menuAction="menuAction";
        Map reciept;
        
        switch (request.getParameter(menuAction)){
            case "placeOrder":
                reciept= new LinkedHashMap();
                RestaurantMenuItem item=null;
                double totalCost=0.00;
                //double tax=0.00;
                //double tip=0.00;
                request.setAttribute(menuAction, "summary");
                String[] order=request.getParameterValues("itemSelected");
                for(int i=0;i<order.length;i++){
                     item= new MenuDAO(new MenuDBAccess()).lookupMenuItem((Integer.parseInt(order[i])));
                     reciept.put(item.getShortDescription(), item.getPrice());
                     totalCost+=item.getPrice();
                }
                
                //set tax & tip
                reciept.put("Tax", new Tax_WI().calculateTax(totalCost));
                reciept.put("Total", new Tax_WI().calculateBillWithTax(totalCost));
                reciept.put("Tip", new Gratuity_15percent().calculateGratuity((double)reciept.get("Total")));
                reciept.put("GrandTotal", ((double)reciept.get("Total")+(double)reciept.get("Tip")));
                
                request.setAttribute(menu,reciept);
                nextPage=summaryPage;
                break;
            default: nextPage=defaultPage;
        }
        
        RequestDispatcher view =
                request.getRequestDispatcher(nextPage);
        view.forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
