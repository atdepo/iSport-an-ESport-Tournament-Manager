package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.torneo.TournamentBean;
import it.unisa.model.torneo.TournamentModel;

/**
 * Servlet implementation class TournamentControl
 */
@WebServlet("/TournamentControl")
public class TournamentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TournamentModel model= new TournamentModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TournamentControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			TournamentBean bean=model.doRetriveByKey("1");
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("Oh vedi che sono stata attivata");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
