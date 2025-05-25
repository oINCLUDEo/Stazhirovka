#!/bin/bash

read -p "Enter node port (default 5555): " NODE_PORT
NODE_PORT=${NODE_PORT:-5555}

read -p "Enter number of browser instances (default 5): " BROWSER_COUNT
BROWSER_COUNT=${BROWSER_COUNT:-5}

echo "Starting Selenium Grid Node"
java -jar selenium-server-4.32.0.jar node --port $NODE_PORT --hub http://localhost:4444 --max-sessions $BROWSER_COUNT --max-threads 5 