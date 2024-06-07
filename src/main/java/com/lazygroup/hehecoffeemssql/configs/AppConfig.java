package com.lazygroup.hehecoffeemssql.configs;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
public class AppConfig {

	@Bean
	Supplier<ServletUriComponentsBuilder> currentUrlBuilder() {
		return new Supplier<ServletUriComponentsBuilder>() {

			@Override
			public ServletUriComponentsBuilder get() {
				return ServletUriComponentsBuilder.fromCurrentRequest();
			}

		};
	}

	@Bean
	Supplier<String> currentUrlRemoveParam() {
		return new Supplier<String>() {

			@Override
			public String get() {
				return ServletUriComponentsBuilder.fromCurrentRequest().replaceQuery(null).build()
						.toString();
			}
		};
	}
}
