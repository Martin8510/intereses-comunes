package red.social.interesescomunes.member.domain.event;

import red.social.interesescomunes.member.domain.model.Member;

public class MemberUpdatedEvent {
    private final MemberEventDTO member;

    public MemberUpdatedEvent(Member member){
        this.member = MemberEventDTO.create(member);
    }

    public MemberEventDTO getMember(){
        return this.member;
    }
}
