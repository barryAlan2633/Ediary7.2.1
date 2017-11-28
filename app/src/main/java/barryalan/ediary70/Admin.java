package barryalan.ediary70;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static barryalan.ediary70.databaseHelper.TABLE_USER;

public class Admin extends AppCompatActivity  implements AdapterView.OnItemClickListener{

    //Open database for access
    final databaseHelper db = new databaseHelper(this);
    //Create buttons to link to the display
    ListView lv_admin;
    Button btn_delete;

    //Everything happening on this page is executed from here
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        lv_admin = (ListView) findViewById(R.id.lv_admin);
        populateGoalListView();

    }

    //Adds all content into the list view on the screen
    public void populateGoalListView(){

        //Get the amount of users in the database
        Cursor res = db.getUserCount();

        //Create a list object of strings
        ArrayList<String> listData = new ArrayList<>();

        //If the database is empty
        if (res.getCount() == 0) {
            //show message
            showmessage("Error", "No data found");
            return;
        }

        //While there is still more users in the database
        while (res.moveToNext()) {
            //Add the ID, username, and email of the user into a string then add it into the list
            listData.add("ID: " + res.getString(0) + "\n   Username: " + res.getString(2) + "\n   Email: " + res.getString(3));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lv_admin.setAdapter(adapter);
        lv_admin.setOnItemClickListener(this);
    }

    //Displays pop up box with two buttons
    public void showAlertDialog(final View v, final String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //Sets message pop up box content
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        //If the Delete button is pressed inside the pop up text box
        alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                //Displays toast message
                Toast.makeText(getApplicationContext(), title + " has been deleted" ,Toast.LENGTH_SHORT).show();

                //Get string text from the selected box in the list view
                final TextView tv = (TextView) v;
                String UserInfo = tv.getText().toString();
                //Get the ID number portion of that string
                String substr = UserInfo.substring(4,5);

                //Delete the user whose ID matches that found from the string above
                db.deleteUser(Integer.parseInt(substr));

                //Update the visual display of the current users
                populateGoalListView();

                //Display a toast message
                Toast.makeText(Admin.this,"You have deleted " + tv.getText(), Toast.LENGTH_SHORT).show();

            }
        });

        //If the Cancel button is pressed inside the pop up text box
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Toast.makeText(Admin.this,"You have canceled the deletion", Toast.LENGTH_SHORT).show();
            }
        });

        //Make the pop up box visible
        alertDialog.show();
    }

    //Displays pop up box with no buttons
    public void showmessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Set the content and settings of the text box
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        //Make the text box visible
        builder.show();
    }

    //When an item on the list view is clicked
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
        final TextView tv = (TextView) view;

        //Display a toast message
        Toast.makeText(this,"You have selected " + tv.getText(), Toast.LENGTH_SHORT).show();

        //Link to the delete button on the Admin page
        btn_delete = (Button) findViewById(R.id.btn_delete);

        //If the delete button on the admin page is clicked on
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create and display pop up text with two buttons
                showAlertDialog(tv, "Are you sure you want to delete?", "");
            }
        });
    }

    //LINK THE LOGIN PAGE TO THE ADMIN PAGE--------------------------------------------------
    public void gotoLoginActivity(View view) {
        Intent name = new Intent(this, login.class);
        startActivity(name);
    }

}
