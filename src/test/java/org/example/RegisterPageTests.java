package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.example.page_object.LoginPage;
import org.example.page_object.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.example.helpers.Utils.*;
import static org.example.helpers.Url.*;

public class RegisterPageTests {
    private static final Faker FAKER = new Faker();
    private final String invalidPassword = FAKER.bothify("?#?#?");
    private String name;
    private String email;
    private String validPassword;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        System.getProperty("webdriver.chrome.driver");
        name = FAKER.name().username();
        email = FAKER.internet().emailAddress();
        validPassword = FAKER.bothify("?#?#?#");
        registerPage = open(REGISTER, RegisterPage.class);
        registerPage.waitForLoadRegisterPage();
    }

    @After
    public void clean() {
        deleteUser(getTokens(email, validPassword).get("accessToken"));
        closeWebDriver();
    }

    @Test
    @DisplayName("Register with valid credentials")
    public void registerWithValidCredentials() {
        registerPage.nameFieldInput(name);
        registerPage.emailFieldInput(email);
        registerPage.passwordFieldInput(validPassword);
        LoginPage loginPage = registerPage.registerButtonClick();
        loginPage.waitForLoadLoginPage();
        loginPage.getEnterButton().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Register with invalid password")
    public void registerWithInvalidPassword() {
        registerPage.nameFieldInput(name);
        registerPage.emailFieldInput(email);
        registerPage.passwordFieldInput(invalidPassword);
        registerPage.registerButtonClick();
        registerPage.getIncorrectPasswordMessage().shouldBe(Condition.visible);
    }
}
