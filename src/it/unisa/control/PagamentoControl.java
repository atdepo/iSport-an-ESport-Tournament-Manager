package it.unisa.control;

import java.io.IOException;
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

/**
 * Servlet implementation class PagamentoControl
 */
@WebServlet({ "/PagamentoControl", "/user/PagamentoControl" })
public class PagamentoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		Iterator<String> it= e.asIterator();
		System.out.println("*****parametri del pagamento******");
		while(it.hasNext()) {
			String par=it.next();
			System.out.print(par);
			System.out.println(" value: "+request.getSession().getAttribute(par));
		}
		System.out.println("*****fine parametri del pagamento******");
		
		String action=request.getParameter("action");
		

		/**
		 * PER ANTONIO
		 * - associa dei tecnici online (se ci sono) random al torneo+
		 * - associa dei tecnici fisici non impegnati al torneo
		 */
		
		
		calcolaTotale(request.getSession());
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
