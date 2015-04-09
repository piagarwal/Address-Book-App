package com.example.pi.addressbookapp;

import android.app.ListActivity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;




public class MainActivity extends ListActivity {
    static final String TAG = "fdf";
    Intent intent;
    TextView contactId;
    TextView contactName;
    DBTools dbTools  = new DBTools(this);
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String b = Integer.toString(a);
        Log.d(TAG,"ONCreate");
        Log.d(TAG,b);
        a= 4;
        ArrayList<HashMap<String, String>> contactList = dbTools.getAllContacts();
       /* Log.d(TAG,"yahan hai hum");
        Integer tt = contactList.size();
        String str = Integer.toString(tt);
        Log.d(TAG,str);*/
        if(contactList.size() != 0){
            ListView listView = getListView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    contactId = (TextView)view.findViewById(R.id.contactId);
                   // contactName = (TextView)view.findViewById(R.id.lastName);
                    String contactIdValue = contactId.getText().toString();
                  //  String contactNameValue = contactName.getText().toString();
                    Intent theIntent = new Intent(getApplication(),EditContact.class);
                    theIntent.putExtra("contactId",contactIdValue);
                   // Log.d(TAG,contactIdValue+ "main aaaaaaaaaaaaaaaaaa");
                    startActivity(theIntent);
                }
            });

            ListAdapter listadapter = new SimpleAdapter(MainActivity.this,contactList,R.layout.contact_entry, new String[] {"contactId","lastName","firstName"}, new int[] {R.id.contactId, R.id.lastName,R.id.firstName});

            setListAdapter(listadapter);








        }

    }




    public void showAddContact(View view){
        Intent theIntent = new Intent(getApplication(),AddNewContact.class);
        startActivity(theIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"ONStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"ONRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"ONResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"ONPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"ONDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"ONStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"SavingInstanceState");
    }
}
