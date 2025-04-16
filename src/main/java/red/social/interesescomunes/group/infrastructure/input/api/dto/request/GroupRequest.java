package red.social.interesescomunes.group.infrastructure.input.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.category.infrastructure.input.api.dto.request.CategoryRequest;
import red.social.interesescomunes.group.domain.enums.GroupStatus;
import red.social.interesescomunes.owner.infrastructure.input.api.dto.request.OwnerRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres.")
    private String name;

    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres.")
    private String description;

    @NotBlank(message = "La ubicación no puede estar vacía.")
    private String location;

    @NotBlank(message = "La URL de la imagen no puede estar vacía.")
    @Size(max = 2083, message = "La URL de la imagen no puede tener más de 2083 caracteres.")
    private String imageUrl;

    @Valid
    @NotNull(message = "La categoría no puede ser nula.")
    private CategoryRequest category;

    private OwnerRequest owner;

    @NotNull(message = "El estado del grupo no puede ser nulo.")
    private GroupStatus status;
}