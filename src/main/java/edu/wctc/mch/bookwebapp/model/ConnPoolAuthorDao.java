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
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Mike
 */
public class ConnPoolAuthorDao implements IAuthorDao {
    private DbAccessor db;
    private DataSource ds;

    public ConnPoolAuthorDao(DataSource ds, DbAccessor db) {
        this.db = db;
        this.ds = ds;
    }
    
    @Override
    public List<Author> getAuthorList(String tableName, int maxRecords) throws ClassNotFoundException, SQLException
    {
        List<Author> authorList = new ArrayList<>();
        db.openConnection(ds);
        
        List<Map<String,Object>> rawData = db.findRecordsFor(tableName, maxRecords);
        
        db.closeConnection();
        
        for(Map<String, Object> recData : rawData)
        {
            Author author = new Author();
            Object objAuthorID = recData.get("author_id");
            author.setAuthorId((Integer)objAuthorID);
            
            Object objName = recData.get("author_name");
            String name = objName != null ? objName.toString() : "";
            author.setAuthorName(name);
            
            Object objDate = recData.get("date_added");
            Date dateAdded = objDate != null ? (Date)objDate : null;
            author.setDateAdded(dateAdded);
            
            authorList.add(author);
        }
        
        return authorList;
    }
    
    @Override
    public int deleteAuthor(String tableName, String colName, Object id) throws ClassNotFoundException, SQLException
    {
        int result = 0;
        db.openConnection(ds);
        result = db.deleteById(tableName, colName, id);
        db.closeConnection();
        return result;
    }
    
    @Override
    public int insertAuthor(String tableName, List<String> colNames, List colValues) throws ClassNotFoundException, SQLException
    {
        int result = 0;
        db.openConnection(ds);
        result = db.insertRecord(tableName, colNames, colValues);
        db.closeConnection();
        return result;
    }
    
    @Override
    public int updateAuthor(String tableName, List<String> colNames, List colValues, String colName, Object id) throws ClassNotFoundException, SQLException
    {
        int result = 0;
        db.openConnection(ds);
        result = db.updateRecord(tableName, colNames, colValues, colName, id);
        db.closeConnection();
        return result;
    }
    
    public DbAccessor getDb() 
    {
        return db;
    }

    public void setDb(DbAccessor db) 
    {
        this.db = db;
    }

    
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        IAuthorDao dao = new ConnPoolAuthorDao(new MySqlDbAccessor(), "com.mysql.jdbc.Driver", 
//                "jdbc:mysql://localhost:3306/book", "root", "admin");
//        
        //Get Author List
//        List<Author> authors = dao.getAuthorList("author", 50);
//        
//        for (Author a: authors)
//        {
//            System.out.println(a);
//        }
        
        //Delete Author
//        dao.deleteAuthor("author", "author_id", 5);
        
        //Insert Author
//        Date date = new Date();
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        colNames.add("date_added");
//        List colValues = new ArrayList<>();
//        colValues.add("James Smith");
//        colValues.add(date);
//        dao.insertAuthor("author", colNames, colValues);

        //Update Author
//        Date date = new Date();
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        colNames.add("date_added");
//        List colValues = new ArrayList<>();
//        colValues.add("Jane Doe");
//        colValues.add(date);
//        dao.updateAuthor("author", colNames, colValues, "author_id", 6);
    //}


}
