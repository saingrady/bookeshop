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

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to generate PDF catalogue on the fly
 */
public class PdfCatalogueGenerator implements ApplicationContextAware {

    final Logger logger = LoggerFactory.getLogger(PdfCatalogueGenerator.class);

    private DataSource dataSource;

    private ApplicationContext applicationContext;

    private String query;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ByteArrayInputStream getPdfCatalogueStream() {

        Resource jreport = applicationContext.getResource("classpath:reports/report.jrxml");

        java.sql.Connection connection = null;
        java.sql.Statement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            logger.warn("Not using compiled jasper design, compiling jasper design from 'reports/report.jrxml' ");
            //Certainly not in production ! Jasper reports should be pre compiled
            JasperDesign jasperDesign = JRXmlLoader.load(jreport.getFile());
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JRResultSetDataSource jrResultSetDataSource = new JRResultSetDataSource(resultSet);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrResultSetDataSource);

            byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
            return new ByteArrayInputStream(bytes);

        } catch (Exception e) {
            logger.error("Error generating PDF catalogue.",e);
            return null;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                logger.error("Error closing database connection when generating PDF catalogue.",e);
            }
        }
    }
}
