package com.superking75.college_app_jasonbaldwin;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    final static String DATE_ARGUMENT ="dateofbirth";
    final static String EXTRA_DATE_OF_BIRTH = "com.jasonb75.college_app_Jasonbaldwin.DatePickerFragment";
    DatePicker mDatePicker;
    Date mDate;
    Calendar mCalendar;

    @Override
    public AlertDialog onCreateDialog(Bundle bundle){
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_date_picker, null);
        mDate = (Date)getArguments().getSerializable(DATE_ARGUMENT);
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);
        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_of_birth);
        mDatePicker.init(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH), null);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Date of Birth")
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Date date = new GregorianCalendar(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth()).getTime();

                        sendResult(Activity.RESULT_OK, date);
                    }
                })
                .create();
    }

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(DATE_ARGUMENT, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void sendResult(int resultCode, Date date){
        if (getTargetFragment() != null){
            Intent intent = new Intent();
            intent.putExtra(EXTRA_DATE_OF_BIRTH, date);
            Log.i("DatePickerFragment", date.toString());
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }

}