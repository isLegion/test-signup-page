package com.miro.utils;

import static org.assertj.core.api.Assertions.*;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class CommonHelper {

    private static final Faker faker = new Faker();

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateRandomName() {
        return faker.name().firstName();
    }

    public static String generateRandomPassword() {
        return faker.internet().password();
    }

    @Step("Verify that \"{actual}\" contains \"{containsString}\"")
    public static void verifyTextContains(final String actual, final String containsString) {
        assertThat(actual).contains(containsString);
    }
}
