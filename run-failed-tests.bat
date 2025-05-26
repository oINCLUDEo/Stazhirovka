@echo off

echo Running failed tests

if not exist "target\surefire-reports\testng-failed.xml" (
    echo Error testng-failed.xml not found
    pause
    exit /b 1
)

mvn test -DsuiteXmlFile=target\surefire-reports\testng-failed.xml

echo.
pause

exit /b