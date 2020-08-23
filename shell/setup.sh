#!/bin/bash
# FOR PROJECT INITIALIZATION ONLY!
sudo apt-get update
sudo apt install postgresql
sudo -u postgres psql -c "CREATE DATABASE gracz_pl"
sudo apt-get install git -y
sudo apt-get install -yq maven
sudo iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080
git clone https://github.com/kraleppa/gracz-pl-back