package tests;


import com.codeborne.selenide.WebDriverRunner;
import org.apache.hc.core5.util.Asserts;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

public class lesson2_SomeTests {

    // Проверка работа ссылок на сервисы Яндекса
    @Test
    void enterSomeYandexTabs() {
        // Открыть страницу Яндекса
        open("https://yandex.ru/");

        // Перейти на вкладку "Видео", проверить переход на нужный URL
        $("[data-id=\"video\"]").click();
        switchTo().window(1);
        String url = getWebDriver().getCurrentUrl();
        assert url.equals("https://yandex.ru/video");
        WebDriverRunner.closeWindow();
        switchTo().window(0);

        // Перейти на вкладку "Картинки", проверить переход на нужный URL
        $("[data-id=\"images\"]").click();
        switchTo().window(1);
        url = getWebDriver().getCurrentUrl();
        assert url.equals("https://yandex.ru/images/");
        WebDriverRunner.closeWindow();
        switchTo().window(0);

        // Перейти на вкладку "Новости", проверить переход на нужный URL
        $("[data-id=\"news\"]").click();
        switchTo().window(1);
        url = getWebDriver().getCurrentUrl();
        assert url.equals("https://yandex.ru/news/");
        WebDriverRunner.closeWindow();
        switchTo().window(0);


        // Перейти на вкладку "Переводчик", проверить переход на нужный URL
        $("[data-id=\"translate\"]").click();
        switchTo().window(1);
        url = getWebDriver().getCurrentUrl();
        assert url.equals("https://translate.yandex.ru/");
        WebDriverRunner.closeWindow();
        switchTo().window(0);

        // Перейти на вкладку "Авто.ру", проверить переход на нужный URL
        $("[data-id=\"autoru\"]").click();
        switchTo().window(1);
        url = getWebDriver().getCurrentUrl();
        assert url.equals("https://auto.ru/?from=yatab&utm_source=tab-yandex-glavnaya&utm_content=web_yatab");
        WebDriverRunner.closeWindow();
        switchTo().window(0);

        // Перейти на вкладку "Недвижимость", проверить переход на нужный URL
        $("[data-id=\"realty\"]").click();
        switchTo().window(1);
        url = getWebDriver().getCurrentUrl();
        assert url.equals("https://realty.yandex.ru/?from=yatab&utm_source=tab-yandex-glavnaya&utm_content=web_yatab");
        WebDriverRunner.closeWindow();
    }

    // Выбор товара в Яндекс.Маркете
    @Test
    void addToCompare() {
        // Открыть страницу Яндекс.Маркет
        open("https://yandex.ru/market");

        // Найти "Блендер Braun"
        $(By.id("header-search")).setValue("блендер Braun").pressEnter();

        // На ввод капчи
        sleep(20000);

        // Выполнить сортировку "по рейтингу"
        $("[data-autotest-id=\"quality\"]").click();

        // sleep(200000);

        // Выбрать товар из списка
        $(byTitle("Погружной блендер Braun MQ 3145")).click();
        WebDriverRunner.closeWindow();
    }

    @Test
    void authAccount() {
        // Открыть страницу Яндекса
        open("https://yandex.ru/");

        // Пройти авторизацию
        $("[data-statlog-showed=\"1\"]").click();

        // Переход на вкладку авторизации
        switchTo().window(1);

        // Пройти авторизацию
        $("#passp-field-login").setValue("test.abramova");
        $("[type=\"submit\"]").click();
        $("#passp-field-passwd").setValue("QaAbramova");
        $("[type=\"submit\"]").click();
       // $("data-t=\"email_skip\"").click();

        // Проверить успешную авторизацию
        $(".mail-NestedList-Item-Name").shouldHave(text("Входящие"));
        WebDriverRunner.closeWindow();
    }

    @Test
    void searchWithHint() {
        // Открыть страницу Яндекса
        open("https://yandex.ru/");

        // Сохранить текст подсказки
        String text = $("[tabindex=\"0\"]").getText();

        // Нажать на подсказку
        $(".home-link.home-link_pseudo_yes.home-link_gray_yes.home-arrow__sample-link").click();

        // Проверить подстановку подсказки
        $(".input__control.mini-suggest__input").shouldHave(value(text));
    }

    // Отключение персонифицированной рекламы
    @Test
    void turnAdSettingsOff() {
        // Открыть страницу Яндекса
        open("https://yandex.ru/");

        // Перейти в настройки рекламы
        $("[data-statlog=\"head.settings\"]").click();
        $("[aria-label=\"Настройки портала\"]").click();
        $("[data-statlog=\"tabs.adv\"]").click();

        // Снять галочки учета рекламы
        $(byName("yes_interest_ad")).click();
        $(byName("yes_geo_ad")).click();
        $("[type=\"submit\"]").click();

        // Задержка для редиректа (вопрос, как её избежать?)
        sleep(400);

        // Проверить редирект на главную страницу
        String url = getWebDriver().getCurrentUrl();
        assert url.equals("https://yandex.ru/");
        WebDriverRunner.closeWindow();
    }


}
