/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.dao.exception;

/**
 *
 * @author Mahmoud
 */
public class InsufficientStockException extends Exception {

    public InsufficientStockException(String s) {
        super(s);
    }
}
