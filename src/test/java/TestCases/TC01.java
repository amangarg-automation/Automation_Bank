package TestCases;

import Base.Setup;
import Base.StepLogger;
import Utilities.CommonSteps;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class TC01 extends Setup {

    @Test
    public void TC01() throws IOException {
        SoftAssert softAssert=new SoftAssert();
        CommonSteps commonSteps=new CommonSteps(driver,pdfGenerator);
        boolean flag=commonSteps.verifyUserLogin("name","password");
        System.out.println(flag);
       softAssert.assertTrue(flag);
       softAssert.assertAll();
    }
}
