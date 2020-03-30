package com.githubdemo;



import java.io.File;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Github {
ExtentReports extent;
ExtentTest test;
@BeforeTest
public void startReport() {
	extent=new ExtentReports(System.getProperty("user.dir")+"/test-output/report.html",true);
	extent.addSystemInfo("HostName","anvitha").addSystemInfo("Environment","Production environment").addSystemInfo("User Name","anvitha");
	extent.loadConfig(new File(System.getProperty("user.dir")+"/extent.config"));
	
}
@Test
public void demoReportPass() {
	test=extent.startTest("demoReportPass");
	Assert.assertTrue(true);
	test.log(LogStatus.PASS,"Assert pass as condition is true");
}
@Test
public void demoReportfail() {
	test=extent.startTest("demoReportFail");
	Assert.assertTrue(false);
	test.log(LogStatus.FAIL,"Assert fail as condition is false");
}
@AfterMethod
public void getResult(ITestResult result) {
	if(result.getStatus()==ITestResult.FAILURE) {
		test.log(LogStatus.FAIL,result.getThrowable());
	}
	extent.endTest(test);
	
	}
@AfterTest
public void endReport() {
	extent.flush();
	extent.close();
}


}
