13
https://raw.githubusercontent.com/graemerocher/framework-comparison-2020/master/spring-example/src/test/java/com/example/demo/MessageControllerTest.java
package com.example.demo;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageControllerTest {

	@LocalServerPort
	int port;

	@BeforeEach
	void setup() {
		RestAssured.port = port;
	}

	@Test
	void testMessage() {
		given()
				.when().get("/hello/John")
				.then()
				.statusCode(200)
				.body(is("Hello John"));
	}

}
