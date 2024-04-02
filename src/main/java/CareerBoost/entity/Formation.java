package CareerBoost.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "formation")
public class Formation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le titre ne peut pas être vide")
    @Size(max = 10, message = "Le titre ne peut pas dépasser {max} caractères")
    @Pattern(regexp = "[a-zA-Z ]*", message = "Le titre ne peut contenir que des lettres")
    private String titre;
    @NotBlank(message = "La description ne peut pas être vide")
    @Size(max = 255, message = "La description ne peut pas dépasser {max} caractères")
    @Pattern(regexp = "[a-zA-Z ]*", message = "La description ne peut contenir que des lettres")
    private String description;
    @Column(name = "date_debut")
    @NotNull(message = "La date de début ne peut pas être vide")
    @PastOrPresent(message = "La date de début doit être dans le passé ou le présent")
    private LocalDate dateDebut; // Date de début de la formation
    @NotNull(message = "La date de fin ne peut pas être vide")
    @FutureOrPresent(message = "La date de fin doit être dans le passé ou le présent")
    @Column(name = "date_fin")
    private LocalDate dateFin;
    // Date de fin de la formation
    //@JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "formation")
    //@NotEmpty(message = "La liste des modules ne peut pas être vide")
    private List<ModuleFormation> modules;
    @JsonIgnore
   @ManyToMany(mappedBy = "formations")
  // @NotEmpty(message = "La liste des participants ne peut pas être vide")
    private Set<Utilisateur> participants = new HashSet<>(); // Utilisateurs participant à la formation (HashSet garantit que chaque utilisateur est unique pas de repetition des utilisateurs)
    @JsonIgnore
    @ManyToMany(mappedBy = "formations")
    //@NotEmpty(message = "La liste des formateurs ne peut pas être vide")
    private Set<Utilisateur> formateurs = new HashSet<>(); // Utilisateurs formateurs de formation

}
