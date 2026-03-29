package ma.fstg.security.spring_security_jpa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private boolean active;//permettra de désactiver un compte sans le supprimer

    @ManyToMany(fetch = FetchType.EAGER) //La relation @ManyToMany permet à un utilisateur d’avoir plusieurs rôles (USER, ADMIN, etc.)
    // fetch = FetchType.EAGER signifie que les rôles associés à un utilisateur seront chargés immédiatement lorsque l'utilisateur est récupéré de la base de données.
    private Collection<Role> roles = new ArrayList<>();
}
