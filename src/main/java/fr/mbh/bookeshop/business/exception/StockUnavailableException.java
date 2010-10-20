/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.business.exception;

/**
 *
 * @author Mahmoud
 */
public class StockUnavailableException extends Exception{

    public StockUnavailableException(String message) {
        super(message);
    }
}
