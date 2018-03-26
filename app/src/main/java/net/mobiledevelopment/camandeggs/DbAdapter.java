package net.mobiledevelopment.camandeggs;

/**
 * Created by robbwise on 3/23/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbAdapter {
    dBHelper helper;
    public DbAdapter(Context context)
    {
        helper = new dBHelper(context);
    }

    //insert the camandeggs webcam server path
    /**
     *
     * @param
     * @return
     */

    //Create data
    public long insertData(String name, int eggs){
        SQLiteDatabase dbb = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dBHelper.NAME, name);
        contentValues.put(dBHelper.EGGS, eggs);
        long id = dbb.insert(dBHelper.TABLE_NAME, null , contentValues);
        return id;
    }



    //Get data -- retrieve chicken record from db
    public String getData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {dBHelper.CID, dBHelper.BREED, dBHelper.NAME, dBHelper.EGGS};
        Cursor cursor =db.query(dBHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext()){
            int cid =cursor.getInt(cursor.getColumnIndex(dBHelper.CID));
            String breed =cursor.getString(cursor.getColumnIndex(dBHelper.BREED));
            String name =cursor.getString(cursor.getColumnIndex(dBHelper.NAME));
            int eggs =cursor.getInt(cursor.getColumnIndex(dBHelper.EGGS));
            buffer.append(cid+ " " + breed + " " + name + " " + eggs +" \n");
        }
        return buffer.toString();

    }

    //delete a chicken record by name
    public int delete(String chickenName){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs ={chickenName};
        int count =db.delete(dBHelper.TABLE_NAME , dBHelper.NAME +" = ?",whereArgs);
        return count;
    }

    //edit/update chicken name
    public int updateChicken(String oldName , String newName){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dBHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(dBHelper.TABLE_NAME,contentValues, dBHelper.NAME +" = ?",whereArgs );
        return count;
    }

    //update eggs count
    public int updateEggs(String oldEggs , String newEggs){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dBHelper.EGGS,newEggs);
        String[] whereArgs= {oldEggs};
        int count =db.update(dBHelper.TABLE_NAME,contentValues, dBHelper.EGGS +" = ?",whereArgs );
        return count;
    }

    static class dBHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "camAndEggsDatabase"; // Database NAME
        private static final String TABLE_NAME = "chickenTable"; // Table NAME
        private static final int DATABASE_Version = 1; // Database Version
        private static final String CID="_id"; // Column I (Primary Key for Chicken object)
        private static final String BREED = "Breed"; //Column II
        private static final String NAME = "NAME"; // Column III
        private static final String EGGS = "Eggs"; // Column III
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+CID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ BREED +" VARCHAR(255) ,"+ NAME +" VARCHAR(225), "+ EGGS+" INTEGER);";

        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public dBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}

