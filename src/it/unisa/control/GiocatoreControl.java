package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.giocatore.GiocatoreBean;
import it.unisa.model.giocatore.GiocatoreModel;
import it.unisa.model.squadra.SquadraBean;
import it.unisa.model.squadra.SquadraModel;

/**
 * Servlet implementation class GiocatoreControl
 */

@WebServlet(urlPatterns = {"/GiocatoreControl","/user/GiocatoreControl"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will temporarily stored on disk
maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
public class GiocatoreControl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Gson gson= new Gson();
	SquadraModel sqModel= new SquadraModel();
	GiocatoreModel pModel = new GiocatoreModel();
	ArrayList<String>nicknames= new ArrayList<String>();
	
    public GiocatoreControl() {
        super();
        // TODO Auto-generated constructor stub
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action= request.getParameter("action");



switch(action) {

/**
 * Questo metodo viene utilizzato per effettuare la validazione backend di un singolo giocatore.
 * Si possono verificare due casi:
 * - Nel caso in cui la validazione sia eseguita senza generare errori, il JSON inviato conterra "null"
 * - Invece nel caso in cui la validazione abbia generato qualche errore, il JSON inviato conterra'
 * 	 la stringa di testo contente l'errore da mostrare nel front-end
 * 
 */    
case "validatePlayer":
		ArrayList<String> errori= new ArrayList<String>();
    	String nick=request.getParameter("nick"); //Inviato nella richiesta alla servlet
    	int num=Integer.valueOf(request.getParameter("numPlayer"));
		if(nick!=null) {
			try {
				if(pModel.doRetriveByKey(nick)!=null) {     //Se il nickname scelto è gia' presente nel database
					errori.add("Nickname gia' utilizzato"); //setto l'errore
					String json=gson.toJson(errori);
					response.getWriter().print(json);		//Mando il JSON con l'errore
					response.getWriter().flush();
					response.setStatus(200);
					System.out.println("Questo nickname e' gia' stato utilizzato");
				}
				else 
					if(checkTeamNick(nick,num)) {
						errori.add("Nickname gia' utilizzato precedentemente"); //setto l'errore
						String json=gson.toJson(errori);
						response.getWriter().print(json);		//Mando il JSON con l'errore
						response.getWriter().flush();
						response.setStatus(200);
						System.out.println("Questo nickname e' gia' stato utilizzato precedentemente");
					}
					else
						if(nick.isEmpty()){ 						//Se il nickname inserito è vuoto
							
							errori.add("Inserisci un nickname"); 	//setto l'errore
							String json=gson.toJson(errori);
							response.getWriter().print(json);		//Mando il JSON con l'errore
							response.getWriter().flush();
							response.setStatus(200);
							System.out.println("Inserisci un nickname valido");
								
						}
						else {										//Se entrambi i controlli sono passati correttamente
							
							errori.add("null");							//setto l'errore a "null"
					    	nicknames.add(num-1,nick);
							String json=gson.toJson(errori);
							response.getWriter().print(json);			//Mando il JSON comunicando l'assenza di errori
							response.getWriter().flush();
							response.setStatus(200);
							System.out.println("Validazione del giocatore andata a buon fine");
							
							}
					} catch (Exception e) {
					e.printStackTrace();
				}
		}
			else
				errori.add("Si è verificato un problema");// nel caso il retrieve del nickname non sia andato a buon fine
		break;
		
		/**
		 * Questo case viene utilizzato per la validazione backend della scheda di inserimento della squadra.
		 * Si possono verificare due casi:
		 * - Nel caso in cui la validazione sia eseguita senza generare errori, il JSON inviato conterra "null"
		 * - Invece nel caso in cui la validazione abbia generato qualche errore, il JSON inviato conterra'
		 * 	 la stringa di testo contente l'errore da mostrare nel frontend
		 */
		case "validateTeam":
		
			String name=request.getParameter("teamName");
			
			try {
				ArrayList<String> errorit= new ArrayList<String>();
				if(sqModel.doRetriveByKey(name)!=null){
					errorit.add("Nome della squadra gia' utilizzato");
					String json=gson.toJson(errorit);
					response.getWriter().print(json);
					response.getWriter().flush();
					response.setStatus(200);
					System.out.println("Nome della squadra gia' utilizzato");
				} else 
					if(name.isEmpty()) {
						errorit.add("Inserisci un nome della squadra");
						String json=gson.toJson(errorit);
						response.getWriter().print(json);
						response.getWriter().flush();
						response.setStatus(200);
						System.out.println("Inserisci un nome della squadra");
						
					} else {
						errorit.add("null");
						String json=gson.toJson(errorit);
						response.getWriter().print(json);
						response.getWriter().flush();
						response.setStatus(200);
						System.out.println("Validazione del giocatore andata a buon fine");
					}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		break;
		
		/**
		 * Questo case viene utilizzato alla fine del form di inserimento dei giocatori.
		 * Il suo compito è quello di validare tutti i campi inseriti all'interno del form,
		 * al fine di evitare l'inserimento di dati incompleti o errati all'interno del database.
		 * 
		 * Successivamente alla validazione, se avrà avuto successo, i dati della squadra e dei giocatori
		 * verranno inseriti all'interno del database e inseriti all'interno del form di inserimento delle 
		 * squadre tramite una apposita chiamata alla servlet 
		 * 
		 */
		case "totalValidate":
			
			String squadra=request.getParameter("nome-squadra");
			String nazionalita=request.getParameter("nazioni");
			int numeroGiocatori= Integer.valueOf(request.getParameter("numeroPartecipanti"));
			
			try {
				//Se la squadra è gia' presente nel database, oppure uno dei campi è vuoto
				if(sqModel.doRetriveByKey(squadra)!=null || squadra.isEmpty() || nazionalita.isEmpty()){
					response.sendRedirect(request.getContextPath()+"/oopsPage.jsp");// pagina oops
				} 
				
				//Se i dati della squadra sono buoni
				else{
					//controllo tutti i giocatori che ci sono sono inseriti correttamente
					for(int i=1;i<=numeroGiocatori;i++) {
						
						String nickname=request.getParameter("nickname-player-"+i);
						String nome=request.getParameter("nome-giocatore-"+i);
						String cognome=request.getParameter("cognome-giocatore-"+i);
						String ruolo=request.getParameter("ruolo-giocatore-"+i);
						String data=request.getParameter("nascita-giocatore-"+i);
						
						//se il nick del giocatore i-esimo è presente oppure uno dei campi è vuoto 
						if(sqModel.doRetriveByKey(nickname)!=null ||nickname.isEmpty() || nome.isEmpty()|| cognome.isEmpty() || ruolo.isEmpty()   || data.isEmpty()) {
							response.sendRedirect(request.getContextPath()+"/oopsPage.jsp");// pagina oops	
						}		
					}
					SquadraBean team= new SquadraBean();
					team.setNazionalita(request.getParameter("nazioni"));
					team.setNome(request.getParameter("nome-squadra"));
					team.setTeamImage("");
					sqModel.doSave(team);
					
					for(int j=1;j<=numeroGiocatori;j++) {
						GiocatoreBean bean= new GiocatoreBean();
						bean.setNickname(request.getParameter("nickname-player-"+j));
						bean.setNome(request.getParameter("nome-giocatore-"+j));
						bean.setCognome(request.getParameter("cognome-giocatore-"+j));
						bean.setRuolo(request.getParameter("ruolo-giocatore-"+j));
						bean.setDatanascita(request.getParameter("nascita-giocatore-"+j));
						bean.setNomesquadra(team.getNome());
						bean.setPlayerImage("");
						bean.setCodtecnico("");
						pModel.doSave(bean);
					}
					response.sendRedirect(request.getContextPath()+"/FormInserimentoSquadre.jsp");
					
				}
			} catch (SQLException e) {
				response.sendRedirect(request.getContextPath()+"/oopsPage.jsp");// pagina oops
				e.printStackTrace();
			}
							
		break;
    	
    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	public boolean checkTeamNick(String control,int pos) {
		pos=pos-1;
		
		for(int j=0;j<pos;j++) {
			if(nicknames.get(j).equals(control))
				return true;
		}
		System.out.println("------i giocatori fino ad ora------");
		for(String s:nicknames) {
			System.out.println(s);
		}
		System.out.println(control);
		return false;
	}
}
