package com.employee;

import java.io.*;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        PrintWriter out = res.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employee_management",
                "root", "happynewyear");

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM employee");

            out.println("<html><head><link rel='stylesheet' href='style.css'></head><body>");

            // NAVBAR
            out.println("<div class='navbar'>");
            out.println("<div class='logo'>EMS</div>");
            out.println("<div class='nav-links'>");
            out.println("<a href='index.html'>Add</a>");
            out.println("<a href='view'>View</a>");
            out.println("<a href='login.html'>Logout</a>");
            out.println("</div></div>");

            out.println("<h1>Employee List</h1>");

            out.println("<table class='emp-table'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>City</th><th>Action</th></tr>");

            while(rs.next()){
                out.println("<tr>");
                out.println("<td>"+rs.getString(1)+"</td>");
                out.println("<td>"+rs.getString(2)+"</td>");
                out.println("<td>"+rs.getString(3)+"</td>");
                out.println("<td>"+rs.getString(4)+"</td>");
                out.println("<td>"+rs.getString(5)+"</td>");
                out.println("<td>");
                out.println("<a class='btn delete' href='delete?empid="+rs.getString(1)+"'>Delete</a> ");
                out.println("<a class='btn edit' href='edit?empid="+rs.getString(1)+"'>Edit</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table></body></html>");

            con.close();

        } catch(Exception e){
            out.println(e);
        }
    }
}