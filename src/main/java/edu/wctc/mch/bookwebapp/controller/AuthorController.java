/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.controller;

import edu.wctc.mch.bookwebapp.entity.Author;
import edu.wctc.mch.bookwebapp.entity.Book;
import edu.wctc.mch.bookwebapp.service.AuthorService;
import edu.wctc.mch.bookwebapp.service.BookService;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
    private static int counter = 0;
    
    private AuthorService authorService;
    private BookService bookService;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter(ACTION);
        String selectedAuthor = request.getParameter("authorSelected");
        
        HttpSession session = request.getSession();
        session.setAttribute("date", LocalDateTime.now());
        
        ServletContext ctx = request.getServletContext();
        counter++;
        ctx.setAttribute("counter", counter);
        
        String destination = LIST_PAGE;
        try 
        {
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
                            Author a = new Author();
                            a.setAuthorName(request.getParameter("authorName"));
                            a.setDateAdded(new Date());
                            authorService.edit(a);
                        }
                        reloadAuthors(request);
                        break;
                    case "edit":
                        if(selectedAuthor != null) 
                        {
                            List<Author> authors = authorService.findAllEagerly();
                            for(Author a: authors)
                            {
                                if (a.getAuthorId() == Integer.valueOf(selectedAuthor))
                                {
                                    request.setAttribute("author_id", a.getAuthorId());
                                    request.setAttribute("author_name", a.getAuthorName());
                                    request.setAttribute("date_added", a.getDateAdded());
                                    Set<Book> books = a.getBookSet();
                                    request.setAttribute("books", books);
                                }
                            }
                            
                            destination = EDIT_PAGE;
                        }
                        reloadAuthors(request);
                        break;
                    case "editSave":
                        Author a = authorService.findByIdAndFetchBooksEagerly(request.getParameter("authorId"));
                        a.setAuthorName(request.getParameter("authorName"));
                        authorService.edit(a);
                        reloadAuthors(request);
                        break;   
                    case "delete":
                        if(selectedAuthor != null) 
                        {
                            authorService.remove(authorService.findById(selectedAuthor));
                        }
                        reloadAuthors(request);
                        break;
                }
            }
            else
            {
                reloadAuthors(request);
            }
        }
        catch (ClassNotFoundException | SQLException e) 
        {
            destination = LIST_PAGE;
        }
        
        RequestDispatcher view =
                request.getRequestDispatcher(response.encodeURL(destination));
        view.forward(request, response);
    }
    
    private void reloadAuthors(HttpServletRequest request) throws ClassNotFoundException, SQLException
    {
        List<Author> authors = authorService.findAll();
        request.setAttribute("authors", authors);
    }
    
    @Override
    public void init() throws ServletException 
    {
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sctx);
        authorService = (AuthorService) ctx.getBean("authorService");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
