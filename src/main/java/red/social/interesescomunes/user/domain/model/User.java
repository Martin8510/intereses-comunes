package red.social.interesescomunes.user.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.role.domain.model.Role;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String address;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isAccountNoExpired;
    private boolean isAccountNoLocked;
    private boolean IsCredentialNoExpired;
    private List<Role> roles;
}
