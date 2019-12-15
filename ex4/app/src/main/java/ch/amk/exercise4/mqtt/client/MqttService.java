package ch.amk.exercise4.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.inject.Inject;
import javax.inject.Named;

public class MqttService {

    MqttClient mqttClient;

    MqttConnectOptions mqttConnectOptions;

    @Inject
    public MqttService(
            @Named("mqtt-clientid") String clientId,
            @Named("mqtt-broker") String broker,
            MqttConnectOptions mqttConnectOptions
    ) {
        this.mqttConnectOptions = mqttConnectOptions;
        try {
            this.mqttClient = new MqttClient(broker, clientId, new MemoryPersistence());
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void connect() throws MqttException {
        this.mqttClient.connect(this.mqttConnectOptions);
    }

    public void subscribe(String topicfilter, IMqttMessageListener listener) throws MqttException {
        this.mqttClient.subscribe(topicfilter, listener);
    }

    public void publish(String topic, String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(message.getBytes());

        this.mqttClient.publish(topic, mqttMessage);
    }

    public void disconnect() throws MqttException {
        this.mqttClient.disconnect();
    }

}
