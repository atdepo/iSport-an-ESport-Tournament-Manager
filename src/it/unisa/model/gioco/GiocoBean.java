package it.unisa.model.gioco;

import java.io.Serializable;

public class GiocoBean implements Serializable{

	private static final long serialVersionUID = 7463727716757079663L;
	private String nomeGioco;
	
	public GiocoBean() {
		nomeGioco="";
	}
	
	public String getNomeGioco() {
		return nomeGioco;
	}
	
	public void setNomeGioco(String nomeGioco) {
		this.nomeGioco=nomeGioco;
	}
}
