package extentUtility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentUtility {

    private static ExtentReports extent;
    private static ExtentTest testReport;
    private static final String pathToProject = System.getProperty("user.dir") + "/target/extentReports";

    // metoda care initializeaza un raport - fisier html care poate contine mai multe teste
    public static void initiateReport() {
        createDirectory();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(pathToProject + "ExtentReport.html");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    // metoda care face un folder
    private static void createDirectory() {
        File directory = new File(pathToProject);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // metoda care face un test report -  pentru fiecare test
    public static void startTest(String testName) {
        testReport = extent.createTest(testName + " - report");
        //testReport.log(Status.INFO, "----START TEST---- " + testName);
        attachReportLog(ReportStep.INFO_STEP, "----START TEST---- " + testName);
    }

    public static void finishTest(String testName) {
        attachReportLog(ReportStep.INFO_STEP, "----FINISH TEST---- " + testName);
    }

    // metoda care adauga un entry in test report
    public static void attachReportLog(String attachType, String message) {
        if (attachType.equals(ReportStep.INFO_STEP)) {
            testReport.log(Status.INFO, message);
        }
        if (attachType.equals(ReportStep.PASS_STEP)) {
            testReport.log(Status.PASS, message);
        }
        if (attachType.equals(ReportStep.FAIL_STEP)) {
            testReport.log(Status.FAIL, message);
        }
    }

    // metoda care genereaza raportul
    public static void generateReport() {
        extent.flush();
    }
}
