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
import it.unisa.model.squadra.SquadraBean;
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
	GiocoModel gModel = new GiocoModel();
	UtenteModel userModel= new UtenteModel();
	
    public UserControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String action = request.getParameter("action"); // azione da far compiere alla servlet
		Gson gson = new Gson();
		System.out.println("Sto facendo questa action: "+ action);
		
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
		
		
		case "getGiocatoreFromSquadra":
		
		break;
		
		
		case "getSquadreFromTorneo":
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			int codTorneo=Integer.parseInt(request.getParameter("codTorneo"));
			
			ArrayList<SquadraBean> squadre=(ArrayList<SquadraBean>) userModel.getSquadreFromTornei(codTorneo);
			String squadra=gson.toJson(squadre);
			System.out.println("Ciao questi sono le squadre del torneo");
			response.getWriter().print(squadra);
			response.getWriter().flush();
			response.setStatus(200);
			
			break;
		case "change":
			String cosa=request.getParameter("cosa");
			UtenteBean utente=(UtenteBean)session.getAttribute("user");
			String valore=request.getParameter("valore");
			
			String regUser="^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$";

			System.out.println("Sto nel change");
			//Impostiamogli eventuali errori avuti in precedenza a null per evitare problemi nella visualizzazione
			session.setAttribute("error-type", null); //error-type ci fornisce il campo sul quale abbiamo riscontrato l'errore
			session.setAttribute("error", null); // error ci fornisce il messaggio di errore da visualizzare
			session.setAttribute("error-location", null); //error-location ci fornisce l'indicazione su quale 
														  //dei due form (login|signup) sia presente l'errore
			
			
			try {
				
				//----------------Controllo username--------------------//
				if (userModel.doRetriveByKey(request.getParameter("username")) != null) {
					System.out.println("guarda che sto user è stato usato");
					session.setAttribute("error-type", "username");
					session.setAttribute("error", "Quest'username è stato utilizzato");
					session.setAttribute("error-location", "signup");
					response.sendRedirect(request.getContextPath()+"/FormLoginAndRegister.jsp");
					return;
				}	else if(!request.getParameter("username").matches(regUser)) {
					System.out.println("mi fermo allo username");
					session.setAttribute("error-type", "username");
					session.setAttribute("error", "Utente non scritto correttamente");
					session.setAttribute("error-location", "signup");
					response.sendRedirect(request.getContextPath()+"/FormLoginAndRegister.jsp");
				}
			} catch (SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				System.out.println("Prima della chiamata di cambia cose");
				userModel.cambiaCose(cosa, valore, utente.getEmail());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
	
		}
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
