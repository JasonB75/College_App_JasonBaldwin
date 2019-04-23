package com.superking75.college_app_jasonbaldwin;

import android.widget.EditText;

public class FamilyMember
{
    String mLastName;
    String mFirstName;
    static final String  EXTRA_RELATION = "org.pltw.examples.collegeapp.relation";
    static final String EXTRA_INDEX = "org.pltw.examples.collegeapp.index";
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

    public boolean equals(Object o) {
        if (o.getClass() == Guardian.class)
        {
            Guardian g = (Guardian) o;
            if (this.getFirstName().equalsIgnoreCase(g.getFirstName()) && this.getLastName().equalsIgnoreCase(g.getLastName()))
            {
                return true;
            }
        }
        else if (o.getClass() == Sibling.class)
        {
            Sibling s = (Sibling) o;
            if (this.getFirstName().equalsIgnoreCase(s.getFirstName()) && this.getLastName().equalsIgnoreCase(s.getLastName()))
            {
                return true;
            }
        }

        return false;



    }


}
