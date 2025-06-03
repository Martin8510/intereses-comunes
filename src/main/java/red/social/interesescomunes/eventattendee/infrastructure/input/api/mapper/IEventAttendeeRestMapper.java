package red.social.interesescomunes.eventattendee.infrastructure.input.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import red.social.interesescomunes.eventattendee.domain.enums.AttendanceStatus;
import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;
import red.social.interesescomunes.eventattendee.infrastructure.input.api.dto.request.EventAttendeeRequest;
import red.social.interesescomunes.eventattendee.infrastructure.input.api.dto.response.EventAttendeeResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEventAttendeeRestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToStatus")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    EventAttendee toDomain(EventAttendeeRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status", qualifiedByName = "typeStatusToString")
    EventAttendeeResponse toResponse(EventAttendee eventAttendee);

    List<EventAttendeeResponse> toResponseList(List<EventAttendee> eventAttendees);

    @Named("stringToStatus")
    default AttendanceStatus stringToStatus(String status){
        return AttendanceStatus.valueOf(status.toUpperCase());
    }

    @Named("typeStatusToString")
    default String typeStatusToString(AttendanceStatus status){
        return status.name();
    }
}