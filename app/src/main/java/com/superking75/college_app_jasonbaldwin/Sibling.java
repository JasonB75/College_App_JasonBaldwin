package com.superking75.college_app_jasonbaldwin;

public class Sibling extends FamilyMember
{
    String mlastName;
    String mFirstName;

    @Override
    public String toString()
    {
        return "Sibling: " + getFirstName() + " " +getLastName();
    }

    public Sibling(String firstName, String lastName)
    {
        mFirstName = firstName;
        mlastName = lastName;
    }
    public Sibling()
    {

    }


    public String getLastName()
    { return mlastName; }
    public void setLastName(String lastName)
    { mlastName = lastName; }

    public String getFirstName()
    { return mFirstName; }
    public void setFirstName(String firstName)
    { mFirstName = firstName; }

    /*public boolean equals(FamilyMember sibling)
    {
        if (sibling.getFirstName().equalsIgnoreCase(mFirstName) && sibling.getLastName().equalsIgnoreCase(mlastName))
        {return true;}
        else
        {return false;}
    }*/
}


