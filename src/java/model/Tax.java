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
public interface Tax {
    /**
     * Provided to calculate tax --> Abstract for the ability to plug in any tax rate
     * @param checkAmount amount the order costs pretax
     * @return double tax value Only
     */
    public double calculateTax(double checkAmount);
    
    /**
     * Provided to return the final bill
     * @param checkAmount amount the order costs pretax
     * @return final bill amount including tax
     */
    public double calculateBillWithTax(double checkAmount);
}
