package com.example.databasesqlite139;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity implements ListView.OnItemClickListener{
    private ListView listView;
    DatabaseHelper databaseHelper;
    private AdapterView adapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        listView=(ListView) findViewById(R.id.listViewId);
        databaseHelper=new DatabaseHelper(this);
        loadData();
    }
    public void loadData(){
        final ArrayList<String> listData = new ArrayList<>();
        final Cursor cursor = databaseHelper.showAllData();

        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"Not Data is Available ",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                listData.add(cursor.getString(0)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2));
               // listData.add(cursor.getString(1)+"\n"+cursor.getString(0));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.textViewId,listData);
                listView.setAdapter(adapter);






    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedValue = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),"Selected value: "+selectedValue,Toast.LENGTH_LONG).show();

    }
}
