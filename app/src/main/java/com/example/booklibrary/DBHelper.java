package com.example.booklibrary;

import android.database.sqlite.SQLiteOpenHelper;



import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static  final String DB_NAME= "TodoData";
    public static DBHelper instance;
    public static  final Integer VERSION= 8;


    public  static DBHelper getInstance(Context context){
        if (instance== null){
            instance = new DBHelper(context);
        }
        return instance;
    }


    private DBHelper(@Nullable Context context) {
        super(context,DB_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Model.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion!=newVersion){
            db.execSQL(Model.DROP_TABLE);
            db.execSQL(Model.CREATE_TABLE);
        }

    }
    public boolean insertData(Model model){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Model.COL_TITLE,model.getTitle());
        contentValues.put(Model.COL_AUTHOR,model.getAuthor());
        contentValues.put(Model.COL_PAGES,model.getPages());
        contentValues.put(Model.COL_ID,model.getId());
        contentValues.put(Model.COL_CATEGORY,model.getCategory());

        long effectedRows=0;
        try {
            effectedRows=sqLiteDatabase.insert(Model.TABLE_NAME,null,contentValues);
        }catch (Exception exception){
            return false;
        }
        return effectedRows>0;
    }
    public  boolean updateStudent(Model model){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Model.COL_TITLE,model.getTitle());
        contentValues.put(Model.COL_AUTHOR,model.getAuthor());
        contentValues.put(Model.COL_PAGES,model.getPages());
        contentValues.put(Model.COL_CATEGORY,model.getCategory());

        long effectedRows= 0;
        try{
            effectedRows=database.update(Model.TABLE_NAME,contentValues,Model.COL_ID+"=?", new String[]{String.valueOf((model.getId()))});
        }catch (Exception ex){
            return  false;
        }
        return effectedRows>0;
    }
    public boolean deleteData( int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long effectedRows=0;
        try {
            effectedRows=sqLiteDatabase.delete(Model.TABLE_NAME,Model.COL_ID+"= ?",new String[]{String.valueOf(id)});
        }catch (Exception e){
            return  false;
        }
        return effectedRows>0;
    }
    @SuppressLint("Range")
    List<Model> fetchALlData(){
        SQLiteDatabase database= getReadableDatabase();
        Cursor cursor = database.rawQuery(Model.SELECT_ALL_DATA,null);
        ArrayList<Model> modelArrayList = new ArrayList<>(cursor.getCount());
        if (cursor.moveToFirst()){
            do{
                Model model = new Model();
                model.setTitle(cursor.getString(cursor.getColumnIndex(Model.COL_TITLE)));
                model.setAuthor(cursor.getString(cursor.getColumnIndex(Model.COL_AUTHOR)));
                model.setPages(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Model.COL_PAGES))));
                model.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Model.COL_ID))));
                model.setCategory(cursor.getString(cursor.getColumnIndex(Model.COL_CATEGORY)));



                modelArrayList.add(model);
            }while (cursor.moveToNext());
        }
//        Log.d("tagM", "fetchALlData:"+modelArrayList.size());
        return modelArrayList;
    }


}
