package CareerBoost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "utilisateur")
public class Utilisateur {
    @Column(name = "role")
    private String role; // Peut être "formateur" ou "participant"
    @Id
    private Long id;
    private String nom;

    /*@OneToMany(mappedBy = "participants")
    private List<Formation> formations; // Formations auxquelles l'utilisateur participe*/
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "utilisateur_formation",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "formation_id")
    )
    private Set<Formation> formations = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    //@NotEmpty(message = "La liste des certifs ne peut pas être vide")
    private List<Certificat> certificats;
}
