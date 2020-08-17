package it.unisa.model.sponsor;

public class SponsorKey {

	String nome;
	int codTorneo;
	
	public SponsorKey(String nome,int codTorneo) {
		this.nome=nome;
		this.codTorneo=codTorneo;
	}

	public String getNome() {
		return nome;
	}

	public int getCodTorneo() {
		return codTorneo;
	}	

}
