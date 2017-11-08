package barryalan.ediary70;

/**
 * Created by Al on 9/20/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;



class databaseHelper extends SQLiteOpenHelper {
    //CLASS VARIABLES-------------------------------------------------------------------------------
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "LoginManager";
    public static final String TABLE_USER = "users";

    //CLASS VARIABLES FOR HEALTH AND CARE TABLE
    private static final String DATABASE_NAME2 = "HealthCare";
    private static final String HEALTH_TABLE= "health";


    //USER TABLE COLUMN NAMES-----------------------------------------------------------------------
    public static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    //TABLE COLUMN NAMES FOR HEALTH
    private static final String COLUMN_USER_ALLERGIES = "allergies_name";
    private static final String COLUMN_USER_MEDICATION = "med_name";
    private static final String COLUMN_USER_VITALSIGNS = "vital_name";
    private static final String COLUMN_USER_DIET = "diet_name";
    private static final String COLUMN_USER_EXCERCISEPLAN = "excercise_plan";

    //TABLE COLUMN NAMES FOR PERSONAL GOALS

    public static final String COLUMN_USER_GOALS1 = "short_goal1";
    private static final String COLUMN_USER_GOALS2 = "short_goal2";
    private static final String COLUMN_USER_GOALS3 = "short_goal3";
    private static final String COLUMN_USER_GOALS4 = "short_goal4";
    private static final String COLUMN_USER_LONGGOALS = "long_goals";


    //CONSTRUCTOR-----------------------------------------------------------------------------------
    databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    //RUNS WHEN THE DATABASE IS CREATED(APP STARTUP)------------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATES QUERY
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY," + COLUMN_USER_NAME + " TEXT," +
                COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_USER_ALLERGIES
                + " TEXT," + COLUMN_USER_MEDICATION + " TEXT," + COLUMN_USER_DIET + " TEXT,"
                + COLUMN_USER_EXCERCISEPLAN + " TEXT," +
                COLUMN_USER_VITALSIGNS + " TEXT," + COLUMN_USER_GOALS1 + " TEXT," + COLUMN_USER_GOALS2
                + " TEXT," + COLUMN_USER_GOALS3 + " TEXT," + COLUMN_USER_GOALS4 + " TEXT,"
                + COLUMN_USER_LONGGOALS + " TEXT" + ")";





        //CREATES TABLE IN THE DATABASE USING THE QUERY
        db.execSQL(CREATE_USER_TABLE);

    }

    //NO CLUE WHAT THIS DOES------------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    //CREATES USER COLUMNS, MERGES THEM INTO A TABLE AND INSERTS IT INTO DATABASE-------------------
    void addUser(user user) {

        //OPEN DATABASE??
        SQLiteDatabase db = this.getWritableDatabase();

        //CREATING COLUMNS WITH INFORMATION AND INPUTTING THEM INTO VALUES
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getUserName());
        values.put(COLUMN_USER_EMAIL, user.getUserEmail());
        values.put(COLUMN_USER_PASSWORD, user.getuserPassword());

        //INSERTING VALUES INTO THE DATABASE UNDER THE USER
        db.insert(TABLE_USER, null, values);
        //CLOSING DATABASE
        db.close();
    }

    //UPDATES USER RECORDS ON THE DATABASE, RETURNS?------------------------------------------------
    public int updateUser(user user) {
        //OPEN DATABASE?
        SQLiteDatabase db = this.getWritableDatabase();

        //CREATING COLUMNS WITH INFORMATION FROM THE PARAMATER AND INPUTING THEM INTO VALUES
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getUserName());
        values.put(COLUMN_USER_EMAIL, user.getUserEmail());
        values.put(COLUMN_USER_ALLERGIES, user.getUserAllergies());
        values.put(COLUMN_USER_MEDICATION, user.getUserMedications());
        values.put(COLUMN_USER_VITALSIGNS, user.getUserVitalSigns());
        values.put(COLUMN_USER_DIET, user.getUserDiet());
        values.put(COLUMN_USER_EXCERCISEPLAN, user.getUserExcercisePlan());
        values.put(COLUMN_USER_GOALS1, user.getUserGoal1());
        values.put(COLUMN_USER_GOALS2, user.getUserGoal2());
        values.put(COLUMN_USER_GOALS3, user.getUserGoal3());
        values.put(COLUMN_USER_GOALS4, user.getUserGoal4());
        values.put(COLUMN_USER_LONGGOALS, user.getUserLongGoal());


        //UPDATE ROWS IN DATABASE WITH VALUES (INFO FROM PARAMETER)
        return db.update(TABLE_USER, values, COLUMN_USER_ID + "= ?", new String[]{String.valueOf(user.getId())});

    }

    //DELETE A USER FROM DATABASE BY ID-------------------------------------------------------------
    public void deleteUser(user user)
    {
        //OPEN DATABASE?
        SQLiteDatabase db = this.getWritableDatabase();

        //DELETE USER
        db.delete(TABLE_USER, COLUMN_USER_ID + "= ?", new String[]{String.valueOf(user.getId())});

        //ALWAYS CLOSE THE DATABASE
        db.close();
    }

    //RETURNS A LIST OF USERS WHICH ARE ON THE DATABASE, CAN BE USED TO ACCESS THEIR DATA-----------
    List<user> getUsers()
    {
        //CREATES NEW LIST
        List<user> userArrayList = new ArrayList<>();

        //SELECT ALL FROM QUERY
        String selectQuery = " SELECT * FROM " + TABLE_USER;

        //OPEN DATABASE??
        SQLiteDatabase db = this.getWritableDatabase();

        //CREATES CURSOR ...
        Cursor cursor = db.rawQuery(selectQuery, null);

        //IF THE DATABASE HAS AT LEAST ONE USER ON IT
        if(cursor.moveToFirst()){
            do {
                //CREATE NEW USER AND FILL IT WITH THE INFORMATION FROM THE TABLE WHICH THE CURSOR IS ON
                user user = new user();
                user.setUserId(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setUserEmail(cursor.getString(2));
                user.setUserPassword(cursor.getString(3));

                //ADD USER TO THE LIST
                userArrayList.add(user);

                //IF THERE IS A NEXT TABLE GO TO IT AND REPEAT THE LOOP
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userArrayList;
    }

    //RETURNS THE NUMBER OF USERS IN THE DATABASE---------------------------------------------------
    public int getUserCount(){
        //??
        String countUserQuery = " SELECT * FROM" + TABLE_USER;

        //OPEN DATABASE?
        SQLiteDatabase db = this.getReadableDatabase();

        //CREATES CURSOR, MAKES IT GO THROUGH THE QUERY AND COUNT IT
        Cursor cursor = db.rawQuery(countUserQuery, null);

        //CLOSES CURSOR
        cursor.close();

        return cursor.getCount();
    }

    //RETURNS A USERS OBJECT FOR THE ID PROVIDED online version--------------------------------------
    user getUser(String username) {

        //OPEN DATABASE??
        SQLiteDatabase db = this.getReadableDatabase();

        //CREATE CURSOR WHICH ALLOWS SEARCH WITHIN A DATABASE TABLE
        user user;
        try (Cursor cursor = db.query(TABLE_USER, new String[]{COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_EMAIL,
                        COLUMN_USER_PASSWORD, COLUMN_USER_ALLERGIES, COLUMN_USER_MEDICATION, COLUMN_USER_DIET,
                        COLUMN_USER_EXCERCISEPLAN, COLUMN_USER_VITALSIGNS, COLUMN_USER_GOALS1, COLUMN_USER_GOALS2,
                        COLUMN_USER_GOALS3, COLUMN_USER_GOALS4, COLUMN_USER_LONGGOALS}, COLUMN_USER_NAME + "=?",
                new String[]{username}, null, null, null, null)) {

            //??
            if (cursor != null) {
                cursor.moveToFirst();


            }

            //CREATES NEW USER WITH THE DATA COLLECTED FROM THE CURSOR
            user = new user(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),
                    cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13));

            cursor.close();
        }
        return user;

    }

    //RETURNS THE USERNAME FOR THE EMAIL PROVIDED IF IT EXISTS IN THE DATABASE-----------------------
    String getUsername(String email)
    {
        //OPEN DATABASE
        SQLiteDatabase db = this.getWritableDatabase();

        //CREATE CURSOR WHICH ITERATES THROUGH DATABASE'S EMAIL TABLE LOOKING FOR THE EMAIL PROVIDED
        Cursor cursor=db.query(TABLE_USER, null, COLUMN_USER_EMAIL + " =?", new String[]{email}, null, null, null);

        //IF THE EMAIL IS NOT FOUND IN THE DATABASE AT LEAST ONCE
        if(cursor.getCount()<1) {
            cursor.close();
            return "email does not exist";
        }
        cursor.moveToFirst();

        //ASSIGN THE USERNAME ASSIGNED TO THIS EMAIL TO THE VARIABLE NAME
        String name= cursor.getString(cursor.getColumnIndex( COLUMN_USER_NAME));
        cursor.close();
        return name;
    }

    //RETURNS THE PASSWORD FOR THE USERNAME PROVIDED IF IT EXISTS IN THE DATABASE-------------------
    String getUserPassword(String userName)
    {
        //OPEN DATABASE
        SQLiteDatabase db = this.getWritableDatabase();

        //CREATE CURSOR WHICH ITERATES THROUGH DATABASE'S USERNAME TABLE LOOKING FOR THE USERNAME PROVIDED
        Cursor cursor=db.query(TABLE_USER, null, COLUMN_USER_NAME + " =?", new String[]{userName}, null, null, null);

        //IF THE USERNAME IS NOT FOUND IN THE DATABASE AT LEAST ONCE
        if(cursor.getCount()<1) {
            cursor.close();
            return "Username does not exist";
        }
        cursor.moveToFirst();

        //ASSIGN THE PASSWORD ASSIGNED TO THIS USERNAME TO THE VARIABLE PASSWORD
        String password= cursor.getString(cursor.getColumnIndex( COLUMN_USER_PASSWORD));
        cursor.close();
        return password;
    }

    //RETURNS THE EMAIL FOR THE USERNAME PROVIDED IF IT EXISTS IN THE DATABASE----------------------
    String getUserEmail(String userName)
    {
        //OPEN DATABASE
        SQLiteDatabase db = this.getWritableDatabase();

        //CREATE CURSOR WHICH ITERATES THROUGH DATABASE'S USERNAME TABLE LOOKING FOR THE USERNAME PROVIDED
        Cursor cursor=db.query(TABLE_USER, null, COLUMN_USER_NAME + " =?", new String[]{userName}, null, null, null);

        //IF THE USERNAME IS NOT FOUND IN THE DATABASE AT LEAST ONCE
        if(cursor.getCount()<1) {
            cursor.close();
            return "Username does not exist";
        }
        cursor.moveToFirst();
        //ASSIGN THE PASSWORD ASSIGNED TO THIS USERNAME TO THE VARIABLE PASSWORD
        String email= cursor.getString(cursor.getColumnIndex( COLUMN_USER_EMAIL));
        cursor.close();
        return email;
    }

    //CHECKS IF THE USERNAME PROVIDED IS IN THE DATABASE--------------------------------------------
    boolean isUsernameTaken(EditText et_username)
    {
        //OPEN DATABASE
        SQLiteDatabase db = this.getWritableDatabase();

        //CREATE CURSOR WHICH ITERATES THROUGH DATABASE LOOKING FOR THE USERNAME
        Cursor cursor=db.query(TABLE_USER, null, COLUMN_USER_NAME + " =?", new String[]{et_username.getText().toString()}, null, null, null);

        if(cursor.getCount()<1) {//IF THE USERNAME IS NOT FOUND MEANING IT IS FOUND LESS THAN 1 TIMES
            cursor.close();
            return false;
        }
        cursor.moveToFirst();
        cursor.close();
        et_username.setError("Username is already taken");//message is chosen on switch statement above
        return true;
    }

    //CHECKS IF THE USERNAME PROVIDED IS IN THE DATABASE--------------------------------------------
    boolean isEmailTaken(EditText et_email)
    {
        //OPEN DATABASE
        SQLiteDatabase db = this.getWritableDatabase();

        //CREATE CURSOR WHICH ITERATES THROUGH DATABASE LOOKING FOR THE USERNAME
        Cursor cursor=db.query(TABLE_USER, null, COLUMN_USER_EMAIL + " =?", new String[]{et_email.getText().toString()}, null, null, null);

        if(cursor.getCount()<1) {//IF THE USERNAME IS NOT FOUND MEANING IT IS FOUND LESS THAN 1 TIMES
            cursor.close();
            return false;
        }
        cursor.moveToFirst();
        cursor.close();
        et_email.setError("Email address is already in use");//message is chosen on switch statement above
        return true;
    }

    public Cursor getGoals()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER;
        Cursor data = db.rawQuery(query, null);
        return data;

    }

    public boolean setGoal(String g)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        /*Cursor c = db.rawQuery("SELECT * FROM TABLE_USER WHERE COLUMN_USER_NAME=" + un , null);
        if (c.moveToFirst()) {
            db.execSQL("INSERT INTO TABLE_USER(COLUMN_USER_GOALS1)VALUES(" + g + "");
        } else {
            msg(this, "Invalid");
        }*/

        //passes goal in goal column of table user
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_GOALS1, g);
        long result = db.insert(TABLE_USER, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

}