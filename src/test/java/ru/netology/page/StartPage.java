package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class StartPage {
    private SelenideElement start = $(withText("Купить"));
    private SelenideElement pay = $(byText("Оплата по карте"));
    private SelenideElement startcredit = $(byText("Купить в кредит"));
    private SelenideElement credit = $(byText("Кредит по данным карты"));

    public Pay goToDebitCardPay(){
        start.click();
        pay.shouldBe(visible);
        return new Pay();
    }

    public Pay goToCreditCardPay(){
        startcredit.click();
        credit.shouldBe(visible);
        return new Pay();
    }

}
