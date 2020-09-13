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

import it.unisa.model.squadra.SquadraBean;
import it.unisa.model.struttura.StrutturaBean;
import it.unisa.model.torneo.TournamentBean;
import it.unisa.model.torneo.TournamentModel;
import it.unisa.model.utente.UtenteBean;

/**
 * Servlet implementation class PagamentoControl
 */
@WebServlet({ "/PagamentoControl", "/user/PagamentoControl" })
public class PagamentoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       TournamentModel tModel= new TournamentModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagamentoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Enumeration<String> e=request.getSession().getAttributeNames();
		HttpSession session=request.getSession();
		UtenteBean utente=(UtenteBean)session.getAttribute("user");
		ArrayList<SquadraBean> team=(ArrayList<SquadraBean>)session.getAttribute("squadreTorneo");
		String action = request.getParameter("action"); // azione da far compiere alla servlet

	
	
		Iterator<String> it= e.asIterator();
		System.out.println("*****parametri del pagamento******");
		while(it.hasNext()) {
			String par=it.next();
			System.out.print(par);
			System.out.println(" value: "+request.getSession().getAttribute(par));
		}
		System.out.println("*****fine parametri del pagamento******");
		switch (action) {
		case "conferma":
		TournamentBean torneo=new TournamentBean();
		torneo.setBudget(calcolaTotale(session));
		StrutturaBean struttura=(StrutturaBean)session.getAttribute("struttura");
		torneo.setCAPStruttura(Integer.parseInt(struttura.getCAP()));
		torneo.setCodGioco((String)session.getAttribute("nomeGioco"));
		torneo.setData((String)session.getAttribute("dataTorneo"));
		torneo.setHomePage((Boolean)session.getAttribute("isHome"));
		torneo.setIndirizzoStruttura((String)struttura.getIndirizzo());
		torneo.setNome((String)session.getAttribute("nomeTorneo"));
		torneo.setProprietario(utente.getEmail());
		
		try {
			tModel.doSave(torneo);
			tModel.doSaveComposto(torneo, team);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/**
		 * PER ANTONIO
		 * - associa dei tecnici online (se ci sono) random al torneo+
		 * - associa dei tecnici fisici non impegnati al torneo
		 */
		break;
		case "visualizza":
		session.setAttribute("budget", calcolaTotale(session));
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/user/Pagamento.jsp"));	
		break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private int calcolaTotale(HttpSession session) {
		int totale=0;
		String isHome=(String) session.getAttribute("isHome");
		ArrayList<SquadraBean> team=(ArrayList<SquadraBean>)session.getAttribute("squadreTorneo");

		if(isHome.equals("true")) {
			LocalDate start=LocalDate.now();
			LocalDate end=LocalDate.parse((String)session.getAttribute("dataTorneo"));
		    long days = ChronoUnit.DAYS.between(start, end);
			totale+=(int) Math.ceil(days/7d)*250*team.size();
		}
		totale+=Integer.parseInt((String)session.getAttribute("tecniciRemoti"))*400;
		totale+=Integer.parseInt((String)session.getAttribute("tecniciFisici"))*700;
		return totale;
		
	}
	

}
