package red.social.interesescomunes.role.infrastructure.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.role.infrastructure.output.persistence.mysql.entity.RoleEntity;
import java.util.List;


/**
 * Interfaz Mapper para la conversión entre el modelo de dominio Rol  y la entidad de persistencia RolEntity.
 */
@Mapper(componentModel = "spring")
public interface IRolePersistenceMapper {
    @Mapping(source = "name", target = "name", qualifiedByName = "stringToTypeRole")
    Role toDomain(RoleEntity roleEntity);

    @Mapping(source = "name", target = "name", qualifiedByName = "typeRoleToString")
    RoleEntity toEntity(Role roleDomain);

    List<Role> toDomainList(List<RoleEntity> rolesEntity);

    //  Metodo para convertir String a TypeRole
    @Named("stringToTypeRole")
    default TypeRole stringToTypeRole(String name) {
        return TypeRole.valueOf(name.toUpperCase()); // Asegura que coincida con el enum
    }

    //  Metodo para convertir TypeRole a String
    @Named("typeRoleToString")
    default String typeRoleToString(TypeRole typeRole) {
        return typeRole.name(); // Devuelve el nombre del enum
    }
}



