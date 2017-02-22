/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mch.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author Mike
 */
public class MySqlDbAccessor implements DbAccessor {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    
    //Consider creating custom exception
    @Override
    public void openConnection(String driverClass, String url, String userName,
            String password) throws ClassNotFoundException, SQLException
    {
        //include validation for strings to check not null
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }
    
    @Override
    public void closeConnection() throws SQLException
    {
        if (conn != null)
        {
            conn.close();
        }
    }
    
    @Override
    public List <Map<String,Object>> findRecordsFor(String tableName, int maxRecords) 
            throws SQLException
    {
        
        String sql = "";
        if (maxRecords > 0)
        {
            sql = "Select * from " + tableName + " limit " + maxRecords;
        }
        else
        {
            sql = "Select * from " + tableName;
        }
        
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        List<Map<String,Object>> results = new ArrayList<>();
        
        ResultSetMetaData rsmd = rs.getMetaData();
        
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;
        
        while (rs.next())
        {
            record = new LinkedHashMap<>();
            for (int colNo = 1; colNo <= colCount; colNo++)
            {
                String colName = rsmd.getColumnName(colNo);
                record.put(colName, rs.getObject(colNo));
            }
            results.add(record);
        }
        
        return results;
    }
    
    @Override
    public int deleteById(String tableName, String colName, Object id) throws SQLException
    {
        //sql = "DELETE FROM actor WHERE" + " actor_id = " + deleteId;
//        String sId = null;
//        Integer intId = null;
        
        String sql = "DELETE FROM " + tableName + " WHERE " + colName + " = ";
        if (id instanceof String)
        {
//            sId = id.toString();
//            sql += sId;
            sql += id.toString();
        }
        else if (id instanceof Integer)
        {
//            intId = (Integer)id;
//            sql += intId;
            sql += (Integer)id;
        }
        
        
        stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }
    
    @Override
    public int insertRecord(String tableName, List<String> colNames, List colValues) throws SQLException
    {
        //sql = "INSERT INTO actor (first_name,last_name)" + " VALUES('Billy','Carter')";
        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner stringJoiner = new StringJoiner(",", "(", ")");
        
        for(String col : colNames)
        {
            stringJoiner.add(col);
        }
        
        sql += stringJoiner.toString();
        stringJoiner = new StringJoiner(",", "(", ")");
        
        sql += " VALUES ";
        
        for(Object val : colValues)
        {
            stringJoiner.add("?");
        }
        
        sql += stringJoiner.toString();
        System.out.println(sql);
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        for(int i = 0; i < colValues.size(); i++)
        {
            pstm.setObject(i + 1, colValues.get(i));
        }
        
        return pstm.executeUpdate();
    }
    
    @Override
    public int updateRecord(String tableName, List<String> colNames, List colValues, String colName, Object id) throws SQLException
    {
        //UPDATE table_name SET column1=value1,column2=value2 WHERE some_column=some_value;
        String sql = "UPDATE " + tableName + " SET ";
        
        StringJoiner stringJoiner = new StringJoiner(",");
        
        for (String col: colNames)
        {
            stringJoiner.add(col + "=?");
        }
        
        sql += stringJoiner.toString();
        
        sql += " WHERE " + colName + "=";
        
        if (id instanceof String)
        {
            sql += id.toString();
        }
        else if (id instanceof Integer)
        {
            sql += (Integer)id;
        }
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        for(int i = 0; i < colValues.size(); i++)
        {
            pstm.setObject(i + 1, colValues.get(i));
        }
        
        return pstm.executeUpdate();
    }
    
//    public static void main(String[] args) throws Exception {
//        DbAccessor db = new MySqlDbAccessor();
//        
//        db.openConnection("com.mysql.jdbc.Driver", 
//                "jdbc:mysql://localhost:3306/book", "root", 
//                "admin");
        //List<Map<String,Object>> records = db.findRecordsFor("author", 50);
        //db.deleteById("author", "author_id", 2);
        
        
//        Date date = new Date();
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        colNames.add("date_added");
//        List colValues = new ArrayList<>();
//        colValues.add("James Smith");
//        colValues.add(date);
//        //db.insertRecord("author", colNames, colValues);
//        db.updateRecord("author", colNames, colValues, "author_id", 5);
//        db.closeConnection();
        
//        for(Map<String,Object> record : records)
//        {
//            System.out.println(record);
//        }
//    }
}
