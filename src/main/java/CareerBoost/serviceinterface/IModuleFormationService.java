package CareerBoost.serviceinterface;

import CareerBoost.entity.ModuleFormation;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IModuleFormationService {
    List<ModuleFormation> retrieveAllModuleFormations();
    ModuleFormation addMFormation(ModuleFormation moduleformation );
    ModuleFormation updateMFormation (ModuleFormation mmoduleformation);
    ModuleFormation retrieveMFormation ( Long id);
    void removeModuleFormation ( Long id);
}
