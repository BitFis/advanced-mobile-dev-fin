package ch.amk.exercise1;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextEntryPair extends Fragment {

    private TextEntryPairViewModel mViewModel;

    private String label;
    private String content;

    public static TextEntryPair newInstance(String label, String content) {
        TextEntryPair entry = new TextEntryPair();
        entry.label = label;
        entry.content = content;
        return entry;
    }

    protected TextEntryPair() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_entry_pair_fragment, container, false);
        ((TextView)view.findViewById(R.id.entry_label)).setText(this.label);
        ((TextView)view.findViewById(R.id.entry_content)).setText(this.content);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(TextEntryPairViewModel.class);
        // TODO: Use the ViewModel
    }

}
