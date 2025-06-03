package red.social.interesescomunes.eventattendee.infrastructure.input.api.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.eventattendee.domain.enums.AttendanceStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttendanceStatusRequest {
    @NotBlank(message = "El estado no puede estar vacío")
    private AttendanceStatus status;
}