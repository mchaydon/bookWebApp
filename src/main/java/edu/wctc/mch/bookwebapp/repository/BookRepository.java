package edu.wctc.mch.bookwebapp.repository;

import edu.wctc.mch.bookwebapp.entity.Book;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mike
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, Serializable{
    
}
