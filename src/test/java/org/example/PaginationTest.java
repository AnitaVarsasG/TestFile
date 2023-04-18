package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PaginationTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    void getNumberTest() {
        List<Integer> resultList = new ArrayList<>();
        Pagination pagination = new Pagination(driver);
        pagination.navigate();

        do {
            pagination.getNumberFromTable(resultList);
            if (pagination.isNextButtonClickable()) {
                break;
            }
            pagination.clickNextArrow();
        } while (true);

        for (int i = 0; i < resultList.size()-1; i++) {
            Assertions.assertTrue(resultList.get(i).equals(resultList.get(i+1)-1));
        }
    }
}
