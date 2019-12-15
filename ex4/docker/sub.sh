#!/bin/bash

# set to '#' for all topics
topic="$1"
echo $topic

docker run --network ex4_default --init -it --rm efrecon/mqtt-client sub \
        -h mosquitto -t "$topic" -d