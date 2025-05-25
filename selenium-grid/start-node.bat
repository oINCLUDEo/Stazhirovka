@echo off
set /p NODE_PORT=Enter node port (default 5555): 
if "%NODE_PORT%"=="" set NODE_PORT=5555

set /p BROWSER_COUNT=Enter number of browser instances (default 5): 
if "%BROWSER_COUNT%"=="" set BROWSER_COUNT=5

echo Starting Selenium Grid Node
java -jar selenium-server-4.32.0.jar node --port %NODE_PORT% --hub http://localhost:4444 --max-sessions %BROWSER_COUNT% --max-threads 5
pause 