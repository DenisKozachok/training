package com.refinitive.listener;

import io.qameta.allure.TmsLink;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRefinitiveListener implements ITestListener, IMethodInterceptor, IAnnotationTransformer {

//    public static List<AddTestCaseResultModel> autoTestResults = new ArrayList<>();
//    public static List<Integer> caseIdList = new ArrayList<>();

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance> testsToRun = new ArrayList<>();
        for (IMethodInstance method : methods) {
//            UseAsTestRailId caseId = method
//                    .getMethod()
//                    .getConstructorOrMethod()
//                    .getMethod()
//                    .getAnnotation(UseAsTestRailId.class);

//            System.out.println("intercept " + caseId.testRailId());
//            if (caseId.testRailId() != 0)
//                caseIdList.add(caseId.testRailId());

            Test testClass = method.getInstance()
                    .getClass()
                    .getAnnotation(Test.class);
//            System.out.println("intercept " + getMethodName(method));
            if (testClass == null || testClass.enabled()) {
                testsToRun.add(method);
            }
        }
        return testsToRun;
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
//        System.out.println("transform - " + testMethod.getName() + testMethod.getAnnotation(UseAsTestRailId.class));
//        try {
//            if (dbCon.getDbConnection() != null) {
//                if (!dbCon.getDbConnection().isClosed()) {
//                    if (dbCon.getTestStatus(testMethod.getName()) == null) {
//                        dbCon.insertClassAndMethodNameInTable(null, testMethod.getName());
//                        annotation.setEnabled(true);
//                    } else annotation.setEnabled(dbCon.getTestStatus(testMethod.getName()));
//                }
//            } else annotation.setEnabled(true);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("Context Tests case name: " + iTestContext.getName() + " ON START");
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("Context Tests case name: " + iTestContext.getName() + " ON FINISH");
    }


    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test case id: " + getTestRailTestCaseId(iTestResult) + " and method name: " + getClassNameAndMethodName(iTestResult) + " - START");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test case id: " + getTestRailTestCaseId(iTestResult) + " and method name: " + getMethodName(iTestResult) + " - SUCCESS");

//        autoTestResults.add(new AddTestCaseResultModel()
//                .setCaseId(getTestRailTestCaseId(iTestResult))
//                .setStatusId(TestCaseStatus.PASSED)
//                .setComment("Test case id: " + getTestRailTestCaseId(iTestResult) + " and method name: " + getMethodName(iTestResult) + " - PASSED"));
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test case id: " + getTestRailTestCaseId(iTestResult) + " and method name: " + getMethodName(iTestResult) + getMethodName(iTestResult) + " - FAILURE");

//        autoTestResults.add(new AddTestCaseResultModel()
//                .setCaseId(getTestRailTestCaseId(iTestResult))
//                .setStatusId(TestCaseStatus.FAILED)
//                .setComment("Test case id: " + getTestRailTestCaseId(iTestResult) + " and method name: " + getMethodName(iTestResult) + " - FAILED"));
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test case id: " + getTestRailTestCaseId(iTestResult) + " and method name: " + getMethodName(iTestResult) + " - SKIPPED");

//        autoTestResults.add(new AddTestCaseResultModel()
//                .setCaseId(getTestRailTestCaseId(iTestResult))
//                .setStatusId(TestCaseStatus.RETEST)
//                .setComment("Test case id: " + getTestRailTestCaseId(iTestResult) + " and method name: " + getMethodName(iTestResult) + " - SKIPPED"));
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test case id: " + getTestRailTestCaseId(iTestResult) + " and method name: " + getClassNameAndMethodName(iTestResult) + " FailedButWithinSuccessPercentage");
    }


    private String getClassNameAndMethodName(ITestResult iTestResult) {
        return getClassName(iTestResult) + "." + getMethodName(iTestResult);
    }

    private String getClassName(ITestResult iTestResult) {
        String[] className = iTestResult.getTestClass().getName().split("\\.");

        return className[className.length - 1];
    }

    private String getMethodName(ITestResult iTestResult) {
        String[] methodName = iTestResult.getMethod().getMethodName().split("\\.");
        return methodName[methodName.length - 1];
    }

    private String getTestRailTestCaseId(ITestResult result) {
        Method testCaseMethod = result.getMethod().getConstructorOrMethod().getMethod();
        TmsLink testID = testCaseMethod.getAnnotation(TmsLink.class);
        String testCaseID;
      if (testID != null) {
            testCaseID = testID.value();
            return testCaseID;
        } else return "0";
    }
}

