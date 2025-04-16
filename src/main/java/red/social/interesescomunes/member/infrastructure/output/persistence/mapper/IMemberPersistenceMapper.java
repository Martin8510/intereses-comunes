package red.social.interesescomunes.member.infrastructure.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.member.domain.model.Member;
import red.social.interesescomunes.member.infrastructure.output.persistence.mysql.entity.MemberEntity;
import red.social.interesescomunes.role.infrastructure.output.persistence.mapper.IRolePersistenceMapper;
import red.social.interesescomunes.user.infrastructure.output.persistence.mapper.IUserPersistenceMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IUserPersistenceMapper.class, IRolePersistenceMapper.class})
public interface IMemberPersistenceMapper {
    @Mapping(source = "user" , target = "user")
    @Mapping(source = "user.roles" , target = "user.roles")
    Member toDomain(MemberEntity entity);

    @Mapping(source = "user" , target = "user")
    @Mapping(source = "user.roles" , target = "user.roles")
    MemberEntity toEntity(Member domain);

    List<Member> toDomainList(List<MemberEntity> entities);
}
