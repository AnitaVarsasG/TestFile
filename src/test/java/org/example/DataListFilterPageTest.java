package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DataListFilterPageTest {

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

    public String[] readFile() {

        List<String> names = new ArrayList<>();
        try {
            File text = new File("names.txt");
            Scanner namescn = new Scanner(text);
            while (namescn.hasNextLine()) {
                String name = namescn.nextLine();
                names.add(name);
            }
        }
        catch (Exception e) {
            System.out.println("File not found");
        }
        //return names.toArray(new String[0]);
        String[] nameArray = new String[names.size()];
        return names.toArray(nameArray);
    }

    @Test
    void testGetNames() {
        DataListFilterPage dataListFilterPage = new DataListFilterPage(driver);
        dataListFilterPage.navigate();
        String[] actual = dataListFilterPage.getNames();

        Assertions.assertArrayEquals(readFile(), actual);
    }

    @AfterEach
    void tearDown() {
    }
}