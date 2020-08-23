#!/bin/bash
git pull

mvn clean install
mvn spring-boot:run