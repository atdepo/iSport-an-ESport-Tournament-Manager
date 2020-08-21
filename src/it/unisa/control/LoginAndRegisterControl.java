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
		System.out.println("La servlet di login e registrazione sta svològendo l'azione di :"+action);

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
					session.setAttribute("error-type", "email");
					session.setAttribute("error", "Questa mail è già stata utilizzata, scegline un'altra");
					session.setAttribute("error-location", "signup");
					response.sendRedirect(request.getContextPath()+"/FormLoginAndRegister.jsp");
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
					session.setAttribute("error-type", "psw");
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
					if (part != null) { //se l'immagine è stata inserita procediamo al parsing in base64
						fis = part.getInputStream();

						byte[] buf = new byte[4096];

						for (int readNum; (readNum = fis.read(buf)) != -1;) {
							bos.write(buf, 0, readNum); // no doubt here is 0
							System.out.println("read " + readNum + " bytes,");
						}
						byte[] bytes = bos.toByteArray();
						String img = "data:image/jpeg;base64, " + Base64.getEncoder().encodeToString(bytes);
						utente.setImg(img);
						userModel.doSave(utente);
						session.setAttribute("user", utente);
						response.sendRedirect("index.jsp");
					}
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
