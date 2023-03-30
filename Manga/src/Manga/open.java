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
	
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")

		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.get("https://w9.jujmanga.com/");
		driver.manage().window().maximize();

		w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//*[text()='Jujutsu Kaisen, Chapter 1']"))).click();
		driver.switchTo().window(newTab.get(0));
		System.out.println("Enter Folder Name");
		folderPath=folderPath + sc.next();
		createFolder(folderPath);
		saveIMGIntoPdf();
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
	public static void saveIMGIntoPdf() throws FileNotFoundException {
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
}

