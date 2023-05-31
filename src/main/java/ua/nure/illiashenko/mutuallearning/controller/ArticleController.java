package ua.nure.illiashenko.mutuallearning.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.QuestionsResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.SaveArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

    @PostMapping
    public List<SaveArticleResponse> createArticle(@RequestBody ArticleRequest articleRequest) {
        return null;
    }

    @PutMapping("/{id}")
    public List<SaveArticleResponse> editArticle(@RequestBody ArticleRequest articleRequest, @PathVariable int id) {
        System.out.println(articleRequest);
        List<SaveArticleResponse> response = new ArrayList();
        for (ArticlePartRequest part : articleRequest.getArticleParts()) {
            if (part.getType().equals("image") || part.getType().equals("file")) {
                response.add(SaveArticleResponse.builder()
                    .id(part.getId())
                    .type(part.getType())
                    .link(part.getId()+"-"+part.getLink())
                    .build());
            }
        }
        return response;
    }

    @GetMapping("/edit")
    public ArticleForUpdateResponse getArticleForUpdate(@RequestParam int id) {
        return ArticleForUpdateResponse.builder()
            .id(id)
            .title("Title1")
            .type("article")
            .marks(new MarkResponse[]{
                MarkResponse.builder()
                    .id(1)
                    .title("Інженерія програмного забезпечення")
                    .creator("user1")
                    .type("custom")
                    .description("Description1")
                    .build(),
                MarkResponse.builder()
                    .id(2)
                    .title("Mark2")
                    .creator("user2")
                    .type("custom")
                    .description("Description2")
                    .build(),
                MarkResponse.builder()
                    .id(3)
                    .title("Mark3")
                    .type("system")
                    .description("Description3")
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
                        .link("1.png")
                        .type("image")
                        .build(),
                    ArticlePartResponse.builder()
                        .id(3)
                        .sequenceNumber(3)
                        .text("Text3")
                        .link("1.pdf")
                        .type("file")
                        .build(),
                    ArticlePartResponse.builder()
                        .id(4)
                        .sequenceNumber(4)
                        .text("<input type='text' \"\n"
                            + "        + \"      id='sequenceNumber\" + partId + \"' \"\n"
                            + "        + \"      value='\" + sequenceNumber + \"' hidden>")
                        .type("code")
                        .build(),
                    ArticlePartResponse.builder()
                        .id(5)
                        .sequenceNumber(5)
                        .text("Current link")
                        .link("https://www.google.de/")
                        .type("link")
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
    public void deleteArticle(@PathVariable int id) {

    }

}
