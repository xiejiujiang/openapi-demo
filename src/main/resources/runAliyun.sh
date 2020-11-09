#! /bin/bash

CurrentDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

cd ${CurrentDir}



SETENV_DIR=${CurrentDir%/*}'/service'
if [ -x $SETENV_DIR/setenv.sh ];then
. $SETENV_DIR/setenv.sh
fi


eval exec java $JAVA_OPTS -Dfile.encoding=UTF-8 -Duser.country=US -Duser.language=en -Dspring.profiles.active=${ConfigType} -Xms1024m -Xmx4096m -Xmn256m -Xss256k -XX:PermSize=256M -XX:MaxPermSize=256M -XX:MaxTenuringThreshold=7 -XX:GCTimeRatio=19 -jar `pwd`/changsha-bank-pay-service.jar
