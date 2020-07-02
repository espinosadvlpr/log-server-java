import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class Server extends HttpServlet {

    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        String ip = request.getRemoteAddr();
        writeLog("New log: "+ new Date() + " from: ");
        out.println("<h1>" + "the IP: " + ip+ "is doing a request to the server." + "</h1>");
    }

    private void writeLog(String sentence) {

		FileWriter fichero = null;
		try {
			fichero = new FileWriter("/var/lib/tomcat9/webapps/app/src/log.txt", true);
			fichero.write("\n" + sentence);
			fichero.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

    public void destroy() {
        // do nothing.
    }
}