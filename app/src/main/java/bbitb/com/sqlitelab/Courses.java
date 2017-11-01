package bbitb.com.sqlitelab;

/**
 * Created by Daniel Thuo on 26/10/2017.
 */

public class Courses {

    //private variables
    int _id;
    String _name;
    String _faculty;

    //Empty Constructor
    public Courses() {

    }

    //constructor
    public Courses(int id, String name, String _faculty) {
        this._id = id;
        this._name = name;
        this._faculty = _faculty;

    }

    //constructor
    public Courses(String name, String _faculty) {
        this._name = name;
        this._faculty = _faculty;

    }

    //getting ID
    public int getID() {
        return this._id;

    }

    //getting name
    public String getName() {
        return this._name;
    }

    //getting phone number
    public String getFaculty() {return this._faculty;}

    //setting ID
    public void setID(int id) {
        this._id = id;
    }

    //setting name
    public void setName(String name) {
        this._name = name;
    }

    //setting phone number
    public void setFaculty(String faculty) {
        this._faculty = faculty;
    }
}
