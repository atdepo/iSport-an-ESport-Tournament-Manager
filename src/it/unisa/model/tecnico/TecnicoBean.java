package it.unisa.model.tecnico;

import java.io.Serializable;

public class TecnicoBean implements Serializable{


	private static final long serialVersionUID = 1960861329438531433L;
	String nome,cognome,dataDiNascita,indirizzo,recapito,CF,specializzazione;
	
	public TecnicoBean() {
		nome="";
		cognome="";
		dataDiNascita="";
		indirizzo="";
		recapito="";
		CF="";
		specializzazione="";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public String getCF() {
		return CF;
	}

	public void setCF(String cF) {
		CF = cF;
	}

	public String getSpecializzazione() {
		return specializzazione;
	}

	public void setSpecializzazione(String specializzazione) {
		this.specializzazione = specializzazione;
	}

	
	
}
