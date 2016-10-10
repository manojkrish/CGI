/**
 * 
 */
package package1;


import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author vithyasankaran
 *
 */
public class CJQA {
	private WebDriver driver;
    private String baseUrl = "http://g1gsn0bms057.britishcouncil.org:12223/";
    /*private static String strCountry = "Poland";
    private static String strCity = "Indore";*/
    private static String strCountry = "India";
    private static String strCity = "Delhi";
    private static String strModule = "Academic"; //"Academic"; // "General Training";
    private static String strFirstName = "KTest";
    private static String strLastName = "Candid";
    private static String strEmail = "vithya.sankaran@in.britishcouncil.org";
    private static String strDOBDate = "3";
    private static String strDOBYear = "1976";
    private static String strDocDate = "3";
    private static String strDocYear = "2059";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		CommonComponents objComm = new CommonComponents();
		System.setProperty("webdriver.chrome.driver", objComm.getLibraryPath() + "chromedriver.exe");
		driver = new ChromeDriver();	
//		System.setProperty("webdriver.ie.driver", objComm.getLibraryPath() + "IEDriverServer.exe");
//		driver = new InternetExplorerDriver();		
		//timeout if site page does not load in 30 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//quit the test
	    driver.quit();
	}	
	
	@Test
	public void test(){
			
		int counter = 7;
		for(int i = 0; i <= counter; i++){
			switch (i){
			case 0:
				strFirstName = "Atest";
				strDOBYear = "1973";
				break;
			case 1:
				strFirstName = "Btest";
				strDOBYear = "1966";
				break;
			case 2:
				strFirstName = "Ctest";
				strDOBYear = "1977";
				break;
			case 3:
				strFirstName = "Dtest";
				strDOBYear = "1972";
				break;
			case 4:
				strFirstName = "Etest";
				strDOBYear = "1974";
				break;
			case 5:
				strFirstName = "Ftest";
				strDOBYear = "1974";
				break;			
			case 6:
				strFirstName = "Gtest";
				strDOBYear = "1976";
				break;
			case 7:
				strFirstName = "Htest";
				strDOBYear = "1977";
				break;
			}
			customerreg();			
		}
	}
	
	public void customerreg(){
		CommonComponents objComm = new CommonComponents();
		try{		
			//navigate to base url
	        driver.get(baseUrl);
	        //Select Country
	        WebElement eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlCountry')]"),"Country Dropdown");
	        //driver.findElement(By.xpath("//select[contains(@id,'ddlCountry')]"));
	        if(eLe != null){                
	        	Select countryDD = new Select(eLe);
	            countryDD.selectByVisibleText(strCountry);
	        }
	        //Click on Continue button
	        eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'imgbRegisterBtn')]"),"Continue button");
	        eLe.click();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        //Select Month Year
	        eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlDateMonthYear')]"),"MonthYear Dropdown");
	        Select MonthYear = new Select(eLe);
	        MonthYear.selectByIndex(0);
	        //MonthYear.selectByVisibleText("January 2016");
	        //Select City
	        eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlTownCityVenue')]"),"City/Town Dropdown");
	        Select cityTown = new Select(eLe);
	        cityTown.selectByVisibleText(strCity);
	        //Select Module
	        eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlModule')]"),"Module Dropdown");
	        Select module = new Select(eLe);
	        module.selectByVisibleText(strModule);
	        //Click on Find button
	        eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'imgbSearch')]"),"Find button");
	        eLe.click();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        //Click on Apply button
	        eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'btnBook')]"),"Apply button");
	        eLe.click();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        //Select Agree checkbox
	        eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'chkAccept')]"),"Agree checkbox");
	        eLe.click();
	        //Click on Continue button
	        eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'imgbContinue')]"),"Continue button");
	        eLe.click();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        
	        //Enter Mandate Data
	        enterMandatoryData();  
	        
	        //Click on Continue button
	        eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'ibtnContinue')]"),"Continue button");
	        eLe.click();                  
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        //Choose your Payment method
	        eLe = objComm.findObject(driver, By.xpath("//select[contains(@id,'ddlPaymentMethod')]"),"Choose your Payment method Dropdown");
	        if(eLe != null){
		        module = new Select(eLe);
		        module.selectByVisibleText("Pay Later");
		        objComm.sleepTime(driver, 5);
	        }else{
	        	objComm.logError("customerreg", "Choose your Payment method - option is not available");
	        	System.out.println("Choose your Payment method - option is not available");
	        }
	        //Click on Apply Now button
	        eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'ibtnBookNow')]"),"Apply Now button");
	        eLe.click();               
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        //assertTrue(objComm.isTextPresent(driver, "ONLINE APPLICATION RECEIVED"));
		}catch(Exception e){
			objComm.logError("customerreg", e.getMessage());
			System.out.println(e.getMessage());
			//objComm.capturePage(driver,"customerreg");
		}finally{
			
		}
	}
		public void enterMandatoryData(){			
			CommonComponents objComm = new CommonComponents();		
		    String value = "";	
		    objComm.acceptAlert(driver);
		    //Select Title		
		    WebElement eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlTitle')]"),"Title Dropdown");		
		    Select select = new Select(eLe);		
		    select.selectByVisibleText("Mr");		
		    //Enter FirstName		
		    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtOtherNames')]"),"First Name");		
		    eLe.sendKeys(strFirstName);		
		    //Enter LastName		
		    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtFamilyName')]"),"Last Name");		
		    eLe.sendKeys(strLastName);		
		    //Select First Language		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlFirstLanguage')]"),"FirstLang Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("English");		
		    //Select Country of Nationality		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlCountryRegion')]"),"Country of Nationality Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("United Kingdom");		
		    //Email Address		
		    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtEmail')]"),"Email");		
		    eLe.sendKeys(strEmail);		
		    //Confirm Mail		
		    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtEmailConfirm')]"),"Email");		
		    eLe.sendKeys(strEmail);		
		    //Select DOB  		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlDays')]"),"DOB Date Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText(strDOBDate);		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlMonths')]"),"DOB Month Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("March");		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlYears')]"),"DOB Year Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText(strDOBYear);		
		    //Select Identification Document		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlIdDocument')]"),"Identification Document");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("Passport");
		    				    		
		    //Enter Address		
		    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtAddr1')]"),"Address1");		
		    eLe.sendKeys("12 West Str");		
		    //Enter Addr2		
		    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtAddr2')]"),"Address2");		
		    eLe.sendKeys("Church Cross Lane");		
		    //Select Country		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlAddrCountry')]"),"Country Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("United Kingdom");
		  
		    //Enter Telephone
		    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtTelephone')]"),"Telephone");		
		    eLe.sendKeys("1286754");
		    //Select Occupation Sector	
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlOccupationSector')]"),"Occupation Sector Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("Administrative Services");		
		    //Select Occupation Level		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlOccupationStatus')]"),"Occupation Level Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("Employer/Partner");		
		    //Y are you Tacking Test		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlReasonForTest')]"),"ReasonForTest Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("Employment");		
		    //Dest Country		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlDestinationCountry')]"),"Dest Country Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("United Kingdom");		
		    //Edu Completed		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlEducationLevel')]"),"Edu Level Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("Post-graduate");		
		    //year Eng		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlEnglishStudyInYears')]"),"year Eng Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("2");   
		    
		  //Identification Document No		
		    value = objComm.generatePIN();		
		    value = "PU7234" + value;			    
		    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtIdDocumentNumber')]"),"Document No");		
		    try {
				eLe.sendKeys(value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				if(e.getMessage().contains("stale element reference: element is not attached to the page document")){
					eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtIdDocumentNumber')]"),"Document No");	
					eLe.sendKeys(value);
				}
			}		
		    //Select ID Doc Exp Date		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlDocIdDay')]"),"ID Doc Exp Date Day Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText(strDocDate);		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlDocIdMonth')]"),"ID Doc Exp Date Month Dropdown");		
		    select = new Select(eLe);		
		    select.selectByVisibleText("March");		
		    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlDocIdYear')]"),"ID Doc Exp Date year Dropdown");
		    select = new Select(eLe);		
		    select.selectByVisibleText(strDocYear);
		
		}
	
	

}
