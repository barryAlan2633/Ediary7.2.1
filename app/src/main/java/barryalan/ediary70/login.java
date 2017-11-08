package barryalan.ediary70;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    user User1 = new user();
    Button btn_Lsignin;
    Button btn_LforgotLogin;
    EditText et_Lusername;
    EditText et_Lpassword;
    CheckBox cb_LpasswordVisibility;
    int numberOfTries = 0;

    //EVERYTHING THAT HAPPENS IN LOGIN PAGE RUNS FROM HERE-----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //CREATE AND LINK BUTTONS AND TEXT BOXES TO THE ONES ON THE DISPLAY
        btn_Lsignin = (Button) findViewById(R.id.btn_Lsignin);
        btn_LforgotLogin = (Button) findViewById(R.id.btn_LforgotLogin);
        et_Lusername = (EditText) findViewById(R.id.et_Lusername);
        et_Lpassword = (EditText) findViewById(R.id.et_Lpassword);
        cb_LpasswordVisibility = (CheckBox) findViewById(R.id.cb_LshowPassword);

        //IF THE BUTTON LINKED TO LOGIN IS CLICKED
        btn_Lsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CHECK IF THE USER INPUTTED A USERNAME AND PASSWORD
                if (!(isEmpty(et_Lusername)) & !(isEmpty(et_Lpassword))) {
                    if (isValid(et_Lusername, et_Lpassword)) { //CHECK IF INFO IS A VALID USERS'
                        //Log.e("hello", " got into onclick");
                        User1.setCurrentUserName(et_Lusername.getText().toString());
                        gotoProfilePageActivity(v);
                    }
                }
            }
        });


        //ALLOWS THE USE OF THE SHOW PASSWORD FEATURE IN THE LOGIN PAGE-----------------------------
        cb_LpasswordVisibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //CHECKBOX STATUS IS CHANGED FROM UNCHECKED TO CHECKED
                if (!isChecked) {
                    // SHOW PASSWORD
                    et_Lpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // MASK PASSWORD
                    et_Lpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    //CHECKS IF THE TEXT BOX IS EMPTY OR NOT--------------------------------------------------------
    public boolean isEmpty(EditText edittext) {

        String message;
        String hint = edittext.getHint().toString();
        switch (hint) {
            case "Name":
                message = "Enter a name";
                break;
            case "Password":
                message = "Enter a password";
                break;
            default:
                message = "Enter a value";
                break;
        }
        //VARIABLES FOR THE MESSAGE
        Context context = getApplicationContext();
        CharSequence text = "Please Enter all values";
        int duration = Toast.LENGTH_SHORT;

        //CREATES MESSAGE
        Toast toast = Toast.makeText(context, text, duration);

        String value = edittext.getText().toString();
        if (value.isEmpty()) {
            edittext.setError(message);//message is chosen on switch statement above
            toast.show(); //DISPLAYS MESSAGE
            return true;
        } else {
            return false;
        }

    }

    //CHECKS IF USERNAME AND PASSWORD ARE IN THE DATABASE AND MATCH EACHOTHER-----------------------
    public boolean isValid(EditText usernametext, EditText passwordtext) {

        //CREATING THE LOGIN SUCCESSFUL MESSAGE
        Context context = getApplicationContext();
        CharSequence text = "Login Successful!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        //CREATING THE LOGIN FAILED MESSAGE
        Context context1 = getApplicationContext();
        CharSequence text1 = "Login Failed!" + numberOfTries;
        int duration1 = Toast.LENGTH_SHORT;
        Toast toast1 = Toast.makeText(context1, text1, duration1);

        //CREATING A NEW INSTANCE OF THE DATABASE IN ORDER TO ACCESS IT
        databaseHelper lbh = new databaseHelper(this);

        //GET THE INPUTTED TEXT FROM THE TEXT BOXES
        String userName = usernametext.getText().toString();
        String password = passwordtext.getText().toString();

        // FETCH THE PASSWORD FROM THE DATABASE FROM THE RESPECTIVE USERNAME
        String storedPassword = lbh.getUserPassword(userName);

        //GIVES YOU THREE TRIES UNTIL THE SYSTEM ALLOWS FOR A FORGOT LOGIN
        if (numberOfTries > 1) {
            btn_LforgotLogin.setVisibility(View.VISIBLE);
        }
        //VALIDATE THE PASSWORD WITH THE ONE ON THE DATABASE
        if (password.equals(storedPassword)) {
            toast.show();
            return true;
        } else {
            toast1.show();
            numberOfTries++;
            return false;
        }
    }

    //LINK THE LOGIN PAGE TO THE PROFILE PAGE THROUGH THE BUTTON-------------------------------
    public void gotoProfilePageActivity(View view) {
        Intent name = new Intent(this, profilePage.class);
        startActivity(name);
    }

    //LINK THE LOGIN PAGE TO THE REGISTRATION PAGE THROUGH THE BUTTON-------------------------------
    public void gotoRegistrationActivity(View view) {
        Intent name = new Intent(this, registration.class);
        startActivity(name);
    }


    //LINK THE LOGIN PAGE TO THE FORGOT LOGIN PAGE--------------------------------------------------
    public void gotoForgotLoginActivity(View view) {
        Intent name = new Intent(this, forgotLogin.class);
        startActivity(name);
    }


}
