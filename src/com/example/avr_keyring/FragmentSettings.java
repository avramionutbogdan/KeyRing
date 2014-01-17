package com.example.avr_keyring;

import classes.MainController;

import com.example.avr_keyring.R;
import com.example.avr_keyring.R.layout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentSettings extends Fragment {
	private MainController mc;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        mc = MainController.getInstance();

        //Implement delete passwords
        Button btnDeletePasswords = (Button) getActivity().findViewById(R.id.buttonDeletePasswords);
        btnDeletePasswords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
           	 	
           	 	builder1.setTitle("Warning !");
                builder1.setMessage("Are you sure you want to remove all your passwords ?");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                	public void onClick(DialogInterface dialog, int id) {
                		mc.deletePasswords();
                		dialog.cancel();
                		
                		Toast.makeText(getActivity(),"All your passwords have been removed", Toast.LENGTH_SHORT).show(); 
                	}
                });
                builder1.setNegativeButton("No",new DialogInterface.OnClickListener() {
                	public void onClick(DialogInterface dialog, int id) {
                		dialog.cancel();
                	}
                });
        	
                AlertDialog alert11 = builder1.create();
                alert11.show();   
            }
        });    
    }
}
