package it.unisa.model.modalita;

import java.io.Serializable;

public class ModalitaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 544928221774913146L;
	String tipo,nomeGioco;
	int numPartecipanti;
	
	public ModalitaBean() {
		tipo="";
		nomeGioco="";
		numPartecipanti=0;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeGioco() {
		return nomeGioco;
	}

	public void setNomeGioco(String nomeGioco) {
		this.nomeGioco = nomeGioco;
	}

	public int getNumPartecipanti() {
		return numPartecipanti;
	}

	public void setNumPartecipanti(int numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}
	
	
	
	
}
