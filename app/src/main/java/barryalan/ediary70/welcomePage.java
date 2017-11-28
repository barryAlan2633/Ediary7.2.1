package barryalan.ediary70;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;

public class welcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        //Declare a task that will be completed after a certain time period
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                gotoLoginActivity(getWindow().getDecorView().getRootView());
            }
        };

        //Set a timer with the task to do t and the delay in miliseconds
        new Timer().schedule(t, 1000);


    }


    //LINKS THE WELCOME PAGE TO THE LOGIN PAGE
    public void gotoLoginActivity(View view) {
        Intent name = new Intent(this, login.class);
        startActivity(name);
    }

}