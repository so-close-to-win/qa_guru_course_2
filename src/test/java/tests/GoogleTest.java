package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class GoogleTest {
    @Test
    void selenideSearchTest() {
        // Открыть google
        open("https://google.com");

        // Ввести Selenide в поиск
        $(byName("q")).setValue("Selenide").pressEnter();

        // Проверить, что Selenide появился в результатах поиска
        $("html").shouldHave(text("ru.selenide.org"));
    }

    @Test
    void selenideSearchTestX2() {
        // Открыть google
        open("https://google.com");

        // Ввести Покемоны в поиск
        $(byName("q")).setValue("Pokemon").pressEnter();

        // Проверить, что Покемоны появился в результатах поиска
        $("html").shouldHave(text("www.pokemon.com"));

        // Очистить поле поиска
        $(byName("q")).clear();

        // Ввести how work clear() selenide в поиск
        $(byName("q")).setValue("how work clear() selenide").pressEnter();

        // Проверить, что stackoverflow появился в результатах поиска
        $("html").shouldHave(text("stackoverflow.com"));
    }
}

class YandexTest {
    @Test
    void selenideYandexSearch() {
        // Открыть Яндекс
        open("https://yandex.ru/");

        // Ввести Покемоны в поиск
        $(byName("text")).setValue("Pokemon").pressEnter();

        // Проверить, что Покемоны появился в результатах поиска
        $("html").shouldHave(text("pokemon.com"));

        // Очистить поле поиска
        $(byName("text")).clear();

        // Ввести how work clear() selenide в поиск
        $(byName("text")).setValue("how work clear() selenide").pressEnter();

        // Проверить, что stackoverflow появился в результатах поиска
        $("html").shouldHave(text("stackoverflow.com"));
    }
}
