package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.DataHelper.DataHelper;

import java.time.Duration;
import java.util.concurrent.Callable;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.awaitility.Awaitility.await;

public class Pay {
    private SelenideElement card = $("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement month = $("[placeholder=\"08\"]");
    private SelenideElement year = $("[placeholder=\"22\"]");
    private SelenideElement name = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvv = $("[placeholder=\"999\"]");
    private SelenideElement button = $(withText("Продолжить"));
    private SelenideElement goodPay = $(withText("Операция одобрена"));
    private SelenideElement errorPay = $(withText("Ошибка"));
    //private SelenideElement status = $(".notification_stick-to_right");

    public void fillValidForm(DataHelper.FillInfo info){
        card.setValue(info.getCard());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        name.setValue(info.getName());
        cvv.setValue(info.getCvv());
        button.click();


    }

    /*public boolean seeGoodOrBadStatus(){
        if(DataHelper.getStatus() == null){
            return false;
        } else {
            return true;
        }
    }*/

    public void fillInvalidForm(DataHelper.FillInfo info){
        card.setValue(info.getCard());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        name.setValue(info.getName());
        cvv.setValue(info.getCvv());
        button.click();

    }

    public void fillRusForm(DataHelper.FillInfo getRussianName){
        card.setValue(getRussianName.getCard());
        month.setValue(getRussianName.getMonth());
        year.setValue(getRussianName.getYear());
        name.setValue(getRussianName.getName());
        cvv.setValue(getRussianName.getCvv());
        button.click();

    }

    public void goGoodInfo(){
        goodPay.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void goErrorInfo(){
        errorPay.shouldBe(visible, Duration.ofSeconds(15));
    }
}
