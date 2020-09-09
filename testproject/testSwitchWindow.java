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
	public WebDriverWait wait1;       //��ʽ�ȴ��ã���wait.until���
    public Actions action1;
	public generalFunctions g1=new generalFunctions();	
//--------------------------------------------------------------------
	public void ppp(String info) {
		System.out.println(info);
	}	

@BeforeAll                      //Junit4�� BeforeClass,����/�����������cases�е�"��һ��test case��ʼǰ"ִ����ֻһ�Σ���@Before��ÿ��test cae��ʼǰִ��һ�Σ�
	public static void setUpBeforeClass() throws Exception {          //�����Ǿ�̬�ģ����⼸���������������ⶨ�壻
	ChromeOptions option=new ChromeOptions();
	option.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});       //for chrome ����ȥ����Chrome���ɲ���������Ƶ�infobar��
	option.setExperimentalOption("useAutomationExtension", false);                            //for chrome ����ȥ����ر�infobar��Ҳ�����ľ����
	driver1 = new ChromeDriver(option); 
	((JavascriptExecutor)driver1).executeScript("window.open(\""+driver1.getCurrentUrl()+"\",\"_blank\");");  //open a new tab
	     //driver1 = new ChromeDriver();
	     //driver1.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  
	}
@AfterAll                      //Junit4��AfterClass,����/�����������cases�е�"���һ��test case"������ִ����ֻһ�Σ���@After��ÿ��test cae������ִ��һ�Σ�
    public static void tearDownAfterClass() throws Exception {        //�����Ǿ�̬�ģ����⼸���������������ⶨ�壻
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
		driver1.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS); //��ʽ�ȴ�15��
		//wait1=new WebDriverWait(driver1,15);                           //��������ʽ���Ȳ�����ʽ		
		//driver1.switchTo().alert().accept();                            //�ر���Ƕ�ڵ�ǰhtml�еĵ�����
//------------------------------------------------------------------------
		driver1.findElement(By.linkText("ѧ��")).click();   //sucess
		System.out.println("��ѧ�����!");	
		//org.openqa.selenium.Dimension targetSize = new org.openqa.selenium.Dimension(1024, 768);  //���ô��ڴ�СΪ1024*768
		//driver1.manage().window().setSize(targetSize);
		//System.out.println("after set 1024,768�� window get size is: "+driver1.manage().window().getSize());		
//-----------------------------------------------------
		String currenthandle=driver1.getWindowHandle();
		String newhandle=g1.GetNewhandleofTaborWindow(driver1);
		action1=new Actions(driver1);
 
	       driver1=driver1.switchTo().window(currenthandle);                              //�л�ԭ����
	       driver1.findElement(By.name("wd")).sendKeys("aaaa");
		   driver1.findElement(By.id("su")).click();
		   driver1.findElement(By.linkText("��¼")).click(); 	                          //����ҳ�ϵĵ�¼
		   driver1.findElement(By.id("TANGRAM__PSP_11__footerULoginBtn")).click();        //�򿪵�����Сҳ���ϵĵ�¼
		   driver1.findElement(By.id("TANGRAM__PSP_11__userName")).sendKeys("aaaaa");
		   driver1.findElement(By.id("TANGRAM__PSP_11__password")).sendKeys("aaaaa");
		   driver1.findElement(By.id("TANGRAM__PSP_11__submit")).submit();		     
		     
           driver1.get("http://www.google.com");
           
//element1=driver1.findElement(By.cssSelector("#tsf > div:nth-child(2) > div.A8SBwf > div.RNNXgb > div > div.a4bIc > input"));  //�� google����������ϵ������Ҽ�
//element1=driver1.findElement(By.cssSelector("#hplogo")); 
//driver1.findElement(By.tagName("body")).getText()); HTML�ĵ�����Щ��ǩ�ı��л��У����ҿ��ܻ������������ӱ�ǩ��������text()��ȡ�����������Ը���string()��string()���Խ������ӱ�ǩ�е��ı�����һ�������������������󲿷�ʱ�������                         
//element1=driver1.findElement(By.xpath("//input[@class='gLFyf gsfi']"));    //success��������һ��"<input...."��ǩ�£��Һ���������ﺬ��class="gLFyf gsfi",ע��input�����ǡ�<����ͷ��
//element1=driver1.findElement(By.xpath("//a[@class='gb_0 gb_xd gb_wd']"));  //success��������һ����<a..."��ǩ��,..... 
//element1=driver1.findElement(By.xpath("//*[@class='gb_yd']/a[@class='gb_6c gb_4 gb_wd gb_zd']")); //success������class="gb_yd"(ע�����class�������ԡ�<"��ͷ)��������Ķ���¼���ǩ���"<a..."�������"<a�������������class="".
//element1=driver1.findElement(By.xpath("//*[@class='QlyBfb']/a[@jsname='tf37qf']"));   //success������class="QlyBfb"(ע�����class�������ԡ�<"��ͷ)...
//element1.findElement(By.className()ʱ�����class(html����ʽα����)��ͬʱ�����˶��α����ʽ������class="gb_6c gb_4 gb_wd gb_zd",������gb_6c,gb_4��3���࣬��ʱҪ��xpath��cssLactor�ķ�ʽ!
//����class="xxx�ո�yyy"��ʽ��α�����������������"copy selector"���ܿ��Եõ�cssSelector�Ĳ����������������һ���Ǿ���·�������ɿ�������ʹ��cssSelector(".xxx.yyy").           
           wait1= new WebDriverWait(driver1, 15);
       
           for (Integer i=1;i<0;i++) {        	
        	   try {
        		 element1=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='gb_yd']/a[@class='gb_0 gb_xd gb_wd']")));   //success        		  
        	   } catch (Exception e) {
			   }
           }           

           //�������λ�á����뽹�㡢����Ƶ�Ԫ������ʾ��������Ϣ��ͻ����ʾЧ����findElement���������Ļ�ϵ�����λ�ò��䣬�������߼�λ���Ѿ�λ���˸�element������λ�ã�
           //..�ҽ��ҵ�ִ��action1.moveToElement(element1).perform()ʱ��Ӧ��������Ϣ����ʾ, ��ʱ��Ը�elementִ���Ҽ�����action1.contextClick(element1).perform(),
           //..��˵������߼�λ��Ϊ������е���������Ը�element(�ٶ���һ�����������Ӧ��logoͼƬ)ִ������������������Ļ������λ�ò������ܵ���element�ϣ�
           //..��������߼�λ��ȴ���Ѿ��ܹ�ȥ�ˡ����action1.moveToElement(element1).click().perform()==element1.click();

          
           //action1.moveToElement(element1).perform();                          //�ƹ�ȥ��ͣ���Դ���������Ϣ��UI������ͻ����ʾ�������ʾЧ��
           //action1.contextClick(element1).perform();                           //�Ҽ�����
           //action1.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform(); //���� ctrl+t�Դ���tabҳ, fail
           //List <WebElement> elements1=driver1.findElements(By.xpath("//*"));  // �ռ�������������Ԫ�ص�list ������,success
           //g1.ListAllElements(driver1);
        	
           try {
        	   String iframehandle=driver1.switchTo().frame(0).getWindowHandle();  //ִ�н��==driver1.switchTo().frame(0)
           } catch (NoSuchFrameException e) {
        	   g1.showInfo(driver1, "the following part of this program is desigbed for testing poped frame by www.google.com in Edge browser and exit now since now is running in Chrome brower�� ", 3000);
        	   //driver1.quit();
        	   fail("the following part of this program is desigbed for testing poped frame by www.google.com in Edge browser and exit now since now is running in Chrome brower�� ");
           }
//��Ҫ�����������frame0,���<body>��</body>�ﺬ��һ�� <iframe>...src="frame.html"</iframe>,���driver1��handle��switchto�󱣳ֲ��䣬
//�������elements��仯Ϊ��frame���Ԫ�ء�ʹ��driver1.switchTo().defaultContent()driver1.switchTo().window(currenthandle)��driver��handle���䵫Ԫ�ػָ�Ϊ�л���frameǰ��;
//���<body></body>��ֻ������һ��frame,��ô�л���frame��frame(0)��ע��frame(0)����ȱʡ��defaultContent(����iframe����ҳ��)

           Set<String> winhandles= driver1.getWindowHandles();
           for (String s1:winhandles) {                               //����Set�����飬��List�����鲻ͬ����set����listȡ���뷽���ķ�������;
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
@Disabled                  //Junit4��Ignore
    public void test3() {
	//no need to run
    }

}
