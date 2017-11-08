package barryalan.ediary70;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class welcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }


    //LINKS THE WELCOME PAGE TO THE LOGIN PAGE
    public void gotoLoginActivity(View view) {
        Intent name = new Intent(this, login.class);
        startActivity(name);
    }
}