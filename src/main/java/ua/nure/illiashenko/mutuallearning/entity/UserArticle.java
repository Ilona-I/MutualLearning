package ua.nure.illiashenko.mutuallearning.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserArticle {

    private int id;
    private String userLogin;
    private int articleId;
    private String role;
    private int point;
}
