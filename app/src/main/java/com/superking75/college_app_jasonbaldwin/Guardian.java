package com.superking75.college_app_jasonbaldwin;

import android.util.Log;

public class Guardian extends FamilyMember {
    String lastName;
    String FirstName;
    String mOccupation;

    public String getOccupation()
    { return mOccupation; }
    public void setOccupation(String occupation)
    { mOccupation = occupation; }
    public String getLastName()
    { return lastName; }
    public void setLastName(String lastName)
    { this.lastName = lastName; }

    public String getFirstName()
    { return FirstName; }
    public void setFirstName(String firstName)
    { FirstName = firstName; }

    public Guardian()
    {
        FirstName = "hey";
        lastName = "boi";
        Log.i("Guardian1212", FirstName);
        mOccupation = "Unknown";
    }

    public Guardian(String firstName, String lastName)
    {

        super(firstName,lastName);
        FirstName = firstName;
        this.lastName = lastName;
        Log.i("Guardian1212", FirstName);
        mOccupation = "Unknown";
    }

    public  Guardian(String firstName, String lastName, String occupation)
    {
        super(firstName,lastName);
        mOccupation = occupation;
    }

    @Override
    public String toString()
    {
        return "Guardian: " + getFirstName() + " " +getLastName() +"\n Occupation: " + getOccupation();
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



