package barryalan.ediary70;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class healthCareMenu extends AppCompatActivity {
    user User1 = new user();
    EditText allergies;
    EditText medication;
    EditText vitalSigns;
    EditText diet;
    EditText excercise;

    //S MEANS SHOW IN TEXT VIEW
    TextView Sallergies;
    TextView Smedication;
    TextView SvitalSigns;
    TextView Sdiet;
    TextView Sexcercise;

    TextView username;
    Button save;
    Button edit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care_menu);

        //TEXT BOXES TO INPUT INFO
        allergies = (EditText) findViewById(R.id.et_Hallergies);
        medication = (EditText) findViewById(R.id.et_Hmedication);
        vitalSigns = (EditText) findViewById(R.id.et_HvitalSigns);
        diet = (EditText) findViewById(R.id.et_Hdiet);
        excercise = (EditText) findViewById(R.id.et_HexcersicePlan);

        //TEXT BOXES TO DISPLAY
        Sallergies = (TextView) findViewById(R.id.tv_Hallergies);
        Smedication = (TextView) findViewById(R.id.tv_Hmedication);
        SvitalSigns = (TextView) findViewById(R.id.tv_HvitalSigns);
        Sdiet = (TextView) findViewById(R.id.tv_Hdiet);
        Sexcercise = (TextView) findViewById(R.id.tv_Hexcercise);

        //BUTTONS
        save = (Button) findViewById(R.id.btn_Hsave);
        edit = (Button) findViewById(R.id.btn_Hedit);

        //OPEN DATABASE
        final databaseHelper db = new databaseHelper(this);


        //IF SOMETHING IS DONE TO THE EDIT TEXT DISPLAY IT ON THE CORRESPONDING TEXT VIEW REAL TIME

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sallergies.setText("Allergies \n" + db.getUser(User1.currentUserName).getUserAllergies());

                db.getUser(User1.currentUserName).setUserAllergies(allergies.getText().toString());

                db.updateUser(db.getUser(User1.currentUserName));


                Smedication.setText("Medication \n" + medication.getText().toString());
                Sdiet.setText("Diet \n" + diet.getText().toString());
                Sexcercise.setText("Excercise \n" + excercise.getText().toString());
                SvitalSigns.setText("Vital Signs \n" + vitalSigns.getText().toString());

                Sallergies.setVisibility(View.VISIBLE);
                Smedication.setVisibility(View.VISIBLE);
                Sdiet.setVisibility(View.VISIBLE);
                Sexcercise.setVisibility(View.VISIBLE);
                SvitalSigns.setVisibility(View.VISIBLE);

                allergies.setVisibility(View.GONE);
                medication.setVisibility(View.GONE);
                diet.setVisibility(View.GONE);
                excercise.setVisibility(View.GONE);
                vitalSigns.setVisibility(View.GONE);

                save.setVisibility(View.GONE);
                edit.setVisibility(View.VISIBLE);
                //SAVE INFO TO DATABASE
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sallergies.setVisibility(View.INVISIBLE);
                Smedication.setVisibility(View.INVISIBLE);
                Sdiet.setVisibility(View.INVISIBLE);
                Sexcercise.setVisibility(View.INVISIBLE);
                SvitalSigns.setVisibility(View.INVISIBLE);

                allergies.setVisibility(View.VISIBLE);
                medication.setVisibility(View.VISIBLE);
                diet.setVisibility(View.VISIBLE);
                excercise.setVisibility(View.VISIBLE);
                vitalSigns.setVisibility(View.VISIBLE);

                save.setVisibility(View.VISIBLE);
                edit.setVisibility(View.INVISIBLE);
            }
        });
    }

    //LINK TO THE PROFILE PAGE THROUGH THE BUTTON-------------------------------
    public void gotoProfilePageActivity(View view) {
        Intent name = new Intent(this, profilePage.class);
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

