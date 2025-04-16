package red.social.interesescomunes.role.infrastructure.input.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.role.application.input.IRoleServicePort;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.infrastructure.input.api.dto.request.RoleRequest;
import red.social.interesescomunes.role.infrastructure.input.api.dto.response.RoleResponse;
import red.social.interesescomunes.role.infrastructure.input.api.mapper.IRoleRestMapper;
import java.util.List;


/**
 * Controlador REST responsable de gestionar las solicitudes HTTP relacionadas con la entidad Rol.
 */
@RestController
@RequestMapping("/api/v1/role")
public class RoleController{
    private final IRoleServicePort service;
    private final IRoleRestMapper mapper;

    public RoleController(IRoleServicePort service, IRoleRestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<RoleResponse>> findAllRoles() {
        List<Role> roles = this.service.findAllRoles();
        List<RoleResponse> responses = this.mapper.toRoleResponseList(roles);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RoleResponse> findRoleById(@PathVariable Long id) {
        Role role = this.service.findRoleById(id);
        RoleResponse response = this.mapper.toRoleResponse(role);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/type/{typeRole}")
    public ResponseEntity<RoleResponse> findRoleByType(@PathVariable String typeRole) {
        Role role = this.service.findRoleByType(typeRole.toUpperCase());
        RoleResponse response = this.mapper.toRoleResponse(role);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<RoleResponse> createRole(@Valid @RequestBody RoleRequest roleRequest) {
        Role role = this.mapper.toDomain(roleRequest);
        Role savedRole = this.service.createRole(role);
        RoleResponse response = this.mapper.toRoleResponse(savedRole);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable Long id,@Valid @RequestBody RoleRequest roleRequest) {
        Role role = mapper.toDomain(roleRequest);
        Role savedRole = this.service.updateRole(id,role);
        RoleResponse response = mapper.toRoleResponse(savedRole);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.service.deleteRoleById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Rol eliminado.");
    }
}
