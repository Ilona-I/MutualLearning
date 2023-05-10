package ua.nure.illiashenko.mutuallearning.dto.article;

import org.springframework.web.multipart.MultipartFile;

public class ArticlePartRequest {

    private int id;
    private int sequenceNumber;
    private String text;
    private MultipartFile file;
    private String type;
}
