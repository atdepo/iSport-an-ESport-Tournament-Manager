package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	Gson gson;
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
		String action = request.getParameter("action");

		HttpSession session = request.getSession();

		if (action.equals("create")) {
			try {
				System.out.println("Inizializzo il necessario per la creazione del torneo");
				if (session.getAttribute("strutture") == null) {
					ArrayList<StrutturaBean> strutture = (ArrayList<StrutturaBean>) sModel.doRetriveAll(null);
					session.setAttribute("strutture", strutture);
				}

				if (session.getAttribute("giochi") == null) {
					ArrayList<GiocoBean> giochi = (ArrayList<GiocoBean>) gModel.doRetriveAll(null);
					session.setAttribute("giochi", giochi);
				}

				if (session.getAttribute("numtecnici") == null) {
					Integer numeroTecnici = tecModel.doRetriveAll(null).size();
					session.setAttribute("numtecnici", numeroTecnici);
				}

				RequestDispatcher dispatcher = this.getServletContext()
						.getRequestDispatcher("/FormCreazioneTorneo.jsp");
				dispatcher.forward(request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (action.equals("validate")) {
			System.out.println("Sto validando il torneo");
			
			String dataTorneoDaCreare=(String) request.getParameter("datatorneo");
			Date d1 = new Date();
			SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
			String data = df.format(d1);
			
			if(controlloData(data, request.getParameter("datatorneo"))) {
				request.setAttribute("error", "Non possediamo una DeLorean, pertanto ci è impossibile organizzare tornei nel passato!");
				RequestDispatcher dispatcher= request.getServletContext().getRequestDispatcher("/FormCreazioneTorneo.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			try {
				ArrayList<TournamentBean> tornei= (ArrayList<TournamentBean>) tModel.doRetriveAll(null);
				for(TournamentBean t: tornei) {
					if(t.getData().equals(dataTorneoDaCreare)) {
						String s= request.getParameter("struttura");
						String tmp=s.substring(s.indexOf(',')+2);
						int value = Integer.parseInt(tmp.replaceAll("[^0-9]", ""));
						String address = tmp.substring(0,tmp.indexOf('-')-1);
						
						if(t.getCAPStruttura()==value&&t.getIndirizzoStruttura().equals(address)) {
							System.out.println("PROBLEMAAAAA");
							String errore="In questa data la struttura selezionata è già occupata, selezionarne una diversa";
							request.setAttribute("error", errore);
							RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/FormCreazioneTorneo.jsp");
							dispatcher.forward(request, response);
							return;
						}
						
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/FormInserimentoGiocatori.jsp");
		dispatcher.forward(request, response);
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// System.out.println("Oh vedi che sono stata attivata");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean controlloData(String a,String b) {
		if(a!=null&&b!=null) {
		int dataA=Integer.valueOf(a.replaceAll("[-]", ""));
		int dataB=Integer.valueOf(b.replaceAll("[-]", ""));

		return dataA>dataB;
		}
		else
			return true;
		
	}
	
}


