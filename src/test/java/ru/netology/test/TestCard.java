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

    @Test
    public void approvedTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var fillInfo = DataHelper.getApprovedInfo();
        var start = login.goToDebitCardPay();
        start.fillValidForm(fillInfo);
        start.goGoodInfo();
    }

    @Test
    public void declinedTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var fillInfo = DataHelper.getDeclinedInfo();
        var start = login.goToDebitCardPay();
        start.fillInvalidForm(fillInfo);
        start.goErrorInfo();
    }

    @Test
    public void rusTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var fillInfo = DataHelper.getRussianName();
        var start = login.goToDebitCardPay();
        start.fillRusForm(fillInfo);
        start.goErrorInfo();
    }

    @Test
    public void noCardTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var fillInfo = DataHelper.getNoCardName();
        var start = login.goToDebitCardPay();
        start.fillInvalidForm(fillInfo);
        start.goErrorInfo();
    }

    @Test
    public void creditTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var fillInfo = DataHelper.getApprovedInfo();
        var start = login.goToCreditCardPay();
        start.fillValidForm(fillInfo);
        start.goGoodInfo();

    }


}
