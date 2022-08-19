package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.example.page_object.ConstructorPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.example.helpers.Url.*;

public class ConstructorPageTests {
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        System.getProperty("webdriver.chrome.driver");
        constructorPage = open(CONSTRUCTOR, ConstructorPage.class);
        constructorPage.waitToLoadConstructorPage();
    }

    @After
    public void clean() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Check that \"Bun\" category can be selected")
    public void bunCategorySelect() {
        constructorPage.scrollToSauceButtonClick();
        constructorPage.scrollToBunButtonClick();
        constructorPage.getSelectedCategory().shouldBe(Condition.text("Булки"));
    }

    @Test
    @DisplayName("Check that \"Sauce\" category can be selected")
    public void sauceCategorySelect() {
        constructorPage.scrollToSauceButtonClick();
        constructorPage.getSelectedCategory().shouldBe(Condition.text("Соусы"));
    }

    @Test
    @DisplayName("Check that \"Filling\" category can be selected")
    public void fillingCategorySelect() {
        constructorPage.scrollToFillingButtonClick();
        constructorPage.getSelectedCategory().shouldBe(Condition.text("Начинки"));
    }
}
