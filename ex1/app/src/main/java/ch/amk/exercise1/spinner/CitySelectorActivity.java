package ch.amk.exercise1.spinner;

import androidx.appcompat.app.AppCompatActivity;
import ch.amk.exercise1.R;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CitySelectorActivity extends AppCompatActivity {

    private static final String WHEATER_API_KEY = "01f4ce4de22786346799bfec88c18db8";

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_city_selector);

        this.spinner = (Spinner) this.findViewById(R.id.city_spinner);

        this.initSpinner(this.spinner);
    }

    private void initSpinner(Spinner spinner) {
        List<String> list = new ArrayList<String>();
        list.add("London, uk");
        list.add("list 2");
        list.add("list 3");
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                list);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
