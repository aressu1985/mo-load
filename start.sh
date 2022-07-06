#!/usr/bin/env bash

WORKSPACE=$(cd `dirname $0`; pwd)
CONFIG_YAML=$WORKSPACE/conf.yml
TRANSACTION_YAML=$WORKSPACE/transaction.yml
LIB_WORKSPACE=$WORKSPACE/lib


function boot {
local libJars libJar
for libJar in `find ${LIB_WORKSPACE} -name "*.jar"`
do
  libJars=${libJars}:${libJar}
done
java -Xms10240M -Xmx30720M -cp ${libJars} \
        -Dconf.yml=${CONFIG_YAML} \
        -Dtransaction.yml=${TRANSACTION_YAML} \
        io.mo.KunPerfTest
}

boot