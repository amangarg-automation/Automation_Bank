package TestCases;

import Base.Setup;
import Base.StepLogger;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC01 extends Setup {

    @Test
    public void TC01() throws IOException {
        StepLogger stepLogger=new StepLogger(driver,pdfGenerator);
        System.out.println("Started Test Case");
        stepLogger.addSteptoPDF("Started TC1","Start tc","Started tc","pass");
    }
}
