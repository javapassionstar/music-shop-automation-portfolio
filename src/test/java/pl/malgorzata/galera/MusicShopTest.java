package pl.malgorzata.galera;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.malgorzata.galera.pages.ThomannHomePage;
import pl.malgorzata.galera.pages.SearchResultsPage;

import java.time.Duration;

public class MusicShopTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSearchForInstrument() {

        ThomannHomePage homePage = new ThomannHomePage(driver);
        SearchResultsPage resultsPage = new SearchResultsPage(driver);

        homePage.open()
                .acceptCookiesIfVisible()
                .searchForInstrument("Gibson");

        String headlineText = resultsPage.getSearchHeadlineText();
        Assertions.assertTrue(headlineText.contains("Gibson"),
                "Search results headline does not contain the expected phrase!");
    }

    @Test
    public void testExtractProductPrice() {
        ThomannHomePage homePage = new ThomannHomePage(driver);

        homePage.open()
                .acceptCookiesIfVisible()
                .searchForInstrument("Gibson");

        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        String price = resultsPage.getProductPrice();

        System.out.println("---------> CENA GIBSONA: " + price + " <---------");
        Assertions.assertFalse(price.isEmpty(), "Product price is empty!");
    }
}
