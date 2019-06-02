package com.example.books;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        Spinner authors = findViewById(R.id.authorSpinner);
        EditText year = findViewById(R.id.yearEditor);
        Button ok = findViewById(R.id.okButton);

        Button cancel = findViewById(R.id.cancelButton);
        TextView authorView = findViewById(R.id.authorResultView);
        TextView yearView = findViewById(R.id.yearResultView);

        String[] authorsList = getResources().getStringArray(R.array.authors);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, authorsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        authors.setAdapter(adapter);

        cancel.setVisibility(View.GONE);

        ok.setOnClickListener(view -> {
            String author = authors.getSelectedItem().toString();
            String yearText = year.getText().toString();

            if (TextUtils.isEmpty(yearText)) {
                year.setError("Please, enter some year.");
            } else {
                authorView.setText(String.format("Selected Author: %s", author));
                yearView.setText(String.format("Entered Year: %s", yearText));

                cancel.setVisibility(View.VISIBLE);
            }
        });


        cancel.setOnClickListener(view -> {
            authorView.setText("");
            yearView.setText("");

            cancel.setVisibility(View.GONE);
        });
    }
}
