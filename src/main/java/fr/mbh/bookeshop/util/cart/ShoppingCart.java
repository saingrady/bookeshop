/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.util.cart;

import java.util.Map;

/**
 *
 * @author Mahmoud
 */
public interface ShoppingCart {

    public int getCount();

    public void addItem(String bookId);

    public void clearCart();

    public Map<String, Integer> getItems();

    public void removeItem(String bookId);

}
