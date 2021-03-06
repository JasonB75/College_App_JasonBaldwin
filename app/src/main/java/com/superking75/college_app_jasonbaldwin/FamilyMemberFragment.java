package com.superking75.college_app_jasonbaldwin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FamilyMemberFragment extends Fragment
{
    FamilyMember jack = new FamilyMember();
    TextView mFirstNameText;
    TextView mLastNameText;
    private Button mSubmitButton;

    EditText mFirstNameEdit;
    EditText mLastNameEdit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle)
    {   super.onCreateView(inflater,view,bundle);


    View rootView = inflater.inflate(R.layout.fragment_family_member, view, false);
    mFirstNameText = rootView.findViewById(R.id.familyfirstNameView);
    mFirstNameText.setText(jack.getFirstName());
    mLastNameText = rootView.findViewById(R.id.familylastNameView);
    mLastNameText.setText(jack.getLastName());

    mSubmitButton = (Button) rootView.findViewById(R.id.familySubmitButton);
    mFirstNameEdit = rootView.findViewById(R.id.familyFirstEdit);
    mLastNameEdit = rootView.findViewById(R.id.familyLastEdit);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            jack.setFirstName(mFirstNameEdit.getText().toString());
            jack.setLastName(mLastNameEdit.getText().toString());
            mFirstNameText.setText(jack.getFirstName());
            mLastNameText.setText(jack.getLastName());

            }
        });



    return rootView;
    }



}
