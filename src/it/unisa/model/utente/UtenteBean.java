package it.unisa.model.utente;

import java.io.Serializable;

public class UtenteBean implements Serializable {
	private String username,dataIscrizione,email,img,pIVA;
	Tipo tipo;
	private enum Tipo {utente,tecnico,admin};
	private byte[] password;
	
	
	

	
	
	
	public UtenteBean() {
		
		this.username = "";
		this.dataIscrizione = "";
		this.email ="" ;
		this.password = new byte[32];
		this.img="";
	}
	


	
	public String getTipo() {
		return tipo.name();
	}




	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}




	public String getpIVA() {
		return pIVA;
	}

	public void setpIVA(String pIVA) {
		this.pIVA = pIVA;
	}

	
	public void setImg(String img) {
		this.img = img;
	}
	public String getImg() {
		return img;
	}

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDataIscrizione() {
		return dataIscrizione;
	}
	public void setDataIscrizione(String dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}
}
