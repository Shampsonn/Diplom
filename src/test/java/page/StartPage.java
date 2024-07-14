package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StartPage {

    private static final String BUY_BUTTON_TEXT = "Купить";
    private static final String BUY_CREDIT_BUTTON_TEXT = "Купить в кредит";

    private final SelenideElement paymentButton = $(byText(BUY_BUTTON_TEXT));
    private final SelenideElement creditButton = $(byText(BUY_CREDIT_BUTTON_TEXT));

    public PaymentPage navigateToPaymentPage() {
        paymentButton.click();
        return new PaymentPage();
    }

    public CreditPage navigateToCreditPage() {
        creditButton.click();
        return new CreditPage();
    }
}
