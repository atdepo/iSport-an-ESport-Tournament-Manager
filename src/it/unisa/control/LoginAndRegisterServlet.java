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
import java.sql.Blob;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import it.unisa.model.utente.UtenteBean;
import it.unisa.model.utente.UtenteModel;



@WebServlet("/LoginAndRegisterServlet")
public class LoginAndRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtenteModel utenteM;   
  
    public LoginAndRegisterServlet() {
        super();
       
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		System.out.println(action);
		
		UtenteBean utente=new UtenteBean();
		utente.setDataIscrizione((String)request.getParameter("dataIscrizione"));
		utente.setEmail((String)request.getParameter("email"));
		System.out.println(request.getParameter("username"));
		Part part=request.getPart("dick");
		
		InputStream fis=null;
		if(part!=null)
			 fis= part.getInputStream();
		else System.out.println("Chiavt a mammt");
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
           
        }
        byte[] bytes = bos.toByteArray();
        String imagine=Base64.getEncoder().encodeToString(bytes);
        System.out.println(imagine);
      
        }
	

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
        
	}
	

	
}
	
