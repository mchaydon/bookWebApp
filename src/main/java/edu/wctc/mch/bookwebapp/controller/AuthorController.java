/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.controller;

import edu.wctc.mch.bookwebapp.model.AuthorDao;
import edu.wctc.mch.bookwebapp.model.AuthorService;
import edu.wctc.mch.bookwebapp.model.MySqlDbAccessor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mike
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    private static final String RESULT_PAGE = "authorList.jsp";
    private static final String ADD_AUTHOR_PAGE = "authorAdd.jsp";
    private static final String EDIT_AUTHOR_PAGE = "authorEdit.jsp";
    private static final String ACTION = "action";

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
        
        String action = request.getParameter(ACTION);
        
        
        String destination = RESULT_PAGE;
        try 
        {
            AuthorService authorService = new AuthorService(new AuthorDao
                (
                    new MySqlDbAccessor(), "com.mysql.jdbc.Driver", 
                    "jdbc:mysql://localhost:3306/book", "root", 
                    "admin"
                )
            );
            
            switch(action)
            {
                case "displayList":
                    request.setAttribute("authors", authorService.getAllAuthors("author", 50));
                    break;
                case "addAuthor":
                    destination = ADD_AUTHOR_PAGE;
                    break;
                case "editAuthor":
                    destination = EDIT_AUTHOR_PAGE;
                    break;
                case "deleteAuthor":
                    break;
            }
            
            
        }
        catch (Exception e) 
        {
            destination = RESULT_PAGE;
        }
        
        RequestDispatcher view =
                request.getRequestDispatcher(destination);
        view.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
