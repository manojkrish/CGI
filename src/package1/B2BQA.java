/**
 * 
 */
package package1;


import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * @author vithyasankaran
 *
 */
public class B2BQA {
    private WebDriver driver;
    private String baseUrl = "http://g1gsn0bms057.britishcouncil.org:12222"; // "http://g1gsn0bms057.britishcouncil.org:842";
    private static String strUserName = "first.contact@north-india-agent1.in"; //"first.contact@north-india-agent1.in";
    private static String strPassword = "P@ssword1"; //"P@ssword1";
    private static String strCity = "Delhi";
    private static String strModule = "Academic";
    private static String strFirstName = "APE";
    private static String strLastName = "Part";
    private static String strAnotherFirstName = "PITest";
    private static String strAnotherLastName = "Part";
    private static String strEmail = "vithya.sankaran@in.britishcouncil.org";
    private static String strDOB = "12/12/1985";
    private static String strDocExpDate = "12/12/2080";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		CommonComponents objComm = new CommonComponents();
		System.setProperty("webdriver.chrome.driver", objComm.getLibraryPath() + "chromedriver.exe");
		driver = new ChromeDriver();
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
		int counter = 5;	
		for(int i = 0; i <= counter; i++){
			switch (i){
			case 0:
				strFirstName = "DATest";
				strDOB = "12/12/1975";
				break;
			case 1:
				strFirstName = "DBTest";
				strDOB = "12/12/1961";
				break;
			case 2:
				strFirstName = "DCTest";
				strDOB = "12/12/1985";
				break;
			case 3:
				strFirstName = "DDTest";
				strDOB = "12/12/1989";
				break;
			case 4:
				strFirstName = "DETest";
				strDOB = "12/12/1964";
				break;
			case 5:
				strFirstName = "DFTest";
				strDOB = "12/12/1966";
				break;
			}
			loginAndAddNewRegistration();			
		}
	}
	
	@Test
    public void loginAndAddNewRegistration(){
		CommonComponents objComm = new CommonComponents();
    	//Launch
    	driver.get(baseUrl);
    	//Login
    	WebElement eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'ContentPlaceHolder1_txtUserName')]"),"UserName");		
	    eLe.sendKeys(strUserName);	
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'ContentPlaceHolder1_txtPassword')]"),"Password");		
	    eLe.sendKeys(strPassword);
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'ContentPlaceHolder1_btnLogin')]"),"Login button");		
	    eLe.click();
    	
    	//Navigate to Candidates tab
    	navigateToTab(By.xpath("//div[contains(@id,'mbrMaster_dTopMenuBar')]//ul"),"Candidates");   
    	driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
    	//Click on AddNew
    	navigateToTab(By.xpath("//div[contains(@id,'ContentPlaceHolder1_mbrNewCandidates_dTopMenuBar')]//ul"),"Add New...");  
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
    	//Accept Terms And Condition
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'ucTermsAndConditions_chkAccept')]"),"Accept Terms And Condition");
	    if(eLe == null || !eLe.isDisplayed()){
	    	navigateToTab(By.xpath("//div[contains(@id,'ContentPlaceHolder1_mbrNewCandidates_dTopMenuBar')]//ul"),"Add New...");  
	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    	eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'ucTermsAndConditions_chkAccept')]"),"Accept Terms And Condition");
	    	eLe.click();
	    }else
	    	eLe.click();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    //Accept Confirmation dialog
	    WebElement parentDiv = objComm.findWebElement(driver, By.xpath("//div[contains(@class,'ui-dialog-buttonpane ui-widget-content')]"), "Parent div");
	    if(parentDiv != null){
	    	eLe = objComm.findWebElement(driver, parentDiv, By.tagName("button"), "OK button on Confirmation dialog");
		    eLe.click();
	    }
    	//Enter Candidate & Test details
    	enterMandatoryData();
    	
    	//Add Another Candidate
    	//Accept Confirmation dialog
    	boolean flag = false;
    	List<WebElement> parentDivs = objComm.findObjectList(driver, By.xpath("//div[contains(@class,'ui-dialog-buttonpane ui-widget-content')]//div[@class='ui-dialog-buttonset']"), "Parent div");
	    for(WebElement parentEle : parentDivs){ 	
	    	List<WebElement> buttonList = objComm.findObjectList(driver, parentEle, By.tagName("button"), "OK button on Confirmation dialog");
	    	for(WebElement eachButton : buttonList){
	    		 if(eachButton.getText().trim().equalsIgnoreCase("Add Another Candidate")){
	    			flag = true;;
	 	    		eLe.click();
	 	    		strFirstName = strAnotherFirstName;
	 	    		strLastName = strAnotherLastName;
	 	    		//Enter Candidate & Test details
	 	        	enterMandatoryData();
	 	        	break;
	 		    }
	    	}
	    	if(flag)
	    		break;
	    }    
	    
    	/*WebElement parentEle = objCommonFunctions.findWebElement(driver, By.xpath("//ul[contains(@id,'ContentPlaceHolder1_mbrNewCandidates_dTopMenuBar')]//span[@text='Add New...']"), "NewCandidates Menu bar");
    	if(parentEle != null){
    		parentEle.click();
    	}else
    		System.out.println("Add New Candidates button not available");*/
    }
	
	public void switchToWindow(){
		for (String ctWnd : driver.getWindowHandles()){
			driver.switchTo().window(ctWnd);
			System.out.println("Wnd Title:"+driver.getTitle());
		}
	}
    
    public void navigateToTab(By by, String strTabName){
    	CommonComponents objComm = new CommonComponents();
    	boolean linkFound = false;
    	WebElement parentEle = objComm.findWebElement(driver, by, "TopMenu bar");
    	if(parentEle != null){
    		try{
    			List<WebElement> liList = parentEle.findElements(By.tagName("li"));
    			for(WebElement wEle : liList){
    				if(wEle.getText().equalsIgnoreCase(strTabName)){
    					System.out.println("---"+wEle.getText());
    					wEle.click();
    					linkFound = true;
    					break;
    				}
    			}
    		}catch(Exception e){
    			objComm.logError("objComm", e.getMessage());
    		}
    	}
    	if(!linkFound)
    		objComm.logError("objComm", strTabName + " Tab Not Found");
    }
	
	   
        //assertTrue(objComm.isTextPresent(driver, "ONLINE APPLICATION RECEIVED"));
		
	public void enterMandatoryData(){			
		CommonComponents objComm = new CommonComponents();		
	    String value = "";	    	    
	    //Select Venue
	    WebElement eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlExamVenue_CDNew')]"),"ExamVenue Dropdown");
        Select select = new Select(eLe);
        select.selectByVisibleText(strCity);
        //Select Module        
        /*eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtFamilyName_CDNew_txtEntryControl')]"),"First Name");
        eLe.sendKeys("{TAB}");*/
        eLe = objComm.findWebElement(driver, By.xpath("//span[contains(@id,'lblExamModule_lblEntryTextLabel_CDNew')]"),"Lable Module");
        eLe.click();
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlExamModule_CDNew')]"),"Module Dropdown");
	    objComm.selectElement(driver, eLe, strModule, "Venue");
        //select = new Select(eLe);
        /*for(int i = 0; i < select.getOptions().size(); i++){
        	select.selectByIndex(i);
        	eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlExamModule_CDNew')]"),"Module Dropdown");
        	//if(!select.getFirstSelectedOption().getText().trim().equalsIgnoreCase("(Choose"))
        	if(!(eLe.getText().trim().equalsIgnoreCase("(Choose")))
        		break;
        }*/
        //select.selectByIndex(1);
        //Select Date
//        eLe = objComm.findWebElement(driver, By.xpath("//span[contains(@id,'lblExamDate_lblEntryTextLabel_CDNew')]"),"Lable ExamDate");
//        eLe.click();
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlExamDate_CDNew')]"),"ExamDate Dropdown");
        select = new Select(eLe);
       /* for(int i = 0; i < select.getOptions().size(); i++){
        	select.selectByIndex(i);
        	if(!select.getFirstSelectedOption().getText().trim().equalsIgnoreCase("(Choose"))
        		break;
        }*/
        select.selectByIndex(1);
        //Enter FirstName		
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtFamilyName_CDNew_txtEntryControl')]"),"First Name");		
	    eLe.sendKeys(strFirstName);	
	    //Select Title		
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlTitle_CDNew_ddlEntryControl')]"),"Title Dropdown");		
	    select = new Select(eLe);		
	    select.selectByVisibleText("Mr");
	    //Enter LastName		
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtOtherName_CDNew_txtEntryControl')]"),"Last Name");		
	    eLe.sendKeys(strLastName);
	    //Enter Address		
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtAddress1_CDNew_txtEntryControl')]"),"Address1");		
	    eLe.sendKeys("12 West Str");
	    //Select Country		
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlCountry_CDNew_ddlEntryControl')]"),"Country Dropdown");		
	    select = new Select(eLe);		
	    select.selectByVisibleText("United Kingdom");
	    //Email Address		
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtEmailAddress_CDNew_txtEntryControl')]"),"Email");		
	    eLe.sendKeys(strEmail);
	    //Enter DOB		
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtDOB_CDNew_txtEntryControl')]"),"DOB");		
	    eLe.sendKeys(strDOB);
	    //Select Identification Document		
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlIdDocument_CDNew_ddlEntryControl')]"),"Identification Document");		
	    select = new Select(eLe);		
	    select.selectByVisibleText("Passport");		
	    //Identification Document No		
	    value = objComm.generatePIN();		
	    value = "PPN734" + value;		
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtIDNumber_CDNew_txtEntryControl')]"),"Document No");		
	    eLe.sendKeys(value);
	    //Enter Document Expiry Date		
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtIDExpiryDate_CDNew_txtEntryControl')]"),"Document ExpDate");		
	    eLe.sendKeys(strDocExpDate);
	    //Select Country origin
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlCountryOrigin_CDNew_ddlEntryControl')]"),"Country origin Dropdown");		
	    select = new Select(eLe);		
	    select.selectByIndex(1);   	    		    
	    //Select First Language		
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlFirstLanguage_CDNew_ddlEntryControl')]"),"FirstLang Dropdown");		
	    select = new Select(eLe);		
	    select.selectByIndex(1);
	    //Select OcupationSectior	
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlOccupationSector_CDNew_ddlEntryControl')]"),"OcupationSectior Dropdown");		
	    select = new Select(eLe);		
	    select.selectByIndex(1);
	    //Select OcupationStatus	
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlOccupationStatus_CDNew_ddlEntryControl')]"),"OcupationStatus Dropdown");		
	    select = new Select(eLe);		
	    select.selectByIndex(1);
	    //Select Test Reason	
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlTestReason_CDNew_ddlEntryControl')]"),"Test Reason Dropdown");		
	    select = new Select(eLe);		
	    select.selectByIndex(1);
	    //Select ApplyingTo
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlCountryApplyingTo_CDNew_ddlEntryControl')]"),"ApplyingTo Dropdown");		
	    select = new Select(eLe);		
	    select.selectByIndex(1);	
	    //Select EducationLevel
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlEducationLevel_CDNew_ddlEntryControl')]"),"EducationLevel Dropdown");		
	    select = new Select(eLe);		
	    select.selectByIndex(1);
	    //Select Year English Study
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlYearsEnglishStudy_CDNew_ddlEntryControl')]"),"Year English Study Dropdown");		
	    select = new Select(eLe);		
	    select.selectByVisibleText("2");
	    //Click on Save button
	    eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'ContentPlaceHolder1_dialogCandidateNew_bSave')]"),"Save button");
	    eLe.click();
	    
	}
	
	

}
