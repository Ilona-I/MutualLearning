package ua.nure.illiashenko.mutuallearning.dto.test.question.answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveAnswerRequest {

    private Integer answerId;
    private String text;
    private Integer mark;
}
