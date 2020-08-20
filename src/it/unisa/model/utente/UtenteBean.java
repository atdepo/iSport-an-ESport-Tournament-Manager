package it.unisa.model.utente;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtenteBean implements Serializable {

	private static final long serialVersionUID = 778244887101392503L;
	
	String username,dataIscrizione,email,pIVA;
	 Tipo tipo;
	 public enum Tipo {utente,tecnico,admin};
	 byte[] password;
	 String img;
	
	
	public UtenteBean() {
		
		this.username = "";
		this.dataIscrizione = "";
		this.email ="" ;
		this.password =new byte[32];;
		this.img="";
		
	}
	
	public String getTipo() {
		return tipo.toString();
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

	
	public void setImg(String image) {
		this.img=image;
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
	
	/**
	 * Questo metodo è stato creato per codificare una password passata come plain-text in una codificata
	 * utilizzando l'algoritmo SHA-256
	 * @param passwordToEncode la password in chiaro
	 */
	public void setPassword(String passwordToEncode) {
		
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			byte arr[]=md.digest(passwordToEncode.getBytes());
			this.password=arr;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean isEmpty() {
		if(email==null||email=="")
			return true;
		else
			return false;
	}
}
