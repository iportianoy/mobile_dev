package com.example.lab2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        return view;
    }

    // обновление текстового поля
    public void setText(String item) {
        Button cancel = Objects.requireNonNull(getView()).findViewById(R.id.cancelButton);
        cancel.setVisibility(View.GONE);
        TextView authorView =  getView().findViewById(R.id.authorResultView);
        TextView yearView =  getView().findViewById(R.id.yearResultView);
        String[] splited = item.split(" ");

        authorView.setText(String.format("Selected Author: %s", splited[0]));
        yearView.setText(String.format("Entered Year: %s", splited[1]));
        cancel.setVisibility(View.VISIBLE);

        cancel.setOnClickListener(view -> {
            authorView.setText("");
            yearView.setText("");

            cancel.setVisibility(View.GONE);
        });
    }
}