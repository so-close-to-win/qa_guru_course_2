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

    // �������� ������ ������ �� ������� �������
    @Test
    void enterSomeYandexTabs() {
        // ������� �������� �������
        open("https://yandex.ru/");

        // ������� �� ������� "�����", ��������� ������� �� ������ URL
        $("[data-id=\"video\"]").click();
        switchTo().window(1);
        String url = getWebDriver().getCurrentUrl();
        assert url.equals("https://yandex.ru/video");
        WebDriverRunner.closeWindow();
        switchTo().window(0);

        // ������� �� ������� "��������", ��������� ������� �� ������ URL
        $("[data-id=\"images\"]").click();
        switchTo().window(1);
        url = getWebDriver().getCurrentUrl();
        assert url.equals("https://yandex.ru/images/");
        WebDriverRunner.closeWindow();
        switchTo().window(0);

        // ������� �� ������� "�������", ��������� ������� �� ������ URL
        $("[data-id=\"news\"]").click();
        switchTo().window(1);
        url = getWebDriver().getCurrentUrl();
        assert url.equals("https://yandex.ru/news/");
        WebDriverRunner.closeWindow();
        switchTo().window(0);


        // ������� �� ������� "����������", ��������� ������� �� ������ URL
        $("[data-id=\"translate\"]").click();
        switchTo().window(1);
        url = getWebDriver().getCurrentUrl();
        assert url.equals("https://translate.yandex.ru/");
        WebDriverRunner.closeWindow();
        switchTo().window(0);

        // ������� �� ������� "����.��", ��������� ������� �� ������ URL
        $("[data-id=\"autoru\"]").click();
        switchTo().window(1);
        url = getWebDriver().getCurrentUrl();
        assert url.equals("https://auto.ru/?from=yatab&utm_source=tab-yandex-glavnaya&utm_content=web_yatab");
        WebDriverRunner.closeWindow();
        switchTo().window(0);

        // ������� �� ������� "������������", ��������� ������� �� ������ URL
        $("[data-id=\"realty\"]").click();
        switchTo().window(1);
        url = getWebDriver().getCurrentUrl();
        assert url.equals("https://realty.yandex.ru/?from=yatab&utm_source=tab-yandex-glavnaya&utm_content=web_yatab");
        WebDriverRunner.closeWindow();
        switchTo().window(0);
    }

    // ����� ������ � ������.�������
    @Test
    void addToCompare() {
        // ������� �������� ������.������
        open("https://yandex.ru/market");

        // ����� "������� Braun"
        $(By.id("header-search")).setValue("������� Braun").pressEnter();

        // �� ���� �����
        sleep(20000);

        // ��������� ���������� "�� ��������"
        $("[data-autotest-id=\"quality\"]").click();

        // sleep(200000);

        // ������� ����� �� ������
        $(byTitle("��������� ������� Braun MQ 3145")).click();
    }

    @Test
    void authAccount() {
        // ������� �������� �������
        open("https://yandex.ru/");

        // ������ �����������
        $("[data-statlog-showed=\"1\"]").click();

        // ������� �� ������� �����������
        switchTo().window(1);

        // ������ �����������
        $("#passp-field-login").setValue("test.abramova");
        $("[type=\"submit\"]").click();
        $("#passp-field-passwd").setValue("QaAbramova");
        $("[type=\"submit\"]").click();
       // $("data-t=\"email_skip\"").click();

        // ��������� �������� �����������
        $(".mail-NestedList-Item-Name").shouldHave(text("��������"));
    }

    @Test
    void searchWithHint() {
        // ������� �������� �������
        open("https://yandex.ru/");

        // ��������� ����� ���������
        String text = $("[tabindex=\"0\"]").getText();

        // ������ �� ���������
        $(".home-link.home-link_pseudo_yes.home-link_gray_yes.home-arrow__sample-link").click();

        // ��������� ����������� ���������
        $(".input__control.mini-suggest__input").shouldHave(value(text));
    }

    @Test
    void switchCity() {
        // ������� �������� �������
        open("https://yandex.ru/");

        // ������� � ���������
        $("[data-statlog=\"head.settings\"]").click();
        $("[aria-label=\"�������� �����\"]").click();

        // �������� �����
        $("#city__front-input").clear();
        $("#city__front-input").setValue("�����").pressEnter();
        
        // ���������, ��� ������������ ����� ���������� ������
        $(".region__cityname_text").shouldHave(text("�����"));

    }


}
