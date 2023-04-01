package Manga;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import javax.imageio.ImageIO;  
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;  
import java.awt.image.BufferedImage;  

public class open{
	static WebDriver driver = new ChromeDriver();
	static WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
	public static String imgXpath ="//div[contains(@class,'simplesocialbuttons')]/..//img[contains(@alt,'1')]";
	public static String folderPath="D:\\MangaLad\\Manga\\Manga\\Manga Chapter Files\\";
	public static String homePage = "https://w9.jujmanga.com";
	public static String newPath = null;
	public static Scanner sc = new Scanner(System.in);
	static ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		String driverPath = "D:\\MangaLad\\Manga\\Manga\\Lib\\chromedriver.exe";	
		driver.get(homePage);
		driver.manage().window().maximize();

		//		w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//*[text()='Jujutsu Kaisen, Chapter 1']"))).click();
		//		driver.switchTo().window(newTab.get(0));
		//		folderPath=folderPath + "IterateIMG";
		//		createFolder(folderPath);
		//		iterateIMGNum();
		iterateChapters();
		driver.quit();
	}

	public static void createFolder(int chpNum) {
		newPath=folderPath + "Chapter " + chpNum;
		File file = new File(newPath);
		boolean bool = file.mkdir();
		if(bool) {
			System.out.println("Folder Created under" + newPath);
		}
		else {
			System.out.println("Folder Not Created");
		}
	}
	/**	public static void saveIMGIntoPdf() throws FileNotFoundException {
		String pdfPath = folderPath + "\\Test.pdf";
		System.out.println(pdfPath);
		PdfWriter writer = new PdfWriter(pdfPath);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document doc = new Document(pdfDoc);
		w.until(ExpectedConditions.presenceOfElementLocated (By.xpath(imgXpath)));
		WebElement imgalt = driver.findElement(By.xpath(imgXpath));
		String imgSRC = imgalt.getAttribute("src");
		URL imageURL;
		try {
			imageURL = new URL(imgSRC);
			BufferedImage saveImage = ImageIO.read(imageURL);
			ImageIO.write(saveImage, "jpg", new File(folderPath + "\\im1.jpg"));
			ImageData imageData = ImageDataFactory.create(folderPath + "\\im1.jpg");
			Image img = new Image(imageData);
			doc.add(img);
			doc.close();
		} catch (Exception e) {
		}
	} 
	 * @throws MalformedURLException **/

	public static void iterateIMGNum(int chpNum) throws FileNotFoundException, MalformedURLException {
		String imgXpath = "//h2[text()='All Chapters']/..//*[contains(text(),'Chapter ##CHPNUM##')]";
		String imgXpath1 = "//div[contains(@class,'simplesocialbuttons')]/..//img[contains(@alt,'Jujutsu Kaisen, Chapter')]";
		String imgXpath2 = "//div[contains(@class,'simplesocialbuttons')]/..//img[contains(@alt,'Jujutsu Kaisen, Chapter ##CHPNUM## image 00##NUM##')]";
		String imgXpath3 = "//div[contains(@class,'simplesocialbuttons')]/..//img[contains(@alt,'Jujutsu Kaisen, Chapter ##CHPNUM## image 0##NUM##')]";
		String imgXpath4= "//*[contains(@class,'simplesocialbuttons')]/..//img[@alt='Jujutsu Kaisen, Chapter ##CHPNUM## image ##IMGNUM##']";
		String pdfPath = newPath + "\\Chapter "+chpNum+".pdf";
		System.out.println(pdfPath);
		Image img = null;
		String c = String.valueOf(chpNum);
		String chpNav = imgXpath.replace("##CHPNUM##", c);
		System.out.println(chpNav);
		w.until(ExpectedConditions.presenceOfElementLocated (By.xpath(chpNav))).click();
		driver.switchTo().window(newTab.get(0));
		PdfWriter writer = new PdfWriter(pdfPath);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document doc = new Document(pdfDoc);
		List<WebElement> allimg = driver.findElements(By.xpath(imgXpath1));
		for(int i=1; i<=allimg.size();i++){
			String s = String.valueOf(i);
			if(i<=9) {
				String chpXpath = imgXpath2.replace("##CHPNUM##", c);
				String temp = chpXpath.replace("##NUM##", s);
				System.out.println(temp);
				Boolean exist = driver.findElements(By.xpath(temp)).size() == 0;
				System.out.println(exist);
				if(exist==false) {
					w.until(ExpectedConditions.presenceOfElementLocated (By.xpath(temp)));
					WebElement imgalt = driver.findElement(By.xpath(temp));
					String imgSRC = imgalt.getAttribute("src");
					URL imageURL;
					try {
						imageURL = new URL(imgSRC);
						BufferedImage saveImage = ImageIO.read(imageURL);
						ImageIO.write(saveImage, "jpg", new File(newPath + "\\img"+ i+".jpg"));
					} catch (Exception e) {
					}
					ImageData imageData = ImageDataFactory.create(newPath + "\\img"+i+".jpg");
					img = new Image(imageData);
					doc.add(img);
				}
			}

			else if(i>9) {
				String chpXpath = imgXpath3.replace("##CHPNUM##", c);
				String temp = chpXpath.replace("##NUM##", s);
				System.out.println(temp);
				Boolean exist = driver.findElements(By.xpath(temp)).size() == 0;
				System.out.println(exist);
				if(exist==false) {
					w.until(ExpectedConditions.presenceOfElementLocated (By.xpath(temp)));
					WebElement imgalt = driver.findElement(By.xpath(temp));
					String imgSRC = imgalt.getAttribute("src");
					URL imageURL;
					try {
						imageURL = new URL(imgSRC);
						BufferedImage saveImage = ImageIO.read(imageURL);
						ImageIO.write(saveImage, "jpg", new File(newPath + "\\img"+ i+".jpg"));
					} catch (Exception e) {
					}
					ImageData imageData = ImageDataFactory.create(newPath + "\\img"+i+".jpg");
					img = new Image(imageData);
					doc.add(img);
				}
			}
			System.out.println("Value of i" + i);
			}
		doc.close();
		}
	public static void iterateChapters() throws FileNotFoundException, MalformedURLException {
		String chpXpath = "//h2[text()='All Chapters']/..//*[contains(text(),'Jujutsu Kaisen, Chapter')]";
		List<WebElement> allchp = driver.findElements(By.xpath(chpXpath));
		for(int j=1; j<allchp.size();j++) {
			createFolder(j);
			iterateIMGNum(j);
			driver.navigate().to(homePage);
			driver.switchTo().window(newTab.get(0));
		}
	}
}