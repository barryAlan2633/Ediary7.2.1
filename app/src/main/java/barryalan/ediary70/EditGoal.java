package barryalan.ediary70;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
    Button save;
    EditText Name1;
    EditText Description1;
    EditText TimeAllowed1;
    Spinner sp_timeType;
    user User1 = new user();
    ArrayAdapter<CharSequence> adapter;

    //Declare database instance for access
    final databaseHelper db = new databaseHelper(this);

    //list that holds all the users in the database
    List<user> usersArrayList = new ArrayList<>(db.getUsers());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_goal);


        //Link buttons to the ones on the page
        save = (Button) findViewById(R.id.btn_EGsave);
        Name1 = (EditText) findViewById(R.id.et_EGname);
        Description1 = (EditText) findViewById(R.id.et_EGgoalDescription);
        TimeAllowed1 = (EditText) findViewById(R.id.et_EGtimeAllowed);

        sp_timeType = (Spinner) findViewById(R.id.sp_EGtimeType);
        adapter = ArrayAdapter.createFromResource(this, R.array.timeTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_timeType.setAdapter(adapter);

        //Using the currentUserID to find the current user object in the list
        for(int i = 0; i <= usersArrayList.size() - 1; i++){

            //Finding the user object that contains the information of the current user
            if(usersArrayList.get(i).getId() == User1.currentUserID){

                //Getting the correct goal name for the goal clicked
                String goalNames = usersArrayList.get(i).getUserGoalNames();
                String goalName = "Title";

                for(int j = 0; j < User1.currentUserGoalNumber ; j ++){
                    goalName = goalNames.substring(1, goalNames.indexOf("&",goalNames.indexOf("&") + 1));

                    goalNames = goalNames.substring(goalNames.indexOf("&",goalNames.indexOf("&") + 1));
                }


                //Pre-set the textviews with the data that is already stored for that goal so the user can edit it
                Name1.setText(goalName);
                Description1.setText(usersArrayList.get(i).getUserGoalDescriptions());

                String substr=usersArrayList.get(i).getUserGoalTime().substring(0,usersArrayList.get(i).getUserGoalTime().indexOf("-"));
                TimeAllowed1.setText(substr);

                String savedTimeType = usersArrayList.get(i).getUserGoalTime().substring(usersArrayList.get(i).getUserGoalTime().indexOf("-")+1);

                if(savedTimeType.compareTo("Minutes") == 0) {
                    sp_timeType.setSelection(0);
                }
                else if(savedTimeType.compareTo("Hours") == 0){
                    sp_timeType.setSelection(1);
                }
                else if(savedTimeType.compareTo("Days") == 0){
                    sp_timeType.setSelection(2);

                }
            }
        }



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Variables
                String typeOfTime = "No unit of time assigned";

                if(sp_timeType.getSelectedItemPosition() == 0){
                    typeOfTime = "Minutes";
                }
                else if(sp_timeType.getSelectedItemPosition() == 1){
                    typeOfTime = "Hours";
                }
                else if(sp_timeType.getSelectedItemPosition() == 2){
                    typeOfTime = "Days";
                }



            //Create and fill a list with the users in the database
            List<user> usersArrayList = new ArrayList<>(db.getUsers());
            user User1 = new user();

                //Iterate as many times as the amount of users
                for(int i = 0; i < db.getUserCount().getCount() ; i++) {

                    if (User1.currentUserID == usersArrayList.get(i).getId()) {

                        //Getting the correct goal name for the goal clicked
                        String originalGoalNames = usersArrayList.get(i).getUserGoalNames();
                        String part2 = usersArrayList.get(i).getUserGoalNames();
                        String part1 = "fail";


                        String finalGoalNames = "";
                        String goalName = "Title";

                        for (int j = 0; j < User1.currentUserGoalNumber; j++) {
                            part2 = part2.substring(part2.indexOf("&", part2.indexOf("&") + 1));
                        }

                        part1 = originalGoalNames.substring(originalGoalNames.indexOf("&"), originalGoalNames.lastIndexOf("&", originalGoalNames.indexOf(part2) - 1));

                        finalGoalNames = part1 + "&" +  Name1.getText().toString() + part2;


                        String un = User1.currentUserName;
                        String Name = finalGoalNames;
                        String Description = Description1.getText().toString();
                        String Time = TimeAllowed1.getText().toString() + "-" + typeOfTime;

                        //Checking if the data was updated
                        boolean isUpdate = db.updateGoals(Name, Description, Time, un);
                        if (isUpdate == true) {
                            Toast.makeText(EditGoal.this, "Data Updated", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(EditGoal.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }

                        //send to the goal menu page
                        gotoGoalMenuActivity(v);
                    }
                }
            }
        });
    }


    public void showmessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    //LINK TO THE LOGIN PAGE THROUGH THE BUTTON-------------------------------
    public void gotoGoalMenuActivity(View view) {
        Intent name = new Intent(this, goalsMenu.class);
        startActivity(name);
    }

}
