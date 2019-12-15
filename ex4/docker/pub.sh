#!/bin/bash

topic="$1"
message="$2"

docker run --network ex4_default --init -it --rm efrecon/mqtt-client pub \
        -h mosquitto -t "$topic" -m "$message" -d