package com.spring.hibernate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/DataBaseConnection")
public class DataBaseConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = "springstudent";
		String pass = "springstudent";
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		
			try {
				PrintWriter out=response.getWriter();
				Class.forName(driver);
				Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
				out.println("Connection Successful");
				myConn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}
}
