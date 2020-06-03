import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {

	public static void main(String[] args) throws InterruptedException {

		//WebDriver driver=new ChromeDriver();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //implicit wait

		int j = 0;
		String[] itemsReqd = { "Brocolli", "Cauliflower", "Tomato", "Beans" };

		List<WebElement> veggies = driver.findElements(By.cssSelector("h4.product-name"));

		for (int i = 0; i < veggies.size(); i++)
		{
			String[] name = veggies.get(i).getText().split("-");
			String formattedName = name[0].trim();

			List cart = Arrays.asList(itemsReqd);

			if (cart.contains(formattedName)) 
			{
				j++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (j == itemsReqd.length)
					break;
			}
		}
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div[3]/a[4]/img")).click();
		driver.findElement(By.xpath("//button[contains(text(),\"PROCEED TO CHECKOUT\")]")).click();
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		//Thread.sleep(2000);
		
		WebDriverWait w=new WebDriverWait(driver,5);
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.promoInfo")));

		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
		driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/select")).click();
		WebElement country=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/select"));
		country.sendKeys("ii");
		//country.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		country.click();
		driver.findElement(By.cssSelector("input[type=checkbox]")).click();
		
		System.out.println("Test Success!!");
	}
}