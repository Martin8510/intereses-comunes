package red.social.interesescomunes.owner.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.social.interesescomunes.owner.application.input.IOwnerServicePort;
import red.social.interesescomunes.owner.application.output.IOwnerPersistencePort;
import red.social.interesescomunes.owner.domain.event.IOwnerDomainEventPublisher;
import red.social.interesescomunes.owner.domain.model.Owner;
import red.social.interesescomunes.role.application.input.IRoleServicePort;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.user.application.input.IUserServicePort;
import red.social.interesescomunes.user.domain.exception.UserNotFoundException;
import red.social.interesescomunes.user.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements IOwnerServicePort {
    private final IOwnerPersistencePort repository;
    private final IOwnerDomainEventPublisher eventPublisher;
    private final IUserServicePort userService;

    public OwnerServiceImpl(IOwnerPersistencePort repository, IOwnerDomainEventPublisher eventPublisher, IUserServicePort userService) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.userService = userService;
    }

    @Override
    public List<Owner> findAllOwners() {
        return  this.repository.findAll();
    }

    @Override
    public Owner findOwnerById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontro un usuario con el id: " + id));
    }

    @Override
    public Optional<Owner> findOwnerByUserId(Long id) {
        return this.repository.findByUserId(id);
    }

    @Override
    @Transactional
    public Owner createOwner(Owner owner) {
        Owner ownerCreated = this.repository.save(owner);
        ownerCreated.create(this.eventPublisher);
        return ownerCreated;
    }

    @Override
    @Transactional
    public Owner updateOwner(Long id, Owner owner) {
        Owner existinOwner = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontró el propietario con el id: " + id));
        // 2. Actualizar solo los campos necesarios del usuario
        User existingUser = existinOwner.getUser();
        User updateData = owner.getUser();
        this.userService.updateInternalUser(existingUser, updateData );
        //3. Guardamos y publicamos el evento
        Owner ownerUpdate = this.repository.save(existinOwner);
        ownerUpdate.update(this.eventPublisher);
        return ownerUpdate;
    }

    @Override
    public void deleteOwnerById(Long id) {
        Owner existingOwner = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontro un usuario con el id: " + id));
        // eliminamos y publica el evento
        this.repository.delete(existingOwner.getId());
        existingOwner.delete(this.eventPublisher);
    }
}
