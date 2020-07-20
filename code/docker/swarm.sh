#!/bin/bash

# project context
master_ip=10.0.0.101
declare -a slaves_ip=("10.0.0.102" "10.0.0.103")
service_name="lei"

# terminal colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# checks ssh connectivity to a specified machine ip address
function checkConn() {
    if [[ $(nmap ${1} -PN -p ssh | grep -oh open) != "open" ]]; then
        echo -en ${RED}could not connect to
        if [[ $1 == $master_ip ]]; then 
            echo -en \ master
        else
            echo -en \ slave
        fi
        echo -e \ at $1${NC}
        echo -e ${YELLOW}sugestion:${NC} check if VMs are up and running by typing ${GREEN}vagrant status${NC}
        exit 1  
    fi
}

# checks ssh connectivity to all machines (master and slaves)
function checkAllConns() {
    echo -e ${GREEN}checking VMs connectivity ...${NC}
    
    # this condition checks if nmap is installed on your system 
    # (or at least if it's accessible by the $PATH system variable)
    if ! [[ -x $(command -v nmap) ]]; then
        echo -e ${RED}to check VMs connectivity you need to install nmap.${NC}
        echo -e please install nmap and run this script again.
        echo -e ${YELLOW}sugestion \(on ubuntu\):${NC} sudo apt install nmap${NC}
        echo -e ${YELLOW}sugestion \(on mac\):${NC} brew install nmap${NC}
        exit 1
    fi
    
    checkConn $master_ip
    for ip in ${slaves_ip[@]};
    do
        checkConn $ip
    done
}

if [[ $1 == "help" ]]; then
    echo -e ${GREEN}$0 create${NC} - to create VMs, swarm and make slaves join swarm.
    echo -e ${GREEN}$0 update${NC} - to update swarm services.
    exit 0
fi

if [[ $1 == "create" || $1 == "update" ]]; then

    # checks VMs connectivity 
    checkAllConns

    if [[ $1 == "create" ]]; then

        # master inits docker swarm
        echo -e ${GREEN}master is trying to init swarm ...${NC}
        ssh vagrant@$master_ip "sudo docker swarm init --advertise-addr ${master_ip} && exit"

        # builds join-swarm.sh script (temporary file) to be run on slaves
        echo -e ${GREEN}getting swarm join command from master ...${NC}
        ssh vagrant@$master_ip "sudo docker swarm join-token worker" | echo sudo $(grep -oh "docker.*") > join-swarm.sh
        echo -e ${YELLOW}$(cat join-swarm.sh)${NC}

        # slaves join swarm
        for ip in ${slaves_ip[@]};
        do
            echo -e ${GREEN}slave at $ip is trying to join swarm ...${NC}
            ssh vagrant@$ip 'bash -s' < join-swarm.sh
        done

        # creating service folder in master
        echo -e ${GREEN}creating $service_name/ folder "in" master ...${NC}
        ssh vagrant@$master_ip "rm -r -f ${service_name}/ && mkdir ${service_name}/ && chmod 777 -R ${service_name}/"

    fi

    # copy needed files to master
    echo -e ${GREEN}building docker-compose.yml from \'docker-compose-for-swarm.yml\' and \'.env\' files ...${NC}
    sudo docker-compose -f docker-compose-for-swarm.yml config > tmp
    echo -e ${GREEN}copying docker-compose.yml to master ...${NC}
    scp tmp vagrant@$master_ip:$service_name/docker-compose.yml

    # removes temporary files
    echo -e ${GREEN}cleaning temporary files ...${NC}
    rm -f join-swarm.sh
    rm -f tmp

    # starts service
    echo -e ${GREEN}starting/ updating $service_name service ...${NC}
    ssh vagrant@$master_ip "sudo docker stack deploy -c ~/${service_name}/docker-compose.yml ${service_name}"

else
    echo -e ${RED}invalid argument, type ${NC}$0 help${RED} to get more info.${NC}
    exit 0
fi