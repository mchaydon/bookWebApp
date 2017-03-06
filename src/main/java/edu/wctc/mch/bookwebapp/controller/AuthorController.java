/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.controller;

import edu.wctc.mch.bookwebapp.model.Author;
import edu.wctc.mch.bookwebapp.model.AuthorDao;
import edu.wctc.mch.bookwebapp.model.AuthorService;
import edu.wctc.mch.bookwebapp.model.DbAccessor;
import edu.wctc.mch.bookwebapp.model.IAuthorDao;
import edu.wctc.mch.bookwebapp.model.MySqlDbAccessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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

    private String driverClass; 
    private String url;
    private String username; 
    private String password;
    private String dbStrategyClassName;
    private String daoClassName;
    private String jndiName;
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
            AuthorService authorService = injectDependenciesAndGetAuthorService();
            
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
                        if(selectedAuthor != null) 
                        {
                            authorService.deleteAuthor("author", "author_id", selectedAuthor);
                        }
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
    
    private AuthorService injectDependenciesAndGetAuthorService() throws Exception {
        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DBStrategy based on the class name retrieved
        // from web.xml
        Class dbClass = Class.forName(dbStrategyClassName);
        // Use Java reflection to instanntiate the DBStrategy object
        // Note that DBStrategy classes have no constructor params
        DbAccessor db = (DbAccessor)dbClass.newInstance();

        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DAO based on the class name retrieved above.
        // This one is trickier because the available DAO classes have
        // different constructor params
        IAuthorDao authorDao = null;
        Class daoClass = Class.forName(daoClassName);
        Constructor constructor = null;
        
        // This will only work for the non-pooled AuthorDao
        try {
            constructor = daoClass.getConstructor(new Class[]{
                DbAccessor.class, String.class, String.class, String.class, String.class
            });
        } catch(NoSuchMethodException nsme) {
            // do nothing, the exception means that there is no such constructor,
            // so code will continue executing below
        }

        // constructor will be null if using connectin pool dao because the
        // constructor has a different number and type of arguments
        
        if (constructor != null) {
            // conn pool NOT used so constructor has these arguments
            Object[] constructorArgs = new Object[]{
                db, driverClass, url, username, password
            };
            authorDao = (IAuthorDao) constructor
                    .newInstance(constructorArgs);

        } else {
            /*
             Here's what the connection pool version looks like. First
             we lookup the JNDI name of the Glassfish connection pool
             and then we use Java Refletion to create the needed
             objects based on the servlet init params
             */
            Context ctx = new InitialContext();
            //Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource)ctx.lookup(jndiName);
            constructor = daoClass.getConstructor(new Class[]{
                DataSource.class, DbAccessor.class
            });
            Object[] constructorArgs = new Object[]{
                ds, db
            };

            authorDao = (IAuthorDao) constructor
                    .newInstance(constructorArgs);
        }
        
        return new AuthorService(authorDao);
    }
    
    @Override
    public void init() throws ServletException {
        driverClass = getServletContext().getInitParameter("db.driver.class");
        url = getServletContext().getInitParameter("db.url");
        username = getServletContext().getInitParameter("db.username");
        password = getServletContext().getInitParameter("db.password");
        dbStrategyClassName = getServletContext().getInitParameter("dbStrategy");
        daoClassName = getServletContext().getInitParameter("authorDao");
        jndiName = getServletContext().getInitParameter("connPoolName");
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
