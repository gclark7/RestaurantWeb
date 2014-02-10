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
public class Tax_WI_WKA implements Tax{
    private double TAX=0.0010;
    private final String ERR_AMOUNT="checkAmount was incorrect";
    
    /**
     * Default constructor
     */
    public Tax_WI_WKA(){
        
    }
    /**
     * Calculates the county tax to add to the bill
     * @param checkAmount customer bill to tax
     * @return tax amount to add to the bill
     */
    @Override
    public double calculateTax(double checkAmount) {
       if(checkAmount<0|| checkAmount==0){
            throw new UnsupportedOperationException(ERR_AMOUNT);
        } else{
            return checkAmount * TAX;
        }
    }

    /**
     * We suggest using calculateTax(double checkAmount) as it will calculate the appropriate amount of tax to add
     * 
     * In this scenario the amount must be the base amount prior to State tax as this will add county tax to the amount
     * Since it was based upon an interface it will do what the method name suggests --> add calculated amount to amount provided
     * @param checkAmount customer bill to tax
     * @return bill + tax amount
     */
    @Override
    public double calculateBillWithTax(double checkAmount) {
        if(checkAmount<0|| checkAmount==0){
            throw new UnsupportedOperationException(ERR_AMOUNT);
        } else{
            return checkAmount * (1+TAX);
        }
    }
    
}
