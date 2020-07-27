package tdd.ytetdd.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tdd.ytetdd.configuration.SecurityContextFacade;

@RestController
public class Contoller {

    @Autowired
    private SecurityContextFacade securityContextFacade;

    @GetMapping("/serhat")
    public String se() {
        return "Serhat";
    }

    @GetMapping("/serhats")
    public ResponseEntity<?> ses() {
        return ResponseEntity.ok(securityContextFacade.getAuthentication());
    }
}
