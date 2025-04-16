package red.social.interesescomunes.user.application.input;

import red.social.interesescomunes.user.domain.model.User;

import java.util.List;

public interface IUserServicePort {
    List<User> findAllUsers();
    User findUserById(Long id);
    User findUserByUserName(String userName);
    User createUser(User user, List<String> roleName);
    User updateUser(Long id, User user);
    void updateInternalUser(User existingUser, User user);
}
