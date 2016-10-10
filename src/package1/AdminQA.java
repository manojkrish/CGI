/*package package1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class AdminQA {
	private WebDriver driver;
    private String baseUrl;
    private static String strUserName = "first.contact@north-india-agent1.in";
    private static String strPassword = "P@ssword1";
    private static String strCity = "Indore";
    private static String strModule = "Academic";
    private static String strFirstName = "APE";
    private static String strLastName = "Part";
    private static String strAnotherFirstName = "PPTest";
    private static String strAnotherLastName = "Part";
    private static String strEmail = "vithya.sankaran@in.britishcouncil.org";
    private static String strDOB = "12/12/1985";
    private static String strDocExpDate = "12/12/2080";
    
    CommonComponents objComm = new CommonComponents();
    
	*//**
	 * @throws java.lang.Exception
	 *//*
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", objComm.getLibraryPath() + "chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://g1gsn0bms057.britishcouncil.org:822/";
		//timeout if site page does not load in 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	*//**
	 * @throws java.lang.Exception
	 *//*
	@After
	public void tearDown() throws Exception {
		//quit the test
	    driver.quit();
	}
	
	@Test
	public void test(){
		int counter = 5;
		//customerreg();	
		for(int i = 0; i <= counter; i++){
			switch (i){
			case 0:
				strFirstName = "IATest";
				strDOB = "12/12/1985";
				break;
			case 1:
				strFirstName = "IBTest";
				strDOB = "12/12/1981";
				break;
			case 2:
				strFirstName = "ICTest";
				strDOB = "12/12/1982";
				break;
			case 3:
				strFirstName = "IDTest";
				strDOB = "12/12/1983";
				break;
			case 4:
				strFirstName = "IETest";
				strDOB = "12/12/1984";
				break;
			case 5:
				strFirstName = "IFTest";
				strDOB = "12/12/1986";
				break;
			}
			loginAndAddNewRegistration();			
		}
	}
	
	@Test
    public void loginAndAddNewRegistration(){
    	//Launch
    	driver.get(baseUrl);
    	//Navigate to Tab & Level
    	navigateToLevel("India", "British Council South", "Calicut");
    	
    	
    }
	
	private void navigateToLevel(String strCountry, String strCentre, String strVenue){
		WebElement eleMainMenuDiv = null; boolean isFound = false;
		eleMainMenuDiv = objComm.findObject(driver, By.xpath("//div[contains(@id,'Menu1_TreeViewDisplay')]"), "Main Menu");
    	if(eleMainMenuDiv != null){
    		List<WebElement> menuListParent = objComm.findObjectList(driver, eleMainMenuDiv, By.tagName("ul"), "Country, Centre and Venue Parent UL List");
    		menuListParent = findLevel(menuListParent, strCountry);
    		if(menuListParent == null || menuListParent.size() == 0){
    			menuListParent = selectLevel(menuListParent, "Global");
    			if(menuListParent != null && menuListParent.size() > 0)
    				isFound = true;
    		}else
    			isFound = true;
    		if(isFound){
    			List<WebElement> CountryListParent = selectLevel(menuListParent, strCountry);
    			if(CountryListParent != null && CountryListParent.size() > 0){
    				List<WebElement> CentreListParent = findLevel(CountryListParent, strCentre);
    				if(CentreListParent != null || CentreListParent.size() > 0){
    					if(strVenue == null || strVenue.equalsIgnoreCase("")){
    						CentreListParent.get(0).click();//Click on Centre name
    					}else{//Expand Centre and click on Venue name
    						List<WebElement> VenueListParent = selectLevel(CentreListParent, strCentre);
    						if(VenueListParent != null || VenueListParent.size() > 0){
    							
    						}
    					}
    				}else{
    					System.out.println(strCentre + " - Centre not found");
    				}
    			}//Country Expanded
    			CountryListParent = null;
    		}//Global Expanded
    	}				
	}
	
	private void navigateToLevel1(String strCountry, String strCentre, String strVenue){
		boolean isFound = false;
		//Select Country
		List<WebElement> wEleCtLink = findLevel(strCountry);
		if(wEleCtLink == null){	//Country not visible, Expand Global
			isFound = selectLevel("Global");			
		}else
			isFound = true;
		if(isFound){	//Expand Country
			isFound = selectLevel(strCountry);	
		}
		//Select Centre & Venue
		wEleCtLink = findLevel(strCentre);
		if(wEleCtLink == null){	//Centre not visible, Expand Country			
			isFound = selectLevel(strCountry);			
		}else
			isFound = true;
		if(isFound){	//IF I
			if(strVenue == null || strVenue.equalsIgnoreCase("")){	//If venue not required, Cick on Centre name
				objComm.findAndClicklinkByText(driver, wEleCtLink.get(0), strCentre);
				isFound = true;
			}else{
				wEleCtLink = findLevel(strCentre);
				for(WebElement eleEachCentreLi: wEleCtLink){
					selectLevel(ulList, strLinkName)
				}
			}
		}	//IF I
	}
	
	private List<WebElement> findLevel(List<WebElement> menuListParent, String strLinkName){
		List<WebElement> expmenuList = objComm.findObjectList(driver, By.xpath("//div[contains(@id,'Menu1_TreeViewDisplay')]"), "Main Menu");
//		WebElement eleMainMenuDiv = objComm.findObject(driver, By.xpath("//div[contains(@id,'Menu1_TreeViewDisplay')]"), "Main Menu");
//    	if(eleMainMenuDiv != null){
    		expmenuList.clear();
    		try {
    			//List<WebElement> menuListParent = objComm.findObjectList(driver, eleMainMenuDiv, By.tagName("ul"), "Country, Centre and Venue Parent UL List");
    			for(WebElement eachUL : menuListParent){//FOR I
    				List<WebElement> menuList = objComm.findObjectList(driver, eachUL, By.tagName("li"), "Country, Centre and Venue List");
            		for(WebElement eachLink : menuList){//FOR II
            			try {
        					if(eachLink.getText().trim().equalsIgnoreCase(strLinkName)){
        						expmenuList.add(eachLink);
        					}
        				} catch (Exception e) {
        					// TODO: handle exception
        				}
            		}//FOR II
    			}//FOR I    			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Link ["+ strLinkName +"] not found");
			}
//    	}eleMainMenuDiv = null;
    	if(expmenuList.size() == 0)
    		System.out.println("Link ["+ strLinkName +"] not found");
    	return expmenuList;
	}
	
	private List<WebElement> selectLevel(List<WebElement> ulList, String strLinkName){
		WebElement wEleImage = null; boolean isImageFound= false;
		List<WebElement> expMenuList = ulList;
    	if(ulList.size() > 0){
    		expMenuList.clear();
    		for(WebElement eachUL : ulList){
    			try {
        			List<WebElement> menuList = objComm.findObjectList(driver, eachUL, By.tagName("li"), "Country, Centre and Venue List");
            		for(WebElement eachLink : menuList){
            			try {
        					if(eachLink.getText().trim().equalsIgnoreCase(strLinkName)){	//Exp link found
        						wEleImage = objComm.findObjectFromWebElement(driver, eachLink, By.tagName("img"), strLinkName + " - Expand image");
        						if(wEleImage != null){	//Click on Expand image
        							wEleImage.click();
        							expMenuList.add(eachLink);
        							isImageFound = true;
        						}
        						break;
        					}
        				} catch (Exception e) {
        					// TODO: handle exception
        				}
            		}
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				System.out.println("Link ["+ strLinkName +"] not found");
    			}
    		}//FOR UL List    		
    	}wEleImage = null;
    	if(!isImageFound)
    		System.out.println("Link ["+ strLinkName +"] not found");
    	return expMenuList;
	}
	
	private boolean selectLevel(String strLinkName){
		WebElement wEleImage = null; boolean isImageFound= false;
		WebElement eleMainMenuDiv = objComm.findObject(driver, By.xpath("//div[contains(@id,'Menu1_TreeViewDisplay')]"), "Main Menu");
    	if(eleMainMenuDiv != null){
    		try {
    			List<WebElement> menuList = objComm.findObjectList(driver, eleMainMenuDiv, By.tagName("li"), "Country, Centre and Venue List");
        		for(WebElement eachLink : menuList){
        			try {
    					if(eachLink.getText().trim().equalsIgnoreCase(strLinkName)){	//Exp link found
    						wEleImage = objComm.findObjectFromWebElement(driver, eachLink, By.tagName("img"), strLinkName + " - Expand image");
    						if(wEleImage != null){	//Click on Expand image
    							wEleImage.click();
    							isImageFound = true;
    						}
    						break;
    					}
    				} catch (Exception e) {
    					// TODO: handle exception
    				}
        		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Link ["+ strLinkName +"] not found");
			}
    	}eleMainMenuDiv = null; wEleImage = null;
    	if(!isImageFound)
    		System.out.println("Link ["+ strLinkName +"] not found");
    	return isImageFound;
	}
	
	public void switchToWindow(){
		for (String ctWnd : driver.getWindowHandles()){
			driver.switchTo().window(ctWnd);
			System.out.println("Wnd Title:"+driver.getTitle());
		}
	}
    
    public void navigateToTab(By by, String strTabName){
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
	    String value = "";	    	    
	    //Select Venue
	    WebElement eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlExamVenue_CDNew')]"),"ExamVenue Dropdown");
        Select select = new Select(eLe);
        select.selectByVisibleText(strCity);
        //Select Module        
        eLe = objComm.findWebElement(driver, By.xpath("//input[contains(@id,'txtFamilyName_CDNew_txtEntryControl')]"),"First Name");
        eLe.sendKeys("{TAB}");
        eLe = objComm.findWebElement(driver, By.xpath("//span[contains(@id,'lblExamModule_lblEntryTextLabel_CDNew')]"),"Lable Module");
        eLe.click();
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlExamModule_CDNew')]"),"Module Dropdown");
	    objComm.selectElement(driver, eLe, strModule, "Venue");
        //select = new Select(eLe);
        for(int i = 0; i < select.getOptions().size(); i++){
        	select.selectByIndex(i);
        	eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlExamModule_CDNew')]"),"Module Dropdown");
        	//if(!select.getFirstSelectedOption().getText().trim().equalsIgnoreCase("(Choose"))
        	if(!(eLe.getText().trim().equalsIgnoreCase("(Choose")))
        		break;
        }
        //select.selectByIndex(1);
        //Select Date
//        eLe = objComm.findWebElement(driver, By.xpath("//span[contains(@id,'lblExamDate_lblEntryTextLabel_CDNew')]"),"Lable ExamDate");
//        eLe.click();
	    eLe = objComm.findWebElement(driver, By.xpath("//select[contains(@id,'ddlExamDate_CDNew')]"),"ExamDate Dropdown");
        select = new Select(eLe);
        for(int i = 0; i < select.getOptions().size(); i++){
        	select.selectByIndex(i);
        	if(!select.getFirstSelectedOption().getText().trim().equalsIgnoreCase("(Choose"))
        		break;
        }
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
*/