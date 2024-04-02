package CareerBoost.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "module_formation")
public class ModuleFormation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le titre ne peut pas être vide")
    @Size(max = 10, message = "Le titre ne peut pas dépasser {max} caractères")
    @Pattern(regexp = "[a-zA-Z ]*", message = "Le titre ne peut contenir que des lettres")
    private String titre;
    @Pattern(regexp = "[a-zA-Z ]*", message = "La description ne peut contenir que des lettres")
    @NotBlank(message = "La description ne peut pas être vide")
    @Size(max = 255, message = "La description ne peut pas dépasser {max} caractères")
    private String description;
  //  @JsonIgnore
  @JsonBackReference
    @ManyToOne
   // @NotNull(message = "La formation ne peut pas être vide")
    @JoinColumn(name = "formation_id")
    private Formation formation;
   // @JsonIgnore
   @JsonManagedReference
    @OneToMany(mappedBy = "moduleFormation")
    private List<Certificat> certificats;

}
