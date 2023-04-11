package br.com.unifacisa.ouvidoria.enums;

public enum FeedbackType {
	
	CLAIM("Reclamação"), COMPLIMENT("Elogio"), IDEA("Sugestão");
	
	public String type;
	
	FeedbackType(String type){
		this.type = type;
	}
	
}
