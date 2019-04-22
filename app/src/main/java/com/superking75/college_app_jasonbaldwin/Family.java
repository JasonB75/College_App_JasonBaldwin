package com.superking75.college_app_jasonbaldwin;

import android.util.Log;

import java.util.ArrayList;

public class Family
{
    private final String TAG = Family.class.getName();
    private ArrayList<FamilyMember> family;

    private static Family sFamily;

    private Family()
    {
        Guardian test = new Guardian("jace", "boi");
        Guardian test2 = new Guardian();
        family = new ArrayList<>();
        Guardian mom = new Guardian("Yvette", "b");
        Guardian dad = new Guardian("Yvette", "b");
        Sibling ryan = new Sibling("rice", "boi");
        //Log.i("Guard1212", mom.getFirstName());
        family.add(mom);
        family.add(dad);
        getFamilyList().add(test);
        getFamilyList().add(ryan);
       getFamilyList().add(test2);
    }

    public static Family getFamily()
    {
        if (sFamily == null)
        {sFamily = new Family();
            Log.i("121Family", "newFamily");}
        return sFamily;
    }

    public ArrayList<FamilyMember> getFamilyList()
    {
        return family;
    }

    public void setFamily(ArrayList<FamilyMember> family) {
        this.family = family;
    }

    public void deleteFamilyMember(FamilyMember familyMember)
    {
        family.remove(familyMember);
    }

    public void addFamilyMember(FamilyMember familyMember)
    {
        family.add(familyMember);
    }

}
