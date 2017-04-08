/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mike
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "edu.wctc.mch_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
    public int deleteById(String id)
    {
        Integer iId = Integer.parseInt(id);
        String jpql = "delete from Book b where b.bookId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
        return q.executeUpdate();
    }
    
     public void update(String id, String name, String isbn, Author author)
    {
        Book b = this.find(Integer.parseInt(id));
        b.setTitle(name);
        b.setIsbn(isbn);
        b.setAuthorId(author);
        this.edit(b);
    }
    
}
