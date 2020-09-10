package it.unisa.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.buf.ByteChunk.ByteOutputChannel;

import com.google.gson.Gson;

import it.unisa.model.giocatore.GiocatoreBean;
import it.unisa.model.giocatore.GiocatoreModel;
import it.unisa.model.squadra.SquadraBean;
import it.unisa.model.squadra.SquadraModel;
import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteModel;

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
	UtenteModel uModel = new UtenteModel();
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
			System.out.println("-------------CHIAMO IL TOTAL VALIDATE----------------");
			String squadra=request.getParameter("nome-squadra");
			String nazionalita=request.getParameter("nazioni");
			UtenteBean u= (UtenteBean)request.getSession().getAttribute("user");
			int numeroGiocatori= Integer.valueOf(request.getParameter("numeroPartecipanti"));
			
			try {
				//Se la squadra è gia' presente nel database, oppure uno dei campi è vuoto
				if(pModel.doRetriveByKey(squadra)!=null || squadra.isEmpty() || nazionalita.isEmpty()){
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
							return;
						}		
					}
					//--------INIZIO INSERIMENTO DELLA SQUADRA---------------------
					SquadraBean team= new SquadraBean();
					team.setNazionalita(request.getParameter("nazioni"));
					team.setNome(request.getParameter("nome-squadra"));
					Part image= request.getPart("images-0");//Prende la parte dal multipart form che rappresenta l'immagine della squadra

					if(image==null)
						team.setTeamImage("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJYAAACWCAAAAAAZai4+AAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QA/4ePzL8AAAAHdElNRQfkCQMIJQ5f/JLzAAAFCUlEQVR42u3biXbaOBQG4L7/cwnbYMzeAAFSAiGhBMISAgSwNVDKIbSSfRfLw5nx/wD4O5IX6eryLXOT+fZvA1JWykpZKStlpawbScpKWSnrFpKyUlbKuoX8t1nikhthHSBOvlxvPXS7P1qNqpe1YqBxWcIuNJ/n630gfyXwN+8/O2Unw5SxWCLjtSeb36Iv2c0fyxYLxmAJq/K0/tt0ynZUdxgwBqvwspMh8ac1+ohRWSLbXcuI7IaFpFmVWRClOmR1byXJstobAOo4k4McaSJJLOfJh6kOmRQoLgorN4RM4DmLUjKs7CsCdchHGT9eeJYzxKmkfMfPI5pl9TEzeMrUNc5qwe/2S4a2WZYoA98M1/E7ZlnZKUUl5QZ52yNZP/A31iljxyDLWxFV0m+ghgvFsp6pqsNbFfU0YliivKWz5ANmuFCj1WOo5Axzd2FY+Q8Oy79DDBeCJe6pj+EpQ8TaC8GykJ/oP7PKG2HlI1fJ4Qnq8FmEs0SdN4dSDoyM1hNTJefwZxHOsqdc1id83QVn5Vivh2P8WvwsUeS84k9pG2B9p6z/rtM3wGpwH0Qpn6EXQ7DabJUcGWB1+Kzx/4cVwyS+GmDFcMu/GGDF8YIwwCrt2KyOgY+PS971nINYnyI+1TMua1s0wMowdmOnvGfB10IsA5vcR3EIHwIEq0CqilwS3JtYNGfsCY+1QVTfMBsy5ufn1cyGjDmLQdPM9pX5LC5yiCuhSiO1PYP1iBkA1GjZjH31yjPGEqVP8p3VxlwIW6Qkb2Fn8Dc8geW+01S770ZLuuKOtLwJesgDPPRxQZfyZZzgppDAcn7iVfhDH/xRlPuGVa2r6IvgWcKb41SfmKIpmXVwTVFjdYe/BOn0VbgjxH1VTepQOJPJ9oFfx2BM6zkgNhxYDVARbtsjto6Qu0YiekaO8ac16q+TWcJqRqwK991s0s0swq70lxG7/2DZr9hEGIklnPoEVEndToh9SZT3llN/A5dJ/DcSDM0SFgL1G4ZvmEKzikP0gn4/LJplCadDOo9ad5AziWMVx8Q6RDDGDRiGZTUZJa5VE7NARbCcHqseuOshDqvhRUp3xCwkBSM3/rJbYcpDHTMFLyeALFFCLknVmZeA4wVjicoyDpWUywrMBWKJMvuI8xxgRx6EJYqLuFRSLkDlZgBLeOzS99fMPIALwMqO41RJOQZssaNZ9oB/BnWVYBDdKRjN6sSsOriiOwWjWKJKrrTp8xm5d4xi5WN8CC9ZRHUBRbCsFxMqKV8sDks0OLXlkOwjOhjDWV5M35y/s/QYrIEpVVTXVBhL1PhtNdpsQ/uAwlh2zK/364xtGkvc88/yQ+KHHS+GsHJGXlmXhJ1N6VmiHftX5zpBSDuXnmV6sEKHS8syPlihw6Vl8TsUozO1sSxxZ+iz8zV7bcVex7LQf5uhRNvmrGGxmx5g0fYg6FgPSaj0vf0alhPrZkcfXW+/miUq/GYtUHaaXbZmtB6TUWnbENQsJ5ZCCCSarnAlS5QTmsPDLKprEmpWQs/hMQ9wFvdfF5io+5SULDe2ulF0PlwoS1QT+B6es1fusJWsBG8tzc2lnMREPtPnKBsZVazk3lrHKN9cKpaXyOrhnI0HY4ma0Y3Yn1H+n0XFaiWpkrIFZLH+hohPD8ZK9kFUP4oKlo1uOeLlzQaxHOP71ussHBCL36+Py8qFsETBQG05LKo/4ilYya0BT1GtBP8BSE5nR7U+M5cAAAAldEVYdGRhdGU6Y3JlYXRlADIwMjAtMDktMDNUMDg6Mzc6MDArMDA6MDDaKuHIAAAAJXRFWHRkYXRlOm1vZGlmeQAyMDIwLTA5LTAzVDA4OjM3OjAwKzAwOjAwq3dZdAAAAABJRU5ErkJggg==");
					else {
						//Immagine -> BASE64
						ByteArrayOutputStream bos= new ByteArrayOutputStream();
						InputStream fis = null;
						
						fis = image.getInputStream();
						byte[] buf = new byte[4096];

						for (int readNum; (readNum = fis.read(buf)) != -1;) {
							bos.write(buf, 0, readNum); // no doubt here is 0
							System.out.println("read " + readNum + " bytes,");
						}
						byte[] bytesTeam = bos.toByteArray();
						
						String img;
						img = "data:image/jpeg;base64, " + Base64.getEncoder().encodeToString(bytesTeam);
						System.out.println("bytes immagine "+bytesTeam.length);
						team.setTeamImage(img);
					}
					
					team.setProprietario(u.getEmail());
					sqModel.doSave(team);
					//-----------------FINE INSERIMENTO DELLA SQUADRA----------------
					
					
					
					
					//------------INIZIO INSERIMENTO DEI GIOCATORI---------------
					String img;
					for(int j=1;j<=numeroGiocatori;j++) {
						GiocatoreBean bean= new GiocatoreBean();
						bean.setNickname(request.getParameter("nickname-player-"+j));
						bean.setNome(request.getParameter("nome-giocatore-"+j));
						bean.setCognome(request.getParameter("cognome-giocatore-"+j));
						bean.setRuolo(request.getParameter("ruolo-giocatore-"+j));
						bean.setDatanascita(request.getParameter("nascita-giocatore-"+j));
						bean.setNomesquadra(team.getNome());
						
						//Immagine -> BASE64
						ByteArrayOutputStream bos= new ByteArrayOutputStream();
						Part part = request.getPart("image-"+j); //Prende la parte dal multipart form che rappresenta l'immagine del giocatore j-esimo
						if(part!=null) {
						InputStream fis = null;
						
						fis = part.getInputStream();
						byte[] buf = new byte[4096];

						for (int readNum; (readNum = fis.read(buf)) != -1;) {
							bos.write(buf, 0, readNum); // no doubt here is 0
							System.out.println("read " + readNum + " bytes,");
						}
						byte[] bytes = bos.toByteArray();
						
						
						
							img = "data:image/jpeg;base64, " + Base64.getEncoder().encodeToString(bytes);
							System.out.println("bytes immagine "+bytes.length);
					}
						else
							 img="data:image/png;base64,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";

						bean.setPlayerImage(img);
						pModel.doSave(bean);
					}
					
					//------------FINE INSERIMENTO DEI GIOCATORI---------------
					ArrayList<SquadraBean> sq=(ArrayList<SquadraBean>)request.getSession().getAttribute("squadreTorneo");
					sq.add(team);
					request.setAttribute("squadreTorneo", sq);
					response.sendRedirect(request.getContextPath()+"/user/FormInserimentoSquadre.jsp");
					
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
		/*System.out.println("------i giocatori fino ad ora------");
		for(String s:nicknames) {
			System.out.println(s);
		}
		System.out.println(control);
		
		*/
		return false;
	}
}
