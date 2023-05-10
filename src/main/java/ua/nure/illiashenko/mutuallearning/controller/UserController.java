package ua.nure.illiashenko.mutuallearning.controller;

import lombok.AllArgsConstructor;
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
import ua.nure.illiashenko.mutuallearning.dto.user.RegistrationRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.UserLoginResponse;
import ua.nure.illiashenko.mutuallearning.dto.user.UserRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.UserResponse;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @PostMapping
    public UserResponse signUp(@RequestBody RegistrationRequest registrationRequest) {
        return null;
    }

    @PutMapping("/password")
    public UserLoginResponse changePassword(@RequestBody ChangePasswordRequest updatePasswordRequest) {
        return null;
    }

    @PutMapping()
    public UserResponse updateUser(@RequestBody UserRequest user) {
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

    @GetMapping
    public UserResponse getUserByEmail(@RequestParam String email) {
        return null;
    }

    @GetMapping("/submit/email/{login}/{token}")
    public UserLoginResponse submitEmail(@PathVariable String login, @PathVariable String token) {
        return null;
    }
}
