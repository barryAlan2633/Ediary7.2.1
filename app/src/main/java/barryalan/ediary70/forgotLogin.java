package barryalan.ediary70;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class forgotLogin extends AppCompatActivity{

    Button btn_FsendEmail;
    EditText et_Fusername;
    EditText et_Femail;

    //EVERYTHING THAT HAPPENS IN FORGOT LOGIN PAGE RUNS FROM HERE-----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_login);

        btn_FsendEmail = (Button) findViewById(R.id.btn_FsendEmail);
        et_Fusername = (EditText) findViewById(R.id.et_Fusername);
        et_Femail = (EditText) findViewById(R.id.et_Femail);

        //CREATING A NEW INSTANCE OF THE DATABASE IN ORDER TO ACCESS IT
        final databaseHelper lbh = new databaseHelper(this);

        //IF THE BUTTON CALLED SEND EMAIL IS CLICKED
        btn_FsendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //IF YOU INPUTTED THE EMAIL IN THE FORGOT LOGIN PAGE
                //IF THE EMAIL ON THE TEXT BOX IS IN THE DATABASE
                if(lbh.isEmailTaken(et_Femail)) { //sends email to et_email.getText().toString()
                    Log.d("MARKER", "COMMENT"); //shows up on the android monitor down below on run time
                    //SEND EMAIL FROM HERE
                }
                //IF YOU INPUTTED THE USERNAME IN THE FORGOT LOGIN PAGE
                //IF THE USERNAME IS IN THE DATABASE
                else if(lbh.isUsernameTaken(et_Fusername)){
                    Log.d("MARKER","COMMENT");//shows up on the android monitor down below on run time
                    //SEND EMAIL FROM HERE
                }

            }
        });
    }
}

