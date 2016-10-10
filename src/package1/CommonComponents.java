package package1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.apache.commons.io.FileUtils;

public class CommonComponents {

	
	public static String StringObj = null, CurrentwinHandle = null, alertMsg = null;
	public static int cntr = 1;
		
	/**
	 * Method Name :getRandomNumberFrom
	 * Parameter Details (if any):
	 * Need/Purpose for the method :To generate random int number		
	 */	
	public int getRandomNumberFrom(int min, int max) {
        Random foo = new Random();
        int randomNumber = foo.nextInt((max + 1) - min) + min;
        return randomNumber;
    }
	
	public String generatePIN()		
	{
		String randomNo = "1267";		
		//generate a 4 digit integer 1000 <10000		
		int randomPIN = (int)(Math.random()*9000)+1000;		
		//Store integer in a string		
		randomNo = String.valueOf(randomPIN);
		 return randomNo;		
	}
	
	public WebElement findWebElement(WebDriver driver, By by, String fieldName){
      WebElement myDynamicElement = (new WebDriverWait(driver, 25)).until(ExpectedConditions.presenceOfElementLocated(by));	
      return myDynamicElement;
	}
	
	//WebElement myDynamicElement = driver.findElement(by);	
    /*if(wEle == null){

            try {

                                    Thread.sleep(1000);

                        } catch (InterruptedException e) {

                                    // TODO Auto-generated catch block

                                    e.printStackTrace();

                        }

            do{

                        

                        cnt++;

            }while(wEle == null || cnt <=5);

    }*/        
	
	/*public static void waitTillBrowserLoad(){
		String tmpStr = null;
		int count = 0;
		while(count < 35){
			try{
//				if(Dlg_MessageFrmWebpage().exists()){
//					FunctionalComponents.close_ConfirmDialog();
//				}
				tmpStr =browser_htmlBrowser().getProperty(".readyState").toString();
				if("4".equals(tmpStr)){
					break;
				}
				sleep(4000);
			}catch(Exception e){
				e.printStackTrace();
				count++;
			}
		}
	}*/
	
	
/**
 * Method Name :sortArrayValue
 * Parameter Details (if any):
 * Need/Purpose for the method :Sort String array using sort method		
 */		  	  
  @SuppressWarnings({ "rawtypes" })
	public ArrayList sortArrayValue(ArrayList<String> pList){
		 java.util.Collections.sort(pList);
         return pList;
	}
	  
//	Finding Object
  public WebElement findObject(WebDriver driver,By by,String elementName){
	  WebElement wElement = null; int timeOut = 0;
	  do{
		  if(timeOut > 0){
			sleepTime(driver, 1);
		  }
		  try{
				wElement = driver.findElement(by);
		  }catch (NoSuchElementException e) {timeOut++;}
	  }while(wElement == null && timeOut < 10);
	    
	  if(wElement == null){
		  //report.updateTestLog("Verifying"+elementName, elementName+" does not available", Status.FAIL);
		  //throw new FrameworkException("Find "+elementName, elementName+" not found");
	  }
	return wElement;
  }
  
  public WebElement findAndClick(WebDriver driver, By by, String elementName){
	  WebElement wElement = null; int timeOut = 0;
	  do{
		  if(timeOut > 0){
			sleepTime(driver, 5);
		  }
		  try{
				wElement = driver.findElement(by);
				if(wElement != null){
					waitTillPageload_UKVI(driver, 10);
					//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wElement);
					wElement.click();
				}
		  }catch (NoSuchElementException e) {
			  try {
				if(e.getStackTrace().toString().toLowerCase().replace(" ", "").contains("elementnotvisible")){
					  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wElement);
					  wElement.click();
				  }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  sleepTime(driver, 5);
			  timeOut++;
			}
	  }while(wElement == null && timeOut < 4);
	    
	  if(wElement == null){
		  System.out.println(elementName+" not found");
	  }
	return wElement;
  }  
  
  public WebElement findAndClick(WebDriver driver, WebElement eleParentEle, By by, String elementName){
	  WebElement wElement = null; int timeOut = 4, cntr = 0;
	  do{
		  if(cntr > 0){
			sleepTime(driver, 5);
		  }
		  try{
				wElement = eleParentEle.findElement(by);
				if(wElement != null){
					try {
						wElement.click();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						if((e.getMessage().contains("Other element would receive the click:")) && (e.getMessage().contains("Element is not clickable at point"))){
							wElement = null;
							if(cntr >= timeOut)
								cntr = timeOut - 2;
							sleepTime(driver, 5);
						}
					}
				}
		  }catch (NoSuchElementException e) {
			  sleepTime(driver, 5);
			  cntr++;
			}
	  }while(wElement == null && cntr < timeOut);
	    
	  if(wElement == null){
		  System.out.println(elementName+" not found");
	  }
	return wElement;
  }
  
  public WebElement findAndSelectOptions(WebDriver driver,By by,String elementName, String strOption){
	  WebElement wElement = null; int timeOut = 0;
	  do{
		  if(timeOut > 0){
			sleepTime(driver, 5);
		  }
		  try{
				wElement = driver.findElement(by);
				if(wElement != null){
					Select slt = new Select(wElement);
					slt.selectByVisibleText(strOption);
				}
		  }catch (NoSuchElementException e) {
			  sleepTime(driver, 5);
			  timeOut++;
		  }
	  }while(wElement == null && timeOut < 4);
	    
	  if(wElement == null){
		  System.out.println(elementName+" not found");
	  }
	return wElement;
  }
  
  public WebElement findAndSelectOptions(WebDriver driver,By by,String elementName, int iIndex){
	  WebElement wElement = null; int timeOut = 0;
	  do{
		  if(timeOut > 0){
			sleepTime(driver, 5);
		  }
		  try{
				wElement = driver.findElement(by);
				if(wElement != null){
					Select slt = new Select(wElement);
					slt.selectByIndex(iIndex);
				}
		  }catch (NoSuchElementException e) {
			  sleepTime(driver, 5);
			  timeOut++;
			}
	  }while(wElement == null && timeOut < 4);
	    
	  if(wElement == null){
		  System.out.println(elementName+" not found");
	  }
	return wElement;
  }
  
  public WebElement findAndEnter(WebDriver driver,By by, String strValue, String elementName){
	  WebElement wElement = null; int timeOut = 0;
	  do{
		  if(timeOut > 0){
			sleepTime(driver, 5);
		  }
		  try{
				wElement = driver.findElement(by);
				if(wElement != null){
					wElement.clear();
					wElement.sendKeys(strValue);
					break;
				}
		  }catch (NoSuchElementException e) {
			  sleepTime(driver, 5);			  
			}
		  timeOut++;
	  }while(wElement == null && timeOut < 4);
	    
	  if(wElement == null){
		  System.out.println(elementName+" not found");
	  }
	return wElement;
  }
  
//	Finding Object from WebElement
	public WebElement findWebElement(WebDriver driver, WebElement wEleParent, By by,String elementName){
		  WebElement wElement = null; int timeOut = 0;
		  do{
			  if(timeOut > 0){
				  sleepTime(driver, 2);
			  }
			  try{
					wElement = wEleParent.findElement(by);
			  }catch (NoSuchElementException e) {timeOut++;}		  
		  }while(wElement == null && timeOut < 10);
		  try{
				wElement = wEleParent.findElement(by);
		  }catch (NoSuchElementException e) {
//			  //report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
		  }
		  if(wElement == null){
			  //report.updateTestLog("Verifying"+elementName, elementName+" does not available", Status.FAIL);
		  }
		return wElement;
	}
  
//Finding Object
	public List<WebElement> findObjectList(WebDriver driver,By by,String elementName){
	  List<WebElement> wElement = null; int timeOut = 0;
	  do{
		  if(timeOut > 0){
			  sleepTime(driver, 5);
		  }
		  try{
				wElement = driver.findElements(by);
		  }catch (NoSuchElementException e) {
			  sleepTime(driver, 5);			  
		  }
		  timeOut++;
	  }while(wElement == null && timeOut < 4);
	  	  
	  if(wElement == null){
		 System.out.println(elementName+" not found");
	  }
	  return wElement;
	}
	
	//Finding Object
		public List<WebElement> findObjectList(WebDriver driver, WebElement wEle,By by,String elementName){
		  List<WebElement> wElement = null; int timeOut = 0;
		  do{
			  if(timeOut > 0){
				  sleepTime(driver, 6);
			  }
			  try{
					wElement = wEle.findElements(by);
			  }catch (NoSuchElementException e) {
				  sleepTime(driver, 5);			  
			  }
			  timeOut++;
		  }while(wElement == null && timeOut < 10);
		  try{
				wElement = wEle.findElements(by);
		  }catch (NoSuchElementException e) {
//			  //report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
		  }	  
		  if(wElement == null){
			  //report.updateTestLog("Verifying"+elementName, elementName+" does not available", Status.FAIL);
		  }
		  return wElement;
		}
 
	public boolean isElementPresent(WebDriver driver,By by)
	{
	  	int timedOut = 10, cntr  =0;WebElement wEle = null;
		do {
			if(cntr > 0){
				sleepTime(driver, 6);
			}
			try {
				wEle = driver.findElement(by);
			} catch (Exception e) {
				cntr++;
			}
		} while (wEle == null && cntr <= timedOut);
		if(wEle != null)
			return true;
		else
			return false;
  }
  
  public void selectElement(WebDriver driver, WebElement wElement, String value, String fieldName){
	  List<WebElement> options = wElement.findElements(By.tagName("option"));
	  boolean txtFound = false;
	  try {
		for (WebElement option : options) {
			System.out.println(option.getText().toUpperCase());
		    if(value.toUpperCase().equals(option.getText().toUpperCase())){
		      option.click();
		      //report.updateTestLog("Select "+fieldName, fieldName+" ["+value+"] has been selected", Status.DONE);
		      sleepTime(driver, 1);
		      txtFound = true;
		      break;
		    }
		 }
	} catch (Exception e) {
		//report.updateTestLog("Select "+fieldName, "Exception occured while trying to select "+fieldName+" ["+value+"]. Exception is ["+e+"]", Status.WARNING);
	}
	if(!txtFound)
		//report.updateTestLog("Select "+fieldName, "Option - ["+value+"] does not exist in "+fieldName+" list", Status.FAIL);
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void enterValue(WebElement wElement, String value, String fieldName){
	 try {
		// highlightElement(driver,wElement);
		  wElement.clear();
		  wElement.sendKeys(value);
		  //report.updateTestLog("Enter "+fieldName, fieldName+" ["+value+"] has been entered", Status.DONE);
	} catch (Exception e) {
		//report.updateTestLog("Enter "+fieldName,"Exception occured while trying to enter "+fieldName+" ["+value+"]. Exception is ["+e+"]",Status.FAIL);
	}
  }
  
  public void clickOnWebelement_UKVI(WebDriver driver, WebElement wElement,String fieldName){	 
	  int timeOut = 5, cntr = 0;
	  if(wElement != null){
		  do{
			try {
				wElement.click();
				cntr = 5;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Clickable Exception:"+e.getMessage());
				if((e.getMessage().contains("Other element would receive the click:")) && (e.getMessage().contains("Element is not clickable at point"))){
					cntr++;
					sleepTime(driver, 5);
				}else
					cntr = 5;
			}
		  }while(cntr < timeOut);
		}
  }
  
  public void findAndClicklinkByText(WebDriver driver,String txt){
	  WebElement wElement = null; int timeOut = 0;
	  do{
		  if(timeOut > 0){
			  sleepTime(driver, 6);
		  }
		  try{
				wElement = driver.findElement(By.partialLinkText(txt));
		  }catch (NoSuchElementException e) {timeOut++;}		  
	  }while(wElement == null && timeOut < 10);		
	  try{
			wElement = driver.findElement(By.partialLinkText(txt));
			if(wElement != null){
				  try{
						wElement = driver.findElement(By.partialLinkText(txt));
						wElement.click();
						try{
							wElement.click();
						}catch(Exception e){}
						txt = txt.replace("'", "");
				  }catch (NoSuchElementException e) {
					 
				  }
			  }
	  }catch (NoSuchElementException e) {
		  
	  }
	  if(wElement == null){
		  txt = txt.replace("'", "");
		  //report.updateTestLog("Find "+txt+" link", "Link "+txt+" not found",Status.FAIL);
	  }
  }   
  
//	 Find Webelement by linkText
	public  WebElement findlinkByText(WebDriver driver,String txt){
		  WebElement wElement = null; int timeOut = 0;
		  do{
			  if(timeOut > 0){
				  sleepTime(driver, 3);
			  }
			  try{
					wElement = driver.findElement(By.linkText(txt));
			  }catch (NoSuchElementException e) {timeOut++;}
		  }while(wElement == null && timeOut < 10);			  
		return wElement;
	  }
	  
	public boolean isTextPresent(WebDriver driver,String textPattern)
	{		
		String txt = null;
		try {
			txt = driver.findElement(By.cssSelector("BODY")).getText();
//			System.out.println(txt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			txt = "NULL";
			e.printStackTrace();
		}
		if(txt.contains(textPattern)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isTextPresent(WebDriver driver,WebElement wEle, String textPattern)
	{		
		String txt = null;
		try {
			txt = wEle.findElement(By.cssSelector("BODY")).getText();
//			System.out.println(txt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			txt = "NULL";
			e.printStackTrace();
		}
		if(txt.contains(textPattern)) {
			return true;
		} else {
			return false;
		}
	}	
	
	public int getRowOrColCntOfTable(WebElement wEle,By by, String tableName){
		int rowCnt = 0;
		try {
			rowCnt = wEle.findElements(by).size();
		} catch (Exception e) {
			//("Get Row//Columncount of table "+tableName, "Exception occured while trying to get Row//Columncount of table "+tableName+". Exception ["+e+"]");
		}
		if(rowCnt == 0){
			//report.updateTestLog("Get Row//Columncount of table ", "Rowcount is Zero", Status.FAIL);
		}
		return rowCnt;
	}
	
	public String getInnerTextOfCell(WebDriver driver,String xpathExpr, int rowIndex, int colIndex){
		String innerTxt = driver.findElement(By.xpath(xpathExpr+"/tr['$rowIndex']/td['$colIndex']")).getText();
		return innerTxt;
	}
	
	public String getInnerTextOfCell(WebElement wEle, int rowIndex, int colIndex){
		String innerTxt = wEle.findElement(By.xpath("/tr['$rowIndex']/td['$colIndex']")).getText();
//		String innerTxt = wEle.findElement(By.xpath("./tr["+rowIndex+"]/td["+colIndex+"]")).getText();
		return innerTxt;
	}
			
	public void waitForPageLoaded(WebDriver driver) {
	     ExpectedCondition<Boolean> expectation = new
	    	ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	        }
	      };

	     Wait<WebDriver> wait = new WebDriverWait(driver,30);
	      try {
	              wait.until(expectation);
	      } catch(Throwable error) {
	             // updateTestLog("Timeout waiting for Page Load Request to complete.","Timeout waiting for Page Load Request to complete.",Status.True);
	      }
	 } 
	
	public WebElement findObjectIfExists(WebDriver driver,By by,String elementName){
		  WebElement wElement = null; int timeOut = 0;
		  do{
			  if(timeOut > 0){
				  sleepTime(driver, 3);
			  }
			  try{
					wElement = driver.findElement(by);
			  }catch (NoSuchElementException e) {timeOut++;}
		  }while(wElement == null && timeOut < 10);		 
		return wElement;
	  }
	
	public void highlightElement(WebDriver driver,WebElement element) {
	    for (int i = 0; i < 2; i++) {
	        try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				        element, "color: green; border: 2px solid green;");
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				        element, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	    }
	}

	public void getAllTbls(WebDriver driver){
		List<WebElement> tbtEle = findObjectList(driver,By.tagName("Table"), "Table");
		System.out.println("Table Ln:"+tbtEle.size());
		int t = 0;
//		for(WebElement tbl : tbtEle){
		for(int i = 0; i < tbtEle.size(); i++){
			System.out.println("Table "+i+"Txt is------>"+tbtEle.get(i).getText());
			/*List<WebElement> trEle = tbtEle.get(i).findElements(By.tagName("tr"));
			int r = 0;
			for(WebElement tr : trEle){
				try {
					System.out.println("TR : "+r+"------>"+tr.getText());
				} catch (Exception e) {}
				r++;
			}
			t++;*/
		}
	}
	
	public WebElement findNextLinkFromParent(WebDriver driver, WebElement element,By by)
    {
        int timedOut = 4, cntr  =0;WebElement wEle = null;
        do {
              if(cntr > 0){
            	  sleepTime(driver, 6);
              }
              try {
                    wEle = element.findElement(by);
              } catch (Exception e) {
                    cntr++;
              }
        } while (wEle == null && cntr <= timedOut);
        if(wEle != null)
              return wEle;
        else
              return null;
    }
	
	public List<WebElement> findObjects(WebDriver driver,By by,String elementName){
        List<WebElement> wElement = null; int timeOut = 0;
	    do{
	          if(timeOut > 0){
	        	  sleepTime(driver, 6);
	          }
	          timeOut++;
	          try{
	              wElement = driver.findElements(by);
	          }catch (NoSuchElementException e) {
	              // throw new FrameworkException("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]");
	          }
	    }while(wElement.size()==0 && timeOut < 10);
	    if(wElement.size()==0){
	          //throw new FrameworkException("Find "+elementName, elementName+" not found");
	    }
	  return wElement;
	}

	public void verifyExpectedActual(String expected,String Actual,String FieldName)
	{
		if(expected.equalsIgnoreCase(Actual))
		{
			//report.updateTestLog("verify"+FieldName,FieldName+" "+expected+" is  displayed correctly", Status.PASS);	
		}else
		{
			//report.updateTestLog("verify"+FieldName, FieldName+"is not displayed correctly expected  "+expected+"but actual is  "+Actual, Status.FAIL);
		}
		
	}
	
	public void verifyExpectedActualContains(String expected,String Actual,String FieldName)
	{
		if(Actual.contains(expected))
		{
			//report.updateTestLog("verify"+FieldName,FieldName+" "+expected+" is  displayed correctly", Status.PASS);	
		}else
		{
			//report.updateTestLog("verify"+FieldName, FieldName+"is not displayed correctly expected  "+expected+"but actual is  "+Actual, Status.FAIL);
		}
		
	}
	  
	  
  public void findAndClicklinkByText(WebDriver driver, WebElement ele,String txt){
	  WebElement wElement = null; int timeOut = 0;
	  do{
		  if(timeOut > 0){
			  sleepTime(driver, 6);
		  }
		  try{
				wElement = ele.findElement(By.linkText(txt));
		  }catch (NoSuchElementException e) {timeOut++;}		  
	  }while(wElement == null && timeOut < 10);		
	  try{
			wElement = ele.findElement(By.linkText(txt));
			if(wElement != null){
				  try{
						wElement = ele.findElement(By.linkText(txt));
						wElement.click();						
				  }catch (NoSuchElementException e) {
					  System.out.println("Click on "+txt+" link. Exception occured while trying to Click on "+txt+" link. Exception ["+e+"]");
				  }
			  }
	  }catch (NoSuchElementException e) {
		  System.out.println("Click on "+txt+" link. Exception occured while trying to Click on "+txt+" link. Exception ["+e+"]");
	  }
  }
	  
  public String formatDateUIParam(String strDate,String INPUTFORMAT,String OUTPUTFORMAT) {
		SimpleDateFormat inFmt;
		SimpleDateFormat outFmt;
		Date date = new Date();
		outFmt = new SimpleDateFormat(OUTPUTFORMAT);
		try {
			inFmt = new SimpleDateFormat(INPUTFORMAT);
			date = inFmt.parse(strDate);
		} catch (Exception e) {
			//report.updateTestLog("Exception", "Exception while formating date", Status.FAIL);
		}
		return outFmt.format(date);
	}
	  
  public List<WebElement> findObjectsFromWebElement(WebDriver driver, WebElement wEleParent, By by,String elementName){
	  List<WebElement> wElement = null; int timeOut = 0;
	  do{
		  if(timeOut > 0){
			  sleepTime(driver, 6);
		  }
		  try{
				wElement = wEleParent.findElements(by);
		  }catch (NoSuchElementException e) {timeOut++;}		  
	  }while(wElement == null && timeOut < 10);
	  try{
			wElement = wEleParent.findElements(by);
	  }catch (NoSuchElementException e) {
		  //report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
	  }
	  if(wElement == null){
		  //throw new FrameworkException("Find "+elementName, elementName+" not found");
	  }
	return wElement;
  }
  
  public WebElement findObjectFromWebElement(WebDriver driver, WebElement wEleParent, By by,String elementName){
	  WebElement wElement = null; int timeOut = 0;
	  do{
		  if(timeOut > 0){
			  sleepTime(driver, 3);
		  }
		  try{
				wElement = wEleParent.findElement(by);
		  }catch (NoSuchElementException e) {timeOut++;}		  
	  }while(wElement == null && timeOut < 5);	  
	  if(wElement == null){
		  System.out.println( elementName+" not found");
		  //sysout("Find "+elementName, elementName+" not found");
	  }
	return wElement;
  }
	  
  public boolean isTextPresentBold(WebDriver driver,String textPattern)
	{	
		System.out.println("bold text is"+driver.findElement(By.cssSelector("b")).getText());
		if(driver.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(textPattern)) {
			return true;
		} else {
			return false;
		}
	}
	  	 	  	
 public void dismissAlertDialog(WebDriver driver) {	       
        Alert alert = driver.switchTo().alert();	       
        alert.dismiss();
        //report.updateTestLog("Close Alert Box","Alert Box has been dismissed " , Status.DONE);	 
   }
  
  public void acceptAlert(WebDriver driver){
	  sleepTime(driver, 10);		  
	  try {
			alertMsg =  driver.switchTo().alert().getText();
			System.out.println("Alert Msg:"+alertMsg);
		driver.switchTo().alert().accept();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  cntr++;
  }

  public void sleepTime(WebDriver driver,int seconds)
  {
	 try {
//		 int mSec = seconds * 100;
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	} catch (Exception e) {	
		System.out.println(e.getMessage());
		e.getMessage();
	}
  }

  public void doubleClickWebElement(WebDriver driver, WebElement ele,String fieldName)
	  {
		 
		  try {
			  Actions action=new Actions(driver);
			  action.doubleClick(ele).perform();
			 // action.keyDown(element, theKey)
			  //report.updateTestLog("Click on "+fieldName, fieldName+" has been double clicked", Status.PASS);	
		} catch (Exception e) {
			//throw new FrameworkException("Click on "+fieldName,"Exception occured while trying to double click on "+fieldName+". Exception is ["+e+"]");
		} 
	  }
	  
	 public boolean isTextPresentPageSource(WebDriver driver, String textPattern)
	{		
		
		System.out.println("source is"+driver.getPageSource());
		if(driver.getPageSource().contains(textPattern)) {
			return true;
		} else {
			return false;
		}
	} 
	  
	public void iterateTable(WebElement tableElement){
		int r = 0, c = 0;
		List<WebElement> trEles = tableElement.findElements(By.tagName("tr"));
		System.out.println("Table Row Cnt:"+trEles.size());
		for(WebElement eachTR : trEles){
			try {
				String colTxt = eachTR.getText();
				System.out.println("Row txt "+r+" is :"+colTxt);
				List<WebElement> colEles = eachTR.findElements(By.tagName("td"));
				System.out.println("Col Cnt of Row "+r+" is :"+colEles.size());
				c = 0;
				for(WebElement eachCol : colEles){					
//					System.out.println("Col Txt :"+c+"--------->"+colTxt);
					try {
						System.out.println("Row "+r+" Col "+c+" value is :"+eachCol.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					c++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("*****************************************************************");
			r++;
		}
	}
	
	public WebElement getExpTRElement(WebElement tableElement, int rowIndex){
		int r = 0;
		WebElement eachTR = null;
		List<WebElement> trEles = tableElement.findElements(By.tagName("tr"));
		System.out.println("Table Row Cnt:"+trEles.size());
		try {
			eachTR = trEles.get(rowIndex);
		} catch (Exception e) {}	
		System.out.println("*****************************************************************");
		return eachTR;
	}
		
	
	public List<WebElement> getExpTRElement(WebElement tableElement, String rowText){
		int r = 0;
		String txtProp = null;
		List<WebElement> trEles = tableElement.findElements(By.tagName("tr"));
		List<WebElement> expTREle = tableElement.findElements(By.tagName("tr"));
		expTREle.clear();
		System.out.println("Table Row Cnt:"+trEles.size());
		for(WebElement eachTR : trEles){
			try {
				txtProp = eachTR.getText();
				System.out.println("TXT:"+txtProp);
				if(txtProp.contains(rowText)){
					expTREle.add(r, eachTR);
					r++;
				}
			}catch(Exception e){}
		}
		System.out.println("*****************************************************************");
		return expTREle;
	}
	
	public List<WebElement> getExpTRElement(WebDriver driver, String rowText){
		int r = 0;
		String txtProp = null;
		List<WebElement> trEles = driver.findElements(By.tagName("tr"));
		List<WebElement> expTREle = driver.findElements(By.tagName("tr"));
		expTREle.clear();
		System.out.println("Table Row Cnt:"+trEles.size());
		for(WebElement eachTR : trEles){
			try {
				txtProp = eachTR.getText();
				System.out.println("TXT:"+txtProp);
				if(txtProp.contains(rowText)){
					expTREle.add(r, eachTR);
					r++;
				}
			}catch(Exception e){}
		}
		System.out.println("*****************************************************************");
		return expTREle;
	}
		
	public String getExpTDText(WebElement tableElement, int rowIndex, int colIndex){
		String txtProp = null;
		WebElement eachTR = null;
		List<WebElement> trEles = tableElement.findElements(By.tagName("tr"));
//		System.out.println("Table Row Cnt:"+trEles.size());
		try {
			eachTR = trEles.get(rowIndex);
			try {
				List<WebElement> colEles = eachTR.findElements(By.tagName("td"));
//				System.out.println("Col Cnt of Row "+r+" is :"+colEles.size());
				try {
					txtProp = colEles.get(colIndex).getText();
//					System.out.println("Row "+rowIndex+" Col "+colIndex+" value is :"+txtProp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {}
		} catch (Exception e) {}
		System.out.println("*****************************************************************");
		return txtProp;
	}
	
	public String getExpTDText(WebElement tableElement, int colIndex){
		String txtProp = null;
		try {
			List<WebElement> colEles = tableElement.findElements(By.tagName("td"));
//			System.out.println("Col Cnt of Row "+r+" is :"+colEles.size());
			try {
				txtProp = colEles.get(colIndex).getText();
//				System.out.println("Row "+rowIndex+" Col "+colIndex+" value is :"+txtProp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {}
		System.out.println("*****************************************************************");
		return txtProp;
	}
	
	public WebElement findCellWebElement(WebElement tableElement, int rowIndex, int colIndex, By by){
//		WebElement  = null;
		WebElement eachTR = null, eachCol = null, wEle = null;
		List<WebElement> trEles = tableElement.findElements(By.tagName("tr"));
//		System.out.println("Table Row Cnt:"+trEles.size());
		try {
			eachTR = trEles.get(rowIndex);
			try {
				List<WebElement> colEles = eachTR.findElements(By.tagName("td"));
//				System.out.println("Col Cnt of Row "+r+" is :"+colEles.size());
				try {
					eachCol = colEles.get(colIndex);
					wEle = eachCol.findElement(by);
					return wEle;
//					System.out.println("Row "+rowIndex+" Col "+colIndex+" value is :"+txtProp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {}
		} catch (Exception e) {}
		System.out.println("*****************************************************************");
		return wEle;
	}
	
	public void scrollToElement(WebDriver driver, WebElement wEle) {
	    try {
			Locatable element = (Locatable) wEle;
			Point p= (Point) element.getCoordinates();
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			js.executeScript("window.scrollTo(" + p.getX() + "," + (p.getY()+150) + ");");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean verifySelectedListValue(WebElement webEle, String expValue){ 
	      String listUI=null;
          List<WebElement> optionList= webEle.findElements(By.tagName("option"));
          for(WebElement valueUI :optionList)
          {
                 if(valueUI.getAttribute("value").equals(webEle.getAttribute("value"))) 
                 {
                	 listUI = valueUI.getText().toString();
                     System.out.println("List Value UI :" + listUI);  
                     break;
                  }
          }
           if(listUI.trim().contains(expValue.trim()))
        	   return true;
           else
            return false;	           
	  }

	public List<WebElement> getExpectedTable(WebDriver driver, String txtStartswith){
		List<WebElement> tbtEle = findObjectList(driver,By.tagName("Table"), "Table");
		List<WebElement> expTbtEle = findObjectList(driver,By.tagName("Table"), "Table");
//		System.out.println("Table Ln:"+tbtEle.size());
		int t = 0; String txtProp = null;
		expTbtEle.clear();
		for(int i = 0; i < tbtEle.size(); i++){
			txtProp = tbtEle.get(i).getText();
//			System.out.println("Table "+i+"Txt is------>"+txtProp);
			if(txtProp.startsWith(txtStartswith)){
				expTbtEle.add(tbtEle.get(i));
				break;
			}
			
		}
		return expTbtEle;
	}

	public void getAllTbls(WebDriver driver, WebElement wEle){
		List<WebElement> tbtEle = findObjectList(driver, wEle,By.tagName("Table"), "Table");
		System.out.println("Table Ln:"+tbtEle.size());
		int t = 0;
//		for(WebElement tbl : tbtEle){
		for(int i = 0; i < tbtEle.size(); i++){
			System.out.println("Table "+i+"Txt is------>"+tbtEle.get(i).getText());
			/*List<WebElement> trEle = tbtEle.get(i).findElements(By.tagName("tr"));
			int r = 0;
			for(WebElement tr : trEle){
				try {
					System.out.println("TR : "+r+"------>"+tr.getText());
				} catch (Exception e) {}
				r++;
			}
			t++;*/
		}
	}
	
	public List<WebElement> getExpectedTable(WebDriver driver, WebElement wEle, String txtStartswith){
		List<WebElement> tbtEle = findObjectList(driver, wEle,By.tagName("Table"), "Table");
		List<WebElement> expTbtEle = findObjectList(driver, wEle,By.tagName("Table"), "Table");
//		System.out.println("Table Ln:"+tbtEle.size());
		int t = 0; String txtProp = null;
		expTbtEle.clear();
		for(int i = 0; i < tbtEle.size(); i++){
			txtProp = tbtEle.get(i).getText();
//			System.out.println("Table "+i+"Txt is------>"+txtProp);
			if(txtProp.startsWith(txtStartswith)){
				expTbtEle.add(tbtEle.get(i));
			}
			
		}
		return expTbtEle;
	}
	
	public void selectElement_NottheExpValue(WebDriver driver, WebElement wElement, String value, String fieldName){		
		String txt = null;
		  List<WebElement> options = wElement.findElements(By.tagName("option"));
		  try {
			for (WebElement option : options) {
				txt = option.getText();
			    if(!value.toUpperCase().equals(option.getText().toUpperCase())){
			      option.click();
			      //report.updateTestLog("Select "+fieldName, fieldName+" ["+txt+"] has been selected", Status.SCREENSHOT);
			      sleepTime(driver, 2);
			      break;
			    }
			 }
		} catch (Exception e) {
			//throw new FrameworkException("Select "+fieldName,"Exception occured while trying to select "+fieldName+" other than ["+value+"]. Exception is ["+e+"]");
		}
		sleepTime(driver, 5);
	  }
	  
	   public String readyState(WebDriver driver) {
          return (String) ((JavascriptExecutor)driver).executeScript("return document.readyState");
        }
	  public void clickOnWebelementDone(WebElement wElement,String fieldName){
			 try {
				  wElement.click();
				  //report.updateTestLog("Click on "+fieldName, fieldName+" has been clicked", Status.DONE);	
				  try {
					Thread.sleep(1000);
				} catch (Exception e) {}
			} catch (Exception e) {
				//throw new FrameworkException("Click on "+fieldName,"Exception occured while trying to click on "+fieldName+". Exception is ["+e+"]");
			}
		  }
	  
	  
	  
	  public void selectElementContains(WebElement wElement, String value, String fieldName){
		  List<WebElement> options = wElement.findElements(By.tagName("option"));
		  boolean txtFound = false;
		  try {
			for (WebElement option : options) {
				if(option.getText().toUpperCase().contains(value.toUpperCase()) || option.getText().toUpperCase().equalsIgnoreCase(value.toUpperCase()))
				// if(value.toUpperCase().equals(option.getText().toUpperCase())){
				{
			      option.click();
			      //report.updateTestLog("Select "+fieldName, fieldName+" ["+value+"] has been selected", Status.DONE);
			      txtFound = true;
			      break;
			    }
			 }
		} catch (Exception e) {
			//throw new FrameworkException("Select "+fieldName,"Exception occured while trying to select "+fieldName+" ["+value+"]. Exception is ["+e+"]");
		}
		if(!txtFound)
			//report.updateTestLog("Select "+fieldName, "Option - ["+value+"] does not exist in "+fieldName+" list", Status.FAIL);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	 /* public void selectElementByVisibleText(WebElement wElement, String value, String fieldName){
		  Select obj=new Select(wElement);
		  obj.selectByValue(value);
	  }*/

	  public List<WebElement> findObjectsIfExists(WebDriver driver,By by,String elementName){
		  List<WebElement> wElement = null; int timeOut = 0;
		  do{
			  if(timeOut > 0){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			  }
			  try{
					wElement = driver.findElements(by);
			  }catch (NoSuchElementException e) {timeOut++;}
		  }while(wElement == null && timeOut < 6);
		  try{
				wElement = driver.findElements(by);
		  }catch (NoSuchElementException e) {
			//  //report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.DONE);
		  }	  
		 /* if(wElement == null){
			  throw new FrameworkException("Find "+elementName, elementName+" not found");
		  }*/
		return wElement;
	  } 
	  
	  
	  public WebElement findObject1(WebDriver driver,By by,String elementName){
		  WebElement wElement = null; int timeOut = 0;
		  do{
			  if(timeOut > 0){
				sleepTime(driver, 6);
			  }
			  try{
					wElement = driver.findElement(by);
			  }catch (NoSuchElementException e) {timeOut++;}
		  }while(wElement == null && timeOut < 10);
		  try{
				wElement = driver.findElement(by);
		  }catch (NoSuchElementException e) {
//			  //report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
		  }		  
		return wElement;
	  }
	  
	  public String getLibraryPath(){
		 String filePath = new File("").getAbsolutePath() + File.separator + "Libraries" + File.separator;
		 return filePath;
	  }	 
	  
	//Appends given text to the said Text file 
	public void appendToTextFile(String strText,String strFilePath)
	{
		try
	    {
			String fileName = strFilePath;
	        BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
	        out.write(strText);
	        out.write("\r\n");
	        out.close();
	    }catch (IOException e){	    	
	    } 
	}
	
	public void logError(String strMethodName, String strError){
		String strFilePath = new File("").getAbsolutePath() + File.separator + "ErrorLog.txt";
		// if file doesn't exists, then create it
		File file = new File(strFilePath);
		if (!file.exists()) {
			try {
				file.createNewFile();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Unable to create ErrorLog.txt");
			}			
		}
		strError = "Method Name:" + strMethodName + strError;
		appendToTextFile(strError, strFilePath);
		
	}
	
	public void capturePage(WebDriver driver, String strFileName){
		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
		String current_time_str = time_formatter.format(System.currentTimeMillis());
		String filePath = new File("").getAbsolutePath() + File.separator + "Libraries" + File.separator + strFileName + "_" + current_time_str+ ".png";		
		try {
			File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logError("CapturePage", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void waitForElement(WebDriver driver, By by, int timeoutInSeconds) 
	{
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public void waitForPageload_UKVI(WebDriver driver) 
	{
//		WebDriverWait wait = new WebDriverWait(driver, 3);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
	}
	
	public void waitForElementToBeClickable(WebDriver driver, By by, int timeoutInSeconds) 
	{
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public void waitForElementToBeClickable(WebDriver driver, WebElement ele, int timeoutInSeconds, String strEleName) 
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Element "+ strEleName + ", not clickable");
		}
	}
	
	public void waitTillPageload_UKVI(WebDriver driver, int timeOut) {
		do{
			try {
				WebDriverWait wait = new WebDriverWait(driver, 3);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='overlay-background']"))));
				timeOut--;
			} catch (Exception e) {
				sleepTime(driver, 3);
				timeOut = 0;					
			}
		}while(timeOut > 0);
	}
	
	//Get data from a Excel sheet and returns a Result Set
		/*public ResultSet getDataFromExcel(String strQuery,String strFilePath) {		
			Connection objConn = null;
			Statement objStatement;
			CachedRowSetImpl crs = null;
			ResultSet rsExcelData=null;
			try {
				objConn = null;
				crs = new CachedRowSetImpl();
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				objConn = DriverManager.getConnection(
						"jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="
								+ strFilePath
								+ ";DriverID=22;READONLY=false", "", "");
				objStatement = objConn.createStatement();
				rsExcelData = objStatement.executeQuery(strQuery);
				crs.populate(rsExcelData);
				objConn.close();

			} catch (Exception e) {
				e.printStackTrace();
				try{
				if (!(objConn.isClosed()))
					objConn.close();
				}catch (Exception e1){}
				
//				configData.reportObj.updateTestLog("Error connecting to Excel ","Error connecting to Excel "+ e.getMessage(),Status.FAIL);
			}
			
			return crs;
		}*/

}
