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

public class CreditPaymentTest {
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
    void shouldStatusBuyCreditValidActiveCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkApprovedForm();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }


    @Test
    void shouldStatusBuyCreditValidDeclinedCard() {
        CardInfo card = new CardInfo(getValidDeclinedCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkDeclinedForm();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditInvalidCard() {
        CardInfo card = new CardInfo(getInvalidNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkDeclinedForm();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditInvalidPatternCard() {
        CardInfo card = new CardInfo(getInvalidPatternNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkCardNumberError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditEmptyCard() {
        CardInfo card = new CardInfo(getEmptyNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkCardNumberError();
        assertNull(SQLHelper.getCreditStatus());
    }

    @Test
    void shouldBuyCreditZeroCard() {
        CardInfo card = new CardInfo(getZeroNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkCardNumberError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditInvalidMonthCardExpiredCardError() {
        CardInfo card = new CardInfo(getValidActiveCard(), getFirstMonth(), getCurrentYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkExpiredCardError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditInvalidMonth() {
        CardInfo card = new CardInfo(getValidActiveCard(), getInvalidMonth(), getCurrentYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkMonthError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditZeroMonth() {
        CardInfo card = new CardInfo(getValidActiveCard(), getZeroMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkMonthError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditEmptyMonth() {
        CardInfo card = new CardInfo(getValidActiveCard(), getEmptyMonth(), getNextYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkMonthError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditInvalidYearCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getPreviousYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkYearError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditEmptyYear() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getEmptyYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkYearError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditZeroYear() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getZeroYear(), getValidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkYearError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditRussianOwner() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getInvalidLocaleOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkOwnerError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditFirstNameOwner() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getInvalidOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkOwnerError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditEmptyOwner() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getEmptyOwner(), getValidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkOwnerError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditInvalidCVC() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getInvalidCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkCVCError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditEmptyCVC() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getEmptyCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkCVCError();
        assertNull(SQLHelper.getCreditStatus());
    }


    @Test
    void shouldBuyCreditZeroCVC() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getZeroCVC());
        val mainPage = new StartPage();
        mainPage.navigateToCreditPage().
                fillingForm(card).
                checkCVCError();
        assertNull(SQLHelper.getCreditStatus());
    }
}
