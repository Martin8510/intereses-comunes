package red.social.interesescomunes.member.domain.event;


import red.social.interesescomunes.member.domain.model.Member;

public class MemberCreatedEvent {
    private final MemberEventDTO member;

    public MemberCreatedEvent(Member member){
        this.member = MemberEventDTO.create(member);
    }

    public MemberEventDTO getMember(){
        return this.member;
    }
}
