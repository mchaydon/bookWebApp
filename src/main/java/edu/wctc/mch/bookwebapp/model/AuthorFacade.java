/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.model;

import static edu.wctc.mch.bookwebapp.model.Author_.authorId;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mike
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "edu.wctc.mch_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    BookFacade bookService;

    public AuthorFacade() {
        super(Author.class);
    }
    
    public int deleteById(String id)
    {
        List<Book> books = bookService.findBookByAuthorId(id);
        for (Book b: books)
        {
            bookService.deleteById(String.valueOf(b.getBookId()));
        }
        Integer iId = Integer.parseInt(id);
        String jpql = "delete from Author a where a.authorId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
        return q.executeUpdate();
    }
    
    public void addNew(String name)
    {
        Author a = new Author();
        a.setAuthorName(name);
        Date createDate = new Date();
        a.setDateAdded(createDate);
        this.create(a);
    }
    
    public void update(String id, String name)
    {
        Author a = this.find(Integer.parseInt(id));
        a.setAuthorName(name);
        this.edit(a);
    }
    
    public Author findAuthorById(String id)
     {
        Integer iId = Integer.parseInt(id);
        String jpql = "Select a from Author a where a.authorId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
        return (Author) q.getSingleResult();
     }
}
