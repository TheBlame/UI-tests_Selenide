package org.example.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.*;

public class LoginPage extends Header {
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement registerLink;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement enterButton;

    @FindBy(how = How.CSS, using = "div fieldset:nth-child(1) input")
    private SelenideElement emailField;

    @FindBy(how = How.CSS, using = "div fieldset:nth-child(2) input")
    private SelenideElement passwordField;

    public void waitForLoadLoginPage() {
        enterButton.shouldBe(Condition.visible, ofSeconds(5));
    }

    public void emailFieldInput(String input) {
        emailField.setValue(input);
    }

    public void passwordFieldInput(String input) {
        passwordField.setValue(input);
    }

    public SelenideElement getEnterButton() {
        return enterButton;
    }

    public ConstructorPage enterButtonClick() {
        enterButton.click();
        return page(ConstructorPage.class);
    }
}
