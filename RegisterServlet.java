import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver");

            Connection con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/sample",
                    "app",
                    "app"
            );

            String sql = "INSERT INTO student(name, email, course) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);

            ps.executeUpdate();

            out.println("<html>");
            out.println("<head><title>Registration Successful</title></head>");
            out.println("<body>");

            out.println("<h1>Registration Successful!</h1>");
            out.println("<p>Student registered successfully.</p>");

            out.println("<p><b>Name:</b> " + name + "</p>");
            out.println("<p><b>Email:</b> " + email + "</p>");
            out.println("<p><b>Course:</b> " + course + "</p>");

            out.println("<br>");
            out.println("<a href='register.html'>Register Another Student</a>");
            out.println("<br><br>");
            out.println("<a href='display'>View All Records</a>");

            out.println("</body>");
            out.println("</html>");

            ps.close();
            con.close();

        } catch (Exception e) {

            out.println("<h2>Registration Failed</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
        }
    }
}