package org.example.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;
import static java.time.Duration.*;

public class PasswordRestorePage {
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement enterLink;

    public LoginPage enterLinkClick() {
        enterLink.click();
        return page(LoginPage.class);
    }

    public void waitForLoadPasswordRestorePage() {
        enterLink.shouldBe(Condition.visible, ofSeconds(5));
    }
}
