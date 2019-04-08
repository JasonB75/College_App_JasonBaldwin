package com.superking75.college_app_jasonbaldwin;

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
        getFamily().getFamilyList().add(test);
        getFamily().getFamilyList().add(test2);
    }

    public static Family getFamily()
    {
        if (sFamily == null)
        {sFamily = new Family();}
        return sFamily;
    }

    public ArrayList<FamilyMember> getFamilyList()
    {
        return family;
    }

    public void setFamily(ArrayList<FamilyMember> family) {
        this.family = family;
    }


}
