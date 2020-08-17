package it.unisa.model.sponsor;

public class SponsorBean {

	String nome;
	int codTorneo;
	
	public SponsorBean() {
		nome="";
		codTorneo=-1;
	}

	public String getNome() {
		return nome;
	}

	public int getCodTorneo() {
		return codTorneo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCodTorneo(int codTorneo) {
		this.codTorneo = codTorneo;
	}
	
	
}
