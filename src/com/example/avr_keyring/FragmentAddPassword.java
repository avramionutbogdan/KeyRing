package com.example.avr_keyring;

import java.io.IOException;
import java.io.OutputStreamWriter;

import classes.MainController;

import com.example.avr_keyring.R;
import com.example.avr_keyring.R.id;
import com.example.avr_keyring.R.layout;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentAddPassword extends Fragment {
	public static String ARG_OBJECT = "object";
	private MainController mc;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	
        return inflater.inflate(R.layout.fragment_add_password, container, false);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mc = MainController.getInstance();
        mc.setApplicationContext(this.getActivity());
      
        //Implement add passwords
        Button btnAddPasswords = (Button) getActivity().findViewById(R.id.btnAddPassword);
        btnAddPasswords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	String alias = ((TextView) getActivity().findViewById(R.id.inputAlias)).getText().toString();
        		String password = ((TextView) getActivity().findViewById(R.id.inputPassword)).getText().toString();
        		
        		if(alias.length()==0 || password.length()==0)
        		{
        			Toast.makeText(getActivity(),"All the fields are mandatory !", Toast.LENGTH_SHORT).show(); 
        		}
        		else
        		{
        			try{
        				mc.addPassword(alias, password);
        				((TextView) getActivity().findViewById(R.id.inputAlias)).setText("");
        				((TextView) getActivity().findViewById(R.id.inputPassword)).setText("");
        				Toast.makeText(getActivity(),"Your password has been saved !", Toast.LENGTH_SHORT).show(); 
        			} catch (Exception e) {
        				Toast.makeText(getActivity(),"Error occurred when saving the password.", Toast.LENGTH_SHORT).show(); 
        			}
        		}
            }
        });
    }
}
