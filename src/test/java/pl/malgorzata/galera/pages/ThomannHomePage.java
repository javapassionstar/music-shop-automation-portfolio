package pl.malgorzata.galera.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ThomannHomePage {

    private static final String BASE_URL = "https://www.thomann.de";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By acceptCookiesButton = By.xpath(
            "//button[contains(@class,'fx-button') " +
                    "or contains(text(),'Accept') " +
                    "or contains(text(),'Akceptuję')]"
    );

    private final By searchInputLocator =
            By.xpath("//*[@data-track-id='searchBox']");

    public ThomannHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public ThomannHomePage open() {
        driver.get(BASE_URL);
        return this;
    }

    public ThomannHomePage acceptCookiesIfVisible() {
        WebElement acceptBtn = wait.until(
                ExpectedConditions.elementToBeClickable(acceptCookiesButton));

        acceptBtn.click();
        return this;
    }

    public SearchResultsPage searchForInstrument(String instrumentName) {

        WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInputLocator));

        searchInput.clear();
        searchInput.sendKeys(instrumentName);
        searchInput.sendKeys(Keys.ENTER);

        return new SearchResultsPage(driver);
    }
}