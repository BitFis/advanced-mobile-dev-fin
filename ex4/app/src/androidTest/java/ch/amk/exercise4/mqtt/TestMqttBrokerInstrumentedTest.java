package ch.amk.exercise4.mqtt;

import android.app.Instrumentation;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

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
                new MqttModule().provideClientId(),
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
}
