package it.unisa.model.torneo;

import java.io.Serializable;

public class TournamentBean implements Serializable{
String nome,data,indirizzoStruttura,codGioco,proprietario;
int CAPStruttura,codice,budget;
boolean isHomePage;

public TournamentBean() {
	
	nome="";
	data="";
	indirizzoStruttura="";
	codGioco="";
	proprietario="";
	budget=0;
	CAPStruttura=0;
	codice=0;
	isHomePage=false;
	
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getData() {
	return data;
}

public void setData(String data) {
	this.data = data;
}

public String getIndirizzoStruttura() {
	return indirizzoStruttura;
}

public void setIndirizzoStruttura(String indirizzoStruttura) {
	this.indirizzoStruttura = indirizzoStruttura;
}

public String getCodGioco() {
	return codGioco;
}

public void setCodGioco(String codGioco) {
	this.codGioco = codGioco;
}

public String getProprietario() {
	return proprietario;
}

public void setProprietario(String proprietario) {
	this.proprietario = proprietario;
}

public int getCAPStruttura() {
	return CAPStruttura;
}

public void setCAPStruttura(int cAPStruttura) {
	CAPStruttura = cAPStruttura;
}

public int getCodice() {
	return codice;
}

public void setCodice(int codice) {
	this.codice = codice;
}

public int getBudget() {
	return budget;
}

public void setBudget(int budget) {
	this.budget = budget;
}

public boolean isHomePage() {
	return isHomePage;
}

public void setHomePage(boolean isHomePage) {
	this.isHomePage = isHomePage;
}



}
 