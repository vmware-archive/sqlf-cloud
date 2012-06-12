# Get current directory
DEMO_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# System specific
export SYSTEM_ID=1
export LOCAL_IP=us-east.chmarny.com
export LOCAL_NAME=us-east.chmarny.com
export SYNC_TO=sing.chmarny.com
export SYNC_TO_PORT=7780

export LOCAL_PORT=7770
export LOCATOR_PORT=7773
export SERVER1_PORT=7771
export SERVER2_PORT=7772

# Common
export GROUP_NAME=DEMOGOUP
export DATA_DIR=$DEMO_HOME/tempdata
export LOCATOR_DIR=$DATA_DIR/locator
export SERVER1_DIR=$DATA_DIR/server1
export SERVER2_DIR=$DATA_DIR/server2




