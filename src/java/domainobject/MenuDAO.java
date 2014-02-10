/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domainobject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.RestaurantMenuItem;
import model.RestaurantMenuItem_AnyItem;

/**
 *
 * @author gcDataTechnology
 */
public class MenuDAO {
    MenuDBAccess db;
    
    public MenuDAO(MenuDBAccess db){
        this.db=db;
    }
    
    public Map<Object,List> getMenuItems(){
        List<Object> itemDetails= new ArrayList();
        Map menuItems = new HashMap();
        String sql = "SELECT * FROM menu";
        Statement stmt = null;
        ResultSet rs = null;
        
		try {
                    if(db.connectToDB()){
//                        System.out.println("DB connected");
			stmt = db.conn.createStatement();
			rs = stmt.executeQuery(sql);
                        ResultSetMetaData md = rs.getMetaData();
                        
                        
//                        for(int i=1;i<md.getColumnCount();i++){
//                            itemDetails.add(md.getColumnName(i));
//                        }
//                        menuItems.put("Headers", itemDetails);
                        
			while( rs.next() ) {
                           List details=new ArrayList();
                            
                            details.add((Integer)rs.getInt("id"));
                            details.add(rs.getString("item_long_description"));
                            details.add((Double)rs.getDouble("price"));
                            menuItems.put(rs.getString("item_description"),details);
			}
                    }
                 } catch (SQLException sqle) {
			System.out.println(sqle);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// Make sure we close the statement and connection objects no matter what.
			// Since these also throw checked exceptions, we need a nested try-catch
			try {
				stmt.close();
				db.conn.close();
			} catch(Exception e) {
				System.out.println(e);
			}
		}
        
        return menuItems;
    }
    
    public RestaurantMenuItem lookupMenuItem(int id){
        RestaurantMenuItem item=null;
         String sql = "SELECT * FROM menu WHERE id="+id;
        Statement stmt = null;
        ResultSet rs = null;
        
		try {
                    if(db.connectToDB()){
//                        System.out.println("DB connected");
			stmt = db.conn.createStatement();
			rs = stmt.executeQuery(sql);
                        ResultSetMetaData md = rs.getMetaData();
                        
                        
//                        for(int i=1;i<md.getColumnCount();i++){
//                            itemDetails.add(md.getColumnName(i));
//                        }
//                        menuItems.put("Headers", itemDetails);
                        if(rs!=null){
                            rs.next();
                            item=new RestaurantMenuItem_AnyItem(rs.getInt("id"),rs.getInt("item_category"),
                                                rs.getString("item_description"),rs.getString("item_long_description"),
                                                rs.getDouble("price"));
                        }
                    }
                 } catch (SQLException sqle) {
			System.out.println(sqle);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// Make sure we close the statement and connection objects no matter what.
			// Since these also throw checked exceptions, we need a nested try-catch
			try {
				stmt.close();
				db.conn.close();
			} catch(Exception e) {
				System.out.println(e);
			}
		}
        return item;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MenuDAO menu = new MenuDAO(new MenuDBAccess());
        menu.db.connectToDB();
        Map menuItems = menu.getMenuItems();
        Iterator iterator; 
        
        for(Object s:menuItems.keySet()){
           List details = (ArrayList)menuItems.get(s);
            System.out.println(s);
           iterator = details.iterator();
           while(iterator.hasNext()){
                System.out.print(iterator.next());
           }
        }
        
        
    }
}
