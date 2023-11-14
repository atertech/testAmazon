package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    By searchFieldMainPage = By.xpath("//*[@id=\"twotabsearchtextbox\"]");
    By searchBtn = By.xpath("//*[@id=\"nav-search-submit-button\"]");
    private final WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver=driver;
    }
    public void goToAmazonMainPage() throws InterruptedException {
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
        Thread.sleep(2500);
    }
    public void selectBookCategory(){
        WebElement dropDownMenu = driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]"));
        dropDownMenu.click();
        WebElement booksOption = driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]/option[6]"));
        booksOption.click();
    }
    public void searchNeededBooks(){
        WebElement searchField = driver.findElement(searchFieldMainPage);
        searchField.sendKeys("Java");
        WebElement searchButton = driver.findElement(searchBtn);
        searchButton.click();
    }
}