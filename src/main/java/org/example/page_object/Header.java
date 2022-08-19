package org.example.page_object;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.*;

public class Header {
    @FindBy(how = How.XPATH, using = ".//a[@href='/']")
    private SelenideElement constructorButton;

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement logoButton;

    @FindBy(how = How.XPATH, using = ".//a[@href='/account']")
    private SelenideElement accountButton;

    public LoginPage accountButtonClickWhenNotLogged() {
        accountButton.click();
        return page(LoginPage.class);
    }

    public AccountPage accountButtonClickWhenLogged() {
        accountButton.click();
        return page(AccountPage.class);
    }

    public ConstructorPage constructorButtonClick() {
        constructorButton.click();
        return page(ConstructorPage.class);
    }

    public ConstructorPage logoButtonClick() {
        logoButton.click();
        return page(ConstructorPage.class);
    }
}
