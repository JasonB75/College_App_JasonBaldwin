package com.superking75.college_app_jasonbaldwin;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.backendless.Backendless;
//import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

public class FamilyListFragment extends ListFragment
{ private final String TAG = FamilyListFragment.class.getName();
    Family mFamily;

    public FamilyListFragment()
    { mFamily = Family.getFamily(); }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.family_member_title);
        FamilyMemberAdapter adapter = new FamilyMemberAdapter(mFamily.getFamilyList());
       // mFamily = Family.getFamily();

       // final FamilyMemberAdapter adapter = new FamilyMemberAdapter(mFamily.getFamilyList());
        setListAdapter(adapter);
       // hasOptionsMenu(true);
    }

  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        ListView listView = (ListView)v.findViewById(R.id.family_member_list_item_nameTextView);
        registerForContextMenu(listView);
        return v;
    }*/

    //@Override
   // public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
   // {
   ////     super.onCreateOptionsMenu(menu,inflater);
  //      inflater.inflate(R.menu.activity_main_drawer, menu);
   // }






    private class FamilyMemberAdapter extends ArrayAdapter<FamilyMember> {
        public FamilyMemberAdapter(ArrayList<FamilyMember> family) {
            super(getActivity(), 0, family);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_family_member, null);
            }

            FamilyMember f = getItem(position);

            TextView nameTextView =
                    (TextView)convertView
                            .findViewById(R.id.family_member_list_item_nameTextView);
            nameTextView.setText(f.getFirstName() + " " + f.getLastName());

            return convertView;
        }
    }
}

