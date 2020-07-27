package tdd.ytetdd.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextFacade {

    public String getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
