package it.unisa.model.torneo;

import java.io.Serializable;

public class TournamentBean implements Serializable{
String nome,data,indirizzoStruttura,codGioco;
int CAPStruttura;

public TournamentBean(String nome,String data,String indirizzoStruttura, String codGioco,int CAPStruttura) {
	
	 nome=this.nome;
	 data=this.data;
	 indirizzoStruttura=this.indirizzoStruttura;
	 codGioco=this.codGioco;
	 CAPStruttura=this.CAPStruttura;
	
}

public TournamentBean() {
	
	nome="";
	data="";
	indirizzoStruttura="";
	codGioco="";
	CAPStruttura=0;
	
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
 