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
    
    public int deleteAuthor(String tableName, String colName, Object id) throws ClassNotFoundException, SQLException
    {
        return dao.deleteAuthor(tableName, colName, id);
    }
    
    public int insertAuthor(String tableName, List<String> colNames, List colValues) throws ClassNotFoundException, SQLException
    {
        return dao.insertAuthor(tableName, colNames, colValues);
    }
    
    public int updateAuthor(String tableName, List<String> colNames, List colValues, String colName, Object id) throws ClassNotFoundException, SQLException
    {
        return dao.updateAuthor(tableName, colNames, colValues, colName, id);
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
        
//        //List test
//        List<Author> authors = authorService.getAllAuthors("author", 50);
//        
//        for (Author a: authors)
//        {
//            System.out.println(a);
//        }

        //Delete Test
//        authorService.deleteAuthor("author", "author_id", 12);
//        authorService.deleteAuthor("author", "author_id", 13);
        
        //Insert Test
//        Date date = new Date();
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        colNames.add("date_added");
//        List colValues = new ArrayList<>();
//        colValues.add("James Smith");
//        colValues.add(date);
//        authorService.insertAuthor("author", colNames, colValues);
        
        //Update Test
        //Date date = new Date();
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        //colNames.add("date_added");
//        List colValues = new ArrayList<>();
//        colValues.add("Albert Doe");
//        //colValues.add(date);
//        authorService.updateAuthor("author", colNames, colValues, "author_id", 7);
    }
}
