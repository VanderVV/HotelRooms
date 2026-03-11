package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportManager {

    private static ExtentTest test;
    private static ExtentReports extent;
    private static String targetPath = "test-output/" ;


    public static void takePassScreenshot(String desc, WebDriver driver) {
        test.pass(desc, MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(desc + generateTimeStamp("yyyy_MM_dd_HH_mm_ss", 0), driver)).build());
    }
    public static void setTest(ExtentTest extentTest) {
        test = extentTest;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void takeErrorScreenshot(String desc, WebDriver driver) {
        test.fail(desc, MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(desc + generateTimeStamp("yyyy_MM_dd_HH_mm_ss", 0), driver)).build());
//        driver.close();
    }



    public static String getScreenshot(String screenshotName, WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "/" + targetPath + "/" + screenshotName + ".png";

        File destination = new File(path);

        try {
            FileUtils.copyFile(src, destination);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return path;
    }

    public static void store_label_red(String text) {
        test.info(MarkupHelper.createLabel(text, ExtentColor.RED));
    }

    public static void store_label_green(String text) {
        test.info(MarkupHelper.createLabel(text, ExtentColor.GREEN));
    }

    public static void designReport(String product, String description, String getReportNameWithTimeStamp) throws InterruptedException, IOException {

        extent = new ExtentReports();

        targetPath = targetPath + generateTimeStamp("yyyy_MM_dd_HH_mm_ss", 0);
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(targetPath + " ExtentReport.html");

        sparkReporter.viewConfigurer().viewOrder().as(new ViewName[]{
                ViewName.DASHBOARD,
                ViewName.TEST,
                ViewName.AUTHOR,
                ViewName.CATEGORY,
                ViewName.DEVICE
        }).apply();
        sparkReporter.config().setReportName("Hotel Booking Automation Testing");
        sparkReporter.config().setDocumentTitle("Automation Testing");
        sparkReporter.config().setCss(".brand-logo { content: url('src/test/resources/fnb.png'); }");

        extent.attachReporter(sparkReporter);
    }

    public static String generateTimeStamp(String myFormat, int additionalHours) {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.HOUR, additionalHours);
        return new SimpleDateFormat(myFormat).format(calender.getTime());
    }
}