package ru.softlogic.carservice.config;

import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import ru.softlogic.carservice.audit.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing
public class CarServiceConfiguration {

	@Value('${spring.security.oauth2.resourceserver.jwt.key-value}')
	RSAPublicKey key;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests{authorize -> authorize
						.mvcMatchers("/api/v1/service/**").hasAuthority("SCOPE_service")
						.anyRequest().authenticated()
		}
				.oauth2ResourceServer{oauth2 -> oauth2
						.jwt{jwt -> jwt.decoder(jwtDecoder())}
				};
		return http.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(this.key).build();
	}
	
	@Bean
	public AuditorAware<String> auditorProvider() {
	    return new AuditorAwareImpl();
	  }
	

}
