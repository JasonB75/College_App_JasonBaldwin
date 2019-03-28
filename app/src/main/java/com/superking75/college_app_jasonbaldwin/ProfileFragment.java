package com.superking75.college_app_jasonbaldwin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle)
    {   super.onCreateView(inflater,view,bundle);


        View rootView = inflater.inflate(R.layout.fragment_profile, view, false);
        return rootView;
    }



}
