package com.example.pi.addressbookapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.HashMap;

/**
 * Created by pi on 13-Mar-15.
 */
public class AddNewContact extends ActionBarActivity {
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText phoneNumberEditText;
    EditText emaileditText;
    EditText addressEditText;
    DBTools dbtools = new DBTools(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewcontact);
        firstNameEditText = (EditText)findViewById(R.id.firstNameEdittext);
        lastNameEditText = (EditText)findViewById(R.id.lastNameEdittext);
        phoneNumberEditText = (EditText)findViewById(R.id.phonenumberEdittext);
        emaileditText = (EditText)findViewById(R.id.emailEdittext);
        addressEditText =(EditText)findViewById(R.id.addressEdittext);

    }

    public void addNewContact(View view){
        HashMap<String,String> queryValuesMap = new HashMap<String,String>();
        queryValuesMap.put("firstName", firstNameEditText.getText().toString());
        queryValuesMap.put("lastName", lastNameEditText.getText().toString());
        queryValuesMap.put("phone",phoneNumberEditText.getText().toString());
        queryValuesMap.put("email",emaileditText.getText().toString());
        queryValuesMap.put("address",addressEditText.getText().toString());
        dbtools.insertContact(queryValuesMap);
        this.callMainActivity(view);

    }

    public void callMainActivity(View view){
        Intent theintent = new Intent(getApplication(),MainActivity.class);
        startActivity(theintent);
    }

}
