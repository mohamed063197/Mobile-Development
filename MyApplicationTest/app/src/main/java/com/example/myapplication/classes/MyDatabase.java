package com.example.myapplication.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;
import android.util.Log;
import android.database.Cursor;
import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    // Nom de la base de données et version
    private static final String DB_NAME="PHARMAX23001";
    private static final int DB_VERSION = 2;

    //Declaration des tables et des colonnes
    private static final String TABLE_USER_NAME="USER";
    // dimention of columns
    private static final int COL_NAME_DIM=50;
    private static final int COL_MAIL_DIM=150;
    private static final int COL_PSEUDO_DIM=150;
    private static final int COL_TEL_DIM=150;
    private static final String COL_ID_NAME="id";
    private static final String COL_NAME_NAME="name";
    private static final String COL_MAIL_NAME="mail";
    //private static final String COL_PSEUDO_NAME="pseudo";
    private static final String COL_PASSWORD_NAME="password";
    private static final String COL_AGE_NAME="age";
    private static final String COL_TEL_NAME="tel";

    // Query to add table USERS
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + DB_NAME + "_"        + TABLE_USER_NAME + " ("+
                    COL_ID_NAME       + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    COL_NAME_NAME     + " VARCHAR("+ COL_NAME_DIM +"), " +
                    COL_MAIL_NAME     + " VARCHAR("+ COL_MAIL_DIM + ") UNIQUE NOT NULL, " +
                    COL_TEL_NAME     + " VARCHAR("+ COL_TEL_DIM + ") UNIQUE, " +
                    //COL_PSEUDO_NAME   + " VARCHAR("+ COL_PSEUDO_DIM + ") UNIQUE NOT NULL, " +
                    COL_PASSWORD_NAME + " VARCHAR("+ COL_NAME_DIM + ") NOT NULL, " +
                    COL_AGE_NAME      + " INTEGER" +
                    ");";

    private static final String DROP_TABLE_USER =
            "DROP TABLE IF EXISTS "+ DB_NAME + "_" + TABLE_USER_NAME;

    public MyDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        // au moment de la creation de la base de données une fois
        Log.d("Data base Creation", "Database created");
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        Log.d("DatabaseUpgrade", "Upgrading database from version " + oldVersion + "to" + newVersion);
        sqLiteDatabase.execSQL(DROP_TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public String getTableUserName(){
        return DB_NAME + "_" + TABLE_USER_NAME;
    }

    public Pair<Boolean, Long> addUser(User user){
        long result = -1;
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_NAME_NAME, user.getName());
            values.put(COL_MAIL_NAME, user.getMail());
            //values.put(COL_PSEUDO_NAME, user.getPseudo());
            values.put(COL_PASSWORD_NAME, user.getPassword());
            values.put(COL_AGE_NAME, user.getAge());
            values.put(COL_TEL_NAME, user.getNumeroTel());

            String v= values.toString();
            result = db.insertOrThrow(this.getTableUserName(), null,values);
            db.close();

        }catch(SQLException ex){

            Log.e("","" , ex);
        }

        return Pair.create(result != -1, result);
    }

    public boolean updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME_NAME, user.getName());
        values.put(COL_MAIL_NAME, user.getMail());
        //values.put(COL_PSEUDO_NAME, user.getPseudo());
        values.put(COL_PASSWORD_NAME, user.getPassword());
        values.put(COL_AGE_NAME, user.getAge());
        values.put(COL_TEL_NAME, user.getNumeroTel());


        String where="";
        //where
        where += COL_ID_NAME+ " = ?";

        //args
        String args[]={user.getId()+""};

        int result = db.update(this.getTableUserName(), values, where, args);
        db.close();
        return  result > 0;
    }

    public boolean deleteUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        String where="";
        //where
        where += COL_ID_NAME+ " = ?";

        //args
        String args[]={user.getId()+""};
        int result = db.delete(this.getTableUserName(),where,args);
        db.close();
        return result > 0;
    }

    public boolean readUser(User user){
        SQLiteDatabase db = getReadableDatabase();
        String where="";
        //where
        where += COL_ID_NAME+ " = ?";
        //args
        String args[]={user.getId()+""};
        String req = new StringBuilder().append("SELECT * FROM ").append(this.getTableUserName()).append(" WHERE ").append(where).toString();
        Cursor cursor = db.rawQuery(req,args);

        User return_user = new User();
        if (cursor.moveToFirst()){
            user.setId(cursor.getInt(Math.abs(cursor.getColumnIndex(COL_ID_NAME))));
            user.setName(cursor.getString(Math.abs(cursor.getColumnIndex(COL_NAME_NAME))));
            user.setMail(cursor.getString(Math.abs(cursor.getColumnIndex(COL_MAIL_NAME))));
            //user.setPseudo(cursor.getString(Math.abs(cursor.getColumnIndex(COL_PSEUDO_NAME))));
            user.setPassword(cursor.getString(Math.abs(cursor.getColumnIndex(COL_PASSWORD_NAME))));
            user.setAge(cursor.getInt(Math.abs(cursor.getColumnIndex(COL_AGE_NAME))));
            user.setNumeroTel(cursor.getString(Math.abs(cursor.getColumnIndex(COL_TEL_NAME))));
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean readUserByMailPwd(User user){
        SQLiteDatabase db = getReadableDatabase();
        String where="";
        //where
        where += COL_MAIL_NAME+ " = ?";
        where += " AND " + COL_PASSWORD_NAME+ " = ?";

        //args
        String args[]={user.getMail()+"", user.getPassword()};
        String req = new StringBuilder().append("SELECT * FROM ").append(this.getTableUserName()).append(" WHERE ").append(where).toString();
        Cursor cursor = db.rawQuery(req,args);

        User return_user = new User();
        if (cursor.moveToFirst()){
            user.setId(cursor.getInt(Math.abs(cursor.getColumnIndex(COL_ID_NAME))));
            user.setName(cursor.getString(Math.abs(cursor.getColumnIndex(COL_NAME_NAME))));
            user.setMail(cursor.getString(Math.abs(cursor.getColumnIndex(COL_MAIL_NAME))));
            //user.setPseudo(cursor.getString(Math.abs(cursor.getColumnIndex(COL_PSEUDO_NAME))));
            user.setPassword(cursor.getString(Math.abs(cursor.getColumnIndex(COL_PASSWORD_NAME))));
            user.setAge(cursor.getInt(Math.abs(cursor.getColumnIndex(COL_AGE_NAME))));
            user.setNumeroTel(cursor.getString(Math.abs(cursor.getColumnIndex(COL_TEL_NAME))));
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean readUserByPhone(User user){
        SQLiteDatabase db = getReadableDatabase();
        String where="";
        //where
        where += COL_TEL_NAME+ " = ?";
        
        //args
        String args[]={user.getNumeroTel()+""};
        String req = new StringBuilder().append("SELECT * FROM ").append(this.getTableUserName()).append(" WHERE ").append(where).toString();
        Cursor cursor = db.rawQuery(req,args);

        if (cursor.moveToFirst()){
            user.setId(cursor.getInt(Math.abs(cursor.getColumnIndex(COL_ID_NAME))));
            user.setName(cursor.getString(Math.abs(cursor.getColumnIndex(COL_NAME_NAME))));
            user.setMail(cursor.getString(Math.abs(cursor.getColumnIndex(COL_MAIL_NAME))));
            //user.setPseudo(cursor.getString(Math.abs(cursor.getColumnIndex(COL_PSEUDO_NAME))));
            user.setPassword(cursor.getString(Math.abs(cursor.getColumnIndex(COL_PASSWORD_NAME))));
            user.setAge(cursor.getInt(Math.abs(cursor.getColumnIndex(COL_AGE_NAME))));
            user.setNumeroTel(cursor.getString(Math.abs(cursor.getColumnIndex(COL_TEL_NAME))));
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean readUserByMail(User user){
        SQLiteDatabase db = getReadableDatabase();
        String where="";
        //where
        where += COL_MAIL_NAME+ " = ?";

        //args
        String args[]={user.getMail()+""};
        String req = new StringBuilder().append("SELECT * FROM ").append(this.getTableUserName()).append(" WHERE ").append(where).toString();
        Cursor cursor = db.rawQuery(req,args);

        User return_user = new User();
        if (cursor.moveToFirst()){
            user.setId(cursor.getInt(Math.abs(cursor.getColumnIndex(COL_ID_NAME))));
            user.setName(cursor.getString(Math.abs(cursor.getColumnIndex(COL_NAME_NAME))));
            user.setMail(cursor.getString(Math.abs(cursor.getColumnIndex(COL_MAIL_NAME))));
            //user.setPseudo(cursor.getString(Math.abs(cursor.getColumnIndex(COL_PSEUDO_NAME))));
            user.setPassword(cursor.getString(Math.abs(cursor.getColumnIndex(COL_PASSWORD_NAME))));
            user.setAge(cursor.getInt(Math.abs(cursor.getColumnIndex(COL_AGE_NAME))));
            user.setNumeroTel(cursor.getString(Math.abs(cursor.getColumnIndex(COL_TEL_NAME))));
            return true;
        }
        cursor.close();
        return false;
    }

    public ArrayList<User> readAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + this.getTableUserName() ,null);

        if (cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(cursor.getInt(Math.abs(cursor.getColumnIndex(COL_ID_NAME))));
                user.setName(cursor.getString(Math.abs(cursor.getColumnIndex(COL_NAME_NAME))));
                user.setMail(cursor.getString(Math.abs(cursor.getColumnIndex(COL_MAIL_NAME))));
                //user.setPseudo(cursor.getString(Math.abs(cursor.getColumnIndex(COL_PSEUDO_NAME))));
                user.setPassword(cursor.getString(Math.abs(cursor.getColumnIndex(COL_PASSWORD_NAME))));
                user.setAge(cursor.getInt(Math.abs(cursor.getColumnIndex(COL_AGE_NAME))));
                user.setNumeroTel(cursor.getString(Math.abs(cursor.getColumnIndex(COL_TEL_NAME))));
                users.add(user);
            }while (cursor.moveToNext());
        }

        cursor.close();
        return users;
    }

}
