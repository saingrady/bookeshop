/*
 * The MIT License
 *
 *   Copyright (c) 2012, Mahmoud Ben Hassine (md.benhassine@gmail.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package org.benassi.bookeshop.web.cart;

import java.util.HashMap;
import java.util.Map;

/**
 * Shopping cart implementation
 * @author Mahmoud Ben Hassine
 */
public class ShoppingCart {

    //<bookId,quantity> map
    private Map<String,Integer> items;

    private int count;

    public ShoppingCart() {
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
