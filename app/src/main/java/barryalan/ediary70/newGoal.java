package barryalan.ediary70;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class newGoal extends AppCompatActivity {

    Button save;
    Button viewData;

    EditText Name1;
    EditText Description1;
    EditText TimeAllowed1;

    TextView username;

    user User1 = new user();

    Spinner spin;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        final databaseHelper db = new databaseHelper(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);
        save = (Button) findViewById(R.id.btn_NGsave);
        viewData = (Button) findViewById(R.id.btn_NGView);

        Name1 = (EditText) findViewById(R.id.et_NGname);
        Description1 = (EditText) findViewById(R.id.et_NGgoalDescription);
        TimeAllowed1 = (EditText) findViewById(R.id.et_NGtimeAllowed);


        username = (TextView) findViewById(R.id.Gun);
        username.setText(User1.currentUserName);

        spin = (Spinner) findViewById(R.id.sp_NGgoalType);
        adapter = ArrayAdapter.createFromResource(this, R.array.GoalTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+ " selected", Toast.LENGTH_LONG).show();

                if (position == 1) {
                    Toast.makeText(getBaseContext(), "SHORT TERRRM", Toast.LENGTH_SHORT).show();

                }

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (position == 1) {
                            Toast.makeText(getBaseContext(), "you clicked save!", Toast.LENGTH_SHORT).show();


                            String un = username.getText().toString();
                            String Name = Name1.getText().toString();
                            String Description = Description1.getText().toString();
                            String Time = TimeAllowed1.getText().toString();


                            boolean isUpdate = db.updateGoals(Name, Description, Time, un);
                            if (isUpdate == true) {
                                Toast.makeText(newGoal.this, "Data Updated", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(newGoal.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }


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
                    buffer.append("UN : " + res.getString(1) + "\n");
                    buffer.append("Email : " + res.getString(2) + "\n");
                    buffer.append("Name : " + res.getString(9) + "\n");
                    buffer.append("Description of Goal : " + res.getString(10) + "\n");
                    buffer.append("Time Allowed for Goal : " + res.getString(11) + "\n");

                }
                showmessage("Short Term Goal", buffer.toString());


            }
        });

    }


    public void showmessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}