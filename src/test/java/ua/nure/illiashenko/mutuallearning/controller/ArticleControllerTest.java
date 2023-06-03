package ua.nure.illiashenko.mutuallearning.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.nure.illiashenko.mutuallearning.constants.ArticlePartType;
import ua.nure.illiashenko.mutuallearning.constants.ArticleType;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleFileLinksResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.entity.Article;
import ua.nure.illiashenko.mutuallearning.mapper.ArticleMapper;
import ua.nure.illiashenko.mutuallearning.mapper.ArticlePartMapper;
import ua.nure.illiashenko.mutuallearning.mapper.MarkMapper;
import ua.nure.illiashenko.mutuallearning.repository.ArticleMarkRepository;
import ua.nure.illiashenko.mutuallearning.repository.ArticlePartRepository;
import ua.nure.illiashenko.mutuallearning.repository.ArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.MarkRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;
import ua.nure.illiashenko.mutuallearning.service.ArticleService;
import ua.nure.illiashenko.mutuallearning.validation.ArticleValidator;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ArticleControllerTest {
/*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleMapper articleMapper;
    @Autowired
    private ArticlePartMapper articlePartMapper;
    @Autowired
    private MarkMapper markMapper;
    @Autowired
    private ArticleValidator articleValidator;
    @Autowired
    private ArticleRepository articleRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserArticleRepository userArticleRepository;
    @MockBean
    private ArticleMarkRepository articleMarkRepository;
    @MockBean
    private ArticlePartRepository articlePartRepository;
    @MockBean
    private MarkRepository markRepository;
    @Autowired
    private ArticleService articleService;

    @Test
    void shouldCreateArticle() throws Exception {
        final ArticleRequest createArticleRequestCorrectData = ArticleRequest.builder()
            .title("Article title #1")
            .type(ArticleType.ARTICLE)
            .articleParts(new ArticlePartRequest[]{
                ArticlePartRequest.builder()
                    .id(1)
                    .sequenceNumber(1)
                    .text("Article text 1235@#^$&8)(")
                    .link(null)
                    .type(ArticlePartType.TEXT)
                    .build(),
                ArticlePartRequest.builder()
                    .id(2)
                    .sequenceNumber(2)
                    .text("<input type=\"text\"/>")
                    .link(null)
                    .type(ArticlePartType.CODE)
                    .build(),
                ArticlePartRequest.builder()
                    .id(3)
                    .sequenceNumber(3)
                    .text("This is file")
                    .link("newFile.pdf")
                    .type(ArticlePartType.FILE)
                    .build(),
                ArticlePartRequest.builder()
                    .id(4)
                    .sequenceNumber(4)
                    .text(null)
                    .link("newImage.png")
                    .type(ArticlePartType.IMAGE)
                    .build(),
                ArticlePartRequest.builder()
                    .id(5)
                    .sequenceNumber(5)
                    .text("This is link!")
                    .link("https://www.google.com/")
                    .type(ArticlePartType.LINK)
                    .build()
            })
            .marksId(new String[]{"1", "2", "3"})
            .build();

        final List<ArticleFileLinksResponse> expected = new ArrayList<>();
        expected.add(ArticleFileLinksResponse.builder()
            .id(3)
            .type(ArticlePartType.FILE)
            .link("3-newFile.pdf")
            .build());
        expected.add(ArticleFileLinksResponse.builder()
            .id(4)
            .type(ArticlePartType.IMAGE)
            .link("4-newImage.png")
            .build());

        final Article savedArticle = new Article();
        savedArticle.setId(11);
        savedArticle.setType(ArticleType.ARTICLE);

        when(markRepository.existsById(any())).thenReturn(true);
        when(articleMapper.mapArticleRequestToArticle(createArticleRequestCorrectData)).thenCallRealMethod();
        when(articleRepository.save(any())).thenReturn(savedArticle);
        when(articlePartRepository.existsById(any())).thenReturn(false,false, false);


        final ObjectMapper objectMapper = configureObjectMapper();
        final String stockProductAsString = objectMapper.writeValueAsString(createArticleRequestCorrectData);

        mockMvc.perform(post("/articles")
                .contentType("application/json")
                .content(stockProductAsString))
            .andExpect(status().isCreated())
            .andExpect(content().string(objectMapper.writeValueAsString(expected)));
    }

    private ObjectMapper configureObjectMapper() {
        final ObjectMapper objectToJson = new ObjectMapper();
        objectToJson.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return objectToJson;
    }*/

}
