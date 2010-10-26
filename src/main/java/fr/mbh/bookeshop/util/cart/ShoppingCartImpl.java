/*
 * This file is part of the Book-eShop project.
 *
 *  Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 *
 * Author :
 * 	Mahmoud Ben Hassine <md.benhassine@gmail.com>
 */

package fr.mbh.bookeshop.util.cart;

import java.util.HashMap;
import java.util.Map;

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
