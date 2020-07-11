#/bin/sh

HOST_IP=192.168.1.35

#   Clients service
cp -rf Clients/dist/Clients.war wildfly-12.0.0.Final/standalone/deployments

#   Request service

#   ...

wildfly-12.0.0.Final/bin/standalone.sh -b $HOST_IP