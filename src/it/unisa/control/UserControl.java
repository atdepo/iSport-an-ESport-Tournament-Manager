package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import it.unisa.model.gioco.GiocoModel;
import it.unisa.model.modalita.ModalitaModel;
import it.unisa.model.sponsor.SponsorModel;
import it.unisa.model.squadra.SquadraModel;
import it.unisa.model.struttura.StrutturaModel;
import it.unisa.model.tecnico.TecnicoModel;
import it.unisa.model.torneo.TournamentModel;
import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteModel;
import it.unisa.model.torneo.TournamentBean;
/**
 * Servlet implementation class UserControl
 */
@WebServlet("/UserControl")
public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TournamentModel tModel = new TournamentModel();
	StrutturaModel sModel = new StrutturaModel();
	GiocoModel gModel = new GiocoModel();
	TecnicoModel tecModel = new TecnicoModel();
	ModalitaModel modModel = new ModalitaModel();
	SquadraModel sqModel= new SquadraModel();
	SponsorModel spModel= new SponsorModel();
	UtenteModel userModel= new UtenteModel();

    public UserControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String action = request.getParameter("action"); // azione da far compiere alla servlet
		Gson gson = new Gson();
		
		switch (action) {	
		case "getMieiTornei":
			
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			UtenteBean user=(UtenteBean) session.getAttribute("user");
			String email=user.getEmail();
			String torneo="";
			ArrayList<TournamentBean> tornei = (ArrayList<TournamentBean>) tModel.doRetriveByUser(email);
			torneo=gson.toJson(tornei);
			System.out.println("ciao mamma, questi sono i miei tornei");
			response.getWriter().print(torneo);
			response.getWriter().flush();
			response.setStatus(200);
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		break;
		
		
		case "change":
			String cosa=request.getParameter("cosa");
			UtenteBean utente=(UtenteBean)session.getAttribute("user");
			String valore=request.getParameter("valore");
			System.out.println("mammt "+utente.getEmail());
			System.out.println("Cambio "+cosa);
			System.out.println(userModel.cambiaCose(cosa,valore,utente.getEmail()));
			
		break;
		}
		
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
