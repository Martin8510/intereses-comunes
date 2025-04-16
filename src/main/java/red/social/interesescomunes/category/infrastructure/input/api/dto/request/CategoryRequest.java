package red.social.interesescomunes.category.infrastructure.input.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Objeto dto que representa la peticion para crear o actualizar una categoria
 */
public class CategoryRequest {
    @NotBlank(message = "Ingrese el nombre.")
    private String name;
    @NotBlank(message = "Ingrese la descripcion.")
    private String description;
}

