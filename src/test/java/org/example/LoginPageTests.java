package org.example;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.example.page_object.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.example.helpers.Utils.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.example.helpers.Url.*;

public class LoginPageTests {
    private static final Faker FAKER = new Faker();
    private String name;
    private String email;
    private String password;
    private ConstructorPage constructorPage;
    private RegisterPage registerPage;
    private PasswordRestorePage passwordRestorePage;
    private LoginPage loginPage;

    private String token;

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        System.getProperty("webdriver.chrome.driver");
        name = FAKER.name().username();
        email = FAKER.internet().emailAddress();
        password = FAKER.bothify("?#?#?#?#");
        registerUser(name, email, password);
    }

    @After
    public void clean() {
        deleteUser(token);
        closeWebDriver();
    }

    @Test
    @DisplayName("Login from constructor \"Enter to account\" button")
    public void loginFromConstructorEnterButton() {
        constructorPage = open(CONSTRUCTOR, ConstructorPage.class);
        constructorPage.waitToLoadConstructorPage();
        loginPage = constructorPage.enterToAccountButtonClick();
        commonSteps();
    }

    @Test
    @DisplayName("Login from header account button")
    public void loginFromHeaderAccountButton() {
        constructorPage = open(CONSTRUCTOR, ConstructorPage.class);
        constructorPage.waitToLoadConstructorPage();
        loginPage = constructorPage.accountButtonClickWhenNotLogged();
        commonSteps();
    }

    @Test
    @DisplayName("Login from register page \"Enter\" link")
    public void loginFromRegisterPageLink() {
        registerPage = open(REGISTER, RegisterPage.class);
        registerPage.waitForLoadRegisterPage();
        loginPage = registerPage.enterLinkClick();
        commonSteps();
    }

    @Test
    @DisplayName("Login from password restore page \"Enter\" link")
    public void loginFromPasswordRestorePageLink() {
        passwordRestorePage = open(FORGOT_PASSWORD, PasswordRestorePage.class);
        passwordRestorePage.waitForLoadPasswordRestorePage();
        loginPage = passwordRestorePage.enterLinkClick();
        commonSteps();
    }

    public void commonSteps() {
        loginPage.waitForLoadLoginPage();
        loginPage.emailFieldInput(email);
        loginPage.passwordFieldInput(password);
        ConstructorPage constructorPage = loginPage.enterButtonClick();
        constructorPage.waitToLoadConstructorPage();
        token = localStorage().getItem("accessToken");
        assertThat(token, notNullValue());
    }
}
