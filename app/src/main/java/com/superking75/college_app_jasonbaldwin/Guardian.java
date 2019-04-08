package com.superking75.college_app_jasonbaldwin;

public class Guardian extends FamilyMember {
    String lastName;
    String FirstName;

    public String getLastName()
    { return lastName; }
    public void setLastName(String lastName)
    { this.lastName = lastName; }


    public String getFirstName()
    { return FirstName; }
    public void setFirstName(String firstName)
    { FirstName = firstName; }

    public Guardian()
    {super();}

    public Guardian(String firstName, String lastName)
    {super(firstName,lastName);}


}
