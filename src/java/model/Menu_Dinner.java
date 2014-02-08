/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import domainobject.MenuDAO;
import java.util.Map;

/**
 *
 * @author gcDataTechnology
 */
public class Menu_Dinner implements Menu{
    MenuDAO menuAccessor;
    
     @Override
    public void setMenuDAO(MenuDAO menuAccessor) {
       if(menuAccessor !=null){
           this.menuAccessor=menuAccessor;
       }
    }
    
    @Override
    public Map showMenu() {
        return menuAccessor.getMenuItems();
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
