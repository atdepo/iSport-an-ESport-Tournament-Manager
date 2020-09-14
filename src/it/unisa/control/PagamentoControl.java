package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import it.unisa.model.squadra.SquadraBean;
import it.unisa.model.struttura.KeyStruttura;
import it.unisa.model.struttura.StrutturaBean;
import it.unisa.model.struttura.StrutturaModel;
import it.unisa.model.tecnico.TecnicoBean;
import it.unisa.model.tecnico.TecnicoModel;
import it.unisa.model.torneo.TournamentBean;
import it.unisa.model.torneo.TournamentModel;
import it.unisa.model.utente.UtenteBean;

/**
 * Servlet implementation class PagamentoControl
 */
@WebServlet({ "/PagamentoControl", "/user/PagamentoControl" })
public class PagamentoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TournamentModel tModel = new TournamentModel();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagamentoControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Enumeration<String> e = request.getSession().getAttributeNames();
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("user");
		String action = request.getParameter("action"); // azione da far compiere alla servlet
		System.out.println("l'azione e'" + action);

		Iterator<String> it = e.asIterator();
		System.out.println("*****parametri del pagamento******");
		while (it.hasNext()) {
			String par = it.next();
			System.out.print(par);
			System.out.println(" value: " + request.getSession().getAttribute(par));
		}
		System.out.println("*****fine parametri del pagamento******");
		
		switch (action) {
		case "conferma":
			TournamentBean torneo = new TournamentBean();
			StrutturaModel sModel= new StrutturaModel();
			torneo.setBudget(calcolaTotale(session));
			StrutturaBean struttura = (StrutturaBean) session.getAttribute("struttura");
			String errore=null;
			String tipo=null;
			StrutturaBean alternativa=new StrutturaBean();
			try {
				ArrayList<TournamentBean> tornei = (ArrayList<TournamentBean>) tModel.doRetriveAll(null);
				for (TournamentBean t : tornei) {
					if (t.getData().equals((String) session.getAttribute("dataTorneo"))) {
						String s = (String) session.getAttribute("struttura");
						String tmp = s.substring(s.indexOf(',') + 2);
						int value = Integer.parseInt(tmp.replaceAll("[^0-9]", ""));
						String address = tmp.substring(0, tmp.indexOf('-') - 1);
						

						if (t.getCAPStruttura() == value && t.getIndirizzoStruttura().equals(address)) {
							errore = "Ci dispiace, in questa data la struttura"+s+"e' gia' stata occupata ";
							tipo="struttura";
						}
						else if(errore!=null){
							alternativa=sModel.doRetriveByKey(new KeyStruttura(String.valueOf(t.getCAPStruttura()), String.valueOf(t.getIndirizzoStruttura())));
							struttura=alternativa;
							/*errore+="in alternativa ti andrebbe bene la struttura "+alternativa.getNome()+"? (NB. premendo annulla il tuo torneo "
								  + "diventera' automaticamente online e di conseguenza perderai tutti i tecnici fisici scelti al momento della registrazione)";
							session.setAttribute("error-msg", errore);
							session.setAttribute("error-tipo", tipo);
							session.setAttribute("error-alternativa", alternativa);
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/Pagamento.jsp"));
							return;*/
						}
					}
				}

				torneo.setCAPStruttura(Integer.parseInt(struttura.getCAP()));
				torneo.setCodGioco((String) session.getAttribute("nomeGioco"));
				torneo.setData((String) session.getAttribute("dataTorneo"));
				torneo.setIndirizzoStruttura((String) struttura.getIndirizzo());
				torneo.setHomePage(Boolean.getBoolean((String) session.getAttribute("isHome")));
				torneo.setNome((String) session.getAttribute("nomeTorneo"));
				torneo.setProprietario(utente.getEmail());

				tModel.doSave(torneo);
				ArrayList<SquadraBean> team = (ArrayList<SquadraBean>) session.getAttribute("squadreTorneoDaInserire");
				tModel.doSaveComposto(torneo, team);

				TecnicoModel teModel = new TecnicoModel();
				if (Integer.parseInt((String)session.getAttribute("tecniciFisici")) > 0) {
					ArrayList<TecnicoBean> liberi = teModel.doRetrieveTecniciLiberi(torneo,
							Integer.parseInt((String) session.getAttribute("tecniciFisici")));
					int max = tModel.maxTorneo();
					if (liberi.size() <= Integer.parseInt((String) session.getAttribute("tecniciFisici"))) {
						
						for (int i=0;i<Integer.parseInt((String)session.getAttribute("tecniciFisici"));i++ ) {
							teModel.doAssocia(liberi.get(i), max);
						}
						} 
						else {
							for (int i=0;i<liberi.size();i++ ) {
								teModel.doAssocia(liberi.get(i), max);
							}
						}
					
						ArrayList<TecnicoBean> online=teModel.doRetrieveTecnici("on-line");
						for (int i=0;i<Integer.parseInt((String)session.getAttribute("tecniciRemoti"));i++ ) {
							teModel.doAssocia(online.get(i), max);
						}
					

				}
				ArrayList<TournamentBean> t=(ArrayList<TournamentBean>) session.getAttribute("torneiDaMostrare");
				t.add(torneo);
				session.setAttribute("torneiDaMostrare", t);
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/user/Profilo.jsp"));
			}

			catch (SQLException ex) {
				ex.printStackTrace();
			}

			break;
		case "visualizza":
			session.setAttribute("budget", calcolaTotale(session));
			ArrayList<SquadraBean> bean=(ArrayList<SquadraBean>) session.getAttribute("squadreTorneo");
			session.setAttribute("squadreTorneoDaInserire", bean);
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private int calcolaTotale(HttpSession session) {
		int totale = 0;
		String isHome = (String) session.getAttribute("isHome");
		ArrayList<SquadraBean> team = (ArrayList<SquadraBean>) session.getAttribute("squadreTorneo");

		if (isHome.equals("true")) {
			LocalDate start = LocalDate.now();
			LocalDate end = LocalDate.parse((String) session.getAttribute("dataTorneo"));
			long days = ChronoUnit.DAYS.between(start, end);
			totale += (int) Math.ceil(days / 7d) * 250 * team.size();
		}
		totale += Integer.parseInt((String) session.getAttribute("tecniciRemoti")) * 400;
		totale += Integer.parseInt((String) session.getAttribute("tecniciFisici")) * 700;
		return totale;

	}

}
