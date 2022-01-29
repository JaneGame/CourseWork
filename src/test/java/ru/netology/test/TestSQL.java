package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ru.netology.DataHelper.DataHelper;
import ru.netology.page.StartPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

public class TestSQL {



    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @SneakyThrows
    @Test
    public void approvedTest(){
        var login = open("http://localhost:8080", StartPage.class);
        var authInfo = DataHelper.getApprovedAuthInfo();
        var start = login.payStart();
        start.goodAuth(authInfo);
        Thread.sleep(15000);
        String actual = DataHelper.getStatus().getStatus();
        String expected = "APPROVED";
        Assertions.assertEquals(actual, expected);
    }

    @SneakyThrows
    @Test
    public void declinedTest() {
        var login = open("http://localhost:8080", StartPage.class);
        var authInfo = DataHelper.getDeclinedAuthInfo();
        var start = login.payStart();
        start.blockedAuth(authInfo);
        Thread.sleep(15000);
        String actual = DataHelper.getStatus().getStatus();
        String expected = "DECLINED";
        Assertions.assertEquals(actual, expected);
    }
}
