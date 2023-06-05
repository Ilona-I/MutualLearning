package ua.nure.illiashenko.mutuallearning.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.dto.user.ChangePasswordRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.LoginRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.RegistrationRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.UserLoginResponse;
import ua.nure.illiashenko.mutuallearning.dto.user.UserLoginRoleResponse;
import ua.nure.illiashenko.mutuallearning.dto.user.UserRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.UserResponse;
import ua.nure.illiashenko.mutuallearning.service.UserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public UserLoginRoleResponse signUp(@RequestBody RegistrationRequest registrationRequest) {
        return userService.signUp(registrationRequest);
    }

    @PostMapping("/logIn")
    public UserLoginRoleResponse logIn(@RequestBody LoginRequest loginRequest) {
        return userService.logIn(loginRequest);
    }

    @PutMapping("/password")
    public UserLoginResponse changePassword(@RequestBody ChangePasswordRequest updatePasswordRequest) {
        return null;
    }

    @PutMapping()
    public UserLoginResponse updateUser(@RequestBody UserRequest user) {
        return null;
    }

    @GetMapping("/{login}")
    public UserResponse getUser(@PathVariable String login) {
        return null;
    }

    @GetMapping
    public UserResponse getUsers() {
        return null;
    }

    @DeleteMapping("/{login}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String login) {

    }

    @GetMapping("/email")
    public UserResponse getUserByEmail(@RequestParam String email) {
        return null;
    }

    @GetMapping("/submit/email/{token}")
    public UserLoginResponse submitEmail(@PathVariable String token) {
        return null;
    }
}
