#!/bin/bash

# Down all
sqlf shut-down-all

# Make sure shitdown finished
sleep 30

# Cleanup
rm -R $DATA_DIR
rm *.log