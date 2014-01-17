package classes;

import com.example.avr_keyring.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class OnItemClickListenerListViewItem implements OnItemClickListener {
	 String textID, textAlias, textPass;
	 Context context;
	 
	@Override 
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) { 

        context = view.getContext(); 
        
        textID = ((TextView) view.findViewById(R.id.textAlias)).getTag().toString(); 
        textAlias = ((TextView) view.findViewById(R.id.textAlias)).getText().toString(); 
        textPass = ((TextView) view.findViewById(R.id.textPassword)).getText().toString(); 
        
        
        // Toast.makeText(context,"ID: " + textID + " Alias: " + textAlias + " Password: " + textPass, Toast.LENGTH_SHORT).show(); 
    
        //Delete password
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
   	 	
   	 	builder1.setTitle("Warning !");
        builder1.setMessage("Are you sure you want to remove \"" + textAlias.substring(0,(textAlias.length()- 3)) + "\" password ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int id) {
        		MainController.getInstance().removePassword(textID);
        		Toast.makeText(context,"\"" + textAlias.substring(0,(textAlias.length()- 3)) + "\" password have been removed", Toast.LENGTH_SHORT).show(); 
        		dialog.cancel();
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
}
