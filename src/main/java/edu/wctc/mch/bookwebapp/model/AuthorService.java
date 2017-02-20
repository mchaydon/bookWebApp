/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mike
 */
public class AuthorService {
    private IAuthorDao dao;

    public AuthorService(IAuthorDao dao) {
        this.dao = dao;
    }
    
    public IAuthorDao getDao() {
        return dao;
    }

    public void setDao(IAuthorDao dao) {
        this.dao = dao;
    }
    
    public List<Author> getAllAuthors(String tableName, int maxRecords) throws ClassNotFoundException, SQLException
    {
        return dao.getAuthorList(tableName, maxRecords);
    }
        
//    private List<Author> authors = new ArrayList<>();
//    private Date date = new Date();
//    
//    public final void writeAuthors()
//    {
//        authors.add(new Author(001, "J.K. Rowling", date));
//        authors.add(new Author(002, "George Orwell", date));
//        authors.add(new Author(003, "James Patterson", date));
//        authors.add(new Author(004, "Christopher Paolini", date));
//    }
//    
//    public final List<Author> getAuthorsList()
//    {
//        return authors;
//    }
//
//    public AuthorService()
//    {
//        writeAuthors();
//    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService authorService = new AuthorService
        (
                new AuthorDao
                (
                    new MySqlDbAccessor(), "com.mysql.jdbc.Driver", 
                    "jdbc:mysql://localhost:3306/book", "root", 
                    "admin"
                )
        );
        
        List<Author> authors = authorService.getAllAuthors("author", 50);
        
        for (Author a: authors)
        {
            System.out.println(a);
        }
    }
}
