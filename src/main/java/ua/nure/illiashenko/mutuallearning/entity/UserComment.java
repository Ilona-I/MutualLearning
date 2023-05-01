package ua.nure.illiashenko.mutuallearning.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserComment {

    private int id;
    private String userLogin;
    private int commentId;
    private String role;
    private int point;
}
