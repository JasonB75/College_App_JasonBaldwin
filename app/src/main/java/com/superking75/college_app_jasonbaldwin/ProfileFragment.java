package com.superking75.college_app_jasonbaldwin;

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

public class ProfileFragment extends Fragment
{
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
                dialog.show(fm, "DialogDateOfBirth");
            }
        });
        return rootView;
    }



}
