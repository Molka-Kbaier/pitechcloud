package CareerBoost.controller;

import CareerBoost.entity.Certificat;
import CareerBoost.entity.Formation;
import CareerBoost.entity.ModuleFormation;
import CareerBoost.serviceinterface.ICertificatService;
import CareerBoost.serviceinterface.IFormationService;
import CareerBoost.serviceinterface.IModuleFormationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "gestion Formation")
//@Controller
@RestController
@RequestMapping("/certificat")
@AllArgsConstructor
public class CertificatController {
    ICertificatService icertificat;
    IModuleFormationService imoduleFormationService;
    IFormationService iformationService;
    //postman
  @GetMapping("/retrieveallCertificat")
    public List<Certificat> retrieveAllCertificat() {
        return icertificat.retrieveAllCertificat();
    }
    //postman
 /*@GetMapping("/retrieveallCertificat")
 public ResponseEntity<List<Certificat>> retrieveAllCertificat() {
     List<Certificat> certificats = icertificat.retrieveAllCertificat();
     if (certificats.isEmpty()) {
         return ResponseEntity.noContent().build(); // Pas de contenu à renvoyer
     } else {
         return ResponseEntity.ok(certificats); // Renvoyer les certificats avec un statut 200 (OK)
     }
 }*/
    //index.html
  /* @GetMapping("/retrieveallCertificat")
   public String retrieveAllCertificat(Model model) {
       List<Certificat> certificat = icertificat.retrieveAllCertificat(); // Correction ici.addAttribute("certificat", certificat);
       model.addAttribute("certificat", certificat);

       return "certificat/index"; // Nom de la page HTML à retourner

   }*/
   /* @GetMapping("/addCertificat")
    public String getaddCertificat(Model model) {
        Certificat certificats = new Certificat();
        model.addAttribute("certificat", certificats);
        return "certificat/addCertificat";}*/
    //get add.html
   @GetMapping("/addCertificat")
   public String showAddCertificatForm(Model model) {
       Certificat certificat = new Certificat();
       List<ModuleFormation> allModuleFormations = imoduleFormationService.retrieveAllModuleFormations();
       model.addAttribute("certificat", certificat);
       model.addAttribute("allModules", allModuleFormations);
       return "certificat/addCertificat"; // Retourner le nom du fichier HTML
   }
   //post add.html
    /*@PostMapping("/addCertificat")
    public String addCertificat(@Valid @ModelAttribute("certificat") Certificat certificat, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si des erreurs de validation sont détectées, renvoyer à la vue avec les erreurs
            return "certificat/addCertificat";
        }
        Certificat addedCertificat = icertificat.addCertificat(certificat);
        if (addedCertificat != null) {
            return "redirect:/certificat/retrieveallCertificat";
        } else {
            // Gérer l'échec de l'ajout de la formation ici, peut-être en affichant un message d'erreur à l'utilisateur
            return "redirect:/certificat/addCertificat";
        }
    }*/
//postman
    /* @PostMapping("/addCertificat")

    public Certificat addCertificat(@Valid @RequestBody Certificat certificat) {
        return icertificat.addCertificat(certificat);
    }*/
    //postman
   @PostMapping("/addCertificat")
   @ResponseBody
   public Map<String, Object> addCertificat(@Valid @RequestBody Certificat certificat, BindingResult result) {
       Map<String, Object> response = new HashMap<>();

       if (result.hasErrors()) {
           // Construction de la liste des erreurs de validation
           Map<String, String> errors = new HashMap<>();
           for (FieldError error : result.getFieldErrors()) {
               errors.put(error.getField(), error.getDefaultMessage());
           }

           // Ajout des erreurs à la réponse
           response.put("success", false);
           response.put("message", "Validation failed");
           response.put("errors", errors);
       } else {
           // Si la validation a réussi, ajoutez le certificat normalement
           Certificat addedCertificat = icertificat.addCertificat(certificat);
           response.put("success", true);
           response.put("message", "Certificat ajouté avec succès");
           response.put("certificat", addedCertificat);
       }

       return response;
   }



    @GetMapping("/updateCertificat/{id}")
    public String getUpdateCertificat(@PathVariable Long id, Model model) {
        Certificat certificat = icertificat.retrieveCertificat(id);
        if (certificat == null) {

            return "redirect:/certificat/retrieveallCertificat";
        }
        model.addAttribute("certificat", certificat);
        return "certificat/updateCertificat";
    }
    @PostMapping("/updateCertificat/{id}")
    public String postUpdateCertificat(@PathVariable Long id, @Valid @ModelAttribute("certificat") Certificat certificat, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si des erreurs de validation sont détectées, renvoyer à la vue avec les erreurs
            return "certificat/updateCertificat";
        }


        Certificat updatedCertificat = icertificat.updateCertificat(certificat);
        if (updatedCertificat!= null) {
            return "redirect:/certificat/retrieveallCertificat";
        } else {
            // Gérer l'échec de la mise à jour de la formation ici, peut-être en affichant un message d'erreur à l'utilisateur
            return "redirect:/certificat/retrieveCertificat/" + id;
        }
    }
    //postman
    /*@PutMapping("/updateCertificat")
    public Certificat updateCertificat(@Valid @RequestBody Certificat certificat) {
        return this.icertificat.updateCertificat(certificat);
    }*/
    //postman
    @PutMapping("/updateCertificat")
    @ResponseBody
    public Map<String, Object> updateCertificat(@Valid @RequestBody Certificat certificat, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            // Construction de la liste des erreurs de validation
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            // Ajout des erreurs à la réponse
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", errors);
        } else {
            // Si la validation a réussi, mettez à jour le certificat normalement
            Certificat updatedCertificat = icertificat.updateCertificat(certificat);
            response.put("success", true);
            response.put("message", "Certificat mis à jour avec succès");
            response.put("certificat", updatedCertificat);
        }

        return response;
    }


    //postman
    @GetMapping("/retrieveCertificat/{id}")
   //@PostMapping("/retrieveCertificat/{id}")
    public Certificat retrieveCertificat(@PathVariable Long id) {
        return icertificat.retrieveCertificat(id);
    }


//postman
    @DeleteMapping("/deleteCertificat/{id}")
    public ResponseEntity<String> removeCertificat(@PathVariable("id") Long id) {
        try {
        icertificat.removeCertificat(id);
            return ResponseEntity.ok("Formation supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de la formation");
        }
    }


  @GetMapping("/deleteCertificat/{id}")
  public String getDeleteCertificat(@PathVariable Long id, Model model) {
      Certificat certificat = icertificat.retrieveCertificat(id);
      if (certificat == null) {
          // Gérer le cas où la formation n'est pas trouvée
          return "redirect:/certificat/retrieveallCertificat";
      }
      model.addAttribute("certificat", certificat);
      return "certificat/deleteCertificat";
  }
    @PostMapping("/deleteCertificat/{id}")
    public String deleteCertificat(@RequestParam("id") Long id) {
        icertificat.removeCertificat(id);
        return "certificat/Certificat deleted successfully";
    }
    @GetMapping("/getImageForStatus/{id}")
    public ResponseEntity<?> getImageForStatus(@PathVariable Long id) {
        try {
            Certificat certificat = this.icertificat.retrieveCertificat(id);
            if (certificat != null) {
                String status = certificat.getStatut();
                String imagePath = "";
                switch (status) {
                    case "valide":
                        imagePath = "/images/tick.png"; // Chemin de l'image pour le statut "valide"
                        break;
                    case "invalide":
                        imagePath = "/images/cross.png"; // Chemin de l'image pour le statut "invalide"
                        break;
                    // Ajoutez d'autres cas selon vos besoins
                    default:
                        imagePath = "/images/default.png"; // Chemin de l'image par défaut
                        break;
                }
                return ResponseEntity.ok(imagePath);
            } else {
                // Retourner une image par défaut si le certificat n'est pas trouvé
                return ResponseEntity.ok("/images/default.png");
            }
        } catch (Exception ex) {
            String errorMessage = "Erreur: certificat non trouvé. Cause: " + ex.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

}
