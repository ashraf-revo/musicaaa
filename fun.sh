#!/usr/bin/env bash

function run_app(){
    mvn spring-boot:run
}

function run_angular_build(){
    cd src/main/resources/music-static/
    ng build --target=production
    rm -rf ../static/*.map
    rm -rf ../static/*.gz
    cd -
    if [ -d "target/classes" ]; then
            if [ -d "target/classes/static" ]; then
                rm -rf target/classes/static
            fi
        cp -r src/main/resources/static target/classes/static
    fi
}

function run_push(){
    mvn clean package -DskipTests=true
    cf push
    mvn clean
}

function run_services(){
    cf delete music
#    cf delete-service MongoDbmusic
    cf delete-service MySqlDbmusic
#    cf create-service mLab sandbox MongoDbmusic
    cf create-service cleardb spark MySqlDbmusic
}

function run_angular(){
    cd src/main/resources/music-static/
    ng serve
}

echo "1 for angular build"
echo "2 for run app"
echo "3 for push"
echo "4 for services"
echo "5 for run angular"
read value
if [ $value -eq 1 ]; then
    run_angular_build
elif [ $value -eq 2 ]; then
    run_app
elif [ $value -eq 3 ]; then
    run_push
elif [ $value -eq 4 ]; then
    run_services
elif [ $value -eq 5 ]; then
    run_angular
fi
