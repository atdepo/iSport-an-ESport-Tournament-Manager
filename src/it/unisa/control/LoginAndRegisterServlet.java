package it.unisa.control;

import java.util.Base64.Encoder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.json.JSONParser;

import com.google.gson.Gson;

import it.unisa.model.struttura.StrutturaBean;
import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteBean.Tipo;
import it.unisa.model.utente.UtenteModel;

@WebServlet(name = "/LoginAndRegisterServlet", urlPatterns = { "/LoginAndRegisterServlet" }, initParams = {
		@WebInitParam(name = "file-upload", value = "tmpDir") })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
														// temporarily stored on disk
		maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
		maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files

public class LoginAndRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteModel userModel= new UtenteModel();

	public LoginAndRegisterServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		System.out.println(action);
		
		switch (action) {

		case "register":

			UtenteBean utente = new UtenteBean();
			utente.setEmail(request.getParameter("email"));
			utente.setUsername(request.getParameter("username"));
			utente.setDataIscrizione(request.getParameter("dataIscrizione"));
			utente.setpIVA(request.getParameter("pIva"));
			utente.setTipo(Tipo.utente);
			utente.setPassword(request.getParameter("password"));
			
			//Immagine -> BASE64
			Part part = request.getPart("immagine");
			InputStream fis = null;
			if (part != null) {
				fis = part.getInputStream();
				
				byte[] buf = new byte[4096];
				try (ByteArrayOutputStream bos = new ByteArrayOutputStream()){

					for (int readNum; (readNum = fis.read(buf)) != -1;) {
						bos.write(buf, 0, readNum); // no doubt here is 0
						System.out.println("read " + readNum + " bytes,");
					}
					byte[] bytes = bos.toByteArray();
					String img ="data:image/jpeg;base64, "+Base64.getEncoder().encodeToString(bytes);
					//System.out.println("immagine base64= " + img);
					
					
					utente.setImg(img);
					userModel.doSave(utente);
					response.sendRedirect("index.jsp");
					
				} catch (IOException ex){
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				catch (SQLException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			break;
			
		case "validateLogin":
		
			
			try {
				MessageDigest md;
				md = MessageDigest.getInstance("SHA-256");
				String str=request.getParameter("password");
				System.out.println(str);
				byte curr[]=md.digest(str.getBytes());
				byte user[]=userModel.getUserPassword(request.getParameter("email"));
				
				
				System.out.println("----------curr--------------");
				for(Byte b:curr) {
					System.out.print(b+" ");
				}

				System.out.println("\n----------user--------------");
				
				for(Byte b:user) {
					System.out.print(b+" ");
				}
				
				System.out.println("Risutato"+Arrays.compare(curr,user));
				if(Arrays.compare(curr,user)==0) {
					response.sendRedirect("index.jsp");
				}
				else {
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
		//Montefusco merda non cancellare da questo commento
		 //String imagine ="data:image/jpeg;base64, "+Base64.getEncoder().encodeToString(bytes);
		 //System.out.println("immagine base64= " + imagine);
		//Montefusco merda non cancellare a questo commento

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);

	}

}
