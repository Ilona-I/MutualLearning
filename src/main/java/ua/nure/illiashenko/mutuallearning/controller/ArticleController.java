package ua.nure.illiashenko.mutuallearning.controller;

import java.sql.Timestamp;
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
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleListElementResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.Member;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleFileLinksResponse;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

    @PostMapping
    public List<ArticleFileLinksResponse> createArticle(@RequestBody ArticleRequest articleRequest) {
        return null;
    }

    @PutMapping("/{id}")
    public List<ArticleFileLinksResponse> editArticle(@RequestBody ArticleRequest articleRequest, @PathVariable int id) {
        System.out.println(articleRequest);
        List<ArticleFileLinksResponse> response = new ArrayList();
        for (ArticlePartRequest part : articleRequest.getArticleParts()) {
            if (part.getType().equals("image") || part.getType().equals("file")) {
                response.add(ArticleFileLinksResponse.builder()
                    .id(part.getId())
                    .type(part.getType())
                    .link(part.getId() + "-" + part.getLink())
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
        return ArticleResponse.builder()
            .id(id)
            .title("Title1")
            .type("article")
            .creationDateTime(new Timestamp(System.currentTimeMillis()))
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

    @GetMapping()
    public List<ArticleListElementResponse> getArticles(@RequestParam(required = false) String marksId,
        @RequestParam String articleType, @RequestParam String ownerType, @RequestParam String sortType,
        @RequestParam(required = false) String searchLine, @RequestParam(required = false) String searchType) {
        List<ArticleListElementResponse> result = new ArrayList<>();
        result.add(ArticleListElementResponse.builder()
            .id(1)
            .title("Title1")
            .type("article")
            .members(new Member[]{
                Member.builder()
                    .login("user11")
                    .level("expert")
                    .role("questionWriter")
                    .name("User #11")
                    .info("Information1")
                    .build(),
                Member.builder()
                    .login("user22")
                    .level("expert")
                    .role("articleCreator")
                    .name("User #22")
                    .info("Information2")
                    .build()
            })
            .creationDateTime(new Timestamp(System.currentTimeMillis()))
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
            .build());
        result.add(ArticleListElementResponse.builder()
            .id(2)
            .title("Title2")
            .type("question")
            .members(new Member[]{
                Member.builder()
                    .login("user11")
                    .level("expert")
                    .role("creator")
                    .name("User #11")
                    .info("Information1")
                    .build(),
            })
            .creationDateTime(new Timestamp(System.currentTimeMillis()))
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
                    .title("Mark4")
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
            .build());
        result.add(ArticleListElementResponse.builder()
            .id(3)
            .title("Title3")
            .type("article")
            .members(new Member[]{
                Member.builder()
                    .login("user33")
                    .level("expert")
                    .role("creator")
                    .name("User #33")
                    .info("Information3")
                    .build(),
            })
            .creationDateTime(new Timestamp(System.currentTimeMillis()))
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
                    .id(5)
                    .title("Mark5")
                    .type("system")
                    .description("Description5")
                    .build()
            })
            .build());
        return result;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable int id) {

    }

}
