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

@WebServlet(name = "/LoginAndRegisterServlet", urlPatterns = { "/LoginAndRegisterServlet", "/ciccio" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
														// temporarily stored on disk
		maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
		maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files

public class LoginAndRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteModel userModel = new UtenteModel();

	public LoginAndRegisterServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		System.out.println(action);

		switch (action) {

		case "register":

			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				HttpSession session=request.getSession();
				if (userModel.doRetriveByKey(request.getParameter("email")) != null) {
					session.setAttribute("error-type", "email");
					session.setAttribute("error", "Questa mail è già stata utilizzata, scegline un'altra");
					session.setAttribute("error-location", "signup");
					response.sendRedirect(request.getContextPath()+"/FormLoginAndRegister.jsp");
				}
				
				else {
					UtenteBean utente = new UtenteBean();
					utente.setEmail(request.getParameter("email"));
					utente.setUsername(request.getParameter("username"));
					utente.setDataIscrizione(request.getParameter("dataIscrizione"));
					utente.setpIVA(request.getParameter("pIva"));
					utente.setTipo(Tipo.utente);
					utente.setPassword(request.getParameter("password"));

					//immagine -> BASE64
					Part part = request.getPart("immagine");
					InputStream fis = null;
					if (part != null) {
						fis = part.getInputStream();

						byte[] buf = new byte[4096];

						for (int readNum; (readNum = fis.read(buf)) != -1;) {
							bos.write(buf, 0, readNum); // no doubt here is 0
							System.out.println("read " + readNum + " bytes,");
						}
						byte[] bytes = bos.toByteArray();
						String img = "data:image/jpeg;base64, " + Base64.getEncoder().encodeToString(bytes);
						// System.out.println("immagine base64= " + img);

						utente.setImg(img);
						userModel.doSave(utente);
						response.sendRedirect("index.jsp");
					}
				}
				
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "validateLogin":

			try {
				MessageDigest md;
				md = MessageDigest.getInstance("SHA-256");
				String str = request.getParameter("password");
				System.out.println(str);
				byte curr[] = md.digest(str.getBytes());
				byte user[] = userModel.getUserPassword(request.getParameter("email"));
				System.out.println("Risutato" + Arrays.compare(curr, user));
				if (Arrays.compare(curr, user) == 0) {
					response.sendRedirect("index.jsp");
				} else {
					response.sendRedirect("FormLogin.jsp");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
