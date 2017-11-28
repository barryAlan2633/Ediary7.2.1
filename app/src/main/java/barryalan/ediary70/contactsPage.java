package barryalan.ediary70;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class contactsPage extends AppCompatActivity {
    //Declare a linear layout, Button
    LinearLayout navigationBar;
    Button btn_barVisibility;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_page);

        //Link buttons to page
        navigationBar = (LinearLayout)findViewById(R.id.navigationBar);
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
