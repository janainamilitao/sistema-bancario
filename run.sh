#!/bin/bash

#------------------
# VARIAVEIS
#------------------
DB_URL=$1
DB_USER=$2
DB_PASS=$3
ADMIN_USER=bank
SERVICE_DIR_BASE=/home/$ADMIN_USER
SERVICE_DIR=$SERVICE_DIR_BASE/app
SERVICE_APP_FILE=sistema-bancario.jar
BIN_JAVA=/usr/local/openjdk-8/bin/java

#------------------
# MAIN
#------------------

# Iniciando a aplicacao
cd $SERVICE_DIR/
#./mvnw clean install

/bin/su -c "$BIN_JAVA \
            -Dspring.datasource.url='jdbc:$DB_URL' \
            -Dspring.datasource.username=$DB_USER \
            -Dspring.datasource.password=$DB_PASS \
            -jar $SERVICE_DIR/$SERVICE_APP_FILE" $ADMIN_USER