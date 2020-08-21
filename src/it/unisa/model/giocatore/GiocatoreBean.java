package it.unisa.model.giocatore;

import java.io.Serializable;

public class GiocatoreBean implements Serializable {

	String nickname,nome,cognome,ruolo,datanascita,nomesquadra,codtecnico,imgPlayer;
	
	public GiocatoreBean() {
		nickname="";
		nome="";
		cognome="";
		ruolo="";
		datanascita="";
		nomesquadra="";
		codtecnico="";
		imgPlayer="";
	}

	public String getPlayerImage() {
		return imgPlayer;
	}
	
	public void setPlayerImage(String img) {
		this.imgPlayer=img;
	}
	
	public String getNickname() {
		return nickname;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getRuolo() {
		return ruolo;
	}

	public String getDatanascita() {
		return datanascita;
	}

	public String getNomesquadra() {
		return nomesquadra;
	}

	public String getCodtecnico() {
		return codtecnico;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public void setDatanascita(String datanascita) {
		this.datanascita = datanascita;
	}

	public void setNomesquadra(String nomesquadra) {
		this.nomesquadra = nomesquadra;
	}

	public void setCodtecnico(String codtecnico) {
		this.codtecnico = codtecnico;
	}
	
	
	
	
}
