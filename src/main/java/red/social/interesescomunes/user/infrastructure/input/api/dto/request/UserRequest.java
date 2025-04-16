package red.social.interesescomunes.user.infrastructure.input.api.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.role.infrastructure.input.api.dto.request.RoleRequest;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El usuario es obligatorio")
    private String userName;

    @NotBlank(message = "La direccion es obligatorio")
    private String address;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Debe ser una dirección de correo electrónico válida")
    private String email;

    @NotBlank(message = "La clave es obligatorio")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    private List<RoleRequest> roles;
}
