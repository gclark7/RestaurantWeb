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
public class Gratuity_15percent implements Gratuity{
    private final double defaultTip = 0.15;
    private final String ERR_AMOUNT="checkAmount was incorrect";
    
    
    public Gratuity_15percent(){
        
    }
    
    /***
     * Calculate and return tip
     * @param checkAmount value to calculate tip against
     * @return double value of tip
     */
    @Override
    public double calculateGratuity(double checkAmount) {
        if(checkAmount<0|| checkAmount==0){
            throw new UnsupportedOperationException(ERR_AMOUNT);
        } else{
            return checkAmount * defaultTip;
        }
    }

    /**
     * Calculate tip and return check amount including tip 
     * @param checkAmount value to calculate tip against
     * @return checkAmount + tip
     */
    @Override
    public double calculateGratuityWithCheck(double checkAmount) {
        if(checkAmount<0|| checkAmount==0){
            throw new UnsupportedOperationException(ERR_AMOUNT);
        } else{
            return checkAmount * (1+defaultTip);
        }
    }
    
}
