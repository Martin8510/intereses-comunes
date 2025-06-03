package red.social.interesescomunes.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.social.interesescomunes.role.application.input.IRoleServicePort;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.user.application.input.IUserServicePort;
import red.social.interesescomunes.user.application.output.IUserPersistencePort;
import red.social.interesescomunes.user.domain.exception.EmailAlreadyExistsException;
import red.social.interesescomunes.user.domain.exception.UserNameAlreadyExistsException;
import red.social.interesescomunes.user.domain.exception.UserNotFoundException;
import red.social.interesescomunes.user.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserServicePort {
    private final IUserPersistencePort repository;
    private final IRoleServicePort roleService;

    public UserServiceImpl(IUserPersistencePort repository, IRoleServicePort roleService) {
        this.repository = repository;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAllUsers() {
        return this.repository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontro un usuario con el id: "+ id));
    }

    @Override
    public User findUserByUserName(String userName) {
        return this.repository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException("No se encontro el usuario: "+ userName));
    }

    @Override
    public User createUser(User user, List<String> roleName) {
        // Validar que el email no exista
        if(this.repository.existsByUserName(user.getUserName())){
            throw new UserNameAlreadyExistsException("Este usuario ya existe :" + user.getUserName());
        }

        // Validar que el email no exista
        if (this.repository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Este correo ya existe :" + user.getEmail());
        }

        List<Role>  roles = roleName.stream()
            .map( name ->   this.roleService.findRoleByType(name)  )
            .toList();

        user.setRoles(roles);
        user.setCreatedAt(LocalDateTime.now());
        user.setAccountNoLocked(true);
        user.setAccountNoExpired(true);
        user.setCredentialNoExpired(true);
        return user;
    }

   @Override
    public User updateUser(Long id, User user) {
       User existingUser  = this.repository.findById(id)
               .orElseThrow(() -> new UserNotFoundException("No se encontro un usuario con el id: "+ id));
       // actualizamos los datos
       existingUser.setFirstName(user.getFirstName());
       existingUser.setLastName(user.getLastName());
       existingUser.setUserName(user.getUserName());
       existingUser.setAddress(user.getAddress());
       existingUser.setEmail(user.getEmail());
       existingUser.setPassword(user.getPassword());
       existingUser.setRoles(user.getRoles());
       existingUser.setUpdatedAt(user.getUpdatedAt());
       existingUser.setAccountNoExpired(user.isAccountNoExpired());
       existingUser.setAccountNoLocked(user.isCredentialNoExpired());
       existingUser.setCredentialNoExpired(user.isCredentialNoExpired());
       return existingUser;
    }

    @Override
    public void updateInternalUser(User existingUser, User user) {
        // actualizamos los datos
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUserName(user.getUserName());
        existingUser.setAddress(user.getAddress());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRoles(user.getRoles());
        existingUser.setUpdatedAt(user.getUpdatedAt());
        existingUser.setAccountNoExpired(user.isAccountNoExpired());
        existingUser.setAccountNoLocked(user.isAccountNoLocked());
        existingUser.setCredentialNoExpired(user.isCredentialNoExpired());
    }
}
