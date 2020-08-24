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
		System.out.println("Sto filtrando la richiesta");
		String requestURI=hRequest.getRequestURI();
		
		if(requestURI.contains("/user/")) {
			HttpSession session= hRequest.getSession(false);
			checkAccess(session, request, response, chain, hResponse, hRequest, "utente");
		}
		else if(requestURI.contains("/admin/")||requestURI.contains("/user/")) {
			HttpSession session= hRequest.getSession(false);
			checkAccess(session, request, response, chain, hResponse, hRequest, "admin");
		}
		else if(requestURI.contains("/tecnico/")||requestURI.contains("/user/")) {
			HttpSession session= hRequest.getSession(false);
			checkAccess(session, request, response, chain, hResponse, hRequest, "tecnico");
			
		}
		
	}
	
	
	public void checkAccess(HttpSession session, ServletRequest sRequest, ServletResponse sResponse, FilterChain chain, HttpServletResponse hResponse, HttpServletRequest hRequest, String tipoUtente) throws IOException, ServletException{
		
		if(session!=null) {
			UtenteBean user=(UtenteBean) session.getAttribute("user");
			if(user!=null && user.getTipo().equals(tipoUtente))
				chain.doFilter(sRequest, sResponse);
			else
				hResponse.sendRedirect(hRequest.getContextPath()+"/FormLoginAndRegister.jsp");
		}
		
		
		
		
		
	}

}










