package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    DBHelper dbHelper = DBHelper.getInstance(this);
EditText etTitle, etAuthor, etPages, etbookCategory;
String getTitleData,getAuthorData, getbookCategory;
int getPagesData,getId;
Button BtnUpdate, BtnDelete;


    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etAuthor=findViewById(R.id.authorUpdate);
        etTitle=findViewById(R.id.titleUpdate);
        etPages=findViewById(R.id.pagesUpdate);
        etbookCategory=findViewById(R.id.category);
        BtnUpdate=findViewById(R.id.UpdateBtn);
        BtnDelete=findViewById(R.id.DeleteBtn);


        Intent intent = getIntent();
        getId=intent.getIntExtra("id",0);
        getAuthorData=intent.getStringExtra("author");
        getTitleData=intent.getStringExtra("title");
        getbookCategory=intent.getStringExtra("category");
        getPagesData=intent.getIntExtra("pages",0);



        etTitle.setText(getTitleData);
        etbookCategory.setText(getbookCategory);
        etAuthor.setText(getAuthorData);
        etPages.setText(Integer.toString(getPagesData));


        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();


                int updatePages = Integer.parseInt(etPages.getText().toString());
                String updateAuthor = etAuthor.getText().toString();
                String updateTitle = etTitle.getText().toString();
                String updateCategory =etbookCategory.getText().toString();

                dbHelper.updateStudent(new Model(updateTitle, updateAuthor, updatePages, getId,updateCategory));
            }

        });

        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteData(getId);
                Toast.makeText(UpdateActivity.this, "Deleted Successfully ", Toast.LENGTH_SHORT).show();
            }
        });


        }


    }
