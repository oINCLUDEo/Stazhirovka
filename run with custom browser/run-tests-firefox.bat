@echo off
echo Running tests in Firefox...
mvn clean test -Dbrowser=firefox -DsuiteXmlFile="src/test/resources/test_suite.xml" -Dusername=angular -Dpassword=password -DsqlExUsername=sqluser -DsqlExPassword=sqlpass
pause 