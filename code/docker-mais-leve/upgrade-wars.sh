#/usr/bin/sh
	# remove old and copy new war
	rm -rf Clients.war
	cp -rf ../backend/Clients/dist/Clients.war .
	rm -rf Core.war
	cp -rf ../backend/Core/Core/dist/Core.war .
	rm -rf GymAtHome.war
	cp -rf GymAtHomeLocalhost/GymAtHome/dist/GymAtHome.war .
	rm -rf Notification.war
	cp -rf ../backend/Notification/dist/Notification.war .
	rm -rf PersonalTrainer.war
	cp -rf ../backend/PersonalTrainer/dist/PersonalTrainer.war .
	rm -rf Request.war
	cp -rf ../backend/Request/Request/dist/Request.war .
	echo "updated sucessfully!"
