#smart!/usr/bin/env bash


set -e
ES_HOME=`dirname "$0"`
ES_HOME=`cd "$ES_HOME"; pwd`

# check java version
# set your own JAVA_HOME if necessary
if [ -z $JAVA_HOME ]; then
    echo "Error: JAVA_HOME is not set."
    exit 1
fi
JAVA=${JAVA_HOME}/bin/java
JAVA_VER=$(${JAVA} -version 2>&1 | sed 's/.* version "\(.*\)\.\(.*\)\..*"/\1\2/; 1q')
if [[ $JAVA_VER < 16 ]]; then
    echo "Error: java version is too old" $JAVA_VER " need jdk 1.7."
    exit 1
fi

ANT_HOME=${ES_HOME}/deps/ant/
export PATH=${ANT_HOME}/bin:$PATH
cd $ES_HOME
ant output

