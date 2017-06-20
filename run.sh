#!/bin/bash
case $1 in
    start)
       echo $$ > /var/run/etherManager.pid;
       exec /opt/EthosRigManager/./gradlew run
       ;;
    stop)  
      kill `cat /var/run/etherManager.pid` ;;
    *)  
      echo "usage: etherManager {start|stop}" ;;
esac
exit 0
