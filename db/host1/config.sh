# Get current directory
DEMO_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# System ID used by Sqlfire 
export SYSTEM_ID=1

# Update with your local IP address and name
export LOCAL_IP=10.0.1.17
export LOCAL_NAME=us-east.chmarny.com

# Update with IP/Name of remote site
export SYNC_TO=10.0.1.17
export SYNC_TO_PORT=7780

# Local port for remote synch locator
export LOCAL_PORT=7770

# Local ports for Sqlf
export LOCATOR_PORT=7773
export SERVER1_PORT=7771
export SERVER2_PORT=7772

# Common
export GROUP_NAME=DEMOGOUP
export DATA_DIR=$DEMO_HOME/tempdata
export LOCATOR_DIR=$DATA_DIR/locator
export SERVER1_DIR=$DATA_DIR/server1
export SERVER2_DIR=$DATA_DIR/server2




