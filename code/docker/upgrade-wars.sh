#/usr/bin/sh
	# remove old and copy new war
	rm -rf client/Clients.war
	cp -rf ../backend/Clients/dist/Clients.war client
	rm -rf core/Core.war
	cp -rf ../backend/Core/Core/dist/Core.war core
	rm -rf gymathome/GymAtHome.war
	cp -rf ../backend/GymAtHome/GymAtHome/dist/GymAtHome.war gymathome
	rm -rf notification/Notification.war
	cp -rf ../backend/Notification/dist/Notification.war notification
	rm -rf pt/PersonalTrainer.war
	cp -rf ../backend/PersonalTrainer/dist/PersonalTrainer.war pt
	rm -rf request/Request.war
	cp -rf ../backend/Request/Request/dist/Request.war request
	echo "updated sucessfully!"
