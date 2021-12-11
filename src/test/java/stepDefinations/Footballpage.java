package stepDefinations;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import Base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Footballpage {

	public WebDriver driver;
	public BaseClass login;
	public Scenario scenario;

	@Before
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-notifications");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		login = new BaseClass(driver);
	}

	@After
	public void teardown(Scenario scenario) {
		this.scenario = scenario;
		if (scenario.isFailed()) {
			TakesScreenshot scrshot = (TakesScreenshot) driver;
			byte[] data = scrshot.getScreenshotAs(OutputType.BYTES);
			scenario.attach(data, "image/png", "ContentPortal");
		}
		driver.close();
	}

	@Given("open the Ladbrokes application")
	public void open_the_Ladbrokes_application() throws Exception {
		login.gotoURL();
	}

	@When("User Hover mouse over on Football tab")
	public void user_Hover_mouse_over_on_Football_tab() throws Exception {
		login.mouseOverFootBall();
	}

	@Then("the Football menu appears")
	public void the_Football_menu_appears() throws Exception {
		login.footBallMenu();
	}

	@Then("click on Premier League")
	public void click_on_Premier_League() throws Exception {
		login.premierLeague();
	}

	@Then("Verify that Premier League page has been opened")
	public void verify_that_Premier_League_page_has_been_opened() throws Exception {
		login.verifyTitlePremierLeague();
	}

	@When("User Click on random article in the Sport News section")
	public void user_Click_on_random_article_in_the_Sport_News_section() throws Exception {
		login.randomArticle();
	}

	@Then("Verify that the correct article has been opened")
	public void verify_that_the_correct_article_has_been_opened() throws Exception {
		login.verifyRandomPageTitile();
	}

	@When("User provides {string} search phrase")
	public void user_provides_search_phrase(String string) throws Exception {
		login.keywordSearch(string);
	}

	@When("User press Enter on the keyboard")
	public void user_press_Enter_on_the_keyboard() throws Exception {
		login.enterKeyBoard();
	}

	@Then("Verify that more than one page of results have been returned")
	public void verify_that_more_than_one_page_of_results_have_been_returned() throws Exception {
		login.verifyResults();
	}
}
