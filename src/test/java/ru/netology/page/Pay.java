package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.DataHelper.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Pay {
    private SelenideElement card = $("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement month = $("[placeholder=\"08\"]");
    private SelenideElement year = $("[placeholder=\"22\"]");
    //private SelenideElement name = $(withText("Владелец")).$("[class=input__control]");
    private SelenideElement name = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvv = $("[placeholder=\"999\"]");
    private SelenideElement button = $(withText("Продолжить"));
    private SelenideElement goodAuth = $(withText("Операция одобрена"));
    private SelenideElement errorAuth = $(withText("Ошибка"));

    public void goodAuth(DataHelper.AuthInfo info){
        card.setValue(info.getCard());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        name.setValue(info.getName());
        cvv.setValue(info.getCvv());
        button.click();
        goodAuth.shouldBe(visible, Duration.ofSeconds(15));


    }

    public void blockedAuth(DataHelper.AuthInfo info){
        card.setValue(info.getCard());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        name.setValue(info.getName());
        cvv.setValue(info.getCvv());
        button.click();
        errorAuth.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void rusAuth(DataHelper.AuthInfo getRussianName){
        card.setValue(getRussianName.getCard());
        month.setValue(getRussianName.getMonth());
        year.setValue(getRussianName.getYear());
        name.setValue(getRussianName.getName());
        cvv.setValue(getRussianName.getCvv());
        button.click();
        errorAuth.shouldBe(visible, Duration.ofSeconds(15));
    }
}
