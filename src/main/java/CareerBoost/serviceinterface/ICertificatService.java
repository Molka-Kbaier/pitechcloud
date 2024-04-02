package CareerBoost.serviceinterface;

import CareerBoost.entity.Certificat;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICertificatService {
    List<Certificat> retrieveAllCertificat();
    Certificat addCertificat(Certificat certificat );
    Certificat updateCertificat (Certificat certificat);
    Certificat retrieveCertificat ( Long id);
    void removeCertificat ( Long id);

}
