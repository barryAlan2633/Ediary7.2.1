package barryalan.ediary70;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditGoal extends AppCompatActivity {

    databaseHelper db = new databaseHelper(this);
    user User1 = new user();

    //Declare page components
    Button btn_save;
    EditText et_name;
    EditText et_description;
    EditText et_timeAllowed;
    Spinner sp_timeType;
    ArrayAdapter<CharSequence> adapter;


    //List that will hold all the users in the database
    List<user> usersArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_goal);

        //Link components to the ones on the page
        btn_save = (Button) findViewById(R.id.btn_EGsave);
        et_name = (EditText) findViewById(R.id.et_EGname);
        et_description = (EditText) findViewById(R.id.et_EGgoalDescription);
        et_timeAllowed = (EditText) findViewById(R.id.et_EGtimeAllowed);

        sp_timeType = (Spinner) findViewById(R.id.sp_EGtimeType);
        adapter = ArrayAdapter.createFromResource(this, R.array.timeTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_timeType.setAdapter(adapter);

        //List that will holds all the users in the database
        usersArrayList = new ArrayList<>(db.getUsers());

        //Filling out the page's textViews with the existing info of the goal being edited
        prePopulateFields();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Saves all data in the page textViews into the database
                saveEditedData();

                //Send user to the goals menu page
                gotoGoalMenuActivity(v);
            }
        });
    }


    //Saves all data in the page textViews into the database
    public void saveEditedData(){
        //Finding out what the type of time inputted into the spinner is
        String typeOfTime = "";

        if(sp_timeType.getSelectedItemPosition() == 0){
            typeOfTime = "Minutes";
        }
        else if(sp_timeType.getSelectedItemPosition() == 1){
            typeOfTime = "Hours";
        }
        else if(sp_timeType.getSelectedItemPosition() == 2){
            typeOfTime = "Days";
        }

        //Gathering the data inputted into the test field in the page
        String editedName = et_name.getText().toString();
        String editedDescription = et_description.getText().toString();
        String editedTime = et_timeAllowed.getText().toString() + "-" + typeOfTime;

        //Iterate as many times as the amount of users
        for(int i = 0; i < db.getUserCount().getCount(); i++) {

            //Finding the user object that contains the information of the current user
            if (User1.currentUserID == usersArrayList.get(i).getId()) {

                //Retrieving the current user's data from the database
                String goalNames = usersArrayList.get(i).getUserGoalNames();
                String goalDescriptions = usersArrayList.get(i).getUserGoalDescriptions();
                String goalTimes = usersArrayList.get(i).getUserGoalTimes();

                //Removing the deleted data from the strings
                goalNames = findEditedString(goalNames, editedName);
                goalDescriptions = findEditedString(goalDescriptions, editedDescription);
                goalTimes = findEditedString(goalTimes, editedTime);


                String username = User1.currentUserName;
                String name = goalNames;
                String description = goalDescriptions;
                String time = goalTimes;

                //Checking if the data was updated
                boolean isUpdate = db.updateGoals(name, description, time, username);
                if (isUpdate == true) {
                    Toast.makeText(EditGoal.this, "Data Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EditGoal.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //Filling out the page's textViews with the existing info of the goal being edited
    public void prePopulateFields(){

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
                for(int j = 0; j < User1.currentUserGoalNumber; j ++){

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



                //Pre-set the textViews with the data that is already stored for that goal so the user can edit it
                et_name.setText(goalName);
                et_description.setText(goalDescription);
                et_timeAllowed.setText(timeAmount);

                if(timeType.compareTo("Minutes") == 0) {
                    sp_timeType.setSelection(0);
                }
                else if(timeType.compareTo("Hours") == 0){
                    sp_timeType.setSelection(1);
                }
                else if(timeType.compareTo("Days") == 0){
                    sp_timeType.setSelection(2);

                }
            }
        }
    }

    //Replaces the section of a string with index specified by the currentUserGoalNumber
    public String findEditedString(String originalString, String editedString){
        String part2 = originalString;
        String part1 = "";

        //Find the goal that we are trying to modify
        for(int j = 0; j < User1.currentUserGoalNumber; j ++) {
            part2 = part2.substring(part2.indexOf("&",part2.indexOf("&") + 1));
        }


        if(part2.compareTo("&") == 0){
            part1 = originalString.substring(0,originalString.lastIndexOf("&",originalString.lastIndexOf("&")-1));
        }
        else{
            part1 = originalString.substring(0,originalString.lastIndexOf("&",originalString.indexOf(part2))-1);
        }

        if(part1.compareTo("") == 0){
            part1 = "&";
        }
        Toast.makeText(getApplicationContext(),part1 + " + " + part2,Toast.LENGTH_SHORT).show();

        return part1 + editedString + part2;


    }

    //LINK TO THE LOGIN PAGE THROUGH THE BUTTON-------------------------------
    public void gotoGoalMenuActivity(View view) {
        Intent name = new Intent(this, goalsMenu.class);
        startActivity(name);
    }

}
