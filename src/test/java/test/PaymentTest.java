package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.CardInfo;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
        SQLHelper.cleanDatabase();
    }


    @Test
    void shouldStatusBuyPaymentValidActiveCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkApprovedForm();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldStatusBuyPaymentValidDeclinedCard() {
        CardInfo card = new CardInfo(getValidDeclinedCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkDeclinedForm();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentInvalidCard() {
        CardInfo card = new CardInfo(getInvalidNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkDeclinedForm();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentInvalidPatternCard() {
        CardInfo card = new CardInfo(getInvalidPatternNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkCardNumberError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentEmptyCard() {
        CardInfo card = new CardInfo(getEmptyNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkCardNumberError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentZeroCard() {
        CardInfo card = new CardInfo(getZeroNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkCardNumberError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentInvalidMonthCardExpiredCardError() {
        CardInfo card = new CardInfo(getValidActiveCard(), getFirstMonth(), getCurrentYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkExpiredCardError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentInvalidMonth() {
        CardInfo card = new CardInfo(getValidActiveCard(), getInvalidMonth(), getCurrentYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkMonthError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentZeroMonth() {
        CardInfo card = new CardInfo(getValidActiveCard(), getZeroMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkMonthError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentEmptyMonth() {
        CardInfo card = new CardInfo(getValidActiveCard(), getEmptyMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkMonthError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentInvalidYearCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getPreviousYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkYearError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentEmptyYear() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getEmptyYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkYearError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentZeroYear() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getZeroYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkYearError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentRussianOwner() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getInvalidLocaleOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkOwnerError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentFirstNameOwner() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getInvalidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkOwnerError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentEmptyOwner() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getEmptyOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkOwnerError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentInvalidCVC() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getInvalidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkCVCError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentEmptyCVC() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getEmptyCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkCVCError();
        assertNull(SQLHelper.getPaymentStatus());
    }


    @Test
    void shouldBuyPaymentZeroCVC() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getZeroCVC());
        val mainPage = new StartPage();
        mainPage.navigateToPaymentPage().
                fillingForm(card).
                checkCVCError();
        assertNull(SQLHelper.getPaymentStatus());
    }
}
