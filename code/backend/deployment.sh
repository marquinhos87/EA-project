#/usr/bin/sh

HOST_IP=192.168.1.53

#   Clients service
cp -rf Clients/dist/Clients.war wildfly-12.0.0.Final/standalone/deployments

#   Request service
cp -rf Request/Request/dist/Request.war wildfly-12.0.0.Final/standalone/deployments

#   ...

wildfly-12.0.0.Final/bin/standalone.sh -b $HOST_IP
