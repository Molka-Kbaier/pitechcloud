package CareerBoost.serviceimpl;
import CareerBoost.entity.Formation;
import CareerBoost.repository.FormationRepository;
import CareerBoost.serviceinterface.IFormationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@AllArgsConstructor
public class FormationServiceImpl implements IFormationService {
    FormationRepository formationRepository;

    @Override
    public List<Formation> retrieveAllFormations(Sort id) {
        return formationRepository.findAll();
    }
    @Override
    public List<Formation> retrieveAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public Formation addFormation(@NotNull @Valid Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Formation updateFormation(@NotNull @Valid Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Formation retrieveFormation(Long id) {
        return formationRepository.findById(id).orElse(null);
    }

    @Override
    public void removeFormation(Long id) {
        formationRepository.deleteById(id);

    }
}
