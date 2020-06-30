import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        crearArchivo("log.txt", ip);
        out.println("<h1>" + ip + "</h1>");
    }

    public void crearArchivo(String nombre, String requestIP) {

        try {
            File file = new File(nombre);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write("New event from: " + requestIP);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroy() {
        // do nothing.
    }
}