package red.social.interesescomunes.group.infrastructure.input.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryIdRequest {
    @NotNull(message = "El ID de categoría no puede ser nulo.")
    private Long id;
}