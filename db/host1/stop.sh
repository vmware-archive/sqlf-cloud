#!/bin/bash

# Down all
sqlf shut-down-all -locators=$LOCAL_IP:$LOCAL_PORT
sqlf locator stop -dir=tempdata/locator

# Make sure shitdown finished
sleep 30

# Cleanup
rm -R $DATA_DIR
rm *.log
