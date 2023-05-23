package ua.nure.illiashenko.mutuallearning.dto.article;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ArticlePartRequest {

    private int id;
    private int sequenceNumber;
    private String text;
    private MultipartFile file;
    private String type;
}
