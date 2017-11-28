package barryalan.ediary70;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class healthCareMenu extends AppCompatActivity {
    //Declare a linear layout, Button
    LinearLayout navigationBar;
    Button btn_barVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care_menu);

        //Link navigationBar to the linear layout on the page
        navigationBar = (LinearLayout)findViewById(R.id.navigationBar);

        //Link navigationBar visibility button to the Button on the page
        btn_barVisibility = (Button)findViewById(R.id.btn_barVisibility);
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

