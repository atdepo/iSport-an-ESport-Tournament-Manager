package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.struttura.StrutturaBean;
import it.unisa.model.torneo.TournamentBean;
import it.unisa.model.torneo.TournamentModel;


@WebServlet("/IndexControl")
public class IndexControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       TournamentModel tModel=new TournamentModel();
  
    public IndexControl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); // azione da far compiere alla servlet
		
		switch (action) {
			case "TournamentHome":
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				System.out.println("Prendo i tornei da mettere in home");
			try {
				ArrayList<TournamentBean> t=(ArrayList<TournamentBean>) tModel.doRetriveInHome();
				ArrayList<Integer> codici= new ArrayList<Integer>();
				for (TournamentBean tournamentBean : t) {
					codici.add(tournamentBean.getCodice());
					System.out.println("mammt");
				}
				request.getSession().setAttribute("commCazzVuoTu", codici);
				response.sendRedirect(request.getContextPath()+"index.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

				
				break;
	}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
