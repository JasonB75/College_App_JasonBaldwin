package com.superking75.college_app_jasonbaldwin;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Date;

public class ProfileFragment extends Fragment
{
    public static final int REQUEST_DATE_OF_BIRTH = 0;
    Button DatePickerButton;
    Profile mProfile;

    Button datePickerButton;
    Profile jack = new Profile();
    TextView mFirstNameText;
    TextView mLastNameText;
    Profile mprofile = new Profile();
    EditText mFirstNameEdit;
    EditText mLastNameEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle)
    {   super.onCreateView(inflater,view,bundle);

        mProfile = new Profile();
        View rootView = inflater.inflate(R.layout.fragment_profile, view, false);

        mFirstNameText = rootView.findViewById(R.id.profilefirstNameView);
        mFirstNameText.setText(jack.getFirstName());
        mLastNameText = rootView.findViewById(R.id.profilelastNameView);
        mLastNameText.setText(jack.getLastName());

        datePickerButton = (Button) rootView.findViewById(R.id.DatePikerButton);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mprofile.dateOfBirth);
                dialog.setTargetFragment(ProfileFragment.this, REQUEST_DATE_OF_BIRTH);
                dialog.show(fm, "DialogDateOfBirth");
            }
        });
        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        Log.i("ProfileFragment", "" + requestCode + " " + resultCode);
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_DATE_OF_BIRTH){
                mProfile.dateOfBirth = (Date)intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE_OF_BIRTH);
                Log.i("ProfileFragment", mProfile.dateOfBirth.toString());
                DatePickerButton.setText(mProfile.dateOfBirth.toString());
            }
        }
    }



}
