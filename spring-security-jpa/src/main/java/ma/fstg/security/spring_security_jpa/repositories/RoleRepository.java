package ma.fstg.security.spring_security_jpa.repositories;

import ma.fstg.security.spring_security_jpa.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
    //Cette méthode permet de trouver un rôle par son nom (name). Elle retourne un objet Role, ce qui signifie que le résultat sera le rôle correspondant au nom spécifié. Si aucun rôle avec ce nom n'est trouvé, la méthode retournera null.
}
