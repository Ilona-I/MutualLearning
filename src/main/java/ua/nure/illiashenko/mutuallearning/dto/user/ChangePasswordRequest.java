package ua.nure.illiashenko.mutuallearning.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordRequest {

    private String login;
    private String currentPassword;
    private String newPassword;
    private String repeatNewPassword;
}
