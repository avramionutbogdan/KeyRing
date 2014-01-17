package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.example.avr_keyring.LoginActivity;
import com.example.avr_keyring.MainActivity;
import com.example.avr_keyring.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ListView;

public class MainController {
	private static MainController instance = null;
	private static AppSettings settings = null;
	private ArrayList<ObjectPassword> passwordCollection = null;
	private Context appContext;
	private int passCT;

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();      
        }
        return instance;
    }
    
    private  MainController() {
    	settings = AppSettings.getInstance();
    	passCT=0;
    }
    
    public void setApplicationContext(Context appContext){
    	this.appContext = appContext;
    }
    
    public void readPasswords(){
    	if(passwordCollection == null)
    	{
    		passwordCollection = new ArrayList<ObjectPassword>();	
	    	try{
		    	String text = readFromFile();
		    	String[] lines = text.split("<<<");
		    	for(String line:lines){
		    		String ID = line.split(">>>")[0];
		    		String Alias = line.split(">>>")[1];
		    		String Password = line.split(">>>")[2];
		    		
		    		passwordCollection.add(new ObjectPassword(ID,Alias,Password));
		    	}
		    	
		    	passCT = lines.length;	   
	    	}
	    	catch(Exception e) {}
    	}
    	
    	Log.i("Passwords read", passCT+"");
    	updateView();
    }
    
    public void addPassword(String alias, String password){
    	if(passwordCollection == null)
    	{
    		readPasswords();
    	}
    	
    	passwordCollection.add(new ObjectPassword(passCT+"", alias, password));
    	passCT++;
    	
    	Log.i("Passwords now", passCT+"");
    	updateView();
    }
    
   
    public ObjectPassword[] getPasswords()
    {
    	return  passwordCollection.toArray(new ObjectPassword[passwordCollection.size()]);  	
    }
    
    
    public void removePassword(String id){
    	for(ObjectPassword item:passwordCollection)
    	{
    		if(item.getID().equals(id)) {
    			passwordCollection.remove(item);	    	
    	    	updateView();
    			break;
			}
    	}
    }
    
    
    public void updateView()
    {
    	try{
    	ObjectPassword[] passwordCollection = getPasswords(); 
    	
    	//if(passwordCollection.length>0) {
	    	ArrayAdapterItem adapter = new ArrayAdapterItem(appContext, R.layout.list_view_passwords, passwordCollection);
	   
	        // Add adaptor and click listener to listView
	        ListView listViewItems = (ListView) ((Activity) appContext).findViewById(R.id.listView1);
	    	listViewItems.setAdapter(adapter); 
	    	listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem()); 
    	} catch (Exception e) {}
    }
    
    
    public void savePasswords(){
    	String finalString = "";

    	for(ObjectPassword item:passwordCollection)
    	{
    		finalString += item.getID()+">>>"+item.getAlias()+">>>"+item.getPassword()+"<<<";
    	}
    	
    	writeToFile(finalString);
    }
    
    
    public void deletePasswords()
    {
    	passwordCollection = new ArrayList<ObjectPassword>();
        File dir = appContext.getFilesDir();
		File file = new File(dir, "Keyring_Passwords.txt");
		file.delete();
		
		updateView();
    }
    
    
    public void writeToFile(String data) {
	    try {
	        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(appContext.openFileOutput("Keyring_Passwords.txt", Context.MODE_PRIVATE));
	        outputStreamWriter.write(data);
	        outputStreamWriter.close();
	    }
	    catch (IOException e) {
	        Log.e("Exception", "File write failed: " + e.toString());
	    } 
	}
    
	
    private String readFromFile() {

	    String ret = "";

	    try {
	        InputStream inputStream = appContext.openFileInput("Keyring_Passwords.txt");
	     
	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	        }
	    }
	    catch (FileNotFoundException e) {
	        Log.e("login activity", "File not found: " + e.toString());
	    } catch (IOException e) {
	        Log.e("login activity", "Can not read file: " + e.toString());
	    }

    	return ret;
	}
}
