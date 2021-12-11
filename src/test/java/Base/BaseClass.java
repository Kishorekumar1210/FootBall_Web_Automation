package Base;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BaseClass {

	public WebDriver driver;

	PropertiesFileReader obj = new PropertiesFileReader();
	Properties data = obj.getProperty();

	public BaseClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//*[text()='Football'])[1]")
	WebElement FootBall;

	@FindBy(xpath = "(//*[text()='Premier League'])[1]")
	WebElement PremierLeague;

	@FindBy(xpath = "//*[text()='SPORTS NEWS']")
	WebElement RandomArticle;

	@FindBy(xpath = "//*[@class='search-input']")
	WebElement SearchKeywordBox;

	@FindBy(css = "h1.search-title")
	WebElement Results;

	@FindBy(css = "a.next.page-numbers")
	WebElement NextPage;

	@FindBy(xpath = "(//a[@class='page-numbers'])[1]")
	WebElement SecondPage;

	@FindBy(xpath = "//*[text()='« Previous']")
	WebElement PreviousPage;

	@FindBy(xpath = "//*[text()='Next »']")
	WebElement NextNumberPage;

	public void gotoURL() throws Exception {
		try {
			getAppUrl();
		} catch (Exception e) {
			throw new Exception("Webpage is not working");
		}
	}

	public void getAppUrl() {
		driver.get(data.getProperty("BaseURI"));
		implicitWait(5);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	// MouseOver
	public void mouseOverFootBall() throws Exception {
		try {
			performMouseHover(FootBall);
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("Mouse over not working");
		}
	}

	public void footBallMenu() throws Exception {
		try {
			FootBall.isDisplayed();
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("FootBall Menu bar is not displayed");
		}
	}

	// Mouse Over
	public void performMouseHover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	// Premier League
	public void premierLeague() throws Exception {
		try {
			clickObject(PremierLeague);
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("Premier League is not clicked");
		}
	}

	// Verify premier league page
	public void verifyTitlePremierLeague() throws Exception {
		try {
			String Expected = "https://sports.ladbrokes.com/news/football/premier-league/";
			String actual = driver.getCurrentUrl();
			System.out.println(actual);
			assertEquals(actual, Expected);
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("Title page is not correct");
		}
	}

	// Click on the Element
	public void clickObject(WebElement element) throws Exception {
		if (element.isDisplayed()) {
			element.click();
		} else {
			throw new Exception("Element is not displayed");
		}
	}

	// click on any random sports news
	public void randomArticle() throws Exception {
		try {
			scrollIntoView(RandomArticle);
			randomclick();
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("Random Article is not picked");
		}
	}

	public void randomclick() {
		List<WebElement> listings = driver.findElements(By.cssSelector("div.news-btn"));
		Random r = new Random();
		int randomValue = r.nextInt(listings.size());
		listings.get(randomValue).click();
	}

	public void verifyRandomPageTitile() throws Exception {
		try {

			String TitlePage1 = "https://sports.ladbrokes.com/news/football/both-teams-to-score-tips";
			String TitlePage2 = "https://sports.ladbrokes.com/news/horse-racing/paul-townend-says-blue-lord-the-pick";
			String TitlePage3 = "https://sports.ladbrokes.com/news/horse-racing/itv-racing-tips-song-for-someone-tuned-up-for-second-international-hurdle-win/";
			String TitlePage4 = "https://sports.ladbrokes.com/news/horse-racing/dan-skelton-cheltenham-doncaster-hereford-dec-11";
			String TitlePage5 = "https://sports.ladbrokes.com/news/horse-racing/kempton-christmas-festival";
			String TitlePage6 = "https://sports.ladbrokes.com/news/football/sons-who-followed-dads-in-becoming-professional-footballers/";

			Thread.sleep(2000);
			String randomArticlePage = driver.getCurrentUrl();
			System.out.println(randomArticlePage);

			if (randomArticlePage.equals(TitlePage1)) {
				assertEquals(randomArticlePage, TitlePage1);
			} else if (randomArticlePage.equals(TitlePage2)) {
				assertEquals(randomArticlePage, TitlePage2);
			} else if (randomArticlePage.equals(TitlePage3)) {
				assertEquals(randomArticlePage, TitlePage3);
			} else if (randomArticlePage.equals(TitlePage4)) {
				assertEquals(randomArticlePage, TitlePage4);
			} else if (randomArticlePage.equals(TitlePage5)) {
				assertEquals(randomArticlePage, TitlePage5);
			} else if (randomArticlePage.equals(TitlePage6)) {
				assertEquals(randomArticlePage, TitlePage6);
			}
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("Random Article Title is not correct");
		}
	}

	// search keyword
	public void keywordSearch(String string) throws Exception {
		try {
			SearchKeywordBox.click();
			SearchKeywordBox.sendKeys(string);
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("Search Keyword is not provided");
		}
	}

	// Click on Enter using keyboard
	public void enterKeyBoard() throws Exception {
		try {
			SearchKeywordBox.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("Enter keyboard is not clicked");
		}
	}

	// Verify the result of pages
	public void verifyResults() throws Exception {
		try {
			String results = Results.getText();
			String Array[] = results.split(" ");
			String Result = Array[0];
			int actualResult = Integer.parseInt(Result);
			System.out.println(Result);
			if (actualResult > 0) {
				System.out.println("Results Number : " + actualResult);
			}
			nextPages();
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("Result page is not displayed");
		}
	}

	// Pagination
	public void nextPages() throws Exception {
		try {
			scrollIntoView(NextPage);
			clickObject(SecondPage);
			Thread.sleep(10000);
			scrollIntoView(NextPage);
			previousPages();
			System.out.println("Verified user can see Previous and more than one pages");
			Thread.sleep(5000);
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("Next Page is not displayed");
		}
	}

	// verify Previous and Next page is displayed
	public void previousPages() throws Exception {
		try {
			PreviousPage.isDisplayed();
			NextNumberPage.isDisplayed();
		} catch (Exception e) {
			takeSnapShot(driver);
			throw new Exception("Next Page is not displayed");
		}
	}

	// Implicit Wait
	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	// Scroll Into View
	public void scrollIntoView(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	// Take screenshots
	public static void takeSnapShot(WebDriver webdriver) throws Exception {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("/Users/kishorekumar/eclipse-workspace/ContentPortal/ContentPortalProject/screenshot/"
				+ timeStamp + ".png");
		FileUtils.copyFile(SrcFile, DestFile);
	}

}
