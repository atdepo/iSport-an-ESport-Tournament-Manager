package it.unisa.model.struttura;

public class StrutturaBean {

	String nome,indirizzo,CAP;
	int capienza;
	
	public StrutturaBean() {
		nome="";
		indirizzo="";
		CAP="";
		capienza=0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCAP() {
		return CAP;
	}

	public void setCAP(String CAP) {
		this.CAP = CAP;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}
	
	
	
	
}
