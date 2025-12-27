# intern3-sample

This sample project contains a minimal Java service with unit tests, SLF4J/Logback logging, and JaCoCo coverage configuration.

Quick commands:

- Run tests and generate reports:

```bash
mvn test verify
```

- Generate JaCoCo report explicitly:

```bash
mvn test jacoco:report
```

Deliverables in this folder:
- Test suite: `src/test/java/com/example/service/UserServiceTest.java`
- Coverage report: generated under `target/site/jacoco/index.html` after running `mvn verify`
- Logging config: `src/main/resources/logback.xml`
- Sample logs: `logs/sample.log`

Notes:
- Tests use JUnit 5 and Mockito.
- Logging uses SLF4J API + Logback implementation; logs will go to console and `logs/app.log` when running the app/tests.

Run tests helper (Windows PowerShell):

```powershell
# from project root
.\run-tests.ps1
```

The `run-tests.ps1` script will download Apache Maven (if `mvn` is not on PATH) and run `mvn test verify`.
