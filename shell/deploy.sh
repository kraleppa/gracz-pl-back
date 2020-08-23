#!/bin/bash
cd ..
git pull

mvn clean install
mvn spring-boot:run