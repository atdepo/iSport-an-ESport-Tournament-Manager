package it.unisa.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteBean.Tipo;
import it.unisa.model.utente.UtenteModel;

@WebServlet(name = "/LoginAndRegisterControl", urlPatterns = { "/LoginAndRegisterControl", "" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
														// temporarily stored on disk
		maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
		maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
/**
 * Questa Servlet svolge il compito di controller sulle funzionalità di login e registrazione.<br>
 * A questa servlet possono essere impartiti solamente due tipi di comandi tramite il parametro action nella richiesta:
 * -register       -> Registra un nuovo utente inserendo le credenziali 
 * -validateLogin  -> Valida una richiesta di login inviata 
 * tutte le altre eventuali action inviate avranno come conseguenza il reindirizzamento alla pagina home 
 * @author della
 *
 */
public class LoginAndRegisterControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteModel userModel = new UtenteModel();

	public LoginAndRegisterControl() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String regEmail="^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		String regUser="^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$";
		String regIva="^[0-9]{11}$";
		String regPsw="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
		
		String action = request.getParameter("action");
		
		if(action==null) {
			response.sendRedirect("index.jsp");
			return;
		}
		System.out.println("La servlet di login e registrazione sta svolgendo l'azione di :"+action);

		switch (action) {
		
		case "logout":
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
			break;

		case "register": //Effettuo la registrazione dell'utente
			
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				HttpSession session=request.getSession();
				
				//Impostiamogli eventuali errori avuti in precedenza a null per evitare problemi nella visualizzazione
				session.setAttribute("error-type", null); //error-type ci fornisce il campo sul quale abbiamo riscontrato l'errore
				session.setAttribute("error", null); // error ci fornisce il messaggio di errore da visualizzare
				session.setAttribute("error-location", null); //error-location ci fornisce l'indicazione su quale 
															  //dei due form (login|signup) sia presente l'errore
				
				
				//Controllo se la mail inserita in fase di registrazione sia già stata associata a qualche altro utente 
				if (userModel.doRetriveByKey(request.getParameter("email")) != null) {
					System.out.println("guarda che sta mail fa schifo");
					session.setAttribute("error-type", "email");
					session.setAttribute("error", "Questa mail è già stata utilizzata, scegline un'altra");
					session.setAttribute("error-location", "signup");
					response.sendRedirect(request.getContextPath()+"/FormLoginAndRegister.jsp");
					return;
				}
				//Di seguito ci sono vari controlli sui campi inseriti in fase di registrazione
				//(controllati nel caso javascript sia disattivato così da evitiare l'inserimento di dati erronei)
				
				else if(!request.getParameter("email").matches(regEmail)) {
					System.out.println("mi fermo alla mail");
					session.setAttribute("error-type", "email");
					session.setAttribute("error", "La mail non è scritta correttamente");
					session.setAttribute("error-location", "signup");
					response.sendRedirect(request.getContextPath()+"/FormLoginAndRegister.jsp");
				}
				else if(!request.getParameter("username").matches(regUser)) {
					System.out.println("mi fermo allo username");
					session.setAttribute("error-type", "username");
					session.setAttribute("error", "Utente non scritto correttamente");
					session.setAttribute("error-location", "signup");
					response.sendRedirect(request.getContextPath()+"/FormLoginAndRegister.jsp");
				}
				
				else if(!request.getParameter("pIva").isEmpty() && !request.getParameter("pIva").matches(regIva)) {
					System.out.println("mi fermo alla piva");
					session.setAttribute("error-type", "iva");
					session.setAttribute("error", "Partita Iva non scritta correttamente");
					session.setAttribute("error-location", "signup");
					response.sendRedirect(request.getContextPath()+"/FormLoginAndRegister.jsp");
				}
				else if(!request.getParameter("password").matches(regPsw)) {
					System.out.println("mi fermo alla psw");
					session.setAttribute("error-type", "password");
					session.setAttribute("error", "Deve essere almeno 8 caratteri con almeno:un carattere speciale,un lowercase,un UPPERCASE e un numero ");
					session.setAttribute("error-location", "signup");
					response.sendRedirect(request.getContextPath()+"/FormLoginAndRegister.jsp");
				}
				//Se tutti i controlli sono stati superati si crea il bean e si inserisce nel database

				else {
					UtenteBean utente = new UtenteBean();
					utente.setEmail(request.getParameter("email"));
					utente.setUsername(request.getParameter("username"));
					utente.setDataIscrizione(request.getParameter("dataIscrizione"));
					utente.setpIVA(request.getParameter("pIva"));
					utente.setTipo(Tipo.utente);
					utente.setPassword(request.getParameter("password"));

					//Immagine -> BASE64
					Part part = request.getPart("immagine"); //Prende la parte dal multipart form che rappresenta l'immagine di profilo dell'utente
					InputStream fis = null;
					
					fis = part.getInputStream();
					System.out.println("ue uaglio bella sta immagine");
					byte[] buf = new byte[4096];

					for (int readNum; (readNum = fis.read(buf)) != -1;) {
						bos.write(buf, 0, readNum); // no doubt here is 0
						System.out.println("read " + readNum + " bytes,");
					}
					byte[] bytes = bos.toByteArray();
					
					String img="";
					if(bytes.length>0) {
						img = "data:image/jpeg;base64, " + Base64.getEncoder().encodeToString(bytes);
						System.out.println("bytes immagine "+bytes.length);
					}
					
					else {
						img="data:image/png;base64,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";
						
					}
						utente.setImg(img);
						userModel.doSave(utente);
						session.setAttribute("user", utente);
						response.sendRedirect("index.jsp");
					
				}
				
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;

		case "validateLogin": //Effettuo la validazione del login

			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");// Istanzio un MessageDigest con un algoritmo di cifratura SHA 256 
																		//già implementato nativamente in Java
				
				String str = request.getParameter("password");//Prendo la password passata in plain-text 
				byte curr[] = md.digest(str.getBytes());//utilizzo la cifratura SHA-256 per cifrare la stringa
				byte user[] = userModel.getUserPassword(request.getParameter("email")); //Prendo la password dal database corrispondente alla mail inserita
				
				if (Arrays.compare(curr, user) == 0) { //Se le due password cifrate coincidono
					
					UtenteBean utente=userModel.doRetriveByKey(request.getParameter("email")); //Prendo il bean dal database che è identificato univocamente tramite la mail
					request.getSession().setAttribute("user", utente); //Inserisco l'utente correttamente loggato in sessione
					response.sendRedirect("index.jsp"); //Reindirizzo l'utente loggato alla pagina iniziale dove potrà svolgere tutte le attività consentite da utente
					
				} else {//Se le due password non coincidono
					request.getSession().setAttribute("error-type", "wrongCred");
					request.getSession().setAttribute("error", "Password o email errate");
					request.getSession().setAttribute("error-location", "login");
					response.sendRedirect("FormLoginAndRegister.jsp"); //Reindirizzo l'utente nuovamente alla schermata di inserimento delle credenziali
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
			
			default:
				
			response.sendRedirect("index.jsp");
			break;
			
		}
		
		
		// Montefusco merda non cancellare da questo commento
		// String imagine ="data:image/jpeg;base64,
		// "+Base64.getEncoder().encodeToString(bytes);
		// System.out.println("immagine base64= " + imagine);
		// Montefusco merda non cancellare a questo commento

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

}
