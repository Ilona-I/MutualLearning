package ua.nure.illiashenko.mutuallearning.service;

import static ua.nure.illiashenko.mutuallearning.constants.SystemUserRole.USER;
import static ua.nure.illiashenko.mutuallearning.constants.UserStatus.ACTIVE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ua.nure.illiashenko.mutuallearning.dto.user.ChangePasswordRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.RegistrationRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.UserLoginResponse;
import ua.nure.illiashenko.mutuallearning.dto.user.UserLoginRoleResponse;
import ua.nure.illiashenko.mutuallearning.dto.user.UserRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.UserResponse;
import ua.nure.illiashenko.mutuallearning.entity.User;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;
import ua.nure.illiashenko.mutuallearning.validation.UserValidator;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserRepository userRepository;

    public UserLoginRoleResponse signUp(@RequestBody RegistrationRequest registrationRequest) {
        userValidator.validateArticleRequest(registrationRequest);
        final User user = new User();
        user.setLogin(registrationRequest.getLogin());
        user.setEmail(registrationRequest.getEmail());
        user.setName(registrationRequest.getName());
        user.setPassword(registrationRequest.getPassword());
        user.setInfo(registrationRequest.getInfo());
        user.setRole(USER);
        user.setStatus(ACTIVE);
        log.info(user.toString());
        return new UserLoginRoleResponse(userRepository.save(user).getLogin(), user.getRole());
    }

    public UserLoginResponse changePassword(@RequestBody ChangePasswordRequest updatePasswordRequest) {
        return null;
    }

    public UserLoginResponse updateUser(@RequestBody UserRequest user) {
        return null;
    }

    public UserResponse getUser(@PathVariable String login) {
        return null;
    }


    public UserResponse getUsers() {
        return null;
    }


    public void deleteUser(@PathVariable String login) {

    }


    public UserResponse getUserByEmail(@RequestParam String email) {
        return null;
    }


    public UserLoginResponse submitEmail(@PathVariable String login, @PathVariable String token) {
        return null;
    }
}
