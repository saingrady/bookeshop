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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Shopping cart unit tests
 */
public class ShoppingCartTest {

    private ShoppingCart cart;

    @Before
    public void setUp() throws Exception {
        cart = new ShoppingCart();
        cart.addItem("9781430216407");
        cart.addItem("9781430219088");
    }

    @After
    public void tearDown() throws Exception {
        cart = null;
        System.gc();
    }

    @Test
    public void testAddItem() throws Exception {
        cart.addItem("9781430224693");
        assertEquals(3,cart.getItems().size());
        assertEquals(1,(Object)cart.getItems().get("9781430224693"));

    }

    @Test
    public void testRemoveItem() throws Exception {
        cart.removeItem("9781430216407");
        assertEquals(1,cart.getItems().size()); 
        assertNull(cart.getItems().get("9781430216407"));
    }

    @Test
    public void testClearCart() throws Exception {
        cart.clearCart();
        assertEquals(0,cart.getItems().size());
    }

}
