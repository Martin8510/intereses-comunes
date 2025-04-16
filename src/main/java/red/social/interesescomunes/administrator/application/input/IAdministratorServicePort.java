package red.social.interesescomunes.administrator.application.input;

import red.social.interesescomunes.administrator.domain.model.Administrator;

import java.util.List;

public interface IAdministratorServicePort {
    List<Administrator> findAllAdministrators();
    Administrator findAdministratorById(Long id);
    Administrator createAdministrator(Administrator administrator);
    Administrator updateAdministrator(Long id, Administrator administrator);
    void deleteAdministratorById(Long id);
}
