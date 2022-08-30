#!/usr/bin/env bash

WORKSPACE=$(cd `dirname $0`; pwd)
LIB_WORKSPACE=$WORKSPACE/lib
CONFPATH=.

while getopts ":ch" opt
do
    case $opt in
        c)
        CONFPATH="${OPTARG}"
        ;;
        h)
        echo -e "Usage:　bash run.sh [option] [param] ...\nExcute mo oltp load task"
        echo -e "   -c  set config path, mo-load will use run.yml, replace.yml from this path"
        echo "For more support,please email to dong.su@matrixorigin.io"
        exit 1
        ;;
        ?)
        echo "Unkown parameter,please use -h to get help."
        exit 1;;
    esac
done


function boot {
local libJars libJar
for libJar in `find ${LIB_WORKSPACE} -name "*.jar"`
do
  libJars=${libJars}:${libJar}
done
java -Xms10240M -Xmx30720M -cp ${libJars} \
        -Drun.yml=${CONFPATH}/run.yml \
        -Dreplace.yml=${CONFPATH}/replace.yml \
        io.mo.MOPerfTest
}

boot
