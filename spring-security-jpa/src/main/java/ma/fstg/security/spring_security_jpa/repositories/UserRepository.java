package ma.fstg.security.spring_security_jpa.repositories;

import ma.fstg.security.spring_security_jpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);//Cette méthode permet de trouver un utilisateur par son nom d'utilisateur (username). Elle retourne un Optional<User>, ce qui signifie que le résultat peut être un utilisateur ou être vide si aucun utilisateur avec ce nom d'utilisateur n'est trouvé.
}
