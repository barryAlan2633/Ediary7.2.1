package barryalan.ediary70;

/**
 * Created by Al on 10/30/2017.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class goalsMenu extends AppCompatActivity implements AdapterView.OnItemClickListener{

    final databaseHelper db = new databaseHelper(this);
    user User1 = new user();

    //Declare page components
    LinearLayout navigationBar;
    Button btn_barVisibility;
    ListView lv;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_menu);

        //Link components to the ones on the page
        navigationBar = (LinearLayout)findViewById(R.id.navigationBar);
        btn_barVisibility = (Button)findViewById(R.id.btn_barVisibility);
        lv = (ListView) findViewById(R.id.lv_Ggoals);

        //Fill out the list view with all the user's goals
        populateGoalListView();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
        String timeRemaining = "";
        User1.currentUserGoalNumber = position + 1;
        //Creates and fills a list of the users in the database
        List<user> usersArrayList = new ArrayList<>(db.getUsers());

        //Iterating through the whole list of users
        for(int i = 0; i <= usersArrayList.size() - 1; i++){

            //Finding the user object that contains the information of the current user
            if(usersArrayList.get(i).getId() == User1.currentUserID){

                //Getting the correct info for the current user
                String goalNames = usersArrayList.get(i).getUserGoalNames();
                String goalDescriptions = usersArrayList.get(i).getUserGoalDescriptions();
                String goalTimes = usersArrayList.get(i).getUserGoalTimes();

                //Declaring the strings that will hold the data of the goal that was clicked on
                String goalName = "";
                String goalDescription = "";
                String goalTime = "";

                //Getting the right values for the name,description, and time of the goal selected
                for(int j = 0; j < position + 1; j ++){
                    goalName = goalNames.substring(1, goalNames.indexOf("&",goalNames.indexOf("&") + 1));
                    goalNames = goalNames.substring(goalNames.indexOf("&",goalNames.indexOf("&") + 1));

                    goalDescription = goalDescriptions.substring(1, goalDescriptions.indexOf("&",goalDescriptions.indexOf("&") + 1));
                    goalDescriptions = goalDescriptions.substring(goalDescriptions.indexOf("&",goalDescriptions.indexOf("&") + 1));

                    goalTime = goalTimes.substring(1, goalTimes.indexOf("&",goalTimes.indexOf("&") + 1));
                    goalTimes = goalTimes.substring(goalTimes.indexOf("&",goalTimes.indexOf("&") + 1));

                }

                //Splitting goalTime Ex. 2-Minutes into timeAmount = "2" and timeType = "Minutes"
                String timeAmount = goalTime.substring(0,goalTime.indexOf("-"));
                String timeType = goalTime.substring(goalTime.indexOf("-") + 1);


                //Creates and displays floating message box with goal information
                goalInfoMessage(view,goalName.toUpperCase(),
                        "Description:\n" + "   " + goalDescription + "\n\n"
                                +"Time Allowed:\n" + "   " + timeAmount + " "    + timeType + "\n\n"
                                +"Time Remaining:\n" + "   " + timeRemaining + "\n\n" );
            }
        }
    }

    //Fills out the list view on the page with information
    public void populateGoalListView(){

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

        //Populate the listView with the items in the listData list
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    public void goalInfoMessage(final View v, final String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        //When the Delete button on the pop up box is pressed
        alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                deleteVerificationMessage(v,"Are you sure you want to delete?","");

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

    public void deleteVerificationMessage(final View v, final String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //Sets pop up box content
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        //If the Delete button is pressed inside the pop up text box
        alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

                //Create and fill a list with the users in the database
                List<user> usersArrayList = new ArrayList<>(db.getUsers());

                //Iterate as many times as the amount of users
                for(int i = 0; i < db.getUserCount().getCount() ; i++){

                    //Finding the user object for the current user
                    if(usersArrayList.get(i).getId() == User1.currentUserID ) {

                        //Retrieving the current user's data from the database
                        String goalNames = usersArrayList.get(i).getUserGoalNames();
                        String goalDescriptions = usersArrayList.get(i).getUserGoalDescriptions();
                        String goalTimes = usersArrayList.get(i).getUserGoalTimes();


                        //Removing the deleted data from the strings
                        goalNames = findNewString(goalNames);
                        goalDescriptions = findNewString(goalDescriptions);
                        goalTimes = findNewString(goalTimes);


                        //Update the goal name, description, and time entries for that user object
                        usersArrayList.get(i).setUserGoalNames(goalNames);
                        usersArrayList.get(i).setUserGoalDescriptions(goalDescriptions);
                        usersArrayList.get(i).setUserGoalTimes(goalTimes);

                        //Update the database with the changes made to that user object
                        db.updateUser(usersArrayList.get(i));

                        //Display toast message
                        Toast.makeText(getApplicationContext(),"Deletion has been made",Toast.LENGTH_SHORT).show();
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

    //Deletes the section of a string with index specified by the currentUserGoalNumber
    public String findNewString(String originalString){

        String part2 = originalString;
        String part1 = "";


        for(int j = 0; j < User1.currentUserGoalNumber; j ++) {

            //If you try to delete the last goal on the listView
            if(j + 1 == lv.getCount()){

                //If there is only one goal
                if(lv.getAdapter().getCount() == 1){
                    return "";
                }

                //If there is more than one goal
                return  originalString.substring(0, originalString.lastIndexOf("&", originalString.length() - 2) + 1);
            }

            //Part 2 is the string after the part that needs to be deleted
            part2 = part2.substring(part2.indexOf("&", part2.indexOf("&") + 1));
        }

        //Part 1 is the string before the part that needs to be deleted
        part1 = originalString.substring(originalString.indexOf("&"),originalString.lastIndexOf("&",originalString.indexOf(part2)-1));

        //If you try to delete anything besides the last goal on the listView
        return part1 + part2;
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

    //LINK TO THE NEW GOAL PAGE THROUGH THE BUTTON-------------------------------
    public void gotoNewGoalActivity(View view) {
        Intent name = new Intent(this, newGoal.class);
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

    //LINK TO THE CONTACTS PAGE THROUGH THE BUTTON-------------------------------
    public void gotoContactsActivity(View view) {
        Intent name = new Intent(this, contactsPage.class);
        startActivity(name);
    }

    //LINK TO THE LOGIN PAGE THROUGH THE BUTTON-------------------------------
    public void gotoLoginActivity(View view) {
        Intent name = new Intent(this, login.class);
        startActivity(name);
    }

    public void logoutVerificationMessage(final View v){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //Sets pop up box content
        alertDialog.setTitle("Are you sure you want to log out?");
        alertDialog.setMessage("You will be required to re-enter your login information to come back");

        //If the Log out button is pressed inside the pop up text box
        alertDialog.setPositiveButton("Log out", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                gotoLoginActivity(v);

            }
        });

        //If the Cancel button is pressed inside the pop up text box
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
            }
        });

        //Make the pop up box visible
        alertDialog.show();
    }
}



