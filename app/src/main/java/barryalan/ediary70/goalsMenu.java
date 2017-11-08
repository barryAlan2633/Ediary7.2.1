package barryalan.ediary70;

/**
 * Created by Al on 10/30/2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class goalsMenu extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_menu);
    }

    //LINK TO THE PROFILE PAGE THROUGH THE BUTTON-------------------------------
    public void gotoNewGoalActivity(View view) {
        Intent name = new Intent(this, newGoal.class);
        startActivity(name);
    }
 //LINKS TO OTHER MAIN PAGES
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

    //button activities
    public void gotoShortTermGoalsActivity(View view) {
        Intent name = new Intent(this, shortTermGoals.class);
        startActivity(name);
    }
    public void gotoLongTermGoalsActivity(View view) {
        Intent name = new Intent(this, longTermGoals.class);
        startActivity(name);
    }
}



