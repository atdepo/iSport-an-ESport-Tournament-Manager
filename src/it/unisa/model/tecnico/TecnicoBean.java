package it.unisa.model.tecnico;

public class TecnicoBean {

	String codStaff,specializzazione;
	
	public TecnicoBean() {
		String codStaff="";
		String specializzazione="";
	}

	public String getCodStaff() {
		return codStaff;
	}

	public String getSpecializzazione() {
		return specializzazione;
	}

	public void setCodStaff(String codStaff) {
		this.codStaff = codStaff;
	}

	public void setSpecializzazione(String specializzazione) {
		this.specializzazione = specializzazione;
	}
	
	
	
}
