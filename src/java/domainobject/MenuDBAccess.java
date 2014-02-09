/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domainobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gcDataTechnology
 */
public class MenuDBAccess {
    public Connection conn;
    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    private final String CONN_FAILED="Connection Failed";
        
    //testing purposes --> static values hard coded for lab only
    public MenuDBAccess(){
        driverClassName = "com.mysql.jdbc.Driver";
	url = "jdbc:mysql://localhost:3306/restaurant";//?zeroDateTimeBehavior=convertToNull";
	userName = "root";
	password = "";
        
    }
    
    public boolean connectToDB() throws SQLException,
                                        ClassNotFoundException{
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
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        
        MenuDBAccess db = new MenuDBAccess();
        db.connectToDB();
        
    }
            
    
}
