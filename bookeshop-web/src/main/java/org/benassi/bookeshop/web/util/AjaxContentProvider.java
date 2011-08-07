/*
 * This file is part of the Book-eShop project.
 *
 *    Copyright (C) 2010-2011 Mahmoud Ben Hassine <md.benhassine@gmail.com>
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 *
 *    Author :
 *   	Mahmoud Ben Hassine <md.benhassine@gmail.com>
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
