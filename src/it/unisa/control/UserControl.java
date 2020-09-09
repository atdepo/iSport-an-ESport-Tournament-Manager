package it.unisa.control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.File;

import com.google.gson.Gson;

import it.unisa.model.giocatore.GiocatoreBean;
import it.unisa.model.giocatore.GiocatoreModel;
import it.unisa.model.gioco.GiocoModel;
import it.unisa.model.modalita.ModalitaModel;
import it.unisa.model.sponsor.SponsorModel;
import it.unisa.model.squadra.SquadraBean;
import it.unisa.model.squadra.SquadraModel;
import it.unisa.model.struttura.KeyStruttura;
import it.unisa.model.struttura.StrutturaBean;
import it.unisa.model.struttura.StrutturaModel;
import it.unisa.model.tecnico.TecnicoModel;
import it.unisa.model.torneo.TournamentModel;
import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteModel;
import it.unisa.model.torneo.TournamentBean;
/**
 * Questa servlet si occupa della gestione dell'utente e della visualizzazione dei suoi tornei
 */

@WebServlet(urlPatterns = {"/UserControl","/user/UserControl"})
public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TournamentModel tModel = new TournamentModel();
	GiocoModel gModel = new GiocoModel();
	UtenteModel userModel= new UtenteModel();
	StrutturaModel sModel= new StrutturaModel();
	SquadraModel teamModel= new SquadraModel();
	GiocatoreModel pModel=new GiocatoreModel();
    public UserControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String action = request.getParameter("action"); // azione da far compiere alla servlet
		Gson gson = new Gson();
		System.out.println("Sto facendo questa action: "+ action);
		
		switch (action) {	
		/**
		 * Questa action serve a prendere tutti i tornei di un dato utente
		 * identificato univocamente dalla sua mail
		 */
		case "getMieiTornei":
			
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			UtenteBean user=(UtenteBean) session.getAttribute("user");
			String email=user.getEmail();
			String torneo="";
			ArrayList<TournamentBean> tornei = (ArrayList<TournamentBean>) tModel.doRetriveByUser(email);
			ArrayList<String>strutture=new ArrayList<String>();
			for(TournamentBean t:tornei) {
				strutture.add(sModel.doRetriveByKey(new KeyStruttura(String.valueOf(t.getCAPStruttura()), t.getIndirizzoStruttura())).getNome());
			}
			ArrayList<ArrayList<?>>tutto=new ArrayList<ArrayList<?>>();
			tutto.add(tornei);
			tutto.add(strutture);
			torneo=gson.toJson(tutto);
			System.out.println("Il JSON dei tornei e' stato creato con successo");
			response.getWriter().print(torneo);
			response.getWriter().flush();
			response.setStatus(200);
		}
		catch(SQLException e2) {	
			e2.printStackTrace();
		}
		break;
		
		/**
		 * Questa action serve a prendere i nomi e le immagini di tutti i giocatori di una data squadra
		 */
		case "getGiocatoriFromSquadra":
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");			
			
			try {
				String nome=(String)request.getParameter("nomeSquadra");
				ArrayList<GiocatoreBean> squadre= (ArrayList<GiocatoreBean>) teamModel.doRetrivePlayerFromSquadra(nome);
				ArrayList<String> names= new ArrayList<String>();
				ArrayList<String> images= new ArrayList<String>();
				
				for(GiocatoreBean b:squadre) {
					names.add(b.getNickname());
				}
				
				for(GiocatoreBean be:squadre) {
					images.add(be.getPlayerImage());
				}
				
				ArrayList<ArrayList<?>> cose= new ArrayList<ArrayList<?>>();
				cose.add(names);
				cose.add(images);
				String mode=gson.toJson(cose);
				response.getWriter().print(mode);
				response.getWriter().flush();
				System.out.println("il json dei giocatori della squadra "+nome+" e' stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e) {
				response.sendError(404);
				e.printStackTrace();
			}
			break;
			
			/**
			 * Questa action serve a restituire tutti i dati di un giocatore
			 * identificato univocamente dal proprio nickname
			 */
		case "getDatiGiocatore":
			
			try {

				String nick=request.getParameter("nick");
				GiocatoreBean beanp=pModel.doRetriveByKey(nick);
				ArrayList<GiocatoreBean> beans= new ArrayList<GiocatoreBean>();
				beans.add(beanp);
				String datip=gson.toJson(beans);
				response.getWriter().print(datip);
				response.getWriter().flush();
				System.out.println("il json del giocatore "+nick+" e' stato creato con successo");
				response.setStatus(200);
			
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			break;
		/**
		 * Questa action viene utilizzata per prendere tutte le squadre di un
		 * dato torneo, identificato univocamente dal suo codice
		 */
		case "getSquadreFromTorneo":
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			int codTorneo=Integer.parseInt(request.getParameter("codTorneo"));
			System.out.println(codTorneo);
			ArrayList<SquadraBean> squadre=(ArrayList<SquadraBean>) userModel.getSquadreFromTornei(codTorneo);
			ArrayList<String> dati= new ArrayList<String>();
			
			try {
				TournamentBean bean= tModel.doRetriveByKey(String.valueOf(codTorneo));
				StrutturaBean struttura=sModel.doRetriveByKey(new KeyStruttura(String.valueOf(bean.getCAPStruttura()), bean.getIndirizzoStruttura()));
				dati.add(bean.getNome());
				dati.add(struttura.getNome());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*for(String s:dati) {
				System.out.println(s);
			}*/
			
			ArrayList<ArrayList<?>> cose= new ArrayList<ArrayList<?>>();
			cose.add(squadre);
			cose.add(dati);
			String squadra=gson.toJson(cose);
			System.out.println("Ciao questi sono le squadre del torneo");
			response.getWriter().print(squadra);
			response.getWriter().flush();
			response.setStatus(200);
			
			break;
		/*
		 * Questa action fittizia serve a chiamare la pagina di visualizzazione delle squadre del torneo
		 * senza pero' mostrare nell'url il nome della squadra 
		 */
		case "visualizza":
			
			session.setAttribute("cod",request.getParameter("codtorneo"));
			response.sendRedirect(request.getContextPath()+"/torneo.jsp");
			
		break;
		/**
		 * Questa action fittizia serve a chiamare la pagina di visualizzazione di una squadra
		 * partecipante ad un torneo senza pero' mostrare nell'url il nome della squadra 
		 */
		case "visualizzaSquadra":
			session.setAttribute("nome",request.getParameter("nomeSquadra"));
			System.out.println(request.getParameter("nomeSquadra"));
			response.sendRedirect(request.getContextPath()+"/squadraView.jsp");
		break;
		
			/**
			 * Questo case viene chiamato nel caso in cui l'utente voglia cambiare qualche campo delle 
			 * proprie informazioni.
			 * 
			 * Per chiamare questo case e' necessario specificare:
			 * -cosa: il campo da modificare email|user|piva
			 * -valore: il valore da modificare nel campo
			 * 
			 * Una validazione preventiva viene eseguita nel caso l'utente cerchi di modificare i suoi dati 
			 * inserendone altri o non correttamente scritti oppure gia' associati a qualche altro utente
			 */
		case "modificaDati":
			
			UtenteBean utente=(UtenteBean)session.getAttribute("user");
			String nome=request.getParameter("nome");
			String email=request.getParameter("email");
			String iva=request.getParameter("iva");
			String img=request.getParameter("img");
			
			String regUser="^[A-Za-z0-9_-]{0,30}$";
			String regEmail="^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
			String regIva="^[0-9]{11}$";
			

			System.out.println("Sto nel change");
			
			//Impostiamogli eventuali errori avuti in precedenza a null per evitare problemi nella visualizzazione
			
			session.setAttribute("error", null); // error ci fornisce il messaggio di errore da visualizzare
			
			session.setAttribute("error-type", null); //error-type ci fornisce il campo sul quale abbiamo riscontrato l'errore
						
		
				if(email.matches(regEmail))
				{session.setAttribute("error", "la mail scelta non e' valida");
				session.setAttribute("error-type", "mail");
				response.sendRedirect(request.getContextPath()+"/Profilo.jsp");}
				else
				if(!userModel.isExistingEmail(email)) { 					//se la nuova mail non e' gia' presente nel db
					userModel.cambiaEmail(email, utente.getEmail());		//la cambio
				}
				else {														
					
					session.setAttribute("error", "la mail scelta e' gia' stata utilizzato"); //altrimenti setto gli errori
					session.setAttribute("error-type", "mail");
					response.sendRedirect(request.getContextPath()+"/Profilo.jsp");
				}
				
		
			
															
				if(nome.matches(regUser))
				{session.setAttribute("error", "lo username inserito non � valido");
				session.setAttribute("error-type", "username");
				response.sendRedirect(request.getContextPath()+"/Profilo.jsp");}
				else
				if(!userModel.isExistingUsername(nome)) {					//se il nuovo username non e' presente nel db
					userModel.cambiaUsername(nome, utente.getEmail());	//lo cambio
				}
				else {														
					
					session.setAttribute("error", "l'username scelto e' gia' stato utilizzato");//altrimenti setto gli errori
					session.setAttribute("error-type", "username");
					response.sendRedirect(request.getContextPath()+"/Profilo.jsp");
					
				}
				
	
				if(iva.matches(regIva))
				{session.setAttribute("error", "la partita IVA inserita non e' valida");
				session.setAttribute("error-type", "piva");
				response.sendRedirect(request.getContextPath()+"/Profilo.jsp");}
				else
				if(!userModel.isExistingPIVA(iva)) {						//se la nuova p.IVA non e' presente nel db
					userModel.cambiaPIVA(iva, utente.getpIVA());		//la cambio
				}
				else {														//altrimenti setto gli errori
					
					session.setAttribute("error", "la partita iva scelta e' gia' stata utilizzato");
					session.setAttribute("error-type", "piva");
					response.sendRedirect(request.getContextPath()+"/Profilo.jsp");
				}
				break;
		
			}//chiusura switch(action)
		
		}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	

}
