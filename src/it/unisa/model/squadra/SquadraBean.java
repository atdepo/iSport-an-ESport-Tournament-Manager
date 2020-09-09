package it.unisa.model.squadra;

import java.io.Serializable;

public class SquadraBean implements Serializable {

	private static final long serialVersionUID = -991459462790678646L;
	private String nome,nazionalita,teamImage,proprietario;
	
	public SquadraBean() {
		nome="";
		nazionalita="";
		teamImage="";
		proprietario="";
	}
	

	public String getProprietario() {
		return proprietario;
	}
	
	public void setProprietario(String prop) {
		 proprietario=prop;
	}

	
	public String getTeamImage() {
		return teamImage;
	}
	
	public void setTeamImage(String img) {
		 teamImage=img;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	
	public boolean isEmpty() {
		return nome.equals("");
	}
	

}
