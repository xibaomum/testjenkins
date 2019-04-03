package cjwsjy.pqmstest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class pqms_test_addproject {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		// chrome作为浏览器启动
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

		driver = new ChromeDriver();

		// 窗口最大化
		driver.manage().window().maximize();

		driver.get("http://192.168.150.129:3013/pqms/pqms/page_login.html");

		WebElement username = driver.findElement(By.id("username"));

		// 用户名
		username.sendKeys("admin");

		username.submit();

		// 密码
		WebElement password = driver.findElement(By.id("password"));

		password.sendKeys("123456");

		password.submit();

		// 【登录】按钮
		WebElement button = driver.findElement(By.id("btn"));

		button.click();

		// 设置 延时
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// 项目管理
		driver.findElement(By.className("projectmanage")).click();
	}

	@DataProvider(name = "parameters")
	public Object[][] para() {

		return new Object[][] { { "24" } };
	}

	@Test(dataProvider = "parameters")
	public void project(String num) {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// 【+】按钮
		WebElement addproject = driver.findElement(By.id("addproject"));

		addproject.click();

		// 项目名称
		WebElement projectname = driver.findElement(By.id("projectname"));

		projectname.sendKeys("cjing-test-" + num);

		projectname.submit();

		// 项目类别
		Select projecttype = new Select(driver.findElement(By
				.id("p_projecttype")));

		projecttype.selectByValue("工程总承包与监理");

		// 设计阶段
		Select designstage = new Select(
				driver.findElement(By.id("designstage")));

		designstage.selectByValue("规划");

		// 项目简介
		WebElement projectintro = driver.findElement(By.id("projectintro"));

		projectintro.sendKeys("测试项目" + num);

		projectintro.submit();

		// 【提交】按钮
		WebElement submit = driver.findElement(By.id("btn1"));

		submit.click();

	}

	@AfterClass
	public void closed() {
		// 关闭浏览器
		driver.quit();
		System.out.println("关闭浏览器成功");
	}

}
