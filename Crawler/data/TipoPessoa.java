1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/model/TipoPessoa.java
package br.senac.sc.meuspedidos.model;

public enum TipoPessoa {

	FISICA ("FISICA"), JURIDICA("JURIDICA");

	private String label;

	TipoPessoa(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}