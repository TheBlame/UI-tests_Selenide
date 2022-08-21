package org.example.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.*;

public class ConstructorPage extends Header {
    @FindBy(how = How.CSS, using = "section.BurgerIngredients_ingredients__1N8v2 div:nth-child(2) div:nth-child(1)")
    private SelenideElement scrollToBunButton;

    @FindBy(how = How.CSS, using = "section.BurgerIngredients_ingredients__1N8v2 div:nth-child(2) div:nth-child(2)")
    private SelenideElement scrollToSauceButton;

    @FindBy(how = How.CSS, using = "section.BurgerIngredients_ingredients__1N8v2 div:nth-child(2) div:nth-child(3)")
    private SelenideElement scrollToFillingButton;

    @FindBy(how = How.CSS, using = ".button_button__33qZ0")
    private SelenideElement enterToAccountButton;

    @FindBy(how = How.CSS, using = ".tab_tab_type_current__2BEPc")
    private SelenideElement selectedCategory;

    public LoginPage enterToAccountButtonClick() {
        enterToAccountButton.click();
        return page(LoginPage.class);
    }

    public SelenideElement getSelectedCategory() {
        return selectedCategory;
    }

    public void waitToLoadConstructorPage() {
        scrollToBunButton.shouldBe(Condition.visible, ofSeconds(5));
    }

    public void scrollToBunButtonClick() {
        scrollToBunButton.click();
    }

    public void scrollToSauceButtonClick() {
        scrollToSauceButton.click();
    }

    public void scrollToFillingButtonClick() {
        scrollToFillingButton.click();
    }
}
