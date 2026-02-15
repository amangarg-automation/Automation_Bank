package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;

public class ExtentManager {
    private static ExtentReports reports;
    private static ExtentSparkReporter reporter;
    public static ExtentReports getInstance(ITestContext context)
    {
        String reportPath= "/app/ExtentReports/"+System.currentTimeMillis();
        reporter=new ExtentSparkReporter(reportPath);
        reports=new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Tester",System.getProperty("user.name"));
        return reports;
    }
}
