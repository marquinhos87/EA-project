#/usr/bin/sh

#HOST_IP=192.168.1.53

HOST_IP=192.168.1.139

#   Clients service
cp -rf Clients/dist/Clients.war wildfly-12.0.0.Final/standalone/deployments

#   Request service
cp -rf Request/Request/dist/Request.war wildfly-12.0.0.Final/standalone/deployments

#   Core service
#cp -rf Core/Core/dist/Core.war wildfly-12.0.0.Final/standalone/deployments

#   GymAtHome service
#cp -rf GymAtHome/GymAtHome/dist/GymAtHome.war wildfly-12.0.0.Final/standalone/deployments

#   PersonalTrainer service
#cp -rf PersonalTrainer/dist/PersonalTrainer.war wildfly-12.0.0.Final/standalone/deployments

#   Notification service
#cp -rf Notification/dist/Notification.war wildfly-12.0.0.Final/standalone/deployments

wildfly-12.0.0.Final/bin/standalone.sh -b $HOST_IP
