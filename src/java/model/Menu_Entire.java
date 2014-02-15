/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import domainobject.MenuDAO;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gcDataTechnology
 */
public class Menu_Entire implements Menu{
    MenuDAO menuAccessor;
    
    public Menu_Entire(){
        
    }
    
    public Menu_Entire(MenuDAO menuAccessor){
        setMenuDAO(menuAccessor);
    }
    
     @Override
    public void setMenuDAO(MenuDAO menuAccessor) {
       if(menuAccessor !=null){
           this.menuAccessor=menuAccessor;
       }
    }
    
    @Override
    public Map showMenu() {
        Map m=null;
        try {
            m= menuAccessor.getMenuItems();
        } catch (Exception ex) {
            Logger.getLogger(Menu_Entire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    /**
     * provided to retrieve customer order
     * @param customerOrder Map of customer selected items
     */
    @Override
    public void takeOrder(Map customerOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * provided to write customer order to database
     * @param customerOrder Map of customer selected items
     */
    @Override
    public void placeOrder(Map customerOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
