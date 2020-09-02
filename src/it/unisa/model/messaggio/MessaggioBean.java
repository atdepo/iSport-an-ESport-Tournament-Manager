package it.unisa.model.messaggio;

public class MessaggioBean {
	private int codice;
	private String testo;
	
	public MessaggioBean() {
	codice=0;
	testo="";
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
	
}
