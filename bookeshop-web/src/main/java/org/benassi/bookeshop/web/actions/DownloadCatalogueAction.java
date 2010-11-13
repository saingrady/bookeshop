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

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Action class to generate and download PDF catalog
 */
public class DownloadCatalogueAction implements ServletResponseAware, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private HttpServletResponse response;

     public void setServletResponse(HttpServletResponse response){
       this.response = response;
     }

     public HttpServletResponse getServletResponse(){
       return response;
     }

    private String error;

    public String getError() {
        return error;
    }

    public String execute(){

        Resource jreport = applicationContext.getResource("classpath:reports/report.jrxml");


        byte[] bytes = null;
        java.sql.Connection connection = null;
        java.sql.Statement statement = null;
        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:bookeshop", "SA", "");
            statement = connection.createStatement();
            String query = "SELECT title,author,price FROM book;";
            java.sql.ResultSet resultSet = statement.executeQuery(query);

            //Certainly not in production environment!
            //Jasper reports should be compiled only once
            JasperDesign jasperDesign = JRXmlLoader.load(jreport.getFile());
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRResultSetDataSource(resultSet));

            bytes = JasperExportManager.exportReportToPdf(jasperPrint);

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            // TODO check file extension for downloaded file (currently .do!)

            servletOutputStream.write(bytes, 0, bytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();

            return"success";


        }
        catch (Exception e) {
            e.printStackTrace();
            error = "An exception occurred while generating PDF catalog.";
            return "error";
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        /* May be used if PDF catalog is already prepared (static content) and not generated on the fly with Jasper Reports

        Resource catalog = applicationContext.getResource("classpath:catalog/catalog.pdf");
        try {
            InputStream is = catalog.getInputStream();
            OutputStream os = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=catalog.pdf");
            int count;
            byte buf[] = new byte[4096];
            while ((count = is.read(buf)) > -1) {
                os.write(buf, 0, count);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }
}