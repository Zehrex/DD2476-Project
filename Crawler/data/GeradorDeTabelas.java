1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/util/GeradorDeTabelas.java
package br.senac.sc.meuspedidos.util;

import javax.persistence.Persistence;

public class GeradorDeTabelas {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("MeusPedidosPU");
	}
	
}
