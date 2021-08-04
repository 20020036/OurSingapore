package sg.edu.rp.c346.id20020036.oursingapore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "books.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_BOOKS = "BOOKS";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_NUM = "num";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Books
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT,
        // singers TEXT, stars INTEGER, year INTEGER );
        String createSongTableSql = "CREATE TABLE " + TABLE_BOOKS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_AUTHOR + " TEXT, "
                + COLUMN_NUM + " INTEGER, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createSongTableSql);
        Log.i("info", createSongTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

    public long insertBook(String title, String author, int num, int stars) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_AUTHOR, author);
        values.put(COLUMN_NUM, num);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_SONG
        long result = db.insert(TABLE_BOOKS, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<Books> getAllBooks() {
        ArrayList<Books> booksList = new ArrayList<Books>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + "," + COLUMN_AUTHOR + ","
                + COLUMN_NUM + ","
                + COLUMN_STARS + " FROM " + TABLE_BOOKS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String author = cursor.getString(2);
                int num = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Books newBook = new Books(id, title, author, num, stars);
                booksList.add(newBook);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return booksList;
    }

    public ArrayList<Books> getAllBooksByStars(int starsFilter) {
        ArrayList<Books> booksList = new ArrayList<Books>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_AUTHOR, COLUMN_NUM, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        //String selectQuery = "SELECT " + COLUMN_ID + ","
        //            + COLUMN_TITLE + ","
        //            + COLUMN_AUTHOR + ","
        //            + COLUMN_NUM + ","
        //            + COLUMN_STARS
        //            + " FROM " + TABLE_SONG;

        Cursor cursor;
        cursor = db.query(TABLE_BOOKS, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String author = cursor.getString(2);
                int num = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Books newBook = new Books(id, title, author, num, stars);
                booksList.add(newBook);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return booksList;
    }



    public int updateBook(Books data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_AUTHOR, data.getAuthor());
        values.put(COLUMN_NUM, data.getNum());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_BOOKS, values, condition, args);
        db.close();
        return result;
    }


    public int deleteBook(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_BOOKS, condition, args);
        db.close();
        return result;
    }

    public ArrayList<String> getNum() {
        ArrayList<String> codes = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_NUM};

        Cursor cursor;
        cursor = db.query(true, TABLE_BOOKS, columns, null, null, null, null, null, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                codes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return codes;
    }

    public ArrayList<Books> getAllBooksByNum(int numFilter) {
        ArrayList<Books> booksList = new ArrayList<Books>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_AUTHOR, COLUMN_NUM, COLUMN_STARS};
        String condition = COLUMN_NUM + "= ?";
        String[] args = {String.valueOf(numFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_BOOKS, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Books newBook = new Books(id, title, singers, year, stars);
                booksList.add(newBook);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return booksList;
    }
}
