package tests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;


public class BaseSteps {
    @Step("Перейти на GitHub")
    public void openGitHub(String GITHUB_URL) {
        open(GITHUB_URL);
    }

    @Step("Авторизироваться")
    public void AuthUser(String GITHUB_LOGIN_URL,String USERNAME, String PASSWORD) {
        open(GITHUB_LOGIN_URL);
        $("#login_field").sendKeys(USERNAME);
        $("#password").sendKeys(PASSWORD);
        $(new By.ByName("commit")).click();
    }

    @Step("Перейти в репозиторий")
    public void openRepository(String GITHUB_REPOSITORY) {
        open(GITHUB_REPOSITORY);
    }

    @Step("Создать новую задачу")
    public void createNewIssue(String ISSUE_NAME) {
        $("a.btn.btn-primary").click();
        $("input[name='issue[title]']").sendKeys(ISSUE_NAME);
        $("#new_issue").submit();
    }

    @Step("Проверить наличие задачи с именем")
    public void assertIssueName(String ISSUE_NAME) {
        $("span.js-issue-title").shouldHave(text(ISSUE_NAME));
    }

    @Step("Проверить наличие задачи по апи")
    public Issue assertIssueWithAPI(String TOKEN, String REPOSITORY) {
        final Issue issue = new Issue();
        int issueNum = Integer
                .parseInt($(".js-issue-title").sibling(0).getText().replace("#", ""));
        // @formatter:off
        return given()
                 .filter(new AllureRestAssured())
                 .header("Authorization", "token" + TOKEN)
                 .baseUri("https;//api.github.com")
        .when()
                 .get("https://github.com" + REPOSITORY + "/" + issueNum)
        .then()
                 .statusCode(200)
                 .log()
                 .all()
        .extract()
                 .as(Issue.class);
        // @formatter:on

    }

}

