/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author gcDataTechnology
 */
public interface RestaurantMenuItem {
    
    public abstract void setPrice(double price);
    public abstract void setShortDescription(String desc);
    public abstract void setLongDescription(String lngDesc);
    public abstract void setID(int id);
    public abstract void setCategoryID(int catID);
    public abstract double getPrice();
    public abstract String getShortDescription();
    public abstract String getLongDescription();
    public abstract int getID();
    public abstract int getCategoryID();
    
}
