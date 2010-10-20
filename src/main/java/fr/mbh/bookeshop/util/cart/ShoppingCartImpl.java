/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.util.cart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mahmoud
 */
public class ShoppingCartImpl implements ShoppingCart {

    /*
     * Using Map<Book,Integer> causes problem when loading books from DB
     * Two different loaded books may have the same id but not the same object id
     * NB : use bookId as map key instead
     */
    //<bookId,quantity> map
    private Map<String,Integer> items;

    private int count;

    public ShoppingCartImpl() {
        this.items = new HashMap<String,Integer>();
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void addItem(String bookId){
        if (items.containsKey(bookId) )
            items.put(bookId, items.get(bookId) + 1);
        else
            items.put(bookId, 1);
        count++;
    }

    public void removeItem(String bookId){
        if (items.containsKey(bookId) ){
            int quantity = items.get(bookId) - 1;
            if(quantity == 0)
                items.remove(bookId);
            else 
                items.put(bookId,quantity);
            count--;
        }
        
    }

    public void clearCart(){
        items.clear();
        count = 0;
    }

    public Map<String,Integer> getItems(){
        return items;
    }
}
