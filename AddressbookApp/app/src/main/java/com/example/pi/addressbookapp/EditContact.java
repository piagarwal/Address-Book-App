package com.example.pi.addressbookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

/**
 * Created by pi on 13-Mar-15.
 */
public class EditContact extends ActionBarActivity {
    static final String TAG = "fdf";
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText phoneNumberEditText;
    EditText emailEditText;
    EditText addressEditText;
    DBTools dbtools = new DBTools(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editcontactlayout);
        firstNameEditText = (EditText)findViewById(R.id.firstNameEdittext);
        lastNameEditText = (EditText)findViewById(R.id.lastNameEdittext);
        phoneNumberEditText = (EditText)findViewById(R.id.phonenumberEdittext);
        emailEditText = (EditText)findViewById(R.id.emailEdittext);
        addressEditText =(EditText)findViewById(R.id.addressEdittext);
        Intent intent = getIntent();
        String ContactId = intent.getStringExtra("contactId");
        Log.d(TAG,ContactId+"ye hai id");
        HashMap<String, String> contactList = dbtools.getContactInfo(ContactId);

        /*Integer tt = contactList.size();
        String str = Integer.toString(tt);
        Log.d(TAG,str);*/
        if(contactList.size() != 0){
            firstNameEditText.setText(contactList.get("firstName"));
            lastNameEditText.setText(contactList.get("lastName"));
            phoneNumberEditText.setText(contactList.get("phone"));
            emailEditText.setText(contactList.get("email"));
            addressEditText.setText(contactList.get("address"));


        }


    }
     public void editContact(View view){
         HashMap<String, String> contactList = new HashMap<String, String>();
         firstNameEditText = (EditText)findViewById(R.id.firstNameEdittext);
         lastNameEditText = (EditText)findViewById(R.id.lastNameEdittext);
         phoneNumberEditText = (EditText)findViewById(R.id.phonenumberEdittext);
         emailEditText = (EditText)findViewById(R.id.emailEdittext);
         addressEditText =(EditText)findViewById(R.id.addressEdittext);
         Intent intent = getIntent();
         String contactId = intent.getStringExtra("contactId");
         contactList.put("contactId",contactId);
         contactList.put("firstName", firstNameEditText.getText().toString());
         contactList.put("lastName", lastNameEditText.getText().toString());
         contactList.put("phone",phoneNumberEditText.getText().toString());
         contactList.put("email",emailEditText.getText().toString());
         contactList.put("address",addressEditText.getText().toString());
         dbtools.updateContact(contactList);
         this.callMainActivity();



     }

    public void callMainActivity(){
        Intent intent  = new Intent(getApplication(),MainActivity.class);
        startActivity(intent);
    }

    public void deleteContact(View view) {
        HashMap<String, String> contactList = new HashMap<String, String>();
        firstNameEditText = (EditText) findViewById(R.id.firstNameEdittext);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEdittext);
        phoneNumberEditText = (EditText) findViewById(R.id.phonenumberEdittext);
        emailEditText = (EditText) findViewById(R.id.emailEdittext);
        addressEditText = (EditText) findViewById(R.id.addressEdittext);
        Intent intent = getIntent();
        String contactId = intent.getStringExtra("contactId");

        dbtools.deleteContact(contactId);
        this.callMainActivity();
    }
}
