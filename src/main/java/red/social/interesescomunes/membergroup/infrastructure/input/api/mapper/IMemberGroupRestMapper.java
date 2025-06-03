package red.social.interesescomunes.membergroup.infrastructure.input.api.mapper;

import org.mapstruct.Mapper;
import red.social.interesescomunes.group.infrastructure.input.api.mapper.IGroupRestMapper;
import red.social.interesescomunes.member.infrastructure.input.api.mapper.IMemberRestMappert;
import red.social.interesescomunes.membergroup.domain.model.MemberGroup;
import red.social.interesescomunes.membergroup.infrastructure.input.api.dto.response.MemberGroupResponse;

@Mapper(componentModel = "spring", uses = {IMemberRestMappert.class, IGroupRestMapper.class})

public interface IMemberGroupRestMapper {
    MemberGroupResponse toResponse(MemberGroup domain);

}
