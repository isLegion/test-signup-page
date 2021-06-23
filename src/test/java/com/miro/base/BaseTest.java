package com.miro.base;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.miro.properties.TestConfig.BASE_CONFIG;
import static java.util.concurrent.TimeUnit.SECONDS;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.miro.pages.SignUpPage;
import com.miro.utils.RemoteDriverManager;
import com.miro.utils.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class BaseTest {

    protected SignUpPage signUpPage = new SignUpPage();

    @BeforeTest(alwaysRun = true)
    public void setup() {

        signUpPage.openPage();

        if(BASE_CONFIG.remoteDriver()) {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new RemoteDriverManager().createInstance();
            WebDriverRunner.setWebDriver(driver);
        }

        Configuration.timeout = BASE_CONFIG.timeout();
        getWebDriver().manage().timeouts().pageLoadTimeout(BASE_CONFIG.pageTimeout(), SECONDS);
        getWebDriver().manage().window().maximize();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterTest(alwaysRun = true)
    public void cleanUp() {
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().close();
    }
}
