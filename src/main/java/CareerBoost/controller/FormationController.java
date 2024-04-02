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
@RestController
//@Controller
@RequestMapping("/formation")
@AllArgsConstructor
public class FormationController {
   IFormationService iformationService;
    IModuleFormationService imoduleFormationService;
    ICertificatService icertificat;

//postman
    @GetMapping("/retrieveallFormation")
    public List<Formation> retrieveAllFormations() {
        return iformationService.retrieveAllFormations();
    }
    //index.html
    /* @GetMapping("/retrieveallFormation")
        public String retrieveAllFormations(Model model) {
            List<Formation> formation = iformationService.retrieveAllFormations(Sort.by(Sort.Direction.DESC,"id"));
            model.addAttribute("formation", formation);
            return "formation/index"; // Nom de la page HTML à retourner
        }*/
    //postman
   /* @PostMapping("/addFormation")
    @ResponseBody//renvoyer des données JSON
    public Formation addFormation(@Valid @RequestBody Formation formation) {
        return iformationService.addFormation(formation);
    }*/
    //postman
    @PostMapping("/addFormation")
    @ResponseBody
    public Map<String, Object> addFormation(@Valid @RequestBody Formation formation, BindingResult result) {
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
            // Si la validation a réussi, ajoutez la formation normalement
            Formation addedFormation = iformationService.addFormation(formation);
            response.put("success", true);
            response.put("message", "Formation ajoutée avec succès");
            response.put("formation", addedFormation);
        }

        return response;
    }
   //@RequestMapping(value = "/addFormation", method = RequestMethod.GET)
   //add.html
    @GetMapping("/addFormation")
    public String getaddFormation(Model model) {
        Formation formation = new Formation(); // Créer une nouvelle instance de Formation
        model.addAttribute("formation", formation); // Ajouter la formation au modèle avec le nom "formation"
        return "formation/addFormation"; // Retourner le nom de la vue
    }
   /* @GetMapping("/addFormation")
    public String showAddFormationForm(Model model) {
        Formation formation = new Formation();
        List<ModuleFormation> allModuleFormations = imoduleFormationService.retrieveAllModuleFormations();
        List<Certificat> allCertificats = icertificat.retrieveAllCertificat();

        model.addAttribute("formation", formation);
        model.addAttribute("allModuleFormations", allModuleFormations);
        model.addAttribute("allCertificats", allCertificats);

        return "formation/addFormation"; // Retourner le nom du fichier HTML
    }*/
    //add.html
 /*  @PostMapping("/addFormation")
   public String addFormation(@Valid @ModelAttribute("formation") Formation formation, BindingResult result, Model model) {
       if (result.hasErrors()) {
           // Si des erreurs de validation sont détectées, renvoyer à la vue avec les erreurs
           return "formation/addFormation";
       }

       // Si aucune erreur de validation n'est détectée, ajouter la formation
       Formation addedFormation = iformationService.addFormation(formation);
       if (addedFormation != null) {
           return "redirect:/formation/retrieveallFormation";
       } else {
           // Gérer l'échec de l'ajout de la formation ici, peut-être en affichant un message d'erreur à l'utilisateur
           return "redirect:/formation/addFormation";
       }
   }*/



//postman
  /*  @PutMapping("/updateFormation")
    @ResponseBody
    public Formation updateFormation(@Valid @RequestBody Formation formation) {
        return iformationService.updateFormation(formation);
    }*/
//postman
@PutMapping("/updateFormation")
@ResponseBody
public Map<String, Object> updateFormation(@Valid @RequestBody Formation formation, BindingResult result) {
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
        // Si la validation a réussi, mettez à jour la formation normalement
        Formation updatedFormation = iformationService.updateFormation(formation);
        response.put("success", true);
        response.put("message", "Formation mise à jour avec succès");
        response.put("formation", updatedFormation);
    }

    return response;
}
   @GetMapping("/updateFormation/{id}")
   public String getUpdateFormation(@PathVariable Long id, Model model) {
       Formation formation = iformationService.retrieveFormation(id);
       if (formation == null) {
           // Gérer le cas où la formation n'est pas trouvée
           return "redirect:/formation/retrieveallFormation";
       }
       model.addAttribute("formation", formation);
       return "formation/updateFormation";
   }

    @PostMapping("/updateFormation/{id}")
    public String postUpdateFormation(@PathVariable Long id, @Valid @ModelAttribute("formation") Formation formation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si des erreurs de validation sont détectées, renvoyer à la vue avec les erreurs
            return "formation/updateFormation";
        }

        // Mettez à jour la formation
        Formation updatedFormation = iformationService.updateFormation(formation);
        if (updatedFormation != null) {
            return "redirect:/formation/retrieveallFormation";
        } else {
            // Gérer l'échec de la mise à jour de la formation ici, peut-être en affichant un message d'erreur à l'utilisateur
            return "redirect:/formation/retrieveFormation/" + id;
        }
    }

    //@PutMapping("/updateFormation")
   /* @PostMapping("/updateFormation")
    public String updateFormation(@Valid @ModelAttribute("formation") Formation formation, Model model) {
        Formation updatedFormation = iformationService.updateFormation(formation);
        if (updatedFormation != null) {
            return "redirect:/formation/retrieveallFormation";
        } else {
            // Gérer l'échec de la mise à jour de la formation ici, peut-être en affichant un message d'erreur à l'utilisateur
            return "redirect:/formation/retrieveFormation/" + formation.getId();
        }
    }*/
    @PostMapping("/retrieveFormation/{idformation}")
    @ResponseBody
    public Formation retrieveFormation(@PathVariable Long id) {
        return iformationService.retrieveFormation(id);
    }
  /* @GetMapping("/retrieveFormation/{idformation}")
   public String @PostMapping("/retrieveCertificat/{id}")(@PathVariable Long id, Model model) {
       Formation formation = iformationService.retrieveFormation(id);
       model.addAttribute("formation", formation);
       return "formation/detail"; // Nom de la page HTML à retourner
   }*/
  //postman
    @DeleteMapping("/deleteFormation/{id}")
    public ResponseEntity<String> removeFormation(@PathVariable("id") Long id) {
        try {
            iformationService.removeFormation(id);
            return ResponseEntity.ok("Formation supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de la formation");
        }
    }
   @GetMapping("/deleteFormation/{id}")
   public String getDeleteFormation(@PathVariable Long id, Model model) {
       Formation formation = iformationService.retrieveFormation(id);
       if (formation == null) {
           // Gérer le cas où la formation n'est pas trouvée
           return "redirect:/formation/retrieveallFormation";
       }
       model.addAttribute("formation", formation);
       return "formation/deleteFormation";
   }
    @PostMapping("/deleteFormation/{id}")
    public String deleteFormation(@RequestParam("id") Long id) {
        iformationService.removeFormation(id);
        return "formation/Formation deleted successfully";
    }

   /* @DeleteMapping("/deleteFormation/{id}")
   public String removeFormation(@PathVariable("id") Long id) {
       iformationService.removeFormation(id);
       return "redirect:/formation/retrieveallFormation";
   }*/
}
