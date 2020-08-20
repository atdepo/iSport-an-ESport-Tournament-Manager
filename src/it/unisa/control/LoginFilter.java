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
		
		String requestURI=hRequest.getRequestURI();
		
		if(requestURI.contains("/user/")) {
			HttpSession session= hRequest.getSession(false);
			checkAccess(session, request, response, chain, hResponse, hRequest, "utente");
		}
		else if(requestURI.contains("/admin/")) {
			HttpSession session= hRequest.getSession(false);
			checkAccess(session, request, response, chain, hResponse, hRequest, "admin");
		}
		else if(requestURI.contains("/tecnico/")) {
			HttpSession session= hRequest.getSession(false);
			checkAccess(session, request, response, chain, hResponse, hRequest, "tecnico");
			
		}
		
	}
	
	
	public void checkAccess(HttpSession session, ServletRequest req, ServletResponse resp, FilterChain chain, HttpServletResponse hresponse, HttpServletRequest hrequest, String tipoUtente) throws IOException, ServletException{
		
		UtenteBean user=(UtenteBean) session.getAttribute("user");
		if(user!=null && user.getTipo().equals("utente"))
			chain.doFilter(req, resp);
		else
			hresponse.sendRedirect(hrequest.getContextPath()+"/FormLoginAndRegister.jsp");
		
		
		
		
	}

}










