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
import it.unisa.model.squadra.SquadraBean;
import it.unisa.model.squadra.SquadraModel;
import it.unisa.model.struttura.KeyStruttura;
import it.unisa.model.struttura.StrutturaBean;
import it.unisa.model.struttura.StrutturaModel;
import it.unisa.model.tecnico.TecnicoModel;
import it.unisa.model.torneo.TournamentBean;
import it.unisa.model.torneo.TournamentModel;

@WebServlet("/TournamentControl")
public class TournamentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TournamentModel tModel = new TournamentModel();
	StrutturaModel sModel = new StrutturaModel();
	GiocoModel gModel = new GiocoModel();
	TecnicoModel tecModel = new TecnicoModel();
	ModalitaModel modModel = new ModalitaModel();
	SquadraModel sqModel= new SquadraModel();

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

		case "initTorneo":
			
			String theJson = "";
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			try {
				
				ArrayList<GiocoBean> giochi = (ArrayList<GiocoBean>) gModel.doRetriveAll(null);
				ArrayList<String> numeroTecnici = new ArrayList<String>();
				numeroTecnici.add(String.valueOf(tecModel.doRetriveAll(null).size()));
				ArrayList<ArrayList> col= new ArrayList<ArrayList>();
				col.add(giochi);
				col.add(numeroTecnici);
				theJson += gson.toJson(col);
				response.getWriter().print(theJson);
				response.getWriter().flush();
				System.out.println("il json di inizializzazione del form � stato creato con successo");
				response.setStatus(200);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		break;
		
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
				System.out.println("il json delle strutture � stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
						
			
		break;
		
		case "getMode":
			
			try {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				String mode="";
				String gioco= request.getParameter("gioco");
				System.out.println("Cerco le modalit� di "+gioco);
				ArrayList<ModalitaBean> modalita;
				modalita = (ArrayList<ModalitaBean>) modModel.doRetriveByGame(gioco);
				mode=gson.toJson(modalita);
				response.getWriter().print(mode);
				response.getWriter().flush();
				System.out.println("il json delle modalit� � stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		break;
	
		case "getGiocatori":
			
			
			try {
				
				request.setAttribute("error", null);
				HttpSession sess=request.getSession();
				ModalitaBean bean=modModel.doRetriveByKey(new ModalitaKey((String)sess.getAttribute("nomeGioco"),(String)sess.getAttribute("modalita")));
				System.out.println((String)sess.getAttribute("nomeGioco")+ " "+(String)sess.getAttribute("modalita") );
				sess.setAttribute("numPartecipanti",bean.getNumPartecipanti()/2);
				response.setStatus(200);
				response.sendRedirect("FormInserimentoGiocatori.jsp?nomesquadra="+request.getParameter("nomesquadra"));
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
			
			
		case "validate":
			response.setCharacterEncoding("UTF-8");
			System.out.println("Sto validando il torneo");
			request.setAttribute("error", null);
			String dataTorneoDaCreare = (String) request.getParameter("datatorneo");
			Date d1 = new Date();
			SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
			String data = df.format(d1);

			if (controlloData(data, request.getParameter("datatorneo"))) {
				request.setAttribute("error",
						"Non possediamo una DeLorean, pertanto ci � impossibile organizzare tornei nel passato!");
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/FormCreazioneTorneo.jsp");
				dispatcher.forward(request, response);
				

				return;
			}

			try {
				ArrayList<TournamentBean> tornei = (ArrayList<TournamentBean>) tModel.doRetriveAll(null);
				for (TournamentBean t : tornei) {
					if (t.getData().equals(dataTorneoDaCreare)) {
						String s = request.getParameter("struttura");
						String tmp = s.substring(s.indexOf(',') + 2);
						int value = Integer.parseInt(tmp.replaceAll("[^0-9]", ""));
						String address = tmp.substring(0, tmp.indexOf('-') - 1);

						if (t.getCAPStruttura() == value && t.getIndirizzoStruttura().equals(address)) {
							System.out.println("PROBLEMAAAAA");
							String errore = "In questa data la struttura selezionata � gi� occupata, selezionarne una diversa";
							request.setAttribute("error", errore);
							RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/FormCreazioneTorneo.jsp");
							dispatcher.forward(request, response);
							return;
							
						}

					}
				}
				
				
				//System.out.println("mannagg geppett");
				
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
				response.sendRedirect(request.getContextPath()+"/FormInserimentoSquadre.jsp");

				return;
			} catch (SQLException e) {
				e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
			
			
		case "getTornei":
			
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String torneo="";
			ArrayList<TournamentBean> tornei = (ArrayList<TournamentBean>) tModel.doRetriveAll(null);
			torneo=gson.toJson(tornei);
			System.out.println("ciao mamma");
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
