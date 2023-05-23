package ua.nure.illiashenko.mutuallearning.controller;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleForUpdateResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.QuestionsResponse;
import ua.nure.illiashenko.mutuallearning.entity.Mark;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

    @PostMapping
    public ArticleResponse createArticle(@RequestBody ArticleRequest articleRequest) {
        return null;
    }

    @PutMapping("/{id}")
    public ArticleResponse editArticle(@RequestBody ArticleRequest articleRequest, @PathVariable int id) {
        return null;
    }

    @GetMapping("/edit")
    public ArticleForUpdateResponse getArticleForUpdate(@RequestParam int id) {
        return ArticleForUpdateResponse.builder()
            .id(id)
            .title("Title1")
            .type("article")
            .marks(new Mark[]{
                Mark.builder()
                    .id(1)
                    .title("Mark1")
                    .type("custom")
                    .description("Description1")
                    .build(),
                Mark.builder()
                    .id(2)
                    .title("Mark2")
                    .type("system")
                    .description("Description2")
                    .build()
            })
            .articleParts(
                new ArticlePartResponse[]{
                    ArticlePartResponse.builder()
                        .id(1)
                        .sequenceNumber(1)
                        .text("Text1")
                        .type("text")
                        .build(),
                    ArticlePartResponse.builder()
                        .id(2)
                        .sequenceNumber(2)
                        .text("Text2")
                        .type("image")
                        .build(),
                    ArticlePartResponse.builder()
                        .id(3)
                        .sequenceNumber(3)
                        .text("Text1")
                        .type("file")
                        .build()
                }
            )
            .build();
    }

    @GetMapping("/{id}")
    public ArticleResponse getArticle(@PathVariable int id) {
        return null;
    }

    @GetMapping
    public QuestionsResponse getQuestions() {
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable int id){

    }

}
