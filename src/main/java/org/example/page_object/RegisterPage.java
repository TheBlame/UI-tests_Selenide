package org.example.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.*;

public class RegisterPage {
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.CSS, using = ".input__error.text_type_main-default")
    private SelenideElement incorrectPasswordMessage;

    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement enterLink;

    @FindBy(how = How.CSS, using = "div fieldset:nth-child(1) input")
    private SelenideElement nameField;

    @FindBy(how = How.CSS, using = "div fieldset:nth-child(2) input")
    private SelenideElement emailField;

    @FindBy(how = How.CSS, using = "div fieldset:nth-child(3) input")
    private SelenideElement passwordField;

    public LoginPage enterLinkClick() {
        enterLink.click();
        return page(LoginPage.class);
    }

    public void nameFieldInput(String input) {
        nameField.setValue(input);
    }

    public void emailFieldInput(String input) {
        emailField.setValue(input);
    }

    public void passwordFieldInput(String input) {
        passwordField.setValue(input);
    }

    public LoginPage registerButtonClick() {
        registerButton.click();
        return page(LoginPage.class);
    }

    public SelenideElement getIncorrectPasswordMessage() {
        return incorrectPasswordMessage;
    }

    public void waitForLoadRegisterPage() {
        registerButton.shouldBe(Condition.visible, ofSeconds(5));
    }
}
