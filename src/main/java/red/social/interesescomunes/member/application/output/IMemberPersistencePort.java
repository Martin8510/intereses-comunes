package red.social.interesescomunes.member.application.output;

import red.social.interesescomunes.member.domain.model.Member;

import java.util.List;
import java.util.Optional;

public interface IMemberPersistencePort {
    List<Member> findAll();
    Optional<Member> findById(Long id);
    Member save(Member member);
    void delete(Long id);
}
