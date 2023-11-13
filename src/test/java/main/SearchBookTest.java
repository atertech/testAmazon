package main;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.pages.MainPage;
import main.pages.SearchResultPage;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class SearchBookTest {
    Properties properties;
    private WebDriver driver;
    //SearchResultPage resultPage = new SearchResultPage(driver);

    @BeforeClass
    @SneakyThrows
    public void beforeClass() {
        properties = new Properties();
        BufferedReader reader = Files.newBufferedReader(Path.of("config.properties"));
        properties.load(reader);
        String driverName = properties.getProperty("driver");
        File file = new File(properties.getProperty("path"));
        System.setProperty(driverName, file.getAbsolutePath());
        driver = new ChromeDriver();
    }

    @SneakyThrows
    @Test(priority = 1)
    public void getBookList() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToAmazonMainPage();
        Thread.sleep(2500);
        mainPage.selectBookCategory();
        mainPage.searchNeededBooks();
        SearchResultPage resultPage = new SearchResultPage(driver);
        resultPage.collectProductsInTheList();
    }

    @Test(priority = 2)
    public void findCertainBookInList() throws InterruptedException {
        SearchResultPage resultPage = new SearchResultPage(driver);
        resultPage.checkIfNeededBookInTheList();
    }

}