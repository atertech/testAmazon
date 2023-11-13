package main.pages;

import main.pages.ProductCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

    public class SearchResultPage {
    public static List<BookInfo> bookInfoList;
    public static List<ProductCard> productCardList;
    private final WebDriver driver;
    public SearchResultPage(WebDriver driver){
        this.driver=driver;
    }
    public void collectProductsInTheList() {
        List<WebElement> bookElements = driver.findElements(By.xpath("//h2/a/span"));
        List<WebElement> authorElements = driver.findElements(By.xpath("//div[@class='a-row a-size-base a-color-secondary']"));
        List<WebElement> priceElements = driver.findElements(By.xpath("//span[@class='a-price']"));

        bookInfoList = new ArrayList<>();

        for (int i = 0; i < bookElements.size(); i++) {
            String bookTitle = bookElements.get(i).getText();
            String authorName = authorElements.get(i).getText();
            String price = priceElements.get(i).getText();

            boolean bestseller = bookElements.get(i).findElements(By.xpath(".//span[@class='.a-badge-text']")).size() > 0;
            BookInfo bookInfo = new BookInfo(bookTitle, authorName, price, bestseller);
            bookInfoList.add(bookInfo);
        }
    }
    public void printProductList(){
        for (BookInfo bookInfo : bookInfoList) {
            System.out.println("Book title: " + bookInfo.getTitle());
            System.out.println("By: " + bookInfo.getAuthor());
            System.out.println("Price: " + bookInfo.getPrice());
            System.out.println("BestSeller: " + bookInfo.isBestseller());
            System.out.println();
        }
    }
    public void checkIfNeededBookInTheList(){
        boolean isNeededBookFound = false;
        String neededBook = "Head First Java, 2nd Edition";
        for (BookInfo bookInfo : bookInfoList) {
            if (bookInfo.getTitle().equals(neededBook)) {
                isNeededBookFound = true;
                break;
            }
        }

        if (isNeededBookFound) {
            System.out.println("Book " + neededBook + " found in the list");
        } else {
            System.out.println("Book " + neededBook + " not found in the list");
        }
    }
    class BookInfo {
        private String title;
        private String author;
        private String price;
        private boolean bestseller;

        public BookInfo(String title, String author, String price, boolean bestseller) {
            this.title = title;
            this.author = author;
            this.price = price;
            this.bestseller = bestseller;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getPrice() {
            return price;
        }

        public boolean isBestseller() {
            return bestseller;
        }
    }
}