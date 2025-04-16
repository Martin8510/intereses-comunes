package red.social.interesescomunes.user.infrastructure.input.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.user.application.input.IUserServicePort;
import red.social.interesescomunes.user.domain.model.User;
import red.social.interesescomunes.user.infrastructure.input.api.dto.request.UserRequest;
import red.social.interesescomunes.user.infrastructure.input.api.dto.response.UserResponse;
import red.social.interesescomunes.user.infrastructure.input.api.mapper.IUserRestMappert;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final IUserServicePort service;
    private final IUserRestMappert mappert;

    public UserController(IUserServicePort servicePort, IUserRestMappert mappert) {
        this.service = servicePort;
        this.mappert = mappert;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<User> users = this.service.findAllUsers();
        List<UserResponse> response = this.mappert.toUserResponseList(users);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id) {
        User user = this.service.findUserById(id);
        UserResponse response = this.mappert.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/user-name/{userName}")
    public ResponseEntity<UserResponse> findByUserName(@PathVariable String userName) {
        User user = this.service.findUserByUserName(userName);
        UserResponse response = this.mappert.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
     //   User user = this.mappert.toDomain(userRequest);
     //   User savedUser = this.service.updateUser(id,user);
    //    UserResponse response = this.mappert.toUserResponse(savedUser);
      //  return ResponseEntity.status(HttpStatus.OK).body(response);
        return null;
    }
}