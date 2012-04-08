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
 * @author Mahmoud Ben Hassine
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
