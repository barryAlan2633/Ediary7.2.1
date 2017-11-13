package barryalan.ediary70;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Al on 10/30/2017.
 */

public class longTermGoals extends AppCompatActivity{

    user User1 = new user();


    EditText LongTerm1;


    TextView SLongTerm1;

    TextView username;

    Button save;
    Button edit;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_term_goals);

        LongTerm1 = (EditText) findViewById(R.id.et_LongTerm1);

        SLongTerm1 = (TextView) findViewById(R.id.tv_LongTerm1);

        save = (Button) findViewById(R.id.btn_Gsave);
        edit = (Button) findViewById(R.id.btn_Gedit);

        username = (TextView) findViewById(R.id.tv_Gusername);

        final databaseHelper db = new databaseHelper(this);


        username.setText(User1.currentUserName);

        //IF SOMETHING IS DONE TO THE EDIT TEXT DISPLAY IT ON THE CORRESPONDING TEXT VIEW REAL TIME

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //db.getUser(User1.currentUserName).setUserLongGoal(LongTerm1.getText().toString());


                SLongTerm1.setText("Long Term \n" + LongTerm1.getText().toString());



                SLongTerm1.setVisibility(View.VISIBLE);


                LongTerm1.setVisibility(View.GONE);



                save.setVisibility(View.GONE);
                edit.setVisibility(View.VISIBLE);
                //SAVE INFO TO DATABASE
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SLongTerm1.setVisibility(View.INVISIBLE);


                LongTerm1.setVisibility(View.VISIBLE);


                save.setVisibility(View.VISIBLE);
                edit.setVisibility(View.INVISIBLE);
            }
        });
    }




}

