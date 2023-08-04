package seleniumintroduction;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Academian {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\omen\\OneDrive\\Desktop\\Selenium\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Open BookMyShow and Select City Bengaluru . click on singin
		driver.get("https://in.bookmyshow.com/explore/home/");
	    driver.findElement(By.xpath("//span[@class='sc-jJcwTH kLTiAF'][normalize-space()='Bengaluru']")).click();
	    driver.findElement(By.cssSelector(".sc-bOhtcR.feRlEy")).click();
	    
	    //Click on email and send email and click on continue
	    driver.findElement(By.xpath("//div[contains(text(),'Continue with Email')]")).click();
	    driver.findElement(By.id("emailId")).sendKeys("seleniumauto@yopmail.com");
	    driver.findElement(By.cssSelector(".sc-kWtpeL.fMffSV")).click();
	    
	    //Go to yopmail.com type email and get otp 
	    driver.get("https://yopmail.com/");
	    Set<String>window = driver.getWindowHandles();
		Iterator<String>it = window.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		driver.findElement(By.id("login")).sendKeys("seleniumauto@yopmail.com");
		driver.findElement(By.cssSelector(".material-icons-outlined.f36")).click();
		driver.findElement(By.cssSelector(".lmf")).click();
		
		//Back to bookmyshow send the otp back on sign in page and check the Hi,Guest
		driver.switchTo().frame(driver.findElement(By.id("ifmail")));
		String otp = driver.findElement(By.xpath("//body[1]/main[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[4]/td[1]/table[1]/tbody[1]/tr[2]/td[1]")).getText();
		driver.switchTo().window(parentId);
		driver.findElement(By.cssSelector(".sc-fqkvVR.sc-dxcDKg.bhNvUi.bdbgij")).sendKeys(otp);
		String ActualText = driver.findElement(By.cssSelector(".sc-gEkIjz.iBRucH")).getText();
		String ExpectedText = "Hi,Guest";
		Assert.assertNotEquals(ActualText,ExpectedText );
		driver.quit();
		
		
		
	}

}
