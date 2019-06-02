package com.example.lab3_1;

import android.app.AlertDialog;
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

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;

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

        Button add = findViewById(R.id.addButton);
        Button showdb = findViewById(R.id.showdbButton);

        sqlHelper = new DatabaseHelper(this);


        String[] authorsList = getResources().getStringArray(R.array.authors);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, authorsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        authors.setAdapter(adapter);

        cancel.setVisibility(View.GONE);

        ok.setOnClickListener(view -> {
            String author = authors.getSelectedItem().toString();
            String yearText = year.getText().toString();

            if(TextUtils.isEmpty(yearText)) {
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

        showdb.setOnClickListener(view -> {
            db = sqlHelper.getReadableDatabase();

            userCursor = db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);

            if (userCursor.moveToFirst()) {
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                startActivity(intent);
            } else {
                showEmptyAlert();
            }

            db.close();
        });

        add.setOnClickListener(view -> {
            String yearText = year.getText().toString();
            if(TextUtils.isEmpty(yearText)) {
                year.setError("Please, enter some year.");
            } else {
                db = sqlHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(DatabaseHelper.COLUMN_AUTHOR, authors.getSelectedItem().toString());
                cv.put(DatabaseHelper.COLUMN_YEAR, year.getText().toString());

                if (userId > 0) {
                    db.update(DatabaseHelper.TABLE, cv, DatabaseHelper.COLUMN_ID + "=" + String.valueOf(userId), null);
                } else {
                    db.insert(DatabaseHelper.TABLE, null, cv);
                }

                showAddAlert();

                db.close();
            }


        });


    }

    public void showAddAlert() {
        new AlertDialog.Builder(this).setTitle("Додано")
                .setMessage("Додано в базу")
                .create()
                .show();
    }

    public void showEmptyAlert() {
        new AlertDialog.Builder(this).setTitle("Пусто")
                .setMessage("У базі поки що немає даних")
                .create()
                .show();
    }
}
