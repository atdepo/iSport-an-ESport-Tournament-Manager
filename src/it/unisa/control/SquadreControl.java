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
import it.unisa.model.modalita.ModalitaBean;
import it.unisa.model.modalita.ModalitaKey;
import it.unisa.model.modalita.ModalitaModel;
import it.unisa.model.sponsor.SponsorModel;
import it.unisa.model.squadra.SquadraBean;
import it.unisa.model.squadra.SquadraModel;
import it.unisa.model.struttura.StrutturaModel;
import it.unisa.model.tecnico.TecnicoModel;
import it.unisa.model.torneo.TournamentModel;

/**
 * Servlet implementation class SquadreControl
 */
@WebServlet(urlPatterns = {"/SquadreControl","/user/SquadreControl"})
public class SquadreControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TournamentModel tModel = new TournamentModel();
	StrutturaModel sModel = new StrutturaModel();
	GiocoModel gModel = new GiocoModel();
	TecnicoModel tecModel = new TecnicoModel();
	ModalitaModel modModel = new ModalitaModel();
	SquadraModel sqModel= new SquadraModel();
	SponsorModel spModel= new SponsorModel();

    public SquadreControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); // azione da far compiere alla servlet
		Gson gson = new Gson();
		
		switch (action) {
		case "getGiocatori":
			HttpSession session=request.getSession();
			try {
				ModalitaBean squadre= modModel.doRetriveByKey(new ModalitaKey((String)session.getAttribute("nomeGioco"),(String)session.getAttribute("modalita")));
				ArrayList<ModalitaBean> coso=new ArrayList<ModalitaBean>();
				String mode=gson.toJson(squadre);
				response.getWriter().print(mode);
				response.getWriter().flush();
				System.out.println("il json delle squadre � stato creato con successo");
				response.setStatus(200);
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
				System.out.println("Mammt"+s.getTeamImage());
				response.getWriter().print(img);
				response.getWriter().flush();
				System.out.println("il json dell'immagine e' stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
			break;
		
		case "getSquadre":
			response.setCharacterEncoding("UTF-8");
			
			try {
				ArrayList<SquadraBean> squadre= (ArrayList<SquadraBean>) sqModel.doRetriveAll(null);
				String mode=gson.toJson(squadre);
				response.getWriter().print(mode);
				response.getWriter().flush();
				System.out.println("il json delle squadre � stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e) {
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
