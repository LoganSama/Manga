package Manga;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;  
import java.io.File;  
import java.io.IOException;  
import java.awt.image.BufferedImage;  
public class open{
	static WebDriver driver = new ChromeDriver();
	static WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
	public static String imgXpath ="//div[contains(@class,'simplesocialbuttons')]/..//img[contains(@alt,'1')]";
	public static String folderPath="D:\\MangaLad\\Manga\\Manga\\Manga Chapter Files\\";
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		@SuppressWarnings("unused")
		String driverPath = "D:\\\\MangaLad\\\\Manga\\\\Manga\\\\Lib\\\\chromedriver.exe";

		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.get("https://w9.jujmanga.com/");
		driver.manage().window().maximize();

		w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//*[text()='Jujutsu Kaisen, Chapter 1']"))).click();
		driver.switchTo().window(newTab.get(0));
		System.out.println("Enter Folder Name");
		folderPath=folderPath + sc.next();
		createFolder(folderPath);
		saveIMG();
		//driver.close();
	}

	public static void createFolder(String path) {
		File file = new File(path);
		boolean bool = file.mkdir();
		if(bool) {
			System.out.println("Folder Created under" + path);
		}
		else {
			System.out.println("Folder Not Created");
		}
	}
	public static void saveIMG() {
		w.until(ExpectedConditions.presenceOfElementLocated (By.xpath(imgXpath)));
		WebElement imgalt = driver.findElement(By.xpath(imgXpath));
		String imgSRC = imgalt.getAttribute("src");
		URL imageURL;
		try {
			imageURL = new URL(imgSRC);
			BufferedImage saveImage = ImageIO.read(imageURL);
			ImageIO.write(saveImage, "jpg", new File(folderPath + "\\im1.jpg"));
			System.out.println("Image Added");
		} catch (Exception e) {
		}
	}

}

