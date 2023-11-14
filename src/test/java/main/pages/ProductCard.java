package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductCard {
    public static List<BookInfo> bookInfoList;
    public static List<ProductCard> productCardList;

    private WebElement productId;
    private final WebDriver driver;
    public ProductCard(WebDriver driver){
        this.driver=driver;
    }
    public void collectProductInfo() throws InterruptedException {
        bookInfoList = new ArrayList<>();

        Thread.sleep(3000);
        //productId = driver.findElement(By.xpath("//div[@data-asin]"));

        String title = getTitle();
        String authorName =getAuthor();

        BookInfo bookInfo = new BookInfo(title, authorName);
        bookInfoList.add(bookInfo);

        /*String author = getAuthor();
        Boolean isBestSeller = isBestSeller();
        System.out.println("Book title: " + title);
        System.out.println("By: " + author);
        System.out.println("BestSeller: " + isBestSeller);
        System.out.println();*/
    }
    public String getTitle(){
        return productId.findElement(By.xpath("//*[@id=\"productTitle\"]")).getText();
    }
    public String getAuthor(){
        return productId.findElement(By.xpath("//*[@id=\"bylineInfo\"]")).getText();
    }
    public boolean isBestSeller() {
        return isElementPresent(By.xpath("//*[@id='multiBadge_feature_div']/div/a/span/span[1]"));
    }
    public boolean isElementPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }
    class BookInfo {
        private String title;
        private String authorName;
       // private String price;
        //private boolean bestseller;

        public BookInfo(String title, String authorName) {
            this.title = title;
            this.authorName = authorName;
            //this.price = price;
            //this.bestseller = bestseller;
        }
    }

}