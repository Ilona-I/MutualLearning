package ua.nure.illiashenko.mutuallearning.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.dto.user.ChangePasswordRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.LoginRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.RegistrationRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.UserLoginResponse;
import ua.nure.illiashenko.mutuallearning.dto.user.UserLoginRoleStatusResponse;
import ua.nure.illiashenko.mutuallearning.dto.user.UserRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.UserResponse;
import ua.nure.illiashenko.mutuallearning.service.UserService;
import ua.nure.illiashenko.mutuallearning.util.JsonParser;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JsonParser jsonParser;

    @PostMapping("/signUp")
    public UserLoginRoleStatusResponse signUp(@RequestBody RegistrationRequest registrationRequest) {
        return userService.signUp(registrationRequest);
    }

    @PostMapping("/logIn")
    public UserLoginRoleStatusResponse logIn(@RequestBody LoginRequest loginRequest) {
        return userService.logIn(loginRequest);
    }

    @PutMapping("/password")
    public void changePassword(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestBody ChangePasswordRequest changePasswordRequest) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        userService.changePassword(login, changePasswordRequest);
    }

    @PutMapping()
    public UserLoginResponse updateUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestBody UserRequest user) {
        return null;
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestBody UserRequest user) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        userService.updateProfile(login, user);
    }

    @GetMapping("/{login}")
    public UserResponse getUser(@PathVariable String login) {
        return userService.getUser(login);
    }

    @GetMapping("/profile")
    public UserResponse getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return userService.getUser(login);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        userService.deleteUser(login);
    }
}
