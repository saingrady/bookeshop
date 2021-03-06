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

package org.benassi.bookeshop.business.exception;

/**
 * Business exception to throw when checking out out of stock items.
 * @author Mahmoud Ben Hassine
 */
public class OutOfStockException extends Exception{

    private int requestedQuantity;

    private int currentStock;

    private String bookISBN;

    public OutOfStockException(int requestedQuantity, int currentStock, String bookISBN) {
        this.requestedQuantity = requestedQuantity;
        this.currentStock = currentStock;
        this.bookISBN = bookISBN;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public String getBookISBN() {
        return bookISBN;
    }
    
}
