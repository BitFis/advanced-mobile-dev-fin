package ch.amk.exercise4.mqtt;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CompletableFuture;

import ch.amk.exercise4.mqtt.client.MqttModule;
import ch.amk.exercise4.mqtt.client.MqttService;

@RunWith(AndroidJUnit4.class)
public class TestMqttBrokerInstrumentedTest {

    MqttService mqttService;

    @Before
    public void testMqttClientFactory() throws MqttException {
        this.mqttService = new MqttService(
                new MqttModule().procideClientId(),
                new MqttModule().provideBroker(),
                new MqttModule().provideMqttConnectOptions()
        );
        this.mqttService.connect();
    }

    @Test
    public void testPublishMessage() throws MqttException {
        this.mqttService.publish("test/topic", "hello World");
    }

    @Test
    public void testSubscribeFor() throws MqttException {
        mqttService.subscribe("test/topic", (topic, message) -> {

        });
    }

    @Test
    public void testConnectToBroker() {
        String topic        = "test/topic";
        String content      = "Message from MqttPublishSample";
        int qos             = 2;
        String broker       = "tcp://iot.eclipse.org:1883";
        broker = "tcp://192.168.1.35:1883";
        String clientId     = "a:i5u4t3:lzurcher";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);

            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }

}
