package it.unisa.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.giocatore.GiocatoreModel;
import it.unisa.model.squadra.SquadraModel;

/**
 * Servlet implementation class GiocatoreControl
 */
@WebServlet("/GiocatoreControl")
public class GiocatoreControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GiocatoreControl() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String action= request.getParameter("action");
    	Gson gson= new Gson();
    	SquadraModel sqModel= new SquadraModel();
    	GiocatoreModel pModel = new GiocatoreModel();
    	
    	switch(action) {
    	
    	case "validatePlayer":
			String nick=request.getParameter("nick");
			
			try {
				ArrayList<String> errori= new ArrayList<String>();
				if(pModel.doRetriveByKey(nick)!=null) {
					errori.add("Nickname gia' utilizzato");
					String json=gson.toJson(errori);
					response.getWriter().print(json);
					response.getWriter().flush();
					response.setStatus(200);
					System.out.println("problema nel controller!");
				}
				else 
					if(nick.isEmpty()){
						errori.add("Inserisci un nickname");
						String json=gson.toJson(errori);
						response.getWriter().print(json);
						response.getWriter().flush();
						response.setStatus(200);
						System.out.println("problema nel controller!");
					}
					else {
						
					errori.add("null");
					String json=gson.toJson(errori);
					response.getWriter().print(json);
					response.getWriter().flush();
					response.setStatus(200);
					System.out.println("non ho avuto problemi nel controller!");
				}
			} catch (Exception e) {
				e.printStackTrace();
}
		break;
		
		case "validateTeam":
		
			String name=request.getParameter("teamName");
			
			try {
				ArrayList<String> errori= new ArrayList<String>();
				if(sqModel.doRetriveByKey(name)!=null){
					errori.add("Nome della squadra gia' utilizzato");
					String json=gson.toJson(errori);
					response.getWriter().print(json);
					response.getWriter().flush();
					response.setStatus(200);
					System.out.println("problema nel controller!");
				} else 
					if(name.isEmpty()) {
						errori.add("Inserisci un nome della squadra");
						String json=gson.toJson(errori);
						response.getWriter().print(json);
						response.getWriter().flush();
						response.setStatus(200);
						System.out.println("problema nel controller!");
						
					} else {
						errori.add("null");
						String json=gson.toJson(errori);
						response.getWriter().print(json);
						response.getWriter().flush();
						response.setStatus(200);
						System.out.println("non ho avuto problemi nel controller!");
					}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		break;
    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
