package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.DataHelper.DataHelper;
import ru.netology.page.StartPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestCard {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    private SelenideElement goodAuth = $(withText("Операция одобрена"));
    private SelenideElement errorAuth = $(withText("Ошибка"));

    @Test
    public void approvedTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var authInfo = DataHelper.getApprovedAuthInfo();
        var start = login.payStart();
        start.goodAuth(authInfo);
        goodAuth.shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void declinedTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var authInfo = DataHelper.getDeclinedAuthInfo();
        var start = login.payStart();
        start.blockedAuth(authInfo);
        errorAuth.shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void rusTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var authInfo = DataHelper.getRussianName();
        var start = login.payStart();
        start.rusAuth(authInfo);
        errorAuth.shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void noCardTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var authInfo = DataHelper.getNoCardName();
        var start = login.payStart();
        start.blockedAuth(authInfo);
        errorAuth.shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void creditTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var authInfo = DataHelper.getApprovedAuthInfo();
        var start = login.creditStart();
        start.goodAuth(authInfo);
        goodAuth.shouldBe(visible, Duration.ofSeconds(15));

    }


}