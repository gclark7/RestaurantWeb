/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domainobject;

import dbaccess.MenuDBAccess;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import model.RestaurantMenuItem;
import model.RestaurantMenuItem_AnyItem;

/**
 *
 * @author gcDataTechnology
 */
public class MenuDAO {
    private MenuDBAccess db;
    private final String DATABASE="restaurant";
    private final String TABLE_MENU="menu";
    private final String ID="id";
    private final String LDESCRIPTION="item_long_description";
    private final String PRICE="price";
    private final String SDESCRIPTION="item_description";
    private final String CAT="item_category";
    
               
                
    public MenuDAO(MenuDBAccess db){
        this.db=db;
    }
    
    public Map<String,Object> getMenuItems() throws Exception{
        //List<Object> itemDetails= new ArrayList();
        Map<String, Object> mItems=new LinkedHashMap();
        Map<String, List> temp = new LinkedHashMap();
        List fields = new ArrayList();
        
        
        fields.add(ID);
        fields.add(CAT);
        fields.add(SDESCRIPTION);
        fields.add(LDESCRIPTION);
        fields.add(PRICE);
       
                
        
        temp=this.db.getRecords(TABLE_MENU, fields, null, null);
        if(temp!=null){
            for(Object t:temp.keySet()){
//                System.out.println("Line 61 " + (int)temp.get(t).get(0) + " " +
//                    (int)temp.get(t).get(1) +" "+
//                    (String)temp.get(t).get(2)+ " " +
//                    (String)temp.get(t).get(3)+ " "+
//                    (double)temp.get(t).get(4));
//                       
                
                RestaurantMenuItem item = new RestaurantMenuItem_AnyItem(
                    Integer.parseInt(temp.get(t).get(0).toString()),
                    Integer.parseInt(temp.get(t).get(1).toString()), 
                    (String)temp.get(t).get(2),
                    (String)temp.get(t).get(3),
                    Double.parseDouble(temp.get(t).get(4).toString()));
                
//                List details = new ArrayList();
//                for(int i=0;i<menuItems.get(t).size();i++){
//                    details.add(menuItems.get(t).get(i));
//                }
                mItems.put(item.getShortDescription(),item);
            }
        }
        
        
        
        /*
        String sql = "SELECT * FROM menu";
        Statement stmt = null;
        ResultSet rs = null;
		try {
                    if(db.openConnection()){
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
        */
        return mItems;
    }
    
    public RestaurantMenuItem lookupMenuItem(int id) throws Exception{
        
        Map<String, Object> mItems=new LinkedHashMap();
        Map<String, List> temp = new LinkedHashMap();
        List fields = new ArrayList();
        RestaurantMenuItem item =null;
        
        
        fields.add(ID);
        fields.add(CAT);
        fields.add(SDESCRIPTION);
        fields.add(LDESCRIPTION);
        fields.add(PRICE);
       
                
        
        temp=this.db.getRecords(TABLE_MENU, fields, ID, id);
        if(temp!=null){
            for(Object t:temp.keySet()){
                
                item = new RestaurantMenuItem_AnyItem(
                    Integer.parseInt(temp.get(t).get(0).toString()),
                    Integer.parseInt(temp.get(t).get(1).toString()), 
                    (String)temp.get(t).get(2),
                    (String)temp.get(t).get(3),
                    Double.parseDouble(temp.get(t).get(4).toString()));
                
                
                mItems.put(item.getShortDescription(),item);
            }
        }
        
        return item;
    }
    
    /**
     * Works with method createAdminFormInsertMenuItems() to ensure data integrity 
     * 
     * @param columns_values Map of columns and values to insert into table
     * @return int value indicating number of records successfully inserted
     * @throws Exception 
     */
    public int insertNewMenuItems(List<Map> columns_values) throws Exception{
        List fields= new Vector();
        List values= null;
        int count=0;
        fields.add(LDESCRIPTION);
        fields.add(SDESCRIPTION);
        fields.add(CAT);
        fields.add(PRICE);
        
        if(columns_values!=null){
            for(int i=0;i<columns_values.size();i++){
                values= new Vector();
                values.add((String)columns_values.get(i).get(LDESCRIPTION));
                values.add((String)columns_values.get(i).get(SDESCRIPTION));
                values.add(Integer.parseInt(columns_values.get(i).get(CAT).toString()));
                values.add(Double.parseDouble(columns_values.get(i).get(PRICE).toString()));
                
                if(db.insertRecords(TABLE_MENU, fields, values)){
                    count++;
                };
            }
        }
        return count;
    }
    
    public int updateMenuItems(Map columns_values, String whereField, Object whereValue) throws Exception{
        int count=0;
        if(columns_values!=null && whereField!=null && whereValue!=null){
            List fields=new Vector();
            List colDescriptors=new Vector();
            List values= new Vector();
            fields.add(LDESCRIPTION);
            fields.add(SDESCRIPTION);
            fields.add(CAT);
            fields.add(PRICE);
            for(Object o:columns_values.keySet()){
                if(columns_values.get(o)!=null){
                    colDescriptors.add(o);
                    Object oj = columns_values.get(o);
                    if(oj instanceof String){
                           values.add((String)oj );
                    } else if(oj instanceof Integer ){
                            values.add(((Integer)oj).intValue() );
                    } else if(oj instanceof Long ){
                            values.add(((Long)oj).longValue() );
                    } else if(oj instanceof Double ){
                            values.add(((Double)oj).doubleValue() );
                    } else if(oj instanceof java.sql.Date ){
                           values.add((java.sql.Date)oj );
                    } else if(oj instanceof Boolean ){
                            values.add(((Boolean)oj).booleanValue() );
                    } else {
                            if(oj != null){
                                values.add(oj);
                            }
                    }
                        
                }
                
//                values.add((String)columns_values.get(i).get(LDESCRIPTION));
//                values.add((String)columns_values.get(i).get(SDESCRIPTION));
//                values.add(Integer.parseInt(columns_values.get(i).get(CAT).toString()));
//                values.add(Double.parseDouble(columns_values.get(i).get(PRICE).toString()));
               
            }
             count+=(db.updateRecords(TABLE_MENU, colDescriptors, values, whereField, whereValue));
        }
        return count;
    }
    
    public int deleteRecords(int id) throws Exception{
        int count=0;
        
            count+=this.db.deleteRecords(TABLE_MENU, ID, id);
      
        return count;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception {
        MenuDAO menu = new MenuDAO(new MenuDBAccess());
        //menu.db.openConnection();
        Map menuItems =  menu.getMenuItems();
        //Iterator iterator; 
        RestaurantMenuItem_AnyItem item=null;
        for(Object s:menuItems.keySet()){
           //List details = (ArrayList)menuItems.get(s);
            System.out.println(s);
           //iterator = details.iterator();
           //while(iterator.hasNext()){
           //     System.out.print(iterator.next());
           //}
            item=(RestaurantMenuItem_AnyItem)menuItems.get(s);
            System.out.println(item.getLongDescription() + " \n" + item.getShortDescription() + "\n\n");
        }
/*
        List records=new Vector();
        //List values = new Vector();
        Map m = new HashMap();
        m.put("item_long_description", "Spegehatti in meat sauce");
        m.put("price", 15.12);
        m.put("item_description", "Speghetti");
        m.put("item_category",3);
        records.add(m);
        
        m = new HashMap();
        m.put("item_long_description", "Vegetarian medly");
        m.put("price", 75.28);
        m.put("item_description", "Vegetarian Soup");
        m.put("item_category",1);
        records.add(m);
        
     
        
        System.out.println(menu.insertNewMenuItems(records));
        */
        
        //test update
        /*
        Map m = new HashMap();
        m.put("item_long_description", "Angel Hair Pasta in meat sauce");
        System.out.println(menu.updateMenuItems(m, "item_long_description", "Spegehatti in meat sauce"));
        */
        System.out.println(menu.lookupMenuItem(1).getLongDescription());
        System.out.println(menu.deleteRecords(5));
    }
}
