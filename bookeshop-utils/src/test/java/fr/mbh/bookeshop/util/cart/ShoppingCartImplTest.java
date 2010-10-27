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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Shopping cart unit tests
 */
public class ShoppingCartImplTest {

    private ShoppingCart cart;

    @Before
    public void setUp() throws Exception {
        cart = new ShoppingCartImpl();
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
