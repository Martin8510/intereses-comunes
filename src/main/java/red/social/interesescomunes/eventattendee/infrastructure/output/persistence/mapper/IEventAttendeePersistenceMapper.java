package red.social.interesescomunes.eventattendee.infrastructure.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import red.social.interesescomunes.eventattendee.domain.enums.AttendanceStatus;
import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;
import red.social.interesescomunes.eventattendee.infrastructure.output.persistence.mysql.entity.EventAttendeeEntity;
import red.social.interesescomunes.groupevents.domain.model.GroupEvent;
import red.social.interesescomunes.member.domain.model.Member;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEventAttendeePersistenceMapper {

    @Mapping(source = "member.id", target = "member.id")
    @Mapping(source = "event.id", target = "event.id")
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToStatus")
    EventAttendee toDomain(EventAttendeeEntity entity);

    @Mapping(source = "member.id", target = "member.id")
    @Mapping(source = "event.id", target = "event.id")
    @Mapping(source = "status", target = "status", qualifiedByName = "typeStatusToString")
    EventAttendeeEntity toEntity(EventAttendee domain);

    List<EventAttendee> toDomainList(List<EventAttendeeEntity> entities);

    @Named("stringToStatus")
    default AttendanceStatus stringToStatus(String status){
        return AttendanceStatus.valueOf(status.toUpperCase());
    }

    @Named("typeStatusToString")
    default String typeStatusToString(AttendanceStatus status){
        return status.name();
    }
}