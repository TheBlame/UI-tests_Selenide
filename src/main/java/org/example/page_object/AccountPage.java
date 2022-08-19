package org.example.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.*;

public class AccountPage extends Header {
    @FindBy(how = How.CSS, using = ".Account_button__14Yp3.text.text_type_main-medium.text_color_inactive")
    private SelenideElement exitButton;

    @FindBy(how = How.CSS, using = "div li:nth-child(1) input")
    private SelenideElement nameField;

    public SelenideElement getNameField() {
        return nameField;
    }

    public void waitForLoadAccountPage() {
        exitButton.shouldBe(Condition.visible, ofSeconds(5));
    }

    public LoginPage exitButtonClick() {
        exitButton.click();
        return page(LoginPage.class);
    }
}
