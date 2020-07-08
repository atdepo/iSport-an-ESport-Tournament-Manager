package it.unisa.model.modalita;

public class ModalitaKey {

	public String nomeGioco,tipo;
	
	public ModalitaKey(String nomeGioco,String tipo) {
		this.nomeGioco=nomeGioco;
		this.tipo=tipo;
	}

	public String getNomeGioco() {
		return nomeGioco;
	}

	public String getTipo() {
		return tipo;
	}
	
}
