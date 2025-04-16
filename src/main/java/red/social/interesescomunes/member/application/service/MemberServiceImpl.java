package red.social.interesescomunes.member.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.social.interesescomunes.administrator.domain.enums.AdministratorStatus;
import red.social.interesescomunes.member.application.input.IMemberServicePort;
import red.social.interesescomunes.member.application.output.IMemberPersistencePort;
import red.social.interesescomunes.member.domain.enums.MemberStatus;
import red.social.interesescomunes.member.domain.event.IMemberDomainEventPublisher;
import red.social.interesescomunes.member.domain.model.Member;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.security.model.Permission;
import red.social.interesescomunes.user.application.input.IUserServicePort;
import red.social.interesescomunes.user.domain.exception.UserNotFoundException;
import red.social.interesescomunes.user.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberServiceImpl implements IMemberServicePort {
    private final IMemberPersistencePort repository;
    private final IMemberDomainEventPublisher eventPublisher;
    private final IUserServicePort userService;

    public MemberServiceImpl(IMemberPersistencePort repository,
                             IMemberDomainEventPublisher eventPublisher,
                             IUserServicePort userService) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.userService = userService;
    }

    @Override
    public List<Member> findAllMembers() {
        return this.repository.findAll();
    }

    @Override
    public Member findMemberById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontro un usuario con el id: " + id));
    }

    @Override
    @Transactional
    public Member createMember(Member member) {
        List<String> rolesName = List.of(TypeRole.MIEMBRO.name());
        User userSave = this.userService.createUser(member.getUser(), rolesName);
        member.setUser(userSave);
        // Guardamos y publicamos el evento
        member.setStatus(MemberStatus.ACTIVO);
        Member memberCreated = this.repository.save(member);
        memberCreated.create(this.eventPublisher);
        return memberCreated;
    }

    @Override
    @Transactional
    public Member updateMember(Long id, Member member) {
        // 1. Obtener el miembro existente
        Member existingMember = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontró un miembro con el id: " + id));

        // 2. Actualizar solo los campos necesarios del usuario
        User existingUser = existingMember.getUser();
        User updateData = member.getUser();
        this.userService.updateInternalUser(existingUser, updateData );
        // 3. Guardamos y publicamos el evento
        Member memberUpdated = this.repository.save(existingMember);
        memberUpdated.update(this.eventPublisher);
        return memberUpdated;
    }

    @Override
    public void deleteMemberById(Long id) {
        Member existingMember = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontro un usuario con el id: " + id));

        // Eliminamos y publicamos el evento
        this.repository.delete(existingMember.getId());
        existingMember.delete(this.eventPublisher);
    }
}
