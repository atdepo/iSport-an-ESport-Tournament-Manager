package it.unisa.control;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.model.utente.UtenteBean;

import javax.servlet.*;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/admin/*", "/user/*", "/tecnico/*"})
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hRequest=(HttpServletRequest)request; 
		HttpServletResponse hResponse=(HttpServletResponse)response; 
		//System.out.println("sono stata chiamata da "+hRequest.getHeader("referer"));<-- utile
		System.out.println("la sessione è valida?"+hRequest.isRequestedSessionIdValid());
		HttpSession session= hRequest.getSession(true);// Prendo la sessione, ma non ne creo un'altra se è invalida
		if(!hRequest.isRequestedSessionIdValid()) {//se la sessione non è valida
			
			session=hRequest.getSession(true);//ne creo una nuova
		}
		
		System.out.println("Sto filtrando la richiesta");
		String requestURI=hRequest.getRequestURI();
		
		if(requestURI.contains("/user/")) {
			System.out.println("il path contiene *user*");
			checkAccess(session, request, response, chain, hResponse, hRequest, "utente");
		}
		else if(requestURI.contains("/admin/")) {
			System.out.println("il path contiene *admin*");
			checkAccess(session, request, response, chain, hResponse, hRequest, "admin");
		}
		else if(requestURI.contains("/tecnico/")) {
			System.out.println("il path contiene *tecnico*");
			checkAccess(session, request, response, chain, hResponse, hRequest, "tecnico");
			
		}
		
	}
	
	
	public void checkAccess(HttpSession session, ServletRequest sRequest, ServletResponse sResponse, FilterChain chain, HttpServletResponse hResponse, HttpServletRequest hRequest, String tipoUtente) throws IOException, ServletException{
		
		if(session!=null) {
			System.out.println("la sessione va bene");
			UtenteBean user=(UtenteBean) session.getAttribute("user");
			if(user!=null && user.getTipo().equals(tipoUtente))
				chain.doFilter(sRequest, sResponse);
			else 
				if(user==null)
					hResponse.sendRedirect(hRequest.getContextPath()+"/FormLoginAndRegister.jsp");
				else
					hResponse.sendRedirect(hRequest.getContextPath()+"/index.jsp");
		}
		
		
		
		
		
	}

}










