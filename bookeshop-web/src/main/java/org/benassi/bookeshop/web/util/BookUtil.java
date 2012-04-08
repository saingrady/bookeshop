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

package org.benassi.bookeshop.web.util;

import org.benassi.bookeshop.data.model.Book;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Utility class for {@link org.benassi.bookeshop.data.model.Book}
 * @author Mahmoud Ben Hassine
 */
public class BookUtil {

    private int limitedStockThreshold;

    private String dateFormat;

    private String outOfStock;

    private String lastItems;

    private String inStock;

    /*
     * public utility methods
     */
    public void prepareBooksForView(List<Book> books){
        for(Book book : books){
            book.setFormattedPublishDate(formatPublishDate(book.getPublishDate()));
            book.setStockStatus(getStockStatus(book.getStock()));
        }
    }

    public String getStockStatus(int stock) {
        if (stock <= 0)
            return outOfStock;
        else if (stock <= limitedStockThreshold)
            return lastItems;
        else return inStock;
    }

    public String formatPublishDate(final Date publishDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(publishDate);
    }

    /*
     * Setters for DI
     */

    public void setLimitedStockThreshold(int limitedStockThreshold) {
        this.limitedStockThreshold = limitedStockThreshold;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setOutOfStock(String outOfStock) {
        this.outOfStock = outOfStock;
    }

    public void setLastItems(String lastItems) {
        this.lastItems = lastItems;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }
}
