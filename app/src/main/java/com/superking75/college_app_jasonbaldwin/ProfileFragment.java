package com.superking75.college_app_jasonbaldwin;
import android.content.SharedPreferences;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;


import android.widget.EditText;

import java.io.File;
import java.util.BitSet;
import java.util.List;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.DataQueryBuilder;

import java.util.Date;
import java.util.List;

import static com.backendless.media.SessionBuilder.TAG;

public class ProfileFragment extends Fragment {

    public static final int REQUEST_DATE_OF_BIRTH = 0;
    Button DatePickerButton;
    Profile mProfile;
    TextView mFirstNameText;
    TextView mLastNameText;
    private Button mSubmitButton;

    EditText mFirstNameEdit;
    EditText mLastNameEdit;
    private ImageButton mSelfieButton;
    private ImageView mSelfieView;
    private File mSelfieFile;
    private final int REQUEST_SELFIE = 1;




//Starts and inflates the profile fragment. It then intilizes all buttons and objects needed before waiting for buttons to be pressed.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle){
        super.onCreateView(inflater, view, bundle);

        mProfile = new Profile();

        String whereClause = "email = 'jasonbaldwin301@gmail.com'";
        //Retrieve from Backendless
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Profile.class).find( new AsyncCallback<List<Profile>>() {
            @Override
            public void handleResponse(List<Profile> response) {
                if (!response.isEmpty()) {
                    mProfile = response.get(0);
                    setmProfile(mProfile, response, 0);
                    mFirstNameText.setText(mProfile.getFirstName());
                    mLastNameText.setText(mProfile.getLastName());
                    Log.i(TAG, "Pulled info from backendless");
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("Profile Fragment", "Failed to find profile: " + fault.getMessage());
            }
        });


        //New code
        View rootView = inflater.inflate(R.layout.fragment_profile, view, false);

        DatePickerButton =
                (Button)rootView.findViewById(R.id.DatePickerButton);

        DatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mProfile.dateOfBirth);
                dialog.setTargetFragment(ProfileFragment.this, REQUEST_DATE_OF_BIRTH);
                dialog.show(fm, "DialogDateOfBirth");

            }
        });
        mFirstNameText = rootView.findViewById(R.id.profilefirstNameView);
        mFirstNameText.setText(mProfile.getFirstName());
        mLastNameText = rootView.findViewById(R.id.profilelastNameView);
        mLastNameText.setText(mProfile.getLastName());

        mSubmitButton = (Button) rootView.findViewById(R.id.profileSubmitButton);
        mFirstNameEdit = rootView.findViewById(R.id.profileFirstEdit);
        mLastNameEdit = rootView.findViewById(R.id.profileLastEdit);

        mSelfieButton = (ImageButton) rootView.findViewById(R.id.profile_camera);
        mSelfieView = (ImageView) rootView.findViewById(R.id.profile_pic);
        mSelfieFile = mProfile.getPhotoFile(getActivity());

        final Intent captureSelfie = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureSelfie.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        boolean canTakeSelfie = mSelfieFile != null &&
                captureSelfie.resolveActivity(getActivity().getPackageManager()) != null;

        mSelfieButton.setEnabled(canTakeSelfie);
        if (canTakeSelfie) {
            Uri uri = Uri.fromFile(mSelfieFile);
            captureSelfie.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        }
        mSelfieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureSelfie, REQUEST_SELFIE);

            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFirstNameEdit.getText().toString()!=null){
                    mProfile.setFirstName(mFirstNameEdit.getText().toString());
                    mFirstNameText.setText(mFirstNameEdit.getText().toString());
                }
                if (mLastNameEdit.getText().toString()!=null){
                    mProfile.setLastName(mLastNameEdit.getText().toString());
                    mLastNameText.setText(mLastNameEdit.getText().toString());
                }
                saveToBackendless();
            }
        });
        DatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mProfile.dateOfBirth);
                dialog.setTargetFragment(ProfileFragment.this, REQUEST_DATE_OF_BIRTH);
                dialog.show(fm, "DialogDateOfBirth");

            }
        });
        updateSelfieView();
        return rootView;
    }


//Auto saves to backendless when paused
    @Override
    public void onPause()
    {
        super.onPause();
        saveToBackendless();

    }

    //Auto loads backendless data on start
    @Override
    public void onStart()
    {Log.i(TAG, "onstart121");
        super.onStart();

        String whereClause = "email = 'jasonbaldwin301@gmail.com'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Profile.class).find( new AsyncCallback<List<Profile>>() {
             @Override
            public void handleResponse(List<Profile> profile)
             {
                 if(!profile.isEmpty())
                 {
                     String profileId = profile.get(0).getObjectId();
                     Log.d(TAG, "Object ID: " + profileId);
                     mProfile.setObjectId(profileId);
                     mProfile = profile.get(0);
                     Log.d(TAG, "On start, data retreaved 121");

                     setmProfile(mProfile, profile, 0);
                     mFirstNameText.setText(mProfile.getFirstName());
                     mLastNameText.setText(mProfile.getLastName());
                 }
             }
            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("Error", fault.getMessage());
            }
        });

        }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        Log.i("ProfileFragment", "" + requestCode + " " + resultCode);
        if (resultCode == Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_DATE_OF_BIRTH){
            mProfile.dateOfBirth = (Date)intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE_OF_BIRTH);
            Log.i("ProfileFragment", mProfile.dateOfBirth.toString());
            DatePickerButton.setText(mProfile.dateOfBirth.toString());
            saveToBackendless();
        }
        if(requestCode == REQUEST_SELFIE){
            updateSelfieView();
        }
    }




    //convenience method for saving to Backendless
    private void saveToBackendless(){
        Log.d("save", "SavetoBackendless121");
        String whereClause = "email = 'jasonbaldwin301@gmail.com'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Profile.class).find( new AsyncCallback<List<Profile>>() {
            @Override
            public void handleResponse(List<Profile> response) {
                if (!response.isEmpty()) {
                    String profileId = response.get(0).getObjectId();
                    Log.d("Profile Fragment", "Object ID: " + profileId);
                    mProfile.setObjectId(profileId);
                    Backendless.Data.of(Profile.class).save(mProfile, new AsyncCallback<Profile>() {
                        @Override
                        public void handleResponse(Profile response) {
                            Log.i("success", response.getFirstName() + " has been saved");
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e("Error", fault.getMessage());
                        }
                    });
                }
                else{
                    Backendless.Data.of(Profile.class).save(mProfile, new AsyncCallback<Profile>() {
                        @Override
                        public void handleResponse(Profile response) {
                            Log.i("success", response.getFirstName() + " has been saved");
                            mProfile.objectId = response.objectId;
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e("Error", fault.getMessage());
                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("Profile Fragment", "Failed to find profile: " + fault.getMessage());
            }
        });
    }

    public void setmProfile(Profile mProfile, List<Profile> response, int dataIndex)
    { Log.i("set", "Set data from backendless 121");
        mProfile.setFirstName(response.get(dataIndex).getFirstName());
        Log.i("121", mProfile.getFirstName());
        mProfile.setLastName(response.get(dataIndex).getLastName());
        Log.i("121", response.get(dataIndex).getLastName());
        mProfile.setDateOfBirth(response.get(dataIndex).getDateOfBirth());
        mProfile.setObjectId(response.get(dataIndex).getObjectId());

    }

    public void updateSelfieView()
    {
        if (mSelfieFile!=null && mSelfieFile.exists())
        {
            Bitmap bitmap = BitmapFactory.decodeFile(mSelfieFile.getPath());
            mSelfieView.setImageBitmap(bitmap);
        }

    }




}