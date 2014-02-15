/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gcDataTechnology
 */
public class MenuDBAccess implements DBAccessor{
    public Connection conn;
    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    private final String CONN_FAILED="Connection Failed";
    private final String SELECT="SELECT";
    private final String INSERT="INSERT";
    private final String UPDATE="UPDATE";
    private final String HAVING="HAVING";
    private final String GROUP_BY="GROUP BY";
    private final String WHERE="WHERE";
    private final String FROM="FROM";
    private final String ORDER_BY="Order By";
    private final String ASC="ASC";
    private final String DESC="DESC";
        
    //testing purposes --> static values hard coded for lab only
    public MenuDBAccess(){
        driverClassName = "com.mysql.jdbc.Driver";
	url = "jdbc:mysql://localhost:3306/restaurant";//?zeroDateTimeBehavior=convertToNull";
	userName = "root";
	password = "";
        
    }
    
    public MenuDBAccess(String driverClassName, String url, String userName, String password){
        this.driverClassName = driverClassName;
	this.url = url;//?zeroDateTimeBehavior=convertToNull";
	this.userName = userName;
	this.password = password;
    }
    
     public boolean openConnection() throws IllegalArgumentException, ClassNotFoundException, SQLException {
        boolean connected=false;
        
        try {
              Class.forName (this.driverClassName);
              this.conn = DriverManager.getConnection(url, userName, password);
              //this.conn = DriverManager.getConnection(url);
              connected=true;
	}catch(Exception e){
                    System.out.println("DB Error" + e);
        }
		
                
        return connected;
    }
    
     /**
      * Will use the first 
      * @param driverClassName
      * @param url
      * @param userName
      * @param password
      * @return
      * @throws IllegalArgumentException
      * @throws ClassNotFoundException
      * @throws SQLException 
      */
    public boolean openConnection(String driverClassName, String url, String userName, String password) throws IllegalArgumentException, ClassNotFoundException, SQLException {
        boolean connected=false;
        
        try {
              Class.forName (driverClassName);
              this.conn = DriverManager.getConnection(url, userName, password);
              //this.conn = DriverManager.getConnection(url);
              connected=true;
	}catch(Exception e){
                    System.out.println("DB Error" + e);
        }
		
                
        return connected;
    }
    
    

    @Override
    public void closeConnection() throws SQLException {
        conn.close(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Will use the first colDescriptor as the Map key, if not provided the database default will be used
     * @param tableName
     * @param colDescriptors
     * @param whereField
     * @param whereValue
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    @Override
    public Map getRecords(String tableName, List colDescriptors, String whereField, Object whereValue) throws SQLException, Exception {
        Map m=null;
        
        ResultSet rs = null;
        PreparedStatement ps = null;
        Statement stmt=null;
        StringBuffer sb=null;
        int placeHolder=1;
        int moreCol=2;
        int col=colDescriptors.size();
        
        //Build the query
        if(!tableName.isEmpty()){
            sb=new StringBuffer(SELECT + " ");
            if(!colDescriptors.isEmpty()){
                for(Object o:colDescriptors){
                    sb.append((String)o).append(", ");
                }
                //remove last comma
                sb.deleteCharAt(sb.lastIndexOf(", "));
            }
            sb.append(FROM +" " + tableName);
            if(whereField!=null & whereValue!=null){
                //add where clause
                sb.append(" " + WHERE + " " + whereField +" = " + whereValue);
            }
            sb.append(" " + ORDER_BY + " " + colDescriptors.get(0) + " " + ASC);
            
            //System.out.println("Line 149 " + sb);
            //Return the ResultSet & Map the record(s)
            try{
                m=new HashMap();
                openConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sb.toString());
                while( rs.next() ) {
                   List details=new ArrayList();
                   //if(col>=moreCol){
                       for(int i=placeHolder;i<=col;i++){//Start at second column
                       details.add(rs.getObject(i));
                         //  System.out.println("Line 160 " + rs.getObject(i));
                       }
                   //}else{details.add(rs.getObject(placeHolder));}
                   m.put(rs.getObject(placeHolder),details);
                }
            }catch(SQLException s){
                throw s;
            }finally{
                try{
                    stmt.close();
                    closeConnection();
                }catch(SQLException s){
                    throw s;
                }
            }
        }
        
        return m;
    }

    /**
     * Will use the first colDescriptor as the Map key, if not provided the database default will be used
     * Provide a tableName to query the database.
     * Provide Only a tableName to retrieve all Fields
     * Provide null values for omit where clause
     * @param tableName table to query
     * @param colDescriptors columns to retrieve
     * @param whereField field to restrict
     * @param whereValue value of restriction
     * @return Map of records
     * @throws SQLException
     * @throws Exception 
     */
    @Override
    public Map getRecord(String tableName, List colDescriptors, String whereField, Object whereValue) throws SQLException, Exception {
        Map m=null;
        
        
        
        return m;
    }

    @Override
    public boolean insertRecords(String tableName, List colDescriptors, List values) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateRecords(String tableName, List colDescriptors, List values, String whereField, Object whereValue) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteRecords(String tableName, String whereField, Object whereValue) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
 
     public static void main(String[] args) throws SQLException, ClassNotFoundException{
        
        MenuDBAccess db = new MenuDBAccess();
         
        System.out.println(db.openConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/restaurant","root",""));
        Map<Object, List> menuItems = new LinkedHashMap();
        List fields = new ArrayList();
        
        String DATABASE="restaurant";
        String TABLE_MENU="menu";
        String ID="id";
        String LDESCRIPTION="item_long_description";
        String PRICE="price";
        String SDESCRIPTION="item_description";
    
        fields.add(SDESCRIPTION);
        fields.add(ID);
        fields.add(LDESCRIPTION);
        fields.add(PRICE);
        try {
            menuItems=db.getRecords(TABLE_MENU, fields, null, null);
            for(Object o:menuItems.keySet()){
                for(int i=0;i<menuItems.get(o).size();i++){
                    System.out.println(o + " : " + menuItems.get(o).get(i));
                }
            }
        } catch (Exception ex) {
            System.out.println("In Main " +  ex);
        }
    }
     
     
}