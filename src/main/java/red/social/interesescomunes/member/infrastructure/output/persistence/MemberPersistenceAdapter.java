package red.social.interesescomunes.member.infrastructure.output.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.member.application.output.IMemberPersistencePort;
import red.social.interesescomunes.member.domain.model.Member;
import red.social.interesescomunes.member.infrastructure.output.persistence.mapper.IMemberPersistenceMapper;
import red.social.interesescomunes.member.infrastructure.output.persistence.mysql.entity.MemberEntity;
import red.social.interesescomunes.member.infrastructure.output.persistence.mysql.repository.IMemberJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class MemberPersistenceAdapter implements IMemberPersistencePort {
    private final IMemberJpaRepository jpaRepository;
    private final IMemberPersistenceMapper mapper;

    public MemberPersistenceAdapter(IMemberJpaRepository jpaRepository, IMemberPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Member> findAll() {
        List<MemberEntity> memberEntities = (List<MemberEntity>) this.jpaRepository.findAll();
        return this.mapper.toDomainList(memberEntities);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = this.mapper.toEntity(member);
        MemberEntity savedMember = this.jpaRepository.save(memberEntity);
        return this.mapper.toDomain(savedMember);
    }

    @Override
    public void delete(Long id) {
        this.jpaRepository.deleteById(id);
    }
}
