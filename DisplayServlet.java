import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/display")
public class DisplayServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/sample",
                    "app",
                    "app"
            );

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM student");

            out.println("<html>");
            out.println("<head><title>Student Records</title></head>");
            out.println("<body>");

            out.println("<h2>All Student Records</h2>");

            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Course</th>");
            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("course") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br>");
            out.println("<a href='register.html'>Back to Registration</a>");

            out.println("</body>");
            out.println("</html>");

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}