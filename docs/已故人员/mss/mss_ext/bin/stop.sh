#!/bin/bash

usage() { echo "Usage: $0 [[-H MSS_HOME] [-b] BUSINESS_NAME"; exit 1; }

while getopts ":b:h:H:" opt; do
    case $opt in
        H)
            MSS_HOME=${OPTARG}
            ;;
        b)
            BUSINESS_NAME=${OPTARG}
            ;;
        h)
            usage
            ;;
        *)
            usage
            ;;
    esac
done
shift $((OPTIND-1))

if [ "$#" -ne 0 ] && [ -z "$BUSINESS_NAME" ]; then
    BUSINESS_NAME=$1
    shift
fi

if [ -z "$BUSINESS_NAME" ] ; then
    usage
fi

if [ -z "$MSS_HOME" ] ; then
    MSS_HOME="/home/mss"
fi

PID_FILE=$MSS_HOME/pids/$BUSINESS_NAME.pid

echo stopping $BUSINESS_NAME
if [ -f "$PID_FILE" ]; then
    if [ -s "$PID_FILE" ]; then
        PID=`cat "$PID_FILE"`
        kill -0 $PID >/dev/null 2>&1
        if [ $? -gt 0 ]; then
            rm -f "$PID_FILE" >/dev/null 2>&1
            echo "PID file found but no matching process was found. PID file cleaned. Stop aborted."
            exit 1
        else
            echo "Killing $APP_NAME with the PID: $PID"
            kill $PID
            echo "Waiting $APP_NAME stopping"
            while kill -0 $PID 2>/dev/null; do sleep 1; done
            echo "$APP_NAME stopped"
            rm -f "$PID_FILE" >/dev/null 2>&1
            if [ $? != 0 ]; then
                echo "$project was killed but the PID file could not be removed."
            fi
        fi
    else
        echo "PID file is empty and has been ignored."
    fi
else
    echo "$PID_FILE file does not exist. Is $BUSINESS_NAME running? Stop aborted."
    exit 1
fi
