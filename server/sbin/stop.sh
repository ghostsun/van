#! /bin/sh

BASE_DIR=$(cd $(dirname $0); pwd)
echo $BASE_DIR

sh $BASE_DIR/configserver.sh stop
