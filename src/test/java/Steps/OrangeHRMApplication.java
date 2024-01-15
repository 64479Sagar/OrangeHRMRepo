package Steps;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import io.cucumber.datatable.DataTable;

public class OrangeHRMApplication {

	public static WebDriver driver;

	public static String empId;

	@Given("user is on Login Page")
	public void user_is_on_login_page() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@When("user enter valid credentails")
	public void user_enter_valid_credentails(DataTable dataTable) {
		List<List<String>> ls = dataTable.asLists();
		String uname = ls.get(0).get(0);
		String pass = ls.get(0).get(1);

		driver.findElement(By.name("username")).sendKeys(uname);
		driver.findElement(By.name("password")).sendKeys(pass);

	}

	@When("user click on login button")
	public void user_click_on_login_button() {
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();

	}

	@When("user is on home page validate home page title")
	public void user_is_on_home_page_validate_home_page_title(DataTable dataTable) {
		String title = dataTable.asLists().get(0).get(0);

		String actualTitle = driver.getTitle();

		//Assert.assertEquals(actualTitle, title);
	}

	@When("user validate home page url")
	public void user_validate_home_page_url() {
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, true);
	}

	@When("user validate home page logo")
	public void user_validate_home_page_logo(DataTable dataTable) {
		String abc = dataTable.asLists().get(0).get(0);
		boolean xyz = Boolean.parseBoolean(abc);
		boolean logo = driver.findElement(By.xpath("//div[@class='oxd-brand-banner']")).isDisplayed();
		Assert.assertEquals(logo, xyz);
	}

	@When("user click on pim page link")
	public void user_click_on_pim_page_link() {
		driver.findElement(By.xpath("(//a[@class='oxd-main-menu-item'])[2]")).click();
	}

	@When("user validate user is on pim page by using url")
	public void user_validate_user_is_on_pim_page_by_using_url(DataTable dataTable) {
		String abc = dataTable.asLists().get(0).get(0);
		String url = driver.getCurrentUrl();
		boolean a = url.contains(abc);
		Assert.assertEquals(a, true);
	}

	@When("user click add employee and enter firstname, lastname and click on save button")
	public void user_click_add_employee_and_enter_firstname_lastname_and_click_on_save_button(DataTable dataTable)
			throws InterruptedException {

		List<List<String>> ls = dataTable.asLists();
		for (int i = 0; i < ls.size(); i++) {
			driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
			Thread.sleep(5000);
			String firstName = ls.get(i).get(0);
			String lastName = ls.get(i).get(1);
			driver.findElement(By.name("firstName")).sendKeys(firstName);

			driver.findElement(By.name("lastName")).sendKeys(lastName);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		}
	}

	@When("user capture employee id number and user click on employeelist")
	public void user_capture_employee_id_number_and_user_click_on_employeelist() throws InterruptedException {
		Thread.sleep(2000);
		empId = driver.findElement(By.xpath("(//input[@class=\"oxd-input oxd-input--active\"])[3]"))
				.getAttribute("value");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();

	}

	@When("user enter employee id is employee id text  box and click on search button")
	public void user_enter_employee_id_is_employee_id_text_box_and_click_on_search_button()
			throws InterruptedException {

		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[text()='Employee Id']/following::input[1]")).sendKeys(empId);
		driver.findElement(By.xpath("//button[text()=' Search ']")).click();
		Thread.sleep(5000);

//		Thread.sleep(2000);
//		driver.findElement(By.xpath("(//input[@class=\\\"oxd-input oxd-input--active\\\"])[3]")).sendKeys(empId);
//		driver.findElement(By.xpath("//a[text()='Employee List']")).click();
//		
//		driver.findElement(By.xpath("//button[text()=' Search ']")).click();
//		Thread.sleep(2000);
	}

	@When("user selected searched employee and click on delete")
	public void user_selected_searched_employee_and_click_on_delete() throws InterruptedException {

		Thread.sleep(2000);
		driver.findElement(By.xpath("(//i[@class='oxd-icon bi-check oxd-checkbox-input-icon'])[2]")).click();

		driver.findElement(By.xpath("//button[text()=' Delete Selected ']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()=' Yes, Delete ']")).click();

//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("(//i[@class=\"oxd-icon bi-check oxd-checkbox-input-icon\"])[2]")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//button[text()=' Delete Selected ']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//button[text()=' Yes, Delete ']")).click();

	}

	@Then("validate employee is deleted or not")
	public void validate_employee_is_deleted_or_not() throws InterruptedException {

		driver.findElement(By.xpath("//label[text()='Employee Id']/following::input[1]")).sendKeys(empId);
		driver.findElement(By.xpath("//button[text()=' Search ']")).click();
		Thread.sleep(5000);
		String norecords = driver.findElement(By.xpath("//span[text()='No Records Found']")).getText();

		Assert.assertEquals(norecords, "No Records Found");
		
		
//		driver.findElement(By.xpath("(//input[@class=\"oxd-input oxd-input--active\"])[2]")).sendKeys(empId);
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//button[text()=' Search ']")).click();
//		Thread.sleep(2000);
//		String norecords = driver.findElement(By.xpath("(//span[@class=\"oxd-text oxd-text--span\"])[1]")).getText();
//		Assert.assertEquals(norecords, "No Records Found");

	}

	@When("user click on profile and click on logout button")
	public void user_click_on_profile_and_click_on_logout_button() {

		driver.findElement(By.xpath("//img[@alt='profile picture']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}

	@AfterStep
	public void tearDown(Scenario scenario) throws InterruptedException {

		Thread.sleep(2000);
		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] f = ts.getScreenshotAs(OutputType.BYTES);
		scenario.attach(f, "image/png", scenario.getName());
	}
}
