package CareerBoost.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "certificat")
public class Certificat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotNull(message = "L'utilisateur associé au certificat ne peut pas être nul")
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur; // Référence à l'utilisateur ayant suivi le module
   // @NotNull(message = "Le module de formation associé au certificat ne peut pas être nul")
    @ManyToOne
    //@JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "module_formation_id")
    private ModuleFormation moduleFormation; // Référence au module de formation associé
    @NotNull(message = "La date de délivrance ne peut pas être nulle")
    @PastOrPresent(message = "La date de délivrance doit être dans le passé ou la date actuelle")
    @Temporal(TemporalType.DATE)
    private LocalDate dateDelivrance;
    @NotBlank(message = "La description du certificat ne peut pas être vide")
    @Size(max = 100, message = "La description du certificat ne peut pas dépasser {max} caractères")
    @Pattern(regexp = "[a-zA-Z ]*", message = "La description ne peut contenir que des lettres")
    private String description;

    private String statut;//actif, expiré, en attente de validation

}
