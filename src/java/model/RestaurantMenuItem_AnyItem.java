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
public class RestaurantMenuItem_AnyItem implements RestaurantMenuItem{
    private double price;
    private String shortDescription;
    private String longDescription;
    private int ID;
    private int categoryID;
    
    private final String ERR_NULL="Null value or Incorect value";
    
    public RestaurantMenuItem_AnyItem(int id, int catID, String description, String lngDescription, double price){
        setPrice(price);
        setShortDescription(description);
        setLongDescription(lngDescription);
        setCategoryID(catID);
        setID(id);
    }
    @Override
    public void setPrice(double price) {
        if(price<0){
            throw new UnsupportedOperationException(ERR_NULL); 
        }else{this.price=price;}
    }

    @Override
    public void setShortDescription(String desc) {
        if(desc.isEmpty()){
            throw new UnsupportedOperationException(ERR_NULL); 
        }else{shortDescription=desc;}
    }

    @Override
    public void setLongDescription(String lngDesc) {
         if(lngDesc.isEmpty()){
            throw new UnsupportedOperationException(ERR_NULL); 
        }else{longDescription=lngDesc;}
    }

    @Override
    public void setID(int id) {
         if(id<0){
            throw new UnsupportedOperationException(ERR_NULL); 
        }else{this.ID=id;}
    }

    @Override
    public void setCategoryID(int catID) {
       if(catID<0){
            throw new UnsupportedOperationException(ERR_NULL); 
        }else{this.categoryID=catID;}
    }

    @Override
    public double getPrice() {
      return price;
    }

    @Override
    public String getShortDescription() {
      return shortDescription;
    }

    @Override
    public String getLongDescription() {
       return longDescription; 
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public int getCategoryID() {
       return categoryID;
    }
    
}
