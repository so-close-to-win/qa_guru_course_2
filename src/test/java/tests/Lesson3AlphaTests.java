package tests;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.hc.core5.util.Asserts;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;


public class Lesson3AlphaTests {

    // Открыть архивные депозиты и посчитать количество
    @Test
    void homeWorkTest() {
        // Открыть страницу АльфаБанка
        open("https://alfabank.ru/");

        // Перейти в архивные депозиты
        $(byTitle("Вклады")).click();
        $(byTitle("Депозиты")).click();
        $(byLinkText("Архивные депозиты")).click();

        // Проверить количество депозитов
        $$(".product-cell__cell").shouldBe(CollectionCondition.size(3));
    }

    @Test
    void anotherGoToPage() {
        // Открыть страницу АльфаБанка
        open("https://alfabank.ru/");

        // Перейти на вкладку "Вклады"
        $(byTitle("Вклады")).click();

        // Перейти на "страхование вкладов"
        $(".selected").sibling(4).click();

        // Проверить переход
        $(".frame-head.row.smooth-static").$("h1").shouldHave(text("Страхование вкладов"));

    }

}
