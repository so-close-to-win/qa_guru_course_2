package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static jdk.nashorn.internal.objects.NativeMath.random;


class LambdaAndSteps {

    private String USERNAME = "so-close-to-win";
    private String PASSWORD = "B5BfZXU9asZ4cLF";
    private String GITHUB_URL = "https://github.com";
    private String GITHUB_LOGIN = "/login";
    private String GITHUB_REPOSITORY = "/so-close-to-win/qa_guru_course_2/issues";
    private String ISSUE_NAME_STEPS = "Создание по степам3";
    private String ISSUE_NAME_LAMBDA = "Создание по лямбде";

    private BaseSteps steps = new BaseSteps();

    @AfterEach
    public void closeDriver() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Создание задачи по степам")
    void createIssueViaSteps() {
        Configuration.baseUrl = GITHUB_URL;
        steps.openGitHub(GITHUB_URL);
        steps.AuthUser(GITHUB_LOGIN, USERNAME, PASSWORD);
        steps.openRepository(GITHUB_REPOSITORY);
        steps.createNewIssue(ISSUE_NAME_STEPS);
        steps.assertIssueName(ISSUE_NAME_STEPS);
    }

    @Test
    void createIssueViaLambda() {
        Configuration.baseUrl = GITHUB_URL;

        step("Перейти на " + GITHUB_URL, ()-> {
            open(GITHUB_URL);
        });

        step("Авторизироваться", ()-> {
            open(GITHUB_LOGIN);
            $("#login_field").sendKeys(USERNAME);
            $("#password").sendKeys(PASSWORD);
            $(new By.ByName("commit")).click();
        });

        step("Перейти в репозиторий " + GITHUB_REPOSITORY, ()-> {
            open(GITHUB_REPOSITORY);
        });

        step("Создать новую задачу" + ISSUE_NAME_LAMBDA, ()->{
            //sleep(100000000);
            $("a.btn.btn-primary").click();
            $("input[name='issue[title]']").sendKeys(ISSUE_NAME_LAMBDA);
            $("#new_issue").submit();
        });

        step("Проверить наличие задачи с именем" + "'" + ISSUE_NAME_LAMBDA + "'", ()->{
            $("span.js-issue-title").shouldHave(text(ISSUE_NAME_LAMBDA));
        });
    }
}

class ApiTests {
    private String USERNAME = "so-close-to-win";
    private String PASSWORD = "B5BfZXU9asZ4cLF";
    private String GITHUB_URL = "https://github.com";
    private String GITHUB_LOGIN = "/login";
    private String GITHUB_REPOSITORY = "/so-close-to-win/qa_guru_course_2/issues";
    private String ISSUE_NAME_STEPS = "Создание по степам3";
    private String ISSUE_NAME_LAMBDA = "Создание по лямбде";
    private String TOKEN = "8a89ad4135d142ca1acad8d4ad72d4ccd801eeca";

    private BaseSteps steps = new BaseSteps();

    @BeforeEach
    void initLogger() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }

    @Test
    @DisplayName("Пользователь создает задачу и проверяет её наличие")
    void createIssueAndCheckWithApi() {
        Configuration.baseUrl = GITHUB_URL;
        steps.openGitHub(GITHUB_URL);
        steps.AuthUser(GITHUB_LOGIN, USERNAME, PASSWORD);
        steps.openRepository(GITHUB_REPOSITORY);
        ISSUE_NAME_STEPS = ISSUE_NAME_STEPS + random(8);
        steps.createNewIssue(ISSUE_NAME_STEPS);

        steps.assertIssueWithAPI(TOKEN, GITHUB_REPOSITORY);
    }


}
