import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class fintechServlet extends HttpServlet {
  public void init(ServletConfig config) throws ServletException {
  }
  public void destroy() {
  }
  protected void doGet(HttpServletRequest request,
   HttpServletResponse response) throws ServletException, IOException {
	  RequestDispatcher requestDispatcher = request.getRequestDispatcher("/META-INF/landing-page.jsp");
	  requestDispatcher.forward(request, response);
	}
	  
  public void doPost(HttpServletRequest request, HttpServletResponse
  response)throws ServletException, IOException {
  }
}