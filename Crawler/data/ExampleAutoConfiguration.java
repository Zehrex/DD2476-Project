47572
https://raw.githubusercontent.com/spring-projects/spring-boot/master/spring-boot-tests/spring-boot-integration-tests/spring-boot-server-tests/src/test/java/com/autoconfig/ExampleAutoConfiguration.java
/*
 * Copyright 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.autoconfig;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWarDeployment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnWarDeployment
@Configuration
public class ExampleAutoConfiguration {

	@Bean
	public TestEndpoint testEndpoint() {
		return new TestEndpoint();
	}

	@Endpoint(id = "war")
	static class TestEndpoint {

		@ReadOperation
		String hello() {
			return "{\"hello\":\"world\"}";
		}

	}

}
