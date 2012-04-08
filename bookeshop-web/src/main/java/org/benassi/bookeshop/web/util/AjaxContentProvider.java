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

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.benassi.bookeshop.data.model.Book;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to provide ajax content
 * @author Mahmoud Ben Hassine
 */
public class AjaxContentProvider {

    private VelocityEngine velocityEngine;

    public String getCartUpdateAsJson(final String status,final String title,final Integer size,final String error){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("status:\"" + status + "\",");
        sb.append("book:\"" + title + "\",");
        sb.append("cartSize:\""+ size + "\",");
        sb.append("error:\""+ error + "\"");
        sb.append("}");
        return sb.toString();
    }

    public String getBookDetailsAsHtml(final Book book){

        Map model = new HashMap();
        model.put("book", book);

        String result = null;
        try {
            result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/bookDetails.vm", model);
        } catch (VelocityException e) {
            result = "Error loading book details.";
        }
        return result;
    }

    /*
     * Setter for DI
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
}
