package it.unisa.model.modalita;

public class ModalitaBean {

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
