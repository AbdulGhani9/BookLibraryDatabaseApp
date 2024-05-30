package com.example.booklibrary;



import java.util.List;
import java.util.Objects;

public class Model {
    private String title, author, category;
           int pages, id;
    public static final String TABLE_NAME = "myLibrary";
    public static final String COL_TITLE = "book_title";
    public static final String COL_CATEGORY = "book_category";

    public static final String COL_ID = "id";


    public static final String COL_AUTHOR = "book_author";
    public static final String COL_PAGES = "book_pages";


    public static final String CREATE_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s( %s INTEGER PRIMARY KEY , %s TEXT, %s TEXT, %s TEXT, %s INTEGER)", TABLE_NAME, COL_ID,COL_TITLE, COL_AUTHOR,COL_CATEGORY, COL_PAGES);
    public static final String SELECT_ALL_DATA = "SELECT * FROM " + TABLE_NAME;
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;



    public Model() {
    }

    public Model(String title, String author, int pages, String category ){
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.category=category;

    }

    public Model(String title, String author, int pages, int id,String category) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.id=id;
        this.category=category;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Model{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages='" + pages + '\'' +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Model model = (Model) o;
//        return Objects.equals(title, model.title) && Objects.equals(author, model.author) && Objects.equals(pages, model.pages);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(title, author, pages);
//    }
}

