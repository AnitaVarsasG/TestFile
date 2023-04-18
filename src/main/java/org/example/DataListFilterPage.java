package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DataListFilterPage extends BasePage{

    public DataListFilterPage(WebDriver driver) {
        super(driver);
    }

    private final String URL = "https://demo.seleniumeasy.com/data-list-filter-demo.html";
    private final By NAME_CARD = By.xpath("//*[@class='content']//*[@class='info-block block-info clearfix']");

    public void navigate() {
        driver.navigate().to(URL);
    }

    public String[] getNames() {
        List<WebElement> nameCards = driver.findElements(NAME_CARD);

        String[] names = new String[nameCards.size()];
        int i = 0;
        for (WebElement nameCard : nameCards) {
            WebElement name = nameCard.findElement(By.xpath(".//h4"));
            names[i] = name.getText().replace("Name: ", "");
            i++;
        }
        return names;
    }


}
