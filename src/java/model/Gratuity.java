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
public interface Gratuity {
    /**
     * Provided to calculate the gratuity suggested for a given customer check
     * @return double value of gratuity based upon customer check value
     */
    public abstract double calculateGratuity(double checkAmount);
    
    /**
     * Provided to return the total bill including suggested gratuity
     * @param checkAmount customer bill
     * @return double value of the bill including gratuity
     */
    public abstract double calculateGratuityWithCheck(double checkAmount);
}
