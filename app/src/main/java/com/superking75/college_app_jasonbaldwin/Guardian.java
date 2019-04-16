package com.superking75.college_app_jasonbaldwin;

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
        super();
        mOccupation = "Unknown";
    }

    public Guardian(String firstName, String lastName)
    {
        super(firstName,lastName);
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
    }



