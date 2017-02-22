/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface IAuthorDao {

    public abstract List<Author> getAuthorList(String tableName, int maxRecords) throws ClassNotFoundException, SQLException;
    public abstract int deleteAuthor(String tableName, String colName, Object id) throws ClassNotFoundException, SQLException;
    public abstract int insertAuthor(String tableName, List<String> colNames, List colValues) throws ClassNotFoundException, SQLException;
    public abstract int updateAuthor(String tableName, List<String> colNames, List colValues, String colName, Object id) throws ClassNotFoundException, SQLException;

    DbAccessor getDb();

    String getDriverClass();

    String getPassword();

    String getUrl();

    String getUserName();

    void setDb(DbAccessor db);

    void setDriverClass(String driverClass);

    void setPassword(String password);

    void setUrl(String url);

    void setUserName(String userName);
    
}
