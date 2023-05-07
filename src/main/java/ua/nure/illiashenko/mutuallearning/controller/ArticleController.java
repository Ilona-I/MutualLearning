package ua.nure.illiashenko.mutuallearning.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.dto.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.QuestionsResponse;

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

    @GetMapping("/questions")
    public QuestionsResponse getQuestions() {
        return null;
    }
}
