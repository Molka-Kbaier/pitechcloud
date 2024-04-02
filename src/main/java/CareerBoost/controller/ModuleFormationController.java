package CareerBoost.controller;

import CareerBoost.entity.Certificat;
import CareerBoost.entity.Formation;
import CareerBoost.entity.ModuleFormation;
import CareerBoost.serviceinterface.ICertificatService;
import CareerBoost.serviceinterface.IFormationService;
import CareerBoost.serviceinterface.IModuleFormationService;
import io.swagger.v3.oas.annotations.Operation;
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

@Tag(name = "gestion ModuleFormation")
//@Controller
@RestController
@RequestMapping("/moduleformation")
@AllArgsConstructor
public class ModuleFormationController {
    IModuleFormationService imoduleFormationService;
    ICertificatService icertificat;
    IFormationService iformationService;
    //postman
    @GetMapping("/retrieveallModuleFormation")
    public List<ModuleFormation> retrieveAllModuleFormations() {
        return imoduleFormationService.retrieveAllModuleFormations();
    }


/*
    @GetMapping("/retrieveallModuleFormation")
    public String retrieveAllModuleFormations(Model model) {
        List<ModuleFormation> moduleFormations = imoduleFormationService.retrieveAllModuleFormations();
        model.addAttribute("moduleFormations", moduleFormations);
        return "moduleformation/index"; // Nom de la page HTML à retourner
    }*/

   /* @GetMapping("/addModuleFormation")
    public String showAddModuleFormationForm(Model model) {
        ModuleFormation moduleFormation = new ModuleFormation();
        List<Formation> allFormations = iformationService.retrieveAllFormations();
        List<Certificat> allCertificats = icertificat.retrieveAllCertificat();

        model.addAttribute("moduleformation", moduleFormation);
        model.addAttribute("allFormations", allFormations);
        model.addAttribute("allCertificats", allCertificats);

        return "moduleformation/addModuleFormation"; // Nom du fichier HTML
    }

*/
   @GetMapping("/addModuleFormation")
   public String showAddModuleFormationForm(Model model) {
       ModuleFormation moduleFormation = new ModuleFormation();
       List<Formation> allFormations = iformationService.retrieveAllFormations();
       List<Certificat> allCertificats = icertificat.retrieveAllCertificat();

       model.addAttribute("moduleformation", moduleFormation); // Ajout de l'objet ModuleFormation au modèle
       model.addAttribute("allFormations", allFormations);
       model.addAttribute("allCertificats", allCertificats);

       return "moduleformation/addModuleFormation"; // Retourner le nom du fichier HTML
   }

/*
    @PostMapping("/addModuleFormation")
    public String addModuleFormation(@Valid @ModelAttribute("moduleFormation") ModuleFormation moduleFormation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si des erreurs de validation sont détectées, renvoyer à la vue avec les erreurs
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("allFormations", iformationService.retrieveAllFormations());
            model.addAttribute("allCertificats", icertificat.retrieveAllCertificat());
            return "moduleformation/addModuleFormation";
        }

        // Si aucune erreur de validation n'est détectée, ajouter la formation
        ModuleFormation addedModuleFormation = imoduleFormationService.addMFormation(moduleFormation);
        if (addedModuleFormation != null) {
            return "redirect:/moduleformation/retrieveallModuleFormation";
        } else {
            // Gérer l'échec de l'ajout de la formation ici, peut-être en affichant un message d'erreur à l'utilisateur
            return "redirect:/moduleformation/addModuleFormation";
        }
    }*/


    //postman
  /*  @PostMapping("/addModuleFormation")
    public ModuleFormation addMFormation(@Valid @RequestBody ModuleFormation mformation) {
        return imoduleFormationService.addMFormation(mformation);
    }*/

    @PostMapping("/addModuleFormation")
    @ResponseBody
    public Map<String, Object> addMFormation(@Valid @RequestBody ModuleFormation mformation, BindingResult result) {
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
            // Si la validation a réussi, ajoutez le module de formation normalement
            ModuleFormation addedModuleFormation = imoduleFormationService.addMFormation(mformation);
            response.put("success", true);
            response.put("message", "Module de formation ajouté avec succès");
            response.put("moduleFormation", addedModuleFormation);
        }

        return response;
    }


    @GetMapping("/updateModuleFormation/{id}")
    public String getUpdateModuleFormation(@PathVariable Long id, Model model) {
        ModuleFormation moduleformation = imoduleFormationService.retrieveMFormation(id);
        if (moduleformation == null) {
            // Gérer le cas où la formation n'est pas trouvée
            return "redirect:/moduleformation/retrieveallModuleFormation";
        }
        model.addAttribute("moduleformation", moduleformation);
        return "moduleformation/updateModuleFormation";
    }

    @PostMapping("/updateModuleFormation/{id}")
    public String postUpdateModuleFormation(@PathVariable Long id, @Valid @ModelAttribute("moduleFormation") ModuleFormation moduleFormation, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "moduleformation/updateModuleFormation";
        }

        // Mettez à jour la formation
        ModuleFormation updatedModuleFormation = imoduleFormationService.updateMFormation(moduleFormation);
        if (updatedModuleFormation != null) {
            return "redirect:/moduleformation/retrieveallModuleFormation";
        } else {
            // Gérer l'échec de la mise à jour de la formation ici, peut-être en affichant un message d'erreur à l'utilisateur
            return "redirect:/moduleformation/retrieveModuleFormation/" + id;
        }
    }

//postman
  /*  @PutMapping("/updateModuleFormation")
    public ModuleFormation updateMFormation(@Valid @RequestBody ModuleFormation mformation) {
        return imoduleFormationService.updateMFormation(mformation);
    }*/
//postman
@PutMapping("/updateModuleFormation")
@ResponseBody
public Map<String, Object> updateMFormation(@Valid @RequestBody ModuleFormation mformation, BindingResult result) {
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
        // Si la validation a réussi, mettez à jour le module de formation normalement
        ModuleFormation updatedModuleFormation = imoduleFormationService.updateMFormation(mformation);
        response.put("success", true);
        response.put("message", "Module de formation mis à jour avec succès");
        response.put("moduleFormation", updatedModuleFormation);
    }

    return response;
}


    @GetMapping("/retrieveModuleFormation/{id}")
    public ModuleFormation retrieveMFormation(Long id) {
        return imoduleFormationService.retrieveMFormation(id);
    }
    /*@GetMapping("/deleteModuleFormation/{id}")
    public String getDeleteModuleFormation(@PathVariable Long id, Model model) {
        ModuleFormation moduleFormation = imoduleFormationService.retrieveMFormation(id);
        if (moduleFormation == null) {
            // Gérer le cas où la formation n'est pas trouvée
            return "redirect:/moduleFormation/retrieveallModuleFormation";
        }
        model.addAttribute("moduleFormation", moduleFormation); // Ajout de l'objet moduleFormation au modèle
        return "moduleformation/deleteModuleFormation";
    }*/
    @GetMapping("/deleteModuleFormation/{id}")
    public String getDeleteModuleFormation(@PathVariable Long id, Model model) {
        ModuleFormation moduleFormation = imoduleFormationService.retrieveMFormation(id);
        if (moduleFormation == null) {
            // Gérer le cas où la formation n'est pas trouvée
            return "redirect:/moduleFormation/retrieveallModuleFormation";
        }
        model.addAttribute("moduleFormation", moduleFormation); // Ajout de l'objet moduleFormation au modèle
        return "moduleformation/deleteModuleFormation";
    }

    @PostMapping("/deleteModuleFormation/{id}")
    public String deleteModuleFormation(@PathVariable Long id) {
        // Supprimer les certificats associés à ce moduleFormation
        icertificat.removeCertificat(id);

        // Ensuite, supprimer le moduleFormation
        imoduleFormationService.removeModuleFormation(id);
        // Retourner le nom du modèle de succès de suppression
        return "moduleformation/moduleformation deleted successfully";
    }
    @DeleteMapping("/deleteModuleFormation/{id}")
    public ResponseEntity<String> removeModuleFormation(@PathVariable("id") Long id) {
        try {
        imoduleFormationService.removeModuleFormation(id);
            return ResponseEntity.ok("Formation supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de la formation");
        }
}
}
