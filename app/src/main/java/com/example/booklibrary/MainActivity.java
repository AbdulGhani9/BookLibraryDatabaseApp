package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    Button order, size;
    Adapter adapter;
    DBHelper dbHelper = DBHelper.getInstance(this);
    RecyclerView recyclerView;
    List<Model> modelList;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        order=findViewById(R.id.order);
        size=findViewById(R.id.size);
        size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelList.size();
                Toast.makeText(MainActivity.this, "Items are  :"+modelList.size(), Toast.LENGTH_SHORT).show();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "List is sorted", Toast.LENGTH_SHORT).show();

            }
        });


        Toast.makeText(this, "WELCOME", Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        modelList=dbHelper.fetchALlData();

        adapter = new Adapter(modelList);

       adapter.setOnItemCLickListener(new Adapter.OnItemCLickListener() {
           @Override
           public void onItemCLick(View view, int position) {
               Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
               Model model = modelList.get(position);
               intent.putExtra("id",model.getId());
               intent.putExtra("title",model.getTitle());
               intent.putExtra("author",model.getAuthor());
               intent.putExtra("pages",model.getPages());
               intent.putExtra("category",model.getCategory());
               startActivity(intent);

           }
       });


//        Toast.makeText(this, "List Size="+modelList.get(1), Toast.LENGTH_LONG).show();

//        Log.d("tagC", "onCreate:"+modelList.size());
//        System.out.println(modelList.size());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        modelList=dbHelper.fetchALlData();

        adapter = new Adapter(modelList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemCLickListener(new Adapter.OnItemCLickListener() {
            @Override
            public void onItemCLick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                Model model = modelList.get(position);
                intent.putExtra("id",model.getId());
                intent.putExtra("title",model.getTitle());
                intent.putExtra("category",model.getCategory());
                intent.putExtra("author",model.getAuthor());
                intent.putExtra("pages",model.getPages());

                startActivity(intent);

            }
        });
    }
}