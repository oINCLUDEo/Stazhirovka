@echo off
echo Running tests in Chrome...
mvn clean test -Dbrowser=chrome -DsuiteXmlFile="src/test/resources/test_suite.xml" -Dusername=angular -Dpassword=password -DsqlExUsername=sqluser -DsqlExPassword=sqlpass
pause 