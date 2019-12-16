package ch.amk.exercise4.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MqttModule {
    @Provides
    @Named("mqtt-broker")
    public String provideBroker() { return "tcp://192.168.1.35:1883"; }

    @Provides
    @Named("mqtt-clientid")
    public String provideClientId() { return "a:i5u4t3:lzurcher"; }

    @Provides
    public MqttConnectOptions provideMqttConnectOptions() {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setConnectionTimeout(5);
        return connOpts;
    }
}
