package edu.wctc.mch.bookwebapp.controller;

import edu.wctc.mch.bookwebapp.model.Author;
import edu.wctc.mch.bookwebapp.model.AuthorFacade;
import edu.wctc.mch.bookwebapp.model.Book;
import edu.wctc.mch.bookwebapp.model.BookFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
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
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {
    private static final String LIST_PAGE = "bookList.jsp";
    private static final String ADD_PAGE = "bookAdd.jsp";
    private static final String EDIT_PAGE = "bookEdit.jsp";
    private static final String ACTION = "submit";
    
    @EJB
    private BookFacade bookService;
    
    @EJB
    private AuthorFacade authorService;
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
            response.setContentType("text/html;charset=UTF-8");
            
            String action = request.getParameter(ACTION);
            String selectedBook = request.getParameter("bookSelected");
            
            List<Author> authors = null;
            
            String destination = LIST_PAGE;
            try 
            {
                if (action != null)
                {
                    switch(action)
                    {
                    case "add":
                        destination = ADD_PAGE;
                        authors = authorService.findAll();
                        request.setAttribute("authors", authors);
                        break;
                    case "addSave":
                        if (!request.getParameter("bookName").isEmpty())
                        {
                            //bookService.addNew(request.getParameter("authorName"));
                        }
                        reloadBooks(request);
                        break;
                    case "edit":
                        if(selectedBook != null) 
                        {
                            List<Book> books = bookService.findAll();
                            for(Book b: books)
                            {
                                if (b.getBookId() == Integer.valueOf(selectedBook))
                                {
                                    request.setAttribute("book_id", b.getBookId());
                                    request.setAttribute("book_name", b.getTitle());
                                    request.setAttribute("book_isbn", b.getIsbn());
                                    request.setAttribute("book_authorId", b.getAuthorId().getAuthorId());
                                }
                            }
                            authors = authorService.findAll();
                            request.setAttribute("authors", authors);
                            
                            destination = EDIT_PAGE;
                        }
                        reloadBooks(request);
                        break;
                    case "editSave":
                        authors = authorService.findAll();
                        int selectedAuthor = Integer.valueOf(request.getParameter("authorList"));
                            for(Author a: authors)
                            {
                                if (a.getAuthorId() == selectedAuthor)
                                {
                                    bookService.update(request.getParameter("bookId"), request.getParameter("bookName"), 
                                request.getParameter("bookIsbn"), a);
                                }
                            }
                        
                        reloadBooks(request);
                        break;
                    case "delete":
                        if(selectedBook != null) 
                        {
                            bookService.deleteById(selectedBook);
                        }
                        reloadBooks(request);
                        break;
                    }
                }
                else
                {
                    reloadBooks(request);
                }
            }
            catch (Exception e)
            {
                destination = LIST_PAGE;
            }
            
            RequestDispatcher view =
                request.getRequestDispatcher(response.encodeURL(destination));
            view.forward(request, response);
        }
    }
    
    private void reloadBooks(HttpServletRequest request) throws ClassNotFoundException, SQLException
    {
        List<Book> books = bookService.findAll();
        request.setAttribute("books", books);
    }
    
    @Override
    public void init() throws ServletException {
        
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
