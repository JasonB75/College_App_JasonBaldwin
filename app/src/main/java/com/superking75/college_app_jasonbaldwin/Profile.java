package com.superking75.college_app_jasonbaldwin;

import java.util.Date;

public class Profile {
    String mFirstName;
    String mLastName;
    Date dateOfBirth;

    public Profile()
    {
        mFirstName = "Alan";
        mLastName = "Balwin";
        dateOfBirth = new Date();
    }
    public String getLastName()
    {
        return mLastName;
    }
    public void setLastName(String LastName)
    {
        this.mLastName = LastName;
    }
    public String getFirstName()
    {
        return mFirstName;
    }
    public void setFirstName(String firstName)
    {
        mFirstName = firstName;
    }





}
