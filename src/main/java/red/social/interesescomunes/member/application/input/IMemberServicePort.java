package red.social.interesescomunes.member.application.input;

import red.social.interesescomunes.member.domain.model.Member;

import java.util.List;

public interface IMemberServicePort {
    List<Member> findAllMembers();
    Member findMemberById(Long id);
    Member createMember(Member members);
    Member updateMember(Long id, Member members);
    void deleteMemberById(Long id);
}
