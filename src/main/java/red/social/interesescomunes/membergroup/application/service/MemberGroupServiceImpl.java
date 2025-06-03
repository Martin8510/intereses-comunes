package red.social.interesescomunes.membergroup.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.social.interesescomunes.group.application.input.IGroupServicePort;
import red.social.interesescomunes.group.domain.model.Group;
import red.social.interesescomunes.member.application.input.IMemberServicePort;
import red.social.interesescomunes.member.domain.model.Member;
import red.social.interesescomunes.membergroup.application.input.IMemberGroupServicePort;
import red.social.interesescomunes.membergroup.application.output.IMemberGroupPersistencePort;
import red.social.interesescomunes.membergroup.domain.enums.MemberGroupStatus;
import red.social.interesescomunes.membergroup.domain.exception.MemberNotInGroupException;
import red.social.interesescomunes.membergroup.domain.model.MemberGroup;

import java.time.LocalDate;

@Service
public class MemberGroupServiceImpl implements IMemberGroupServicePort {

    private final IMemberGroupPersistencePort repository;
    private final IMemberServicePort memberService;
    private final IGroupServicePort groupService;

    public MemberGroupServiceImpl(IMemberGroupPersistencePort repository,
                                  IMemberServicePort memberService,
                                  IGroupServicePort groupService) {
        this.repository = repository;
        this.memberService = memberService;
        this.groupService = groupService;
    }

    @Override
    @Transactional
    public MemberGroup joinGroup(Long memberId, Long groupId) {
        if (repository.existsByMemberAndGroup(memberId, groupId)) {
            throw new IllegalStateException("El miembro ya está unido al grupo.");
        }

        Member member = memberService.findMemberById(memberId);
        Group group = groupService.findGroupById(groupId);

        MemberGroup relation = MemberGroup.builder()
                .member(member)
                .group(group)
                .joinedAt(LocalDate.now())
                .status(MemberGroupStatus.ACTIVO)
                .build();

        return repository.save(relation);
    }

    @Override
    @Transactional
    public void leaveGroup(Long memberId, Long groupId) {
        MemberGroup relation = repository.findByMemberAndGroup(memberId, groupId)
                .orElseThrow(() -> new MemberNotInGroupException("El miembro no está unido a este grupo"));

        if (relation.getStatus() == MemberGroupStatus.INACTIVO) {
            throw new IllegalStateException("El miembro ya ha abandonado este grupo");
        }

        // Verificar si es el propietario del grupo
        if (relation.getGroup().getOwner().getId().equals(memberId)) {
            throw new IllegalStateException("El propietario no puede abandonar el grupo");
        }

        relation.setStatus(MemberGroupStatus.INACTIVO);
        relation.setLeftAt(LocalDate.now());

        repository.save(relation);
    }
}
