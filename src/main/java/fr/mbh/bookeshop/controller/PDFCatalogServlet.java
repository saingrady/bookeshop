package fr.mbh.bookeshop.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class PDFCatalogServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ApplicationContext context =
                WebApplicationContextUtils.getRequiredWebApplicationContext((getServletContext()));

        Resource jreport = context.getResource("classpath:reports/report.jrxml");
        ServletOutputStream servletOutputStream = response.getOutputStream();

        byte[] bytes = null;
        java.sql.Connection connection = null;
        java.sql.Statement statement = null;
        try {
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

            servletOutputStream.write(bytes, 0, bytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();


        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        /* May be used if PDF catalog is already prepared and not generated on the fly with Jasper Reports
          
        Resource catalog = context.getResource("classpath:catalog/catalog.pdf");
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
