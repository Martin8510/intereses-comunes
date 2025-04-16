package red.social.interesescomunes.category.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.category.domain.model.Category;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Objeto de Transferencia de Datos (DTO) utilizado para transportar información de la categoria dentro de los eventos del dominio.
 */
public class CategoryEventDTO {
    private Long id;
    private String name;
    private String description;

    public static CategoryEventDTO create(Category category) {
        return CategoryEventDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
