package ua.nure.illiashenko.mutuallearning.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.constants.ArticleType;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleForUpdateResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleListElementResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleFileLinksResponse;
import ua.nure.illiashenko.mutuallearning.service.ArticleService;
import ua.nure.illiashenko.mutuallearning.util.JsonParser;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private JsonParser jsonParser;

    @PostMapping
    public List<ArticleFileLinksResponse> createArticle(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestBody ArticleRequest articleRequest) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return articleService.createArticle(login, articleRequest);
    }

    @PutMapping("/{id}")
    public List<ArticleFileLinksResponse> editArticle(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestBody ArticleRequest articleRequest,
        @PathVariable int id) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return articleService.editArticle(login, id, articleRequest);
    }

    @GetMapping("/edit")
    public ArticleForUpdateResponse getArticleForUpdate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestParam int id) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return articleService.getArticleForUpdate(login, id);
    }

    @GetMapping("/{id}")
    public ArticleResponse getArticle(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @PathVariable int id) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return articleService.getArticle(login, id);
    }

    @GetMapping()
    public List<ArticleListElementResponse> getArticles(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestParam(required = false) Integer[] marksId,
        @RequestParam String articleType, @RequestParam String ownerType,
        @RequestParam(required = false) String searchLine, @RequestParam(required = false) String searchType,
        @RequestParam int page, @RequestParam int size) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return articleService.getArticles(login, marksId, articleType, ownerType, searchLine, searchType, page, size);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @PathVariable int id) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        articleService.deleteArticle(login, id);
    }
}
