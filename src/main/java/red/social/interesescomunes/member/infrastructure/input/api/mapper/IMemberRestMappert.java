package red.social.interesescomunes.member.infrastructure.input.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.member.domain.model.Member;
import red.social.interesescomunes.member.infrastructure.input.api.dto.request.MemberRequest;
import red.social.interesescomunes.member.infrastructure.input.api.dto.response.MemberResponse;
import red.social.interesescomunes.role.infrastructure.input.api.mapper.IRoleRestMapper;
import red.social.interesescomunes.user.infrastructure.input.api.mapper.IUserRestMappert;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IUserRestMappert.class, IRoleRestMapper.class})
public interface IMemberRestMappert {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(source = "user", target = "user")
    Member toDomain(MemberRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "user.roles", target = "user.roles")
    MemberResponse toUserResponse(Member member);

    List<MemberResponse> toUserResponseList(List<Member> members);
}
