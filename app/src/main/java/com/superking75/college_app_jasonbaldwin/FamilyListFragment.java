package com.superking75.college_app_jasonbaldwin;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FamilyListFragment extends ListFragment {
    private final String TAG = FamilyListFragment.class.getName();

    Family mFamily;

    public FamilyListFragment(){
        mFamily = Family.getFamily();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.family_member_title);
        FamilyMemberAdapter adapter = new FamilyMemberAdapter(mFamily.getFamilyList());
        setListAdapter(adapter);
    }


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
            Log.d(TAG, "The type of FamilyMember at position " + position + " is " + f.getClass().getName());

            TextView nameTextView =
                    (TextView)convertView
                            .findViewById(R.id.family_member_list_item_nameTextView);
            nameTextView.setText(f.toString());

            return convertView;
        }
    }
}

