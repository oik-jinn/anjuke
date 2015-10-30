#!/bin/bash

usage() { echo "Usage: $0 [[-H MSS_HOME] [-m MAX_MEM]] [-bvp] BUSINESS_NAME VERSION HTTP_PORT "; exit 1; }

while getopts ":b:h:m:v:H:" opt; do
    case $opt in
        H)
            MSS_HOME=${OPTARG}
            ;;
        m)
            MAX_MEM=${OPTARG}
            ;;
        b)
            BUSINESS_NAME=${OPTARG}
            ;;
        v)
            VERSION=${OPTARG}
            ;;
        p)
            HTTP_PORT=${OPTARG}
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

if [ "$#" -ne 0 ] && [ -z "$VERSION" ]; then
    VERSION=$1
    shift
fi

if [ "$#" -ne 0 ] && [ -z "$HTTP_PORT" ]; then
    HTTP_PORT=$1
    shift
fi

if [ -z "$BUSINESS_NAME" ] || [ -z "$VERSION" ] || [ -z "$HTTP_PORT" ] ; then
    usage
fi

if [ -z "$MSS_HOME" ] ; then
    MSS_HOME="/home/mss"
fi

if [ -z "$MAX_MEM" ] ; then
    MAX_MEM="512M"
fi

MSS_CONFIG=$MSS_HOME/apps/$BUSINESS_NAME/conf/mss.properties
LOGGING_CONFIG=$MSS_HOME/apps/$BUSINESS_NAME/conf/logback.xml
PID_FILE=$MSS_HOME/pids/$BUSINESS_NAME.pid

if [ ! -f $MSS_HOME/bin/mss-$VERSION.jar ]; then
    echo "mss-$VERSION.jar not found. Start aborted."
    exit 1
fi

echo starting $APP_NAME $LAYER layer
if [ -f "$PID_FILE" ]; then
    # pid 存在
    if [ -s "$PID_FILE" ]; then
        # pid 文件 size 大于0
        echo "Existing PID file found during start."
        if [ -r "$PID_FILE" ]; then
            # pid 文件可读
            PID=`cat "$PID_FILE"`
            ps -p $PID >/dev/null 2>&1
            if [ $? -eq 0 ] ; then
                echo "$APP_NAME $LAYER layer appears to still be running with PID $PID. Start aborted."
                exit 1
            else
                echo "Removing/clearing stale PID file."
                rm -f "$PID_FILE" >/dev/null 2>&1
                if [ $? != 0 ]; then
                    if [ -w "$PID_FILE" ]; then
                        cat /dev/null > "$PID_FILE"
                    else
                        echo "Unable to remove or clear stale PID file. Start aborted."
                        exit 1
                    fi
                fi
            fi
        else
            echo "Unable to read PID file. Start aborted."
            exit 1
        fi
    else
        rm -f "$PID_FILE" >/dev/null 2>&1
        if [ $? != 0 ]; then
            if [ ! -w "$PID_FILE" ]; then
                echo "Unable to remove or write to empty PID file. Start aborted."
                exit 1
            fi
        fi
    fi
fi

nohup java -Xmn384M -Xmx$MAX_MEM -Xms512M \
    -Dbusiness.name=$BUSINESS_NAME \
    -Dmss.config=$MSS_CONFIG \
    -Dlogging.config=$LOGGING_CONFIG \
    -Dserver.port=$HTTP_PORT \
    -jar $MSS_HOME/bin/mss-$VERSION.jar \
    >/dev/null 2>&1 &

PID=$!
echo $PID > $PID_FILE
echo start $BUSINESS_NAME success
