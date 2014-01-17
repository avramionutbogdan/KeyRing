package com.example.avr_keyring;

import java.util.ArrayList;

import classes.ArrayAdapterItem;
import classes.MainController;
import classes.ObjectPassword;
import classes.OnItemClickListenerListViewItem;

import com.example.avr_keyring.R;
import com.example.avr_keyring.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentShowPasswords extends Fragment {

	private MainController mc;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_passwords, container, false);     
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mc = MainController.getInstance();
    }
    
    
    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            
        	mc.readPasswords();
            
        	/*ObjectPassword[] passwordCollection = mc.getPasswords(); 
        	
        	if(passwordCollection.length>0) {
    	    	ArrayAdapterItem adapter = new ArrayAdapterItem(this.getView().getContext(), R.layout.list_view_passwords, passwordCollection);
    	   
    	        // Add adaptor and click listener to listView
    	        ListView listViewItems = (ListView) getActivity().findViewById(R.id.listView1);
    	    	listViewItems.setAdapter(adapter); 
    	    	listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem()); 
         	}*/
        }
    }
}
