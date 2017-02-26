/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.controller;

import edu.wctc.mch.bookwebapp.model.Author;
import edu.wctc.mch.bookwebapp.model.AuthorDao;
import edu.wctc.mch.bookwebapp.model.AuthorService;
import edu.wctc.mch.bookwebapp.model.MySqlDbAccessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private static final String LIST_PAGE = "authorList.jsp";
    private static final String ADD_PAGE = "authorAdd.jsp";
    private static final String EDIT_PAGE = "authorEdit.jsp";
    private static final String ACTION = "submit";

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
        String selectedAuthor = request.getParameter("authorSelected");
        
        
        String destination = LIST_PAGE;
        try 
        {
            AuthorService authorService = new AuthorService(new AuthorDao
                (
                    new MySqlDbAccessor(), "com.mysql.jdbc.Driver", 
                    "jdbc:mysql://localhost:3306/book", "root", 
                    "admin"
                )
            );
            
            if (action != null)
            {
                switch(action)
                {
                    case "add":
                        destination = ADD_PAGE;
                        break;
                    case "addSave":
                        if (!request.getParameter("authorName").isEmpty())
                        {
                            Date date = new Date();
                            List<String> colNames = new ArrayList<>();
                            colNames.add("author_name");
                            colNames.add("date_added");
                            List colValues = new ArrayList<>();
                            colValues.add(request.getParameter("authorName"));
                            colValues.add(date);
                            authorService.insertAuthor("author", colNames, colValues);
                        }
                        reloadAuthors(authorService, request);
                        break;
                    case "edit":
                        if(selectedAuthor != null) 
                        {
                            List<Author> authors = authorService.getAllAuthors("author", 50);
                            for (Author a : authors)
                            {
                                if (a.getAuthorId() == Integer.valueOf(selectedAuthor))
                                {
                                  request.setAttribute("author_id", a.getAuthorId());
                                  request.setAttribute("author_name", a.getAuthorName());
                                  request.setAttribute("date_added", a.getDateAdded());
                                }
                            }
                            destination = EDIT_PAGE;
                        }
                        reloadAuthors(authorService, request);
                        break;
                    case "editSave":
                        List<String> colNamesEdit = new ArrayList<>();
                        colNamesEdit.add("author_name");
                        List colValuesEdit = new ArrayList<>();
                        colValuesEdit.add(request.getParameter("authorName"));
                        
                        authorService.updateAuthor("author", colNamesEdit, colValuesEdit, "author_id", request.getParameter("authorId"));
                        //request.setAttribute("test", request.getParameter("authorId"));
                        reloadAuthors(authorService, request);
                        break;   
                    case "delete":
                        authorService.deleteAuthor("author", "author_id", selectedAuthor);
                        reloadAuthors(authorService, request);
                        break;
                }
            }
            else
            {
                reloadAuthors(authorService, request);
            }
        }
        catch (Exception e) 
        {
            destination = LIST_PAGE;
        }
        
        RequestDispatcher view =
                request.getRequestDispatcher(destination);
        view.forward(request, response);
    }
    
    private void reloadAuthors(AuthorService authorService, HttpServletRequest request) throws ClassNotFoundException, SQLException
    {
        List<Author> authors = authorService.getAllAuthors("author", 50);
        request.setAttribute("authors", authors);
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
