package ua.nure.illiashenko.mutuallearning.dto;

public class ArticleRequest {

    private String userLogin;
    private String title;
    private String type;
    private ArticlePartRequest[] articleParts;
    private int[] marksId;
}
