package ch.amk.exercise3.api;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikepenz.iconics.IconicsColor;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.IconicsSize;
import com.mikepenz.iconics.typeface.library.materialdesigniconic.MaterialDesignIconic;

import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Consumer;

import javax.inject.Inject;

import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.service.FeedbackService;
import ch.amk.exercise3.api.utils.ExceptionBox;
import dagger.android.AndroidInjection;

/**
 * Activity takes in an Intent with extra defined with key *INTENT_EXTRA_ATTR_FEEDBACK*.
 * Same Intent will be returned, it will contain the changed Feedback or the created Feedback.
 */
public class FeedbackFormActivity extends AppCompatActivity {

    public static String INTENT_EXTRA_ATTR_FEEDBACK = "feedback";

    private Feedback feedback;

    @Inject
    FeedbackService feedbackService;

    class SimpleTextWatcherOnChange implements TextWatcher {

        private Consumer<CharSequence> function;

        public SimpleTextWatcherOnChange(Consumer<CharSequence> function) {
            this.function = function;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            this.function.accept(s);
        }

        @Override
        public void afterTextChanged(Editable s) { }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);

        this.feedback = this.getIntent().getParcelableExtra(INTENT_EXTRA_ATTR_FEEDBACK);

        this.setupButton();
        this.setupForm();
    }

    private void setupButton() {
        FloatingActionButton saveButton = this.findViewById(R.id.button_save);

        saveButton.setImageDrawable(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_save)
            .color(IconicsColor.colorInt(Color.WHITE))
            .size(IconicsSize.dp(24)));

        saveButton.setOnClickListener(v -> {
            this.save();
        });
    }

    private void setupForm() {
        if(this.feedback == null) {
            this.feedback = new Feedback();
        }

        this.setEditTextContent(R.id.textbox_name, this.feedback.getName());
        this.setEditTextContent(R.id.textbox_value, this.feedback.getValue());
        this.setEditTextContent(R.id.textbox_location, this.feedback.getLocation());

        this.setEditTextOnTextChanged(R.id.textbox_name, this.feedback::setName);
        this.setEditTextOnTextChanged(R.id.textbox_value, this.feedback::setValue);
        this.setEditTextOnTextChanged(R.id.textbox_location, this.feedback::setLocation);
    }

    private void save() {
        this.feedbackService.save(this.feedback)
            .thenAccept(feedback -> {
                Intent intent = new Intent();

                intent.putExtra(INTENT_EXTRA_ATTR_FEEDBACK, feedback);

                this.setResult(Activity.RESULT_OK, intent);
                this.finish();
            })
            .exceptionally(throwable -> {
                new ExceptionBox(throwable).show(this);
                throwable.printStackTrace();
                return null;
            });

    }

    private void setEditTextOnTextChanged(int editTextId, Consumer<String> function) {
        ((EditText)this.findViewById(editTextId))
                .addTextChangedListener(new SimpleTextWatcherOnChange(
                        charSequence -> function.accept(charSequence.toString())
                ));
    }

    private void setEditTextContent(int editTextId, String value) {
        ((EditText)this.findViewById(editTextId)).setText(value);
    }

}
