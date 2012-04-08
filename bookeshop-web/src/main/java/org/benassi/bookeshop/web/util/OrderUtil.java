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

import org.benassi.bookeshop.data.model.Order;
import org.benassi.bookeshop.data.model.OrderItem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Utility class for {@link org.benassi.bookeshop.data.model.Order}
 */
public class OrderUtil {

    private String dateFormat;

    private String totalFormat;

    /*
     * public utility methods
     */

    public void prepareOrdersForView(Set<Order> orders) {
        for(Order order : orders){
            order.setFormattedDate(formatDate(order.getDate()));
            order.setFormattedTotal(formatTotal(order.getTotal()));
        }
    }

    public String formatDate(final Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    public String formatTotal(float total) {
        DecimalFormat df = new DecimalFormat(totalFormat);
        return df.format(total);
    }

     /*
     * Setters for DI
     */

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setTotalFormat(String totalFormat) {
        this.totalFormat = totalFormat;
    }


}
