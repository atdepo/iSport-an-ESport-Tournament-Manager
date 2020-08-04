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
				ArrayList<StrutturaBean> strutture = (ArrayList<StrutturaBean>) sModel.doRetriveAll(null);
				ArrayList<GiocoBean> giochi = (ArrayList<GiocoBean>) gModel.doRetriveAll(null);
				ArrayList<String> numeroTecnici = new ArrayList<String>();
				numeroTecnici.add(String.valueOf(tecModel.doRetriveAll(null).size()));
				ArrayList<ArrayList> col= new ArrayList<ArrayList>();
				col.add(strutture);
				col.add(giochi);
				col.add(numeroTecnici);
				theJson += gson.toJson(col);
				response.getWriter().print(theJson);
				response.getWriter().flush();
				System.out.println("il json di inizializzazione del form è stato creato con successo");
				response.setStatus(200);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		break;
		
		case "getMode":
			
			try {
				String mode="";
				String gioco= request.getParameter("gioco");
				System.out.println("Cerco le modalità di "+gioco);
				ArrayList<ModalitaBean> modalita;
				modalita = (ArrayList<ModalitaBean>) modModel.doRetriveByGame(gioco);
				mode=gson.toJson(modalita);
				response.getWriter().print(mode);
				response.getWriter().flush();
				System.out.println("il json delle modalità è stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		break;
		
		case "validate":
		
			System.out.println("Sto validando il torneo");

			String dataTorneoDaCreare = (String) request.getParameter("datatorneo");
			Date d1 = new Date();
			SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
			String data = df.format(d1);

			if (controlloData(data, request.getParameter("datatorneo"))) {
				request.setAttribute("error",
						"Non possediamo una DeLorean, pertanto ci è impossibile organizzare tornei nel passato!");
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/FormCreazioneTorneo.jsp");
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
							String errore = "In questa data la struttura selezionata è già occupata, selezionarne una diversa";
							request.setAttribute("error", errore);
							RequestDispatcher dispatcher = this.getServletContext()
									.getRequestDispatcher("/FormCreazioneTorneo.jsp");
							dispatcher.forward(request, response);
							return;
						}

					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
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
