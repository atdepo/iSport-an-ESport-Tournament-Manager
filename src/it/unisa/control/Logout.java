package it.unisa.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet(urlPatterns = {"/Logout","/user/Logout"})
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Logout() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Redirect to"+request.getContextPath()+"/index.jsp");
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		request.getSession().invalidate();
		

	}

}