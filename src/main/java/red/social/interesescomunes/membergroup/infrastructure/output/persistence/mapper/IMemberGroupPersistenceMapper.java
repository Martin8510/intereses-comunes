package red.social.interesescomunes.membergroup.infrastructure.output.persistence.mapper;

import org.mapstruct.Mapper;
import red.social.interesescomunes.membergroup.domain.model.MemberGroup;
import red.social.interesescomunes.membergroup.infrastructure.output.persistence.mysql.entity.MemberGroupEntity;

@Mapper(componentModel = "spring")
public interface IMemberGroupPersistenceMapper {
    MemberGroup toDomain(MemberGroupEntity entity);
    MemberGroupEntity toEntity(MemberGroup domain);
}
