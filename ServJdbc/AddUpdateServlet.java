package mypack;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AddUpdateServlet extends HttpServlet
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
           res.setContentType("text/html");
           PrintWriter out=res.getWriter();
           String name=req.getParameter("txtname");
           String course=req.getParameter("course");
           int marks=Integer.parseInt(req.getParameter("txtmarks"));
           String option=req.getParameter("opt");
           try 
           {
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","VARUN","varun");
                 PreparedStatement ps=null;
                 if(option.equals("add"))
                 {
                       ps=con.prepareStatement("insert into studsj values(?,?,?)");
                       ps.setString(1,name);
                       ps.setString(2,course);
                       ps.setInt(3,marks);
                 }
                 else
                 {
                       ps=con.prepareStatement("update studsj set course=?,marks=? where name=?");
                       ps.setString(1,name);
                       ps.setString(2,course);
                       ps.setInt(3,marks);
                 }
                 
                 int x=ps.executeUpdate();
                 if(x>=1)
                 {
                       if(option.equals("add"))
                       {
                             out.println("Record added successfully");
                       }
                       else
                       {
                             out.println("Record updated successfully");
                       }
                 }
                 else
                 {
                       out.println("Record could not be inserted nor updated");
                 }
                 out.println("<br><a href='index.html'>Home</a>");
                 con.close();
           }
           catch(Exception e)
           {
                 e.printStackTrace();
           }
    }
}
                            
















