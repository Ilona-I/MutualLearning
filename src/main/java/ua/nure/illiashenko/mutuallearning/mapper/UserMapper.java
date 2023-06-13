package ua.nure.illiashenko.mutuallearning.mapper;

import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.dto.user.RegistrationRequest;
import ua.nure.illiashenko.mutuallearning.entity.User;

@Component
public class UserMapper {

    public User mapRegistrationRequestToUser(RegistrationRequest registrationRequest) {
        final User user = new User();
        user.setLogin(registrationRequest.getLogin());
        user.setEmail(registrationRequest.getEmail());
        user.setName(registrationRequest.getName());
        user.setPassword(registrationRequest.getPassword());
        user.setInfo(registrationRequest.getInfo());
        return user;
    }
}
