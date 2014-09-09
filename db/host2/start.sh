#!/bin/bash

mkdir $DATA_DIR
mkdir $LOCATOR_DIR
mkdir $SERVER1_DIR
mkdir $SERVER2_DIR

sqlf locator start \
	-peer-discovery-address=$LOCAL_IP \
	-peer-discovery-port=$LOCAL_PORT \
	-locators=$LOCAL_IP:$LOCAL_PORT \
	-conserve-sockets=false \
        -license-serial-number=Y102F-J0J93-N8A8R-0M2VK-2EJ4U,Y543F-J0101-1828H-0P2WK-3DT6Y \
	-distributed-system-id=$SYSTEM_ID \
	-remote-locators=$SYNC_TO[$SYNC_TO_PORT] \
	-client-bind-address=$LOCAL_IP \
	-client-port=$LOCATOR_PORT \
	-dir=$LOCATOR_DIR 

sqlf server start \
	-server-groups=$GROUP_NAME \
	-locators=$LOCAL_IP[$LOCAL_PORT] \
        -license-serial-number=Y102F-J0J93-N8A8R-0M2VK-2EJ4U,Y543F-J0101-1828H-0P2WK-3DT6Y \
	-client-bind-address=$LOCAL_IP \
	-client-port=$SERVER1_PORT \
	-dir=$SERVER1_DIR 

sqlf server start \
	-server-groups=$GROUP_NAME \
	-locators=$LOCAL_IP[$LOCAL_PORT] \
        -license-serial-number=Y102F-J0J93-N8A8R-0M2VK-2EJ4U,Y543F-J0101-1828H-0P2WK-3DT6Y \
	-client-bind-address=$LOCAL_IP \
	-client-port=$SERVER2_PORT \
	-dir=$SERVER2_DIR 
