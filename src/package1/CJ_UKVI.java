package package1;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CJ_UKVI {
	private WebDriver driver;
    private String baseUrl;
    private String strPassword = "Password@1";		
	private String strEmail;
    CommonComponents objComm = new CommonComponents();
    
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", objComm.getLibraryPath() + "chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://ieltsukvisas-uat.britishcouncil.org/";
		//timeout if site page does not load in 30 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}
	
	@Test
	public void test(){
		/*login();
		bookTest();		
		enterCandidateDetails();*/
		chooseTest();
		enterCandidateDetails_Register();		
		enterCandidateDetails();
	}
	
	public void login(){		
		objComm.waitForElement(driver, By.linkText("Log In"), 15);
		objComm.findAndClicklinkByText(driver, "Log In");
		objComm.waitForElement(driver, By.xpath("//form//input[contains(@ng-model,'vm.loginUserModel.email')]"), 10);
		objComm.findAndEnter(driver, By.xpath("//form//input[contains(@ng-model,'vm.loginUserModel.email')]"), strEmail, "Email");
		objComm.findAndEnter(driver, By.xpath("//form//input[@type='password']"), strPassword, "Password");
		objComm.findAndClick(driver, By.xpath("//input[@value='Log in']"), "Log in");
	}
	
	public void bookTest(){
		objComm.waitForElement(driver, By.xpath("//div[@class='panel home-panel panel-book']"), 25);
		objComm.sleepTime(driver, 10);
		objComm.findAndClick(driver, By.xpath("//div[@class='panel home-panel panel-book']"), "Book Test");
		chooseTest();
		clickButton(driver, By.xpath("//button[@class='btn btn-primary']"), "Book Test");
	}
	
	public void chooseTest(){
		//Change Country
		objComm.waitTillPageload_UKVI(driver, 20);
		objComm.waitForElement(driver, By.linkText("Change country"), 10);
		objComm.findAndClicklinkByText(driver, "Change country");
		objComm.waitForElement(driver,By.xpath("//select[@ng-model='vm.selectedCountry']"), 10);
		objComm.findAndSelectOptions(driver, By.xpath("//select[@ng-model='vm.selectedCountry']"), "Select Country", "India");
		objComm.findAndClick(driver, By.xpath("//a[@class='cc-link'][text()='Close']"), "Notification Close");
		clickButton(driver, By.xpath("//div[starts-with(@class,'btn-group')]//button[@class='btn btn-primary btn-block']"), "Continue");
		objComm.sleepTime(driver, 20);		
		objComm.waitForElement(driver,By.xpath("//div[@ng-bind-html='vm.countryInformation']"), 70);
		objComm.waitTillPageload_UKVI(driver, 20);
		clickButton(driver, By.xpath("//div[starts-with(@class,'btn-group')]//button[@class='btn btn-primary btn-block']"), "Start now");
		//Select Exam Type
		String strExamType = "IELTS for UKVI (General Training)";
		if(!selectExamType(strExamType))
			System.out.println("Exam type "+ strExamType + "not available");
		else{
			objComm.sleepTime(driver, 3);
			clickButton(driver, By.xpath("//div[starts-with(@class,'btn-group')]//button[@class='btn btn-primary']"), "Next");
			objComm.waitForElement(driver, By.xpath("//select[@ng-model='vm.selectedLocation']"), 10);
			objComm.findAndSelectOptions(driver, By.xpath("//select[@ng-model='vm.selectedLocation']"), "Select Location", "Any");
			objComm.findAndSelectOptions(driver, By.xpath("//select[@ng-model='vm.selectedMonth']"), "Select Month", 1);
			objComm.sleepTime(driver, 5);
			clickButton(driver, By.xpath("//div[starts-with(@class,'btn-group')]//button[@class='btn btn-primary']"), "Next");
			//objComm.waitForElement(driver, By.xpath("//*[starts-with(@class,'page-header')][@text='Choose your test date:']"), 20);
			objComm.sleepTime(driver, 15);
			clickButton(driver, By.xpath("//table[starts-with(@class,'table')]//button[@class='btn btn-primary btn-sm']"), "Select date");
			objComm.sleepTime(driver, 5);
			clickButton(driver, By.xpath("//table[starts-with(@class,'table')]//button[@class='btn btn-primary btn-sm']"), "Book Now");
			/*objComm.waitForElement(driver, By.xpath("//input[@type='checkbox'][contains(@name,'userHasAccepted')]"), 10);
			objComm.findAndClick(driver, By.xpath("//input[@type='checkbox'][contains(@name,'userHasAccepted')]"), "Ack Checkbox");
			objComm.sleepTime(driver, 1);
			clickButton(driver, By.xpath("//div[starts-with(@class,'btn-group')]//button[@class='btn btn-primary']"), "Next");*/			
		}		
	}
	
	public void enterCandidateDetails(){
		objComm.waitTillPageload_UKVI(driver, 10);
		objComm.waitForElement(driver, By.xpath("//select[contains(@ng-model,'vm.data.eeCounter_Title')]"), 10);
		objComm.findAndSelectOptions(driver, By.xpath("//select[contains(@ng-model,'vm.data.eeCounter_Title')]"), "Select Title", 1);
		//objComm.findAndEnter(driver, By.xpath("//form//input[@placeholder='First (given) Name(s)']"), strFirstName, "FirstName");
		//objComm.findAndEnter(driver, By.xpath("//form//input[@placeholder='Last Name']"), "Test", "LastName");
		objComm.findAndEnter(driver, By.xpath("//form//input[contains(@ng-model,'vm.data.dob')]"), "04.05.1989", "DOB");
		selectGender("Male");
		objComm.findAndEnter(driver, By.xpath("//form//input[@placeholder='Address Line 1']"), "12,Church St", "AddressLine1");
		objComm.findAndEnter(driver, By.xpath("//form//input[@placeholder='City']"), "Chennai", "City");
		objComm.findAndSelectOptions(driver, By.xpath("//select[@placeholder='Country']"), "Country", "India");
		objComm.findAndEnter(driver, By.xpath("//form//input[@placeholder='Telephone']"), "999999999", "Telephone");
		clickButton(driver, By.xpath("//button[@class='btn btn-primary'][@type='submit']"), "Next");
		objComm.waitForElement(driver, By.xpath("//input[@name='userHasAccepted'][@value='true'][@type='radio']"), 10);
		objComm.findAndClick(driver, By.xpath("//input[@name='userHasAccepted'][@value='true'][@type='radio']"), "Confirm Name and DOB checkbox");
		objComm.sleepTime(driver, 1);
		clickButton(driver, By.xpath("//button[@class='btn btn-primary']"), "Next");
		objComm.findAndSelectOptions(driver, By.xpath("//select[@placeholder='Identification Document:']"), "Identification Document", "Passport");
		objComm.findAndEnter(driver, By.xpath("//input[@placeholder='Identification Document Number:']"), "PASS"+objComm.generatePIN(), "Identification DocumentNo");
		objComm.findAndEnter(driver, By.xpath("//input[@name='idDocExpiryDate']"), "12.12.2050", "Identification Document Expiry");
		objComm.findAndEnter(driver, By.xpath("//input[@placeholder='Identification Document Issuing Authority:']"), "Test", "Identification Document Issuing Authority");		
		objComm.findAndClick(driver, By.xpath("//input[@name='studyAtUniversity'][@value='false'][@type='radio']"), "Planning to study PG - No");
		objComm.findAndClick(driver, By.xpath("//input[@type='radio'][@name='takenBefore'][@value='true']"), "Taken IELTS before- YES");
		objComm.findAndSelectOptions(driver, By.xpath("//select[@placeholder='First Language:']"), "First language", 2);
		objComm.findAndSelectOptions(driver, By.xpath("//select[@placeholder='Country of Nationality:']"), "Country of Nationality", 2);
		objComm.findAndSelectOptions(driver, By.xpath("//select[@placeholder='What is your occupation sector?']"), "Occupation sector", 2);
		objComm.findAndSelectOptions(driver, By.xpath("//select[@placeholder='What is your occupation level?']"), "Occupation level", 2);
		objComm.findAndSelectOptions(driver, By.xpath("//select[@placeholder='Why are you taking the test?']"), "Why are you taking test?", 2);
		objComm.findAndSelectOptions(driver, By.xpath("//select[@placeholder='Which country are you applying to/intending to go to?']"), "Which Country", 2);
		objComm.findAndSelectOptions(driver, By.xpath("//select[@placeholder='What level of education have you completed?']"), "EducationCompleted", 2);
		objComm.findAndSelectOptions(driver, By.xpath("//select[@placeholder='How many years have you been studying English?']"), "English study in years", 2);
		clickButton(driver, By.xpath("//button[@type='submit']"), "Next");
		objComm.waitForElement(driver, By.xpath("//input[@name='userHasAccepted'][@value='true'][@type='radio']"), 10);
		objComm.findAndClick(driver, By.xpath("//input[@name='userHasAccepted'][@value='true'][@type='radio']"), "Confirm Name and DOB checkbox");
		clickButton(driver, By.xpath("//button[contains(@class,'btn btn-primary')]"), "Next");
		objComm.sleepTime(driver, 3);
		objComm.waitTillPageload_UKVI(driver, 20);
		objComm.waitForElement(driver, By.xpath("//div[@id='testTakerDetails']"), 10);		
		objComm.findAndClick(driver, By.xpath("//div[contains(@class,'checkbox')]//input[@type='checkbox'][@ng-model='vm.submitEnable']"), "Yes I Agree checkbox");
		clickButton(driver, By.xpath("//button[@class='btn btn-primary btn-block']"), "Next");
		objComm.sleepTime(driver, 3);
		objComm.waitTillPageload_UKVI(driver, 20);
		//((JavascriptExecutor)driver).executeScript("window.scrollBy(0,900)");
		if(!clickButton(driver, By.xpath("//label/button[contains(@class,'btn')][@type='button']"), "Read Full Version"))
			objComm.findAndClick(driver, By.xpath("//button[text()='Read Full Version']"), "Read Full Version");
		clickButton(driver, By.xpath("//div[@class='modal-dialog']//div[@class='modal-content']//div[contains(@class,'modal-footer')]//button"), "Ok");		
		objComm.sleepTime(driver, 2);
		objComm.findAndClick(driver, By.xpath("//button[text()='Accept T&Cs']"), "Accept T&Cs");
		objComm.sleepTime(driver, 3);
		objComm.waitTillPageload_UKVI(driver, 20);
		objComm.findAndClick(driver, By.xpath("//a[@title='Pay Offline']"), "Pay Offline");
		objComm.sleepTime(driver, 3);
		objComm.waitTillPageload_UKVI(driver, 20);
		WebElement ele  = objComm.findObject(driver, By.xpath("//*[@exam-type-name='message']/span"), "Exam details");
		if(ele != null){
			try{
				System.out.println(ele.getText());
			}catch(Exception e){
				System.out.println("Exam details not found");
			}
		}
	}

	private void selectGender(String strGender) {
		WebElement ele = objComm.findObject(driver, By.xpath("//div[starts-with(@class,'btn-group')]"), "Gender-Male");
		if(ele!= null){
			List<WebElement> btnList = ele.findElements(By.cssSelector(".btn.btn-primary"));
			for(WebElement eachBtn : btnList){
				if((eachBtn.getText().replace(" ", "")).replace("\n", "").equalsIgnoreCase(strGender)){
					eachBtn.click();				
					break;
				}
			}
		}
	}

	public void enterCandidateDetails_Register(){	
		strEmail = "Test"+objComm.generatePIN()+"@gmail.com";
		String strFName = "Test";
		objComm.waitForElement(driver, By.xpath("//form[@name='signUpForm']//input[contains(@ng-model,'vm.registrationModel.firstName')]"), 10);
		objComm.findAndEnter(driver, By.xpath("//form[@name='signUpForm']//input[contains(@ng-model,'vm.registrationModel.firstName')]"), strFName, "FirstName");
		objComm.findAndEnter(driver, By.xpath("//form[@name='signUpForm']//input[contains(@ng-model,'vm.registrationModel.lastName')]"), "Test", "LaststName");
		objComm.findAndEnter(driver, By.xpath("//form[@name='signUpForm']//input[contains(@ng-model,'vm.registrationModel.email')]"), strEmail, "Email");
		objComm.findAndEnter(driver, By.xpath("//form[@name='signUpForm']//input[contains(@ng-model,'vm.registrationModel.password')]"), strPassword, "Password");
		objComm.findAndEnter(driver, By.xpath("//form[@name='signUpForm']//input[contains(@ng-model,'vm.registrationModel.confirmPassword')]"), strPassword, "Confirm Password");
		objComm.findAndClick(driver, By.xpath("//form[@name='signUpForm']//div[@class='form-group']//div[@class='well']//input[@type='checkbox'][@ng-model='checked']"), "Terms and conditions accepted Checkbox");
		/*try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.cssSelector("span[id=recaptcha-anchor]"));
			js.executeScript("arguments[0].setAttribute('aria-checked', 'true')",element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		clickButton(driver, By.xpath("//form[@name='signUpForm']//button[@type='submit'][@class='btn btn-primary btn-lg']"), "Complete Account Registration");
		objComm.sleepTime(driver, 3);
		objComm.waitTillPageload_UKVI(driver, 40);
		clickButton(driver, By.xpath("//button[@class='btn btn-primary']"), "Book Test");
		objComm.sleepTime(driver, 10);
	}
	
	private boolean selectExamType(String strExamType) {
		boolean isExamSelected = false;
		objComm.waitTillPageload_UKVI(driver, 25);
		List<WebElement> eleExamLbl = objComm.findObjectList(driver, By.xpath("//div[contains(@ng-repeat,'examType')]//label[contains(@class,'control-label')]"), "Examtype label");
		for(int i = 0; i < eleExamLbl.size(); i++){
			try {
				if(((eleExamLbl.get(i).getText()).replace(" ", "")).replace("\n", "").equalsIgnoreCase(strExamType.replace(" ", ""))){
					objComm.findAndClick(driver, eleExamLbl.get(i), By.xpath("//input[@type='radio'][@name='examType']"), strExamType + " radio button");
					isExamSelected = true;
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//FOR Loop
		return isExamSelected;
	}

	private boolean clickButton(WebDriver driver, By by, String strButtonText) {
		boolean btnFound = false; String strActualBtnText = "";		
		List<WebElement> btnList = objComm.findObjectList(driver, by, strButtonText + " button");
		if(btnList != null && btnList.size() > 0){
			objComm.waitTillPageload_UKVI(driver, 20);
		}
		btnList = objComm.findObjectList(driver, by, strButtonText + " button");
		for(int i = 0; i < btnList.size(); i++){
			try {				
				strActualBtnText = ((btnList.get(i).getText()).replace(" ", "")).replace("\n", "");
				System.out.println("Btn Txt:"+strActualBtnText);
				if((btnList.get(i).isDisplayed()) && (strActualBtnText.equalsIgnoreCase(strButtonText.replace(" ", "")))){
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnList.get(i));
					objComm.waitForElementToBeClickable(driver, btnList.get(i), 20, strButtonText + " button");
					btnList.get(i).click();
					btnFound = true;
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}//FOR Loop
		if(!btnFound){
			//tearDown();
			System.out.println("Button -"+ strButtonText + "- not found");
		}
		return btnFound;
	}

	
	

}
