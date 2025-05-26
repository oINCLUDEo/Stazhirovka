@echo off
echo Running tests in Edge...
mvn clean test -Dbrowser=edge -DsuiteXmlFile="src/test/resources/test_suite.xml" -Dusername=angular -Dpassword=password -DsqlExUsername=sqluser -DsqlExPassword=sqlpass
pause 