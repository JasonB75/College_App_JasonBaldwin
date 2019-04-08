package com.superking75.college_app_jasonbaldwin;

import android.widget.EditText;

public class FamilyMember
{
    String mLastName;
    String mFirstName;
 //   EditText mFirstNameEdit;
   // EditText mLastNameEdit;



    public String getLastName()
    { return mLastName; }
    public void setLastName(String lastName)
    { this.mLastName = lastName; }


    public String getFirstName()
    { return mFirstName; }
    public void setFirstName(String firstName)
    { mFirstName = firstName; }

    public FamilyMember()
    {
        mFirstName = "Hey";
        mLastName = "Baby";

    }

    public FamilyMember(String firstName, String lastName)
    {
        mFirstName = firstName;
        mLastName = lastName;

    }
    public void testMethod(){
        if (mFirstName == "test"){
            mFirstName = "hello world";
        }
    }




}
