package ua.nure.illiashenko.mutuallearning.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePartRequest {

    private int id;
    private int sequenceNumber;
    private String text;
    private String link;
    private String type;
}
