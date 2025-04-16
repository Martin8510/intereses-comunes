package red.social.interesescomunes.administrator.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.administrator.domain.enums.AdministratorStatus;
import red.social.interesescomunes.administrator.domain.model.Administrator;
import red.social.interesescomunes.user.domain.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdministratorEventDTO {
    private Long id;
    private User user;
    private AdministratorStatus status;

    public static AdministratorEventDTO create(Administrator administrator){
        return AdministratorEventDTO.builder()
                .id(administrator.getId())
                .user(administrator.getUser())
                .status(administrator.getStatus())
                .build();
    }
}
