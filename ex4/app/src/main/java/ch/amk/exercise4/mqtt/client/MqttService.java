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

    private MqttClient mqttClient;

    private MqttConnectOptions mqttConnectOptions;

    private String broker;
    private String clientId;

    @Inject
    public MqttService(
            @Named("mqtt-clientid") String clientId,
            @Named("mqtt-broker") String broker,
            MqttConnectOptions mqttConnectOptions
    ) {
        this.mqttConnectOptions = mqttConnectOptions;
        this.clientId = clientId;
        this.broker = broker;
    }

    public void connect() throws MqttException {
        this.mqttClient = new MqttClient(broker, clientId, new MemoryPersistence());
        this.mqttClient.connect(this.mqttConnectOptions);
    }

    private void checkConnected() throws MqttException {
        if(this.mqttClient == null) throw new MqttException(MqttException.REASON_CODE_CLIENT_CONNECTED);
    }

    public void subscribe(String topicfilter, IMqttMessageListener listener) throws MqttException {
        this.checkConnected();

        this.mqttClient.subscribe(topicfilter, listener);
    }

    public void publish(String topic, String message) throws MqttException {
        this.checkConnected();

        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(message.getBytes());

        this.mqttClient.publish(topic, mqttMessage);
    }

    public void disconnect() throws MqttException {
        this.mqttClient.disconnect();
    }

}
