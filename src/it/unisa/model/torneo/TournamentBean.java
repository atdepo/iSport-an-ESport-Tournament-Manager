package it.unisa.model.torneo;

import java.io.Serializable;

public class TournamentBean implements Serializable{
String nome,data,indirizzoStruttura,codGioco;
int CAPStruttura,codice;

public TournamentBean(String nome,String data,String indirizzoStruttura, String codGioco,int CAPStruttura,int codice) {
	
	 this.nome=nome;
	 this.data=data;
	 this.indirizzoStruttura=indirizzoStruttura;
	 this.codGioco=codGioco;
	 this.CAPStruttura=CAPStruttura;
	 this.codice=codice;
}

public TournamentBean() {
	
	nome="";
	data="";
	indirizzoStruttura="";
	codGioco="";
	CAPStruttura=0;
	codice=0;
	
}

public int getCodice() {
	return codice;
}

public void setCodice(int cod) {
	codice=cod;
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

public void setCodGioco(String string) {
	this.codGioco = string;
}

public int getCAPStruttura() {
	return CAPStruttura;
}

public void setCAPStruttura(int CAPStruttura) {
	this.CAPStruttura = CAPStruttura;
}


}
 