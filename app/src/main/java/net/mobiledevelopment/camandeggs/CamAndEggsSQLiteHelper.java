package net.mobiledevelopment.camandeggs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by robbwise on 3/23/18.
 */

public class CamAndEggsSQLiteHelper extends SQLiteOpenHelper {

    // Define static constants for table & columns names (all column headers are Strings)
    private static final String TABLE_CHICKENS = "chickens";
    private static final String TABLE_SERVER = "server";

    // Server Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_BREED = "breed";
    private static final String KEY_NAME = "name";
    private static final String KEY_EGGS = "eggs";
    private static final String[] CHICKENS_COLUMNS = {KEY_ID, KEY_BREED, KEY_NAME, KEY_EGGS};

    //webcam server table columns names
    private static final String KEY_SERVER_ID = "id";
    private static final String KEY_SERVER_IP= "ip";
    private static final String[] SERVER_COLUMNS = {KEY_SERVER_ID, KEY_SERVER_IP};


    //database version
    private static final int DATABASE_VERSION = 3;  //change this value when making changes to the DB structure.

    //database Name
    private static final String DATABASE_NAME = "CamAndEggsDB";

    public CamAndEggsSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create the chicken table with datatypes for each column
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create chicken table
        String CREATE_CHICKEN_TABLE = "CREATE TABLE chickens ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "breed TEXT, " +
                "name TEXT, " +
                "eggs INTEGER DEFAULT 0)";

        // create chickens table
        db.execSQL(CREATE_CHICKEN_TABLE);

        //SQL Statement to create a webcam server table
        String CREATE_SERVER_TABLE = "CREATE TABLE server ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ip TEXT)";
        db.execSQL(CREATE_SERVER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older chicken table if existed
        db.execSQL("DROP TABLE IF EXISTS chickens");
        db.execSQL("DROP TABLE IF EXISTS server");

        // create fresh chickens table
        this.onCreate(db);
    }

    /***********************************************************
     *   Add CRUD methods (Create, Read, Update, Delete
     *
     *   addChicken(Chicken chicken)
     *   getChicken(int id)
     *   getAllChickens()
     *   update(Chicken chicken)
     *   delete(Chicken chicken)
     *
     *   addServer(Server server)
     *   deleteServer(Server server)
     *
     ***********************************************************/

    //Create a chicken entry in the DB from the Server.class --addChicken()
    public void addChicken(Chicken chicken) {
        //for logging
        Log.d("addChicken", chicken.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_BREED, chicken.getBreed()); // getter from Server.java
        values.put(KEY_NAME, chicken.getName()); // getter from Server.java
        values.put(KEY_EGGS, chicken.getEggs()); //getter from Server.java

        // 3. insert
        db.insert(TABLE_CHICKENS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    //Read -- get a Server object
    public Chicken getChicken(String name) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_CHICKENS, // a. table
                        CHICKENS_COLUMNS, // b. column names
                        " name = ?", // c. selections
                        new String[]{String.valueOf(name)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build chicken object
        Chicken chicken = new Chicken();
        chicken.setId(Integer.parseInt(cursor.getString(0)));
        chicken.setBreed(cursor.getString(1));
        chicken.setName(cursor.getString(2));
        chicken.setEggs(Integer.parseInt(cursor.getString(3)));

        //log
        Log.d("getChicken(" + name + ")", chicken.toString());

        // 5. return chicken
        return chicken;
    }

    //REad -- get all chickens
    public List<Chicken> getAllChickens() {
        List<Chicken> chickens = new LinkedList<Chicken>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CHICKENS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build chicken and add it to list
        Chicken chicken = null;
        if (cursor.moveToFirst()) {
            do {
                chicken = new Chicken();
                chicken.setId(Integer.parseInt(cursor.getString(0)));
                chicken.setBreed(cursor.getString(1));
                chicken.setName(cursor.getString(2));
                chicken.setEggs(Integer.parseInt(cursor.getString(3)));

                // Add chicken to chickens
                chickens.add(chicken);
            } while (cursor.moveToNext());
        }

        Log.d("getAllChickens()", chickens.toString());

        // return chickens
        return chickens;
    }

    //Update a chicken (most likely used for updating egg counts
    public int updateChicken(Chicken chicken) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("breed", chicken.getBreed()); // get breed
        values.put("name", chicken.getName()); // get name
        values.put("eggs", chicken.getEggs()); //get eggs

        // 3. updating row
        int i = db.update(TABLE_CHICKENS, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(chicken.getId())}); //selection args

        // 4. close
        db.close();

        return i;

    }

    //Delete a chicken
    public void deleteChicken(Chicken chicken) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_CHICKENS, //table name
                KEY_ID + " = ?",  // selections
                new String[]{String.valueOf(chicken.getId())}); //selections args

        // 3. close
        db.close();

        //log
        Log.d("deleteChicken", chicken.toString());

    }

    //create a server and add it
    public void addServer(Server server) {
        //for logging
        Log.d("addServer", server.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_SERVER_IP, server.getIP()); // getter from Server.java

        // 3. insert
        db.insert(TABLE_SERVER, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    //delete a server
    public void deleteServer(Server server) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_SERVER, //table name
                KEY_ID + " = ?",  // selections
                new String[]{String.valueOf(server.getId())}); //selections args

        // 3. close
        db.close();

        //log
        Log.d("deleteServer", server.toString());

    }

    //get allServers
    //REad -- get all chickens
    public List<Server> getAllServers() {
        List<Server> servers = new LinkedList<Server>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SERVER;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build chicken and add it to list
        Server server = null;
        if (cursor.moveToFirst()) {
            do {
                server = new Server();
                server.setId(Integer.parseInt(cursor.getString(0)));
                server.setIP(cursor.getString(1));


                // Add chicken to chickens
                servers.add(server);
            } while (cursor.moveToNext());
        }

        Log.d("getAllServers()", servers.toString());

        // return chickens
        return servers;
    }

}