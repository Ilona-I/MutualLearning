package ua.nure.illiashenko.mutuallearning.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.QuestionsResponse;

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
