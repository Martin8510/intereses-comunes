package red.social.interesescomunes.administrator.infrastructure.input.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.administrator.domain.model.Administrator;
import red.social.interesescomunes.administrator.infrastructure.input.api.dto.request.AdministratorRequest;
import red.social.interesescomunes.administrator.infrastructure.input.api.dto.response.AdministratorResponse;
import red.social.interesescomunes.role.infrastructure.input.api.mapper.IRoleRestMapper;
import red.social.interesescomunes.user.infrastructure.input.api.mapper.IUserRestMappert;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IUserRestMappert.class, IRoleRestMapper.class})
public interface IAdministratorRestMappert {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(source = "user", target = "user")
    Administrator toDomain(AdministratorRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "user.roles", target = "user.roles")
    AdministratorResponse toUserResponse(Administrator administrator);

    List<AdministratorResponse> toUserResponseList(List<Administrator> administrators);
}
