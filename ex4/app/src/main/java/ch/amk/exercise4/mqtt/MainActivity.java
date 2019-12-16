package ch.amk.exercise4.mqtt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

// tag::AndroidDagger2Injection[]

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.inject.Inject;
import javax.inject.Named;

import ch.amk.exercise4.mqtt.client.MqttService;
import dagger.Provides;
import dagger.android.AndroidInjection;

// end::AndroidDagger2Injection[]

// tag::class[]
public class MainActivity extends AppCompatActivity {
    // end::class[]

    private static final String TAG = "MainActivity";

    @Inject
    @Named("mqtt-clientid") String clientId;

    @Inject
    @Named("mqtt-broker") String broker;

    @Inject
    MqttService mqttService;

    // tag::AndroidDagger2Injection[]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this); // <1>
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // end::AndroidDagger2Injection[]

        // tag::AndroidDagger2Injection[]
    }
    // end::AndroidDagger2Injection[]

    private void connectToMqtt() {
        try {
            this.mqttService.connect();
            this.mqttService.subscribe("test/topic", (topic, message) -> {
                this.showPopup("recevied (" + topic + "): " + message);
            });
            this.runOnUiThread(() -> this.showPopup("connected to MQTT"));
        } catch (MqttException e) {
            this.runOnUiThread(() -> this.showPopup("connection to MQTT broker failed!"));
            Log.i(TAG, "connection to mqtt server failed", e);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        new Thread(this::connectToMqtt).start();

        ((Button)this.findViewById(R.id.action_button))
                .setOnClickListener(v -> {
                    String content = ((EditText)this.findViewById(R.id.message_box)).getText().toString();
                    this.showPopup(content);

                    try {
                        this.mqttService.publish("publish/topic", content);
                    } catch (MqttException e) {
                        Log.i(TAG, "publishing failed", e);
                    }
                });
    }

    /** tag::AndroidDagger2InjectionDesc[]
<1> Inject itself, all attributes with `@Inject` will be resolved.
        end::AndroidDagger2InjectionDesc[] */



    public void showPopup(String message) {
        Snackbar
            .make(this.findViewById(R.id.constraintLayout), message, Snackbar.LENGTH_LONG)
            .show();
    }

// tag::class[]
}
// end::class[]
