package ru.softlogic.carservice.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		
		 return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName())
				/* .map(SecurityContext::getAuthentication)
				 .filter(Authentication::isAuthenticated)
				 .map(Authentication::getName)
				 .map(String.class::cast);*/
		 
		
			/*
			 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			 * auth.getName();
			 */
	}
 
    

}
