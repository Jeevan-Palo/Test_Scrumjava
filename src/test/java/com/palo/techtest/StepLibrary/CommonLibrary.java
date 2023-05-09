package com.palo.techtest.StepLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.palo.techtest.Constant.UserConfig;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class CommonLibrary {
	public static WebDriver webDriver = null;
	public static AndroidDriver<MobileElement> nativeDriver = null;
	public static WebElement element = null;
	static WebDriverWait browserWithElementWait = null;
	static long t1 = 0;
	static long t2 = 0;

	public static Configuration config = null;

	private static Properties properties;
	// public static String device = null;

	public static void configMethod() throws Exception {
		ConfigurationFactory factory = new ConfigurationFactory("config/config.xml");
		config = factory.getConfiguration();

	}

	public static void initiateBrowser() throws ConfigurationException, IOException, InterruptedException {
		// device = "Desktop";
		ConfigurationFactory factory = new ConfigurationFactory("config/config.xml");
		config = factory.getConfiguration();
		if (config.getString("breakPoint").equalsIgnoreCase("Desktop")) {
			if ("Yes".equalsIgnoreCase(config.getString("fireFox"))) {
				webDriver = new FirefoxDriver();
				webDriver.get(config.getString("applicationURL"));
				webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if ("Yes".equalsIgnoreCase(config.getString("chrome"))) {
				System.out.println(UserConfig.chromeDriver_Desktop_Location);
				System.setProperty("webdriver.chrome.driver", UserConfig.chromeDriver_Desktop_Location);
				webDriver = new ChromeDriver();
				if ("Windows".equalsIgnoreCase(config.getString("operatingSystem"))) {
				} else {
					webDriver = new ChromeDriver();
				}
				webDriver.get(config.getString("applicationURL"));
				webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if ("Yes".equalsIgnoreCase(config.getString("IE"))) {
				System.out.println("IE initiated");
				System.out.println(UserConfig.IE_Desktop_Location);
				System.setProperty("webdriver.ie.driver", UserConfig.IE_Desktop_Location);
				DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
				capability.setCapability("nativeEvents", false);
				webDriver = new InternetExplorerDriver(capability);
				webDriver.get(config.getString("applicationURL"));
				webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			} else {
				System.out.println("**********Given Browser Name is Wrong************");
			}
			webDriver.manage().window().maximize();
		}
else if(config.getString("breakPoint").equalsIgnoreCase("Mobile")) {
			
			if ("iOS".equalsIgnoreCase(config.getString("operatingSystem"))) {
				//Under progress for implementation
			} else if("android".equalsIgnoreCase(config.getString("operatingSystem"))) {
				initiateBrowser_Android();
			}else if("androidNative".equalsIgnoreCase(config.getString("appType"))) {
				initiateNativeAPP_Android();
			} 
			else {
			}
		}

	}

	
	public static void initiateBrowser_Android() throws IOException, InterruptedException {
		try {
			String adbPath = UserConfig.sdk_location + File.separator + "platform-tools";
			//Runtime.getRuntime().exec(UserConfig.projectLocation + File.separator + "Extensions/Killchromedriver.sh" + " start");
			Thread.sleep(1000 * 2);
			Thread.sleep(1000);
			Runtime.getRuntime().exec(adbPath + "/adb" + " start-server");
			Thread.sleep(1000);
			Runtime.getRuntime().exec(UserConfig.projectLocation + "/drivers/chromedriver");
			Thread.sleep(1000);//UserConfig
			System.out.println("initialising the browser");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("androidPackage", "com.android.chrome");
			options.setExperimentalOption("androidDeviceSerial", UserConfig.deviceId);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setPlatform(Platform.ANDROID);
			capabilities.setCapability("device", "android");
			capabilities.setCapability("app", "chrome");
			webDriver = new RemoteWebDriver(new URL(UserConfig.chromeDriver_location), capabilities);
			webDriver.manage().deleteAllCookies();
			webDriver.get(config.getString("applicationURL"));
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initiateNativeAPP_Android() throws IOException, InterruptedException {
		try {
			System.out.println("initialising the Android Application");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			capabilities.setCapability("app", "C:\\Users\\Jeevan\\Desktop\\Jeevan\\APK_Files\\JCPenney_v7.0.2_apkpure.com.apk");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "7.0");
			capabilities.setCapability("deviceName", UserConfig.deviceId);
			capabilities.setCapability("appPackage", "com.jcp"); 
			capabilities.setCapability("appActivity", "com.jcpenney.activities.StartupActivity"); 
			
			nativeDriver=new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void closeBrowser() throws InterruptedException, IOException {
		Thread.sleep(10000);

		webDriver.quit();
		Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
		Thread.sleep(3000);

	}

	public static String getElementText(String objectProperty) throws Exception {
		element = getElementByProperty(objectProperty, webDriver);
		String linkText = element.getText();
		return linkText;
	}

	public static WebElement getElementByProperty(String objectProperty, WebDriver webDriver) throws Exception {
		String propertyType = null;
		WebDriverWait browserWithElementWait = null;
		try {
			if (browserWithElementWait == null) {
				browserWithElementWait = new WebDriverWait(webDriver, config.getInt("elementWaitInSeconds"));
			}
			propertyType = StringUtils.substringAfter(objectProperty, "~");
			objectProperty = StringUtils.substringBefore(objectProperty, "~");
			if (propertyType.equalsIgnoreCase("CSS")) {
				element = browserWithElementWait
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(objectProperty)));
				highlightElement(element, webDriver);
			} else if (propertyType.equalsIgnoreCase("XPATH")) {
				element = browserWithElementWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectProperty)));
				highlightElement(element, webDriver);
			} else if (propertyType.equalsIgnoreCase("ID")) {
				element = browserWithElementWait
						.until(ExpectedConditions.presenceOfElementLocated(By.id(objectProperty)));
				// highlightElement(webElement, browser);
			} else if (propertyType.equalsIgnoreCase("NAME")) {
				element = browserWithElementWait
						.until(ExpectedConditions.presenceOfElementLocated(By.className(objectProperty)));
				highlightElement(element, webDriver);
			} else if (propertyType.equalsIgnoreCase("LINKTEXT")) {
				element = browserWithElementWait
						.until(ExpectedConditions.presenceOfElementLocated(By.linkText(objectProperty)));
				highlightElement(element, webDriver);
			} else {
				element = browserWithElementWait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectProperty)));

			}
		} catch (Exception e) {

		}

		return element;
	}

	/*
	 * Methods for Element Verification
	 */
	public static boolean isElementPresentVerification(String objectProperty) {
		boolean isElementPresent = false;
		browserWithElementWait = new WebDriverWait(webDriver, 30);
		try {
			element = getElementByProperty(objectProperty, webDriver);
			if (element != null) {
				isElementPresent = true;
				t2 = System.currentTimeMillis();
			} else {
				throw new Exception("Object Couldn't be retrieved and verified");
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isElementPresent;
	}

	/*
	 * Methods for clear and text
	 */

	public static boolean clearAndEnterText(String objectProperty, String Text) throws Exception {
		boolean isTextEnteredResult = false;
		try {
			if ("-".equals(Text)) {
				isTextEnteredResult = true;

			} else {
				WebElement textBox = getElementByProperty(objectProperty, webDriver);
				textBox.clear();
				Thread.sleep(2000);
				textBox.sendKeys(Text);
				isTextEnteredResult = true;

			}
		} catch (Exception e) {
			throw new Exception("Enter text fails");
		}
		return isTextEnteredResult;
	}

	/*
	 * Methods for Highlight the Elements
	 */
	public static void highlightElement(WebElement element, WebDriver webDriver) {
		System.out.println("Hilighted");
		for (int i = 0; i < 1; i++) {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: black; border: 3px solid black;");
		}
	}

	/*
	 * Common Method for Click
	 */
	public static boolean isElementPresentVerifyClick(String objectProperty) throws Exception {
		boolean isVerifiedAndClicked = false;
		browserWithElementWait = new WebDriverWait(webDriver, 30);

		element = getElementByProperty(objectProperty, webDriver);
		if (element != null) {
			t1 = System.currentTimeMillis();
			element.click();
			isVerifiedAndClicked = true;
		} else {
			throw new Exception("Object Couldn't be retrieved and clicked");
		}

		return isVerifiedAndClicked;
	}

	/*
	 * Methods for Screenshot
	 */

	public static void getscreenshot(String screenShotName) throws Exception {
		File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		FileUtils.copyFile(scrFile,
				new File(UserConfig.projectLocation + "/FailureScreenShot/" + screenShotName + ".png"));
	}

	public static void getscreenshotEmbed(String screenShotName, Scenario scenario) throws Exception {
		File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,
				new File(UserConfig.projectLocation + "/FailureScreenShot/" + screenShotName + ".png"));

		final byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
		webDriver.quit();
	}

	public static Map<String, List<String>> getHorizontalData(DataTable dataTable) {
		Map<String, List<String>> dataMap = null;
		try {
			dataMap = new HashMap<String, List<String>>();
			List<String> headingRow = dataTable.raw().get(0);
			int dataTableRowsCount = dataTable.getGherkinRows().size() - 1;
			ArrayList<String> totalRowCount = new ArrayList<String>();
			totalRowCount.add(Integer.toString(dataTableRowsCount));
			dataMap.put("totalRowCount", totalRowCount);
			for (int i = 0; i < headingRow.size(); i++) {
				List<String> dataList = new ArrayList<String>();
				dataMap.put(headingRow.get(i), dataList);
				for (int j = 1; j <= dataTableRowsCount; j++) {
					List<String> dataRow = dataTable.raw().get(j);
					dataList.add(dataRow.get(i));
				}
			}
		} catch (Exception e) {

		}
		return dataMap;
	}

	/*
	 * Methods for read data from EXCell Sheet
	 */

	public static String getXLSTestData(String FileName, String SheetName, String RowId, String column)
			throws IOException {

		String col1 = null;
		DataFormatter df = new DataFormatter();
		FileInputStream file = new FileInputStream(
				new File(System.getProperty("user.dir") + "/InputData" + File.separator + FileName + ".xls"));
		HSSFWorkbook book = new HSSFWorkbook(file);
		HSSFSheet sheet = book.getSheet(SheetName);

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int rowIterator = 1; rowIterator <= rowCount; rowIterator++) {
			String row = sheet.getRow(rowIterator).getCell(0).getStringCellValue();
			if (row.equalsIgnoreCase(RowId)) {
				for (int colIterator = 1; colIterator < sheet.getRow(rowIterator).getLastCellNum(); colIterator++) {
					String col = sheet.getRow(0).getCell(colIterator).getStringCellValue();
					if (col.equalsIgnoreCase(column)) {
						Cell cellvalue = sheet.getRow(rowIterator).getCell(colIterator);
						col1 = df.formatCellValue(cellvalue);
						break;
					}
				}
			}
		}
		book.close();
		return col1;
	}

	public static List<WebElement> getListOfElements(String objectProperty) throws Exception {
		element = getElementByProperty(objectProperty, webDriver);
		List<WebElement> listSize = webDriver.findElements(By.className(objectProperty));
		return listSize;
	}

	public static Properties TD = CommonLibrary.getInstance();

	// Initiate property file
	private static Properties loadFromPropertiesFile(String propertyFileName) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(propertyFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	// load the properties for file
	public static Properties getInstance() {

		if (properties == null) {
			properties = loadFromPropertiesFile("resources//Entity.properties");
		}
		return properties;
	}

}
