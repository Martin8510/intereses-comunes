package red.social.interesescomunes.role.infrastructure.input.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.role.infrastructure.input.api.dto.request.RoleRequest;
import red.social.interesescomunes.role.infrastructure.input.api.dto.response.RoleResponse;

import java.util.List;


/**
 * Interfaz Mapper  para la conversión entre DTO de RoleReques o RoleResponse y el modelo de dominio  Role.
 */
@Mapper(componentModel = "spring")
public interface IRoleRestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name", qualifiedByName = "stringToTypeRole")
    Role toDomain(RoleRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name", qualifiedByName = "typeRoleToString")
    RoleResponse toRoleResponse(Role role);

    List<RoleResponse> toRoleResponseList(List<Role> roleList);

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
