package com.example.avr_keyring;

import classes.MainController;
import android.R;
import android.R.id;
import android.R.layout;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class DialogLogin extends DialogFragment {
    
	MainController mc; 
	EditText mTextView;
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(com.example.avr_keyring.R.layout.dialog_login, null));    

        
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
         	 

           	}
        });
        
    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int id) {   	
        	   System.exit(0);
           	}
        });
        
    	// Create the AlertDialog object and return it
        return builder.create();
    }
}