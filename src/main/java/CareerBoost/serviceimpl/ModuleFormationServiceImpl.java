package CareerBoost.serviceimpl;

import CareerBoost.entity.ModuleFormation;
import CareerBoost.repository.ModuleFormationRepository;
import CareerBoost.serviceinterface.IModuleFormationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ModuleFormationServiceImpl implements IModuleFormationService {
    ModuleFormationRepository moduleFormationRepository;

    @Override
    public List<ModuleFormation> retrieveAllModuleFormations() {
        return moduleFormationRepository.findAll();
    }


    @Override
    public ModuleFormation addMFormation(@NotNull @Valid ModuleFormation moduleformation) {
        return moduleFormationRepository.save(moduleformation);
    }

    @Override
    public ModuleFormation updateMFormation(@NotNull @Valid ModuleFormation moduleformation) {
        return moduleFormationRepository.save(moduleformation);
    }

    @Override
    public ModuleFormation retrieveMFormation(Long id) {
        return moduleFormationRepository.findById(id).orElse(null);
    }

    @Override
    public void removeModuleFormation(Long id) {
        moduleFormationRepository.deleteById(id);

    }
}
