package Manga;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unused")
		String driverPath = "";
		WebDriver driver = new ChromeDriver();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.get("https://w9.jujmanga.com/");
		driver.manage().window().maximize();
		 WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
		 w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//*[text()='Jujutsu Kaisen, Chapter 1']"))).click();
		 driver.switchTo().window(newTab.get(0));
//		 driver.close();
		System.out.println("YAY??");
		w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//div[contains(@class,'simplesocialbuttons')]/..//img[contains(@alt,'1')]")));
		WebElement imgalt = driver.findElement(By.xpath("//div[contains(@class,'simplesocialbuttons')]/..//img[contains(@alt,'1')]"));
		String imgSRC = imgalt.getAttribute("src");
		 URL imageURL;
		try {
			imageURL = new URL(imgSRC);
	     BufferedImage saveImage = ImageIO.read(imageURL);
	     
	     ImageIO.write(saveImage, "png", new File("img1.png"));
		} catch (Exception e) {
		}
	        driver.close();
	}
	}
