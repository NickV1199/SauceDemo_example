# SauceDemo UI Test Automation

Automated UI testing project for [SauceDemo](https://www.saucedemo.com/), a demo e-commerce website.

The project demonstrates automated functional testing using **Java, Selenium WebDriver, TestNG, and the Page Object Model (POM)**, with Allure reporting and GitHub Actions CI.

## Tech Stack

- **Java 17** — target Java version
- **Selenium WebDriver 4.26.0** — browser automation
- **TestNG 7.11.0** — test execution, assertions, and data-driven testing
- **Gradle** — build and dependency management
- **Allure** — test steps, results, and screenshots on failure
- **GitHub Actions** — continuous integration
- **Google Chrome** — browser used for UI testing

## Test Coverage

### Login
- Successful login with valid credentials
- Invalid username and password
- Empty username or password
- Locked-out user
- Login form elements validation
- Error message validation

### Shopping Cart
- Adding products to the cart
- Removing products from the cart
- Checking product names and quantities
- Cart navigation

### Checkout
- Successful checkout
- Customer information validation
- Error messages for missing required fields
- Order summary validation
- Product prices, subtotal, tax, and total verification
- Order confirmation

## Project Architecture

The project follows the **Page Object Model (POM)** design pattern to separate test logic from UI interactions.

```text
src/
├── main/
│   └── java/
│       ├── manager/
│       │   └── ApplicationManager.java
│       └── pages/
│           ├── BasePage.java
│           ├── LoginPage.java
│           ├── InventoryPage.java
│           ├── CartPage.java
│           ├── CheckoutPage.java
│           ├── CheckoutOverviewPage.java
│           └── CheckoutCompletePage.java
└── test/
    └── java/
        └── tests/
            ├── TestBase.java
            ├── LoginTest.java
            ├── CartTest.java
            └── CheckoutTest.java
```

**Key components:**

- `BasePage` — shared WebDriver methods and explicit waits
- `ApplicationManager` — browser setup and Page Object initialization
- Page Objects — page-specific locators and user actions
- `TestBase` — test setup, teardown, and failure screenshots
- Test classes — test scenarios, assertions, and TestNG data providers

## Running Tests

### Prerequisites

- JDK 17 or newer
- Google Chrome
- Internet connection

The project includes the Gradle Wrapper, so a separate Gradle installation is not required.

### Run all tests

**Windows:**

```bash
./gradlew.bat clean test
```

**Linux / macOS:**

```bash
./gradlew clean test
```

### Run smoke tests

```bash
./gradlew clean test -PtestGroup=smoke
```

### Run regression tests

```bash
./gradlew clean test -PtestGroup=regression
```

### Run a specific test class

```bash
./gradlew clean test --tests "tests.LoginTest"
```

## Test Reports

The project integrates **Allure** for test reporting.

Allure steps are added to Page Object methods using `@Step` annotations.

Screenshots are automatically attached to Allure results when a test fails.

Test results are generated in:

```text
build/allure-results/
```

TestNG HTML reports are available in:

```text
build/reports/tests/test/index.html
```

## Continuous Integration

The project uses **GitHub Actions** to automatically run tests on pushes and pull requests.

The CI workflow:

1. Checks out the repository
2. Sets up Java 17
3. Installs Google Chrome
4. Runs the Selenium test suite using Gradle
5. Uploads test reports as workflow artifacts

Tests run in **headless Chrome** in the CI environment.

## Test Automation Practices

This project demonstrates:

- Page Object Model architecture
- Data-driven testing with TestNG DataProviders
- Explicit waits for dynamic web elements
- Test grouping (smoke and regression)
- UI validation using assertions
- Monetary calculations using `BigDecimal`
- Screenshot capture on test failures
- Continuous integration with GitHub Actions

## Application Under Test

**Website:** https://www.saucedemo.com/

SauceDemo is a publicly available demo application used for practicing web application testing and automation.