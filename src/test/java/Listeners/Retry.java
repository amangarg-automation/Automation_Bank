package Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int maxRetry=3;
    int retryCounter=0;
    @Override
    public boolean retry(ITestResult result) {
        if(retryCounter<maxRetry)
        {
            retryCounter++;
            return true;
        }
        else {
            return false;
        }
    }
}
