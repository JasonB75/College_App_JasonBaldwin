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
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;


public class GuardianFragment extends Fragment
{

    TextView mFirstNameTextView, mLastNameTextView, mOccupationTextView;
    EditText mFirstNameEditText, mLastNameEditText, mOccupationEditText;
    Button mSubmitButton;
    Guardian mGuardian;
    final String TAG = "GUARDIAN_FRAGMENT1212";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_guardian, container, false);
        Log.i(TAG, "In Guardian frag 121");
        mGuardian = new Guardian();


        mFirstNameTextView = (TextView) rootView.findViewById(R.id.guardianfirstNameView);
        mLastNameTextView = (TextView) rootView.findViewById(R.id.guardianlastNameView);
        mOccupationTextView = (TextView) rootView.findViewById(R.id.guardian_ocupation_view);
        mFirstNameEditText = (EditText) rootView.findViewById(R.id.guardianFirstEdit);
        mLastNameEditText = (EditText) rootView.findViewById(R.id.guardianLastEdit);
        mOccupationEditText = (EditText) rootView.findViewById(R.id.guardianoccupationedit);
        mSubmitButton = (Button) rootView.findViewById(R.id.guardianSubmitButton);

        mFirstNameTextView.setText(mGuardian.getFirstName());
        mLastNameTextView.setText(mGuardian.getLastName());
        mOccupationTextView.setText(mGuardian.getOccupation());

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFirstNameEditText.getText().length() > 0){
                    String firstName = mFirstNameEditText.getText().toString();
                    mGuardian.setFirstName(firstName);
                    mFirstNameTextView.setText(firstName);
                    mFirstNameEditText.setText("");
                }

                if(mLastNameEditText.getText().length() > 0){
                    String lastName = mLastNameEditText.getText().toString();
                    mGuardian.setLastName(lastName);
                    mLastNameTextView.setText(lastName);
                    mLastNameEditText.setText("");
                }

                if(mOccupationEditText.getText().length() > 0){
                    String occupation = mOccupationEditText.getText().toString();
                    mGuardian.setOccupation(occupation);
                    mOccupationTextView.setText(occupation);
                    mOccupationEditText.setText("");
                }

                saveToBackendless();
                /*Backendless.Persistence.of(Guardian.class).save(mGuardian, new AsyncCallback<Guardian>() {
                    @Override
                    public void handleResponse(Guardian guardian) {
                        Log.i(TAG, "Saved" + guardian.toString());
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        Log.i(TAG, backendlessFault.toString());
                    }
                });*/

            }
        });

        return rootView;
    }

    @Override
    public void onStart(){
        int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, -1);
        if (index != -1){
            mGuardian = (Guardian)Family.getFamily().getFamilyList().get(index);
            mFirstNameTextView.setText(mGuardian.getFirstName());
            mLastNameTextView.setText(mGuardian.getLastName());
            mOccupationTextView.setText(mGuardian.getOccupation());
        }
        super.onStart();
    }




    private void saveToBackendless(){
        Log.d("save", "SavetoBackendless1212");
        String whereClause = "email = 'jasonbaldwin301@gmail.com'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Guardian.class).find( new AsyncCallback<List<Guardian>>() {
            @Override
            public void handleResponse(List<Guardian> response) {
                if (!response.isEmpty()) {
                    String guardianId = response.get(0).getObjectId();
                    Log.d("Guardian Fragment", "Object ID: " + guardianId);
                    mGuardian.setObjectId(guardianId);
                    Backendless.Data.of(Guardian.class).save(mGuardian, new AsyncCallback<Guardian>() {
                        @Override
                        public void handleResponse(Guardian response) {
                            Log.i("success", response.getFirstName() + " has been saved");
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e("Error", fault.getMessage());
                        }
                    });
                }
                else{
                    Backendless.Data.of(Guardian.class).save(mGuardian, new AsyncCallback<Guardian>() {
                        @Override
                        public void handleResponse(Guardian response) {
                            Log.i("success1212", response.getFirstName() + " has been saved");
                            mGuardian.setObjectId(response.getObjectId());
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e("Error1212", fault.getMessage());
                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("Guardian Fragment", "Failed to find profile: " + fault.getMessage());
            }
        });
    }
}