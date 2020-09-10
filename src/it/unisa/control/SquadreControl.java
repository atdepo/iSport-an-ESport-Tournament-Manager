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

import it.unisa.model.giocatore.GiocatoreBean;
import it.unisa.model.giocatore.GiocatoreModel;
import it.unisa.model.gioco.GiocoModel;
import it.unisa.model.modalita.ModalitaBean;
import it.unisa.model.modalita.ModalitaKey;
import it.unisa.model.modalita.ModalitaModel;
import it.unisa.model.sponsor.SponsorModel;
import it.unisa.model.squadra.SquadraBean;
import it.unisa.model.squadra.SquadraModel;
import it.unisa.model.struttura.StrutturaModel;
import it.unisa.model.tecnico.TecnicoModel;
import it.unisa.model.torneo.TournamentModel;
import it.unisa.model.utente.UtenteBean;

/**
 * Servlet implementation class SquadreControl
 */
@WebServlet(urlPatterns = {"/SquadreControl","/user/SquadreControl"})
public class SquadreControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GiocoModel gModel = new GiocoModel();
	TecnicoModel tecModel = new TecnicoModel();
	ModalitaModel modModel = new ModalitaModel();
	SquadraModel sqModel= new SquadraModel();
	GiocatoreModel pModel = new GiocatoreModel();

    public SquadreControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); // azione da far compiere alla servlet
		Gson gson = new Gson();
		HttpSession session=request.getSession();
		ArrayList<SquadraBean> squadre= request.getAttribute("squadre");
		switch (action) {
		
		case "getGiocatori":
			
			try {
				
				ModalitaBean squadre= modModel.doRetriveByKey(new ModalitaKey((String)session.getAttribute("nomeGioco"),(String)session.getAttribute("modalita")));
				Integer num=squadre.getNumPartecipanti()/2;
				String test=gson.toJson(num);
				response.getWriter().print(test);
				response.getWriter().flush();
				System.out.println("il json del numero di giocatori e' stato creato con successo");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			
			break;
					
		case"getImgSquadra":
			try {
				SquadraBean s=(SquadraBean)sqModel.doRetriveByKey(request.getParameter("squadraScelta"));
				
				ArrayList<String> immagine=new ArrayList<String>();
				immagine.add(s.getTeamImage());
				String img=gson.toJson(immagine);
				response.getWriter().print(img);
				response.getWriter().flush();
				System.out.println("il json dell'immagine e' stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
			break;
		
		case "getSquadreNoIva":
			response.setCharacterEncoding("UTF-8");

			ModalitaBean modalita;
			try {
				modalita = modModel.doRetriveByKey(new ModalitaKey((String)session.getAttribute("nomeGioco"),(String)session.getAttribute("modalita")));
				Integer num=modalita.getNumPartecipanti()/2;
				UtenteBean u=(UtenteBean)session.getAttribute("user");
				ArrayList<SquadraBean> squadr= (ArrayList<SquadraBean>) sqModel.doRetriveByUserNum(u.getEmail(),num);
				String mod=gson.toJson(squadr);
				response.getWriter().print(mod);
				response.getWriter().flush();
				System.out.println("il json delle squadre e' stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			break;
			
		case "getSquadre":
			response.setCharacterEncoding("UTF-8");
			
			try {
				ModalitaBean modalita1= modModel.doRetriveByKey(new ModalitaKey((String)session.getAttribute("nomeGioco"),(String)session.getAttribute("modalita")));
				Integer num1=modalita1.getNumPartecipanti()/2;
				ArrayList<SquadraBean> squadre= (ArrayList<SquadraBean>) sqModel.doRetriveAllNum(num1);
				String mode=gson.toJson(squadre);
				response.getWriter().print(mode);
				response.getWriter().flush();
				System.out.println("il json delle squadre e' stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
		
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
