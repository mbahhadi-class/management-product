package is.product.sampleproduct.service;

import is.product.sampleproduct.config.JwtService;
import is.product.sampleproduct.dto.AuthReq;
import is.product.sampleproduct.dto.AuthenticationResponse;
import is.product.sampleproduct.dto.RegisterReq;
import is.product.sampleproduct.model.Role;
import is.product.sampleproduct.model.UserAccount;
import is.product.sampleproduct.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author "Noverry Ambo"
 * @start 10/25/2023
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserAccountRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterReq req){

        var checkUser = repository.findByEmail(req.getEmail());
        if (checkUser.isPresent()){
//            throw new UsernameNotFoundException("username already taken");
            return AuthenticationResponse.builder()
                    .message("username already taken")
                    .token(null)
                    .build();
        }

        var user = UserAccount.builder()
                .firstname(req.getFirstname())
                .lastname(req.getLastname())
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("success")
                .build();
    }

    public AuthenticationResponse authenticate(AuthReq req){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getEmail(),
                            req.getPassword()
                    )
            );
        }catch (Exception e){
            log.error(e.getMessage());
        }
        var user = repository.findByEmail(req.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("success")
                .build();
    }
}
