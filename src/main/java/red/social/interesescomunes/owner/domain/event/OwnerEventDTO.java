package red.social.interesescomunes.owner.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.owner.domain.model.Owner;
import red.social.interesescomunes.owner.domain.enums.OwnerStatus;
import red.social.interesescomunes.user.domain.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerEventDTO {
    private Long id;
    private User user;
    private OwnerStatus status;

    public static OwnerEventDTO create(Owner owner){
        return OwnerEventDTO.builder()
            .id(owner.getId())
            .user(owner.getUser())
            .status(owner.getStatus())
            .build();
    }
}
