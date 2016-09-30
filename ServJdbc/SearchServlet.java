package mypack;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class SearchServlet extends HttpServlet
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
           res.setContentType("text/html");
           PrintWriter out=res.getWriter();
           String option=req.getParameter("opt");
           try 
           {
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","VARUN","varun");
                 PreparedStatement ps=null;
                 if(option.equals("course"))
                 {
                       ps=con.prepareStatement("select * from studsj where course=?");
                       ps.setString(1,req.getParameter("course"));
                       ResultSet rs=ps.executeQuery();
                       while(rs.next())
                       {
                            out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
                            out.println("<br>");
                       }
                 }
                 else
                 {
                       ps=con.prepareStatement("select * from studsj where name=?");
                       ps.setString(1,req.getParameter("txtname"));
                       ResultSet rs=ps.executeQuery();
                       if(rs.next())
                       {
                            out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
                       }
                       else
                       {
                            out.println("Record not found");
                       }
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
                            
















