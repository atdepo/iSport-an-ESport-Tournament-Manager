package it.unisa.control;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PagamentoControl
 */
@WebServlet({ "/PagamentoControl", "/user/PagamentoControl" })
public class PagamentoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagamentoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Enumeration<String> e=request.getAttributeNames();
		Iterator<String> it= e.asIterator();
		System.out.println("*****parametri del pagamento******");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("*****fine parametri del pagamento******");

		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
