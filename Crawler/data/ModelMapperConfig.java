2
https://raw.githubusercontent.com/jeanpsilva/osworks/master/src/main/java/com/algaworks/osworks/core/ModelMapperConfig.java
package com.algaworks.osworks.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();		
	}

}
