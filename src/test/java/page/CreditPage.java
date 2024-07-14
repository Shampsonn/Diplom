package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreditPage {
    private final SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private final SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private final SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private final SelenideElement owner = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvc = $(byText("CVC/CVV")).parent().$(".input__control");
    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement cardNumberError = $(byText("Номер карты")).parent().$(".input__sub");
    private final SelenideElement monthError = $(byText("Месяц")).parent().$(".input__sub");
    private final SelenideElement yearError = $(byText("Год")).parent().$(".input__sub");
    private final SelenideElement expiredCardError = $(byText("Истек срок действия карты")).parent().$(".input__sub");
    private final SelenideElement ownerError = $(byText("Владелец")).parent().$(".input__sub");
    private final SelenideElement cvcError = $(byText("CVC/CVV")).parent().$(".input__sub");
    private final SelenideElement approvedForm = $(".notification_status_ok");
    private final SelenideElement declinedForm = $(".notification_status_error");

    public CreditPage fillingForm(CardInfo card) {
        cardNumber.setValue(card.getCardNumber());
        month.setValue(card.getMonth());
        year.setValue(card.getYear());
        owner.setValue(card.getOwner());
        cvc.setValue(card.getCardCVC());
        continueButton.click();
        return new CreditPage();
    }

    public void checkApprovedForm() {
        approvedForm.shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    public void checkDeclinedForm() {
        declinedForm.shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    public void checkCardNumberError() {
        cardNumberError.shouldBe(Condition.visible);
    }

    public void checkMonthError() {
        monthError.shouldBe(Condition.visible);
    }

    public void checkExpiredCardError() {
        expiredCardError.shouldBe(Condition.visible);
    }

    public void checkYearError() {
        yearError.shouldBe(Condition.visible);
    }

    public void checkOwnerError() {
        ownerError.shouldBe(Condition.visible);
    }

    public void checkCVCError() {
        cvcError.shouldBe(Condition.visible);
    }
}

