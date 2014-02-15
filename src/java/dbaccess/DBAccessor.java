/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gcDataTechnology
 */
public interface DBAccessor {
    /**
     * Uses a pre-configured connection for a default database
     * @return
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public abstract boolean openConnection()throws IllegalArgumentException, ClassNotFoundException, SQLException;
    
    public abstract boolean openConnection(String driverClassName, String url, String userName, String password)
            throws IllegalArgumentException, ClassNotFoundException, SQLException;
    
    public abstract void closeConnection()throws SQLException;
    
    public abstract Map getRecords(String tableName, List colDescriptors, String whereField, Object whereValue)
            throws SQLException,Exception;
    
    public abstract Map getRecord(String tableName, List colDescriptors, String whereField, Object whereValue)
            throws SQLException,Exception;
    
    public boolean insertRecords(String tableName, List colDescriptors, List values)
	throws SQLException, Exception;
    
    public int updateRecords(String tableName, List colDescriptors, List values, String whereField, Object whereValue)
			 throws SQLException, Exception;
    
    public abstract int deleteRecords(String tableName, String whereField, Object whereValue)
	throws SQLException, Exception;
    
}
