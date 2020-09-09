package testproject;

import static org.junit.jupiter.api.Assertions.*;

//import java.awt.List;
import java.util.List;
import java.util.Set;
//import java.awt.Dimension;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.AndFileFilter;
import org.checkerframework.checker.index.qual.LengthOf;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.experimental.theories.Theories;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testSwitchWindow {
	
	static WebDriver driver1;
	public WebElement element1;
	public WebDriverWait wait1;       //显式等待用，和wait.until配合
    public Actions action1;
	public generalFunctions g1=new generalFunctions();	
//--------------------------------------------------------------------
	public void ppp(String info) {
		System.out.println(info);
	}	

@BeforeAll                      //Junit4是 BeforeClass,在组/父类里的所有cases中的"第一个test case开始前"执行且只一次！而@Before是每个test cae开始前执行一次；
	public static void setUpBeforeClass() throws Exception {          //必须是静态的，但这几个子类名可以随意定义；
	ChromeOptions option=new ChromeOptions();
	option.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});       //for chrome 用来去掉“Chrome正由测试软件控制的infobar”
	option.setExperimentalOption("useAutomationExtension", false);                            //for chrome 用来去掉因关闭infobar而也引发的警告框
	driver1 = new ChromeDriver(option); 
	((JavascriptExecutor)driver1).executeScript("window.open(\""+driver1.getCurrentUrl()+"\",\"_blank\");");  //open a new tab
	     //driver1 = new ChromeDriver();
	     //driver1.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  
	}
@AfterAll                      //Junit4是AfterClass,在组/父类里的所有cases中的"最后一个test case"结束后执行且只一次！而@After是每个test cae结束后都执行一次；
    public static void tearDownAfterClass() throws Exception {        //必须是静态的，但这几个子类名可以随意定义；
            driver1.close();
            driver1.quit();
    }	
@Test
	public void test1() {		

		//System.setProperty("webdriver.edge.driver", "C:\\msedgedriver.exe");		
		//EdgeOptions option=new EdgeOptions();		
		//option.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");  //not necessary
		//option.setExperimentalOption("useAutomationExtension", false);
		//option.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		//driver1 = new EdgeDriver(option);		
		
    

      	//driver1=new InternetExplorerDriver();   // IE 11.0 		
//------------------------------------------------------------------------
		driver1.navigate().to("http://wWw.baidu.com");		
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS); //隐式等待15秒
		//wait1=new WebDriverWait(driver1,15);                           //这里用隐式，先不用显式		
		//driver1.switchTo().alert().accept();                            //关闭内嵌在当前html中的弹出框
//------------------------------------------------------------------------
		driver1.findElement(By.linkText("学术")).click();   //sucess
		System.out.println("打开学术完成!");	
		//org.openqa.selenium.Dimension targetSize = new org.openqa.selenium.Dimension(1024, 768);  //设置窗口大小为1024*768
		//driver1.manage().window().setSize(targetSize);
		//System.out.println("after set 1024,768， window get size is: "+driver1.manage().window().getSize());		
//-----------------------------------------------------
		String currenthandle=driver1.getWindowHandle();
		String newhandle=g1.GetNewhandleofTaborWindow(driver1);
		action1=new Actions(driver1);
 
	       driver1=driver1.switchTo().window(currenthandle);                              //切回原窗口
	       driver1.findElement(By.name("wd")).sendKeys("aaaa");
		   driver1.findElement(By.id("su")).click();
		   driver1.findElement(By.linkText("登录")).click(); 	                          //打开首页上的登录
		   driver1.findElement(By.id("TANGRAM__PSP_11__footerULoginBtn")).click();        //打开弹出的小页面上的登录
		   driver1.findElement(By.id("TANGRAM__PSP_11__userName")).sendKeys("aaaaa");
		   driver1.findElement(By.id("TANGRAM__PSP_11__password")).sendKeys("aaaaa");
		   driver1.findElement(By.id("TANGRAM__PSP_11__submit")).submit();		     
		     
           driver1.get("http://www.google.com");
           
//element1=driver1.findElement(By.cssSelector("#tsf > div:nth-child(2) > div.A8SBwf > div.RNNXgb > div > div.a4bIc > input"));  //在 google搜索输入框上点击鼠标右键
//element1=driver1.findElement(By.cssSelector("#hplogo")); 
//driver1.findElement(By.tagName("body")).getText()); HTML文档中有些标签文本有换行，而且可能还夹杂着其他子标签而可能用text()提取不出来，可以改用string()，string()可以将所有子标签中的文本串成一起提出来，可以满足绝大部分时候的需求                         
//element1=driver1.findElement(By.xpath("//input[@class='gLFyf gsfi']"));    //success遍历到第一个"<input...."标签下，且后面的内容里含有class="gLFyf gsfi",注意input必须是“<”开头的
//element1=driver1.findElement(By.xpath("//a[@class='gb_0 gb_xd gb_wd']"));  //success遍历到第一个“<a..."标签下,..... 
//element1=driver1.findElement(By.xpath("//*[@class='gb_yd']/a[@class='gb_6c gb_4 gb_wd gb_zd']")); //success遍历到class="gb_yd"(注意这个class并不是以“<"开头)且其紧跟的多个下级标签里的"<a..."，且这个"<a后面的内容里有class="".
//element1=driver1.findElement(By.xpath("//*[@class='QlyBfb']/a[@jsname='tf37qf']"));   //success遍历到class="QlyBfb"(注意这个class并不是以“<"开头)...
//element1.findElement(By.className()时，许多class(html的样式伪类名)是同时引用了多个伪类样式，比如class="gb_6c gb_4 gb_wd gb_zd",它用了gb_6c,gb_4等3个类，这时要用xpath或cssLactor的方式!
//对于class="xxx空格yyy"格式的伪类名，利用浏览器的"copy selector"功能可以得到cssSelector的参数，但是这个参数一般是绝对路径，不可靠，可以使用cssSelector(".xxx.yyy").           
           wait1= new WebDriverWait(driver1, 15);
       
           for (Integer i=1;i<0;i++) {        	
        	   try {
        		 element1=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='gb_yd']/a[@class='gb_0 gb_xd gb_wd']")));   //success        		  
        	   } catch (Exception e) {
			   }
           }           

           //关于鼠标位置、输入焦点、鼠标移到元素上显示的悬浮信息或突出显示效果：findElement后，鼠标在屏幕上的物理位置不变，但鼠标的逻辑位置已经位于了该element的中心位置，
           //..且仅且当执行action1.moveToElement(element1).perform()时对应的悬浮信息会显示, 此时如对该element执行右键命令action1.contextClick(element1).perform(),
           //..则菜单会以逻辑位置为顶点进行弹出，如果对该element(假定是一个不会进行相应的logo图片)执行左键单击，鼠标在屏幕的物理位置并不会跑到该element上，
           //..不过鼠标逻辑位置却会已经跑过去了。这里，action1.moveToElement(element1).click().perform()==element1.click();

          
           //action1.moveToElement(element1).perform();                          //移过去悬停，以触发悬浮信息或UI的诸如突出显示或浮起的显示效果
           //action1.contextClick(element1).perform();                           //右键单击
           //action1.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform(); //发送 ctrl+t以打开新tab页, fail
           //List <WebElement> elements1=driver1.findElements(By.xpath("//*"));  // 收集遍历到的所有元素到list 数组中,success
           //g1.ListAllElements(driver1);
        	
           try {
        	   String iframehandle=driver1.switchTo().frame(0).getWindowHandle();  //执行结果==driver1.switchTo().frame(0)
           } catch (NoSuchFrameException e) {
        	   g1.showInfo(driver1, "the following part of this program is desigbed for testing poped frame by www.google.com in Edge browser and exit now since now is running in Chrome brower！ ", 3000);
        	   //driver1.quit();
        	   fail("the following part of this program is desigbed for testing poped frame by www.google.com in Edge browser and exit now since now is running in Chrome brower！ ");
           }
//重要：这里进入了frame0,如果<body>和</body>里含有一段 <iframe>...src="frame.html"</iframe>,则该driver1的handle在switchto后保持不变，
//但里面的elements会变化为该frame里的元素。使用driver1.switchTo().defaultContent()driver1.switchTo().window(currenthandle)后，driver的handle不变但元素恢复为切换到frame前的;
//如果<body></body>里只定义了一个frame,那么切换到frame是frame(0)。注意frame(0)不是缺省的defaultContent(包含iframe的主页面)

           Set<String> winhandles= driver1.getWindowHandles();
           for (String s1:winhandles) {                               //遍历Set型数组，与List型数组不同。用set还是list取决与方法的返回类型;
        	   ppp("the window handle is: "+s1);
           }

           
           element1=driver1.findElement(By.cssSelector(".M6CB1c.rr4y5c")); //==//element1=driver1.findElement(By.cssSelector("#yDmH0d > c-wiz > div > div > c-wiz > div > div > div > div.DRc6kd.bdn4dc > div.QlyBfb > button"));
           element1.click();
           driver1.switchTo().window(currenthandle);
           element1=driver1.findElement(By.xpath("//input[@name='q']"));
               if (element1.isDisplayed()) {
                  System.out.println(element1.getTagName()+" is displayed!");        
               }                

                //driver1.navigate().back();
                //driver1.navigate().back();
                //driver1.navigate().back();
                //driver1.navigate().back();
                //driver1.navigate().forward();
                //driver1.navigate().refresh();
                //System.out.println(driver1.getPageSource());
            
		g1.waiting(4000);
		//fail("Not yet implemented");
	}
@Test 
	public void test2() {
		System.out.println("in test2 now!");
		driver1.get("http://www.baidu.com");
	}
@Disabled                  //Junit4是Ignore
    public void test3() {
	//no need to run
    }

}
