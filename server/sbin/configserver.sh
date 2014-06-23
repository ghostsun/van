#! /bin/sh

CLASS_NAME=com.sunan.van.main.Main

BASE_DIR=$(cd $(dirname $(cd $(dirname $0); pwd)); pwd)
echo $BASE_DIR

LOG_PATH=$BASE_DIR/logs
LIB_PATH=$BASE_DIR/lib
GC_LOG_PATH=$BASE_DIR/gclogs
CONF_DIR=$BASE_DIR/conf
PID_FILE=$BASE_DIR/configserver.pid

test -d $LOG_PATH || mkdir $LOG_PATH

nowday=`date +%Y%m%d_%H%M%S`
test -d $GC_LOG_PATH || mkdir $GC_LOG_PATH


CLASSPATH=$CONF_DIR

for f in $LIB_PATH/*.jar; do
    CLASSPATH=$CLASSPATH:$f
done
echo $CLASSPATH

PROGRAM_ARGS="-Xms6g -Xmx6g  -Xmn2g -XX:PermSize=128m -XX:MaxPermSize=128m -XX:+UseConcMarkSweepGC -server -XX:SurvivorRatio=5 -XX:CMSInitiatingOccupancyFraction=80 -XX:+PrintTenuringDistribution -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOG_PATH/memory.hprof -Xloggc:$GC_LOG_PATH/gc.log.$nowday"

start() {
	if test -e $PID_FILE
		then
			echo
            echo Config Server already Started!
            echo
    else
    		echo
    		echo Starting config server...
    		echo
    		cd $BASE_DIR
    		$JAVA_HOME/bin/java $PROGRAM_ARGS -classpath $CLASSPATH $CLASS_NAME & #2>&1>$LOG_PATH/console.log &
    		echo $!>$PID_FILE
            sleep 2
            STATUS=`ps -p $!|grep java |awk '{print $1}'`
            if test $STATUS
            	then
                	echo Config Server Started!
                    echo
            else
            	rm $PID_FILE
                echo Config Server Start Failed
                echo please Check the system
                echo
           	fi
  	fi
}

stop() {
	if test -e $PID_FILE
		then
			echo 
			echo Stopping Config Server....
			echo 
			TPID=`cat $PID_FILE`
           	kill -9 $TPID
           	sleep 1
           	STATUS=`ps -p $TPID |grep java | awk '{print $1}'`
           	if test $STATUS
           		then
           			echo Config Server NOT Stopped!
                    echo please Check the system
                    echo
            else
            	echo Config Server Stopped
                echo
                rm $PID_FILE
           	fi
	else
		echo
        echo Config Server already Stopped!
        echo
    fi
}

status()
{
	if test -e $PID_FILE
        then
        	TPID=`cat $PID_FILE`
            STATUS=`ps -p $TPID|grep java | awk '{print $1}'`
            if test $STATUS
            	then
                	echo "Config Server Running($TPID)!"
                    echo
            else
            	echo Config Server NOT Running!
                rm $PID_FILE
                echo
           	fi
	else
    	echo
        echo Config Server NOT Running!
        echo
	fi
}

case "$1" in
	'start')
        start
        ;;
	'stop')
        stop
        ;;
	'status')
        status
        ;;
	*)
        echo
        echo
        echo "Usage: $0 {status | start | stop }"
        echo
        echo Status of config Servers ......
        	status
        ;;
esac
exit 0
