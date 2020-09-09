package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.tecnico.TecnicoBean;
import it.unisa.model.tecnico.TecnicoModel;
import it.unisa.model.torneo.TournamentModel;
import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteModel;

/**
 * Servlet implementation class Admin
 */

@WebServlet(urlPatterns = {"/admin/Admin","/Admin"})
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    TecnicoModel tModel=new TecnicoModel();
    TournamentModel torModel=new TournamentModel();
    UtenteModel uModel=new UtenteModel();
    public Admin() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		
		switch (action) {
		case "addTecnico" : 
				TecnicoBean bean=new TecnicoBean();
				bean.setNome(request.getParameter("nome"));
				bean.setCognome(request.getParameter("cognome"));
				bean.setDataDiNascita(request.getParameter("dataN"));
				bean.setIndirizzo(request.getParameter("indirizzo"));
				bean.setRecapito(request.getParameter("recapito"));
				bean.setCF(request.getParameter("CF"));
				
				UtenteBean user=new UtenteBean();
				user.setUsername(request.getParameter("CF"));
				user.setEmail(request.getParameter("email"));
				user.setPassword(request.getParameter("password"));
			try {
				uModel.doSave(user);
				tModel.doSave(bean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
	case "deleteTorneo":
			
			try {
				if(torModel.doRetriveByKey((String)request.getParameter("cod"))!=null)
					torModel.doDelete((String)request.getParameter("cod"));
				
				response.sendRedirect(request.getContextPath()+"/admin/Admin.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		
		
		
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
