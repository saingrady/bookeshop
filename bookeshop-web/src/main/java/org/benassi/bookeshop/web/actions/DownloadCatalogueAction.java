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

package org.benassi.bookeshop.web.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.benassi.bookeshop.web.util.PdfCatalogueGenerator;
import org.springframework.context.MessageSource;

import java.io.InputStream;

/**
 * Action class to generate and download PDF catalogue
 * @author Mahmoud Ben Hassine
 */
public class DownloadCatalogueAction  {

    private PdfCatalogueGenerator pdfCatalogueGenerator;

    private InputStream inputStream;

    private String error;

    private MessageSource messageProvider;

    public String execute(){

        inputStream = pdfCatalogueGenerator.getPdfCatalogueStream();

        if (inputStream != null){
            return ActionSupport.SUCCESS;
        }
        else{
            error = messageProvider.getMessage("web.error.catalogue.pdf",null,null,null);
            return ActionSupport.ERROR;
        }

    }

    /*
     * Setters for DI
     */
    public void setPdfCatalogueGenerator(PdfCatalogueGenerator pdfCatalogueGenerator) {
        this.pdfCatalogueGenerator = pdfCatalogueGenerator;
    }

    public void setMessageProvider(MessageSource messageProvider) {
        this.messageProvider = messageProvider;
    }

    /*
    * Getters for model
    */
    public InputStream getInputStream() {
        return inputStream;
    }

    public String getError() {
        return error;
    }
}