package ch.amk.exercise4.mqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// tag::AndroidDagger2Injection[]

import dagger.android.AndroidInjection;

// end::AndroidDagger2Injection[]

// tag::class[]
public class MainActivity extends AppCompatActivity {
    // end::class[]

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
    /** tag::AndroidDagger2InjectionDesc[]
<1> Inject itself, all attributes with `@Inject` will be resolved.
        end::AndroidDagger2InjectionDesc[] */

// tag::class[]
}
// end::class[]
