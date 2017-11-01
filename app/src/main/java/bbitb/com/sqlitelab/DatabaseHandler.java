package bbitb.com.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static bbitb.com.sqlitelab.Contract.DATABASE_NAME;
import static bbitb.com.sqlitelab.Contract.DATABASE_VERSION;
import static bbitb.com.sqlitelab.Contract.TABLE_CONTACTS;
import static bbitb.com.sqlitelab.Contract.TABLE_COURSES;
import static bbitb.com.sqlitelab.Contract.courses.CREATE_COURSES_TABLE;
import static bbitb.com.sqlitelab.Contract.courses.KEY_FACULTY;
import static bbitb.com.sqlitelab.Contract.users.CREATE_CONTACTS_TABLE;
import static bbitb.com.sqlitelab.Contract.users.KEY_ID;
import static bbitb.com.sqlitelab.Contract.users.KEY_NAME;
import static bbitb.com.sqlitelab.Contract.users.KEY_PH_NO;

/**
 * Created by Daniel Thuo on 17/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_COURSES_TABLE);
    }

    //For anytime we need to upgrade the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);

        //create tables again
        onCreate(db);
    }

    //Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); //Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); //Contact Phone Number

        //inserting row
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    //Adding new course
    public void addCourse(Courses courses) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues coursevalues = new ContentValues();
        coursevalues.put(KEY_NAME, courses.getName()); //Contact Name
        coursevalues.put(KEY_FACULTY, courses.getFaculty());

        //inserting row
        db.insert(TABLE_COURSES, null, coursevalues);
        db.close();
    }

    //Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
             KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
             new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact  = new Contact (Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        //return contact
        return contact;
    }

    //Getting single course
    public Courses getCourse(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COURSES, new String[] { KEY_ID,
                        KEY_NAME, KEY_FACULTY}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Courses course  = new Courses (Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        //return contact
        return course;
    }

    //Getting All Contact
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        //Select  All query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all row and adding to list
        if (cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //Adding contact  to list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        //return contact list

        return contactList;
     }

    //Getting All Courses
    public List<Courses> getAllCourses() {
        List<Courses> coursesList = new ArrayList<Courses>();
        //Select  All query
        String selectQuery = "SELECT * FROM " + TABLE_COURSES;

        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all row and adding to list
        if (cursor.moveToFirst()){
            do {
                Courses course = new Courses();
                course.setID(Integer.parseInt(cursor.getString(0)));
                course.setName(cursor.getString(1));
                course.setFaculty(cursor.getString(2));
                //Adding contact  to list
                coursesList.add(course);
            }while (cursor.moveToNext());
        }
        //return contact list

        return coursesList;
    }

    //Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM" + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //Getting courses Count
    public int getCoursesCount() {
        String coursecountQuery = "SELECT * FROM" + TABLE_COURSES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor coursecursor = db.rawQuery(coursecountQuery, null);
        coursecursor.close();

        //return count
        return coursecursor.getCount();

    }

    //updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        //updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?",
                new String[] {String.valueOf(contact.getID())});

    }

    //updating single course
    public int updateCourse(Courses course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, course.getName());
        values.put(KEY_FACULTY, course.getFaculty());

        //updating row
        return db.update(TABLE_COURSES, values, KEY_ID + "=?",
                new String[] {String.valueOf(course.getID())});

    }

    //deleting single contact
    public void deleteContact(Contact contact) {

        SQLiteDatabase db  = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }

    //deleting single contact
    public void deleteCourse(Courses course) {

        SQLiteDatabase db  = this.getWritableDatabase();
        db.delete(TABLE_COURSES, KEY_ID + "=?",
                new String[] { String.valueOf(course.getID()) });
        db.close();
    }

}
