package org.example;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.example.helpers.UserClient;
import org.junit.BeforeClass;

public abstract class AbstractTest {
    public static Faker faker = new Faker();
    public static UserClient userClient = new UserClient();

    @BeforeClass
    public static void init() {
        System.getProperty("webdriver.chrome.driver", "");
        Configuration.browserSize = "1920x1080";
    }
}
