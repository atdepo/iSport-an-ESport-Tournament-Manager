package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.google.gson.Gson;

import it.unisa.model.gioco.GiocoBean;
import it.unisa.model.gioco.GiocoModel;
import it.unisa.model.modalita.ModalitaBean;
import it.unisa.model.modalita.ModalitaKey;
import it.unisa.model.modalita.ModalitaModel;
import it.unisa.model.sponsor.SponsorBean;
import it.unisa.model.sponsor.SponsorModel;
import it.unisa.model.squadra.SquadraBean;
import it.unisa.model.squadra.SquadraModel;
import it.unisa.model.struttura.KeyStruttura;
import it.unisa.model.struttura.StrutturaBean;
import it.unisa.model.struttura.StrutturaModel;
import it.unisa.model.tecnico.TecnicoModel;
import it.unisa.model.torneo.TournamentBean;
import it.unisa.model.torneo.TournamentModel;
import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteModel;

@WebServlet("/TournamentControl")
public class TournamentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TournamentModel tModel = new TournamentModel();
	StrutturaModel sModel = new StrutturaModel();
	GiocoModel gModel = new GiocoModel();
	TecnicoModel tecModel = new TecnicoModel();
	ModalitaModel modModel = new ModalitaModel();
	SquadraModel sqModel= new SquadraModel();
	SponsorModel spModel= new SponsorModel();
	

	public TournamentControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action"); // azione da far compiere alla servlet
		Gson gson = new Gson();
		
		switch (action) {

		/**
		 * Inizializza il torneo creando un JSON contenente i campi del form 
		 * che sono obbligatoriamente mostrati: i giochi, gli sponsor, il numero 
		 * massimo di tecnici disponibili e il numero massimo di tecnici fisici
		 * disponibili 
		 */
		case "initTorneo":
			
			String theJson = "";
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			try {
				
				ArrayList<GiocoBean> giochi = (ArrayList<GiocoBean>) gModel.doRetriveAll(null);
				ArrayList<String> numeroTecnici = new ArrayList<String>();
				ArrayList<String> maxTecniciFisici = new ArrayList<String>();
				ArrayList<SponsorBean> sponsor= (ArrayList<SponsorBean>) spModel.doRetriveAll(null);
				numeroTecnici.add(String.valueOf(tecModel.doRetriveAll(null).size()));
				maxTecniciFisici.add(String.valueOf(tecModel.doRetrieveTecniciFisici()));
				ArrayList<ArrayList<?>> col= new ArrayList<ArrayList<?>>();
				col.add(giochi);
				col.add(sponsor);
				col.add(numeroTecnici);
				col.add(maxTecniciFisici);
				theJson += gson.toJson(col);
				response.getWriter().print(theJson);
				response.getWriter().flush();	
				System.out.println("il json di inizializzazione del form � stato creato con successo");
				request.getSession().setAttribute("error", null);
				request.getSession().setAttribute("error-type", null);
				response.setStatus(200);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		break;
		
		/**
		 * Crea un JSON contenente tutte le strutture 
		 */
		case "getStrutture":
		
			try {
				String str="";
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				System.out.println("Prendo le strutture");
				ArrayList<StrutturaBean> st=(ArrayList<StrutturaBean>) sModel.doRetriveAll(null);
				str=gson.toJson(st);
				response.getWriter().print(str);
				response.getWriter().flush();
				System.out.println("il json delle strutture e' stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
						
			
		break;
		/**
		 * Restituisce tutte le modalita' di un dato gioco passato come parametro dalla chiamata alla servlet.
		 * Nel caso il gioco non sia stato inserito, restituisce il codice di errore 404
		 */
		case "getMode":
			
			try {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				String mode="";
				String gioco= request.getParameter("gioco");
				if(gioco!=null && !gioco.equals(" ")) {
						System.out.println("Cerco le modalita' di "+gioco);
						ArrayList<ModalitaBean> modalita;
						modalita = (ArrayList<ModalitaBean>) modModel.doRetriveByGame(gioco);
						mode=gson.toJson(modalita);
						response.getWriter().print(mode);
						response.getWriter().flush();
						System.out.println("il json delle modalita' e' stato creato con successo");
						response.setStatus(200);
				}
				else {
					System.out.println("Il gioco non e' stato indicato correttamente");
					response.setStatus(404);
				}
					
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		break;
	
	
			
		
			
		/** Questo case viene chiamato nel terzo step del form di creazione di un nuovo torneo.	
		 * Viene utilizzato per validare la data di svolgimento del torneo, in particolare:
		 * -controlla se la data di svolgimento del torneo e' nel passato(controllo gi� fatto nel front-end ma js e' disattivabile).
		 * -controlla se, nel caso in cui il torneo sia organizzato presso una struttura fisica, che tale struttura in quella giornata
		 * sia effettivamente libera e utilizzabile.
		 * 
		 * Nel caso in cui venga riscontrato un errore, un oggetto error in sessione viene creato e inserito opportunamente dove e' presente l'errore. 
		 */
		case "validateTorneo":
			HttpSession session= request.getSession();
			//session.setAttribute("error", null);
			//session.setAttribute("error-type", null);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			
			System.out.println("Sto validando il torneo");
			
			//Controllo se la data e' nel passato
			String dataTorneoDaCreare = (String) request.getParameter("datatorneo");
			Date d1 = new Date();
			SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
			String data = df.format(d1);
			if (controlloData(data, request.getParameter("datatorneo"))) {
				String errore="Non possediamo una DeLorean, pertanto ci e' impossibile organizzare tornei nel passato!";
				//session.setAttribute("error",errore);
				//session.setAttribute("error-type","data");
				//response.sendRedirect(request.getContextPath()+"/user/FormCreazioneTorneo.jsp");
				//return;
				ArrayList<String>err= new ArrayList<String>();
				err.add(errore);
				String wr=gson.toJson(err);
				response.getWriter().print(wr);
				response.getWriter().flush();
				System.out.println("data nel passato");
				response.setStatus(200);
				return;
			}
			
			else {
			//Controllo se la struttura nella data specificata e' gia' stata occupata
				try {
					ArrayList<TournamentBean> tornei = (ArrayList<TournamentBean>) tModel.doRetriveAll(null);
					for (TournamentBean t : tornei) {
						if (t.getData().equals(dataTorneoDaCreare)) {
							String s = request.getParameter("struttura");
							String tmp = s.substring(s.indexOf(',') + 2);
							int value = Integer.parseInt(tmp.replaceAll("[^0-9]", ""));
							String address = tmp.substring(0, tmp.indexOf('-') - 1);
	
							if (t.getCAPStruttura() == value && t.getIndirizzoStruttura().equals(address)) {
								String errore = "In questa data la struttura selezionata e' gia' occupata, selezionarne una diversa";
								ArrayList<String>err= new ArrayList<String>();
								err.add(errore);
								String wr=gson.toJson(err);
								response.getWriter().print(wr);
								response.getWriter().flush();
								System.out.println("struttura non valida");
								response.setStatus(200);
								return;
							}
	
						}
					}
				
				}
				
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
				
			ArrayList<String>err= new ArrayList<String>();
			err.add("null");
			String wr=gson.toJson(err);
			response.getWriter().print(wr);
			response.getWriter().flush();
			//System.out.println("il json delle modalita' e' stato creato con successo");
			response.setStatus(200);
			break;
			
				//session.setAttribute("error",null);
				//session.setAttribute("error-type", null);
		case "saveTorneo":
			
				HttpSession sessione= request.getSession();
				sessione.setAttribute("nomeTorneo", request.getParameter("nometorneo"));
				sessione.setAttribute("dataTorneo", request.getParameter("datatorneo"));
				sessione.setAttribute("nomeGioco", request.getParameter("gioco"));
				sessione.setAttribute("tipoTorneo", request.getParameter("sel"));
				sessione.setAttribute("modalita", request.getParameter("mode"));
				sessione.setAttribute("struttura", request.getParameter("strutture"));
				sessione.setAttribute("budget", request.getParameter("budget"));
				sessione.setAttribute("totaleTecnici", request.getParameter("tot_tecnici"));
				sessione.setAttribute("tecniciFisici", request.getParameter("tecnici_fisici"));
				response.sendRedirect(request.getContextPath()+"/user/FormInserimentoSquadre.jsp");			
				
		
		break;
			
		case "deleteTorneo":
			
			try {/*
				TournamentBean t = new TournamentBean();
				t.setBudget(311);
				t.setCAPStruttura(80654);
				t.setCodGioco("Rocket League");
				t.setData("2032-10-30");
				t.setHomePage(true);
				t.setIndirizzoStruttura("Via Francia");
				t.setNome("TEST");
				t.setProprietario("test@test.com");
				tModel.doSave(t);*/
				tModel.doDelete((String)request.getParameter("cod"));
				
				response.sendRedirect(request.getContextPath()+"/admin/ListaTornei.jsp");
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
			
		case "getTornei":
			
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String torneo="";
			ArrayList<TournamentBean> tornei = (ArrayList<TournamentBean>) tModel.doRetriveAll(null);
			torneo=gson.toJson(tornei);
			System.out.println("ciao mamma, questi sono i tornei");
			response.getWriter().print(torneo);
			response.getWriter().flush();
			response.setStatus(200);
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		break;
		
		
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean controlloData(String a, String b) {
		if (a != null && b != null) {
			int dataA = Integer.valueOf(a.replaceAll("[-]", ""));
			int dataB = Integer.valueOf(b.replaceAll("[-]", ""));

			return dataA > dataB;
		} else
			return true;

	}

}
