package Manga;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.imageio.ImageIO;  
import java.io.File;  
import java.io.IOException;  
import java.awt.image.BufferedImage;  
public class open{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unused")
		String driverPath = "D:\\GIt\\Manga\\Manga\\Manga\\Lib\\chromedriver.exe";
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://w9.jujmanga.com/");
		driver.manage().window().maximize();
		
		try {
		WebElement imgalt = driver.findElement(By.xpath(""));
		String imgSRC = imgalt.getAttribute("src");
		 URL imageURL = new URL(imgSRC);
	     BufferedImage saveImage = ImageIO.read(imageURL);
	     
	     ImageIO.write(saveImage, "png", new File("img1.png"));
	     
	     } catch (Exception e) {
	        e.printStackTrace();
	     } finally {
	        driver.close();
	     }
		

		

	}

}
