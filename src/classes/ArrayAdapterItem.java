package classes;

import com.example.avr_keyring.FragmentShowPasswords;
import com.example.avr_keyring.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ArrayAdapterItem extends ArrayAdapter<ObjectPassword> {


	Context mContext; 
    int layoutResourceId; 
    ObjectPassword data[] = null; 


    public ArrayAdapterItem(Context fragmentShowPasswords, int layoutResourceId, ObjectPassword[] data) { 
        super(fragmentShowPasswords, layoutResourceId, data); 
        this.layoutResourceId = layoutResourceId; 
        this.mContext = fragmentShowPasswords; 
        this.data = data; 
    } 


@Override 
    public View getView(int position, View convertView, ViewGroup parent) { 

        if(convertView==null){ 

            // inflate the layout 
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater(); 
            convertView = inflater.inflate(layoutResourceId, parent, false); 
        } 

        // object item based on the position 
        ObjectPassword objectItem = data[position]; 

        // get the TextView and then set the text (item name) and tag (item ID) values 
        TextView textViewItem1 = (TextView) convertView.findViewById(R.id.textAlias); 
        textViewItem1.setText(objectItem.getAlias() + " - "); 
        textViewItem1.setTag(objectItem.getID()); 
        
        TextView textViewItem2 = (TextView) convertView.findViewById(R.id.textPassword); 
        textViewItem2.setText(objectItem.getPassword()); 
        textViewItem2.setTag(objectItem.getID()); 
        return convertView; 
    } 
}
