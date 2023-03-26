package Manga;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class open{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String driverPath = "D:\\GIt\\Manga\\Manga\\Manga\\Lib\\chromedriver.exe";
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://w9.jujmanga.com/");
		driver.manage().window().maximize();
//		driver.close();
		

	}

}
