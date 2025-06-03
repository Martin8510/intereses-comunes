package red.social.interesescomunes.membergroup.application.input;

import red.social.interesescomunes.membergroup.domain.model.MemberGroup;

public interface IMemberGroupServicePort {
    MemberGroup joinGroup(Long memberId, Long groupId);
    void leaveGroup(Long memberId, Long groupId); // Nuevo método
}
