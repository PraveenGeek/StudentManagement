package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Reg extends HttpServlet {

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  String name = request.getParameter("fullname");
  String userName = request.getParameter("userName");
  String pass = request.getParameter("pass");
  String addr = request.getParameter("address");
  String age = request.getParameter("age");
  String qual = request.getParameter("qual");
  String percent = request.getParameter("percent");
  String year = request.getParameter("yop");

   try {
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb2", "root", "everest100"); 

    String query = "insert into student values(?,?,?,?,?,?,?,?)";

    PreparedStatement ps = con.prepareStatement(query); // generates sql query

    ps.setString(1, name);
    ps.setString(2, userName);
    ps.setString(3, pass);
    ps.setString(4, addr);
    ps.setInt(5, Integer.parseInt(age));
    ps.setString(6, qual);
    ps.setString(7, percent);
    ps.setString(8, year);

    ps.executeUpdate(); // execute it on test database
    System.out.println("successfuly inserted");
    ps.close();
    con.close();
   } catch (ClassNotFoundException | SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
   RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
   rd.forward(request, response);
  }
 }

