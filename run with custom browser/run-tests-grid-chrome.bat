@echo off
echo Running tests in Chrome Selenium Grid...
mvn clean test -Dbrowser=chrome -DgridUrl=http://localhost:4444/wd/hub -DsuiteXmlFile="src/test/resources/test_suite.xml" -Dusername=angular -Dpassword=password -DsqlExUsername=sqluser -DsqlExPassword=sqlpass
pause 