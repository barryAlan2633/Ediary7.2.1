package barryalan.ediary70;

/**
 * Created by Al on 10/30/2017.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class goalsMenu extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static final String TAG = "";
    final databaseHelper db = new databaseHelper(this);

    //Declare a linear layout, Button
    LinearLayout navigationBar;
    Button btn_barVisibility;

    private ListView lv;

    //Variables
    String timeRemaining;
    int seconds = 60;
    String timeAmount = "";

    //Everything that runs on this page is executed from here
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_menu);

        //Link navigationBar to the linear layout on the page
        navigationBar = (LinearLayout)findViewById(R.id.navigationBar);

        //Link navigationBar visibility button to the Button on the page
        btn_barVisibility = (Button)findViewById(R.id.btn_barVisibility);

        user User1 = new user();

        lv = (ListView) findViewById(R.id.lv_Ggoals);

        populateGoalListView();
    }

    //Fills out the list view on the page with information
    public void populateGoalListView(){
        lv = (ListView) findViewById(R.id.lv_Ggoals);

        //Initialize a user object to access the username of the current user
        user User1 = new user();

        //Grabbing all users from the database
        List<user> usersArrayList = new ArrayList<>(db.getUsers());

        //Create a list to hold all the goal names of this user
        ArrayList<String> listData = new ArrayList<>();

        //Using the currentUsername obtained on the login page find the current user's ID
        for(int i = 0; i <= usersArrayList.size() - 1; i++){

            //Finding the user object that contains the information of the current user
            if(usersArrayList.get(i).getUserUsername().compareTo(User1.currentUserName) == 0){
                User1.currentUserID = usersArrayList.get(i).getId();

                //Populate listData with all the goal names for this user's goals if the user has goals saved
                if(!TextUtils.isEmpty(usersArrayList.get(i).getUserGoalNames())){

                    String goalNames = usersArrayList.get(i).getUserGoalNames().toUpperCase();
                    String goalName;

                   while(goalNames.compareTo("&") != 0) {
                       goalName = goalNames.substring(1, goalNames.indexOf("&",goalNames.indexOf("&") + 1));

                       listData.add(goalName);

                       goalNames = goalNames.substring(goalNames.indexOf("&",goalNames.indexOf("&") + 1));
                   }
                }
            }
        }

        //Populate the listview with the items in the listData list
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    //When an item on the list view is clicked
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
        //Initialize a user object to access the username of the current user
        user User1 = new user();
        user.currentUserGoalNumber = position + 1;

        //Creates and fills a list of the users in the database
        List<user> usersArrayList = new ArrayList<>(db.getUsers());

        //Using the currentUserID to find the current user object in the list
        for(int i = 0; i <= usersArrayList.size() - 1; i++){

            //Finding the user object that contains the information of the current user
            if(usersArrayList.get(i).getId() == User1.currentUserID){


                //Getting the correct goal name for the goal clicked
                String goalNames = usersArrayList.get(i).getUserGoalNames();
                String goalName = "Title";

                for(int j = 0; j < position + 1; j ++){
                    goalName = goalNames.substring(1, goalNames.indexOf("&",goalNames.indexOf("&") + 1));

                    goalNames = goalNames.substring(goalNames.indexOf("&",goalNames.indexOf("&") + 1));
                }



                //timeAmount = usersArrayList.get(i).getUserGoalTime().substring(0,usersArrayList.get(i).getUserGoalTime().indexOf("-"));
                //String timeType = usersArrayList.get(i).getUserGoalTime().substring(usersArrayList.get(i).getUserGoalTime().indexOf("-")+1);


                //Creates and displays floating message box with goal information
                showAlertDialog(view,goalName.toUpperCase(),
                        "Description : \n" + "   " + usersArrayList.get(i).getUserGoalDescriptions() + "\n\n" +
                                //"Time Allowed : \n" + "   " + timeAmount + " " + timeType + "\n\n" +
                                "Time Remaining : \n" + "   " + timeRemaining + "\n\n" );
            }
        }
    }

    //Creates and displays pop up text vox with two buttons
    public void showAlertDialog(final View v, final String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        //When the Delete button on the pop up box is pressed
        alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                showAlertDialog2(v,"Are you sure you want to delete?","");

            }
        });

        //When the Edit button on the pop up box is pressed
        alertDialog.setNegativeButton("Edit", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                gotoEditGoalActivity(v);
            }
        });
        alertDialog.show();
    }

    //Displays pop up box with two buttons
    public void showAlertDialog2(final View v, final String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //Sets message pop up box content
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        //If the Delete button is pressed inside the pop up text box
        alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

                //Create and fill a list with the users in the database
                List<user> usersArrayList = new ArrayList<>(db.getUsers());
                user User1 = new user();

                //Iterate as many times as the amount of users
                for(int i = 0; i < db.getUserCount().getCount() ; i++){

                    if(User1.currentUserID == usersArrayList.get(i).getId()) {

                        //Getting the correct goal name for the goal clicked
                        String originalGoalNames = usersArrayList.get(i).getUserGoalNames();
                        String part2 = usersArrayList.get(i).getUserGoalNames();
                        String part1 = "fail";


                        String finalGoalNames = "";
                        String goalName = "Title";

                        for(int j = 0; j < User1.currentUserGoalNumber; j ++){
                            part2 = part2.substring(part2.indexOf("&",part2.indexOf("&") + 1));
                        }


                        part1 = originalGoalNames.substring(originalGoalNames.indexOf("&"),originalGoalNames.lastIndexOf("&",originalGoalNames.indexOf(part2)-1));
                        finalGoalNames = part1 + part2;

                        //Delete the goal name, description, and time entries for that user
                        usersArrayList.get(i).setUserGoalNames(finalGoalNames);
                        usersArrayList.get(i).setUserGoalDescriptions("l");
                        usersArrayList.get(i).setUserGoalTimes("l");

                        //Update the database with the changes made to that user
                        db.updateUser(usersArrayList.get(i));

                        //Display toast message
                        Toast.makeText(getApplicationContext(), "Deletion has been made" ,Toast.LENGTH_SHORT).show();
                    }
                }
                //Update the list view display of the current goals
                populateGoalListView();
            }
        });

        //If the Cancel button is pressed inside the pop up text box
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Toast.makeText(goalsMenu.this,"You have canceled the deletion", Toast.LENGTH_SHORT).show();
            }
        });

        //Make the pop up box visible
        alertDialog.show();
    }


    //Changes the visibility of the navigation bar
    public void changeBarVisibility(View view){
        if(navigationBar.getVisibility() == View.VISIBLE){
            navigationBar.setVisibility(View.GONE);
            btn_barVisibility.setText(">\n>\n>");

        }
        else{
            navigationBar.setVisibility(View.VISIBLE);
            btn_barVisibility.setText("<\n<\n<");
        }
    }

//    //Updates the remaining time on the timer
//    public void updateTimeLeft(int timeLeft){
//        timeRemaining = timeLeft;
//    }


    //LINK TO THE NEW GOAL PAGE THROUGH THE BUTTON-------------------------------
    public void gotoNewGoalActivity(View view) {
        Intent name = new Intent(this, newGoal.class);
        startActivity(name);
    }

    //LINK TO THE PROFILE PAGE THROUGH THE BUTTON-------------------------------
    public void gotoProfilePageActivity(View view) {
        Intent name = new Intent(this, profilePage.class);
        startActivity(name);
    }

    //LINK TO THE LOGIN PAGE THROUGH THE BUTTON-------------------------------
    public void gotoEditGoalActivity(View view) {
        Intent name = new Intent(this, EditGoal.class);
        startActivity(name);
    }

    //LINK TO THE HEALTH MENU PAGE THROUGH THE BUTTON-------------------------------
    public void gotoHealthCareMenuActivity(View view) {
        Intent name = new Intent(this, healthCareMenu.class);
        startActivity(name);
    }

    //LINK TO THE GOALS MENU PAGE THROUGH THE BUTTON-------------------------------
    public void gotoGoalsMenuActivity(View view) {
        Intent name = new Intent(this, goalsMenu.class);
        startActivity(name);
    }

    //LINK TO THE CONTACTS PAGE THROUGH THE BUTTON-------------------------------
    public void gotoContactsActivity(View view) {
        Intent name = new Intent(this, contactsPage.class);
        startActivity(name);
    }

    //LINK TO THE SETTINGS PAGE THROUGH THE BUTTON-------------------------------
    public void gotoSettingsActivity(View view) {
        Intent name = new Intent(this, settingsPage.class);
        startActivity(name);
    }

    //LINK TO THE LOGIN PAGE THROUGH THE BUTTON-------------------------------
    public void gotoLoginActivity(View view) {
        Intent name = new Intent(this, login.class);
        startActivity(name);
    }

}



