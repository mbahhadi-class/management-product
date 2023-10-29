package is.product.sampleproduct.controller;

import is.product.sampleproduct.dto.AuthReq;
import is.product.sampleproduct.dto.AuthenticationResponse;
import is.product.sampleproduct.dto.RegisterReq;
import is.product.sampleproduct.service.AuthenticationService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author "Noverry Ambo"
 * @start 10/25/2023
 */

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterReq request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    @PermitAll
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthReq request){
        return ResponseEntity.ok(service.authenticate(request));
    }


}
