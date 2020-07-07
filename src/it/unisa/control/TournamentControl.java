package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.struttura.StrutturaBean;
import it.unisa.model.struttura.StrutturaModel;
import it.unisa.model.torneo.TournamentBean;
import it.unisa.model.torneo.TournamentModel;


@WebServlet("/TournamentControl")
public class TournamentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TournamentModel tModel= new TournamentModel();
	StrutturaModel sModel= new StrutturaModel();
	
	
    
    public TournamentControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		
			try {
				ArrayList<StrutturaBean> strutture= (ArrayList<StrutturaBean>) sModel.doRetriveAll(null);
				
				request.setAttribute("strutture", strutture);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/FormCreazioneTorneo.jsp");
			dispatcher.forward(request, response);
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("Oh vedi che sono stata attivata");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
