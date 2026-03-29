package ma.fstg.security.spring_security_jpa.services;


import ma.fstg.security.spring_security_jpa.entities.User;
import ma.fstg.security.spring_security_jpa.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService { //Cette classe implémente l'interface UserDetailsService de Spring Security, ce qui signifie qu'elle doit fournir une implémentation de la méthode loadUserByUsername. Cette méthode est utilisée par Spring Security pour charger les détails d'un utilisateur à partir de la base de données lors du processus d'authentification.


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Cette méthode est appelée par Spring Security pour charger les détails d'un utilisateur à partir de la base de données en fonction du nom d'utilisateur (username) fourni lors de l'authentification.
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User( //Cette ligne crée une nouvelle instance de la classe User de Spring Security, qui implémente l'interface UserDetails. Cette classe est utilisée pour représenter les détails d'un utilisateur dans le contexte de Spring Security.
                user.getUsername(),
                user.getPassword(),
                user.isActive(),
                true, true, true,// Ces valeurs indiquent que le compte de l'utilisateur est actif, non expiré, non verrouillé et que les informations d'identification sont valides.
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }
}
