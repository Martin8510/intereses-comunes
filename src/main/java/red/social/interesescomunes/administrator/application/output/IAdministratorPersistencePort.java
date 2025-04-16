package red.social.interesescomunes.administrator.application.output;

import red.social.interesescomunes.administrator.domain.model.Administrator;

import java.util.List;
import java.util.Optional;

public interface IAdministratorPersistencePort {
    List<Administrator> findAll();
    Optional<Administrator> findById(Long id);
    Administrator save(Administrator administrator);
    void delete(Long id);
}
