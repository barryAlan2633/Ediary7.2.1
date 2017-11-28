package barryalan.ediary70;

import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class newGoal extends AppCompatActivity {

    //Declare objects to link to the ones on the page
    Button save;
    Button viewData;
    EditText Name1;
    EditText Description1;
    EditText TimeAllowed1;
    Spinner spin;
    Spinner sp_timeType;

    //Create instance of user object to allow access to its functions
    user User1 = new user();

    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);

        final databaseHelper db = new databaseHelper(this);

        //Link objects with the ones on the page
        save = (Button) findViewById(R.id.btn_NGsave);
        viewData = (Button) findViewById(R.id.btn_NGView);
        Name1 = (EditText) findViewById(R.id.et_NGname);
        Description1 = (EditText) findViewById(R.id.et_NGgoalDescription);
        TimeAllowed1 = (EditText) findViewById(R.id.et_NGtimeAllowed);

        spin = (Spinner) findViewById(R.id.sp_NGgoalType);
        adapter = ArrayAdapter.createFromResource(this, R.array.GoalTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        sp_timeType = (Spinner) findViewById(R.id.sp_NtimeType);
        adapter = ArrayAdapter.createFromResource(this, R.array.timeTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_timeType.setAdapter(adapter);


        //Listen to see what was selected on the drop down spinner for goal type
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                //When pressing on the save button
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //If long term is selected on the drop down
                        if (position == 0) {
                        }
                        //If short term is selected on the drop down
                        else if (position == 1){

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

                            String username = User1.currentUserName;
                            String GoalName = Name1.getText().toString();
                            String GoalDescription = Description1.getText().toString();
                            String GoalTime = TimeAllowed1.getText().toString() + "-" + typeOfTime;


                            //checking if the data was updated
                            boolean isUpdate = db.addNewGoal(GoalName, GoalDescription, GoalTime, username);

                            if (isUpdate == true) {
                                Toast.makeText(newGoal.this, "Data Updated", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(newGoal.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                        gotoGoalMenuActivity(v);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


        //this was to make sure we were saving data and a way to view it
        //we dont have to keep this but I liked it for testing purposes
        //can be reusable in other pages to test
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = db.getUserCount();
                //if there is no data
                if (res.getCount() == 0) {
                    //show message
                    showmessage("Error", "No data found");
                    return;
                }

                //if there is data
                StringBuffer buffer = new StringBuffer();
                //moves cursor to next result
                while (res.moveToNext()) {
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("UN : " + res.getString(2) + "\n");
                    buffer.append("Email : " + res.getString(3) + "\n");
                    buffer.append("Name : " + res.getString(10) + "\n");
                    buffer.append("Description of Goal : " + res.getString(11) + "\n");
                    buffer.append("Time Allowed for Goal : " + res.getString(12) + "\n\n");
                }

                showmessage("Short Term Goal", buffer.toString());
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