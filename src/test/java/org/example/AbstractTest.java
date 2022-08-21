package org.example;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.BeforeClass;

public abstract class AbstractTest {
    public static final Faker FAKER = new Faker();

    @BeforeClass
    public static void init() {
        System.getProperty("webdriver.chrome.driver", "");
        Configuration.browserSize = "1920x1080";
    }
}
