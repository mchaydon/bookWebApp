/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mike
 */
public class AuthorService {
    private List<Author> authors = new ArrayList<>();
    private Date date = new Date();
    
    public final void writeAuthors()
    {
        authors.add(new Author(001, "J.K. Rowling", date));
        authors.add(new Author(002, "George Orwell", date));
        authors.add(new Author(003, "James Patterson", date));
        authors.add(new Author(004, "Christopher Paolini", date));
    }
    
    public final List<Author> getAuthorsList()
    {
        return authors;
    }

    public AuthorService()
    {
        writeAuthors();
    }
    
    
}
