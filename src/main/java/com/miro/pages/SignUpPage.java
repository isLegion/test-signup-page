package com.miro.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.miro.properties.TestConfig.BASE_CONFIG;
import static com.miro.utils.CommonHelper.verifyTextContains;

import com.codeborne.selenide.Condition;
import com.miro.data.ErrorMessages;
import com.miro.data.HowDidYouHear;
import com.miro.data.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignUpPage {

    private static final By nameInput = By.cssSelector("[data-autotest-id='mr-form-signup-name-1']");
    private static final By emailInput = By.cssSelector("[data-autotest-id='mr-form-signup-email-1']");
    private static final By passwordInput = By.cssSelector("[data-autotest-id='mr-form-signup-password-1']");

    private static final By sourceMiroSelector = By.cssSelector(".speero-trig-dropdown");
    private static final By sourceMiroItem = By.cssSelector(".speero-dropdown__menu-item");
    private static final By selectedSourceValue = By.cssSelector(".speero-dropdown__title");

    private static final By miroTermsPolicyCheckbox = By.cssSelector("[for='signup-terms']");
    private static final By miroNewsCheckbox = By.cssSelector("[data-autotest-id='mr-form-signup-subscribe-1']");

    private static final By getStartedButton = By.cssSelector("[data-autotest-id='mr-form-signup-btn-start-1']");

    private static final By errorMessage = By.cssSelector("[role='alert']");
    private static final By invalidEmail = By.cssSelector("[data-autotest-id='mr-error-emailformat-1']");
    private static final By weakPasswordError = By.cssSelector("[data-text-weak='So-so password']");

    private static final By successRegistrationHeader = By.cssSelector(".signup__title-form");

    @Step("Type name {name}")
    public SignUpPage typeName(String name) {
        $(nameInput).clear();
        $(nameInput).sendKeys(name);
        return this;
    }

    @Step("Type email {email}")
    public SignUpPage typeEmail(String email) {
        $(emailInput).clear();
        $(emailInput).sendKeys(email);
        return this;
    }

    @Step("Type password {password}")
    public SignUpPage typePassword(String password) {
        $(passwordInput).clear();
        $(passwordInput).sendKeys(password);
        return this;
    }

    @Step("Terms and privacy policy is {termsCheck}")
    public SignUpPage checkUncheckTerms(Boolean termsCheck) {
        if (termsCheck) {
            $(miroTermsPolicyCheckbox).click();
        }
        return this;
    }

    @Step(" Receive News and Updates is {termsCheck}")
    public SignUpPage checkUncheckNewsUpdates(Boolean newsCheck) {
        if (newsCheck) {
            $(miroNewsCheckbox).click();
        }
        return this;
    }

    @Step()
    public SignUpPage fillAllRequiredFields(User user) {
        typeName(user.getName())
            .typeEmail(user.getEmail())
            .typePassword(user.getPassword());
        return this;
    }

    @Step("Click on Get Started Mow button")
    public SignUpPage clickGetStartedButton() {
        $(getStartedButton).click();
        return this;
    }

    @Step("Verify validation message {errorMessage} is correct")
    public SignUpPage checkValidationMessage(String message) {
        $(errorMessage).shouldHave(Condition.text(message));
        return this;
    }

    @Step("Verify email validation message is correct")
    public SignUpPage checkInvalidEmailMessage() {
        $(invalidEmail).shouldHave(Condition.text(ErrorMessages.INVALID_EMAIL.toString()));
        return this;
    }

    @Step("Verify password validation message is correct")
    public SignUpPage checkWeakPasswordMessage() {
        $(weakPasswordError).shouldHave(Condition.text(ErrorMessages.SECURE_PASSWORD.toString()));
        return this;
    }

    @Step("Verify success registration page is opened")
    public void checkSuccessRegistrationPage() {
        $(successRegistrationHeader).shouldHave(Condition.text("Check your email"));
        verifyTextContains(getWebDriver().getCurrentUrl(), "email-confirm");
    }

    @Step("Select How did you hear about Miro {option}")
    public SignUpPage selectHowDidYouHearAboutMiro(HowDidYouHear option) {
        $(sourceMiroSelector).click();
        $$(sourceMiroItem).filterBy(Condition.text(option.toString())).first().scrollTo().click();
        $(selectedSourceValue).shouldHave(Condition.text(option.toString()));
        return this;
    }

    @Step("Open signup page")
    public SignUpPage openPage() {
        open(BASE_CONFIG.baseUrl());
        return this;
    }

}
