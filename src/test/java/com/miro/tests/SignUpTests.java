package com.miro.tests;

import static com.miro.utils.CommonHelper.generateRandomString;

import com.miro.base.BaseTest;
import com.miro.data.ErrorMessages;
import com.miro.data.HowDidYouHear;
import com.miro.data.User;
import com.miro.utils.CommonHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpTests extends BaseTest {

    private User user;
    private static final String name = CommonHelper.generateRandomName();
    private static final String email = CommonHelper.generateRandomEmail();
    private static final String password = CommonHelper.generateRandomPassword();

    @BeforeClass
    public void beforeClass() {
        user = User.builder().name(name).email(email).password(password).build();
    }

    @DataProvider
    public static Object[][] loadUserEmptyField() {
        return new Object[][]{
            {User.builder().name(name).email(email).password("").build(), ErrorMessages.EMPTY_PASSWORD},
            {User.builder().name(name).email("").password(password).build(), ErrorMessages.EMPTY_EMAIL},
            {User.builder().name("").email(email).password(password).build(), ErrorMessages.EMPTY_NAME},
            {User.builder().name(name).email(email).password(password).build(), ErrorMessages.NO_TERMS},
        };
    }

    @Test(dataProvider = "loadUserEmptyField")
    public void checkFieldValidationForEmptyValues(User user, ErrorMessages errorMessage) {

        signUpPage.fillAllRequiredFields(user)
            .clickGetStartedButton()
            .checkValidationMessage(errorMessage.toString());
    }

    @Test
    public void checkInvalidEmail() {
        signUpPage.fillAllRequiredFields(user)
            .typeEmail(generateRandomString(8))
            .checkUncheckTerms(true)
            .clickGetStartedButton()
            .checkInvalidEmailMessage();
    }

    @Test
    public void checkInvalidPassword() {
        signUpPage.fillAllRequiredFields(user)
            .typePassword(generateRandomString(3))
            .checkUncheckTerms(true)
            .clickGetStartedButton()
            .checkWeakPasswordMessage();
    }

    @Test(dependsOnMethods = "checkSuccessRegistrationWithAllFields")
    public void checkSuccessRegistrationWithRegisteredEmailError() {
        signUpPage.openPage()
            .fillAllRequiredFields(user)
            .checkUncheckTerms(true)
            .clickGetStartedButton()
            .checkValidationMessage(ErrorMessages.REGISTERED_EMAIL.toString())
            .typeEmail(CommonHelper.generateRandomEmail())
            .typePassword(CommonHelper.generateRandomPassword())
            .checkUncheckTerms(true)
            .clickGetStartedButton()
            .checkSuccessRegistrationPage();
    }

    @Test
    public void checkSuccessRegistrationWithAllFields() {
        signUpPage.openPage()
            .fillAllRequiredFields(user)
            .checkUncheckTerms(true)
            .checkUncheckNewsUpdates(true)
            .selectHowDidYouHearAboutMiro(HowDidYouHear.SOCIAL_MEDIA)
            .clickGetStartedButton()
            .checkSuccessRegistrationPage();
    }
}
