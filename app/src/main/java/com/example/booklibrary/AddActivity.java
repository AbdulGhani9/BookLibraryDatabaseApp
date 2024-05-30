package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText title, author,pages,category;
    Button btn;
    DBHelper dbHelper = DBHelper.getInstance(this);
    int count;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        pages = findViewById(R.id.pages);
        category = findViewById(R.id.category);
        btn = findViewById(R.id.btnAdd);
        if (dbHelper.fetchALlData().size()==0) {
            count = 1;
        }
         else if (dbHelper.fetchALlData().size() != 0) {
            count = dbHelper.fetchALlData().size();
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author_data, title_data, category_data;
                int pages_data;
                author_data = author.getText().toString();
                title_data = title.getText().toString();
                category_data = category.getText().toString();
                pages_data = Integer.parseInt(pages.getText().toString());
                if (dbHelper.insertData(new Model(title_data, author_data, pages_data, count++, category_data))) {
                    Toast.makeText(AddActivity.this, "Added, Thank-You", Toast.LENGTH_SHORT).show();
                } else if (dbHelper.insertData(new Model(title_data, author_data, pages_data, count++, category_data))){
                    Toast.makeText(AddActivity.this, "Added, Thank-You", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    

}