package ua.nure.illiashenko.mutuallearning.service;

import static ua.nure.illiashenko.mutuallearning.constants.SystemUserRole.PREMIUM_USER;
import static ua.nure.illiashenko.mutuallearning.constants.SystemUserRole.USER;
import static ua.nure.illiashenko.mutuallearning.constants.UserStatus.ACTIVE;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.nure.illiashenko.mutuallearning.dto.user.ChangePasswordRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.LoginRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.RegistrationRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.UserLoginRoleStatusResponse;
import ua.nure.illiashenko.mutuallearning.dto.user.UserRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.UserResponse;
import ua.nure.illiashenko.mutuallearning.entity.User;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;
import ua.nure.illiashenko.mutuallearning.exception.user.UserNotFoundException;
import ua.nure.illiashenko.mutuallearning.mapper.UserMapper;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;
import ua.nure.illiashenko.mutuallearning.validation.UserValidator;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserRepository userRepository;

    public UserLoginRoleStatusResponse signUp(RegistrationRequest registrationRequest) {
        userValidator.validateArticleRequest(registrationRequest);
        final User user = userMapper.mapRegistrationRequestToUser(registrationRequest);
        user.setRole(USER);
        user.setStatus(ACTIVE);
        return new UserLoginRoleStatusResponse(userRepository.save(user).getLogin(), user.getRole(), user.getStatus());
    }

    public UserLoginRoleStatusResponse logIn(LoginRequest loginRequest) {
        final Optional<User> optionalUser = userRepository.findById(loginRequest.getLogin());
        if (optionalUser.isEmpty()) {
            throw new ServiceApiException(List.of("wrongLogin"), HttpStatus.BAD_REQUEST);
        }
        final User user = optionalUser.get();
        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new ServiceApiException(List.of("wrongPassword"), HttpStatus.BAD_REQUEST);
        }
        return new UserLoginRoleStatusResponse(user.getLogin(), user.getRole(), user.getStatus());
    }

    public void updateProfile(String login, UserRequest userRequest) {
        final User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if (userRepository.existsByEmail(userRequest.getEmail()) && !user.getEmail().equals(userRequest.getEmail())) {
            throw new ServiceApiException(List.of("userWithThisEmailAlreadyExist"), HttpStatus.BAD_REQUEST);
        }
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setInfo(userRequest.getInfo());
        userRepository.save(user);
    }

    public void changePassword(String login, ChangePasswordRequest changePasswordRequest) {
        final User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if (!changePasswordRequest.getCurrentPassword().equals(user.getPassword())) {
            throw new ServiceApiException(List.of("wrongCurrentPassword"), HttpStatus.BAD_REQUEST);
        }
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getRepeatedPassword())) {
            throw new ServiceApiException(List.of("wrongRepeatedPassword"), HttpStatus.BAD_REQUEST);
        }
        user.setPassword(changePasswordRequest.getNewPassword());
        userRepository.save(user);
    }

    public UserResponse getUser(String login) {
        final User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        return UserResponse.builder()
            .login(user.getLogin())
            .name(user.getName())
            .email(user.getEmail())
            .info(user.getInfo())
            .role(user.getRole())
            .status(user.getStatus())
            .build();
    }

    public void updateToPremiumUser(String login) {
        final User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        user.setRole(PREMIUM_USER);
        userRepository.save(user);
    }

    public void deleteUser(String login) {
        if (!userRepository.existsById(login)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(login);
    }
}
