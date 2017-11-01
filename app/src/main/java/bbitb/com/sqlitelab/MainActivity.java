package bbitb.com.sqlitelab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DatabaseHandler db = new DatabaseHandler(this);
        DatabaseHandler db_1 = new DatabaseHandler(this);

        //CRUD OPERATIONS
        Log.d("Insert:", "Inserting..");
        db.addContact(new Contact("Ravi", "91000000"));
        db.addContact(new Contact("Thuo", "71000000"));
        db.addContact(new Contact("Raj", "61000000"));
        db.addContact(new Contact("Kui", "51000000"));

        //Reading all contacts
        Log.d("Reading:", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log  = "Id: "+cn.getID()+ " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();

            //Writing Contacts to log
            Log.d("Name: ", log);
        }


        Log.d("Insert:", "Inserting Courses..");
        db_1.addCourse(new Courses("BBIT", "IT"));
        db_1.addCourse(new Courses("ACTURIAL SCIENCE", "SMC"));
        db_1.addCourse(new Courses("ETHICS", "HUMANITIES"));
        db_1.addCourse(new Courses("GERMAN", "HUMANITIES"));

        //Reading all contacts
        Log.d("Reading:", "Reading all courses..");
        List<Courses> course = db_1.getAllCourses();

        for (Courses cn : course) {
            String log  = "Id: "+cn.getID()+ " ,Name: " + cn.getName() + " ,Phone: " + cn.getFaculty();

            //Writing Contacts to log
            Log.d("Name: ", log);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
