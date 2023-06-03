package ua.nure.illiashenko.mutuallearning.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import ua.nure.illiashenko.mutuallearning.constants.ArticleType;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleForUpdateResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleListElementResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleFileLinksResponse;
import ua.nure.illiashenko.mutuallearning.service.ArticleService;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public List<ArticleFileLinksResponse> createArticle(@RequestBody ArticleRequest articleRequest) {
        return articleService.createArticle(articleRequest);
    }

    @PutMapping("/{id}")
    public List<ArticleFileLinksResponse> editArticle(@RequestBody ArticleRequest articleRequest,
        @PathVariable int id) {
        return articleService.editArticle(id, articleRequest);
    }

    @GetMapping("/edit")
    public ArticleForUpdateResponse getArticleForUpdate(@RequestParam int id) {
        return articleService.getArticleForUpdate(id);
    }

    @GetMapping("/{id}")
    public ArticleResponse getArticle(@PathVariable int id) {
        return articleService.getArticle(id);
    }

    @GetMapping()
    public List<ArticleListElementResponse> getArticles(@RequestParam(required = false) Integer[] marksId,
        @RequestParam String articleType, @RequestParam String ownerType, @RequestParam String sortType,
        @RequestParam(required = false) String searchLine, @RequestParam(required = false) String searchType,
        @RequestParam int page, @RequestParam int size) {
        return articleService.getArticles(marksId, articleType, ownerType, sortType, searchLine, searchType, page, size);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable int id) {
        articleService.deleteArticle(id);
    }
}
