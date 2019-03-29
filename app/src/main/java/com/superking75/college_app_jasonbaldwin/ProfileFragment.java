package com.superking75.college_app_jasonbaldwin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileFragment extends Fragment
{
    Profile jack = new Profile();
    TextView mFirstNameText;
    TextView mLastNameText;

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
        return rootView;
    }



}
