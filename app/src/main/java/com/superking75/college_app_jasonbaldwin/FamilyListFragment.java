package com.superking75.college_app_jasonbaldwin;
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
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

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
       // mFamily = Family.g
        FamilyMemberAdapter adapter = new FamilyMemberAdapter(mFamily.getFamilyList());
        setListAdapter(adapter);
        setHasOptionsMenu(true);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        ListView listView = (ListView)v.findViewById(android.R.id.list);
        registerForContextMenu(listView);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_family_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FamilyMemberAdapter adapter = (FamilyMemberAdapter)getListAdapter();
        switch (item.getItemId()) {

            case R.id.menu_item_new_guardian:
                Boolean duplicateG =false;
                Log.d(TAG, "Selected add new guardian.");
                Guardian guardian = new Guardian();
                for (FamilyMember f: Family.getFamily().getFamilyList())
                {
                    if (f.equals(guardian)) {
                        Log.i(TAG, "Possible match " + guardian + " and" + f);
                        duplicateG = true;
                    }
                }
                if (!duplicateG)Family.getFamily().addFamilyMember(guardian);

                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_item_new_sibling:
                Boolean duplicateS =false;
                Log.d(TAG, "Selected add new sibling.");
                Sibling sibling = new Sibling();
                for (FamilyMember f: Family.getFamily().getFamilyList())
                {
                    if (f.equals(sibling))
                    {Log.i(TAG, "Found a Match of Sibling");
                    duplicateS = true;}
                }
                if (!duplicateS)Family.getFamily().addFamilyMember(sibling);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        Log.d(TAG, "Creating Context Menu.");
        getActivity().getMenuInflater().inflate(R.menu.family_list_item_context,
                menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        Log.d(TAG, "Context item selected.");
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        FamilyMemberAdapter adapter = (FamilyMemberAdapter) getListAdapter();
        final FamilyMember familyMember = adapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_family_member:
                Family.getFamily().deleteFamilyMember(familyMember);
                adapter.notifyDataSetChanged();
                Backendless.Data.of(FamilyMember.class).remove(familyMember,new
                        AsyncCallback<Long>() {
                            @Override
                            public void handleResponse(Long response) {
                                Log.i(TAG, familyMember.toString() + " deleted");
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Log.e(TAG, fault.getMessage());
                            }
                        });
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        FamilyMemberAdapter adapter = (FamilyMemberAdapter) getListAdapter();
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        FamilyMember f = ((FamilyMemberAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, f.toString() + " was clicked." + FamilyMemberActivity.class);
        Intent i = new Intent(getActivity(), FamilyMemberActivity.class);
        i.putExtra(FamilyMember.EXTRA_RELATION, f.getClass().getName());
        i.putExtra(FamilyMember.EXTRA_INDEX, position);
        startActivity(i);
    }
}
