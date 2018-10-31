# WebAppTestFramework
This is a framework which can be used for WEB APP and REST API testing. 


# Technologies/tools
- Java,
- mvn,
- TestNG,
- ExtentReports,
- restAssured,
- AssertJ,
- log4J.

This framework is based on Page object Model with some modifications.

It allows You to run automated Web app and Web API tests in parallel. Screenshots and reporting included. It handles race condition with user reservation for parallel tests.

It can be easily integrated with CI/CD and also is prepared for connection to database.

In this framework You can find various ways of locating elements on pages and it also fits for frame based apps.


# QUICK START
To start using it, You only need to have installed java sdk ("1.8.0_181"), chrome, mvn (apache-maven-3.5.4) and Java IDE (I preffer IntelliJ)  --(tools vers. can be upgraded)

1. Download source code.
2. Open it in IDE.
3. Enable auto-import functionality and let mvn resolve all dependencies.
4. Right click on "testScope.xml" and click Run.
5. Report can be found under /target/report/
6. Scrreenshots can be found under /target/screenshots
7. Feel free to break tests and try to write more.


# Example of test report:
![My image](https://github.com/kwolkowicz/WebAppTestFramework/blob/master/reportResults.PNG)
