package red.social.interesescomunes.groupevents.infrastructure.input.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import red.social.interesescomunes.groupevents.domain.enums.GroupEventStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupEventRequest {
    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 255, message = "El título no puede tener más de 255 caracteres")
    private String title;

    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres")
    private String description;

    @NotNull(message = "La fecha del evento es requerida")
    @FutureOrPresent(message = "La fecha del evento debe ser hoy o en el futuro")
    private LocalDate eventDate;

    @NotNull(message = "La hora del evento es requerida")
    private LocalTime eventTime;

    @Min(value = 1, message = "La capacidad máxima debe ser al menos 1")
    private Integer maxCapacity;

    private MultipartFile imageFile;
    private String imageUrl;

    @NotNull(message = "El estado del evento es requerido")
    private GroupEventStatus status;
}
