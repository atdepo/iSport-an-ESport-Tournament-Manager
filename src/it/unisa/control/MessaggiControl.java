package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.messaggio.MessaggioBean;
import it.unisa.model.messaggio.MessaggioModel;

/**
 * Servlet implementation class MessaggiControl
 */
@WebServlet("/MessaggiControl")
public class MessaggiControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Gson gson= new Gson();
       MessaggioModel mModel;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessaggiControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		String str="";
		int cod;

		switch (action) {
		case "getMessaggi": 
			str="";
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			System.out.println("Prendo i messaggi");
			
			try {
				System.out.println("asd i messaggi");

				ArrayList<MessaggioBean> st = (ArrayList<MessaggioBean>) mModel.doRetriveAll(null);
				str=gson.toJson(st);
				response.getWriter().print(str);
				response.getWriter().flush();
				System.out.println("il json dei messaggi e' stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		break;
		
		case "delMessaggio":
			cod= Integer.parseInt(request.getParameter("codice"));
			try {
				mModel.doDelete(cod);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		
		case "showMessaggio":
			str="";
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			System.out.println("Mostro il messaggi");
			try {
				 cod= Integer.parseInt(request.getParameter("codice"));
				MessaggioBean messaggio = mModel.doRetriveByKey(cod);
				str=gson.toJson(messaggio);
				response.getWriter().print(str);
				response.getWriter().flush();
				System.out.println("il json dei messaggi e' stato creato con successo");
				response.setStatus(200);
			} catch (SQLException e) {
				// TODO: handle exception
			}
	}
	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
