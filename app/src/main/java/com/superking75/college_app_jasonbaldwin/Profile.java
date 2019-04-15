package com.superking75.college_app_jasonbaldwin;

import java.util.Date;

public class Profile {
    String mFirstName;
    String mLastName;
    Date dateOfBirth;
    String objectId;
    String email;



    public String getEmail() {
        return email;
    }  

    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getObjectId() {
        return objectId;
    }
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


    public String getLastName()
    {
        return mLastName = mLastName;
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

    public Profile()
    {
        mFirstName = "Alan";
        mLastName = "Balwin";
        dateOfBirth = new Date();
    }


//cgo


}
