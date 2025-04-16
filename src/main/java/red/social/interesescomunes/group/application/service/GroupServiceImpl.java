package red.social.interesescomunes.group.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.social.interesescomunes.category.application.input.ICategoryServicePort;
import red.social.interesescomunes.category.domain.model.Category;
import red.social.interesescomunes.group.application.input.IGroupServicePort;
import red.social.interesescomunes.group.application.output.IGroupPersistencePort;
import red.social.interesescomunes.group.domain.enums.GroupStatus;
import red.social.interesescomunes.group.domain.event.IGroupDomainEventPublisher;
import red.social.interesescomunes.group.domain.exception.GroupNotFoundException;
import red.social.interesescomunes.group.domain.model.Group;
import red.social.interesescomunes.member.application.input.IMemberServicePort;
import red.social.interesescomunes.member.domain.model.Member;
import red.social.interesescomunes.owner.application.input.IOwnerServicePort;
import red.social.interesescomunes.owner.domain.enums.OwnerStatus;
import red.social.interesescomunes.owner.domain.model.Owner;
import red.social.interesescomunes.role.application.input.IRoleServicePort;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.user.domain.exception.UserNotFoundException;
import red.social.interesescomunes.user.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupServiceImpl implements IGroupServicePort {
    private final IGroupPersistencePort groupRepository;
    private final IMemberServicePort memberService;
    private final IRoleServicePort roleService;
    private final IOwnerServicePort ownerService;
    private final IGroupDomainEventPublisher eventPublisher;
    private final ICategoryServicePort categoryService;

    public GroupServiceImpl(IGroupPersistencePort groupRepository, IMemberServicePort memberService, IRoleServicePort roleService, IOwnerServicePort ownerService, IGroupDomainEventPublisher eventPublisher, ICategoryServicePort categoryService) {
        this.groupRepository = groupRepository;
        this.memberService = memberService;
        this.roleService = roleService;
        this.ownerService = ownerService;
        this.eventPublisher = eventPublisher;
        this.categoryService = categoryService;
    }

    @Override
    public List<Group> findAllGroups() {
        return this.groupRepository.findAll();
    }

    @Override
    public Group findGroupById(Long id) {
        return this.groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("No se encontró un grupo con el id " + id));
    }

    @Override
    @Transactional
    public Group createGroup(Long id, Group group) {
        // iniciamos la creacion del grupo tematico y asignacion del usuario propietario
        Group groupAndAssignOwner = this.prepareGroupWithOwner(group,id);
        groupAndAssignOwner.setCreatedAt(LocalDateTime.now());
        groupAndAssignOwner.setUpdatedAt(LocalDateTime.now());
        // buscamos la categoria y se la asignamos al grupo tematico
        Category category = this.categoryService.findCategoryByName(group.getCategory().getName());
        group.setCategory(category);
        // Guardamos y publicamos el evento
        Group groupCreated = this.groupRepository.save(groupAndAssignOwner);
        groupCreated.create(this.eventPublisher);
        return groupCreated;
    }

    public Group prepareGroupWithOwner(Group group, Long idMember) {
        // Obtener miembro y usuario
        Member member =  this.memberService.findMemberById(idMember);
        User user = member.getUser();
        // Actualizar roles del usuario (si no es ya propietario)
        if(!this.hasOwnerRole(user)){
            this.updateUserRoles(user);
        }
        // Creamos el propietario
        Owner owner =  this.ownerService.findOwnerByUserId(user.getId()).orElse(this.createOwner(user));
        member.setUser(user);
        this.memberService.updateMember(idMember,member);
        // Se inicializa pendiente hasta que completa la pasarela de pagos.
        group.setStatus(GroupStatus.PENDIENTE);
        group.setOwner(owner);
        return group;
    }

    private boolean hasOwnerRole(User user) {
        List<TypeRole> roleTypes = user.getRoles().stream()
            .map(Role::getName)
            .toList();
        return roleTypes.contains(TypeRole.PROPIETARIO);
    }

    private void updateUserRoles(User user) {
        Role memberRole = this.roleService.findRoleByType(TypeRole.MIEMBRO.name());
        Role ownerRole  =  this.roleService.findRoleByType(TypeRole.PROPIETARIO.name());

        List<Role> updatedRoles = List.of(memberRole, ownerRole);
        user.setRoles(updatedRoles);
        user.setUpdatedAt(LocalDateTime.now());
    }

    private Owner createOwner(User user) {
        Owner owner = Owner.builder()
                .user(user)
                .startDate(LocalDateTime.now())
                .status(OwnerStatus.ACTIVO)
                .build();
         return this.ownerService.createOwner(owner);
    }

    @Override
    @Transactional
    public Group updateGroup(Long id, Group group) {
        Group existingGroup = this.groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("No se encontró un grupo con el id " + id));
        // Actualizamos los datos del grupo
        existingGroup.setName(group.getName());
        existingGroup.setDescription(group.getDescription());
        existingGroup.setLocation(group.getLocation());
        existingGroup.setImageUrl(group.getImageUrl());
        existingGroup.setCategory(group.getCategory());
        existingGroup.setOwner(group.getOwner());
        existingGroup.setStatus(group.getStatus());
        existingGroup.setUpdatedAt(LocalDateTime.now());
        // Guardamos y publicamos el evento
        Group groupUpdated = this.groupRepository.save(existingGroup);
        groupUpdated.update(this.eventPublisher);
        return groupUpdated;
    }

    @Override
    public void deleteGroupById(Long id) {
        Group existingGroup = this.groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("No se encontró un grupo con el id " + id));
        // Eliminamos y publicamos el evento
        this.groupRepository.delete(existingGroup.getId());
        existingGroup.delete(this.eventPublisher);
    }
}