package com.example.lab2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

public class ListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);

        final Spinner authors = view.findViewById(R.id.authorSpinner);
        final EditText year = view.findViewById(R.id.yearEditor);
        Button ok = view.findViewById(R.id.okButton);

        String[] authorsList = getResources().getStringArray(R.array.authors);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, authorsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        authors.setAdapter(adapter);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearText = year.getText().toString();
                if (TextUtils.isEmpty(yearText)) {
                    year.setError("Please, enter some year.");
                } else {
                    updateDetail(authors, year);
                }

            }
        });
        return view;
    }

    interface OnFragmentInteractionListener {

        void onFragmentInteraction(String link);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
    public void updateDetail(Spinner authors, EditText year) {
        String author = authors.getSelectedItem().toString();
        String yearText = year.getText().toString();
        String link = author + ' ' + yearText;
        // Посылаем данные Activity
        mListener.onFragmentInteraction(link);
    }
}