package com.example.databasesqlite139;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseHelper databaseHelper;


    private EditText nameEditText,locationEditText,contactEditText;
    private Button saveButton,showButton,updateButton,deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText=(EditText) findViewById(R.id.nameEditTextId);
        locationEditText=(EditText) findViewById(R.id.locationEditTextId);
        contactEditText=(EditText) findViewById(R.id.contractEditTextId);


        saveButton=(Button) findViewById(R.id.saveButtonId);
        showButton=(Button) findViewById(R.id.showButtonId);
        updateButton=(Button) findViewById(R.id.updateButtonId);
        deleteButton=(Button) findViewById(R.id.deleteButtonId);

        saveButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);


        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        String name= nameEditText.getText().toString();
        String location= locationEditText.getText().toString();
        String contact= contactEditText.getText().toString();

        if (v.getId()==R.id.saveButtonId){

            if (contact.equals("") && name.equals("") && location.equals("")){
                Toast.makeText(getApplicationContext(),"Please enter the data",Toast.LENGTH_LONG).show();
            }else{
               long rowNumber = databaseHelper.saveData(name,location,contact);
               if (rowNumber>-1){
                   Toast.makeText(getApplicationContext(),"Data Inserted Successfully",Toast.LENGTH_LONG).show();
                   nameEditText.setText("");
                   locationEditText.setText("");
                   contactEditText.setText("");


               }else {
                   Toast.makeText(getApplicationContext(),"Data Not Inserted Successfully",Toast.LENGTH_LONG).show();
               }
            }
        }

       else if (v.getId()==R.id.showButtonId){
            Intent intent=new Intent(MainActivity.this,ListDataActivity.class);
             startActivity(intent);

        }

       else if (v.getId()==R.id.updateButtonId){
                Boolean isUpdated = databaseHelper.updateData(name,location,contact);

                if (isUpdated == true){
                    Toast.makeText(getApplicationContext(),"Data is updated",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getApplicationContext(),"Data not updated",Toast.LENGTH_LONG).show();
                }

        }

       else if (v.getId()==R.id.deleteButtonId){
           int value = databaseHelper.deleteData(contact);
           if (value<0){
               Toast.makeText(getApplicationContext(),"Data Not Deleted",Toast.LENGTH_LONG).show();
           }else {
               Toast.makeText(getApplicationContext(),"Data is Deleted",Toast.LENGTH_LONG).show();
           }

        }
    }
}
