package red.social.interesescomunes.eventattendee.infrastructure.input.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.eventattendee.domain.enums.AttendanceStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventAttendeeRequest {
    @NotNull(message = "El ID del miembro no puede ser nulo")
    private Long memberId;

    @NotNull(message = "El ID del evento no puede ser nulo")
    private Long eventId;

    @NotNull(message = "El estado de asistencia no puede ser nulo")
    private AttendanceStatus status;

}
