package com.example.ex2.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ex2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LabelTextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LabelTextFragment extends Fragment {
    private static final String ARG_LABEL = "label";
    private static final String ARG_CONTENT = "content";

    private String label;
    private String content;

    public LabelTextFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param label The label of the fragment.
     * @param content The actual content.
     * @return A new instance of fragment LabelTextFragment.
     */
    public static LabelTextFragment newInstance(String label, String content) {
        LabelTextFragment fragment = new LabelTextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LABEL, label);
        args.putString(ARG_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.label = getArguments().getString(ARG_LABEL);
            this.content = getArguments().getString(ARG_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_label_text, container, false);
        ((TextView)view.findViewById(R.id.entry_content)).setText(this.content);
        ((TextView)view.findViewById(R.id.entry_label)).setText(this.label);
        return view;
    }
}
