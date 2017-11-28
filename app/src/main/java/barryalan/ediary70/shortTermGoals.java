package barryalan.ediary70;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




/**
 * Created by Al on 10/30/2017.
 */

public class shortTermGoals extends AppCompatActivity {

    TextView username;

    databaseHelper db = new databaseHelper(this);
    user User1 = new user();
    EditText ShortTerm1;

    Button save;
    Button edit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_term_goals);

        ShortTerm1 = (EditText) findViewById(R.id.et_shortterm1);



        save = (Button) findViewById(R.id.btn_Ssave);
        edit = (Button) findViewById(R.id.btn_Sedit);

        username = (TextView) findViewById(R.id.tv_Susername);

        // final LoginDatabaseHelper db = new LoginDatabaseHelper(this);


        username.setText(User1.currentUserName);

        //IF SOMETHING IS DONE TO THE EDIT TEXT DISPLAY IT ON THE CORRESPONDING TEXT VIEW REAL TIME

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String Username = User1.currentUserName;

                String goal = ShortTerm1.getText().toString();



            }
        });



    }


    public void gotoGoalsListView(View view) {
        Intent name = new Intent(this, goalsMenu.class);
        startActivity(name);
    }

}

