/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Mike
 */
public interface DbAccessor {

    public abstract void closeConnection() throws SQLException;

    public abstract List<Map<String, Object>> findRecordsFor(String tableName, int maxRecords) throws SQLException;

    //Consider creating custom exception
    public abstract void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
    public abstract int deleteById(String tableName, String colName, Object id)  throws SQLException;
    
    public abstract int insertRecord(String tableName, List<String> colNames, List colValues) throws SQLException;
    
    public int updateRecord(String tableName, List<String> colNames, List colValues, String colName, Object id) throws SQLException;
    
    public void openConnection(DataSource ds) throws SQLException;
}
