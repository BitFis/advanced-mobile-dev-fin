# MQTT in Android

This project solves the task of connecting to a MQTT-Broker. To be exact, connecting to the
[weather station broker in the IBM cloud](ibmcloud.com).

## Setup environment

To run the instrumented tests successfully, [docker](https://docs.docker.com/) needs to be installed.

## testing MQTT broker

After starting the broker with `docker-compose up`, test it as following

```bash
# run sub docker to subscribe to topic
docker/sub.sh "test/topic"

# publish to the broker
docker/pub.sh "test/topic" "Hello World"
```


