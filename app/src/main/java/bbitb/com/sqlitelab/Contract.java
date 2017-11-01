package bbitb.com.sqlitelab;

import android.provider.BaseColumns;

/**
 * Created by Daniel Thuo on 26/10/2017.
 */

public class Contract {

    private Contract(){

    }

    //All Static Variables
    //Database Version
    public  static final  int DATABASE_VERSION = 1;

    //Database Name
    public static final String DATABASE_NAME = "contactManager";

    //Contact table name
    public static final String TABLE_CONTACTS = "contacts";
    public static final String TABLE_COURSES = "courses";


    public static  abstract class users implements BaseColumns {
        //Contact Table Column names
        public static final String KEY_ID = "id";
        public static final String KEY_NAME = "name";
        public static final String KEY_PH_NO = "phone_number";

        public static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
    }

    public static  abstract class courses implements BaseColumns {
        //Contact Table Column names
        public static final String KEY_ID = "id";
        public static final String KEY_NAME = "name";
        public static final String KEY_FACULTY = "faculty";

        public static final String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_FACULTY + " TEXT" + ")";
    }

}
