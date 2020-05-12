2
https://raw.githubusercontent.com/WhiteFerrari666/Medication/develop/src/main/java/com/Medication/Medication/ServletInitializer.java
package com.Medication.Medication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MedicationApplication.class);
	}

}
