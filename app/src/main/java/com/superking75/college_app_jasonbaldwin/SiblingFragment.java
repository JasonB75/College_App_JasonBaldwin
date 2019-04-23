package com.superking75.college_app_jasonbaldwin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
//import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;


public class SiblingFragment extends Fragment
{

        TextView mFirstNameTextView, mLastNameTextView, mOccupationTextView;
        EditText mFirstNameEditText, mLastNameEditText;
        Button mSubmitButton;
        Sibling mSibling;
        final String TAG = "SIBLING FRAGMENT1212";


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_sibling, container, false);
                Log.i(TAG, "In Sibling frag 121");
                mSibling = new Sibling("Tice", "Da Boi");

                mFirstNameTextView = (TextView) rootView.findViewById(R.id.siblingfirstNameView);
                mLastNameTextView = (TextView) rootView.findViewById(R.id.siblinglastNameView);
                mFirstNameEditText = (EditText) rootView.findViewById(R.id.siblingFirstEdit);
                mLastNameEditText = (EditText) rootView.findViewById(R.id.siblingLastEdit);
                mSubmitButton = (Button) rootView.findViewById(R.id.siblingSubmitButton);

                mFirstNameTextView.setText(mSibling.getFirstName());
                mLastNameTextView.setText(mSibling.getLastName());


                mSubmitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                if (mFirstNameEditText.getText().length() > 0){
                                        String firstName = mFirstNameEditText.getText().toString();
                                        mSibling.setFirstName(firstName);
                                        mFirstNameTextView.setText(firstName);
                                        mFirstNameEditText.setText("");
                                }

                                if(mLastNameEditText.getText().length() > 0){
                                        String lastName = mLastNameEditText.getText().toString();
                                        mSibling.setLastName(lastName);
                                        mLastNameTextView.setText(lastName);
                                        mLastNameEditText.setText("");
                                }

                                Backendless.Persistence.save(mSibling, new AsyncCallback<Sibling>() {
                                        @Override
                                        public void handleResponse(Sibling sibling) {
                                                Log.i(TAG, "Saved" + sibling.toString());
                                        }

                                        @Override
                                        public void handleFault(BackendlessFault backendlessFault) {
                                                Log.i(TAG, backendlessFault.toString());
                                        }
                                });

                        }
                });

                return rootView;
        }

        @Override
        public void onStart(){
                int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, -1);
                if (index != -1){
                        mSibling = (Sibling) Family.getFamily().getFamilyList().get(index);
                        mFirstNameTextView.setText(mSibling.getFirstName());
                        mLastNameTextView.setText(mSibling.getLastName());

                }
                super.onStart();
        }
}