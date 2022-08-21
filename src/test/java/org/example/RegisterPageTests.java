package org.example;

import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import org.example.page_object.LoginPage;
import org.example.page_object.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.example.helpers.Utils.*;
import static org.example.helpers.Url.*;

public class RegisterPageTests extends AbstractTest {

    private String name;
    private String email;
    private String password;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        name = FAKER.name().username();
        email = FAKER.internet().emailAddress();
        registerPage = open(REGISTER, RegisterPage.class);
        registerPage.waitForLoadRegisterPage();
    }

    @After
    public void clean() {
        deleteUser(getTokens(email, password).get("accessToken"));
        closeWebDriver();
    }

    @Test
    @DisplayName("Register with valid credentials")
    public void registerWithValidCredentials() {
        password = FAKER.bothify("?#?#?#");
        registerPage.nameFieldInput(name);
        registerPage.emailFieldInput(email);
        registerPage.passwordFieldInput(password);
        LoginPage loginPage = registerPage.registerButtonClick();
        loginPage.waitForLoadLoginPage();
        loginPage.getEnterButton().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Register with invalid password")
    public void registerWithInvalidPassword() {
        password = FAKER.bothify("?#?#?");
        registerPage.nameFieldInput(name);
        registerPage.emailFieldInput(email);
        registerPage.passwordFieldInput(password);
        registerPage.registerButtonClick();
        registerPage.getIncorrectPasswordMessage().shouldBe(Condition.visible);
    }
}
