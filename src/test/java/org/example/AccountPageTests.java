package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.page_object.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.example.helpers.Url.*;

public class AccountPageTests extends AbstractTest {
    private ConstructorPage constructorPage;
    private AccountPage accountPage;
    private LoginPage loginPage;
    private Map<String, String> tokens;

    @Before
    public void setUp() {
        String name = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = faker.bothify("?#?#?#?#");
        userClient.registerUser(name, email, password);
        tokens = userClient.getTokens(email, password);
        constructorPage = open(CONSTRUCTOR, ConstructorPage.class);
        localStorage().setItem("accessToken", tokens.get("accessToken"));
        localStorage().setItem("refreshToken", tokens.get("refreshToken"));
        constructorPage.waitToLoadConstructorPage();
    }

    @After
    public void clean() {
        userClient.deleteUser(tokens.get("accessToken"));
        closeWebDriver();
    }

    @Test
    @DisplayName("Enter account from header button")
    public void enterAccountPageFromHeaderButton() {
        accountPage = constructorPage.accountButtonClickWhenLogged();
        accountPage.waitForLoadAccountPage();
    }

    @Test
    @DisplayName("Enter constructor from account page via header \"Constructor\" button")
    public void enterConstructorFromAccountViaConstructorButton() {
        accountPage = constructorPage.accountButtonClickWhenLogged();
        accountPage.waitForLoadAccountPage();
        constructorPage = accountPage.constructorButtonClick();
        constructorPage.waitToLoadConstructorPage();
    }

    @Test
    @DisplayName("Enter constructor from account page via logo")
    public void enterConstructorFromAccountViaLogo() {
        accountPage = constructorPage.accountButtonClickWhenLogged();
        accountPage.waitForLoadAccountPage();
        constructorPage = accountPage.logoButtonClick();
        constructorPage.waitToLoadConstructorPage();
    }

    @Test
    @DisplayName("Exit from account via \"Exit\" button")
    public void exitAccountViaExitButton() {
        accountPage = constructorPage.accountButtonClickWhenLogged();
        accountPage.waitForLoadAccountPage();
        loginPage = accountPage.exitButtonClick();
        loginPage.waitForLoadLoginPage();
    }
}
