package hooks;

//  Before Report Generation
 /*
 Firstly Execute the Program with above code after successfull test passes!
 then comment the code from line 9 to 25 the run the code below.
 */

//   import io.cucumber.java.Before;
//   import io.cucumber.java.After;
//   import utils.DriverFactory;
//
//   public class Hooks {
//
//    @Before
//    public void setup() {
//        DriverFactory.initDriver();
//     }
//
//    @After
//    public void tearDown() {
//        DriverFactory.quitDriver();
//      }
//    }
//
//import io.cucumber.java.Before;
//import io.cucumber.java.After;
//import io.cucumber.java.Scenario;
//import utils.DriverFactory;
//import utils.ExtentManager;
//import com.aventstack.extentreports.*;
//
//public class Hooks {
//
//    static ExtentReports extent = ExtentManager.getInstance();
//    static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//    @Before
//    public void setup(Scenario scenario) {
//        test.set(extent.createTest(scenario.getName()));
//        DriverFactory.initDriver();
//    }
//
//    @After
//    public void tearDown() {
//        DriverFactory.quitDriver();
//        extent.flush();
//    }
//
//    public static ExtentTest getTest() {
//        return test.get();
//    }
//}

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import utils.DriverFactory;
import utils.FeatureGenerator;
import utils.ExtentManager;

import com.aventstack.extentreports.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // ✅ Step 1: Generate Feature File from Excel (Runs FIRST)
    @Before(order = 0)
    public void generateFeatureFile() {
        FeatureGenerator.generateFeatureFile();
    }

    // ✅ Step 2: Setup Test + Driver
    @Before(order = 1)
    public void setup(Scenario scenario) {

        ExtentTest extentTest = extent.createTest(scenario.getName());
        test.set(extentTest);

        DriverFactory.initDriver();
    }

    // ✅ Step 3: Tear Down + Screenshot + Report
    @After
    public void tearDown(Scenario scenario) {

        try {
            if (scenario.isFailed()) {

                byte[] screenshot = ((TakesScreenshot)
                        DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

                test.get().fail("Test Failed",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(
                                java.util.Base64.getEncoder().encodeToString(screenshot)
                        ).build()
                );

            } else {
                test.get().pass("Test Passed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        DriverFactory.quitDriver();
        extent.flush();
    }
}