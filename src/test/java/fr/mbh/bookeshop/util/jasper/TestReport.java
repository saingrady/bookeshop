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

package fr.mbh.bookeshop.util.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.DriverManager;

/**
 * Created by IntelliJ IDEA.
 * User: mahmoud
 * Date: Aug 2, 2010
 * Time: 6:58:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestReport {
/*
    @Test
    public void testReport() {
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        try {
            jasperReport = JasperCompileManager.compileReport("helloworld.jrxml");
            jasperPrint = JasperFillManager.fillReport(
                    jasperReport, new HashMap(), new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint, "simple_report.pdf");
        }
        catch (JRException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        try{
            ApplicationContext appContext = new ClassPathXmlApplicationContext("test-context.xml");


            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            java.sql.Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:bookeshop", "SA", "");
            java.sql.Statement statement = connection.createStatement();
            String query = "SELECT title,author,price FROM book;";
            java.sql.ResultSet resultSet = statement.executeQuery(query);

            JasperDesign jasperDesign = JRXmlLoader.load("/home/mahmoud/IdeaProjects/BookeShop/src/main/resources/reports/report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRResultSetDataSource(resultSet));
            
            JasperViewer.viewReport(jasperPrint);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/mahmoud/db_report.pdf");
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
