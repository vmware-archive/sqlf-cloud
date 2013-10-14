# Get current directory
DEMO_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# System ID used by Sqlf
export SYSTEM_ID=2

# Local IP and Name
export LOCAL_IP=10.0.1.17
export LOCAL_NAME=localhost

# IP or name of remote SQLF Cluster
export SYNC_TO=10.0.1.17

# Port used by remote Locator for synch
export SYNC_TO_PORT=7770

# Port for remote synch on SQLF cluster
export LOCAL_PORT=7780

# Local ports ofr local SQLF cluster
export LOCATOR_PORT=7783
export SERVER1_PORT=7781
export SERVER2_PORT=7782

# Common
export GROUP_NAME=DEMOGOUP
export DATA_DIR=$DEMO_HOME/tempdata
export LOCATOR_DIR=$DATA_DIR/locator
export SERVER1_DIR=$DATA_DIR/server1
export SERVER2_DIR=$DATA_DIR/server2



