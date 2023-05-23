package ua.nure.illiashenko.mutuallearning.dto.article;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {

    private String login;
    private String name;
    private String info;
    private String level;

}
