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
public interface Menu {
    /**
     * 2/8/2014 currently this is a concrete class --> better served as a strategy object
     * provided to make a connection based upon a database access object
     * @param db db access object
     */
    public abstract void setMenuDAO(MenuDAO menuAccessor);
    /**
     * return a Map of menu items
     */
    public abstract Map showMenu();
    /**
     * retrieve Map of customer selected menu items
     * @param customerOrder Map of customer selected menu items
     */
    public abstract void takeOrder(Map customerOrder);
    /**
     * write the order from method takeOrder() to the database for processing
     */
    public abstract void placeOrder(Map customerOrder);
}
