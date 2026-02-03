package Utilities;

import Base.PDFGenerator;
import Base.StepLogger;
import CommonPageElements.LoginPage;
import io.cucumber.core.gherkin.Step;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CommonSteps {
    private WebDriver driver;
    private PDFGenerator pdfGenerator;
    private StepLogger stepLogger;
    Logger logger= LogManager.getLogger(this.getClass());
    public CommonSteps(WebDriver driver,PDFGenerator pdfGenerator)
    {
       this.driver=driver;
       this.pdfGenerator=pdfGenerator;
       this.stepLogger=new StepLogger(driver,pdfGenerator);
    }
    public boolean verifyUserLogin(String username, String password) throws IOException {
        try {
            LoginPage.clickOnLoginButton();
            if (LoginPage.verifyLoginPage()) {
                stepLogger.addSteptoPDF("Click on LoginButton and Verify user is navigated to Login Page", "User should be navigated to login button", "User is navigated to Login page", "Pass");
                return true;
            }
            else {
                stepLogger.addSteptoPDF("Click on LoginButton and Verify user is navigated to Login Page", "User should be navigated to login button", "User is not navigated to Login page", "Fail");
                logger.error("Step Failed");
                return false;
            }
        } catch (Exception e) {
            stepLogger.addSteptoPDF("Click on LoginButton and Verify user is navigated to Login Page", "User should be navigated to login button", "User is not navigated to Login page due to following error: "+e.getMessage(), "Fail");
            logger.error("Step Failed due to following error :"+e.getMessage());
            return false;
        }
    }
}
