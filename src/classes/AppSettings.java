package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class AppSettings {
	private static AppSettings instance = null;
	
	//Variables
	private static String masterPassword = null;

    public static AppSettings getInstance() {
        if (instance == null) {
            instance = new AppSettings();
        }
        return instance;
    }
    
    private  AppSettings() {
    	readSettings();
    }
    
    private void readSettings(){
    	//Open the file for reading
    	BufferedReader br = null;
        try {
          br = new BufferedReader(new FileReader("KeyRing_settings.txt"));
          masterPassword = br.readLine();
        }
        catch (IOException e) {
        	masterPassword = "0000";
        }
        finally
    	{    		
			try {
				if ( br != null) br.close();
			} catch (IOException e) {
				Log.e("ERROR", e.getMessage());
			}
    	}
    }
    
    public void saveSettings()
    {
    	BufferedWriter writer = null;
    	try
    	{
    	    writer = new BufferedWriter( new FileWriter("KeyRing_settings.txt"));
    	    writer.write("1234");

    	} catch (IOException e) {
			Log.e("ERROR", e.getMessage());
		}   	
    	finally
    	{    		
			try {
				if ( writer != null) writer.close();
			} catch (IOException e) {
				Log.e("ERROR", e.getMessage());
			}
    	}
    }

    public boolean checkPassword(String password)
    {
    	return masterPassword.equals(password);
    }
}
