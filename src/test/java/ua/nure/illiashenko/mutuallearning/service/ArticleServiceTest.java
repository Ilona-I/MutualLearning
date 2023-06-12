package ua.nure.illiashenko.mutuallearning.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.nure.illiashenko.mutuallearning.BaseSpringBootTest;
import ua.nure.illiashenko.mutuallearning.constants.ArticlePartType;
import ua.nure.illiashenko.mutuallearning.constants.ArticleType;
import ua.nure.illiashenko.mutuallearning.constants.ArticleUserRole;
import ua.nure.illiashenko.mutuallearning.constants.MarkType;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleFileLinksResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleForUpdateResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleListElementResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.Member;
import ua.nure.illiashenko.mutuallearning.dto.article.TestTitleResponse;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;
import ua.nure.illiashenko.mutuallearning.entity.Article;
import ua.nure.illiashenko.mutuallearning.entity.ArticleMark;
import ua.nure.illiashenko.mutuallearning.entity.ArticlePart;
import ua.nure.illiashenko.mutuallearning.entity.Mark;
import ua.nure.illiashenko.mutuallearning.entity.UserArticle;
import ua.nure.illiashenko.mutuallearning.mapper.ArticleMapper;
import ua.nure.illiashenko.mutuallearning.mapper.ArticlePartMapper;
import ua.nure.illiashenko.mutuallearning.mapper.MarkMapper;
import ua.nure.illiashenko.mutuallearning.repository.ArticleMarkRepository;
import ua.nure.illiashenko.mutuallearning.repository.ArticlePartRepository;
import ua.nure.illiashenko.mutuallearning.repository.ArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.MarkRepository;
import ua.nure.illiashenko.mutuallearning.repository.TestRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;
import ua.nure.illiashenko.mutuallearning.validation.ArticleValidator;


public class ArticleServiceTest extends BaseSpringBootTest {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticlePartMapper articlePartMapper;
    @Autowired
    private MarkMapper markMapper;
    @MockBean
    private ArticleValidator articleValidator;
    @MockBean
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
    @MockBean
    private TestRepository testRepository;
    @Autowired
    private ArticleService articleService;

    @Test
    void shouldReturnArticleFileLinksResponse_WhenCreateArticle() {
        final Article savedArticle = new Article();
        savedArticle.setId(11);
        savedArticle.setType(ArticleType.ARTICLE);
        final List<ArticleFileLinksResponse> expected = new ArrayList<>();
        expected.add(ArticleFileLinksResponse.builder()
            .id(1)
            .type(ArticlePartType.TEXT)
            .build());
        expected.add(ArticleFileLinksResponse.builder()
            .id(2)
            .type(ArticlePartType.CODE)
            .build());
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
        expected.add(ArticleFileLinksResponse.builder()
            .id(5)
            .type(ArticlePartType.LINK)
            .link("https://www.google.com/")
            .build());
        when(markRepository.existsById(any())).thenReturn(true);
        when(articleRepository.save(notNull())).thenReturn(savedArticle);
        when(articlePartRepository.existsById(notNull())).thenReturn(false);

        final List<ArticleFileLinksResponse> actual = articleService.createArticle("login",
            createArticleRequestCorrectData);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnArticleFileLinksResponse_WhenCreateArticleWithExistedPart() {
        final Article savedArticle = new Article();
        savedArticle.setId(11);
        savedArticle.setType(ArticleType.ARTICLE);
        final ArticlePart articlePart = new ArticlePart();
        articlePart.setId(1);
        articlePart.setSequenceNumber(1);
        articlePart.setArticleId(1);
        articlePart.setType(ArticlePartType.TEXT);
        articlePart.setText("text");
        final UserArticle userArticle = new UserArticle();
        userArticle.setUserLogin("login");
        userArticle.setArticleId(1);
        userArticle.setId(1);
        userArticle.setRole(ArticleUserRole.ARTICLE_CREATOR);
        final List<ArticleFileLinksResponse> expected = new ArrayList<>();
        expected.add(ArticleFileLinksResponse.builder()
            .id(1)
            .type(ArticlePartType.TEXT)
            .build());
        expected.add(ArticleFileLinksResponse.builder()
            .id(2)
            .type(ArticlePartType.CODE)
            .build());
        expected.add(ArticleFileLinksResponse.builder()
            .id(3)
            .type(ArticlePartType.FILE)
            .link("1-newFile.pdf")
            .build());
        expected.add(ArticleFileLinksResponse.builder()
            .id(4)
            .type(ArticlePartType.IMAGE)
            .link("1-newImage.png")
            .build());
        expected.add(ArticleFileLinksResponse.builder()
            .id(5)
            .type(ArticlePartType.LINK)
            .link("https://www.google.com/")
            .build());
        when(markRepository.existsById(any())).thenReturn(true);
        when(articleRepository.save(notNull())).thenReturn(savedArticle);
        when(articlePartRepository.save(notNull())).thenReturn(articlePart);
        when(userArticleRepository.findAllByArticleIdAndRoleIsIn(anyInt(), anyList())).thenReturn(List.of(userArticle));
        when(articlePartRepository.existsById(notNull())).thenReturn(true);

        final List<ArticleFileLinksResponse> actual = articleService.createArticle("login",
            createArticleRequestCorrectData);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnArticleFileLinksResponse_WhenUpdateArticle() {
        final Article savedArticle = new Article();
        savedArticle.setId(11);
        savedArticle.setType(ArticleType.ARTICLE);
        final List<ArticleFileLinksResponse> expected = new ArrayList<>();
        expected.add(ArticleFileLinksResponse.builder()
            .id(1)
            .type(ArticlePartType.TEXT)
            .build());
        expected.add(ArticleFileLinksResponse.builder()
            .id(2)
            .type(ArticlePartType.CODE)
            .build());
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
        expected.add(ArticleFileLinksResponse.builder()
            .id(5)
            .type(ArticlePartType.LINK)
            .link("https://www.google.com/")
            .build());
        when(articleRepository.findById(1)).thenReturn(Optional.of(savedArticle));
        when(markRepository.existsById(any())).thenReturn(true);
        when(articleRepository.save(notNull())).thenReturn(savedArticle);
        when(articlePartRepository.existsById(notNull())).thenReturn(false);

        final List<ArticleFileLinksResponse> actual = articleService.editArticle("login", 1,
            createArticleRequestCorrectData);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnArticleFileLinksResponse_WhenUpdateArticleWithTypeQuestion() {
        final Article savedArticle = new Article();
        savedArticle.setId(11);
        savedArticle.setType(ArticleType.QUESTION);
        final List<ArticleFileLinksResponse> expected = new ArrayList<>();
        expected.add(ArticleFileLinksResponse.builder()
            .id(1)
            .type(ArticlePartType.TEXT)
            .build());
        expected.add(ArticleFileLinksResponse.builder()
            .id(2)
            .type(ArticlePartType.CODE)
            .build());
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
        expected.add(ArticleFileLinksResponse.builder()
            .id(5)
            .type(ArticlePartType.LINK)
            .link("https://www.google.com/")
            .build());
        when(articleRepository.findById(1)).thenReturn(Optional.of(savedArticle));
        when(markRepository.existsById(any())).thenReturn(true);
        when(articleRepository.save(notNull())).thenReturn(savedArticle);
        when(articlePartRepository.existsById(notNull())).thenReturn(false);

        final List<ArticleFileLinksResponse> actual = articleService.editArticle("login", 1,
            createArticleRequestCorrectData);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnArticleToUpdate() {
        final Article dbArticle = new Article();
        dbArticle.setId(1);
        dbArticle.setTitle("title");
        dbArticle.setType(ArticleType.ARTICLE);
        final UserArticle userArticle = new UserArticle();
        userArticle.setUserLogin("login");
        userArticle.setArticleId(1);
        userArticle.setId(1);
        userArticle.setRole(ArticleUserRole.ARTICLE_CREATOR);
        final ArticlePart articlePart = new ArticlePart();
        articlePart.setId(1);
        articlePart.setSequenceNumber(1);
        articlePart.setArticleId(1);
        articlePart.setType(ArticlePartType.TEXT);
        articlePart.setText("text");
        final ArticleMark articleMark = new ArticleMark();
        articleMark.setId(1);
        articleMark.setArticleId(1);
        articleMark.setMarkId(1);
        final Mark mark = new Mark();
        mark.setId(1);
        mark.setType(MarkType.CUSTOM);
        mark.setCreator("login");
        mark.setTitle("title");
        mark.setDescription("description");

        final ArticleForUpdateResponse expected = ArticleForUpdateResponse.builder()
            .id(1)
            .title("title")
            .type(ArticleType.ARTICLE)
            .marks(new MarkResponse[]{
                MarkResponse.builder()
                    .id(1)
                    .title("title")
                    .type(MarkType.CUSTOM)
                    .description("description")
                    .creator("login")
                    .build()

            })
            .articleParts(new ArticlePartResponse[]{
                ArticlePartResponse.builder()
                    .id(1)
                    .sequenceNumber(1)
                    .text("text")
                    .type(ArticlePartType.TEXT)
                    .build()

            })
            .build();

        when(articleRepository.findById(1))
            .thenReturn(Optional.of(dbArticle));
        when(userArticleRepository.findByUserLoginAndArticleId("login", 1))
            .thenReturn(Optional.of(userArticle));
        when(articlePartRepository.findByArticleIdOrderBySequenceNumberAsc(1))
            .thenReturn(List.of(articlePart));
        when(articleMarkRepository.findByArticleId(1))
            .thenReturn(List.of(articleMark));
        when(markRepository.getById(1))
            .thenReturn(mark);

        final ArticleForUpdateResponse actual = articleService.getArticleForUpdate("login", 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnArticleListSearchByTitle() {
        final Article dbArticle = new Article();
        dbArticle.setId(1);
        dbArticle.setTitle("title");
        dbArticle.setType(ArticleType.ARTICLE);
        final List<Article> articles = new ArrayList<>();
        articles.add(dbArticle);
        final ArticleMark articleMark = new ArticleMark();
        articleMark.setId(1);
        articleMark.setArticleId(1);
        articleMark.setMarkId(1);
        final List<ArticleMark> articleMarks = new ArrayList<>();
        articleMarks.add(articleMark);
        final UserArticle userArticle = new UserArticle();
        userArticle.setUserLogin("login");
        userArticle.setArticleId(1);
        userArticle.setId(1);
        userArticle.setRole(ArticleUserRole.ARTICLE_CREATOR);
        final Integer[] marksId = new Integer[]{1, 2};
        final String articleType = ArticleType.ARTICLE;
        final String ownerType = "OWN";
        final String searchLine = "";
        final String searchType = "by_title";
        final int page = 1;
        final int size = 10;

        when(articleRepository.findAll())
            .thenReturn(articles);
        when(articleMarkRepository.findAll())
            .thenReturn(articleMarks);
        when(articleMarkRepository.findAllByMarkIdIsIn(Arrays.asList(marksId)))
            .thenReturn(articleMarks);
        when(userArticleRepository.findAllByUserLoginAndRoleIsIn(anyString(), anyList()))
            .thenReturn(List.of(userArticle));
        when(userArticleRepository.findAllByUserLoginContains(anyString()))
            .thenReturn(List.of(userArticle));
        when(articleRepository.findAllByIdIsInAndTypeIsInAndTitleContainsOrderByCreationDateTimeDesc(
            anySet(), anySet(), anyString(), any()))
            .thenReturn(List.of(dbArticle));
        when(articleRepository.findAllByIdIsInAndTypeIsInOrderByCreationDateTimeDesc(
            anySet(), anySet(), any()))
            .thenReturn(List.of(dbArticle));
        final List<ArticleListElementResponse> expected = new ArrayList<>();
        expected.add(ArticleListElementResponse.builder()
            .id(1)
            .title("title")
            .type(ArticleType.ARTICLE)
            .members(new Member[0])
            .marks(new MarkResponse[0])
            .build());

        final List<ArticleListElementResponse> actual = articleService.getArticles("login", marksId, articleType,
            ownerType, searchLine, searchType, page, size);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnArticleListFilterQuestions() {
        final Article dbArticle = new Article();
        dbArticle.setId(1);
        dbArticle.setTitle("title");
        dbArticle.setType(ArticleType.ARTICLE);
        final List<Article> articles = new ArrayList<>();
        articles.add(dbArticle);
        final ArticleMark articleMark = new ArticleMark();
        articleMark.setId(1);
        articleMark.setArticleId(1);
        articleMark.setMarkId(1);
        final List<ArticleMark> articleMarks = new ArrayList<>();
        articleMarks.add(articleMark);
        final UserArticle userArticle = new UserArticle();
        userArticle.setUserLogin("login");
        userArticle.setArticleId(1);
        userArticle.setId(1);
        userArticle.setRole(ArticleUserRole.ARTICLE_CREATOR);
        final Integer[] marksId = new Integer[]{1, 2};
        final String articleType = ArticleType.QUESTION;
        final String ownerType = "OWN";
        final String searchLine = "";
        final String searchType = "by_title";
        final int page = 1;
        final int size = 10;

        when(articleRepository.findAll())
            .thenReturn(articles);
        when(articleMarkRepository.findAll())
            .thenReturn(articleMarks);
        when(articleMarkRepository.findAllByMarkIdIsIn(Arrays.asList(marksId)))
            .thenReturn(articleMarks);
        when(userArticleRepository.findAllByUserLoginAndRoleIsIn(anyString(), anyList()))
            .thenReturn(List.of(userArticle));
        when(userArticleRepository.findAllByUserLoginContains(anyString()))
            .thenReturn(List.of(userArticle));
        when(articleRepository.findAllByIdIsInAndTypeIsInAndTitleContainsOrderByCreationDateTimeDesc(
            anySet(), anySet(), anyString(), any()))
            .thenReturn(List.of(dbArticle));
        when(articleRepository.findAllByIdIsInAndTypeIsInOrderByCreationDateTimeDesc(
            anySet(), anySet(), any()))
            .thenReturn(List.of(dbArticle));
        final List<ArticleListElementResponse> expected = new ArrayList<>();
        expected.add(ArticleListElementResponse.builder()
            .id(1)
            .title("title")
            .type(ArticleType.ARTICLE)
            .members(new Member[0])
            .marks(new MarkResponse[0])
            .build());

        final List<ArticleListElementResponse> actual = articleService.getArticles("login", marksId, articleType,
            ownerType, searchLine, searchType, page, size);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnArticleListFilterAll() {
        final Article dbArticle = new Article();
        dbArticle.setId(1);
        dbArticle.setTitle("title");
        dbArticle.setType(ArticleType.ARTICLE);
        final List<Article> articles = new ArrayList<>();
        articles.add(dbArticle);
        final ArticleMark articleMark = new ArticleMark();
        articleMark.setId(1);
        articleMark.setArticleId(1);
        articleMark.setMarkId(1);
        final List<ArticleMark> articleMarks = new ArrayList<>();
        articleMarks.add(articleMark);
        final UserArticle userArticle = new UserArticle();
        userArticle.setUserLogin("login");
        userArticle.setArticleId(1);
        userArticle.setId(1);
        userArticle.setRole(ArticleUserRole.ARTICLE_CREATOR);
        final Integer[] marksId = new Integer[]{1, 2};
        final String articleType = "ALL";
        final String ownerType = "OWN";
        final String searchLine = "";
        final String searchType = "by_title";
        final int page = 1;
        final int size = 10;

        when(articleRepository.findAll())
            .thenReturn(articles);
        when(articleMarkRepository.findAll())
            .thenReturn(articleMarks);
        when(articleMarkRepository.findAllByMarkIdIsIn(Arrays.asList(marksId)))
            .thenReturn(articleMarks);
        when(userArticleRepository.findAllByUserLoginAndRoleIsIn(anyString(), anyList()))
            .thenReturn(List.of(userArticle));
        when(userArticleRepository.findAllByUserLoginContains(anyString()))
            .thenReturn(List.of(userArticle));
        when(articleRepository.findAllByIdIsInAndTypeIsInAndTitleContainsOrderByCreationDateTimeDesc(
            anySet(), anySet(), anyString(), any()))
            .thenReturn(List.of(dbArticle));
        when(articleRepository.findAllByIdIsInAndTypeIsInOrderByCreationDateTimeDesc(
            anySet(), anySet(), any()))
            .thenReturn(List.of(dbArticle));
        final List<ArticleListElementResponse> expected = new ArrayList<>();
        expected.add(ArticleListElementResponse.builder()
            .id(1)
            .title("title")
            .type(ArticleType.ARTICLE)
            .members(new Member[0])
            .marks(new MarkResponse[0])
            .build());

        final List<ArticleListElementResponse> actual = articleService.getArticles("login", marksId, articleType,
            ownerType, searchLine, searchType, page, size);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnArticleById() {
        final Article dbArticle = new Article();
        dbArticle.setId(1);
        dbArticle.setTitle("title");
        dbArticle.setType(ArticleType.ARTICLE);
        final UserArticle userArticle = new UserArticle();
        userArticle.setUserLogin("login");
        userArticle.setArticleId(1);
        userArticle.setId(1);
        userArticle.setRole(ArticleUserRole.ARTICLE_CREATOR);
        final ArticlePart articlePart = new ArticlePart();
        articlePart.setId(1);
        articlePart.setSequenceNumber(1);
        articlePart.setArticleId(1);
        articlePart.setType(ArticlePartType.TEXT);
        articlePart.setText("text");
        final ArticleMark articleMark = new ArticleMark();
        articleMark.setId(1);
        articleMark.setArticleId(1);
        articleMark.setMarkId(1);
        final Mark mark = new Mark();
        mark.setId(1);
        mark.setType(MarkType.CUSTOM);
        mark.setCreator("login");
        mark.setTitle("title");
        mark.setDescription("description");

        final ArticleResponse expected = ArticleResponse.builder()
            .id(1)
            .title("title")
            .type(ArticleType.ARTICLE)
            .marks(new MarkResponse[]{
                MarkResponse.builder()
                    .id(1)
                    .title("title")
                    .type(MarkType.CUSTOM)
                    .description("description")
                    .creator("login")
                    .build()

            })
            .articleParts(new ArticlePartResponse[]{
                ArticlePartResponse.builder()
                    .id(1)
                    .sequenceNumber(1)
                    .text("text")
                    .type(ArticlePartType.TEXT)
                    .build()
            })
            .members(new Member[0])
            .tests(new TestTitleResponse[0])
            .build();

        when(articleRepository.findById(1))
            .thenReturn(Optional.of(dbArticle));
        when(articlePartRepository.findByArticleIdOrderBySequenceNumberAsc(1))
            .thenReturn(List.of(articlePart));
        when(articleMarkRepository.findByArticleId(1))
            .thenReturn(List.of(articleMark));
        when(articleMarkRepository.findByArticleId(1))
            .thenReturn(List.of(articleMark));
        when(markRepository.getById(1))
            .thenReturn(mark);

        final ArticleResponse actual = articleService.getArticle("login", 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnArticleListSearchByLogin() {
        final Article dbArticle = new Article();
        dbArticle.setId(1);
        dbArticle.setTitle("title");
        dbArticle.setType(ArticleType.ARTICLE);
        final List<Article> articles = new ArrayList<>();
        articles.add(dbArticle);
        final ArticleMark articleMark = new ArticleMark();
        articleMark.setId(1);
        articleMark.setArticleId(1);
        articleMark.setMarkId(1);
        final List<ArticleMark> articleMarks = new ArrayList<>();
        articleMarks.add(articleMark);
        final UserArticle userArticle = new UserArticle();
        userArticle.setUserLogin("login");
        userArticle.setArticleId(1);
        userArticle.setId(1);
        userArticle.setRole(ArticleUserRole.ARTICLE_CREATOR);
        final Integer[] marksId = new Integer[]{1, 2};
        final String articleType = ArticleType.ARTICLE;
        final String ownerType = "OWN";
        final String searchLine = "login";
        final String searchType = "by_login";
        final int page = 1;
        final int size = 10;

        when(articleRepository.findAll())
            .thenReturn(articles);
        when(articleMarkRepository.findAll())
            .thenReturn(articleMarks);
        when(articleMarkRepository.findAllByMarkIdIsIn(Arrays.asList(marksId)))
            .thenReturn(articleMarks);
        when(userArticleRepository.findAllByUserLoginAndRoleIsIn(anyString(), anyList()))
            .thenReturn(List.of(userArticle));
        when(userArticleRepository.findAllByUserLoginContains(anyString()))
            .thenReturn(List.of(userArticle));
        when(articleRepository.findAllByIdIsInAndTypeIsInAndTitleContainsOrderByCreationDateTimeDesc(
            anySet(), anySet(), anyString(), any()))
            .thenReturn(List.of(dbArticle));
        when(articleRepository.findAllByIdIsInAndTypeIsInOrderByCreationDateTimeDesc(
            anySet(), anySet(), any()))
            .thenReturn(List.of(dbArticle));
        final List<ArticleListElementResponse> expected = new ArrayList<>();
        expected.add(ArticleListElementResponse.builder()
            .id(1)
            .title("title")
            .type(ArticleType.ARTICLE)
            .members(new Member[0])
            .marks(new MarkResponse[0])
            .build());

        final List<ArticleListElementResponse> actual = articleService.getArticles("login", marksId, articleType,
            ownerType, searchLine, searchType, page, size);

        assertThat(actual).isEqualTo(expected);
    }

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

}
