package com.Appscook.listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class ListenerImpl implements ITestListener {

	@Override
	public void onTestStart(ITestResult result)
	{
		log.info("===========================================================");
		log.info("Test Case Execution Started,Test Name is:\t"+result.getName());
		log.info("===========================================================");
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		log.info("===========================================================");
		log.info("Test Case Executed Sucesfully,Test Name is:\t"+result.getName());
		log.info("===========================================================");
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		log.info("===========================================================");
		log.info("Test Case Execution Got Failed,Test Name is:\t"+result.getName());
		log.info("===========================================================");
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		log.info("===========================================================");
		log.info("Test Case is Skipped,Test Name is:\t"+result.getName());
		log.info("===========================================================");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{


	}

	@Override
	public void onStart(ITestContext context)
	{


	}

	@Override
	public void onFinish(ITestContext context)
	{


	}



}
