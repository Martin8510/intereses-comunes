package red.social.interesescomunes.category.infrastructure.input.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Objeto dto que representa una categoria enviados como respuesta de la API REST
 */
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
}

