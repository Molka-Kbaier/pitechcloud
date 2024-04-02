package CareerBoost.serviceinterface;

import CareerBoost.entity.Formation;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IFormationService {
    List<Formation> retrieveAllFormations(Sort id);
    List<Formation> retrieveAllFormations();
    Formation addFormation(Formation formation );
    Formation updateFormation (Formation formation);
    Formation retrieveFormation ( Long id);
    void removeFormation ( Long id);
}
