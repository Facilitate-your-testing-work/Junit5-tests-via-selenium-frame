package testproject;
import org.testng.Reporter;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.interactions.Actions;

public class generalFunctions {
//-------------------------------------------------------------------------------------------------
	//to call it, use If (isElementPresent(driver,By.linkText("立即注册")),3000)) 
	//                 {driver.findElement(By.linkText("立即注册")).click();}
	public void isElementPresent(WebDriver driver,By by,int seconds) {
		try {
			driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.MILLISECONDS);			
		    driver.findElement(by);
		    //driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS); //恢复默认值--不能加这句
		    System.out.println(by.toString()+" is present now!");
		    //return true;
		} catch (NoSuchElementException e) {
			System.out.println(by.toString()+" is not present yet!");
			//return false;
		}
	}
	
	public boolean isAlertPresent(WebDriver driver) throws Exception {  //判断一下当前是否有弹出窗口
		try {
			driver.switchTo().alert();
			return true;
			} catch (NoAlertPresentException e) {
				// e.printStackTrace();
				return false;
			}
	}
	
	public void showInfo(WebDriver driver,String info,int waiting) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String s1="alert('"+info+"')";
	    js.executeScript(s1);
	    generalFunctions g=new generalFunctions();
	    g.waiting(waiting);
	    try {
	    	driver.switchTo().alert().accept();	    	
	    } catch (NoAlertPresentException e ) {
	    	System.out.println("Alert might be closed by mannual.");
	    	driver.quit();
	    }
	}
//-------------------------------------------------------------------------------------------------

	public void waitpageloaded(WebDriver driver) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	//js.executeScript("document.onreadystatechange=listen();"+"function listen()"+"{if (document.readyState == 'complete')"+ "alert('load compeleted!')}");
    js.executeScript("document.onreadystatechange=listen();"+"function listen()"+"{if (document.readyState == 'complete')"+"console.log('内容')}");
    System.out.println("page loaded here!");
	}
	public boolean IsTextExisting(WebDriver driver,String specialtext) {
		
		try {
			return driver.findElement(By.tagName("body")).getText().contains(specialtext);
			//  遍历body节点下的所有结点，取文本值，并判断是否包含指定文本; 包含则返回true;
		}
		catch (Exception e) {
			return false;
		}
	}
//-------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------
	public void waiting(int waitTime) {
	        
	        try {           
	              Thread.sleep(waitTime);
	        } catch (InterruptedException ex) {
	               //Logger.getLogger(waiting.class.getName()).log(Level.SEVERE, null, ex);
	           }
	    
	}
//-------------------------------------------------------------------------------------------------
	// 切换算法0 Switch-method - 0:
	public String GetNewhandleofTaborWindow(WebDriver driver) {  //****only get newhandle ,not switch to new window!****//
	//this function is to get newhandle of the "browser window" poped up by the current "brower".“in same brower with different tab”
	//It is important that the "driver" is a webdriver, not only can refer to current window ,current tab 0, but also can 
    //...refer to poped new window,tab 1,tab 2, to swtitch to other windows or tabs,just use same webdriver variant:driver.switchto().window(string handle);
	//to call this method, in test cases use :
	//1.String currenthandle=driver.getWindowHandle();
	//2.click new link,resulting opening a new url in a newtab of the same browser or in a new window;
	//3. generalFunctions g1=new generalFunctions();
	//4. String newhandle1=g1.SwitchBrowserTaborWindow(driver);
    //5. driver.switchTo().window(newhandle or currenthandle);  //switch to new tab or window/tab x or window;parrent tab
    //6. ***IF you need to operate element in new tab x or new window, you need to :WebDriver newdriver=driver.switchTo().window(newhandle);		
		
		String newhandle="";		
		String currenthandle = driver.getWindowHandle();         //首先得到最先的窗口 权柄
		for(String winhandle : driver.getWindowHandles()) {    //得到浏览器所有窗口的权柄为Set集合，遍历
			newhandle=winhandle;
			System.out.println("newhandle is : "+newhandle);
			if (winhandle.equals(currenthandle)) {				//如果为 最先的窗口 权柄跳出				
				continue;
			}	
	    	try {
	    		//WebDriver newdriver=driver.switchTo().window(newhandle);// 切换到新窗口
	            //System.out.println("New page Title is:" + newdriver.getTitle());  
	            //System.out.println("New page URL is:" + newdriver.getCurrentUrl());
	    		System.out.println("CurrentHandle is : "+currenthandle);
	    		System.out.println("NewHandle is : "+newhandle);
	        } catch (Exception e) {  
	        	System.out.println("无法切换至新打开的窗口");  
	        	System.out.println(e.getMessage());  	        
	        }
	    	//
	    }
		return newhandle;	
	 
	}
	// 切换算法1 Switch-method - 1:
	//public WebDriver SwitchBrowserTab(WebDriver driver,String currenthandle) {
	//this function is to get new driver of the "browser window" poped up by the current "brower".“in same brower with different tab”
	//to call this function, in test cases use :
	//1.String currenthandle=driver.getWindowHandle();
	//2.click new link,resulting opening a new url in a newtab of the same browser;
	//3. WebDriver newdriver=new SwitchBrowserTab(driver,currenthandle);
	//4. WebDriver driver2=newdriver.switchTo().window(currenthandle);  //switch to parrent tab, in fact its unnecessary to define driver2 ,because dirver2 ==driver1;
		
		//String newhandle="";	
		//WebDriver newdriver=null;
	
	    //Set<String> handles = driver.getWindowHandles();
	    //Iterator<String> it = handles.iterator();
		//Iterator<String> it = driver.getWindowHandles().iterator();
	    //while (it.hasNext()) {
	    	//newhandle = it.next();  
	    	//if(currenthandle.equals(newhandle)) {
	    		//continue;
	    	//}
	    	//try {
	            //newdriver = driver.switchTo().window(newhandle);// 切换到新窗口  
	            //System.out.println("New page Title is:" + newdriver.getTitle());  
	            //System.out.println("New page URL is:" + newdriver.getCurrentUrl());
	        //} catch (Exception e) {  
	        	//System.out.println("无法切换至新打开的窗口");  
	        	//System.out.println(e.getMessage());  
	        //}
	    //}	    	
	    //return newdriver; 
	//}		
	// 切换算法2 Switch-method - 2 ：
	public WebDriver getnewdriver2(WebDriver driver,String currenthandle) {
		WebDriver newdriver = null;
	    Set<String> handles = driver.getWindowHandles();	   
		for (String handle:handles){
            if (handle.equals(currenthandle)==false){         	
    	    	try {
    	            newdriver = driver.switchTo().window(handle);// 切换到新窗口  
    	            System.out.println("New page Title is:" + newdriver.getTitle());  
    	            System.out.println("New page URL is:" + newdriver.getCurrentUrl());
    	        } catch (Exception e) {  
    	        	System.out.println("无法切换至新打开的窗口");  
    	        	System.out.println(e.getMessage());    	        	
                }
            }        
        }
		return newdriver;
	}

//-------------------------------------------------------------------------------------------------
	public void SnapshotSingleWindow(WebDriver driver) {
		
	    //driver.manage().window().maximize();  
		String filePreStr="E:\\TEMP2\\snapshot_window_";
	    String serialNum ;                                         //截图名称后面的数字序号
	    String imageFormat = "jpg";                                //默认的图像文件的格式
	    	    
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        serialNum=sdf.format(d1);
        String filename = filePreStr +serialNum+ "." + imageFormat;
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);          //执行屏幕截图操作
        try {
            //FileUtils.copyFile(srcFile, new File(filename));//通过FileUtils中的copyFile()方法保存getScreenshotAs()返回的文件;"屏幕截图"即时保存截图的文件夹
        	FileUtils.copyFile(srcFile, new File(filename));//通过FileUtils中的copyFile()方法保存getScreenshotAs()返回的文件;"屏幕截图"即时保存截图的文件夹
		    System.out.println("Save current window of '"+driver.getTitle()+ "' snapshot to File " + filename);
        } catch(Exception e) {
             e.printStackTrace();
        }    
    }

//-------------------------------------------------------------------------------------------------
	public void SnapshotWholeScreen() {

		String filePreStr="E:\\TEMP2\\snapshot_screen_";
		String serialNum ;                                         //截图名称后面的数字序号
		String imageFormat = "jpg";                        //默认的图像文件的格式
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //获取全屏幕的宽高尺寸等数据

	    try {
	        BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));  //拷贝屏幕到一个BufferedImage对象screenshot
	        Date d1 = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
	        serialNum=sdf.format(d1);
	        String filename = filePreStr +serialNum+ "." + imageFormat;
	        File f = new File(filename);

	        ImageIO.write(screenshot, imageFormat, f);             //   System.out.println("Save File " + name); 将screenshot对象写入图像文件
		    System.out.println("Save current whole screen snapshot to File " + filename);
		    //   System.out.println("..Finished!\n");
	    } catch (Exception ex) {
	        System.out.println(ex);
	    }
	} 

//-------------------------------------------------------------------------------------------------
	public void SnapshotWebdriverElement(WebDriver driver,WebElement element) {

		String filePreStr="E:\\TEMP2\\snapshot_webElement_";
	    String serialNum ;                                         //截图名称后面的数字序号
	    String imageFormat = "jpg";                                //默认的图像文件的格式		
		    
    	WrapsDriver wrapsDriver=(WrapsDriver)element;
    	((TakesScreenshot)wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);  // 截图整个页面
    	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);          //执行屏幕截图操作

    	BufferedImage img = null;
		try {
			img = ImageIO.read(srcFile);
		} catch (IOException e) {
			System.out.println(e);
		}
    	int width =element.getSize().getWidth();     
    	int height=element.getSize().getHeight();
    	Rectangle rect=new Rectangle(width,height);  // 创建一个矩形使面上面的高度，和宽度
    	Point p=element.getLocation();               

    	BufferedImage dest=img.getSubimage(p.getX(),p.getY(),rect.width,rect.height);
    
    	Date d1 = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
    	serialNum=sdf.format(d1);    
    	String filename =filePreStr +serialNum + "." + imageFormat;
    	File f = new File(filename);
    	try {
			ImageIO.write(dest, imageFormat, f);
		} catch (IOException e) {			
			System.out.println(e);
		} 
    	System.out.println("Save File of webElement with tagName of '"+ element.getTagName()+" snapshot to File "  + filename);
    }    

//-------------------------------------------------------------------------------------------------
	public void ListAllElements(WebDriver driver) {

		Actions action1=new Actions(driver);
	    
		List <WebElement> elementlist1=driver.findElements(By.cssSelector("*"));   
		for (Integer i=0;i<elementlist1.size();i++) {      // 遍历所有元素；注意有些元素用getText()并不能提取出来里面的text;
			System.out.println("test is : "+elementlist1.get(i).getText()+"! Url is "+elementlist1.get(i).getAttribute("href")+"!  textContext is : "+elementlist1.get(i).getAttribute("textContext"));  
			if (elementlist1.get(i).getAttribute("href") !=null && elementlist1.get(i).getAttribute("href").contains("https://www.google.com/?gws_rd=ssl#")) {
				System.out.println("here , element1 arrtribute is : "+elementlist1.get(i).getAttribute("href") + "  and its enabled is: "+ elementlist1.get(i).isEnabled());
				action1.moveToElement(elementlist1.get(i));
				try {
					action1.click(elementlist1.get(i)).perform();
				} catch(Exception e) {
					System.out.println("this element can not be clicked!");
				}
			}else{        			  			
				
		    }        	   
		}
	}
//-------------------------------------------------------------------------------------------------
	private String name = "name";
	private String pwd = "pwd";
	private String forLogin = "inputbutton";	 

	public void login(WebDriver driver, String username, String password) throws Exception {
		driver.findElement(By.name(name)).sendKeys(username);
		driver.findElement(By.name(pwd)).sendKeys(password);
		// 点击登录
		driver.findElement(By.id(forLogin)).click();
		Thread.sleep(2000);
		// System.out.println(driver.getCurrentUrl());
	}	 

	public boolean isLoginSuccess(WebDriver driver) throws Exception{
		boolean flag = false;
		try {
			if(driver.findElement(By.id("asset")).isDisplayed()){
				flag=true;
			}
    	} catch (Exception e) {
    		// e.printStackTrace();
    		// System.out.println(e);
    	}
		return flag;
	}

	public boolean loginStatus(WebDriver driver) throws Exception {
		if (isAlertPresent(driver)) {
			Reporter.log(driver.switchTo().alert().getText());
			System.out.println(driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
			driver.navigate().refresh();
			return false;
		}
		else if (!(isLoginSuccess(driver))) {
			Reporter.log("用户名错误！");
			System.out.println("用户名错误！");
			driver.navigate().refresh();
			Thread.sleep(2000);
			return false;
		}
		else {
			Reporter.log("登录成功！");
			System.out.println("登录成功！");
			return true;
		}
	}	 

	public  boolean isLoginPage(WebDriver driver) throws Exception {
		boolean flag = false;
		try {
			if (driver.findElement(By.id(forLogin)).getAttribute("value").equals("登录")) {
				flag = true;
				return flag;
			}
		} catch (Exception e) {
			//	     System.out.println(e);
			return flag;
		}
		return flag;
	}
	
	
}

