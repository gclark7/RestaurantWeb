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
public class Tax_WI implements Tax{
    private double TAX=0.05;
    private final String ERR_AMOUNT="checkAmount was incorrect";
    private Tax[] additionalTaxes;
    /**
     * Constructor 
     */
    public Tax_WI(){
        
    }
    
    /**
     * Calculates the county tax to add to the bill
     * @param checkAmount customer bill to tax
     * @return tax amount to add to the bill including additional taxes for County and State
     */
    @Override
    public double calculateTax(double checkAmount) {
       if(checkAmount<0|| checkAmount==0){
            throw new UnsupportedOperationException(ERR_AMOUNT);
        } else{
            return (checkAmount * TAX) + calculateAdditionalTaxes(checkAmount);
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
            return (checkAmount * (1+TAX)) + calculateAdditionalTaxes(checkAmount);
        }
    }
    
    /**
     * Will eventually look up a database object to retrieve the appropriate tax additions
     * @param checkAmount amount to tax
     * @return value to add to tax amount
     */
    private double calculateAdditionalTaxes(double checkAmount){
        //double additionalTaxes=0.00;
        if(checkAmount<0|| checkAmount==0){
            throw new UnsupportedOperationException(ERR_AMOUNT);
        }else{
            return new Tax_WI_WKA().calculateTax(checkAmount);
        }
        
    }
}
