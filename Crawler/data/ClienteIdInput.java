2
https://raw.githubusercontent.com/jeanpsilva/osworks/master/src/main/java/com/algaworks/osworks/api/model/ClienteIdInput.java
package com.algaworks.osworks.api.model;

import javax.validation.constraints.NotNull;

public class ClienteIdInput {

	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
