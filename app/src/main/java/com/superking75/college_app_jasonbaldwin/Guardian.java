package com.superking75.college_app_jasonbaldwin;

import android.util.Log;

public class Guardian extends FamilyMember {
    String lastName;
    String FirstName;
    String mOccupation;
    String mEmail;
    String mObjectId;
    final String TAG = "GUARDIAN1212";

    public String getOccupation()
    { return mOccupation; }
    public void setOccupation(String occupation)
    { mOccupation = occupation; }
    public String getLastName()
    { return lastName; }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
        Log.i(TAG, "Set first name: " + lastName);
    }
    public String getEmail() {
        return mEmail;
    }
    public String getFirstName()
    { return FirstName; }
    public void setFirstName(String firstName)
    {
        FirstName = firstName;
        Log.i(TAG, "Set first name: " + firstName);
    }

    public Guardian()
    {
        FirstName = "hey";
        lastName = "boi";
        Log.i("Guardian1212", FirstName);
        mOccupation = "Unknown";
        mEmail = "jasonbaldwin301@gmail.com";
    }

    public Guardian(String firstName, String lastName)
    {


        FirstName = firstName;
        this.lastName = lastName;
        mOccupation = "Unknown";
        Log.i("Guardian1212", FirstName);
        mEmail = "jasonbaldwin301@gmail.com";

    }

    public  Guardian(String firstName, String lastName, String occupation)
    {
        FirstName = firstName;
        this.lastName = lastName;
        mOccupation = occupation;
        mEmail = "jasonbaldwin301@gmail.com";
    }

    @Override
    public String toString()
    {
        return "Guardian: " + getFirstName() + " " +getLastName() +"\n Occupation: " + getOccupation();
    }

    public String getObjectId() {
        return mObjectId;
    }
    public void setObjectId(String objectId) {
        mObjectId = objectId;
    }

    //@Override
    /*public boolean equals(FamilyMember guardian)
    {
        if (guardian.getFirstName().equalsIgnoreCase(FirstName) && guardian.getLastName().equalsIgnoreCase(lastName))
        {return true;}
        else
        {return false;}
    }*/
}



