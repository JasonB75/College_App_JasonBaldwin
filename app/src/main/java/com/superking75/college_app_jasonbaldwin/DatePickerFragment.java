package com.superking75.college_app_jasonbaldwin;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment
{
    final static String DATE_ARGUMENT = "dateofbirth";
    DatePicker mDatePicker;
    Date mDate;
    Calendar mCalander; 
    @Override
    public AlertDialog onCreateDialog(Bundle budle)
    {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_date_picker, null);
      //  mDate = (Date)getArguments.setSerializable(DATE_ARGUMENT);
      //  mCalander.Calendar.getInstance();
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Date of Birth")
                .setPositiveButton("Done", null)
                .create();
    }

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(DATE_ARGUMENT, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }



}
