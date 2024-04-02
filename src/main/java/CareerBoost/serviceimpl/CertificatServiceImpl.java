package CareerBoost.serviceimpl;

import CareerBoost.entity.Certificat;
import CareerBoost.repository.CertificatRepository;
import CareerBoost.serviceinterface.ICertificatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CertificatServiceImpl implements ICertificatService {
    CertificatRepository certificatRepository;

    @Override
    public List<Certificat> retrieveAllCertificat() {
        return certificatRepository.findAll();
    }

    @Override
    public Certificat addCertificat(@NotNull @Valid Certificat certificat) {
        return certificatRepository.save(certificat);
    }
//@Valid est utilisée pour activer la validation des contraintes définies dans l'entité Certificat
    @Override
    public Certificat updateCertificat(@NotNull @Valid Certificat certificat) {
        return certificatRepository.save(certificat);
    }

    @Override
    public Certificat retrieveCertificat(Long id) {
        return certificatRepository.findById(id).orElse(null);
    }

    @Override
    public void removeCertificat(Long id) {
        certificatRepository.deleteById(id);

    }
}
