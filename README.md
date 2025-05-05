# PrestaShop project

Project has one e2e test to check Shop (http://demo.prestashop.com) main flow from Account registration till make an order.
(!) Web site has bug (!) - disabled button for make an order (Button is disabled in any case of flow and UserInfo)

##  Project Abilities

- Project implements UI test flow
- Allure report can be generated after test

## Technology stack

- Language: Java ms-17 17.0.14
- Frameworks: Selenium, TestNG
- Tools: Gradle, Allure

## Deploy
- IntellijIdia -> File -> New -> Project from version control -> repository URL
- copy URL -> Clone
  
## Test run
IntellijIdia: 
- open test -> java -> UserCaseTest
- Run UserTestCase

Terminal:
- run test: .\gradlew clean test
- create Allure report: .\gradlew allureReport
- open Allure report: .\gradlew allureServe




