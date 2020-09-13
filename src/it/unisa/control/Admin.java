package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.tecnico.TecnicoBean;
import it.unisa.model.tecnico.TecnicoModel;
import it.unisa.model.torneo.TournamentModel;
import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteModel;

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
			String img="data:image/png;base64,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";

				TecnicoBean bean=new TecnicoBean();
				bean.setNome(request.getParameter("nome"));
				bean.setCognome(request.getParameter("cognome"));
				bean.setDataDiNascita(request.getParameter("dataN"));
				bean.setIndirizzo(request.getParameter("indirizzo"));
				bean.setRecapito(request.getParameter("recapito"));
				bean.setCF(request.getParameter("CF"));
				bean.setSpecializzazione(request.getParameter("toggle"));
				System.out.println(request.getParameter("toggle"));
				
				UtenteBean user=new UtenteBean();
				user.setUsername(request.getParameter("CF"));
				user.setEmail(request.getParameter("email"));
				user.setPassword(request.getParameter("password"));
				user.setImg(img);
			try {
				uModel.doSave(user);
				tModel.doSave(bean);
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/admin/Admin.jsp"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
	case "deleteTorneo":
			
			try {
				if(torModel.doRetriveByKey((String)request.getParameter("cod"))!=null)
					torModel.doDelete((String)request.getParameter("cod"));
				
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/admin/Admin.jsp"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}

	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
