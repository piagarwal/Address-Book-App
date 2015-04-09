package com.example.pi.addressbookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pi on 12-Mar-15.
 */
public class DBTools extends SQLiteOpenHelper {
    static final String TAG = "dsf";
    public DBTools(Context applicationContext){ // context provide tool of application, classes, resources
        super(applicationContext,"contactbook4.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table contacts (contact_id integer primary key autoincrement, first_name text,last_name text, phone text, email text, address text)";
        db.execSQL(query);
        Log.d("TAG","Oncreatetable");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exist contacts";
        db.execSQL(query);
        onCreate(db);
    }

    public void insertContact(HashMap<String, String> queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // String SQLquery = "insert into contacts(contact_id, first_name, last_name, phone, email, address) values( 2 ,'" + queryValues.get("firstName") + "','" + queryValues.get("lastName") + "'," + queryValues.get("phone")+ ",'" + queryValues.get("email") + "','" + queryValues.get("address") + "')";
        values.put("first_name",queryValues.get("firstName"));
        values.put("last_name",queryValues.get("lastName"));
        values.put("phone",queryValues.get("phone"));
        values.put("email",queryValues.get("email"));
        values.put("address",queryValues.get("address"));

        database.insert("contacts",null,values);
       // database.execSQL(SQLquery);
        database.close();

    }

    public int updateContact(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("first_name", queryValues.get("firstName"));
        values.put("last_name", queryValues.get("lastName"));
        values.put("phone", queryValues.get("phone"));
        values.put("email", queryValues.get("email"));
        values.put("address", queryValues.get("address"));

        return database.update("contacts", values, "contact_id" + " =? ", new String[]{queryValues.get("contactId")});

    }

    public void deleteContact(String Id){
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "Delete from contacts where contact_id ='" + Id + "'";
        database.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String,String>> getAllContacts(){
        ArrayList<HashMap<String,String>> contactArrayList = new ArrayList<HashMap<String,String>>();
        String selectQuery = "select * from contacts order by last_name";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> contactMap = new HashMap<String,String>();
                contactMap.put("contactId",cursor.getString(0));
                contactMap.put("firstName",cursor.getString(1));
                contactMap.put("lastName",cursor.getString(2));
                contactMap.put("phone",cursor.getString(3));
                contactMap.put("email",cursor.getString(4));
                contactMap.put("address",cursor.getString(5));
                contactArrayList.add(contactMap);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return contactArrayList;
    }

    public HashMap<String, String> getContactInfo(String id){
        HashMap<String, String> contactMap = new HashMap<String, String>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "select * from contacts where contact_id = '" + id + "'";
       // Log.d(TAG,id+"ye hai id");
        Cursor cursor = database.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()) {
            do {

                contactMap.put("firstName", cursor.getString(1));
                contactMap.put("lastName", cursor.getString(2));
                contactMap.put("phone", cursor.getString(3));
                contactMap.put("email", cursor.getString(4));
                contactMap.put("address", cursor.getString(5));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return contactMap;
    }
}
