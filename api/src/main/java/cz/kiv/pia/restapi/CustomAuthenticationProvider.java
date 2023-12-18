package cz.kiv.pia.restapi;

import cz.kiv.pia.domain.User;
import cz.kiv.pia.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Custom Authentication provider used for authenticating users in this application. Only called when users are login
 * in through username and password login form and not 0Auth2.0 provider
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    /**
     * Service used for retrieving users.
     */
    private final UserService userService;
    /**
     * used for decoding given passwords.
     */
    private final BCryptPasswordEncoder encoder;

    public CustomAuthenticationProvider(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    /**
     * Method authenticates user by given id.
     * @param authentication the authentication request object.
     * @return - UsernamePasswordAuthenticationToken with id, name and role of user.
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        User potential = userService.loadUserByUsername(name);
        String password = authentication.getCredentials().toString();
        if(potential != null){
            if(!encoder.matches(password, potential.getPassword())){
                throw new AuthenticationException("Chybné přihlašovací údaje") {};
            }
            else{
                String data = potential.getId() + ";" + potential.getUserName() + ";" + potential.getRole();
                return new UsernamePasswordAuthenticationToken(
                        data, null, new ArrayList<>());
            }

        }
        else{
            throw new AuthenticationException("Chybné přihlašovací údaje") {};
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
