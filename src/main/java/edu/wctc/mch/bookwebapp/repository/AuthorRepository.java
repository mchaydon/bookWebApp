package edu.wctc.mch.bookwebapp.repository;

import edu.wctc.mch.bookwebapp.entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mike
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable{
    
}
